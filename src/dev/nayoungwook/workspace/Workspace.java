package dev.nayoungwook.workspace;

import java.awt.Color;

import com.sun.glass.events.KeyEvent;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.input.Input;
import dev.nayoungwook.peridot.math.Vector;
import dev.nayoungwook.peridot.object.GameObject;
import dev.nayoungwook.peridot.object.Shader;
import dev.nayoungwook.peridot.object.Sprite;
import dev.nayoungwook.peridot.renderer.Renderer;
import dev.nayoungwook.peridot.scene.Scene;

public class Workspace implements Scene {

	private Vector cameraTarget;
	private double cameraRotation = 0;
	private Sprite spr = new Sprite("hd.png");
//	private Font font = new Font("gmksans.ttf", 25);

	private GameObject object1, plate;

	@Override
	public void init() {
		cameraTarget = new Vector(0, 0, 1);

		object1 = new GameObject(200, 200);
		plate = new GameObject(200, 220);

		object1.sprite = spr;
		object1.position.z = 1;

		plate.position.z = 0.5f;
		plate.width = 500;
		plate.height = 50;
		plate.sprite = spr;

		Shader.applicateShader(spr, "{tint:{\"r\":0, \"g\":0, \"b\":0}}");
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
		Renderer.rect(new Color(20, 20, 20), String.format("{x:%d, y:%d, width:%d, height:%d, ui:true}",
				Component.width / 2, Component.height / 2, Component.width, Component.height));
		object1.render();
		plate.render();

	}

}
