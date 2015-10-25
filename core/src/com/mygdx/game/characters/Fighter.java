package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Fighter {
	public static final int ANIMATION_WIDTH = (int)(MyGdxGame.GAME_WIDTH/12.5);
	public static final int ANIMATION_HEIGHT = (int)(MyGdxGame.GAME_HEIGHT/8.4375f);

	SpriteBatch batch;
	private float positionX, positionY;
	private Rectangle collisionBox;
	private ShapeRenderer shapeRenderer;

	private boolean keyHoldingT;
	private boolean keyHoldingSpace;
	
	private boolean showBoxes;
	private boolean capVerticalPosition;

	public static final int LEFT_KEY = 29;
	public static final int RIGHT_KEY = 32;
	public static final int SPACE_KEY = 62;
	
	private double fallingSpeed;

	private Animation standingLeftAnimation, standingRightAnimation, walkingLeftAnimation, walkingRightAnimation, jumpingLeftAnimation, jumpingRightAnimation, 
	runningLeftAnimation, runningRightAnimation;

	private StandingLeftState standingLeft;		//The various states that alternate in a game. Each state will be in charge of its own animation and movement pattern.
	private StandingRightState standingRight;

	private WalkingLeftState walkingLeft;
	private WalkingRightState walkingRight;

	private FallingLeftState fallingLeft;
	private FallingRightState fallingRight;

	private JumpingLeftState jumpingLeft;
	private JumpingRightState jumpingRight;

	private RunningLeftState runningLeft;
	private RunningRightState runningRight;

	private DoubleJumpingLeftState doubleJumpingLeft;
	private DoubleJumpingRightState doubleJumpingRight;

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

	private boolean canChangeState;
	private float counter;

	public Fighter(SpriteBatch batch, float posX, float posY) {
		this.batch = batch;
		positionX = posX;
		positionY = posY;
		
		keyHoldingT = false;
		keyHoldingSpace = false;
		
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

		runningLeft = new RunningLeftState();
		runningRight = new RunningRightState();

		doubleJumpingLeft = new DoubleJumpingLeftState();
		doubleJumpingRight = new DoubleJumpingRightState();

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

		currentState = fallingRight;		//Initially the character is standing.
		fallingSpeed = 0;
	}

	public State getState() {
		return currentState;
	}
	
	private void resetFallingSpeed()
	{
		fallingSpeed = 0;
	}
	
	private void resetCounter(State toChange)
	{
		if((currentState == jumpingLeft && toChange == jumpingRight) || (currentState == jumpingRight && toChange == jumpingLeft))
		{
			
		}
		else if((currentState == doubleJumpingLeft && toChange == doubleJumpingRight) || (currentState == doubleJumpingRight && toChange == doubleJumpingLeft))
		{
			
		}
		else {
			counter = 0;
		}
	}

	public Vector2 getPosition() {
		return new Vector2(positionX, positionY);
	}

	public void addAnimations(Animation[] animations) {

		standingLeftAnimation = animations[0];
		standingRightAnimation = animations[1];
		walkingLeftAnimation = animations[2];
		walkingRightAnimation = animations[3];
		jumpingLeftAnimation = animations[4];
		jumpingRightAnimation = animations[5];
		runningLeftAnimation = animations[6];
		runningRightAnimation = animations[7];
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
		collisionBox.setPosition(positionX + ANIMATION_WIDTH*0.25f + collisionBox.getWidth(), positionY);

		if(canChangeState)
		{
			if(currentState == standingLeft)
			{
				if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					changeState(walkingLeft);
				}
				else if(Gdx.input.isKeyPressed(RIGHT_KEY)) 
				{
					changeState(walkingRight);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY))
				{
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
			else if(currentState == standingRight)
			{
				if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					changeState(walkingLeft);
				}
				else if(Gdx.input.isKeyPressed(RIGHT_KEY)) 
				{
					changeState(walkingRight);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY))
				{
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}
			else if(currentState == walkingLeft)
			{
				if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					//Continue walking to the left
				}
				else if(Gdx.input.isKeyPressed(RIGHT_KEY)) 
				{
					changeState(walkingRight);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY))
				{
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
			else if(currentState == walkingRight)
			{
				if(Gdx.input.isKeyPressed(RIGHT_KEY))
				{
					//Continue walking to the right
				}
				else if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					changeState(walkingLeft);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY))
				{
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}
			else if(currentState == jumpingLeft)
			{
				if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					//Continue jumping to the left
				}
				else if(Gdx.input.isKeyPressed(RIGHT_KEY)) 
				{
					changeState(jumpingRight);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY) && !keyHoldingSpace)
				{
					resetFallingSpeed();
					changeState(doubleJumpingLeft);
				}
			}
			else if(currentState == jumpingRight)
			{
				if(Gdx.input.isKeyPressed(RIGHT_KEY))
				{
					//Continue jumping to the right
				}
				else if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					changeState(jumpingLeft);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY)  && !keyHoldingSpace)
				{
					resetFallingSpeed();
					changeState(doubleJumpingRight);
				}
			}
			else if(currentState == doubleJumpingLeft)
			{
				if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					//Continue double jumping to the left
				}
				else if(Gdx.input.isKeyPressed(RIGHT_KEY)) 
				{
					changeState(doubleJumpingRight);
				}
			}
			else if(currentState == doubleJumpingRight)
			{
				if(Gdx.input.isKeyPressed(RIGHT_KEY))
				{
					//Continue double jumping to the right
				}
				else if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					changeState(doubleJumpingLeft);
				}
			}
			else if(currentState == fallingLeft)
			{
				if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					//Continue falling to the left
				}
				else if(Gdx.input.isKeyPressed(RIGHT_KEY)) 
				{
					changeState(fallingRight);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY))
				{
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
			else if(currentState == fallingRight)
			{
				if(Gdx.input.isKeyPressed(RIGHT_KEY))
				{
					//Continue falling to the right
				}
				else if(Gdx.input.isKeyPressed(LEFT_KEY))
				{
					changeState(fallingLeft);
				}
				if(Gdx.input.isKeyPressed(SPACE_KEY))
				{
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}	
		}
		checkKeyHoldings();
	}
	
	public void checkKeyHoldings()
	{
		if(Gdx.input.isKeyPressed(48) && !keyHoldingT) {		//If "T" is pressed, testing mode is entered.
			showBoxes = !showBoxes;
			keyHoldingT = true;
		}
		else if(!Gdx.input.isKeyPressed(48)) {
			keyHoldingT = false;
		}
		
		if(!keyHoldingSpace && Gdx.input.isKeyPressed(SPACE_KEY)) {
			keyHoldingSpace = true;
		}
		else if(!Gdx.input.isKeyPressed(SPACE_KEY)) {
			keyHoldingSpace = false;
		}
	}
	
	public void changeState(State toChange) {		//Method to change states depending on user input.
		resetCounter(toChange);
		currentState = toChange;
		canChangeState = false;
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
	//=====================================================================
	private class State {
		public void render() {
		}
	}
	//=====================================================================
	private class StandingLeftState extends State {
		TextureRegion currentFrame;
		public StandingLeftState() {

		}

		public void update() {
			canChangeState = true;
			currentFrame = standingLeftAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}
	}

	private class StandingRightState extends State {
		TextureRegion currentFrame;
		public StandingRightState() {

		}

		public void update() {
			canChangeState = true;
			currentFrame = standingRightAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}
	}

	private class WalkingLeftState extends State {//also needed to enter running state (double tap)
		TextureRegion currentFrame;
		int walkingSpeed = 4;
		public WalkingLeftState() {

		}

		public void update() {
			canChangeState = true;
			currentFrame = walkingLeftAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(LEFT_KEY)) {
				if(capVerticalPosition) {
					positionX -= walkingSpeed;
				}
				else {
					resetFallingSpeed();
					changeState(fallingLeft);
				}
			}
			else
			{
				changeState(standingLeft);
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}

	}

	private class WalkingRightState extends State {//also needed to enter running state (double tap)
		TextureRegion currentFrame;
		int walkingSpeed = 4;
		public WalkingRightState() {

		}

		public void update() {
			canChangeState = true;
			currentFrame = walkingRightAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(RIGHT_KEY)) {
				if(capVerticalPosition) {
					positionX += walkingSpeed;
				}
				else {
					resetFallingSpeed();
					changeState(fallingRight);
				}
			}
			else 
			{
				changeState(standingRight);
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}

	}

	private class RunningLeftState extends State {
		TextureRegion currentFrame;
		int runningSpeed = 8;
		public RunningLeftState() {

		}

		public void update() {
			canChangeState = true;
			currentFrame = runningLeftAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(LEFT_KEY)) {
				if(capVerticalPosition) {
					positionX -= runningSpeed;
				}
				else {
					resetFallingSpeed();
					changeState(fallingLeft);
				}
			}
			else {
				changeState(standingLeft);
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}

	}

	private class RunningRightState extends State {
		TextureRegion currentFrame;
		int runningSpeed = 8;
		public RunningRightState() {

		}

		public void update() {
			canChangeState = true;
			currentFrame = runningRightAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(RIGHT_KEY)) {
				if(capVerticalPosition) {
					positionX -= runningSpeed;
				}
				else {
					resetFallingSpeed();
					changeState(fallingLeft);
				}
			}
			else {
				changeState(standingRight);
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}
	}
	//======================================================================================================
	private class FallingLeftState extends State {
		TextureRegion currentFrame;
		float fallingAcceleration = 0.5f;

		public FallingLeftState() {
		}

		public void update() {
			canChangeState = true;
			currentFrame = jumpingLeftAnimation.getKeyFrame(counter, false);
			if(fallingSpeed > -5) {
				fallingSpeed -= fallingAcceleration;
			}
			if(Gdx.input.isKeyPressed(LEFT_KEY)) {
				positionX -= 2;
			}
			positionY += fallingSpeed;
			if(capVerticalPosition) {
				changeState(standingLeft);
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}
	}
	//======================================================================================================
	private class FallingRightState extends State {
		TextureRegion currentFrame;
		float fallingAcceleration = 0.5f;
		public FallingRightState() {
		}

		public void update() {
			canChangeState = true;
			currentFrame = jumpingRightAnimation.getKeyFrame(counter, false);
			if(fallingSpeed > -5) {
				fallingSpeed -= fallingAcceleration;
			}
			else if(Gdx.input.isKeyPressed(RIGHT_KEY)) {
				positionX += 2;
			}
			positionY += fallingSpeed;
			if(capVerticalPosition) {
				changeState(standingLeft);
			}
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}
	}
	//======================================================================================================
	private class JumpingLeftState extends State {
		TextureRegion currentFrame;
		int movementSpeedSideways = 4;
		int jumpVelocity = 100;
		int acceleration = -500;
		
		public JumpingLeftState() {
		}

		public void update() {
			if(!capVerticalPosition)
			{
				canChangeState = true;
			}
			if(Gdx.input.isKeyPressed(LEFT_KEY)) {
				positionX -= movementSpeedSideways;
			}
			if(fallingSpeed > -10)
				fallingSpeed = jumpVelocity*counter + 0.5*acceleration*Math.pow(counter,2);
			positionY += fallingSpeed;
			
			if(capVerticalPosition && fallingSpeed < 0) {
				resetFallingSpeed();
				changeState(standingLeft);
			}
			currentFrame = jumpingLeftAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}

	}

	private class JumpingRightState extends State {
		TextureRegion currentFrame;
		int movementSpeedSideways = 4;
		int jumpVelocity = 100;
		int acceleration = -500;

		public JumpingRightState() {
		}
		
		public void update() {
			if(!capVerticalPosition)
			{
				canChangeState = true;
			}
			if(Gdx.input.isKeyPressed(RIGHT_KEY)) {
				positionX += movementSpeedSideways;
			}
			if(fallingSpeed > -10)
				fallingSpeed = jumpVelocity*counter + 0.5*acceleration*Math.pow(counter,2);
			positionY += fallingSpeed;
			if(capVerticalPosition && fallingSpeed < 0) {
				resetFallingSpeed();
				changeState(standingRight);
			}
			currentFrame = jumpingRightAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}

	}

	private class DoubleJumpingLeftState extends State {
		TextureRegion currentFrame;
		int movementSpeedSideways = 4;
		int jumpVelocity = 100;
		int acceleration = -500;
		
		public DoubleJumpingLeftState() {
		}

		public void update() {
			if(!capVerticalPosition)
			{
				canChangeState = true;
			}
			if(Gdx.input.isKeyPressed(LEFT_KEY)) {
				positionX -= movementSpeedSideways;
			}
			if(fallingSpeed > -10)
				fallingSpeed = jumpVelocity*counter + 0.5*acceleration*Math.pow(counter,2);
			positionY += fallingSpeed;
			if(capVerticalPosition) {
				resetFallingSpeed();
				changeState(standingLeft);
			}
			currentFrame = jumpingLeftAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}

	}

	private class DoubleJumpingRightState extends State {
		TextureRegion currentFrame;
		int movementSpeedSideways = 4;
		int jumpVelocity = 100;
		int acceleration = -500;

		public DoubleJumpingRightState() {
		}
		
		public void update() {
			if(!capVerticalPosition)
			{
				canChangeState = true;
			}
			if(Gdx.input.isKeyPressed(RIGHT_KEY)) {
				positionX += movementSpeedSideways;
			}
			if(fallingSpeed > -10)
				fallingSpeed = jumpVelocity*counter + 0.5*acceleration*Math.pow(counter,2);
			positionY += fallingSpeed;
			if(capVerticalPosition) {
				resetFallingSpeed();
				changeState(standingRight);
			}
			currentFrame = jumpingRightAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}

	}
	//====================================================================================================
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
