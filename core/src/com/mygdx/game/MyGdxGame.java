package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.OnePlayerCharacterSelect;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.Screen;
import com.mygdx.game.screens.HomeScreen;
import com.mygdx.game.screens.TwoPlayerCharacterSelect;

public class MyGdxGame extends ApplicationAdapter {
	public static final int GAME_WIDTH = 1400;
	public static final int GAME_HEIGHT = 800;
	SpriteBatch batch;
	Screen screen;
	HomeScreen home;
	PlayScreen play;
	OnePlayerCharacterSelect player1;
	TwoPlayerCharacterSelect player2;
	OrthographicCamera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player1 = new OnePlayerCharacterSelect(this);
		player2 = new TwoPlayerCharacterSelect(this);
		play = new PlayScreen(this);
		home = new HomeScreen(this, batch);
		
		home.addScreens(play);
		play.addScreens(player1, player2, home);
		player1.addScreens(play);
		home.show();
		screen = home;
	}
	
	public void changeScreen(Screen toChange) {
		toChange.show();
		screen = toChange;
	}

	@Override
	public void render () {
		screen.render();
		update();
	}
	
	public void update(){
		
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
