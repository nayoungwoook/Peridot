package dev.nayoungwook.peridot.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	public BufferedImage image;

	public Sprite(String path) {
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite cloneImage() {
		Sprite spr = new Sprite("");
		spr.image = image;

		return spr;
	}

	public Sprite cutImage(int x, int y, int w, int h) {
		image = image.getSubimage(x, y, w, h);
		return this;
	}

}
