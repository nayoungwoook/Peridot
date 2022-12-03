package dev.nayoungwook.onsen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Component extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public BufferedImage image;

	public void start() {
		new Thread(this, "GameThread").start();
	}

	private void init() throws IOException {
		image = ImageIO.read(getClass().getClassLoader().getResource("hd.png"));
	}

	private void update() {

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

		g2d.drawImage(image, getWidth() / 2 - 1982 / 4, getHeight() / 2 - 3000 / 4, 1982 / 2, 3000 / 2, null);

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

		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
