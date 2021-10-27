package com.example.filedemo.controller;

import com.example.filedemo.model.ImageModel;
import com.example.filedemo.model.Person;
import com.example.filedemo.payload.ImageUriResponse;
import com.example.filedemo.payload.UploadFileResponse;
import com.example.filedemo.service.FileStorageService;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.xml.sax.ContentHandler;
import reactor.core.publisher.Flux;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

     @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = getDownloadUrtiString(fileName);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/downloadFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageUriResponse> downloadFile(HttpServletRequest request) {

        // Load file as Resource
        //TODO: fo test
        String fileName = "noname";
        String fileName2 = "noname2";
        String fileName3 = "noname3";

        List downloadUriList = Stream.of(
                getDownloadUrtiString(fileName),
                getDownloadUrtiString(fileName2),
                getDownloadUrtiString(fileName3)
        ).collect(Collectors.toList());

        ImageUriResponse imageDownloadListResponse = new ImageUriResponse(downloadUriList);
        return ResponseEntity.ok().body(imageDownloadListResponse);
    }

    private String getDownloadUrtiString(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
    }

    @RequestMapping(value = "/test")
    public void downloadZip(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        try {

            Path zipPath = fileStorageService.getPath("noname");

            //String zioPath
            File file = new File(zipPath.toString());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + System.currentTimeMillis() + ".zip" + "\"");
            InputStream is = new FileInputStream(file);
            FileCopyUtils.copy(IOUtils.toByteArray(is), response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * +
     * Download several files Only firefox     *
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/test-mixed")
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Set the response type and specify the boundary string
        response.setContentType("multipart/x-mixed-replace;boundary=END");

        // Set the content type based on the file type you need to download
        String contentType = "Content-type: image/jpg";

        // List of files to be downloaded
        List<File> files = new ArrayList<>();
        Path zipPath = fileStorageService.getPath("noname");
        Path zipPath2 = fileStorageService.getPath("noname2");
        Path zipPath3 = fileStorageService.getPath("noname3");

        files.add(new File(zipPath.toString()));
        files.add(new File(zipPath2.toString()));
        files.add(new File(zipPath3.toString()));

        ServletOutputStream out = response.getOutputStream();

        // Print the boundary string
        out.println();
        out.println("--END");

        for (File file : files) {

            // Get the file
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);

            } catch (FileNotFoundException fnfe) {
                // If the file does not exists, continue with the next file
                System.out.println("Could not find file " + file.getAbsolutePath());
                continue;
            }

            BufferedInputStream fif = new BufferedInputStream(fis);

            // Print the content type
            out.println(contentType);
            out.println("Content-Disposition: attachment; filename=" + file.getName());
            out.println();

            System.out.println("Sending file " + file.getName());

            // Write the contents of the file
            int data = 0;
            while ((data = fif.read()) != -1) {
                out.write(data);
            }
            fif.close();

            // Print the boundary string
            out.println();
            out.println("--END");
            out.flush();
            System.out.println("Finished sending file " + file.getName());
        }

        // Print the ending boundary string
        out.println("--END--");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response) throws IOException {
        InputStream in =
                fileStorageService.loadFileAsResource("noname").getInputStream();

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(value = "/image-response-entity", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = fileStorageService.loadFileAsResource("noname").getInputStream();
        byte[] media = IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Test. get as resource
     *
     * @return
     */
    @RequestMapping(value = "/image-resource", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getImageAsResource() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.parse("attachment"));
        Resource resource =
                fileStorageService.loadFileAsResource("noname");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    /**
     * Downloading Multiple Files As Zip
     *
     * @author JavaDigest
     */
    @RequestMapping(value = "/test-zip")
    public void testZip(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set the content type based to zip
        response.setContentType("Content-type: text/zip");
        response.setHeader("Content-Disposition",
                "attachment; filename=mytest.zip");

        // List of files to be downloaded
        List<File> files = new ArrayList();
        Path zipPath = fileStorageService.getPath("noname");
        Path zipPath2 = fileStorageService.getPath("noname2");
        Path zipPath3 = fileStorageService.getPath("noname3");

        files.add(new File(zipPath.toString()));
        files.add(new File(zipPath2.toString()));
        files.add(new File(zipPath3.toString()));

        ServletOutputStream out = response.getOutputStream();
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

        for (File file : files) {

            System.out.println("Adding file " + file.getName());
            zos.putNextEntry(new ZipEntry(file.getName()));

            // Get the file
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);

            } catch (FileNotFoundException fnfe) {
                // If the file does not exists, write an error entry instead of
                // file
                // contents
                zos.write(("ERROR: Could not find file " + file.getName())
                        .getBytes());
                zos.closeEntry();
                System.out.println("Could not find file "
                        + file.getAbsolutePath());
                continue;
            }

            BufferedInputStream fif = new BufferedInputStream(fis);

            // Write the contents of the file
            int data = 0;
            while ((data = fif.read()) != -1) {
                zos.write(data);
            }
            fif.close();

            zos.closeEntry();
            System.out.println("Finished adding file " + file.getName());
        }

        zos.close();
    }

    @GetMapping(value = "/stream")
    ResponseEntity<Flux<byte[]>> streamObjects() {
        Flux<byte[]> flux = Flux.fromStream(fetchImageFrame()).delayElements(Duration.ofSeconds(5));
        HttpHeaders headers = HttpHeaders.writableHttpHeaders(HttpHeaders.EMPTY);
        headers.add("Content-Type", "multipart/x-mixed-replace; boundary=--icecream");

        return new ResponseEntity<>(flux, headers, HttpStatus.OK);
    }

    private Stream<byte[]> fetchImageFrame() {
        Stream stream = Stream.of(
                //  load("noname"),
                load("noname2"),
                load("noname3")
        );

        return stream;

    }

    private byte[] load(String name) {
        try {
            Path path = fileStorageService.getPath(name);

            byte[] raw = Files.readAllBytes(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            String headers =
                    "--icecream\r\n" +
                            "Content-Type: image/png\r\n" +
                            "Content-Length: " + raw.length + "\r\n\r\n";
            bos.write(headers.getBytes());
            bos.write(raw);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------------
    private String loadBase(String name) {
        try {
            Path path = fileStorageService.getPath(name);

            byte[] raw = Files.readAllBytes(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            String headers =
                    "--icecream\r\n" +
                            "Content-Type: image/png\r\n" +
                            "Content-Length: " + raw.length + "\r\n\r\n";
            bos.write(headers.getBytes());
            bos.write(raw);

            return Base64.getEncoder().encodeToString(bos.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/my-images", method = RequestMethod.GET, produces = "image/jpg")
    public @ResponseBody
    byte[] getFile() {
        try {

            InputStream is =
                    fileStorageService.loadFileAsResource("noname").getInputStream();
            // Retrieve image from the classpath.

            // Prepare buffered image.
            BufferedImage img = ImageIO.read(is);

            // Create a byte array output stream.
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // Write to output stream
            ImageIO.write(img, "jpg", bao);

            return bao.toByteArray();
        } catch (IOException ex) {
            logger.error(ex.toString());
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/upload-valid-file")
    public UploadFileResponse uploadValidFile(
            @RequestParam("file") @Valid @NonNull   MultipartFile file)
            throws IOException {
        // step 1 базовый вариант с итика
        Tika tika = new Tika();
        String mimeType = tika.detect(file.getBytes());
        System.out.println("--> Tika mime type : " + mimeType);

        //step 2
        String contentType = file.getContentType();
        System.out.println("--> content-type: " + contentType);
//---------------------
        try (InputStream tikaStream = TikaInputStream.get(file.getBytes())) {
            TikaConfig tikaConfig = new TikaConfig();
            org.apache.tika.mime.MediaType mediaType = tikaConfig.getDetector().detect(tikaStream, new Metadata());

            System.out.println("org.apache.tika.mime.MediaType: " + contentType);
        } catch (TikaException e) {
            e.printStackTrace();
        }

        //все что можно вытянуть
        displayFileInfo(file);

        MediaType currr = MediaType.valueOf(mimeType);
        String subType = currr.getSubtype();
        System.out.println("subtype : " + subType);

        List<String> correctImageFormat= Arrays.asList("JPG", "JPEG", "PNG", "BMP");
        boolean res =  correctImageFormat.contains(currr.getSubtype().toUpperCase());

        fileStorageService.validate(file);

        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = getDownloadUrtiString(fileName);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    private void displayFileInfo(@RequestParam("file") MultipartFile file) {
        try {
            // is = new FileInputStream(file.getInputStream());

            ContentHandler contenthandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
            Parser parser = new AutoDetectParser();
            ParseContext parseContext = new ParseContext();
            // OOXMLParser parser = new OOXMLParser();
            parser.parse(file.getInputStream(), contenthandler, metadata, parseContext);
            System.out.println("Mime: " + metadata.get(Metadata.CONTENT_TYPE));
            System.out.println("Title: " + metadata.get(Metadata.TITLE));
            System.out.println("Author: " + metadata.get(Metadata.AUTHOR));
            System.out.println("content: " + contenthandler.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: complete
    @PostMapping("/upload-valid-file-model")
    public void uploadValidFileDto(@RequestBody  ImageModel imageModel)
            throws IOException {

        logger.debug("Multiple file upload! With UploadModel");

    }

    @PostMapping("/upload-valid-file-model2")
    public void uploadValidFileDto2( @RequestParam("file") @Valid  MultipartFile multipartFile ,
                                     @RequestParam("owner_id") String ownerId){

        int a = 109;


    }

    @PostMapping("/upload-valid-file-model3")
    public void uploadValidFileDto3( @Validated Person bucket ){

        int a = 109;


    }


}
