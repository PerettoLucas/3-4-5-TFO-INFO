package net.tfobz.threads.compression;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * Class that can compress an jpg-image
 * @see "Java ist auch eine Insel" Chapter "Grafikprogrammierung"
 * @author Michael Wild
 */
public class JPGImageCompress
{
	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("images/image1.jpg"));
		for (double quality = 1.0; quality >= 0; quality -= 0.1)
			compressImage(image, "images/image.", quality);
	}

	/**
	 * Compresses an image into a file
	 * @param image that will be compressed
	 * @param filename of the file to create
	 * @param quality between 0.0 and 1.0
	 * @throws IOException
	 */
	public static void compressImage(BufferedImage image, String filename,
			double quality) throws IOException {
		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
		File f = new File(filename + getNumber(quality) + ".jpg");
		FileImageOutputStream ios = new FileImageOutputStream(f);
		writer.setOutput(ios);
		ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
		iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwparam.setCompressionQuality((float) quality);
		writer.write(null, new IIOImage(image, null, null), iwparam);
		ios.flush();
		writer.dispose();
		ios.close();
	}

	/**
	 * Compresses an image and creates a new image
	 * @param image that will be compressed
	 * @param quality between 0.0 and 1.0
	 * @return the new compressed image
	 * @throws IOException
	 */
	public static BufferedImage compressImage(BufferedImage image, double quality)
			throws IOException {
		BufferedImage ret = null;
		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
		ByteArrayOutputStream out = new ByteArrayOutputStream(0xfff);
		ImageOutputStream ios = ImageIO.createImageOutputStream(out);
		writer.setOutput(ios);
		ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
		iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwparam.setCompressionQuality((float) quality);
		writer.write(null, new IIOImage(image, null, null), iwparam);
		ios.flush();
		writer.dispose();
		ios.close();
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		ret = ImageIO.read(in);
		out.close();
		in.close();
		return ret;
	}

	private static String getNumber(double number) {
		return new DecimalFormat("0000").format((int)(number * 1000));
	}
}
