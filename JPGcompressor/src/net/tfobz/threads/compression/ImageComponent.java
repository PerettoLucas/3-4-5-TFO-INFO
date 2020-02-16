package net.tfobz.threads.compression;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Shows an image in a JComponent. ImageComponent can be integrated
 * in a JFrame like any other component. The image will be automatically scaled 
 * to the dimension of the component
 * @author Michael Wild
 */
@SuppressWarnings("serial")
public class ImageComponent extends JComponent
{
	private BufferedImage image = null;
	
	public void setImage(String fileName) throws IOException {
		File file = new File(fileName);
		setImage(file);
	}
	
	public void setImage(File file) throws IOException {
		if ((image = ImageIO.read(file)) != null) {
			repaint();
		}
	}
	
	public void setImage(BufferedImage image) {
		if (image == null)
			throw new IllegalArgumentException("Image is null");
		this.image = image;
		repaint();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (image != null) {
			g.drawImage(image,  0, 0, this.getWidth(), this.getHeight(), null);
		}
	}
}
