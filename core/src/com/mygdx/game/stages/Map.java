package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Map {
	private Texture stage;
	private Texture background;
	private Rectangle[] rectangles;
	
	protected float posX;
	protected float posY;
	
	public Map() {
		
	}
	
	public void create(Texture stage, Texture background, Rectangle[] rectangles) {
		this.stage = stage;
		this.background = background;
		this.rectangles = rectangles;
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
		
	}
	
	public void update() {
		
	}
}
