package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class HomeScreen extends Screen{
	Button play;
	Button settings;
	Button quit;
	SpriteBatch batch;
	
	public HomeScreen(SpriteBatch batch) {
		this.batch = batch;
		play = new Button();
		play.setName("Play");
		settings = new Button();
		quit = new Button();
	}
	
	public void update() {
		
	}
	
	public void render() {
		play.draw(batch, 1f);
	}
}
