package com.example.filedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FilenameFilter;

@Configuration
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {

    //String uploadDirectory= System.getProperty("user.home") + "/images";

    public static String uploadDirectory= System.getProperty("user.home") + "/images/";

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/images/**").addResourceLocations("file:" + uploadDirectory+"\\");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String[] directories = listDirectories(uploadDirectory);
        for (String subDir : directories){


            System.out.println("----------------------------------" );
            String path = new File(uploadDirectory+subDir).getAbsolutePath()+"/";
            System.out.println("path : " + path );

            registry
                    .addResourceHandler("/media/" + subDir+"/**")
                    .addResourceLocations("file:"+path);
        }

        // TODO: for testing
//        registry
//                .addResourceHandler("/images/33/**")
//                .addResourceLocations("file:"+uploadDirectory+"33/");
//
//        registry
//                .addResourceHandler("/images/34/**")
//                .addResourceLocations("file:"+uploadDirectory+"34/");
//
//        registry
//                .addResourceHandler("/images/35/**")
//                .addResourceLocations("file:"+uploadDirectory+"35/");

        int aaa =10;
        // работает
        //http://localhost:8080//images2/2222
//        registry
//                .addResourceHandler("/images2/**")
//                .addResourceLocations("file:"+uploadDirectory+"33/");

        // home/images
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:"+uploadDirectory);
    }


    private String[] listDirectories(String root){
        File file = new File(root);
        String[] directories = file.list(new FilenameFilter() {

            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        return  directories;
    }


}