package com.se.resttemplatedemo.controller;


import com.se.resttemplatedemo.componentns.MultipartInputStreamFileResource;
import com.se.resttemplatedemo.componentns.UploadClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/rest-templ")
public class UploadFileController {

    @Autowired
    private UploadClient uploadClient;

    private final RestTemplate restTemplate;

    public UploadFileController() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        this.restTemplate = new RestTemplate(requestFactory);
    }


    @ApiOperation(value = "upload file base .", nickname = "create",
            notes = "Created files in system tmp directory and send with Rest template client to 8000.", tags = {})
    @GetMapping("/upload-tmp-base")
    public void uploadBase() throws IOException {
        uploadClient.uploadTmp();
    }

    @PostMapping("/upload-param")
    public ResponseEntity<?> uploadFileWithParam(@RequestParam("customerId") int customerId,
                                                 @RequestPart("file") MultipartFile multipartFile) {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("customerId", customerId);
        try {
            parts.add("file",
                    new MultipartInputStreamFileResource(multipartFile.getInputStream(), multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parts, headers);
        ResponseEntity response = restTemplate.exchange("http://localhost:8000/upload/upload-param", HttpMethod.POST, request, Void.class);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/upload-multiparts")
    public ResponseEntity<?> uploadFilesWithParam(@RequestParam("customerId") int customerId,
                                                  @RequestPart("files") MultipartFile[] multipartFile) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();


        for (MultipartFile item : multipartFile) {
            body.add("images", item);

        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String serverUrl = "http://localhost:8000/upload/upload-param/multiplefileupload/";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(serverUrl, requestEntity, String.class);

        return response;
    }
}
