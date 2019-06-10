package com.beejee.task.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 

public class ImageResizer {
	/**
     * Resizes a binary image for farther usage.
     * @param image Binary image got from anywhere 
     * @param scaledWidth Absolute width in pixels
     * @param scaledHeight Absolute height in pixels
     * @return Resized binary image
     * @throws IOException
     */
	public static byte[] resize(byte[] image, int scaledWidth, int scaledHeight) throws IOException {
		byte[] resizedImage = new byte[] {};
		BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(image));
		
		if(scaledWidth > inputImage.getWidth() && scaledHeight > inputImage.getHeight()) {
			return image;
		}

		BufferedImage outputImage = getScaledImage(inputImage, scaledWidth, scaledHeight);
		
		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(outputImage, "png", baos);
		baos.flush();
		resizedImage = baos.toByteArray();
		baos.close();

		return resizedImage;
	}
	
	private static BufferedImage getScaledImage(BufferedImage inputImage, int scaledWidth, int scaledHeight) {
		// creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        
        g2d.setComposite(AlphaComposite.Src);

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
        		RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        		RenderingHints.VALUE_ANTIALIAS_ON);
    	
        return outputImage;
	}
	
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = getScaledImage(inputImage, scaledWidth, scaledHeight);
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }
 
}