package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.MyGdxGame;

public class HomeScreen extends Screen{
	private MyGdxGame game;
	private Screen next;
	
	//Objects necessary for the stage 
	private Stage stage;
    private TextButton button;
    private TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private float opacity;
    private boolean decreaseOpacity;
	
	public HomeScreen(MyGdxGame game, Screen next) {
		stage = new Stage();
		opacity = 0F;
		decreaseOpacity = true;
		this.next = next;
		this.game = game;
		create();
        stage.addActor(button);
	}
	//Creates the necessary objects for the stage (skin, images, font, textButtonStyle, buttons and textureAtlas)
	public void create() {
		skin = new Skin();
		buttonAtlas = new TextureAtlas("homeScreen/button/logo.pack");
		skin.addRegions(buttonAtlas);
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.setScale(4);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("background2");
        button = new TextButton("CLICK TO PLAY", textButtonStyle);
        button.setHeight(600);
        button.setWidth(800);
        button.setPosition(MyGdxGame.GAME_WIDTH/2 - (button.getWidth()/2), MyGdxGame.GAME_HEIGHT/2 - (button.getHeight()/2));
        
        //Creates an event listener for the button which makes screen point to the playScreen
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	game.changeScreen(next);
            	return true;
            }
        });
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public Stage getStage () {
		return stage;
	}
	//Updates the location of the background - will add event listeners for the button(s) here
	public void update() {
		if(decreaseOpacity) {
			opacity -= 0.01;
		}
		else {
			opacity += 0.01;
		}
		if(opacity >= 1) {
			decreaseOpacity = true;
		}
		if(opacity <= 0) {
			decreaseOpacity = false;
		}
		font.setColor(0,0,0,opacity);
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
