package com.mygdx.game.stages;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class FinalDestination extends Map{
	
	public static final String BACKGROUND_LOCATION = "stages/final_destination/final_destination_background.png";
	
	public static final float MAIN_PLATFORM_LOCATION_X = MyGdxGame.GAME_WIDTH/4.615384f;
	public static final float MAIN_PLATFORM_LOCATION_Y = MyGdxGame.GAME_HEIGHT/2.41071f;
	public static final float MAIN_COLLISION_WIDTH = MyGdxGame.GAME_WIDTH/1.71275f;
	public static final float MAIN_COLLISION_HEIGHT = MyGdxGame.GAME_HEIGHT/16.3636f;
	
	public static final float LEFT_PLATFORM_LOCATION_X = MAIN_PLATFORM_LOCATION_X;
	public static final float LEFT_PLATFORM_LOCATION_Y = 0;
	public static final float LEFT_COLLISION_WIDTH = MyGdxGame.GAME_WIDTH/16.13445f;
	public static final float LEFT_COLLISION_HEIGHT = MyGdxGame.GAME_HEIGHT/2.4161f;
	
	public static final float RIGHT_PLATFORM_LOCATION_X = MyGdxGame.GAME_WIDTH/1.34737f;
	public static final float RIGHT_PLATFORM_LOCATION_Y = 0;
	public static final float RIGHT_COLLISION_WIDTH = LEFT_COLLISION_WIDTH;
	public static final float RIGHT_COLLISION_HEIGHT = LEFT_COLLISION_HEIGHT;
	
	public static final float LEDGE_COLLISION_WIDTH = 10;
	public static final float LEDGE_COLLISION_HEIGHT = MAIN_COLLISION_HEIGHT*0.8f;
	
	public static final float REDGE_COLLISION_WIDTH = LEDGE_COLLISION_WIDTH;
	public static final float REDGE_COLLISION_HEIGHT = LEDGE_COLLISION_HEIGHT;
	
	public static final float MAIN_CAP = MAIN_PLATFORM_LOCATION_Y + MAIN_COLLISION_HEIGHT*0.9f;
	public static final float LEFT_CAP = LEFT_PLATFORM_LOCATION_X;
	public static final float RIGHT_CAP = RIGHT_PLATFORM_LOCATION_X + RIGHT_COLLISION_WIDTH;
	
	public static final Vector2 LEFT_HANG_POSITION = new Vector2(MAIN_PLATFORM_LOCATION_X, MAIN_PLATFORM_LOCATION_Y + MAIN_COLLISION_HEIGHT);
	public static final Vector2 RIGHT_HANG_POSITION = new Vector2(MAIN_PLATFORM_LOCATION_X + MAIN_COLLISION_WIDTH, MAIN_PLATFORM_LOCATION_Y + MAIN_COLLISION_HEIGHT);
	
	private HashMap<Rectangle,Float> topSurfaces;
	private HashMap<Rectangle, Float> bottomSurfaces;
	private HashMap<Rectangle,Float> leftSurfaces;
	private HashMap<Rectangle, Float> rightSurfaces;
	private HashMap<Rectangle, Vector2> leftEdges;
	private HashMap<Rectangle, Vector2> rightEdges;
	
	Texture background;
	Image backgroundImage;
	
	Rectangle main, left, right, leftEdge, rightEdge;
	
	Vector2 mainPlatform;

	public FinalDestination(SpriteBatch batch) {
		
		topSurfaces = new HashMap<Rectangle, Float>();
		bottomSurfaces = new HashMap<Rectangle, Float>();
		leftSurfaces = new HashMap<Rectangle, Float>();
		rightSurfaces = new HashMap<Rectangle, Float>();
		leftEdges = new HashMap<Rectangle, Vector2>();
		rightEdges = new HashMap<Rectangle, Vector2>();

		background = new Texture(BACKGROUND_LOCATION);
		backgroundImage = new Image(background);
		backgroundImage.setWidth(MyGdxGame.GAME_WIDTH);
		backgroundImage.setHeight(MyGdxGame.GAME_HEIGHT);
		
		mainPlatform = new Vector2(MAIN_PLATFORM_LOCATION_X, MAIN_PLATFORM_LOCATION_Y);
		
		main = new Rectangle();
		left = new Rectangle();
		right = new Rectangle();
		leftEdge = new Rectangle();
		rightEdge = new Rectangle();
		
		main.setHeight(MAIN_COLLISION_HEIGHT);
		main.setWidth(MAIN_COLLISION_WIDTH);
		main.setPosition(mainPlatform);
		
		left.setHeight(LEFT_COLLISION_HEIGHT);
		left.setWidth(LEFT_COLLISION_WIDTH);
		left.setPosition(LEFT_PLATFORM_LOCATION_X, LEFT_PLATFORM_LOCATION_Y);
		
		right.setHeight(RIGHT_COLLISION_HEIGHT);
		right.setWidth(RIGHT_COLLISION_WIDTH);
		right.setPosition(RIGHT_PLATFORM_LOCATION_X, RIGHT_PLATFORM_LOCATION_Y);
		
		leftEdge.setHeight(LEDGE_COLLISION_HEIGHT);
		leftEdge.setWidth(LEDGE_COLLISION_WIDTH);
		leftEdge.setPosition(MAIN_PLATFORM_LOCATION_X - LEDGE_COLLISION_WIDTH, MAIN_PLATFORM_LOCATION_Y);
		
		rightEdge.setHeight(REDGE_COLLISION_HEIGHT);
		rightEdge.setWidth(REDGE_COLLISION_WIDTH);
		rightEdge.setPosition(MAIN_PLATFORM_LOCATION_X + MAIN_COLLISION_WIDTH, MAIN_PLATFORM_LOCATION_Y);
		
		Vector2 playerOneSpawnPoint = new Vector2(MAIN_PLATFORM_LOCATION_X, MAIN_PLATFORM_LOCATION_Y + MAIN_COLLISION_HEIGHT);
		Vector2 playerTwoSpawnPoint = new Vector2(MAIN_PLATFORM_LOCATION_X + 200, MAIN_PLATFORM_LOCATION_Y + MAIN_COLLISION_HEIGHT);
		Vector2 playerThreeSpawnPoint = new Vector2(MAIN_PLATFORM_LOCATION_X + 400, MAIN_PLATFORM_LOCATION_Y + MAIN_COLLISION_HEIGHT);
		Vector2 playerFourSpawnPoint = new Vector2(MAIN_PLATFORM_LOCATION_X + 600, MAIN_PLATFORM_LOCATION_Y + MAIN_COLLISION_HEIGHT);
		
		spawnPoints = new Vector2[] {playerOneSpawnPoint, playerTwoSpawnPoint, playerThreeSpawnPoint, playerFourSpawnPoint};
		
		topSurfaces.put(main, MAIN_CAP);
		leftSurfaces.put(left, LEFT_CAP);
		rightSurfaces.put(right, RIGHT_CAP);
		leftEdges.put(leftEdge, LEFT_HANG_POSITION);
		rightEdges.put(rightEdge, RIGHT_HANG_POSITION);
		
		create(backgroundImage, topSurfaces, bottomSurfaces, leftSurfaces, rightSurfaces, leftEdges, rightEdges, batch);
	}
	
}
