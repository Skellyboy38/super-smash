package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Character {
	private StandingState standing;		//The various states that alternate in a game. Each state will be in charge of its own animation and movement pattern.
	private WalkingState walking;
	private RunningState running;
	private JumpingState jumping;
	private HangingState hanging;
	
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
	private ThrowForwardState throwForward;
	private ThrowBackwardState throwbackward;
	private ThrowUpState throwUp;
	private ThrowDownState throwDown;
	
	private ShieldState shield;
	
	private State currentState;		//The current state points to whatever state the character is in.

	private float posX;		//The current position of the character in screen coordinates
	private float posY;

	public Character() {
		standing = new StandingState();
		walking = new WalkingState();
		running = new RunningState();
		jumping = new JumpingState();
		hanging = new HangingState();
		
		currentState = standing;		//Initially the character is standing.
	}

	public void render() {		//render calls the render method of whatever state the character is currently in.
		currentState.render();
		update();
	}
	
	public void update() {
		
	}

	public void changeState(State toChange) {		//Method to change states depending on user input.
		currentState = toChange;
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
		
		public StandingState() {

		}
	}

	private class WalkingState extends State {
	
		public WalkingState() {

		}

	}

	private class RunningState extends State {
		
		public RunningState() {

		}

	}

	private class JumpingState extends State {
		
		public JumpingState() {

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
	
	private class ThrowForwardState extends State {
		Texture image;
		Animation animation;
		public ThrowForwardState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class ThrowBackwardState extends State {
		Texture image;
		Animation animation;
		public ThrowBackwardState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class ThrowUpState extends State {
		Texture image;
		Animation animation;
		public ThrowUpState() {

		}

		public void update() {

		}

		public void render() {

		}
	}
	
	private class ThrowDownState extends State {
		Texture image;
		Animation animation;
		public ThrowDownState() {

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
