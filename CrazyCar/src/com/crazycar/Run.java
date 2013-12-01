package com.crazycar;
import com.badlogic.gdx.Game;
import com.crazycar.screens.MainScreen;

public class Run extends Game{

	@Override
	public void create() {
		setScreen(new MainScreen());
	}
}
