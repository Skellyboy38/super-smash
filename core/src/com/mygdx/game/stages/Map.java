package com.mygdx.game.stages;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Map {
	private Stage stage;
	private Image background;
	private ShapeRenderer shapeRenderer;
	
	private HashMap<Rectangle, Float> topSurfacesMap;
	private HashMap<Rectangle, Float> bottomSurfacesMap;
	private HashMap<Rectangle, Vector2> leftEdgesMap;
	private HashMap<Rectangle, Vector2> rightEdgesMap;
	
	private ArrayList<Rectangle> allRectangles;
	
	protected Vector2[] platforms;
	protected Vector2[] spawnPoints;
	
	boolean showBoxes;
	boolean keyHolding;
	
	public Map() {
		stage = new Stage();
		showBoxes = false;
		keyHolding = false;
		shapeRenderer = new ShapeRenderer();
	}
	
	public void create(Image background, HashMap<Rectangle, Float> topSurfacesMap, 
			HashMap<Rectangle, Float> bottomSurfacesMap, HashMap<Rectangle, Vector2> leftEdgesMap, HashMap<Rectangle, Vector2> rightEdgesMap, SpriteBatch batch) {
		this.background = background;
		this.topSurfacesMap = topSurfacesMap;
		this.bottomSurfacesMap = bottomSurfacesMap;
		this.leftEdgesMap = leftEdgesMap;
		this.rightEdgesMap = rightEdgesMap;
		stage.addActor(background);
		shapeRenderer.setColor(1,1,0,1);
		
		addAllRectangles();
	}
	
	public void addAllRectangles()
	{
		allRectangles = new ArrayList<Rectangle>();
		for(Rectangle r : topSurfacesMap.keySet())
		{
			allRectangles.add(r);
		}
		for(Rectangle r : bottomSurfacesMap.keySet())
		{
			allRectangles.add(r);
		}
		for(Rectangle r : leftEdgesMap.keySet())
		{
			allRectangles.add(r);
		}
		for(Rectangle r : rightEdgesMap.keySet())
		{
			allRectangles.add(r);
		}
	}
	
	public HashMap<Rectangle, Float> getTopSurfacesMap()
	{
		return topSurfacesMap;
	}
	
	public HashMap<Rectangle, Float> getBottomSurfacesMap()
	{
		return bottomSurfacesMap;
	}
	
	public HashMap<Rectangle, Vector2> getLeftEdgesMap()
	{
		return leftEdgesMap;
	}
	
	public HashMap<Rectangle, Vector2> getRightEdgesMap()
	{
		return rightEdgesMap;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Vector2[] getPlatforms()
	{
		return platforms;
	}
	
	public Vector2[] getSpawnPoints()
	{
		return spawnPoints;
	}
	
	public Image getBackground() {
		return background;
	}
	
	public void updateCamera(int width, int height) {
		stage.getViewport().update(width, height);
	}
	
	public void render() 
	{
		update();
		//stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.act();
		stage.draw();
		if(showBoxes) 
		{	
			shapeRenderer.begin(ShapeType.Line);
			for(Rectangle r : allRectangles)
			{
				shapeRenderer.rect(r.x, r.y, r.getWidth(), r.getHeight());
			}
			shapeRenderer.end();
		}
	}
	
	public void update() {
		if(Gdx.input.isKeyPressed(48) && !keyHolding) {		//If "T" is pressed, testing mode is entered.
			showBoxes = !showBoxes;
			keyHolding = true;
		}
		else if(!Gdx.input.isKeyPressed(48))
			keyHolding = false;
	}
}
