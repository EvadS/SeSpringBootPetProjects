package com.example.filedemo.controller;


import com.example.filedemo.image.handling.MyModelAttribute;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLConnection;

@RestController("/my-img")
public class ImageHandlerController {

    Logger logger = LoggerFactory.getLogger(ImageHandlerController.class);

    @ApiOperation(value = "Upload file with validation. Only image/png ")
    @PostMapping("/upload")

//    public String processUploadWithModelAttribute(
//                                                   @Validated final MyModelAttribute myModelAttribute,
//                                                  final BindingResult result) throws IOException
    public ResponseEntity<ByteArrayResource> upload(@Validated  @ModelAttribute("formUpload") MyModelAttribute fileUpload,
                                                    BindingResult result,
                                                     HttpServletRequest request) throws IOException {

        int a = 10;
        if (result.hasErrors()) {
            // Error handling
          //  return "fileupload";

             throw  new FileNotFoundException("!!!!!!!!!!!!!!!");
        }

        BufferedImage img = ImageIO.read(fileUpload.getFile().getInputStream());
        BufferedImage scaled =getCompatibleImage2 (img,100,100); //scale


        //--------------------------------------------------
        //get the mimetype
        String mimeType = URLConnection.guessContentTypeFromName(fileUpload.getFile().getOriginalFilename());
        if (mimeType == null) {
            //unknown mimetype so set the mimetype to application/octet-stream
            mimeType = "application/octet-stream";
        }


        //----------------------------------------------------
        // TODO: how to now extention
        byte[] byteArray = toByteArrayAutoClosable(scaled, "png");
//-----------------------------
        ByteArrayResource resource = new ByteArrayResource(byteArray);
//---------------------------------
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        //----------------------------
        return ResponseEntity.ok()
               // .headers(header)
               // .contentLength(byteArray.length)
                .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    }



    @ApiOperation(value = "Upload file with validation. Only image/png.  Upload as a my model. MULTIPART_FORM_DATA_VALUE in swagger test")
    @PostMapping(value = "/upload/form-model", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void postFooAsMultiPart(@Valid @RequestBody  MyModelAttribute fileUpload) {
        long size = fileUpload.getFile().getSize();
        logger.info("----------------------------------------------------------");
        logger.info("file size:", size);
            int a =10;
    }

    @ApiOperation(value = "Upload as requests param. consumes MULTIPART_FORM_DATA_VALUE")
    @RequestMapping(path = "/upload/request-param", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody ResponseEntity<String> add2(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("brand") String brand) {
        System.out.println("File : " + file.getOriginalFilename()+ "\t file size:" + file.getSize()  + "\n" + name + "\n" + brand);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }


    @ApiOperation(value = "Upload as requests param. consumes _WITHOUT_ MULTIPART_FORM_DATA_VALUE")
    @RequestMapping(path = "/upload/request-param2", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> add22(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("brand") String brand) {
        System.out.println("File : " + file.getOriginalFilename()+ "\t file size:" + file.getSize()  + "\n" + name + "\n" + brand);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @ApiOperation(value = "Upload files as a array. consumes MULTIPART_FORM_DATA_VALUE")
    @RequestMapping(path = "/files", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody ResponseEntity<String> add3(
            @RequestParam("file") MultipartFile[] file,
            @RequestParam("name") String name,
            @RequestParam("brand") String brand) {
        System.out.println(file[0].getOriginalFilename() + "\n" + name + "\n" + brand + "files count : " + file.length);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }


    // ------------------- MAYBE VERSION FOR DSOME DEVICE -- >
    private BufferedImage scale(BufferedImage source, double ratio) {
        int w = (int) (source.getWidth() * ratio);
        int h = (int) (source.getHeight() * ratio);
        BufferedImage bi = getCompatibleImage(w, h);
        Graphics2D g2d = bi.createGraphics();
        double xScale = (double) w / source.getWidth();
        double yScale = (double) h / source.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
        g2d.drawRenderedImage(source, at);
        g2d.dispose();
        return bi;
    }

    private BufferedImage getCompatibleImage(int w, int h) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        return image;
    }

    private BufferedImage getCompatibleImage2(BufferedImage sourceImage, int w, int h) {


            int width = sourceImage.getWidth();
            int height = sourceImage.getHeight();

            if(width>height){
                float extraSize=    height-100;
                float percentHight = (extraSize/height)*100;
                float percentWidth = width - ((width/100)*percentHight);
                BufferedImage img = new BufferedImage((int)percentWidth, 100, BufferedImage.TYPE_INT_RGB);
                Image scaledImage = sourceImage.getScaledInstance((int)percentWidth, 100, Image.SCALE_SMOOTH);
                img.createGraphics().drawImage(scaledImage, 0, 0, null);
                BufferedImage img2 = new BufferedImage(100, 100 ,BufferedImage.TYPE_INT_RGB);
                img2 = img.getSubimage((int)((percentWidth-100)/2), 0, 100, 100);
               return img2;
//                ImageIO.write(img2, "jpg", new File(outputFile));
            }else{
                float extraSize=    width-100;
                float percentWidth = (extraSize/width)*100;
                float  percentHight = height - ((height/100)*percentWidth);
                BufferedImage img = new BufferedImage(100, (int)percentHight, BufferedImage.TYPE_INT_RGB);
                Image scaledImage = sourceImage.getScaledInstance(100,(int)percentHight, Image.SCALE_SMOOTH);
                img.createGraphics().drawImage(scaledImage, 0, 0, null);
                BufferedImage img2 = new BufferedImage(100, 100 ,BufferedImage.TYPE_INT_RGB);
                img2 = img.getSubimage(0, (int)((percentHight-100)/2), 100, 100);



                return img2;
            }
    }
// <<-------------------
    private static byte[] toByteArrayAutoClosable(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
    }

}
