package com.crazycar.view;

import com.crazycar.model.Vehicle;
import com.crazycar.model.World;
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
	
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;

	private World world;
	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	private Texture blockTexture;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	public void setSize (int w, int h) {
//		this.world.width = w;
//		this.world.height = h;
//		System.out.println(this.world.width);
//		System.out.println(this.world.height);
		ppuX = (float)this.world.width/ CAMERA_WIDTH;
		ppuY = (float)this.world.height/ CAMERA_HEIGHT;
	}
	
	public WorldRenderer(World world,int w,int h) {
		this.world = world;
		cam = this.world.CarCam;
		
//		this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		this.cam.position.set(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, 0);
//		this.cam.update();
		this.ppuX = 1;
		this.ppuY = 1;
		this.spriteBatch = new SpriteBatch();
		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}

	public void render() {
		Texture.setEnforcePotImages(false);
		
		Vehicle UserCar = world.getUserCar();
		Rectangle rect = UserCar.getBounds();
		float x1 = UserCar.getPosition().x + rect.x;
		float y1 = UserCar.getPosition().y + rect.y;
//		Vector2 pos = UserCar.getPosition();
//		Vector3 camPos = cam.position;
//		cam.translate(camPos.x-pos.x,camPos.y-pos.y,camPos.z);
		cam.update();
		
		debugRenderer.setProjectionMatrix(cam.combined);
		spriteBatch.setProjectionMatrix(cam.combined);
		// render User's car
		
		debugRenderer.setColor(Color.RED);
		debugRenderer.begin(ShapeType.FilledRectangle);
		debugRenderer.filledRect(50, 0, 2 * ppuX, 2 * ppuY);
		debugRenderer.setColor(Color.BLUE);
		debugRenderer.filledRect(100, 0, 2 * ppuX, 2 * ppuY);
		debugRenderer.end();
		
		spriteBatch.begin();
		spriteBatch.draw(blockTexture, UserCar.getPosition().x * ppuX, UserCar.getPosition().y * ppuY, 2 * ppuX, 2 * ppuY);
//		spriteBatch.draw(blockTexture, UserCar.getPosition().x * ppuX, UserCar.getPosition().y * ppuY, x1/2 * ppuX, y1/2 * ppuY);
		spriteBatch.end();
	}
}
