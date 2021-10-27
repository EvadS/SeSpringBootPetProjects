package com.se.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImageToSquareAlgoritm {
    public static final String input_img = "E:\\Temp\\img\\test.jpg";
    // public static final String input_img = "E:\\Temp\\img\\1.jpg";
    static int width = 200;
    static int height = 200;

    public static void main(String... args) throws Exception {

        Dimension originalDimention = getImageDim(input_img);
        Dimension scallableSize = getSize(originalDimention.width, originalDimention.height, width);

        // for test
//       Dimension scallableSize = getSize(200,100,width);
//       Dimension scallableSize2 = getSize(100,200,width);
//       Dimension scallableSize3 = getSize(200,200,width);

        String outPutfileName = "E:\\Temp\\img\\res\\scaled_with_proportion.jpg";
        String outputFileName = "E:\\Temp\\img\\res\\scaled_with_proportion2.jpg";

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

        if (scallableSize.height > height) {
            int diffHeight = scallableSize.height - height;
            dy = diffHeight / 2;
            //preview_height =  height - height/ 2;
        }

//         correct working
        cropImage(outPutfileName, outputFileName, input,
                preview_width,
                preview_height,
                height,
                width,
                dx, dy);


        //ResizeImageExample.resize(image,  200,  200,  outPutfileName);
        int a = 0;
    }

    private static void cropImage(String outPutfileName, String outputFileName,
                                  File input, int preview_width, int preview_height,
                                  int destHeight, int destWidth,
                                  int dx, int dy)
            throws IOException {
        BufferedImage img = ImageIO.read(input);
        BufferedImage resized = mresize(img, preview_height, preview_width);
        File output = new File(outPutfileName);
        ImageIO.write(resized, "png", output);

        // Crop
        BufferedImage croppedImage = resized.getSubimage(
                dx,
                dy,
                destWidth, // widht
                destHeight); // height
        output = new File(outputFileName);
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

        float ratio = 1;

        boolean isWidthBiggest = true;

        if (currHeight > currWidth) {
            isWidthBiggest = false;
        }

        if (isWidthBiggest) {
            ratio = size / destHeigth;
            destHeigth = size;
            destWidth = currWidth * ratio;
        } else {
            ratio = size / destWidth;
            destWidth = size;
            destHeigth = currHeight * ratio;
        }

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

    private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
        return dest;
    }
}
