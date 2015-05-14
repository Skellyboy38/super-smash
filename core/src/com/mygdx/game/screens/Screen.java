package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class Screen {
	private MyGdxGame game;
	public Screen(MyGdxGame game){
		this.game = game;
	}
	public Screen(SpriteBatch batch){
	}
	public void render(){
		if(Gdx.input.isKeyPressed(131)) {
			escPressed();
		}
	}
	public void show() {
		
	}
	public void update(){
		
	}
	
	public void escPressed() {
		if(Gdx.graphics.isFullscreen()) {
			Gdx.graphics.setDisplayMode(game.GAME_WIDTH, game.GAME_HEIGHT,false);
		}
		else {
			Graphics.DisplayMode m = null;
			for(Graphics.DisplayMode mode: Gdx.graphics.getDisplayModes()) {
				if(m == null) {
					m = mode;
				} else {
					if(m.width < mode.width) {
						m = mode;
					}
				}
			}
			Gdx.graphics.setDisplayMode(m);
		}
	}
	
}
