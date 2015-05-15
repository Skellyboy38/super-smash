package com.mygdx.game.screens;

import java.util.Random;
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

public class OptionsScreen extends Screen{
	private Texture picture;
	private Image background;
	private MyGdxGame game;
	private Screen onePlayer;
	private Screen back;
	private Random rand;		
	
	private Stage stage;
	private BitmapFont font;
	
    private TextButton buttonFX;
    private TextButton buttonMusic;
    private TextButton buttonData;
    private TextButton buttonBack;
    private TextButtonStyle textButtonStyle1;
    private TextButtonStyle textButtonStyle3;
    private Skin skin1;
    private Skin skin3;
    private TextureAtlas buttonAtlas1;
    private TextureAtlas buttonAtlas3;
	
	public OptionsScreen(MyGdxGame game) {
		super(game);
		rand = new Random();
		this.game = game;
		stage = new Stage();
		create();
        stage.addActor(background);
        stage.addActor(buttonFX);
        stage.addActor(buttonMusic);
        stage.addActor(buttonData);
        stage.addActor(buttonBack);
	}
	
	public void addScreens(Screen back) {
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
        buttonFX = new TextButton("Sound Effects Volume", textButtonStyle1);
        buttonMusic = new TextButton("Music Volume", textButtonStyle1);;
        buttonData = new TextButton("Manage Data", textButtonStyle1);
        buttonBack = new TextButton("", textButtonStyle3);
        buttonFX.setHeight(75);
        buttonFX.setWidth(250);
        buttonFX.setPosition(90,game.GAME_HEIGHT/2+250);
        buttonMusic.setHeight(75);
        buttonMusic.setWidth(250);
        buttonMusic.setPosition(90 + (game.GAME_WIDTH-200)/4, game.GAME_HEIGHT/2+250);
        buttonData.setHeight(75);
        buttonData.setWidth(250);
        buttonData.setPosition(90 + 2*(game.GAME_WIDTH-200)/4, game.GAME_HEIGHT/2+250);
        buttonBack.setHeight(100);
        buttonBack.setWidth(100);
        buttonBack.setPosition(50,50);
        
        buttonFX.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	buttonFX.setPosition(rand.nextFloat()*1200, rand.nextFloat()*700);
            	return true;
            }
        });
        
        buttonMusic.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	buttonMusic.setPosition(rand.nextFloat()*1200, rand.nextFloat()*700);
            	return true;
            }
        });

        
        buttonData.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	buttonData.setPosition(rand.nextFloat()*1200, rand.nextFloat()*700);
            	return true;
            }
        });
        
        buttonBack.addListener(new InputListener() {
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
		super.render();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
