package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Map {
	private Texture stage;
	private Texture background;
	private Rectangle[] rectangles;
	private SpriteBatch batch;
	
	protected float posX;
	protected float posY;
	
	public Map() {
		
	}
	
	public void create(Texture stage, Texture background, Rectangle[] rectangles, SpriteBatch batch) {
		this.stage = stage;
		this.background = background;
		this.rectangles = rectangles;
		this.batch = batch;
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
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(stage, posX, posY);
		batch.end();
	}
	
	public void update() {
		
	}
}
