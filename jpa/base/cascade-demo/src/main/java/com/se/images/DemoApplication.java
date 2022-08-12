package com.se.images;

import com.se.images.entities.Image;
import com.se.images.entities.ImageTag;
import com.se.images.entities.ImageTagId;
import com.se.images.entities.Tag;
import com.se.images.repo.ImageRepo;
import com.se.images.repo.ImageTagRepo;
import com.se.images.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    ImageRepo imageRepo;

    @Autowired
    ImageTagRepo imageTagRepo;

    @Autowired
    TagRepo tagRepo;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\t*********** many-to-many- demo ***********  ");

        imageTagRepo.deleteAllInBatch();
        imageRepo.deleteAllInBatch();
        tagRepo.deleteAllInBatch();

        correctSaveImage();
        storeImage();

        editImage();
    }

    private void editImage() {
        Tag tag11 = new Tag("tag11");
        Tag tag21 = new Tag("tag21");
        Tag tag31 = new Tag("tag31");

        tagRepo.saveAll(Arrays.asList(tag11,tag21,tag31));

        List<Image> imageList = imageRepo.findAll();
        Image imageFromBD = imageList.get(1);
        imageFromBD.setImageUrl("IMAGE URL");

        Tag tagFromDb = (tagRepo.findAllByTagLike("tag2")).get();
        imageFromBD.removeTag(tagFromDb);

        ImageTagId imageTagId = new ImageTagId(imageFromBD.getId(), tagFromDb.getId() );
        ImageTag imgTagDB = imageTagRepo.findById(imageTagId).get();

        System.out.println("== Found image tag :" + imgTagDB +"\n");
        try {

            imageTagRepo.delete(imgTagDB);
            int aftr = 0;

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void correctSaveImage(){
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Tag tag3 = new Tag("tag3");

        tagRepo.saveAll(Arrays.asList(tag1,tag2));

        System.out.println("--- The current tags: ");
        tagRepo.findAll().stream().forEach(System.out::println);
        System.out.println("-----------------------------------");

        ///////////////////////////////////////////////
        Image image = new Image( "originName",  "path3",  "imageUr");

        ImageTag imageTag = new ImageTag(image, tag3);
        tag3.addImageTag(imageTag);
        image.addImageTag(imageTag);


        imageRepo.saveAndFlush(image);

        // HERE
        imageTagRepo.save(imageTag);
        int after = 0;
//------------------------
    }

    /**
     * detached entity passed to persist: com.se.images.entities.Tag; nested exception is org.hibernate.PersistentObjectException: detached entity passed to persist
     */
    private void storeImage() {
        Tag tag3= new Tag("tag3");
        Tag tagFromDb = tagRepo.findAllByTagLike("tag3").get();
        Tag tagFromDb2= tagRepo.findAllByTagLike("tag2").get();

        Image image2 = new Image( "TEST_THIS_IMAGE2!!!!",  "path3",  "imageUr");

        ImageTag imageTag2 = new ImageTag(image2, tagFromDb);
        tagFromDb.addImageTag(imageTag2);
        image2.addImageTag(imageTag2);

        ImageTag imageTag = new ImageTag(image2, tagFromDb2);
        tagFromDb2.addImageTag(imageTag);
        image2.addImageTag(imageTag);

        try {
            //image2.removeImageTag(imageTag2);
            imageRepo.save(image2);
        }catch (Exception ex){
            System.out.println("Gor exception while saving ");
            ex.printStackTrace();

        }

        imageTagRepo.save(imageTag);
        imageTagRepo.save(imageTag2);

        System.out.println("--- The current image: ");
        imageRepo.findAll().stream().forEach(System.out::println);
        System.out.println("-----------------------------------");
    }


    private void deatachedTagError() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Tag tag3 = new Tag("tag3");

        tagRepo.saveAll(Arrays.asList(tag1,tag2,tag3));

        System.out.println("--- The current tags: ");
        tagRepo.findAll().stream().forEach(System.out::println);
        System.out.println("-----------------------------------");

        Image image = new Image( "originName",  "path3",  "imageUr");

        ImageTag imageTag = new ImageTag(image, tag1);
        tag1.addImageTag(imageTag);
        image.addImageTag(imageTag);

        imageRepo.saveAndFlush(image);



        System.out.println("--- The current image: ");

        imageRepo.findAll().stream().forEach(System.out::println);
        System.out.println("-----------------------------------");
    }
}
