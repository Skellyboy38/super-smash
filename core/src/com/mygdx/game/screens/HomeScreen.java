package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private Texture picture;
	private Image background;
	private SpriteBatch batch;
	long a = 0;
	long b = 0;
	long c = 0;
	
	public HomeScreen(MyGdxGame game, SpriteBatch batch) {
		stage = new Stage();
		opacity = 1F;
		this.game = game;
		this.batch = batch;
		create();
		stage.addActor(background);
        stage.addActor(button);
	}
	
	public void addScreens(Screen next) {
		this.next = next;
	}
	
	//Creates the necessary objects for the stage (skin, images, font, textButtonStyle, buttons and textureAtlas)
	public void create() {
		picture = new Texture("homeScreen/background.png");
		background = new Image(picture);
		background.setHeight(MyGdxGame.GAME_HEIGHT);
		background.setWidth(MyGdxGame.GAME_WIDTH);
		skin = new Skin();
		buttonAtlas = new TextureAtlas("homeScreen/button/button.pack");
		skin.addRegions(buttonAtlas);
        font = new BitmapFont(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"), false);
        font.setScale(3);
        font.setColor(255,255,255,opacity);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("buttonGood");
        button = new TextButton("", textButtonStyle);
        button.setHeight(100);
        button.setWidth(500);
        button.setPosition(MyGdxGame.GAME_WIDTH/2 - (button.getWidth()/2), MyGdxGame.GAME_HEIGHT/2 - 220);
        
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
		a++;
		b=b+2;
		c=c+3;
		font.setColor(a,b,c,1);
	}
	
	public void render() {
		update();
		stage.act();
		stage.draw();
		batch.begin();
		font.draw(batch, "CLICK TO PLAY", MyGdxGame.GAME_WIDTH/2 - 170, MyGdxGame.GAME_HEIGHT/2 - 150);
		batch.end();
	}
	
	//Called when the home screen is no longer needed (disposes of all the instance variables)
	public void dispose() {
		stage.dispose();
		skin.dispose();
		buttonAtlas.dispose();
		font.dispose();
	}
}
