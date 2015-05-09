package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.MyGdxGame;

public class HomeScreen extends Screen{
	private Texture picture;
	private Image background;
	private float locationX;
	private boolean moveRight;
	
	//Objects necessary for the stage 
	private Stage stage;
    private TextButton button;
    private TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
	
	public HomeScreen(Stage stage) {
		this.stage = stage;
		locationX = 0;
		create();
        stage.addActor(background);
        stage.addActor(button);
	}
	//Creates the necessary objects for the stage (skin, images, font, textButtonStyle, buttons and textureAtlas)
	public void create() {
		skin = new Skin();
		buttonAtlas = new TextureAtlas("buttons/buttons.pack");
		picture = new Texture("background.jpg");
		background = new Image(picture);
		skin.addRegions(buttonAtlas);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button");
        button = new TextButton("Push Me", textButtonStyle);
        button.setPosition(100, 100);
        button.setHeight(300);
        button.setWidth(300);
	}
	//Updates the location of the background - will add event listeners for the button(s) here
	public void update() {
		if(moveRight)
			locationX += 1;
		if(!moveRight)
			locationX -= 1;
		if(locationX >= MyGdxGame.GAME_WIDTH - background.getWidth())
			moveRight = false;
		if(locationX <= 0)
			moveRight = true;
		background.setPosition(locationX, 0);
	}
	
	public void render() {
		update();
		stage.act();
		stage.draw();
		
	}
	//Called when the home screen is no longer needed (disposes of all the instance variables)
	public void dispose() {
		stage.dispose();
		skin.dispose();
		buttonAtlas.dispose();
		font.dispose();
	}
}
