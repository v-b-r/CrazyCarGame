package com.crazycar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.crazycar.controller.WorldController;
import com.crazycar.model.World;
import com.crazycar.view.WorldRenderer;


public class MainScreen implements Screen, InputProcessor{

	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	private int width;
	private int height;
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        controller.update(delta);
		renderer.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		renderer.setSize(width, height);
		world.setCarCamViewport(width,height);
//		System.out.println(width);
        this.width = width;
        this.height = height;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		world = new World();                   // World height is defined here
		renderer = new WorldRenderer(world,width,height);
		controller = new WorldController(world);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		 Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		 Gdx.input.setInputProcessor(null);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
        if (keycode == Keys.UP)
            controller.upPressed();
        if (keycode == Keys.DOWN)
            controller.downPressed();
        if (keycode == Keys.Z)
            controller.jumpPressed();
        if (keycode == Keys.X)
            controller.firePressed();
        return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
        if (keycode == Keys.UP){
            controller.upReleased();
            }
        if (keycode == Keys.DOWN){
            controller.downReleased();
        }
        if (keycode == Keys.Z)
            controller.jumpReleased();
        if (keycode == Keys.X)
            controller.fireReleased();
        return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (x < width / 2 && y > height / 2) {
            controller.upPressed();
        }
        if (x > width / 2 && y > height / 2) {
            controller.downPressed();
        }
        return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (x < width / 2 && y > height / 2) {
            controller.upReleased();
        }
        if (x > width / 2 && y > height / 2) {
            controller.downReleased();
        }
        return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


}

