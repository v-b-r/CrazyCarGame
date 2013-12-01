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
		UP, DOWN, JUMP, FIRE
	}
	private World world;
	private Vehicle UserCar;

	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};

	public WorldController(World world) {
		this.world = world;
		this.UserCar = world.getUserCar();
	}

	// ** Key presses and touches **************** //

	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}

	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void firePressed() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}

	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
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
		if (keys.get(Keys.UP)) {
			// up is pressed
			UserCar.setState(State.MOVING);
			UserCar.getVelocity().y = -Vehicle.SPEED;
			this.world.CarCam.translate(UserCar.getVelocity().cpy().mul(-1));
//			System.out.format("velocity value is %f and delta is %f\n",UserCar.getVelocity().x,delta);
			this.world.CarCam.update();
		}
		if (keys.get(Keys.DOWN)) {
			// up is pressed
			UserCar.setState(State.MOVING);
			UserCar.getVelocity().y = Vehicle.SPEED;
			this.world.CarCam.translate(UserCar.getVelocity().cpy().mul(-1));
			this.world.CarCam.update();
		}
		// need to check if both or none direction are pressed, then Bob is idle
		if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
				(!keys.get(Keys.UP) && !(keys.get(Keys.DOWN)))) {
			UserCar.setState(State.IDLE);
			// acceleration is 0 on the x
			//UserCar.getAcceleration().x = 0;
			// horizontal speed is 0
			UserCar.getVelocity().y = 0;
		}
	}
}
