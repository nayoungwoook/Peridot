package dev.nayoungwook.peridot.math;

public class Vector {

	public float x, y, z;

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setTransform(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setTransform(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public void translate(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

}
