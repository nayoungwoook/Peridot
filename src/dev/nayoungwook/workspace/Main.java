package dev.nayoungwook.workspace;

import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.Display;

public class Main {

	public static void main(String[] args) {
		new Display("Peridot", 1280, 720);
		Component.scene = new Workspace();
	}

}
