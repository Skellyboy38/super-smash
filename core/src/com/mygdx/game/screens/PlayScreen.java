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
	
	//Objects necessary for the stage 
	private Stage stage;
	private BitmapFont font;
	
    private TextButton button1;
    private TextButton button2;
    private TextButtonStyle textButtonStyle1;
    private TextButtonStyle textButtonStyle2;
    private Skin skin1;
    private Skin skin2;
    private TextureAtlas buttonAtlas1;
    private TextureAtlas buttonAtlas2;
	
	public PlayScreen(MyGdxGame game, Screen onePlayer, Screen twoPlayer) {
		this.onePlayer = onePlayer;
		this.twoPlayer = twoPlayer;
		this.game = game;
		moveRight = true;
		locationX = 0F;
		stage = new Stage();
		create();
        stage.addActor(background);
        stage.addActor(button1);
        stage.addActor(button2);
	}
	
	public void create() {
		skin1 = new Skin();
		skin2 = new Skin();
		buttonAtlas1 = new TextureAtlas("playScreen/player1/button.pack");
		buttonAtlas2 = new TextureAtlas("playScreen/player2/button.pack");
		picture = new Texture("playScreen/background.jpg");
		background = new Image(picture);
		skin1.addRegions(buttonAtlas1);
		skin2.addRegions(buttonAtlas2);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        textButtonStyle1 = new TextButtonStyle();
        textButtonStyle2 = new TextButtonStyle();
        textButtonStyle1.font = font;
        textButtonStyle2.font = font;
        textButtonStyle1.up = skin1.getDrawable("player1nine");
        textButtonStyle2.up = skin2.getDrawable("player2nine");
        button1 = new TextButton("", textButtonStyle1);
        button2 = new TextButton("", textButtonStyle2);
        button1.setPosition(100, MyGdxGame.GAME_HEIGHT - 400);
        button1.setHeight(200);
        button1.setWidth(200);
        button2.setPosition(MyGdxGame.GAME_WIDTH - 400, MyGdxGame.GAME_HEIGHT - 400);
        button2.setHeight(200);
        button2.setWidth(200);
        
        button1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	button1.setHeight(300);
            	button1.setWidth(300);
            	return true;
            }
        });
        
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	game.changeScreen(twoPlayer);
            	return true;
            }
        });
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
