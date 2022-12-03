package dev.nayoungwook.onsen.object;

import dev.nayoungwook.onsen.math.Vector;

public class GameObject {

	public Vector position;
	public int width, height;
	public Sprite sprite;

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

	public final void _render() {
		/* Actual object rendering */
	}

	public void render() {
		/* Add this object into render queue */
	}

}
