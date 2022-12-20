package dev.nayoungwook.peridot;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends JFrame {

	private static final long serialVersionUID = 1L;
	private int width, height;
	private String title;
	private Component compo;

	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		setSize(width, height);
		setTitle(title);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setFocusable(true);
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));

		compo = new Component();
		add(compo);
		pack();
		compo.start();
	}

	// tick for engine
	public void _tick() {

	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void osSetSize(int width, int height) {
		if (width < 0 || height < 0)
			return;

		this.width = width;
		this.height = height;
	}

	public void osSetTitle(String title) {
		if (title == null)
			return;

		this.title = title;
	}

}
