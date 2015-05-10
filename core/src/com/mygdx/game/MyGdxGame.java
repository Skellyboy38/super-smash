package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.screens.HomeScreen;
import com.mygdx.game.screens.OnePlayerCharacterSelect;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.Screen;
import com.mygdx.game.screens.TwoPlayerCharacterSelect;

public class MyGdxGame extends ApplicationAdapter {
	public static final int GAME_WIDTH = 1400;
	public static final int GAME_HEIGHT = 800;
	Screen screen;
	HomeScreen home;
	PlayScreen play;
	OnePlayerCharacterSelect onePlayer;
	TwoPlayerCharacterSelect twoPlayer;
	
	@Override
	public void create () {
		screen = new Screen();
		onePlayer = new OnePlayerCharacterSelect(this);
		twoPlayer = new TwoPlayerCharacterSelect();
		play = new PlayScreen(this, onePlayer, twoPlayer);
		home = new HomeScreen(this, play);
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
