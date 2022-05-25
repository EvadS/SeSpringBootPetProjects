package com.se.sample.service;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Avatar;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class AvatarService {

    private AmazonRekognition rekognitionClient;
    private static Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Value("${aws.accessKey}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.region}")
    private String region;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        rekognitionClient = new AmazonRekognitionClient(credentials);
        rekognitionClient.setRegion(RegionUtils.getRegion(region));
    }


    public List<String> getList(String id) {
        logger.debug("Obtaining list for: " + id);
        switch (id) {
            case "TOP":
                return Stream.of(Avatar.TOP.values()).map(Avatar.TOP::name).collect(Collectors.toList());
            case "ACCESSORIES":
                return Stream.of(Avatar.ACCESSORIES.values()).map(Avatar.ACCESSORIES::name).collect(Collectors.toList());
            case "HAIRCOLOR":
                return Stream.of(Avatar.HAIRCOLOR.values()).map(Avatar.HAIRCOLOR::name).collect(Collectors.toList());
            case "FACIALHAIR":
                return Stream.of(Avatar.FACIALHAIR.values()).map(Avatar.FACIALHAIR::name).collect(Collectors.toList());
            case "CLOTHE":
                return Stream.of(Avatar.CLOTHE.values()).map(Avatar.CLOTHE::name).collect(Collectors.toList());
            case "CLOTHECOLOR":
                return Stream.of(Avatar.CLOTHECOLOR.values()).map(Avatar.CLOTHECOLOR::name).collect(Collectors.toList());
            case "EYE":
                return Stream.of(Avatar.EYE.values()).map(Avatar.EYE::name).collect(Collectors.toList());
            case "EYEBROW":
                return Stream.of(Avatar.EYEBROW.values()).map(Avatar.EYEBROW::name).collect(Collectors.toList());
            case "MOUTH":
                return Stream.of(Avatar.MOUTH.values()).map(Avatar.MOUTH::name).collect(Collectors.toList());
            case "SKINCOLOR":
                return Stream.of(Avatar.SKINCOLOR.values()).map(Avatar.SKINCOLOR::name).collect(Collectors.toList());
            default:
                return new ArrayList<String>();
        }
    }

    public FaceDetail generateAvatar(MultipartFile multipartFile) {
        Avatar avatar = Avatar.getDefault();
        FaceDetail face = null;
        try {
            logger.debug("Preparing avatar for image:" + multipartFile.getName());
            Image image = new Image().withBytes(ByteBuffer.wrap(multipartFile.getBytes()));
            DetectFacesResult faceResult = awsDetectFaces(image, Attribute.ALL);
            if (faceResult.getFaceDetails() != null && faceResult.getFaceDetails().size() > 0) {
                face = faceResult.getFaceDetails().get(0);
                avatar = buildAvatar(face);
                if (logger.isDebugEnabled()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    logger.debug("Face features:");
                    logger.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(face));
                }
            } else {
                logger.debug("No face detected by Rekognition");
            }

            if (logger.isDebugEnabled()) {
                ObjectMapper objectMapper = new ObjectMapper();
                logger.debug("Avatar features:");
                logger.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(avatar));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return face;
    }

    private DetectFacesResult awsDetectFaces(Image image, Attribute attributes) {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(image)
                .withAttributes(attributes);
        return rekognitionClient.detectFaces(request);
    }

    public void awsDetectLabels(MultipartFile multipartFile) {
        try {
            Image image = new Image().withBytes(ByteBuffer.wrap(multipartFile.getBytes()));

            DetectLabelsRequest request = new DetectLabelsRequest();
            request.setImage(image);
            request.withMaxLabels(10);
            request.withMinConfidence(90F);

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List<Label> labels = result.getLabels();
            for (Label label : labels) {
                log.info("Label ::" + label.getName());
                log.info("Confidence ::" + label.getConfidence());
            }

        } catch (AmazonRekognitionException | IOException e) {
            e.printStackTrace();
        }


//        DetectFacesRequest request = new DetectFacesRequest()
//                .withImage(image)
//                .withAttributes(attributes);
//        return rekognitionClient.detectLabels(request);
    }


    private Avatar buildAvatar(FaceDetail face) {
        //TODO: Give your creativity a chance
        return null;
    }
}