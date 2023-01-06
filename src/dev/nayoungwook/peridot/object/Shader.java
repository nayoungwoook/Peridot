package dev.nayoungwook.peridot.object;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.json.JSONObject;

public class Shader {

	private static int rgbLimit(int rgb) {
		if (rgb > 255)
			return 255;
		if (rgb < 0)
			return 0;

		return rgb;
	}

	public static void applicateShader(Sprite sprite, String _format) {
		JSONObject format = new JSONObject(_format);

		BufferedImage image = sprite.image;

		if (format.has("tint")) {
			JSONObject tint = format.getJSONObject("tint");

			int tr = tint.getInt("r");
			int tg = tint.getInt("g");
			int tb = tint.getInt("b");

			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {
					Color c = new Color(image.getRGB(i, j));

					int fr = rgbLimit(c.getRed() + tr);
					int fg = rgbLimit(c.getGreen() + tg);
					int fb = rgbLimit(c.getBlue() + tb);

					int r = 255 << 24 | fr << 16 | fg << 8 | fb;

					image.setRGB(i, j, r);
				}
			}
		}
	}
}
