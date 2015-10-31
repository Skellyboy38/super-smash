package com.mygdx.game.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.Fighter;
import com.mygdx.game.stages.Map;

public class FightScreen extends Screen{
	private Map map;
	private Stage stage;
	private OrthographicCamera camera;
	private ArrayList<Fighter> characters;
	private SpriteBatch batch;
	private Screen back;
	private MyGdxGame game;

	Set<Rectangle> topSurfaceCollisionSet;
	Set<Rectangle> bottomSurfaceCollisionSet;
	Set<Rectangle> leftSurfaceCollisionSet;
	Set<Rectangle> rightSurfaceCollisionSet;
	Set<Rectangle> leftHangCollisionSet;
	Set<Rectangle> rightHangCollisionSet;

	HashMap<Rectangle, Float> topSurfaces;
	HashMap<Rectangle, Float> bottomSurfaces;
	HashMap<Rectangle, Float> leftSurfaces;
	HashMap<Rectangle, Float> rightSurfaces;
	HashMap<Rectangle, Vector2> leftHangs;
	HashMap<Rectangle, Vector2> rightHangs;

	HashMap<Fighter, HashMap<Rectangle, Float>> fighterToMapCollisionBoxes;

	private float cameraWidth;
	private float cameraHeight;

	public FightScreen(MyGdxGame game, SpriteBatch batch, Map map, ArrayList<Fighter> characters, Stage stage) {
		super(game);
		this.map = map;
		this.batch = batch;
		this.characters = characters;
		this.game = game;
		this.stage = stage;
		camera = (OrthographicCamera)stage.getCamera();

		cameraHeight = MyGdxGame.GAME_HEIGHT;
		cameraWidth = MyGdxGame.GAME_WIDTH;

		topSurfaces = map.getTopSurfacesMap();
		bottomSurfaces = map.getBottomSurfacesMap();
		leftSurfaces = map.getLeftEdgesMap();
		rightSurfaces = map.getRightEdgesMap();
		leftHangs = map.getLeftHangMap();
		rightHangs = map.getRightHangMap();
		
		topSurfaceCollisionSet = topSurfaces.keySet();
		bottomSurfaceCollisionSet = bottomSurfaces.keySet();
		leftSurfaceCollisionSet = leftSurfaces.keySet();
		rightSurfaceCollisionSet = rightSurfaces.keySet();
		leftHangCollisionSet = leftHangs.keySet();
		rightHangCollisionSet = rightHangs.keySet();

		int counter = 0;
		for(int i = 0; i < characters.size(); i++)
		{
			characters.get(i).setSpawnPoint(map.getSpawnPoints()[counter]);
			counter++;
		}
	}

	public void addCharacters(ArrayList<Fighter> characters) {
		this.characters = characters;
	}

	public void render() {
		super.render();
		stage.act();
		stage.draw();
		map.render();
		for(Fighter f : characters) {
			f.render();
		}
		update();
	}

	public void addScreens(Screen back) {
		this.back = back;
	}

	public void clear() {
		map = null;
	}

	public void updateCamera() {
		camera.viewportHeight = cameraHeight;
		camera.viewportWidth = cameraWidth;
		camera.translate(1,1);
	}

	boolean contactTopSurface, contactBottomSurface, contactLeftSurface, contactRightSurface;
	
	public void update() {
		
		contactTopSurface = false;
		contactRightSurface = false;
		contactLeftSurface = false;
		contactBottomSurface = false;
		
		for(Fighter f : characters)
		{
			for(Rectangle r : topSurfaceCollisionSet)
			{
				if(Intersector.overlaps(r, f.getCollisionBoxes()[0]))
				{
					f.capFromBottom(topSurfaces.get(r));
					contactTopSurface = true;
				}
			}
			
			if(!contactTopSurface)
			{
				f.enableCapFromBottom();
			}

			for(Rectangle r : bottomSurfaceCollisionSet)
			{
				if(Intersector.overlaps(r, f.getCollisionBoxes()[0]))
				{
					f.capFromTop(bottomSurfaces.get(r));
					contactBottomSurface = true;
				}
			}
			
			if(!contactLeftSurface)
			{
				f.enableCapFromTop();
			}
			
			for(Rectangle r : leftSurfaceCollisionSet)
			{
				if(Intersector.overlaps(r, f.getCollisionBoxes()[0]))
				{
					f.capFromRight(leftSurfaces.get(r));
				}
			}
			
			for(Rectangle r : rightSurfaceCollisionSet)
			{
				if(Intersector.overlaps(r, f.getCollisionBoxes()[0]))
				{
					f.capFromLeft(rightSurfaces.get(r));
				}
			}
			
			for(Rectangle r : leftHangCollisionSet)
			{
				if(Intersector.overlaps(r, f.getCollisionBoxes()[2]))
				{
					f.setPosition(leftHangs.get(r).x - f.getWidth()*0.75f, leftHangs.get(r).y - f.getHeight()*0.9f);
					f.hangLeft();
				}
			}
			
			for(Rectangle r : rightHangCollisionSet)
			{
				if(Intersector.overlaps(r, f.getCollisionBoxes()[1]))
				{
					f.setPosition(rightHangs.get(r).x + r.getWidth() - f.getWidth()*0.245f, rightHangs.get(r).y - f.getHeight()*0.9f);
					f.hangRight();
				}
			}
		}


		if(Gdx.input.isKeyPressed(31)) {	
			game.changeScreen(back);
			((CharacterSelect)(back)).lightReset();
		}
	}

	public void show() {
		Gdx.input.setInputProcessor(stage);
	}



}
