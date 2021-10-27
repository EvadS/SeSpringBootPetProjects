package com.se.image;


import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleFilter;
import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResizeImageExample {

    static int width = 200;
    static int height  = 200;


   // public static  final  String input_img  = "E:\\Temp\\img\\test.jpg";
    public static  final  String input_img  = "E:\\Temp\\img\\1.jpg";
    public static void main(String... args) throws Exception {

        File input = new File(input_img);
       BufferedImage image = ImageIO.read(input);
//
        String filePath = "E:\\Temp\\img\\res\\127_86.jpg";
        resize(image, height, width, filePath);

//--------------------------
        String outPutfileName = "E:\\Temp\\img\\res\\127_86_scaled_with_proportion.jpg";
        Dimension imgSize = new Dimension(2744, 4049);
        Dimension boundary = new Dimension(width, height);
        //---------------------------------------------------

        Dimension resultDimentions = calculateAspectRatioFit(imgSize.width, imgSize.height, boundary.width, boundary.height);


        resize(image, resultDimentions.width, resultDimentions.height, outPutfileName);
        int a =0;

//----------------------------------------------

        File inputFile = new File(input_img);
        File resampleOpFile = new File("E:\\Temp\\img\\res\\ResampleOp_127_86.jpg");

        System.out.println(String.format("resizing %s to %s x %s ", input_img, width, height));

        try(InputStream originalImageStream =  new FileInputStream(inputFile) ) {

            BufferedImage original = ImageIO.read(originalImageStream);
            ResampleOp resampleOp = new ResampleOp(width, height);
            resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
            BufferedImage resized = resampleOp.filter(original, null);
            ImageIO.write(resized, "jpg", resampleOpFile);

        }

       // BufferedImage scaledImg = Scalr.resize(img, 320)
        File destgetLanczos3Filter = new File("E:\\Temp\\img\\res\\getLanczos3Filter_127_86.jpg");

        try(InputStream originalImageStream =  new FileInputStream(inputFile) ) {

            BufferedImage bi = ImageIO.read(originalImageStream);
            resize(bi, width,height,destgetLanczos3Filter);
        }

        // BufferedImage scaledImg = Scalr.resize(img, 320)
        File destgetLanczos3Filter22 = new File("E:\\Temp\\img\\res\\getLanczos3Filter_127_86.jpg");

        try(InputStream originalImageStream =  new FileInputStream(inputFile) ) {

            BufferedImage bi = ImageIO.read(originalImageStream);
            resize(bi, width,height,destgetLanczos3Filter22);
        }

//------
        try(InputStream originalImageStream =  new FileInputStream(inputFile) ) {

            BufferedImage bi = ImageIO.read(originalImageStream);
            resizeByFilter(bi, width,height,ResampleFilters.getBellFilter());
            resizeByFilter(bi, width,height,ResampleFilters.getLanczos3Filter());
            resizeByFilter(bi, width,height,ResampleFilters.getBoxFilter());
            resizeByFilter(bi, width,height,ResampleFilters.getHermiteFilter());
            resizeByFilter(bi, width,height,ResampleFilters.getMitchellFilter());
            resizeByFilter(bi, width,height,ResampleFilters.getTriangleFilter());
            resizeByFilter(bi, width,height,ResampleFilters.getBiCubicFilter());
            resizeByFilter(bi, width,height,ResampleFilters.getBSplineFilter());
            resizeByFilter(bi, width,height,ResampleFilters.getBiCubicHighFreqResponse());

        }
//------------------------------------------------------------------------
        // universal algoritm



        int b  =0;
    }

    private static void resize(BufferedImage bi, int w, int h, File dest) throws Exception {
        ResampleOp ro = new ResampleOp(w, h);
        ro.setFilter(ResampleFilters.getLanczos3Filter());
        BufferedImage biscaled = ro.filter(bi, null);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(dest);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.8f);
        writer.write(biscaled);
    }


    private static void resizeByFilter(BufferedImage bi, int w, int h,ResampleFilter filter  ) throws Exception {

        File dest = new File("E:\\Temp\\img\\res\\resample_"+filter.getName()+"_127_86.jpg");

        ResampleOp ro = new ResampleOp(w, h);
        //ro.setFilter(ResampleFilters.getBellFilter());
        ro.setFilter(filter);
        BufferedImage biscaled = ro.filter(bi, null);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(dest);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.8f);
        writer.write(biscaled);
    }
    static Dimension calculateAspectRatioFit(float srcWidth, float  srcHeight,float maxWidth,float maxHeight) {

        float  ratio = Math.min(maxWidth / srcWidth, maxHeight / srcHeight);

        float height = srcWidth*ratio;
        float width = srcHeight*ratio;

        Dimension dimension = new Dimension((int)width,(int)height);
        return dimension;
    }


    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }
    //------------------------

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    private static void resize(BufferedImage img, int height, int width, String filePath) throws IOException {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

//        double aspectRatio = (double) img.getWidth(null)/(double) img.getHeight(null);
//        tempPNG = resizeImage(img, 100, (int) (100/aspectRatio));

        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        File output = new File(filePath);
        System.out.print("Res file: " + output.getAbsolutePath());
        ImageIO.write(resized, "png", output);
    }
//**************************************
    private static BufferedImage scale(BufferedImage source,double ratio) {
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

    private static BufferedImage getCompatibleImage(int w, int h) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        return image;
    }


}
