package com.mygdx.game.stages;

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
	private Rectangle[] rectangles;
	private ShapeRenderer shapeRenderer;
	
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
	
	public void create(Image background, Rectangle[] rectangles, SpriteBatch batch) {
		this.background = background;
		this.rectangles = rectangles;
		stage.addActor(background);
		shapeRenderer.setColor(1,1,0,1);
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
	
	public void render() {
		update();
		//stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.act();
		stage.draw();
		if(showBoxes) {			//Draw all the collision boxes
			shapeRenderer.begin(ShapeType.Line);
			for(int i = 0; i < rectangles.length; i++) {
				shapeRenderer.rect(rectangles[i].getX(), rectangles[i].getY(), rectangles[i].getWidth(), rectangles[i].getHeight());
			}
			shapeRenderer.end();
		}
	}
	
	public Rectangle[] getCollisionBoxes() {
		return rectangles;
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
