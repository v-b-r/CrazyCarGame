package com.crazycar.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.crazycar.model.Vehicle;
import com.crazycar.model.Vehicle.State;
import com.crazycar.view.WorldRenderer;

public class WorldController {
	enum Keys{
		UP, DOWN, LEFT, RIGHT, JUMP
	}
	private World world;
	private Body userCar;

	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
	};

	public WorldController(World world) {
		this.world = world;
		this.userCar = this.world.getBodies().next();
	}

	// ** Key presses and touches **************** //

	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}

	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}	

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}
	
	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}
	
	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
	}


	/** The main update method **/
	public void update(float delta) {
		processInput(delta);
//		userCar.applyForceToCenter(new Vector2(0,delta));
	}

	/** Change Bob's state and parameters based on input controls **/
	private void processInput(float delta) {
		if (keys.get(Keys.UP)) {
			// up is pressed
//			userCar.setState(State.MOVING);
			userCar.setLinearVelocity(0, 80);
//			this.world.CarCam.translate(UserCar.getVelocity().cpy().mul(-1));
//			System.out.format("velocity value is %f and delta is %f\n",UserCar.getVelocity().x,delta);
//			this.world.CarCam.update();
		}
		if (keys.get(Keys.DOWN)) {
			// up is pressed
//			userCar.setState(State.MOVING);
//			userCar.setLinearVelocity(0, -80);
			userCar.setLinearDamping((float)5);
//			this.world.CarCam.translate(UserCar.getVelocity().cpy().mul(-1));
//			this.world.CarCam.update();
		}
		if (keys.get(Keys.LEFT)) {
			// up is pressed
//			userCar.setState(State.MOVING);
//			userCar.applyForceToCenter(-5, 0);
			userCar.setLinearVelocity(-40, 40);
//			this.world.CarCam.translate(UserCar.getVelocity().cpy().mul(-1));
//			System.out.format("velocity value is %f and delta is %f\n",UserCar.getVelocity().x,delta);
//			this.world.CarCam.update();
		}
		if (keys.get(Keys.RIGHT)) {
			// up is pressed
//			userCar.setState(State.MOVING);
//			userCar.applyForceToCenter(5, 0);
			userCar.setLinearVelocity(40, 40);
//			this.world.CarCam.translate(UserCar.getVelocity().cpy().mul(-1));
//			System.out.format("velocity value is %f and delta is %f\n",UserCar.getVelocity().x,delta);
//			this.world.CarCam.update();
		}
		// need to check if both or none direction are pressed, then Bob is idle
		if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
				(!keys.get(Keys.UP) && !(keys.get(Keys.DOWN)))) {
			userCar.setLinearDamping((float)1);
			// acceleration is 0 on the x
			//UserCar.getAcceleration().x = 0;
			// horizontal speed is 0
//			userCar.getVelocity().y = 0;
		}
	}
}
