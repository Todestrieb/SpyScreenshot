package SpyScreenshot;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 * SpyScreenshot - Very simple desktop screnshotter 
 * Authored by Martin Kumst 2015
 * License: GNU GPL version 2
 * Software is provided as is without warranty of any kind.
 * Use this software only for legal purposes! 
 * 
 * @author Martin Kumst
 */
public class SpyScreenshot {

	// Configuration
	private static final String DESTINATION_DIRECTORY = "/tmp/spy";		// DST directory for saving screenshots
	private static final String OUTPUT_FILES_PREFIX = "screen_";		// Screenshot file prefix
	private static final int SCREENSHOT_INTERVAL = 1000;					// Interval in milliseconds between screenshots
	
	/**
	 * Take screenshot of the current desktop
	 * @return Screenshot
	 * @throws Exception 
	 */
	private static BufferedImage getScreeshot() throws Exception {
	
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Robot robot = new Robot();
		BufferedImage img = robot.createScreenCapture(new Rectangle(dimension));
		
		return img;
	}
	
	/**
	 * Get current date and time
	 * @return Current date and time formatted as "Y-M-d-H-m-s-S"
	 */
	private static String getActDateTime() {
	
		Date actDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("Y-M-d-H-m-s-S");
		String result = format.format(actDate);
		
		return result;
	}
	
	/**
	 * Save supplied image to a file
	 * @param img Image
	 * @throws Exception 
	 */
	private static void saveImage(BufferedImage img) throws Exception {
		
		File outputFile = new File(DESTINATION_DIRECTORY + File.separator + OUTPUT_FILES_PREFIX + getActDateTime() + ".png");
		ImageIO.write(img, "PNG", outputFile);
	}
	
	/**
	 * Save current screenshot into file
	 * @throws Exception 
	 */
	private static void saveScreenshot() throws Exception {
		
		BufferedImage screenshot = getScreeshot();
		saveImage(screenshot);
	}
	
	/**
	 * Create destination directory if does not exist
	 */
	private static void createDstDirIfNec() {
		
		File dstDir = new File(DESTINATION_DIRECTORY);
		if (! dstDir.exists()) {
			
			dstDir.mkdirs();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("SpyScreenshot by M.Kumst");
		System.out.println("Use this software only for legal purposes!");
		
		createDstDirIfNec();
		
		for (;;) {
			
			System.out.println("Taking screenshot");
			saveScreenshot();
			System.out.println("OK");
			System.out.println("Sleeping for " + SCREENSHOT_INTERVAL + " ms");
			Thread.sleep(SCREENSHOT_INTERVAL);
		}
	}
}