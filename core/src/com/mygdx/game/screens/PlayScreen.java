package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.MyGdxGame;
import java.util.Random;

public class PlayScreen extends Screen{
	private Texture picture;
	private Image background;
	private MyGdxGame game;
	private Screen onePlayer;
	private Screen twoPlayer;
	private Screen back;
	private Random rand;
	
	//Objects necessary for the stage 
	private Stage stage;
	private BitmapFont font;
	
    private TextButton button1;
    private TextButton button2;
    private TextButton buttonOptions;
    private TextButton buttonItem;
    private TextButton button3;
    private TextButtonStyle textButtonStyle1;
    private TextButtonStyle textButtonStyle3;
    private Skin skin1;
    private Skin skin3;
    private TextureAtlas buttonAtlas1;
    private TextureAtlas buttonAtlas3;
	
	public PlayScreen(MyGdxGame game) {
		rand = new Random();
		this.game = game;
		stage = new Stage();
		create();
        stage.addActor(background);
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(buttonOptions);
        stage.addActor(buttonItem);
	}
	
	public void addScreens(Screen onePlayer, Screen twoPlayer, Screen back) {
		this.onePlayer = onePlayer;
		this.twoPlayer = twoPlayer;
		this.back = back;
	}
	
	public void create() {
		skin1 = new Skin();
		skin3 = new Skin();
		buttonAtlas1 = new TextureAtlas("playScreen/button/button.pack");
		buttonAtlas3 = new TextureAtlas("back/goBack.pack");
		picture = new Texture("playScreen/background.jpg");
		background = new Image(picture);
		background.setWidth(game.GAME_WIDTH);
		background.setHeight(game.GAME_HEIGHT);
		skin1.addRegions(buttonAtlas1);
		skin3.addRegions(buttonAtlas3);
        font = new BitmapFont();
        textButtonStyle1 = new TextButtonStyle();
        textButtonStyle3 = new TextButtonStyle();
        textButtonStyle1.font = font;
        textButtonStyle3.font = font;
        textButtonStyle1.up = skin1.getDrawable("ButtonUp");
        textButtonStyle1.over = skin1.getDrawable("ButtonDown");
        textButtonStyle1.checkedOver = skin1.getDrawable("ButtonDown");
        textButtonStyle3.up = skin3.getDrawable("backButton");
        button1 = new TextButton("Single player", textButtonStyle1);
        button2 = new TextButton("Multiplayer", textButtonStyle1);
        buttonOptions = new TextButton("Options", textButtonStyle1);
        buttonItem = new TextButton("Items", textButtonStyle1);
        button3 = new TextButton("", textButtonStyle3);
        button1.setHeight(75);
        button1.setWidth(250);
        button1.setPosition(90,game.GAME_HEIGHT/2+250);
        button2.setHeight(75);
        button2.setWidth(250);
        button2.setPosition(90 + (game.GAME_WIDTH-200)/4, game.GAME_HEIGHT/2+250);
        buttonOptions.setHeight(75);
        buttonOptions.setWidth(250);
        buttonOptions.setPosition(90 + (game.GAME_WIDTH-200)/2, game.GAME_HEIGHT/2+250);
        buttonItem.setHeight(75);
        buttonItem.setWidth(250);
        buttonItem.setPosition(90 + 3*(game.GAME_WIDTH-200)/4, game.GAME_HEIGHT/2+250);
        button3.setHeight(100);
        button3.setWidth(100);
        button3.setPosition(50,50);
        
        button1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	game.changeScreen(onePlayer);
            	return true;
            }
        });
        
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	game.changeScreen(onePlayer);
            	return true;
            }
        });
        buttonOptions.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	buttonOptions.setHeight(buttonOptions.getHeight()/1.1F);
            	buttonOptions.setWidth(buttonOptions.getWidth()/1.1F);
            	return true;
            }
        });
        buttonItem.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	buttonItem.setPosition(rand.nextFloat()*1200, rand.nextFloat()*700);
            	return true;
            }
        });
        
        button3.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	game.changeScreen(back);
            	return true;
            }
        });
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public Stage getStage() {
		return stage;
	}

	public void render() {
		update();
		stage.act();
		stage.draw();
	}

	public void update() {
			
	}
	
	public void dispose() {
		stage.dispose();
		skin1.dispose();
		buttonAtlas1.dispose();
		font.dispose();
		picture.dispose();
	}
	
}
