package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.Screen;
import com.mygdx.game.screens.HomeScreen;
import com.mygdx.game.screens.MenuScreen;

public class MyGdxGame extends ApplicationAdapter {
	public static final int GAME_WIDTH = 1400;
	public static final int GAME_HEIGHT = 800;
	SpriteBatch batch;
	Screen screen;
	HomeScreen home;
	MenuScreen menu;
	OrthographicCamera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		home = new HomeScreen(batch);
		menu = new MenuScreen(batch);
		screen = home;
		

		cam = new OrthographicCamera();
		cam.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
	}

	@Override
	public void render () {
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		screen.render();
		update();
		batch.end();
	}
	
	public void update(){
		if(home.locationX==100){
			screen = menu;
		}
		if(menu.locationX==100){
			screen = home;
		}
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
