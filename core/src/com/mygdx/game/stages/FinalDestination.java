package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class FinalDestination extends Map{

	public FinalDestination(SpriteBatch batch) {
		
		Texture background = new Texture("stages/final_destination/final_destination_background.png");
		Image background_image = new Image(background);
		background_image.setWidth(MyGdxGame.GAME_WIDTH);
		background_image.setHeight(MyGdxGame.GAME_HEIGHT);
		Rectangle[] rectangles = new Rectangle[4];
		
		posX = MyGdxGame.GAME_WIDTH/5.59766F;			//Position in x-y coordinates for the rectangle
		posY = MyGdxGame.GAME_HEIGHT/2.65356F;
		
		Rectangle main = new Rectangle();		//Creating the bounding rectangles for collision detection
		Rectangle under = new Rectangle();
		Rectangle leftEdge = new Rectangle();
		Rectangle rightEdge = new Rectangle();
		
		main.setHeight(MyGdxGame.GAME_HEIGHT/20F);		//Setting the parameters of the bounding rectangles
		main.setWidth(MyGdxGame.GAME_WIDTH/1.60267F);
		main.setPosition(posX, posY);
		
		under.setHeight(main.getHeight()/10);
		under.setWidth(main.getWidth());
		under.setPosition(posX, posY - under.getHeight());
		
		leftEdge.setHeight(main.getHeight()*0.8f);
		leftEdge.setWidth(10);
		leftEdge.setPosition(posX - leftEdge.getWidth(), posY);
		
		rightEdge.setHeight(leftEdge.getHeight());
		rightEdge.setWidth(leftEdge.getWidth());
		rightEdge.setPosition(posX + main.getWidth(), posY);
		
		rectangles[0] = main;
		rectangles[1] = under;
		rectangles[2] = leftEdge;
		rectangles[3] = rightEdge;
		
		create(background_image, rectangles, batch);
	}
	
}
