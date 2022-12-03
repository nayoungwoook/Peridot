package dev.nayoungwook.onsen.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage image;

	public Sprite(String path) {
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		return image;
	}

}
