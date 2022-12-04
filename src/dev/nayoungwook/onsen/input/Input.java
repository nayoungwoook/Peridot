package dev.nayoungwook.onsen.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import dev.nayoungwook.onsen.math.Vector;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public static boolean keys[] = new boolean[KeyEvent.KEY_LAST];
	public static int mouseScroll;
	public static Vector mouse = new Vector(0, 0);
	public static boolean mouseLeft, mouseRight, mouseCenter;

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseLeft = true;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			mouseCenter = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			mouseRight = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseLeft = false;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			mouseCenter = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			mouseRight = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = true;
		} catch (Exception f) {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = false;
		} catch (Exception f) {
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseScroll = (int) e.getPreciseWheelRotation();
	}
}