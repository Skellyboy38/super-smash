package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Lonrk extends Fighter{
	
	public static final int NUMBER_ANIMATIONS = 6;
	
	private Animation[] animations;
	private Animation walkLeftAnimation, walkRightAnimation, standLeftAnimation, standRightAnimation, jumpLeftAnimation, jumpRightAnimation;
	private Texture walkLeftSheet, walkRightSheet, standLeftSheet, standRightSheet, jumpLeftSheet, jumpRightSheet;
	private TextureRegion[] walkLeftFrames, walkRightFrames, standLeftFrames, standRightFrames, jumpLeftFrames, jumpRightFrames;
	private Rectangle collisionBox;
	
	public Lonrk(SpriteBatch batch, float posX, float posY) {
		super(batch, posX, posY);
		animations = new Animation[NUMBER_ANIMATIONS];
		collisionBox = new Rectangle();
		
		walkLeftSheet = new Texture("characters/lonrk/walking_left/lonrk_walking_left_animation.png");
		TextureRegion[][] temp1 = TextureRegion.split(walkLeftSheet, walkLeftSheet.getWidth()/8, walkLeftSheet.getHeight());
		walkLeftFrames = new TextureRegion[8];
		for(int i = 0; i < 8; i++) {
			walkLeftFrames[i] = temp1[0][i];
		}
		walkLeftAnimation = new Animation(0.075f, walkLeftFrames);
		//==================================================================================================================================
		walkRightSheet = new Texture("characters/lonrk/walking_right/lonrk_walking_left_animation.png");
		TextureRegion[][] temp2 = TextureRegion.split(walkRightSheet, walkRightSheet.getWidth()/8, walkRightSheet.getHeight());
		walkRightFrames = new TextureRegion[8];
		for(int i = 0; i < 8; i++) {
			walkRightFrames[i] = temp2[0][i];
		}
		walkRightAnimation = new Animation(0.075f, walkRightFrames);
		//==================================================================================================================================
		standLeftSheet = new Texture("characters/lonrk/standing_left/lonrk_standing.png");
		TextureRegion[][] temp3 = TextureRegion.split(standLeftSheet, standLeftSheet.getWidth(), standLeftSheet.getHeight());
		standLeftFrames = new TextureRegion[1];
		standLeftFrames[0] = temp3[0][0];
		standLeftAnimation = new Animation(1f, standLeftFrames);
		//==================================================================================================================================
		standRightSheet = new Texture("characters/lonrk/standing_right/lonrk_standing.png");
		TextureRegion[][] temp4 = TextureRegion.split(standRightSheet, standRightSheet.getWidth(), standRightSheet.getHeight());
		standRightFrames = new TextureRegion[1];
		standRightFrames[0] = temp4[0][0];
		standRightAnimation = new Animation(1f, standRightFrames);
		//==================================================================================================================================
		jumpLeftSheet = new Texture("characters/lonrk/jumping_left/lonrk_jumping_animation.png");
		TextureRegion[][] temp5 = TextureRegion.split(jumpLeftSheet, jumpLeftSheet.getWidth(), jumpLeftSheet.getHeight());
		jumpLeftFrames = new TextureRegion[1];
		for(int i = 0; i < 1; i++) {
			jumpLeftFrames[i] = temp5[0][i];
		}
		jumpLeftAnimation = new Animation(0.05f, jumpLeftFrames);
		//==================================================================================================================================
		jumpRightSheet = new Texture("characters/lonrk/jumping_right/lonrk_jumping_animation.png");
		TextureRegion[][] temp6 = TextureRegion.split(jumpRightSheet, jumpRightSheet.getWidth(), jumpRightSheet.getHeight());
		jumpRightFrames = new TextureRegion[1];
		for(int i = 0; i < 1; i++) {
			jumpRightFrames[i] = temp6[0][i];
		}
		jumpRightAnimation = new Animation(0.05f, jumpRightFrames);
		
		collisionBox.setHeight(ANIMATION_HEIGHT);
		collisionBox.setWidth(ANIMATION_WIDTH*0.16667f);
		
		animations[0] = standLeftAnimation;
		animations[1] = standRightAnimation;
		animations[2] = walkLeftAnimation;
		animations[3] = walkRightAnimation;
		animations[4] = jumpLeftAnimation;
		animations[5] = jumpRightAnimation;
		
		addAnimations(animations);
		addCollisionBoxes(collisionBox);
	}
}
