package com.crazycar.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Vehicle {

	public enum State {
		IDLE, MOVING, JUMPING, DYING
	}

	public static final float SPEED = 2f;	// unit per second
	static final float JUMP_VELOCITY = 1f;
	static final float SIZE = 0.5f; // half a unit

	Vector2 	position = new Vector2();
	Vector2 	acceleration = new Vector2();
	Vector2 	velocity = new Vector2(10,0);
	Rectangle 	bounds = new Rectangle();
	State		state = State.IDLE;
	
    World world;
    public OrthographicCamera camera; 

	public Vehicle(Vector2 position) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		
  	    world = new World(new Vector2(0, -100), true);
//        camera = new OrthographicCamera();  
//        camera.viewportHeight = 320;  
//        camera.viewportWidth = 480;  
//        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);  
//        camera.update();  
		
        //Dynamic Body  // Not use yet
        BodyDef bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;  
        bodyDef.position.set(0,0);//camera.viewportWidth / 2, camera.viewportHeight / 2);  
        Body body = world.createBody(bodyDef);
        PolygonShape dynamicVehicle = new PolygonShape();
        dynamicVehicle.setAsBox(5, 5, position, 0.0f);
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicVehicle;  
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 0.5f;  
        body.createFixture(fixtureDef); 
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setState(State newState) {
		this.state = newState;
	}
	
	public void update(float delta) {
		position.add(velocity.cpy().mul(delta));
//		System.out.format("position value is %f\n",position.x);
//		System.out.println(velocity.cpy().mul(delta));
	}

	public Vector2 getVelocity() {
		// TODO Auto-generated method stub
		return velocity;
	}

}
