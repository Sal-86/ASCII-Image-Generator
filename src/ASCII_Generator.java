import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.io.*;
import java.util.*;


/**
 * Generates a printed "image" comprised of ASCII characters
 *
 * @author Lily Scheerer
 *
 */
public final class ASCII_Generator {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ASCII_Generator() {
    }

    /**
     * Gets user input for file path name of image
     */
    private static String getFileName() {
        // Gets user input for file path name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter image file name:\n");
        String imageFileName = scanner.nextLine();
        scanner.close();
        return imageFileName;
    }

    /**
     * Determines and prints pixel dimensions of image
     * @param image
     *             Buffered image that has been processed
     */
    private static void imageDimensions(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Image pixel dimensions= "+ width + ", "+height);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
       
        // Prompt user for image file name
        String imageFileName = getFileName();

        // Use file path to return a BufferedImage
        File imageFile = new File("./"+imageFileName);
        BufferedImage image = ImageIO.read(imageFile);
            if(image == null){
                System.out.print("Null, could not read image file.");
            }

        // Get image dimensions
        imageDimensions(image);

        // Populate 2D matrix with numerical brightness data for each pixel
        
        

    }
}