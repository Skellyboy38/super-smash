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

public class PlayScreen extends Screen{
	private Texture picture;
	private Image background;
	private float locationX;
	private boolean moveRight;
	private MyGdxGame game;
	private Screen onePlayer;
	private Screen twoPlayer;
	private Screen back;
	
	//Objects necessary for the stage 
	private Stage stage;
	private BitmapFont font;
	
    private TextButton button1;
    private TextButton button2;
    private TextButton button3;
    private TextButtonStyle textButtonStyle1;
    private TextButtonStyle textButtonStyle2;
    private TextButtonStyle textButtonStyle3;
    private Skin skin1;
    private Skin skin2;
    private Skin skin3;
    private TextureAtlas buttonAtlas1;
    private TextureAtlas buttonAtlas2;
    private TextureAtlas buttonAtlas3;
	
	public PlayScreen(MyGdxGame game) {
		this.game = game;
		moveRight = true;
		locationX = 0F;
		stage = new Stage();
		create();
        stage.addActor(background);
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
	}
	
	public void addScreens(Screen onePlayer, Screen twoPlayer, Screen back) {
		this.onePlayer = onePlayer;
		this.twoPlayer = twoPlayer;
		this.back = back;
	}
	
	public void create() {
		skin1 = new Skin();
		skin2 = new Skin();
		skin3 = new Skin();
		buttonAtlas1 = new TextureAtlas("playScreen/player1/button.pack");
		buttonAtlas2 = new TextureAtlas("playScreen/player2/button.pack");
		buttonAtlas3 = new TextureAtlas("back/goBack.pack");
		picture = new Texture("playScreen/background.jpg");
		background = new Image(picture);
		skin1.addRegions(buttonAtlas1);
		skin2.addRegions(buttonAtlas2);
		skin3.addRegions(buttonAtlas3);
        font = new BitmapFont();
        textButtonStyle1 = new TextButtonStyle();
        textButtonStyle2 = new TextButtonStyle();
        textButtonStyle3 = new TextButtonStyle();
        textButtonStyle1.font = font;
        textButtonStyle2.font = font;
        textButtonStyle3.font = font;
        textButtonStyle1.up = skin1.getDrawable("player1nine");
        textButtonStyle2.up = skin2.getDrawable("player2nine");
        textButtonStyle3.up = skin3.getDrawable("backButton");
        button1 = new TextButton("", textButtonStyle1);
        button2 = new TextButton("", textButtonStyle2);
        button3 = new TextButton("", textButtonStyle3);
        button1.setHeight(200);
        button1.setWidth(200);
        button1.setPosition(100, MyGdxGame.GAME_HEIGHT - (button1.getHeight()+100));
        button2.setHeight(200);
        button2.setWidth(200);
        button2.setPosition(MyGdxGame.GAME_WIDTH - (button2.getWidth()+100), MyGdxGame.GAME_HEIGHT - (button2.getHeight()+100));
        button3.setHeight(200);
        button3.setWidth(200);
        button3.setPosition(100,100);
        
        button1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	game.changeScreen(onePlayer);
            	return true;
            }
        });
        
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	button2.setHeight(button2.getHeight()+10);
            	button2.setWidth(button2.getWidth()+10);
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
	
	public void dispose() {
		stage.dispose();
		skin1.dispose();
		skin2.dispose();
		buttonAtlas1.dispose();
		buttonAtlas2.dispose();
		font.dispose();
		picture.dispose();
	}
	
}
