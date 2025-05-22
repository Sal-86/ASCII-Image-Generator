import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
     * @return
     *          name of the image file
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
     * Calculates the brightness of each pixel as a single value
     * 
     * @param r
     *          value of red
     * @param g
     *          value of green
     * @param b
     *          value of blue
     * @return
     *          value of brightness, 0-255
     */
    private static int calculateBrightness (int r, int g, int b){
        // Implements the "average" approach for calculating brightness
        int brightness = (r + g + b) / 3;
        return brightness;
    }

    /**
     * 
     * @param element
     *              brightness value of a pixel
     * @return
     *              corresponding ASCII character
     */
    private static String chooseCharacter (int element) {
    // String ASCII = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
    String[] ASCII = {"`", "^", "\"", ",", ":", ";", "I", "l", "!", "i", "~", "+", "_", "-", "?", "]", "[", "}", "{", "1", ")", "(", "|", "\\", "/", "t", "f", "j", "r", "x", "n", "u", "v", "c", "z", "X", "Y", "U", "J", "C", "L", "Q", "0", "O", "Z", "m", "w", "q", "p", "d", "b", "k", "h", "a", "o", "*", "#", "M", "W", "&", "8", "%", "B", "@", "$"};

    double temp = 255/65, benchmark = 0;
    int counter = 0;
    String chosenChar = "";

        // Creates ranges of brightness values that correspond to each character in the ASCII String array
        while (temp <= 255 && counter < 65) {
            if ( element < temp && element >= benchmark){
                chosenChar = ASCII[counter];
            }
        temp += 255/65;
        benchmark += 255/65;
        counter++;
        }

    // Ensures every pixel is represented by a character
    if ( chosenChar.equals("")){
        chosenChar = "x";
    }

    return chosenChar;
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
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("Dimensions are: " + w + " x " + h);

        // Populate array with brightness value using RGB data for each pixel
        int[][][] rgbArray = new int[h][w][3];  // Unused thus far.
        int[][] brightnessArray = new int[h][w];

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {

                    // RGB value for each pixel
                    int pixel = image.getRGB(x,y);

                    // Separate binary (AAAAAAAARRRRRRRRGGGGGGGGBBBBBBBB) into correct components
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = (pixel) & 0xff;

                    // Store values in RGB array. RGB array is not inherently used.
                    rgbArray[y][x][0] = red;
                    rgbArray[y][x][1] = green;
                    rgbArray[y][x][2] = blue;

                    // Use RGB values to calculate brightness, store in array
                    brightnessArray[y][x] = calculateBrightness(red, green, blue);
                }
            }

        // Create text file for output
        PrintWriter fileWriter = new PrintWriter("PrintedImage.txt", "UTF-8");

        // Determine ASCII character to represent each pixel
        String[][] generatedImage = new String[h][w];
        int i = 0, j = 0;

        for (int[] row : brightnessArray) {
            for (int element : row){
                generatedImage[j][i] = chooseCharacter(element);

                // Print each character three times to help counteract squashing/stretching
                fileWriter.print(chooseCharacter(element));
                fileWriter.print(chooseCharacter(element));
                fileWriter.print(chooseCharacter(element));

                    if (i<h){
                        i++;
                    }
            }
            fileWriter.println(); // Create a new line after each printed row

            if (j<w){
                j++;
            }
        }

        System.out.println("ASCIIs determined. Image is printed in .txt file!");
        fileWriter.close();
    }
}