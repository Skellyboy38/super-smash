package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Map {
	private Texture stage;
	private Texture background;
	private Rectangle[] rectangles;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	protected float posX;
	protected float posY;
	
	boolean showBoxes;
	boolean keyHolding;
	
	public Map() {
		showBoxes = false;
		keyHolding = false;
		shapeRenderer = new ShapeRenderer();
	}
	
	public void create(Texture stage, Texture background, Rectangle[] rectangles, SpriteBatch batch) {
		this.stage = stage;
		this.background = background;
		this.rectangles = rectangles;
		this.batch = batch;
		shapeRenderer.setColor(1,1,0,1);
	}
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public Texture getBackground() {
		return background;
	}
	
	public Texture getStage() {
		return stage;
	}
	
	public void render() {
		update();
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(stage, posX, posY);
		if(showBoxes) {			//Draw all the collision boxes
			shapeRenderer.begin(ShapeType.Line);
			for(int i = 0; i < rectangles.length; i++) {
				shapeRenderer.rect(rectangles[i].getX(), rectangles[i].getY(), rectangles[i].getWidth(), rectangles[i].getHeight());
			}
			shapeRenderer.end();
		}
		batch.end();
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
