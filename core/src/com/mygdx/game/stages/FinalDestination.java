package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class FinalDestination extends Map{

	public FinalDestination(SpriteBatch batch) {
		
		Texture background = new Texture("stages/final_destination/final_destination_background.png");
		Image background_image = new Image(background);
		background_image.setWidth(MyGdxGame.GAME_WIDTH);
		background_image.setHeight(MyGdxGame.GAME_HEIGHT);
		Rectangle[] rectangles = new Rectangle[4];
		
		Vector2 mainPlatform = new Vector2(MyGdxGame.GAME_WIDTH/5.59766F, MyGdxGame.GAME_HEIGHT/2.65356F);
		platforms = new Vector2[] {mainPlatform};
		
		Rectangle main = new Rectangle();		//Creating the bounding rectangles for collision detection
		Rectangle under = new Rectangle();
		Rectangle leftEdge = new Rectangle();
		Rectangle rightEdge = new Rectangle();
		
		main.setHeight(MyGdxGame.GAME_HEIGHT/20F);		//Setting the parameters of the bounding rectangles
		main.setWidth(MyGdxGame.GAME_WIDTH/1.60267F);
		main.setPosition(mainPlatform);
		
		under.setHeight(main.getHeight()/10);
		under.setWidth(main.getWidth());
		under.setPosition(mainPlatform.x, mainPlatform.y - under.getHeight());
		
		leftEdge.setHeight(main.getHeight()*0.8f);
		leftEdge.setWidth(10);
		leftEdge.setPosition(mainPlatform.x - leftEdge.getWidth(), mainPlatform.y);
		
		rightEdge.setHeight(leftEdge.getHeight());
		rightEdge.setWidth(leftEdge.getWidth());
		rightEdge.setPosition(mainPlatform.x + main.getWidth(), mainPlatform.y);
		
		Vector2 playerOneSpawnPoint = new Vector2(mainPlatform.x, mainPlatform.y + main.height);
		Vector2 playerTwoSpawnPoint = new Vector2(mainPlatform.x + 200, mainPlatform.y + main.height);
		Vector2 playerThreeSpawnPoint = new Vector2(mainPlatform.x + 400, mainPlatform.y + main.height);
		Vector2 playerFourSpawnPoint = new Vector2(mainPlatform.x + 600, mainPlatform.y + main.height);
		
		spawnPoints = new Vector2[] {playerOneSpawnPoint, playerTwoSpawnPoint, playerThreeSpawnPoint, playerFourSpawnPoint};
		
		rectangles[0] = main;
		rectangles[1] = under;
		rectangles[2] = leftEdge;
		rectangles[3] = rightEdge;
		
		create(background_image, rectangles, batch);
	}
	
}
