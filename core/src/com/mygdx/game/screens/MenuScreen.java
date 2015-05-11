package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.MyGdxGame;

public class MenuScreen extends Screen{

	private Button play;
	private Button settings;
	private Button quit;
	private SpriteBatch batch;
	public float locationX;
	boolean moveRight;
	Texture background;
	
	public MenuScreen(SpriteBatch batch) {
		this.batch = batch;
		play = new Button();
		play.setName("Play");
		settings = new Button();
		quit = new Button();
		background = new Texture("naruto.jpg");
	}
	
	@Override
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
	
	@Override
	public void render() {
		update();
		batch.draw(background, locationX, 0);
		
		
	}



}
