package dev.nayoungwook.workspace;

import java.awt.Color;

import com.sun.glass.events.KeyEvent;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.input.Input;
import dev.nayoungwook.peridot.math.Vector;
import dev.nayoungwook.peridot.object.Sprite;
import dev.nayoungwook.peridot.object.font.Font;
import dev.nayoungwook.peridot.renderer.Renderer;
import dev.nayoungwook.peridot.scene.Scene;

public class Worksapce implements Scene {

	private Vector cameraTarget;
	private double cameraRotation = 0;
	private Sprite spr = new Sprite("hd.png");
	private Font font = new Font("gmksans.ttf");

	@Override
	public void init() {
		cameraTarget = new Vector(0, 0, 1);

		int sw = 1983 / 15, sh = 3000 / 15;
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
		Camera.position.x += (cameraTarget.x - Camera.position.x) / 5;
		Camera.position.y += (cameraTarget.y - Camera.position.y) / 5;
		Camera.position.z += (cameraTarget.z - Camera.position.z) / 10;
	}

	@Override
	public void render() {
		Renderer.image(spr, String.format("{x:%d, y:%d, width:%d, height:%d}", 100, 100, 200, 200));
		Renderer.text("test", new Color(255, 255, 245), font, String.format("{x:%d, y:%d}", 100, 100, 200, 200));
//		Renderer.text("test", new Color(255, 255, 245), 300, 300, font);
//		Renderer.textUI("test", new Color(255, 255, 245), 300, 300, font);
	}

}
