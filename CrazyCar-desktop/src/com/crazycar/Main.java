package com.crazycar;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CrazyCar";
		cfg.useGL20 = false;
		cfg.width = 320;//480;
		cfg.height = 480;//320;
		
		new LwjglApplication(new Run(), cfg);
	}
}
