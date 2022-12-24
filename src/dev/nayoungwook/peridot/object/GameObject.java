package dev.nayoungwook.peridot.object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.math.Mathf;
import dev.nayoungwook.peridot.math.Vector;

public class GameObject implements Comparable<GameObject> {

	public Vector position, renderPosition, anchor = new Vector(0.5f, 0.5f);

	// size
	public int width, height;
	public int renderWidth, renderHeight;

	public float rotation = 0f;
	public Sprite sprite;
	public boolean flipx, flipy;
	public boolean visible = true;

	public GameObject(float x, float y, int width, int height) {
		position = new Vector(x, y, 1);
		this.width = width;
		this.height = height;
	}

	public GameObject(float x, float y) {
		position = new Vector(x, y, 1);
		this.width = 100;
		this.height = 100;
	}

	public void _render(Graphics2D g) {
		if (sprite == null)
			return;

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
		if (visible)
			g.drawImage(sprite.getImage(), 0, 0, (int) Math.ceil(renderWidth), (int) Math.ceil(renderHeight), null);

		g.setTransform(backup);
	}

	public void render() {
		/* Add this object into render queue */
		Component.renderQueue.add(this);
	}

	public void tick() {
	}

	@Override
	public int compareTo(GameObject g) {
		if (position.z < g.position.z)
			return -1;
		else if (position.z == g.position.z)
			return 0;
		else
			return 1;
	}

}
