package com.mygdx.game.stages;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class GrassLands extends Map
{
	public static final String BACKGROUND_LOCATION = "stages/grass_lands/grass_lands.png";

	public static final float PLATFORM1_LOCATION_X = MyGdxGame.GAME_WIDTH/20.21053f;
	public static final float PLATFORM1_LOCATION_Y = MyGdxGame.GAME_HEIGHT/1.7079f;
	
	public static final float PLATFORM2_LOCATION_X = MyGdxGame.GAME_WIDTH/1.87317f;
	public static final float PLATFORM2_LOCATION_Y = MyGdxGame.GAME_HEIGHT/2.49422f;
	
	public static final float PLATFORM1_COLLISION_WIDTH = MyGdxGame.GAME_WIDTH/2.77056f;
	public static final float PLATFORM1_COLLISION_HEIGHT = MyGdxGame.GAME_HEIGHT/16.61538f;
	
	public static final float PLATFORM2_COLLISION_WIDTH = MyGdxGame.GAME_WIDTH/2.382134f;
	public static final float PLATFORM2_COLLISION_HEIGHT = PLATFORM1_COLLISION_HEIGHT;

	public static final float UNDER1_COLLISION_WIDTH = PLATFORM1_COLLISION_WIDTH;
	public static final float UNDER1_COLLISION_HEIGHT = PLATFORM1_COLLISION_HEIGHT/10;
	public static final Vector2 UNDER1_COLLISION_POSITION = new Vector2(PLATFORM1_LOCATION_X, PLATFORM1_LOCATION_Y - UNDER1_COLLISION_HEIGHT);
	
	public static final float UNDER2_COLLISION_WIDTH = PLATFORM2_COLLISION_WIDTH;
	public static final float UNDER2_COLLISION_HEIGHT = PLATFORM2_COLLISION_HEIGHT/10;
	public static final Vector2 UNDER2_COLLISION_POSITION = new Vector2(PLATFORM2_LOCATION_X, PLATFORM2_LOCATION_Y - UNDER2_COLLISION_HEIGHT);

	public static final float LEFT1_COLLISION_WIDTH = 10;
	public static final float LEFT1_COLLISION_HEIGHT = PLATFORM1_COLLISION_HEIGHT*0.8f;
	
	public static final float LEFT2_COLLISION_WIDTH = 10;
	public static final float LEFT2_COLLISION_HEIGHT = PLATFORM1_COLLISION_HEIGHT*0.8f;

	public static final float RIGHT1_COLLISION_WIDTH = LEFT1_COLLISION_WIDTH;
	public static final float RIGHT1_COLLISION_HEIGHT = LEFT1_COLLISION_HEIGHT;
	
	public static final float RIGHT2_COLLISION_WIDTH = LEFT2_COLLISION_WIDTH;
	public static final float RIGHT2_COLLISION_HEIGHT = LEFT2_COLLISION_HEIGHT;

	public static final float TOP1_CAP = PLATFORM1_LOCATION_Y + PLATFORM1_COLLISION_HEIGHT*0.9f;
	public static final float UNDER1_CAP = UNDER1_COLLISION_POSITION.y*1.1f;
	
	public static final float TOP2_CAP = PLATFORM2_LOCATION_Y + PLATFORM2_COLLISION_HEIGHT*0.9f;
	public static final float UNDER2_CAP = UNDER2_COLLISION_POSITION.y*1.1f;

	public static final Vector2 LEFT1_HANG_POSITION = new Vector2(PLATFORM1_LOCATION_X, PLATFORM1_LOCATION_Y + PLATFORM1_COLLISION_HEIGHT);
	public static final Vector2 RIGHT1_HANG_POSITION = new Vector2(PLATFORM1_LOCATION_X + PLATFORM1_COLLISION_WIDTH, PLATFORM1_LOCATION_Y + PLATFORM1_COLLISION_HEIGHT);
	
	public static final Vector2 LEFT2_HANG_POSITION = new Vector2(PLATFORM2_LOCATION_X, PLATFORM2_LOCATION_Y + PLATFORM2_COLLISION_HEIGHT);
	public static final Vector2 RIGHT2_HANG_POSITION = new Vector2(PLATFORM2_LOCATION_X + PLATFORM2_COLLISION_WIDTH, PLATFORM2_LOCATION_Y + PLATFORM2_COLLISION_HEIGHT);

	private HashMap<Rectangle,Float> topSurfaces;
	private HashMap<Rectangle, Float> bottomSurfaces;
	private HashMap<Rectangle, Vector2> leftEdges;
	private HashMap<Rectangle, Vector2> rightEdges;

	Texture background;
	Image backgroundImage;

	Rectangle top1, top2, under1, under2, left1, left2, right1, right2;

	public GrassLands(SpriteBatch batch) {

		topSurfaces = new HashMap<Rectangle, Float>();
		bottomSurfaces = new HashMap<Rectangle, Float>();
		leftEdges = new HashMap<Rectangle, Vector2>();
		rightEdges = new HashMap<Rectangle, Vector2>();

		background = new Texture(BACKGROUND_LOCATION);
		backgroundImage = new Image(background);
		backgroundImage.setWidth(MyGdxGame.GAME_WIDTH);
		backgroundImage.setHeight(MyGdxGame.GAME_HEIGHT);

		top1 = new Rectangle();
		top2 = new Rectangle();
		under1 = new Rectangle();
		under2 = new Rectangle();
		left1 = new Rectangle();
		left2 = new Rectangle();
		right1 = new Rectangle();
		right2 = new Rectangle();
		
		top1.setHeight(PLATFORM1_COLLISION_HEIGHT);
		top1.setWidth(PLATFORM1_COLLISION_WIDTH);
		top1.setPosition(PLATFORM1_LOCATION_X, PLATFORM1_LOCATION_Y);
		top2.setHeight(PLATFORM2_COLLISION_HEIGHT);
		top2.setWidth(PLATFORM2_COLLISION_WIDTH);
		top2.setPosition(PLATFORM2_LOCATION_X, PLATFORM2_LOCATION_Y);
		under1.setHeight(UNDER1_COLLISION_HEIGHT);
		under1.setWidth(UNDER1_COLLISION_WIDTH);
		under1.setPosition(UNDER1_COLLISION_POSITION);
		under2.setHeight(UNDER2_COLLISION_HEIGHT);
		under2.setWidth(UNDER2_COLLISION_WIDTH);
		under2.setPosition(UNDER2_COLLISION_POSITION);
		left1.setHeight(LEFT1_COLLISION_HEIGHT);
		left1.setWidth(LEFT1_COLLISION_WIDTH);
		left1.setPosition(PLATFORM1_LOCATION_X - LEFT1_COLLISION_WIDTH, PLATFORM1_LOCATION_Y);
		left2.setHeight(LEFT2_COLLISION_HEIGHT);
		left2.setWidth(LEFT2_COLLISION_WIDTH);
		left2.setPosition(PLATFORM2_LOCATION_X - LEFT2_COLLISION_WIDTH, PLATFORM2_LOCATION_Y);
		right1.setHeight(RIGHT1_COLLISION_HEIGHT);
		right1.setWidth(RIGHT1_COLLISION_WIDTH);
		right1.setPosition(PLATFORM1_LOCATION_X + PLATFORM1_COLLISION_WIDTH, PLATFORM1_LOCATION_Y);
		right2.setHeight(RIGHT2_COLLISION_HEIGHT);
		right2.setWidth(RIGHT2_COLLISION_WIDTH);
		right2.setPosition(PLATFORM2_LOCATION_X + PLATFORM2_COLLISION_WIDTH, PLATFORM2_LOCATION_Y);

		Vector2 playerOneSpawnPoint = new Vector2(PLATFORM1_LOCATION_X, PLATFORM1_LOCATION_Y + PLATFORM1_COLLISION_HEIGHT);
		Vector2 playerTwoSpawnPoint = new Vector2(PLATFORM1_LOCATION_X + 200, PLATFORM1_LOCATION_Y + PLATFORM1_COLLISION_HEIGHT);
		Vector2 playerThreeSpawnPoint = new Vector2(PLATFORM2_LOCATION_X + 400, PLATFORM2_LOCATION_Y + PLATFORM2_COLLISION_HEIGHT);
		Vector2 playerFourSpawnPoint = new Vector2(PLATFORM2_LOCATION_X + 600, PLATFORM2_LOCATION_Y + PLATFORM2_COLLISION_HEIGHT);

		spawnPoints = new Vector2[] {playerOneSpawnPoint, playerTwoSpawnPoint, playerThreeSpawnPoint, playerFourSpawnPoint};

		topSurfaces.put(top1, TOP1_CAP);
		topSurfaces.put(top2, TOP2_CAP);
		bottomSurfaces.put(under1, UNDER1_CAP);
		bottomSurfaces.put(under2, UNDER2_CAP);
		leftEdges.put(left1, LEFT1_HANG_POSITION);
		leftEdges.put(left2, LEFT2_HANG_POSITION);
		rightEdges.put(right1, RIGHT1_HANG_POSITION);
		rightEdges.put(right2, RIGHT2_HANG_POSITION);

		create(backgroundImage, topSurfaces, bottomSurfaces, leftEdges, rightEdges, batch);
	}
}
