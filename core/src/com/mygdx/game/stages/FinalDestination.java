package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

public class FinalDestination extends Map{

	public FinalDestination() {
		Texture stage = new Texture("stages/final_destination/final_destination.png");
		Texture background = new Texture("stages/final_destination/final_destination_background.png");
		Rectangle[] rectangles = new Rectangle[1];
		Rectangle main = new Rectangle();
		main.setHeight(MyGdxGame.GAME_HEIGHT/8);
		main.setWidth((MyGdxGame.GAME_WIDTH - stage.getWidth())/2);
		rectangles[0] = main;
		posX = (MyGdxGame.GAME_WIDTH - stage.getWidth())/2;
		posY = MyGdxGame.GAME_HEIGHT/6;
		create(stage, background, rectangles);
	}
	
}
