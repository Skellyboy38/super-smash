package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.characters.Fighter.State;

public interface FighterInterface {
	
	public void setSpawnPoint(Vector2 spawn);
	
	public void setControls(int[] controls);
	
	public void addCollisionBoxes(Rectangle[] boxes);
	
	public void addAnimations(Animation[] animations);
	
	public void render();
	
	public void update();
	
	
	public float getHeight();
	
	public float getWidth();
	
	public State getState();
	
	public void resetFallingSpeed();
	
	public void resetCounter();
	
	public Vector2 getPosition();
	
	
	
	public Rectangle[] getCollisionBoxes();
	
	
	public void elevateCollisionBox();
	
	public void dropCollisionBox();
	
	public void updateCollisionBoxes();
	
	public boolean isHanging();
	
	public void hangLeft();
	
	public void hangRight();
	
	public void setPosition(float x, float y);
	
	public void setPosition(Vector2 position);
	
	public void setPositionX(float x);
	
	public void capFromBottom(float height);
	
	public void capFromTop(float height);
	
	public void capFromLeft(float length);
	
	public void capFromRight(float length);
	
	public void changeState(State toChange);
	
	public boolean getDirection();
	
	public void capVerticalPosition();
	
	public void uncapVerticalPosition();
	
	
	
	public void checkRunningLeft();
	
	public void checkRunningRight();
	
	public void resetRunningStats();
	
	public void checkKeyHoldings();
	
}
