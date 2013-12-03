package com.crazycar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.crazycar.controller.WorldController;
import com.crazycar.view.WorldRenderer;


public class MainScreen implements Screen, InputProcessor{

	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	private int width;
	private int height;
	public OrthographicCamera cam;
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        controller.update(delta);
		renderer.render();
		
		Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
		debugRenderer.render(world, cam.combined);
		world.step(1/60f, 6, 2);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		renderer.setSize(width, height);
		this.setCarCamViewport(width,height);
//		System.out.println(width);
        this.width = width;
        this.height = height;
	}
	
	public void setCarCamViewport(int width, int height) {
		// TODO Auto-generated method stub
		this.cam.viewportWidth = width;
		this.cam.viewportHeight = height;
		this.cam.update();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.update();
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		world = new World(new Vector2(0, 0)	,false);                   // World height is defined here
        BodyDef bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;  
        bodyDef.position.set(0,0);//camera.viewportWidth / 2, camera.viewportHeight / 2);  
        Body body = world.createBody(bodyDef);
        PolygonShape dynamicVehicle = new PolygonShape();
        dynamicVehicle.setAsBox(50, 50, new Vector2(0, 0), 0.0f);
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicVehicle;  
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 0.5f;  
        body.createFixture(fixtureDef); 
		body.setBullet(true);
		renderer = new WorldRenderer(world,cam,width,height);
		controller = new WorldController(world);
		Gdx.input.setInputProcessor(this);
		dynamicVehicle.dispose();
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
        if (keycode == Keys.LEFT)
            controller.leftPressed();
        if (keycode == Keys.RIGHT)
            controller.rightPressed();
        if (keycode == Keys.Z)
            controller.jumpPressed();
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
        if (keycode == Keys.LEFT){
            controller.leftReleased();
        }
        if (keycode == Keys.RIGHT){
            controller.rightReleased();
        }
        if (keycode == Keys.Z)
            controller.jumpReleased();
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

