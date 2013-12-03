package com.crazycar.view;

import java.util.ArrayList;
import java.util.Iterator;

import com.crazycar.model.Vehicle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WorldRenderer {
	
	private float CamWidth;
	private float CamHeight;

	private World world;
	private OrthographicCamera cam;
//	private SpriteBatch spriteBatch;
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	private Texture blockTexture;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	public void setSize (int w, int h) {
//		this.world.width = w;
//		this.world.height = h;
		ppuX = (float)320/ CamWidth;
		ppuY = (float)1200/ CamHeight;
		System.out.println(ppuX);
		System.out.println(ppuY);
	}
	
	public WorldRenderer(World world,OrthographicCamera camera,int w,int h) {
		this.world = world;
		cam = camera;
		this.CamHeight = cam.viewportHeight;
		this.CamWidth = cam.viewportWidth;
//		this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		this.cam.position.set(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, 0);
//		this.cam.update();
		this.ppuX = 1;
		this.ppuY = 1;
//		this.spriteBatch = new SpriteBatch();
//		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}

	public void render() {
		Texture.setEnforcePotImages(false);
		
		Iterator<Body> Bodies = world.getBodies();
//		int bodyCount = world.getBodyCount();
		Body userCar = Bodies.next();
		ArrayList<Fixture> fixtures= userCar.getFixtureList();
		float x1 = userCar.getPosition().x;
		float y1 = userCar.getPosition().y;
//		Vector2 pos = UserCar.getPosition();
//		Vector3 camPos = cam.position;
//		cam.translate(x1-camPos.x,y1-camPos.y,0);
		cam.position.set(x1, y1+100, 0);
		cam.update();

		debugRenderer.setProjectionMatrix(cam.combined);
//		spriteBatch.setProjectionMatrix(cam.combined);
		// render User's car
		
		debugRenderer.setColor(Color.RED);
		debugRenderer.begin(ShapeType.FilledRectangle);
		debugRenderer.filledRect(-150, 0, 300, 300);
		debugRenderer.setColor(Color.BLUE);
		debugRenderer.filledRect(150, 0, 300, 300);
		debugRenderer.end();
		
//		spriteBatch.begin();
//		spriteBatch.draw(blockTexture, userCar.getPosition().x-50, userCar.getPosition().y, (float)100,(float)100);
//		spriteBatch.draw(blockTexture, UserCar.getPosition().x * ppuX, UserCar.getPosition().y * ppuY, x1/2 * ppuX, y1/2 * ppuY);
//		spriteBatch.end();
	}
}
