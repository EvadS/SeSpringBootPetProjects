package com.se.image;

import com.se.image.utils.BufferedImageUtils;
import com.se.image.utils.ResizeModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImageToFixedWidthAlgoritm {
     //public static final String input_img = "E:\\Temp\\img\\1226_768.jpg";

    public static final String input_img = "E:\\Temp\\img\\test.jpg";
//   public static final String input_img = "E:\\Temp\\img\\1d.png";

//    String outPutfileName = "E:\\Temp\\img\\res\\scaled_with_proportion.jpg";
   private static final String outputFileName = "E:\\Temp\\img\\res\\WITH_proportion_0.jpg";
   private static final String outputFileName2 = "E:\\Temp\\img\\res\\WITH_proportion_0_ref.jpg";


    static int width = 200;
    static int recomendedHeight =200;

    private static  void test(String originalName, String destNames){

    }
    public static void main(String... args) throws Exception {

        Dimension originalDimention = getImageDim(input_img);
        Dimension scallableSize = getSize(originalDimention.width, originalDimention.height, width);

        File input = new File(input_img);
        BufferedImage image = ImageIO.read(input);

        int dx = 0;
        int dy = 0;

        int preview_width = scallableSize.width;
        int preview_height = scallableSize.height;

        if (scallableSize.width > width) {
            int diffWidth = scallableSize.width - width;

            dx = diffWidth / 2;
            // preview_width = width - width/ 2;
        }

        if (scallableSize.height > recomendedHeight) {
            int diffHeight = scallableSize.height - recomendedHeight;
            dy = diffHeight / 2;
            // здесь мы обрезаем
            //preview_height =  recomendedHeight;

            cropImage(outputFileName,
                    input,
                    preview_width,
                    preview_height,
                    recomendedHeight,
                    width,
                    dx, dy);
        }
        else if(recomendedHeight > scallableSize.height  ){
            preview_height =  scallableSize.height;

            cropImage(outputFileName,
                    input,
                    preview_width,
                    preview_height,
                    preview_height,
                    width,
                    dx, dy);
        }
        else{
            // w == h
            cropImage(outputFileName,
                    input,
                    preview_width,
                    preview_height,
                    recomendedHeight,
                    width,
                    dx, dy);
        }

        System.out.println("Completed: Result file path : " + outputFileName);


        //test refactored version
        ResizeModel resizeModel =  BufferedImageUtils.buildResizeModelByCurrentSize(originalDimention.width ,
                originalDimention.height, width,recomendedHeight);

        File refInput = new File(input_img);
        BufferedImage refImage = ImageIO.read(input);


        BufferedImage refdbf =   BufferedImageUtils.cropImage(refImage , resizeModel);
        File output = new File(outputFileName2);
        ImageIO.write(refdbf, "png", output);

        System.out.println("refactored version completed: Result file path : " + outputFileName2);
    }

    /**
     *
     * @param outputFilePath
     * @param input
     * @param preview_width
     * @param preview_height
     * @param destHeight
     * @param destWidth
     * @param dx
     * @param dy
     * @throws IOException
     */
    private static void cropImage(String outputFilePath,
                                  File input, int preview_width, int preview_height,
                                  int destHeight, int destWidth,
                                  int dx, int dy)
            throws IOException {
        BufferedImage img = ImageIO.read(input);
        BufferedImage resized = mresize(img, preview_height, preview_width);
        File output = new File(outputFilePath);

        // TODO for test
        //  ImageIO.write(resized, "png", output);

        // Crop
        BufferedImage croppedImage = resized.getSubimage(
                dx,
                dy,
                destWidth, // widht
                destHeight); // height

        output = new File(outputFilePath);
        ImageIO.write(croppedImage, "png", output);
    }

    private static BufferedImage mresize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static Dimension getSize(int currWidth, int currHeight, int size) {
        float destWidth = currWidth;
        float destHeigth = currHeight;

        float ratio = size / destWidth;

        destWidth = size;
        destHeigth = currHeight * ratio;
        return new Dimension((int) destWidth, (int) destHeigth);
    }

    private static Dimension getImageDim(final String path) {
        BufferedImage readImage = null;

        try {
            readImage = ImageIO.read(new File(path));
            int h = readImage.getHeight();
            int w = readImage.getWidth();

            return new Dimension(w, h);
        } catch (Exception e) {
            // TODO :
            return new Dimension(0, 0);
        }
    }
}
