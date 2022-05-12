package com.example.filedemo.constraint;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageFileValidator implements ConstraintValidator<ValidImage, MultipartFile> {


    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        System.out.println("HERE :::");
        boolean result = true;

        return false;
      //  String contentType =;
       // return  (Objects.isNull(multipartFile)  || !isSupportedContentType( multipartFile.getContentType())) ;//{
        //    context.disableDefaultConstraintViolation();

        //    context.buildConstraintViolationWithTemplate(
       //             "Only PNG or JPG images are allowed.")
         //           .addConstraintViolation();

      //      result = false;
     //   }

        // добавить сообщение
//        context
//                .buildConstraintViolationWithTemplate("User " + "213123213213" + "already exists!")
//                .addConstraintViolation();

      //  return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
}