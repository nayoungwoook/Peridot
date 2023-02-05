package dev.nayoungwook.workspace;

import java.awt.event.KeyEvent;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.input.Input;
import dev.nayoungwook.peridot.math.Vector;
import dev.nayoungwook.peridot.object.Sprite;
import dev.nayoungwook.peridot.renderer.Renderer;
import dev.nayoungwook.peridot.scene.Scene;

public class Workspace implements Scene {

	private Vector cameraTarget;
	private double cameraRotation = 0;
	private Sprite spr = new Sprite("hd.png");

	@Override
	public void init() {
		cameraTarget = new Vector(0, 0, 1);
	}

	@Override
	public void tick() {
		float movespeed = 20.4f / Camera.position.z;
		if (Input.keys[KeyEvent.VK_W])
			cameraTarget.y -= movespeed;
		if (Input.keys[KeyEvent.VK_S])
			cameraTarget.y += movespeed;
		if (Input.keys[KeyEvent.VK_A])
			cameraTarget.x -= movespeed;
		if (Input.keys[KeyEvent.VK_D])
			cameraTarget.x += movespeed;

		if (Input.keys[KeyEvent.VK_E])
			cameraRotation += 0.05;
		if (Input.keys[KeyEvent.VK_Q])
			cameraRotation -= 0.05;

		if (Input.mouseScroll == 1)
			cameraTarget.z -= 0.2;
		if (Input.mouseScroll == -1)
			cameraTarget.z += 0.2;
		if (cameraTarget.z < 0.1)
			cameraTarget.z = 0.1f;

		Input.mouseScroll = 0;

		Camera.rotation += (cameraRotation - Camera.rotation) / 10;

		Camera.position.x += (cameraTarget.x - Component.width / 2 - Camera.position.x) / 5;
		Camera.position.y += (cameraTarget.y - Component.height / 2 - Camera.position.y) / 5;
		Camera.position.z += (cameraTarget.z - Camera.position.z) / 10;
	}

	@Override
	public void render() {
		for (int i = 0; i < 50; i++)
			for (int j = 0; j < 50; j++)
				Renderer.image(spr,
						String.format("{x:%d, y:%d, width:%d, height:%d}", 200 + i * 50, 200 + j * 50, 50, 50));
	}

}
