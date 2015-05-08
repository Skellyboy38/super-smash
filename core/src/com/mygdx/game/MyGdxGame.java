package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.HomeScreen;

public class MyGdxGame extends ApplicationAdapter {
	public static final int GAME_WIDTH = 1400;
	public static final int GAME_HEIGHT = 800;
	SpriteBatch batch;
	HomeScreen home;
	OrthographicCamera cam;
	Texture picture;
	float locationX;
	boolean moveRight;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		home = new HomeScreen(batch);
		cam = new OrthographicCamera();
		cam.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
		picture = new Texture("background.jpg");
		locationX = 0F;
		moveRight = true;
	}

	@Override
	public void render () {
		if(moveRight)
			locationX += 1;
		if(!moveRight)
			locationX -= 1;
		if(locationX >= GAME_WIDTH - picture.getWidth())
			moveRight = false;
		if(locationX <= 0)
			moveRight = true;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(picture, locationX, 0);
		batch.end();
	}
	
	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
