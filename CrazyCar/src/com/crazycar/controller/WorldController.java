package com.crazycar.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.crazycar.model.Vehicle;
import com.crazycar.model.Vehicle.State;
import com.crazycar.model.World;
import com.crazycar.view.WorldRenderer;

public class WorldController {
	enum Keys{
		LEFT, RIGHT, JUMP, FIRE
	}
	private World world;
	private Vehicle UserCar;

	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};

	public WorldController(World world) {
		this.world = world;
		this.UserCar = world.getUserCar();
	}

	// ** Key presses and touches **************** //

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void firePressed() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
	}

	public void fireReleased() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	/** The main update method **/
	public void update(float delta) {
		processInput(delta);
		UserCar.update(delta);
	}

	/** Change Bob's state and parameters based on input controls **/
	private void processInput(float delta) {
		if (keys.get(Keys.LEFT)) {
			// left is pressed
			UserCar.setState(State.MOVING);
			UserCar.getVelocity().x = -Vehicle.SPEED;
			this.world.CarCam.translate(UserCar.getVelocity().cpy());
//			System.out.format("velocity value is %f and delta is %f\n",UserCar.getVelocity().x,delta);
			this.world.CarCam.update();
		}
		if (keys.get(Keys.RIGHT)) {
			// left is pressed
			UserCar.setState(State.MOVING);
			UserCar.getVelocity().x = Vehicle.SPEED;
			this.world.CarCam.translate(UserCar.getVelocity().cpy());
			this.world.CarCam.update();
		}
		// need to check if both or none direction are pressed, then Bob is idle
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
				(!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
			UserCar.setState(State.IDLE);
			// acceleration is 0 on the x
			//UserCar.getAcceleration().x = 0;
			// horizontal speed is 0
			UserCar.getVelocity().x = 0;
		}
	}
}
