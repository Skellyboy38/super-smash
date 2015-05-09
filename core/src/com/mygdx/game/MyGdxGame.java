package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.screens.HomeScreen;

public class MyGdxGame extends ApplicationAdapter {
	public static final int GAME_WIDTH = 1400;
	public static final int GAME_HEIGHT = 800;
	HomeScreen home;
	Stage stage;
	
	@Override
	public void create () {
		stage = new Stage();
		home = new HomeScreen(stage);
	}

	@Override
	public void render () {
		home.render();
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
