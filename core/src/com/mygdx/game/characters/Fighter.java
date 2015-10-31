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

public class Fighter implements FighterInterface 
{
	public static final int ANIMATION_WIDTH = (int)(MyGdxGame.GAME_WIDTH/12.5);
	public static final int ANIMATION_HEIGHT = (int)(MyGdxGame.GAME_HEIGHT/8.4375f);

	SpriteBatch batch;
	private float positionX, positionY;
	private Rectangle[] collisionBoxes;
	private ShapeRenderer shapeRenderer;

	private boolean keyHoldingT;
	private boolean keyHoldingspace;
	private boolean keyHoldingLeft;
	private boolean keyHoldingRight;

	private boolean showBoxes;
	private boolean capVerticalPosition;

	public int left;
	public int right;
	public int up;
	public int down;
	public int space;

	private double fallingSpeed;

	private Animation standingLeftAnimation, standingRightAnimation, walkingLeftAnimation, walkingRightAnimation, jumpingLeftAnimation, jumpingRightAnimation, 
	runningLeftAnimation, runningRightAnimation, hangingLeftAnimation, hangingRightAnimation;

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

	private HangingLeftState hangingLeft;
	private HangingRightState hangingRight;
	
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

	public Fighter(SpriteBatch batch, int[] controls) {
		this.batch = batch;

		keyHoldingT = false;
		keyHoldingspace = false;
		keyHoldingLeft = false;
		keyHoldingRight = false;
		
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

		hangingLeft = new HangingLeftState();
		hangingRight = new HangingRightState();
		
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
		
		setControls(controls);
	}
	
	public void setSpawnPoint(Vector2 spawn)
	{
		positionX = spawn.x;
		positionY = spawn.y;
	}
	
	public void setControls(int[] controls)
	{
		left = controls[0];
		right = controls[1];
		up = controls[2];
		down = controls[3];
		space = controls[4];
	}
	
	public float getHeight()
	{
		return ANIMATION_HEIGHT;
	}
	
	public float getWidth()
	{
		return ANIMATION_WIDTH;
	}

	public State getState() 
	{
		return currentState;
	}

	public void resetFallingSpeed()
	{
		fallingSpeed = 0;
	}

	public void resetCounter()
	{
		counter = 0;
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
		hangingLeftAnimation = animations[8];
		hangingRightAnimation = animations[9];
	}

	public void addCollisionBoxes(Rectangle[] boxes) {
		this.collisionBoxes = boxes;
	}

	public Rectangle[] getCollisionBoxes() {
		return collisionBoxes;
	}

	public void render() {		//render calls the render method of whatever state the character is currently in.
		currentState.render();
		update();
		if(showBoxes) {			//Draw all the collision boxes
			shapeRenderer.begin(ShapeType.Line);
			for(Rectangle r : collisionBoxes)
			{
				shapeRenderer.rect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
			}
			shapeRenderer.end();
		}
	}
	
	boolean isUp = true;
	boolean elevateBox = false;
	
	public void elevateCollisionBox()
	{
		elevateBox = true;
	}
	public void dropCollisionBox()
	{
		elevateBox = false;
	}

	public void updateCollisionBoxes()
	{
		if(!elevateBox)
		{
			collisionBoxes[0].setPosition(positionX + ANIMATION_WIDTH*0.25f + collisionBoxes[0].getWidth(), positionY);
		}
		else
		{
			collisionBoxes[0].setPosition(positionX + ANIMATION_WIDTH*0.25f + collisionBoxes[0].getWidth(), positionY + ANIMATION_HEIGHT*0.1f);
		}
		
		if(isHanging())
		{
			collisionBoxes[1].setPosition(positionX + ANIMATION_WIDTH*0.15f + collisionBoxes[0].getWidth(), positionY + ANIMATION_HEIGHT*2);
			collisionBoxes[2].setPosition(positionX + ANIMATION_WIDTH*0.65f, positionY + ANIMATION_HEIGHT*2);
		}
		else if(!isHanging() && isUp)
		{
			collisionBoxes[1].setPosition(positionX + ANIMATION_WIDTH*0.15f + collisionBoxes[0].getWidth(), positionY + ANIMATION_HEIGHT*0.5f);
			collisionBoxes[2].setPosition(positionX + ANIMATION_WIDTH*0.65f, positionY + ANIMATION_HEIGHT*0.5f);
		}
	}
	
