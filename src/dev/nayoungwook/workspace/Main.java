package dev.nayoungwook.workspace;

import dev.nayoungwook.peridot.Component;
import dev.nayoungwook.peridot.Display;

public class Main {

	public static void main(String[] args) {
		new Display("Peridot", 800, 600);
		Component.scene = new Workspace();
	}

}
