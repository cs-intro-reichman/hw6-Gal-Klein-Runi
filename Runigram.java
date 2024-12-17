import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {

		//// Hide / change / add to the testing code below, as needed.

		// Tests the reading and printing of an image:
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);

		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
		/// 
		    // Test horizontal flipping
			System.out.println("Horizontally Flipped Image:");
			Color[][] flippedH = flippedHorizontally(tinypic);
			print(flippedH);
			display(flippedH);
			StdDraw.pause(1000);
			
			// Test vertical flipping
			System.out.println("Vertically Flipped Image:");
			Color[][] flippedV = flippedVertically(tinypic);
			print(flippedV);
			display(flippedV);
			StdDraw.pause(1000);
		
			// Test gray scaling
			System.out.println("Gray Scaled Image:");
			Color[][] grayImage = grayScaled(tinypic);
			print(grayImage);
			display(grayImage);
			StdDraw.pause(1000);
		
			// Test scaling the image (resizing)
			System.out.println("Scaled Image (Resized to 5x5):");
			Color[][] scaledImage = scaled(tinypic, 5, 5);
			print(scaledImage);
			display(scaledImage);
			StdDraw.pause(1000);
		
			// Test blending two images
			System.out.println("Blended Image (Alpha = 0.5):");
			Color[][] blendedImage = blend(tinypic, flippedH, 0.5);
			print(blendedImage);
			display(blendedImage);
			StdDraw.pause(1000);
		
			// Test morphing (from tinypic to flipped image)
			System.out.println("Morphing from Original to Horizontally Flipped Image:");
			morph(tinypic, flippedH, 5);
	}

	/**
	 * Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file.
	 */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array.
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				Color newColor = new java.awt.Color(in.readInt(), in.readInt(), in.readInt());
				image[i][j] = newColor;
			}
		}
		return image;
	}

	// Prints the RGB values of a given color.
	private static void print(Color c) {
		System.out.print("(");
		System.out.printf("%3s,", c.getRed()); // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
		System.out.printf("%3s", c.getBlue()); // Prints the blue component
		System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting
	// image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				print(image[i][j]);
			}
		}
	}

	/**
	 * Returns an image which is the horizontally flipped version of the given
	 * image.
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] newImage = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				newImage[i][j] = image[i][image[i].length - 1 - j];
			}
		}
		return newImage;
	}

	/**
	 * Returns an image which is the vertically flipped version of the given image.
	 */
	public static Color[][] flippedVertically(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] newImage = new Color[image[0].length][image.length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				newImage[i][j] = image[image.length - 1 - i][j];
			}
		}
		return newImage;
	}

	// Computes the luminance of the RGB values of the given pixel, using the
	// formula
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object
	// consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		double r = pixel.getRed();
		double g = pixel.getGreen();
		double b = pixel.getBlue();
		double lum = 0.299 * r + 0.587 * g + 0.114 * b;
		int lumInt = (int) Math.max(0, Math.min(255, lum));
		Color color = new Color(lumInt, lumInt, lumInt);
		return color;
	}

	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] newImage = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				newImage[i][j] = luminance(image[i][j]);
			}
		}
		return newImage;
	}

	/**
	 * Returns an image which is the scaled version of the given image.
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		int heightO = image.length;
		int widthO = image[0].length;
		Color[][] newImage = new Color[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newImage[i][j] = image[i * heightO / height][j * widthO / width];
			}
		}
		return newImage;
	}

	/**
	 * Computes and returns a blended color which is a linear combination of the two
	 * given
	 * colors. Each r, g, b, value v in the returned color is calculated using the
	 * formula
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r,
	 * g, b
	 * values in the two input color.
	 */

	 public static int colorBlend(double v1, double v2, double alpha) {
		double blendcolor = alpha * v1 + (1 - alpha) * v2;
		int result =  (int) Math.max(0, Math.min(255, blendcolor));
		return result;
	}
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int r = colorBlend(c1.getRed(), c2.getRed(), alpha);
		int g = colorBlend(c1.getGreen(), c2.getGreen(), alpha); 
		int b =  colorBlend(c1.getBlue(), c2.getBlue(), alpha); 

		Color color = new Color(r, g, b);
		return color;
	}

	/**
	 * Cosntructs and returns an image which is the blending of the two given
	 * images.
	 * The blended image is the linear combination of (alpha) part of the first
	 * image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		
		Color[][] newImage = new Color[image1.length][image1[0].length];
		for (int i = 0; i < image1.length; i++) {
			for (int j = 0; j < image1[0].length; j++) {
				newImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return newImage;

	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		if (source.length!=target.length || source[0].length!=target[0].length) {
			target = scaled(target,  source[0].length, source.length );
		}
		for (int i=0; i<=n; i++) {
			double a = (n-i)/n;
			Color[][] newImage = blend(source,target,a);
			display(newImage);
			StdDraw.pause(200);
		}
	}

	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		// Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor(image[i][j].getRed(),
						image[i][j].getGreen(),
						image[i][j].getBlue());
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}