	public boolean isHanging()
	{
		return (currentState == hangingLeft || currentState == hangingRight);
	}
	
	public void hangLeft()
	{
		isUp = false;
		resetCounter();
		changeState(hangingLeft);
	}
	
	public void hangRight()
	{
		isUp = false;
		resetCounter();
		changeState(hangingRight);
	}
	
	public void setPosition(float x, float y)
	{
		positionX = x;
		positionY = y;
	}
	
	public void setPosition(Vector2 position)
	{
		positionX = position.x;
		positionY = position.y;
	}
	
	public void setPositionX(float x)
	{
		positionX = x;
	}
	
	boolean canCapFromBottom = true;
	public void capFromBottom(float height)
	{
		if(canCapFromBottom)
		{
			positionY = height;
			if(getDirection())
			{
				changeState(standingLeft);
			}
			else
			{
				changeState(standingRight);
			}
		}
		canCapFromBottom = false;
	}
	
	public void enableCapFromBottom()
	{
		canCapFromBottom = true;
	}
	
	boolean canCapFromTop = true;
	public void capFromTop(float height)
	{
		if(canCapFromTop)
		{
			positionY = height - ANIMATION_HEIGHT;
			if(getDirection())
			{
				changeState(fallingLeft);
			}
			else
			{
				changeState(fallingRight);
			}
		}
		canCapFromTop = false;
	}
	
	public void enableCapFromTop()
	{
		canCapFromTop = true;
	}
	
	public void capFromLeft(float length)
	{
		positionX = length;
	}
	
	public void capFromRight(float length)
	{
		positionX = length - ANIMATION_WIDTH;
	}
	
	public void changeState(State toChange) {		//Method to change states depending on user input.
		currentState = toChange;
		canChangeState = false;
	}

