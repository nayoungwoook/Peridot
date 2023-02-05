package dev.nayoungwook.peridot.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	public BufferedImage image;

	public Sprite(String path) {
		try {

			BufferedImage _image = ImageIO.read(getClass().getClassLoader().getResource(path));

			if (_image != null) {
				BufferedImage _result = new BufferedImage(_image.getWidth(), _image.getHeight(),
						BufferedImage.TYPE_INT_ARGB);

				Graphics g = _result.getGraphics();
				g.drawImage(_image, 0, 0, _image.getWidth(), _image.getHeight(), null);

				image = _result;
			}
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
