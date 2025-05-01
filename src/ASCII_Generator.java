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
     * 
     * @param w
     *          the image's width in pixels
     * @param h
     *          the image's height in pixels
     */
    private static void populateArray(int w, int h, BufferedImage image) {
        int[][][] rgbArray = new int[h][w][3];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                // RGB value for each pixel
                int pixel = image.getRGB(x,y);

                // Separate binary (AAAAAAAARRRRRRRRGGGGGGGGBBBBBBBB) into correct components
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                // Store values in array
                rgbArray[y][x][0] = red;
                rgbArray[y][x][1] = green;
                rgbArray[y][x][2] = blue;
            }
        }
        System.out.print("Successful iteration, RGB data stored!");
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
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Dimensions are: " + width + " x " + height);

        // Populate 2D matrix with numerical brightness data for each pixel
        populateArray(width, height, image);

    }
}