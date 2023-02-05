package dev.nayoungwook.peridot.math;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.Component;

public class Mathf {

	public static double getDistance(Vector v1, Vector v2) {
		if (v1 == null || v2 == null)
			return -1;

		return Math.sqrt((v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y));
	}

	public static double getAngle(Vector v1, Vector v2) {
		return Math.atan2(v2.y - v1.y, v2.x - v1.x);
	}

	public static double getXv(float moveSpeed, Vector v1, Vector v2) {
		return Math.cos(getAngle(v1, v2)) * moveSpeed;
	}

	public static double getYv(float moveSpeed, Vector v1, Vector v2) {
		return Math.sin(getAngle(v1, v2)) * moveSpeed;
	}

	public static double getXv(float moveSpeed, float rotation) {
		return Math.cos(rotation) * moveSpeed;
	}

	public static double getYv(float moveSpeed, float rotation) {
		return Math.sin(rotation) * moveSpeed;
	}

	public static Vector calculateRenderSize(int width, int height, boolean flipx, boolean flipy) {
		float fx = 1, fy = 1;

		float renderWidth, renderHeight;

		renderWidth = (float) Math.ceil(width * (Camera.position.z));
		renderHeight = (float) Math.ceil(height * (Camera.position.z));

		if (flipx) {
			fx = -1;
		}

		if (flipy) {
			fy = -1;
		}

		renderWidth *= (fx);
		renderHeight *= (fy);

		return new Vector(renderWidth, renderHeight);
	}

	public static Vector calculateRenderPosition(Vector v) {
		int scw = Component.width, sch = Component.height;

		// distance from center of screen
		double _dist = getDistance(new Vector(scw / 2 + Camera.position.x, sch / 2 + Camera.position.y),
				new Vector(v.x, v.y));

		// rotation
		double _rot = Math.atan2(sch / 2 + Camera.position.y - v.y, scw / 2 + Camera.position.x - v.x)
				+ Camera.rotation;

		double xx = (v.x - (scw / 2 + Camera.position.x));
		double yy = (v.y - (sch / 2 + Camera.position.y));
		double _zDist = _dist * (Camera.position.z);

		double _zx = (Math.cos(_rot) * _zDist), _zy = (Math.sin(_rot) * _zDist);

		Vector result = new Vector(0, 0);

		result.x = (float) (v.x - Camera.position.x - (xx + _zx));
		result.y = (float) (v.y - Camera.position.y - (yy + _zy));

		return result;
	}

}
