package dev.nayoungwook.onsen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;

import dev.nayoungwook.onsen.input.Input;
import dev.nayoungwook.onsen.object.GameObject;
import dev.nayoungwook.onsen.scene.Scene;

public class Component extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int width, height;

	public static Scene scene;
	private Scene backupScene;

	public static ArrayList<GameObject> renderQueue = new ArrayList<GameObject>();

	public void start() {
		new Thread(this, "GameThread").start();

		addKeyListener(new Input());
		addMouseListener(new Input());
		addMouseMotionListener(new Input());
	}

	private void init() {
	}

	private void update() {
		width = this.getParent().getWidth();
		height = this.getParent().getHeight();

		if (scene != null) {
			if (backupScene != scene) {
				backupScene = scene;
				scene.init();
			}
		}

		if (scene != null)
			scene.update();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		if (scene != null)
			scene.render();

		Collections.sort(renderQueue);

		for (int i = 0; i < renderQueue.size(); i++) {
			renderQueue.get(i)._render(g2d);
		}

		renderQueue.clear();

		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / 60;
		final double timeF = 1000000000 / 60;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		init();

		while (true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				update();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				render();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}

}
