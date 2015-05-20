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
	private MyGdxGame game;		//The game object that every screen needs a reference to in order to change screen.
	private Screen next;		//The play screen to jump to.
	private Stage stage;		//Every screen needs its own stage in which to add buttons, images, etc (each stage is different from screen to screen).
	
    private TextButton button;		//The TextButton, TextButtonStyle, Skin and TextureAtlas are necessary components to create a stylish button.
    private TextButtonStyle textButtonStyle;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    
    private BitmapFont font;		//The font that is used to write to the screen or to label a button.
    
    private Texture picture;		//Texture and Image are used for the background of the stage.
	private Image background;
	private SpriteBatch batch;
	long r = 0;			//Values used to alter the color of the text for the play button.
	long g = 0;
	long b = 0;
	
	public HomeScreen(MyGdxGame game, SpriteBatch batch) {
		super(game);	
		stage = new Stage();
		this.game = game;
		this.batch = batch;
		create();
		
		stage.addActor(background);	//Add the components to the stage (background and play button).
        stage.addActor(button);
	}
	
	public void addScreens(Screen next) {
		this.next = next;
	}
	
	public void create() {
		picture = new Texture("homeScreen/background.png");
		background = new Image(picture);
		background.setHeight(game.GAME_HEIGHT);
		background.setWidth(game.GAME_WIDTH);
		
		skin = new Skin();
		buttonAtlas = new TextureAtlas("homeScreen/button/button.pack");
		skin.addRegions(buttonAtlas);
		
        font = new BitmapFont();
        font.setScale(3);			//set scale amplifies the size of the text.
        font.setColor(r,g,b,1);		//The color is represented in terms of RED/GREEN/BLUE, and the 1 is the opacity which ranges from 0 to 1.
        
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("buttonGood");
        button = new TextButton("", textButtonStyle);
        button.setHeight(100);
        button.setWidth(500);
        button.setPosition(game.GAME_WIDTH/2 - (button.getWidth()/2), game.GAME_HEIGHT/4.5F);
        
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
	
	public void update() {		//The only change that happens on the home screen is the changing of colors for the text.
		if(r < 100000 && g < 100000 && b < 100000) {	//Sets a limit to the values (without the cap it could throw an error eventually).
			r++;
			g=g+2;
			b=b+3;
			font.setColor(r,g,b,1);
		}
	}
	
	public void render() {
		super.render();		//Super.render() allows the game to screen for any buttons that are pressed (mainly the escape key to exit full screen).
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		update();
		stage.act();
		stage.draw();
		batch.begin();
		font.draw(batch, "CLICK TO PLAY", game.GAME_WIDTH/2 - 170, game.GAME_HEIGHT/4.5F + 70);
		batch.end();
	}
	
	public void dispose() {
		stage.dispose();
		skin.dispose();
		buttonAtlas.dispose();
		font.dispose();
	}
}
