package dev.nayoungwook.peridot.object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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
	public boolean rendering = false;

	private GameObject parent;
	private ArrayList<GameObject> children = new ArrayList<GameObject>();

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

	public GameObject getParent() {
		return parent;
	}

	public ArrayList<GameObject> getChildren() {
		return children;
	}

	public void addChild(GameObject object) {
		children.add(object);
		object.parent = this;
	}

	public void removeChild(GameObject object) {
		children.remove(object);
		object.parent = null;
	}

	public void _render(Graphics2D g) {
		if (sprite == null)
			return;

		/* Actual object rendering */

		float xx = 0f, yy = 0f;
		if (parent != null) {
			xx = parent.position.x;
			yy = parent.position.y;
		}

		renderPosition = Mathf.calculateRenderPosition(new Vector(position.x + xx, position.y + yy));

		Vector s = Mathf.calculateRenderSize(width, height, flipx, flipy);
		renderWidth = (int) s.x;
		renderHeight = (int) s.y;

		/* Check should be rendered */
		if (Mathf.getDistance(new Vector(Component.width / 2, Component.height / 2), renderPosition) > Math
				.max(Component.width, Component.height)) {
			rendering = false;
			return;
		} else {
			rendering = true;
		}

		renderPosition.x -= renderWidth / 2;
		renderPosition.y -= renderHeight / 2;

		AffineTransform backup = g.getTransform();
		g.translate(this.renderPosition.x, this.renderPosition.y);
		g.rotate(rotation + Camera.rotation, this.renderWidth * anchor.x, this.renderHeight * anchor.y);

		if (visible)
			g.drawImage(sprite.image, 0, 0, (int) Math.ceil(renderWidth), (int) Math.ceil(renderHeight), null);

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
