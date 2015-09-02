package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Map {
	private Stage stage;
	private Image background;
	private Rectangle[] rectangles;
	private ShapeRenderer shapeRenderer;
	
	protected float posX;
	protected float posY;
	
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
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public Image getBackground() {
		return background;
	}
	
	public void render() {
		update();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
