package dev.nayoungwook.peridot.renderer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.math.Mathf;
import dev.nayoungwook.peridot.math.Vector;
import dev.nayoungwook.peridot.object.GameObject;
import dev.nayoungwook.peridot.object.font.Font;

public class TextQueue extends GameObject {

	private Font font;
	private String str;
	public Color color;
	private String textAlign = "center";
	private boolean ui;

	public TextQueue(String str, float x, float y, Font font, boolean ui) {
		super(x, y, 0, 0);

		this.font = font;
		this.str = str;
		this.ui = ui;
	}

	public void setTextAlign(String align) {
		this.textAlign = align;
	}

	@Override
	public void _render(Graphics2D g) {

		FontMetrics metrics = g.getFontMetrics();
		this.width = metrics.stringWidth(str);
		this.height = metrics.getHeight();

		if (ui) {

			float backupX = position.x;
			if (textAlign.equals("center"))
				position.x -= metrics.stringWidth(str) / 2;
			else if (textAlign.equals("right"))
				position.x -= metrics.stringWidth(str);
			position.x = backupX;

			int y = metrics.getHeight() / 2;

			if (visible) {
				g.setColor(color);
				g.setFont(font.getFont());
				g.drawString(str, position.x, position.y - y);
			}
		} else {
			/* Actual object rendering */

			g.setFont(font.getFont());

			float backupX = position.x;
			float backupY = position.y;

			position.y += metrics.getHeight() / 2;
			if (textAlign.equals("center"))
				position.x -= metrics.stringWidth(str) / 2;
			else if (textAlign.equals("right"))
				position.x -= metrics.stringWidth(str);

			renderPosition = Mathf.calculateRenderPosition(position);

			position.x = backupX;
			position.y = backupY;

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
			if (visible)
				g.drawString(str, 0, 0);

			g.setTransform(backup);
		}
	}

}
