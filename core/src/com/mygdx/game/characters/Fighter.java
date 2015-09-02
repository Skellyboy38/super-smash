package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Fighter {
	SpriteBatch batch;
	private Animation[] animations;
	private float positionX, positionY;
	private Rectangle collisionBox;
	private ShapeRenderer shapeRenderer;
	
	private boolean keyHolding;
	private boolean showBoxes;
	private boolean capVerticalPosition;
	
	Animation standingLeftAnimation, standingRightAnimation, walkingLeftAnimation, walkingRightAnimation, jumpingLeftAnimation, jumpingRightAnimation;
	
	private StandingLeftState standingLeft;		//The various states that alternate in a game. Each state will be in charge of its own animation and movement pattern.
	private StandingRightState standingRight;
	
	private WalkingLeftState walkingLeft;
	private WalkingRightState walkingRight;
	
	private FallingLeftState fallingLeft;
	private FallingRightState fallingRight;
	
	private JumpingLeftState jumpingLeft;
	private JumpingRightState jumpingRight;
	
	private RunningState running;
	private DoubleJumpState doubleJump;
	private HangingState hanging;
	private CrouchingState crouching;
	
	private NeutralAState neutralA;
	private SideSmashAState sideSmashA;
	private UpSmashAState upSmashA;
	private DownSmashAState downSmashA;
	private SideTiltAState sideTiltA;
	private UpTiltAState upTiltA;
	private DownTiltAState downTiltA;
	private DashAState dashA;
	private NeutralAirAState neutralAirA;
	private SideForwardAirAState sideForwardAirA;
	private SideBackwardAirAState sideBackwardAirA;
	private UpAirAState upAirA;
	private DownAirAState downAirA;
	private ReturnFromHangingAState returnFromHangingA;
	
	private NeutralBState neutralB;
	private SideBState sideB;
	private UpBState upB;
	private DownBState downB;
	
	private GrabState grab;
	private SideForwardThrowState sideForwardThrow;
	private SideBackwardThrowState sideBackwardThrow;
	private UpThrowState upThrow;
	private DownThrowState downThrow;
	
	private ShieldState shield;
	
	private State currentState;		//The current state points to whatever state the character is in.

	private float posX;		//The current position of the character in screen coordinates
	private float posY;
	
	private boolean canChangeState;
	private float counter;

	public Fighter(SpriteBatch batch, float posX, float posY) {
		this.batch = batch;
		positionX = posX;
		positionY = posY;
		keyHolding = false;
		showBoxes = false;
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setColor(1,1,0,1);
		capVerticalPosition = false;
		
		standingLeft = new StandingLeftState();
		standingRight = new StandingRightState();
		
		walkingLeft = new WalkingLeftState();
		walkingRight = new WalkingRightState();
		
		fallingLeft = new FallingLeftState();
		fallingRight = new FallingRightState();
		
		jumpingLeft = new JumpingLeftState();
		jumpingRight = new JumpingRightState();
		
		running = new RunningState();
		doubleJump = new DoubleJumpState();
		hanging = new HangingState();
		crouching = new CrouchingState();
		
		neutralA = new NeutralAState();
		sideSmashA = new SideSmashAState();
		upSmashA = new UpSmashAState();
		downSmashA = new DownSmashAState();
		sideTiltA = new SideTiltAState();
		upTiltA = new UpTiltAState();
		downTiltA = new DownTiltAState();
		neutralAirA = new NeutralAirAState();
		sideForwardAirA = new SideForwardAirAState();
		sideBackwardAirA = new SideBackwardAirAState();
		upAirA = new UpAirAState();
		downAirA = new DownAirAState();
		returnFromHangingA = new ReturnFromHangingAState();
		
		neutralB = new NeutralBState();
		sideB = new SideBState();
		upB = new UpBState();
		downB = new DownBState();
		
		grab = new GrabState();
		sideForwardThrow = new SideForwardThrowState();
		sideBackwardThrow = new SideBackwardThrowState();
		upThrow = new UpThrowState();
		downThrow = new DownThrowState();
		
		shield = new ShieldState();
		
		canChangeState = true;
		counter = 0;
		
		currentState = jumpingRight;		//Initially the character is standing.
	}
	
	public State getState() {
		return currentState;
	}
	
	public void addAnimations(Animation[] animations) {
		this.animations = animations;
		
		standingLeftAnimation = animations[0];
		standingRightAnimation = animations[1];
		walkingLeftAnimation = animations[2];
		walkingRightAnimation = animations[3];
		jumpingLeftAnimation = animations[4];
		jumpingRightAnimation = animations[5];
	}
	
	public void addCollisionBoxes(Rectangle rect) {
		this.collisionBox = rect;
	}
	
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void render() {		//render calls the render method of whatever state the character is currently in.
		currentState.render();
		update();
		if(showBoxes) {			//Draw all the collision boxes
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.rect(collisionBox.getX(), collisionBox.getY(), collisionBox.getWidth(), collisionBox.getHeight());
			shapeRenderer.end();
		}
	}
	
	public void update() {
		counter += Gdx.graphics.getDeltaTime();
		collisionBox.setPosition(positionX + walkingLeftAnimation.getKeyFrame(0, false).getRegionWidth()*0.125f, positionY + walkingLeftAnimation.getKeyFrame(0).getRegionHeight()*0.125f);
		
		if(canChangeState){//if the animation of a move is over
			if((currentState == standingLeft || currentState == walkingLeft || currentState == fallingLeft) && Gdx.input.isKeyPressed(62)){//jumping
				changeState(jumpingLeft);
			}
			else if((currentState == standingRight || currentState == walkingRight || currentState == fallingRight) && Gdx.input.isKeyPressed(62)){//jumping
				changeState(jumpingRight);
			}
			else if(currentState != jumpingLeft && currentState != jumpingRight && currentState != fallingLeft && currentState != fallingRight && Gdx.input.isKeyPressed(29)){//move left or right
				if(currentState == walkingLeft) {
					//Do nothing because you are already walking to the left
				}
				else
					changeState(walkingLeft);
			}
			else if(currentState != jumpingLeft && currentState != jumpingRight && currentState != fallingLeft && currentState != fallingRight && Gdx.input.isKeyPressed(32)) {
				if(currentState == walkingRight) {
					//Do nothing because you already are walking to the right
				}
				else
					changeState(walkingRight);
			}
			else if(currentState == walkingLeft) {
				changeState(standingLeft);
			}
			else if(currentState == walkingRight) {
				changeState(standingRight);
			}
			
			
			if(Gdx.input.isKeyPressed(48) && !keyHolding) {		//If "T" is pressed, testing mode is entered.
				showBoxes = !showBoxes;
				keyHolding = true;
			}
			else if(!Gdx.input.isKeyPressed(48))
				keyHolding = false;
		}
	}
	public void changeState(State toChange) {		//Method to change states depending on user input.
		currentState = toChange;
		counter = 0;
		//canChangeState = false;
	}
	
	public boolean getDirection() {
		if(currentState == standingLeft || currentState == walkingLeft || currentState == jumpingLeft) {
			return true;
		}
		else
			return false;
	}
	
	public void capVerticalPosition() {
		capVerticalPosition = true;
	}
	
	public void uncapVerticalPosition() {
		capVerticalPosition = false;
	}

	private class State {
		public void render() {
		}
	}

	private class StandingLeftState extends State {
		TextureRegion currentFrame;
		public StandingLeftState() {
			
		}
		
		public void update() {
			currentFrame = standingLeftAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}
	}
	
	private class StandingRightState extends State {
		TextureRegion currentFrame;
		public StandingRightState() {
			
		}
		
		public void update() {
			currentFrame = standingRightAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}
	}

	private class WalkingLeftState extends State {//also needed to enter running state (double tap)
		TextureRegion currentFrame;
		int walkingSpeed = 4;
		public WalkingLeftState() {
			
		}
		
		public void update() {
			currentFrame = walkingLeftAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(29)) {
				if(capVerticalPosition) {
					positionX -= walkingSpeed;
				}
				else {
					changeState(fallingLeft);
				}
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}

	}
	
	private class WalkingRightState extends State {//also needed to enter running state (double tap)
		TextureRegion currentFrame;
		int walkingSpeed = 4;
		public WalkingRightState() {
			
		}
		
		public void update() {
			currentFrame = walkingRightAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(32)) {
				if(capVerticalPosition) {
					positionX += walkingSpeed;
				}
				else {
					changeState(fallingRight);
				}
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}

	}

	private class RunningState extends State {
		Texture image;
		Animation animation;
		public RunningState() {

		}
		
		public void update() {

		}

		public void render() {

		}

	}
	//======================================================================================================
	private class FallingLeftState extends State {
		TextureRegion currentFrame;
		double delta;
		float fallingSpeed = 0.5f;
		
		public FallingLeftState() {
			delta = 0;
		}
		
		public void update() {
			currentFrame = jumpingLeftAnimation.getKeyFrame(counter, false);
			if(delta > -5) {
				delta -= fallingSpeed;
			}
			if(Gdx.input.isKeyPressed(29)) {
				positionX -= 2;
			}
			else if(Gdx.input.isKeyPressed(32)) {
				positionX += 2;
			}
			positionY += delta;
			if(capVerticalPosition) {
				changeState(standingLeft);
			}
		}
		
		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}
	}
	//======================================================================================================
	private class FallingRightState extends State {
		TextureRegion currentFrame;
		double delta;
		float fallingSpeed = 0.5f;
		public FallingRightState() {
			delta = 0;
		}
		
		public void update() {
			currentFrame = jumpingRightAnimation.getKeyFrame(counter, false);
			if(delta > -5) {
				delta -= fallingSpeed;
			}
			if(Gdx.input.isKeyPressed(29)) {
				positionX -= 2;
			}
			else if(Gdx.input.isKeyPressed(32)) {
				positionX += 2;
			}
			positionY += delta;
			if(capVerticalPosition) {
				changeState(standingLeft);
			}
		}
		
		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}
	}
	//======================================================================================================
	private class JumpingLeftState extends State {
		TextureRegion currentFrame;
		int movementSpeedSideways = 4;
		int jumpVelocity = 100;
		int acceleration = -500;
		double delta;
		public JumpingLeftState() {
			delta = 0;
		}
		
		public void update() {
			if(counter >= 1000) {
				canChangeState = true;
			}
			if(Gdx.input.isKeyPressed(29)) {
				positionX -= movementSpeedSideways;
			}
			else if(Gdx.input.isKeyPressed(32)) {
				positionX += movementSpeedSideways;
			}
			if(delta > -10)
				delta = jumpVelocity*counter + 0.5*acceleration*Math.pow(counter,2);
			positionY += delta;
			if(capVerticalPosition && delta < 0) {
				delta = 0;
				changeState(standingLeft);
			}
			currentFrame = jumpingLeftAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}

	}
	
	private class JumpingRightState extends State {
		TextureRegion currentFrame;
		int movementSpeedSideways = 4;
		int jumpVelocity = 100;
		int acceleration = -500;
		double delta;
		public JumpingRightState() {

		}
		public void update() {
			if(counter >= 1000) {
				canChangeState = true;
			}
			if(Gdx.input.isKeyPressed(29)) {
				positionX -= movementSpeedSideways;
			}
			else if(Gdx.input.isKeyPressed(32)) {
				positionX += movementSpeedSideways;
			}
			if(delta > -10)
				delta = jumpVelocity*counter + 0.5*acceleration*Math.pow(counter,2);
			positionY += delta;
			if(capVerticalPosition && delta < 0) {
				delta = 0;
				changeState(standingLeft);
			}
			currentFrame = jumpingRightAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY);
			batch.end();
		}

	}
	
	private class DoubleJumpState extends State {
		Texture image;
		Animation animation;
		public DoubleJumpState() {

		}
		
		public void update() {
			if(counter >= 1000) {
				canChangeState = true;
			}
		}

		public void render() {
			update();
		}

	}
	
	private class HangingState extends State {
		Texture image;
		Animation animation;
		public HangingState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class CrouchingState extends State {
		Texture image;
		Animation animation;
		public CrouchingState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class NeutralAState extends State {
		Texture image;
		Animation animation;
		public NeutralAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class SideSmashAState extends State {
		Texture image;
		Animation animation;
		public SideSmashAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class UpSmashAState extends State {
		Texture image;
		Animation animation;
		public UpSmashAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class DownSmashAState extends State {
		Texture image;
		Animation animation;
		public DownSmashAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class SideTiltAState extends State {
		Texture image;
		Animation animation;
		public SideTiltAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class UpTiltAState extends State {
		Texture image;
		Animation animation;
		public UpTiltAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class DownTiltAState extends State {
		Texture image;
		Animation animation;
		public DownTiltAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class DashAState extends State {
		Texture image;
		Animation animation;
		public DashAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class NeutralAirAState extends State {
		Texture image;
		Animation animation;
		public NeutralAirAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class SideForwardAirAState extends State {
		Texture image;
		Animation animation;
		public SideForwardAirAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class SideBackwardAirAState extends State {
		Texture image;
		Animation animation;
		public SideBackwardAirAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class UpAirAState extends State {
		Texture image;
		Animation animation;
		public UpAirAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class DownAirAState extends State {
		Texture image;
		Animation animation;
		public DownAirAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class ReturnFromHangingAState extends State {
		Texture image;
		Animation animation;
		public ReturnFromHangingAState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class NeutralBState extends State {
		Texture image;
		Animation animation;
		public NeutralBState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class SideBState extends State {
		Texture image;
		Animation animation;
		public SideBState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class UpBState extends State {
		Texture image;
		Animation animation;
		public UpBState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class DownBState extends State {
		Texture image;
		Animation animation;
		public DownBState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class GrabState extends State {
		Texture image;
		Animation animation;
		public GrabState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class SideForwardThrowState extends State {
		Texture image;
		Animation animation;
		public SideForwardThrowState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class SideBackwardThrowState extends State {
		Texture image;
		Animation animation;
		public SideBackwardThrowState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class UpThrowState extends State {
		Texture image;
		Animation animation;
		public UpThrowState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class DownThrowState extends State {
		Texture image;
		Animation animation;
		public DownThrowState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class ShieldState extends State {
		Texture image;
		Animation animation;
		public ShieldState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
}
