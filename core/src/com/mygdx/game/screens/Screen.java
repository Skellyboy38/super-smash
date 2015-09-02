package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class Screen {
	public Screen(MyGdxGame game){
		
	}
	public Screen(SpriteBatch batch){
	}
	public void render(){
		if(Gdx.input.isKeyPressed(131)) {		//If the escape key is pressed (which is key 131) called the method.
			escPressed();
		}
	}
	public void show() {
		
	}
	
	public void clear() {
		
	}
	
	public void addScreens() {
		
	}
	public void update(){
		
	}
	
	public void escPressed() {
		if(Gdx.graphics.isFullscreen()) {		//If the game is in full screen, minimize it with the following dimensions (false means it is not full screen).
			Gdx.graphics.setDisplayMode(MyGdxGame.GAME_WIDTH, MyGdxGame.GAME_HEIGHT,false);
		}
		else {
			Graphics.DisplayMode m = null;
			for(Graphics.DisplayMode mode: Gdx.graphics.getDisplayModes()) {
				if(m == null) {
					m = mode;
				} else {
					if(m.width < mode.width) {		//Switches mode until it is the one with the largest width (full screen to be exact).
						m = mode;
					}
				}
			}
			Gdx.graphics.setDisplayMode(m);	//Set the display mode of the game to the full screen mode.
		}
	}
	
}
