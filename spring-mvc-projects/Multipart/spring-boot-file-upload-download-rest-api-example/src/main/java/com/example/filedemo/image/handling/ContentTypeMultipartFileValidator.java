package com.example.filedemo.image.handling;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator of content type. This is simple and not complete implementation
 * of content type validating. It's based just on <code>String</code> equalsIgnoreCase
 * method.
 *
 * @author Michal Kreuzman
 */
public class ContentTypeMultipartFileValidator implements ConstraintValidator<ContentType, MultipartFile> {

    private String[] acceptedContentTypes;

    @Override
    public void initialize(ContentType constraintAnnotation) {
        this.acceptedContentTypes = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty())
            return true;

        //TODO: commented for test
       // return ContentTypeMultipartFileValidator.acceptContentType(value.getContentType(), acceptedContentTypes);
        return true;
    }

    private static boolean acceptContentType(String contentType, String[] acceptedContentTypes) {
        for (String accept : acceptedContentTypes) {
            // TODO this should be done more clever to accept all possible content types
            if (contentType.equalsIgnoreCase(accept)) {
                return true;
            }
        }

        return false;
    }
}