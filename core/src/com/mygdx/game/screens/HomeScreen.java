package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.MyGdxGame;

public class HomeScreen extends Screen{
	Button play;
	Button settings;
	Button quit;
	SpriteBatch batch;
	Texture picture;
	Image background;
	float locationX;
	boolean moveRight;
	
	Stage stage;
    TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
	
	public HomeScreen(Stage stage) {
		this.stage = stage;
		skin = new Skin();
		locationX = 0;
		
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
        stage.addActor(background);
        stage.addActor(button);
	}
	
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
}
