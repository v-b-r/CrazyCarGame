package com.crazycar.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class World {

	public Vehicle UserCar;
	public float height;
	public float width;
	public OrthographicCamera CarCam;
	
	public World(){
		this.UserCar = new Vehicle(new Vector2(0, 0));
		this.width = 320;
		this.height = 1200;
		CarCam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		CarCam.update(); 
//		System.out.format("cam width is %f and cam height is %f\n",CarCam.viewportWidth,CarCam.viewportHeight);
	}

	public Vehicle getUserCar() {
		return UserCar;
	}

	public void setCarCamViewport(int width, int height) {
		// TODO Auto-generated method stub
		this.CarCam.viewportWidth = width;
		this.CarCam.viewportHeight = height;
		this.CarCam.update();
	}

}
