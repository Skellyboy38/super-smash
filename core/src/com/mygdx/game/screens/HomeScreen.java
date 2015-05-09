package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.MyGdxGame;

public class HomeScreen extends Screen{
	Button play;
	Button settings;
	Button quit;
	SpriteBatch batch;
	Texture background;
	public float locationX;
	boolean moveRight;
	MenuScreen menu = new MenuScreen(batch);
	
	public HomeScreen(SpriteBatch batch) {
		this.batch = batch;
		play = new Button();
		play.setName("Play");
		settings = new Button();
		quit = new Button();
		background = new Texture("background.jpg");
	}
	
	public void update() {
		if(moveRight)
			locationX += 1;
		if(!moveRight)
			locationX -= 1;
		if(locationX >= MyGdxGame.GAME_WIDTH - background.getWidth())
			moveRight = false;
		if(locationX <= 0)
			moveRight = true;
	}
	
	public void render() {
		update();
		batch.draw(background, locationX, 0);
	}
}
