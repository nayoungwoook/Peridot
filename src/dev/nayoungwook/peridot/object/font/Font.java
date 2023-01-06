package dev.nayoungwook.peridot.object.font;

import java.io.InputStream;

import dev.nayoungwook.peridot.Camera;

public class Font {

	private float size = 15f;
	private String path;
	private java.awt.Font _font;

	public Font(String path) {
		this.path = path;
		this.size = 15f;
		_font = loadFont();
	}

	public Font(String path, int size) {
		this.path = path;
		this.size = size;
		_font = loadFont();
	}

	private java.awt.Font loadFont() {
		InputStream stream;
		java.awt.Font font = null;

		try {
			stream = getClass().getClassLoader().getResourceAsStream(path);
			font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, stream).deriveFont(size * Camera.position.z);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return font;
	}

	public java.awt.Font getFont() {
		return _font;
	}

}
