package com.aran.tech.managementArea.services;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

/**
 * @author oawon
 *
 */
@Service
	public class ImageResizer {
	
	   // File representing the folder that you select using a FileChooser
    static final File dir = new File("D:/callicoder/uploads/");

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "bmp" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
		 
	    /**
	     * Resizes an image to a absolute width and height (the image may not be
	     * proportional)
	     * @param inputImagePath Path of the original image
	     * @param outputImagePath Path to save the resized image
	     * @param scaledWidth absolute width in pixels
	     * @param scaledHeight absolute height in pixels
	     * @throws IOException
	     */
	    public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
	            throws IOException {
	        // reads input image
	        File inputFile = new File(inputImagePath);
	        BufferedImage inputImage = ImageIO.read(inputFile);
	 
	        // creates output image
	        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
	 
	        // scales the input image to the output image
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
	        g2d.dispose();
	 
	        // extracts extension of output file
	        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
	 
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
	    
	    public InputStream resizeImage(InputStream uploadedInputStream, String fileName, double percent) {

	        try {
	            BufferedImage image = ImageIO.read(uploadedInputStream);
	            int width = (int)(image.getWidth()*percent) ;
	            int height = (int)(image.getHeight()*percent) ;
	            Image originalImage= image.getScaledInstance(width, height, Image.SCALE_DEFAULT);

	            int type = ((image.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : image.getType());
	            BufferedImage resizedImage = new BufferedImage(width, height, type);

	            Graphics2D g2d = resizedImage.createGraphics();
	            g2d.drawImage(originalImage, 0, 0, width, height, null);
	            g2d.dispose();
	            g2d.setComposite(AlphaComposite.Src);
	            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	            ImageIO.write(resizedImage, fileName.split("\\.")[1], byteArrayOutputStream);
	            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	        } catch (IOException e) {
	            // Something is going wrong while resizing image
	            return uploadedInputStream;
	        }
	    }
	    
	    public static InputStream resizeImage(BufferedImage image, String fileName, double percent) {

	        try {
	            //BufferedImage image = ImageIO.read(uploadedInputStream);
	            int width = (int)(image.getWidth()*percent) ;
	            int height = (int)(image.getHeight()*percent) ;
	            Image originalImage= image.getScaledInstance(width, height, Image.SCALE_DEFAULT);

	            int type = ((image.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : image.getType());
	            BufferedImage resizedImage = new BufferedImage(width, height, type);

	            Graphics2D g2d = resizedImage.createGraphics();
	            g2d.drawImage(originalImage, 0, 0, width, height, null);
	            g2d.dispose();
	            g2d.setComposite(AlphaComposite.Src);
	            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	            ImageIO.write(resizedImage, fileName.split("\\.")[1], byteArrayOutputStream);
	            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	        } catch (IOException e) {
	            // Something is going wrong while resizing image
	            return null;
	        }
	    }
	 
	    
	   
	    /**
	     * Test resizing images
	     */
	    public static void mainx(String[] args) {
	        String inputImagePath = "D:/callicoder/uploads/0a5b14be-762c-44a1-b4c5-9e8b1962f2fa.png";
	        String outputImagePath2 = "D:/callicoder/uploads/Puppy_Smaller.jpg";
	        
	        try {
	 
	            // resize smaller by 50%
	            double percent = 0.5;
	            ImageResizer.resize(inputImagePath, outputImagePath2, percent);
	 
	        } catch (IOException ex) {
	            System.out.println("Error resizing the image.");
	            ex.printStackTrace();
	        }
	    }
	    
	    public static void main(String[] args) {

	        if (dir.isDirectory()) { // make sure it's a directory
	            for (final File f : dir.listFiles(IMAGE_FILTER)) {
	                BufferedImage img = null;

	                try {
	                    img = ImageIO.read(f);

	                    // you probably want something more involved here
	                    // to display in your UI
	                    System.out.println("image: " + f.getName());
	                    System.out.println(" width : " + img.getWidth());
	                    System.out.println(" height: " + img.getHeight());
	                    System.out.println(" size  : " + f.length());
	                    //Path targetLocation = f.toPath() ;
	                    //InputStream imageStream = ImageResizer.resizeImage(img, f.getName(), 0.5) ;
	                    //Files.copy(imageStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
	                } catch (final IOException e) {
	                    // handle errors here
	                }
	                //break ;
	            }
	        }
	    }
	 
	}