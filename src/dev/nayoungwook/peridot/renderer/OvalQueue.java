package dev.nayoungwook.peridot.renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.math.Mathf;
import dev.nayoungwook.peridot.math.Vector;
import dev.nayoungwook.peridot.object.GameObject;

public class OvalQueue extends GameObject {

	public Color color;
	private boolean ui;

	public OvalQueue(float x, float y, float width, float height, boolean ui) {
		super(x, y, (int) width, (int) height);
		this.ui = ui;
	}

	@Override
	public void _render(Graphics2D g) {
		if (!visible)
			return;

		if (ui) {
			g.setColor(color);
			g.fillOval((int) (position.x - width / 2), (int) (position.y - height / 2), width, height);
		} else {
			/* Actual object rendering */
			renderPosition = Mathf.calculateRenderPosition(position);
			Vector s = Mathf.calculateRenderSize(width, height, flipx, flipy);
			renderWidth = (int) s.x;
			renderHeight = (int) s.y;

			/* Check should be rendered */
			if (renderPosition.x < -renderWidth || renderPosition.x >= Component.width + renderWidth)
				return;
			if (renderPosition.y < -renderHeight || renderPosition.y >= Component.height + renderHeight)
				return;

			renderPosition.x -= renderWidth / 2;
			renderPosition.y -= renderHeight / 2;

			AffineTransform backup = g.getTransform();
			g.translate(this.renderPosition.x, this.renderPosition.y);
			g.rotate(rotation + Camera.rotation, this.renderWidth * anchor.x, this.renderHeight * anchor.y);

			g.setColor(color);
			g.fillOval(0, 0, renderWidth, renderHeight);

			g.setTransform(backup);
		}
	}

}
