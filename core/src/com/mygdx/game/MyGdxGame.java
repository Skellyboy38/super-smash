package com.mygdx.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.CharacterSelect;
import com.mygdx.game.screens.OptionsScreen;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.Screen;
import com.mygdx.game.screens.HomeScreen;
import com.mygdx.game.screens.StageSelectScreen;

public class MyGdxGame extends ApplicationAdapter {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double width = screenSize.getWidth();
	public static double height = screenSize.getHeight();
	public static final int GAME_WIDTH = (int)width;			//The width and height of the game tailored to each screen
	public static final int GAME_HEIGHT = (int)height;
	SpriteBatch batch;		//Drawing tool. So far only the home screen uses it for the flashy text.
	Screen screen;			//The default screen that will be used to point to the active screen
	HomeScreen home;		//Create the various screens here. 
	PlayScreen play;
	CharacterSelect player1;
	OptionsScreen options;
	StageSelectScreen stageSelect;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stageSelect = new StageSelectScreen(this, batch);
		player1 = new CharacterSelect(this, batch);
		options = new OptionsScreen(this);
		play = new PlayScreen(this);
		home = new HomeScreen(this, batch);				//Every screen should take in an instance of the game in order to perform the changeScreen() operation.
		
		home.addScreens(play);							//Add the screens that the specified screen interacts with (mostly for the purpose of changing the screen).
		play.addScreens(player1, home, options);
		player1.addScreens(play, stageSelect);
		options.addScreens(play);
		stageSelect.addScreens(player1);
		home.show();				//Make all the input listeners point to the home page once the game starts.
		screen = home;				//Sets the default screen to run the home screen at the beginning.
	}
	
	public void changeScreen(Screen toChange) {
		toChange.show();		//When you change screen, the show() method passes on the input listeners to the new screen.
		screen = toChange;		//Sets the default screen to point to the new screen.
	}

	@Override
	public void render () {
		screen.render();		//Render the screen.
		update();
	}
	
	public void update(){
		//Nothing happens yet. Everything so far is handled by the screens.
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
