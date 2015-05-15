package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Character {
	private StandingState standing;		//The various states that alternate in a game. Each state will be in charge of its own animation and movement pattern.
	private WalkingState walking;
	private RunningState running;
	private JumpingState jumping;
	private State currentState;		//The current state points to whatever state the character is in.

	private float posX;		//The current position of the character in screen coordinates
	private float posY;

	public Character() {
		standing = new StandingState();
		walking = new WalkingState();
		running = new RunningState();
		jumping = new JumpingState();
		currentState = standing;		//Initially the character is standing.
	}

	public void render() {		//render calls the render method of whatever state the character is currently in.
		currentState.render();
	}

	public void changeState(State toChange) {		//Method to change states depending on user input.
		currentState = toChange;
	}

	private class State {
		public void render() {

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

	private class WalkingState extends State {
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

		}

		public void render() {

		}
	}
}
