package dev.nayoungwook.workspace;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.nayoungwook.peridot.Camera;
import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.input.Input;
import dev.nayoungwook.peridot.math.Vector;
import dev.nayoungwook.peridot.object.GameObject;
import dev.nayoungwook.peridot.object.Sprite;
import dev.nayoungwook.peridot.renderer.Renderer;
import dev.nayoungwook.peridot.scene.Scene;

public class Workspace implements Scene {

	private Vector cameraTarget;
	private double cameraRotation = 0;
	private Sprite spr = new Sprite("hd.png");
//	private Font font = new Font("gmksans.ttf", 25);
	private ArrayList<LightParticle> lightParticles = new ArrayList<>();

	class LightParticle extends GameObject {

		public LightParticle(float x, float y) {
			super(x, y);
			this.width = 10;
			this.height = 10;
		}

		@Override
		public void render() {
			Renderer.oval(new Color(240, 240, 240, (int) Math.round(Math.random() * 10) + 10),
					String.format("{x:%d, y:%d, z:3, width:%d, height:%d}", (int) position.x, (int) position.y,
							this.width + (int) Math.round(Math.random() * 2),
							this.height + (int) Math.round(Math.random() * 2)));
		}

		private float timer = 0;

		@Override
		public void tick() {
			timer += 0.005f;
			if (timer >= 1) {
				lightParticles.remove(this);
			}

			this.width = (int) ((10 - timer * 10));
			position.y -= .1f;
		}

	}

	@Override
	public void init() {
		cameraTarget = new Vector(0, 0, 1);
		lightParticles.add(new LightParticle(200, 200));
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

		for (int i = 0; i < lightParticles.size(); i++)
			lightParticles.get(i).tick();

		if ((int) Math.round(Math.random() * 2) == 0)
			lightParticles.add(new LightParticle(200, 200));
	}

	@Override
	public void render() {
		Renderer.rect(new Color(20, 20, 20), String.format("{x:%d, y:%d, width:%d, height:%d, ui:true}",
				Component.width / 2, Component.height / 2, Component.width, Component.height));

		Renderer.image(spr, String.format("{x:%d, y:%d, width:%d, height:%d}", 200, 200, 50, 50));
		for (int i = 0; i < lightParticles.size(); i++)
			lightParticles.get(i).render();
	}

}
