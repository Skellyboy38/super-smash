package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Character {
	private StandingState standing;		//The various states that alternate in a game. Each state will be in charge of its own animation and movement pattern.
	private WalkingState walking;
	private RunningState running;
	private JumpingState jumping;
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
	private int counter;

	public Character() {
		standing = new StandingState();
		walking = new WalkingState();
		running = new RunningState();
		jumping = new JumpingState();
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
		
		currentState = standing;		//Initially the character is standing.
	}

	public void render() {		//render calls the render method of whatever state the character is currently in.
		currentState.render();
		update();
	}
	
	public void update() {
		counter += Gdx.graphics.getDeltaTime();
		if(canChangeState){//if the animation of a move is over
			if(Gdx.input.isKeyPressed(29) || Gdx.input.isKeyPressed(32)){//move left or right
				changeState(walking);//running is entered by tapping twice
			}
			
			else if(currentState == standing || currentState == running || currentState == crouching && Gdx.input.isKeyPressed(62) ){//jumping
				changeState(jumping);
			}
			else if(currentState == jumping && Gdx.input.isKeyPressed(62)){
				changeState(doubleJump);
			}
			else if(false){//hang on the edge of the stage
				changeState(hanging);
			}
			else if(currentState == standing && Gdx.input.isKeyPressed(47)){//crouching from standing
				changeState(crouching);
			}
			else{//return to standing
				currentState = standing;
			}
		}
	}
	public void changeState(State toChange) {		//Method to change states depending on user input.
		currentState = toChange;
		counter = 0;
		canChangeState = false;
	}

	private class State {
		Texture image;
		Animation animation;
		public void render() {
			update();
		}
		
		public void update() {
			
		}
		
		public void updateImage(Texture newImage) {
			image = newImage;
		}
		
		public void updateAnimation(Animation newAnimation) {
			animation = newAnimation;
		}
	}

	private class StandingState extends State {
		Texture image;
		Animation animation;
		public StandingState() {

		}
		
		public void update() {

		}

		public void render() {

		}
	}

	private class WalkingState extends State {//also needed to enter running state (double tap)
		Texture image;
		Animation animation;
		public WalkingState() {
			
		}
		
		public void update() {

		}

		public void render() {

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

	private class JumpingState extends State {
		Texture image;
		Animation animation;
		public JumpingState() {

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
