package dev.nayoungwook.peridot.renderer;

import java.awt.Graphics2D;

import dev.nayoungwook.peridot.object.GameObject;
import dev.nayoungwook.peridot.object.Sprite;

public class ImageQueue extends GameObject {

	private boolean ui;

	public ImageQueue(Sprite sprite, float x, float y, int width, int height, boolean ui) {
		super(x, y, width, height);
		this.sprite = sprite;
		this.ui = ui;
	}

	@Override
	public void _render(Graphics2D g) {
		if (!ui) {
			super._render(g);
		} else {
			if (sprite == null)
				return;
			if (visible)
				g.drawImage(sprite.image, (int) (position.x - width / 2), (int) (position.y - height / 2), width,
						height, null);
		}
	}

}