	public boolean getDirection() {
		if(currentState == standingLeft || currentState == walkingLeft || currentState == jumpingLeft || currentState == runningLeft || currentState == doubleJumpingLeft || currentState == fallingLeft) {
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

	public void update() {
		counter += Gdx.graphics.getDeltaTime();
		updateCollisionBoxes();

		if(canChangeState)
		{
			if(currentState == standingLeft)
			{
				checkRunningLeft();
				if(Gdx.input.isKeyPressed(left))
				{
					changeState(walkingLeft);
				}
				else if(Gdx.input.isKeyPressed(right)) 
				{
					changeState(walkingRight);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					elevateCollisionBox();
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
			else if(currentState == standingRight)
			{
				checkRunningRight();
				if(Gdx.input.isKeyPressed(left))
				{
					changeState(walkingLeft);
				}
				else if(Gdx.input.isKeyPressed(right)) 
				{
					changeState(walkingRight);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					elevateCollisionBox();
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}
			else if(currentState == walkingLeft)
			{
				checkRunningLeft();
				if(Gdx.input.isKeyPressed(left))
				{
					//Continue walking to the left
				}
				else if(Gdx.input.isKeyPressed(right)) 
				{
					changeState(walkingRight);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					elevateCollisionBox();
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
			else if(currentState == walkingRight)
			{
				checkRunningRight();
				if(Gdx.input.isKeyPressed(right))
				{
					//Continue walking to the right
				}
				else if(Gdx.input.isKeyPressed(left))
				{
					changeState(walkingLeft);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					elevateCollisionBox();
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}
			else if(currentState == runningLeft)
			{
				if(Gdx.input.isKeyPressed(left))
				{
					//Continue walking to the left
				}
				else if(Gdx.input.isKeyPressed(right)) 
				{
					changeState(walkingRight);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					elevateCollisionBox();
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
			else if(currentState == runningRight)
			{
				if(Gdx.input.isKeyPressed(right))
				{
					//Continue walking to the right
				}
				else if(Gdx.input.isKeyPressed(left))
				{
					changeState(walkingLeft);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					elevateCollisionBox();
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}
			else if(currentState == jumpingLeft)
			{
				if(Gdx.input.isKeyPressed(left))
				{
					//Continue jumping to the left
				}
				else if(Gdx.input.isKeyPressed(right)) 
				{
					changeState(jumpingRight);
				}
				if(Gdx.input.isKeyPressed(space) && !keyHoldingspace)
				{
					resetCounter();
					resetFallingSpeed();
					changeState(doubleJumpingLeft);
				}
			}
			else if(currentState == jumpingRight)
			{
				if(Gdx.input.isKeyPressed(right))
				{
					//Continue jumping to the right
				}
				else if(Gdx.input.isKeyPressed(left))
				{
					changeState(jumpingLeft);
				}
				if(Gdx.input.isKeyPressed(space)  && !keyHoldingspace)
				{
					resetCounter();
					resetFallingSpeed();
					changeState(doubleJumpingRight);
				}
			}
			else if(currentState == doubleJumpingLeft)
			{
				if(Gdx.input.isKeyPressed(left))
				{
					//Continue double jumping to the left
				}
				else if(Gdx.input.isKeyPressed(right)) 
				{
					changeState(doubleJumpingRight);
				}
			}
			else if(currentState == doubleJumpingRight)
			{
				if(Gdx.input.isKeyPressed(right))
				{
					//Continue double jumping to the right
				}
				else if(Gdx.input.isKeyPressed(left))
				{
					changeState(doubleJumpingLeft);
				}
			}
			else if(currentState == fallingLeft)
			{
				if(Gdx.input.isKeyPressed(left))
				{
					//Continue falling to the left
				}
				else if(Gdx.input.isKeyPressed(right)) 
				{
					changeState(fallingRight);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
			else if(currentState == fallingRight)
			{
				if(Gdx.input.isKeyPressed(right))
				{
					//Continue falling to the right
				}
				else if(Gdx.input.isKeyPressed(left))
				{
					changeState(fallingLeft);
				}
				if(Gdx.input.isKeyPressed(space))
				{
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}
			else if(currentState == hangingLeft)
			{
				if(Gdx.input.isKeyPressed(space))
				{
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingRight);
				}
			}
			else if(currentState == hangingRight)
			{
				if(Gdx.input.isKeyPressed(space))
				{
					resetCounter();
					resetFallingSpeed();
					changeState(jumpingLeft);
				}
			}
		}
		checkKeyHoldings();
	}
	
	//Values to calculate if the running state should be entered
	int numberLeftClicks = 0;
	float initialLeftTime = 0;
	float initialRightTime = 0;
	float finalLeftTime = 0;
	float finalRightTime = 0;
	boolean leftConditionMet = false;
	int numberRightClicks = 0;
	boolean rightConditionMet = false;

	public void checkRunningLeft()
	{
		if(!keyHoldingLeft && Gdx.input.isKeyPressed(left) && numberLeftClicks == 0) {
			numberLeftClicks++;
			initialLeftTime = counter;
			keyHoldingLeft = true;
		}
		else if(!keyHoldingLeft && Gdx.input.isKeyPressed(left) && numberLeftClicks == 1)
		{
			finalLeftTime = counter;
			if(finalLeftTime - initialLeftTime < 0.2f)
			{
				leftConditionMet = true;
			}
			else 
			{
				initialLeftTime = 0;
				finalLeftTime = 0;
				numberLeftClicks = 0;
			}
		}
		else if(!Gdx.input.isKeyPressed(left)) {
			keyHoldingLeft = false;
		}

		if(leftConditionMet && Gdx.input.isKeyPressed(left))
		{
			if(currentState == walkingLeft)
			{
				resetRunningStats();
				changeState(runningLeft);
			}
		}
		else
		{
			leftConditionMet = false;
		}
	}

	public void checkRunningRight()
	{
		if(!keyHoldingRight && Gdx.input.isKeyPressed(right) && numberRightClicks == 0) {
			numberRightClicks++;
			initialRightTime = counter;
			keyHoldingRight = true;
		}
		else if(!keyHoldingRight && Gdx.input.isKeyPressed(right) && numberRightClicks == 1)
		{
			finalRightTime = counter;
			if(finalRightTime - initialRightTime < 0.2f)
			{
				rightConditionMet = true;
			}
			else 
			{
				initialRightTime = 0;
				finalRightTime = 0;
				numberRightClicks = 0;
			}
		}
		else if(!Gdx.input.isKeyPressed(right)) {
			keyHoldingRight = false;
		}

		if(rightConditionMet && Gdx.input.isKeyPressed(right))
		{
			if(currentState == walkingRight)
			{
				resetRunningStats();
				changeState(runningRight);
			}
		}
		else
		{
			rightConditionMet = false;
		}
	}
	
	public void resetRunningStats()
	{
		numberLeftClicks = 0;
		numberRightClicks = 0;
		initialLeftTime = 0;
		initialRightTime = 0;
		finalLeftTime = 0;
		finalRightTime = 0;
		leftConditionMet = false;
		rightConditionMet = false;
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

		if(!keyHoldingspace && Gdx.input.isKeyPressed(space)) {
			keyHoldingspace = true;
		}
		else if(!Gdx.input.isKeyPressed(space)) {
			keyHoldingspace = false;
		}
	}
	//=====================================================================
	public class State {
		public void render() {
		}
	}
	//=====================================================================
	private class StandingLeftState extends State {
		TextureRegion currentFrame;
		public StandingLeftState() {

		}

		public void update() {
			System.out.println("standing\n");
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
			System.out.println("standing\n");
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
			System.out.println("walking\n");
			canChangeState = true;
			currentFrame = walkingLeftAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(left)) {

				if(!canCapFromBottom) {
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
			System.out.println("walking\n");
			canChangeState = true;
			currentFrame = walkingRightAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(right)) {
				if(!canCapFromBottom) {
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
			System.out.println("running\n");
			canChangeState = true;
			currentFrame = runningLeftAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(left)) {
				if(!canCapFromBottom) {
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
			System.out.println("running\n");
			canChangeState = true;
			currentFrame = runningRightAnimation.getKeyFrame(counter, true);
			if(Gdx.input.isKeyPressed(right)) {
				if(!canCapFromBottom) {
					positionX += runningSpeed;
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
			System.out.println("falling\n");
			canChangeState = true;
			currentFrame = jumpingLeftAnimation.getKeyFrame(counter, false);
			if(fallingSpeed > -5) {
				fallingSpeed -= fallingAcceleration;
			}
			if(Gdx.input.isKeyPressed(left)) {
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
			System.out.println("falling\n");
			canChangeState = true;
			currentFrame = jumpingRightAnimation.getKeyFrame(counter, false);
			if(fallingSpeed > -5) {
				fallingSpeed -= fallingAcceleration;
			}
			else if(Gdx.input.isKeyPressed(right)) {
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
			System.out.println("jumping\n");
			if(counter > 0.2)
			{
				if(!capVerticalPosition)
				{
					isUp = true;
					canChangeState = true;
				}
				dropCollisionBox();
			}
			if(Gdx.input.isKeyPressed(left)) {
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
			System.out.println("jumping\n");
			if(counter > 0.2)
			{
				if(!capVerticalPosition)
				{
					isUp = true;
					canChangeState = true;
				}
				dropCollisionBox();
			}
			
			if(Gdx.input.isKeyPressed(right)) {
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
			if(Gdx.input.isKeyPressed(left)) {
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
			if(Gdx.input.isKeyPressed(right)) {
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
	private class HangingLeftState extends State {
		TextureRegion currentFrame;
		public HangingLeftState() {

		}

		public void update() {
			if(counter > 0.2)
			{
				canChangeState = true;
			}
			currentFrame = hangingLeftAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
		}
	}
	
	private class HangingRightState extends State {
		TextureRegion currentFrame;
		public HangingRightState() {

		}

		public void update() {
			if(counter > 0.2)
			{
				canChangeState = true;
			}
			currentFrame = hangingRightAnimation.getKeyFrame(counter, false);
		}

		public void render() {
			update();
			batch.begin();
			batch.draw(currentFrame, positionX, positionY, ANIMATION_WIDTH, ANIMATION_HEIGHT);
			batch.end();
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
