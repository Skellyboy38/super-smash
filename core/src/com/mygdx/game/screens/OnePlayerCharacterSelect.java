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

public class OnePlayerCharacterSelect extends Screen{
	private Texture picture;
	private Image background;
	private MyGdxGame game;
	private BitmapFont font;

	private Stage stage;
	private TextButton button1;
	private TextButton button2;
	private TextButton button3;
	private TextButton button4;
	private TextButton button5;
	private TextButton button6;
	private TextButtonStyle textButtonStyle1;
	private TextButtonStyle textButtonStyle2;
	private TextButtonStyle textButtonStyle3;
	private TextButtonStyle textButtonStyle4;
	private TextButtonStyle textButtonStyle5;
	private TextButtonStyle textButtonStyle6;
	private Skin skin1;
	private Skin skin2;
	private Skin skin3;
	private Skin skin4;
	private Skin skin5;
	private Skin skin6;
	private TextureAtlas buttonAtlas1;
	private TextureAtlas buttonAtlas2;
	private TextureAtlas buttonAtlas3;
	private TextureAtlas buttonAtlas4;
	private TextureAtlas buttonAtlas5;
	private TextureAtlas buttonAtlas6;
	
	private float buttonHeight;
	private float buttonWidth;

	public OnePlayerCharacterSelect(MyGdxGame game) {
		stage = new Stage();
		this.game = game;
		buttonHeight = MyGdxGame.GAME_HEIGHT/3;
		buttonWidth = MyGdxGame.GAME_WIDTH/6 - 140/6;
		create();
		stage.addActor(background);
		stage.addActor(button1);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);
		stage.addActor(button5);
		stage.addActor(button6);
	}
	
	public void addScreens() {
		
	}

	public void create() {
		picture = new Texture("CharacterSelectScreen/background.png");
		background = new Image(picture);
		
		createC1();
		createC2();
		createC3();
		createC4();
		createC5();
		createC6();
	}

	public void createC1() {
		skin1 = new Skin();
		buttonAtlas1 = new TextureAtlas("CharacterSelectScreen/c1/c1.pack");
		
		skin1.addRegions(buttonAtlas1);
        font = new BitmapFont();
        textButtonStyle1 = new TextButtonStyle();
        textButtonStyle1.font = font;
        textButtonStyle1.up = skin1.getDrawable("gameandwatch01");
        textButtonStyle1.down = skin1.getDrawable("gameandwatch02");
        button1 = new TextButton("", textButtonStyle1);
        
        button1.setHeight(buttonHeight);
        button1.setWidth(buttonWidth);
        button1.setPosition(20, MyGdxGame.GAME_HEIGHT/2 - button1.getHeight()/2);
        
        button1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//Add event later
            	return true;
            }
        });
	}

	public void createC2() {
		skin2 = new Skin();
		buttonAtlas2 = new TextureAtlas("CharacterSelectScreen/c2/c2.pack");
		
		skin2.addRegions(buttonAtlas2);
        textButtonStyle2 = new TextButtonStyle();
        textButtonStyle2.font = font;
        textButtonStyle2.up = skin2.getDrawable("lucas01");
        textButtonStyle2.down = skin2.getDrawable("lucas02");
        button2 = new TextButton("", textButtonStyle2);
        
        button2.setHeight(buttonHeight);
        button2.setWidth(buttonWidth);
        button2.setPosition(40 + button1.getWidth(), MyGdxGame.GAME_HEIGHT/2 - button1.getHeight()/2);
        
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//Add event later
            	return true;
            }
        });
	}

	public void createC3() {
		skin3 = new Skin();
		buttonAtlas3 = new TextureAtlas("CharacterSelectScreen/c3/c3.pack");
		
		skin3.addRegions(buttonAtlas3);
        textButtonStyle3 = new TextButtonStyle();
        textButtonStyle3.font = font;
        textButtonStyle3.up = skin3.getDrawable("mario01");
        textButtonStyle3.down = skin3.getDrawable("mario02");
        button3 = new TextButton("", textButtonStyle3);
        
        button3.setHeight(buttonHeight);
        button3.setWidth(buttonWidth);
        button3.setPosition(60 + button2.getWidth() + button1.getWidth(), MyGdxGame.GAME_HEIGHT/2 - button1.getHeight()/2);
        
        button3.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//Add event later
            	return true;
            }
        });
	}

	public void createC4() {
		skin4 = new Skin();
		buttonAtlas4 = new TextureAtlas("CharacterSelectScreen/c4/c4.pack");
		
		skin4.addRegions(buttonAtlas4);
        textButtonStyle4 = new TextButtonStyle();
        textButtonStyle4.font = font;
        textButtonStyle4.up = skin4.getDrawable("pacman01");
        textButtonStyle4.down = skin4.getDrawable("pacman02");
        button4 = new TextButton("", textButtonStyle4);
        
        button4.setHeight(buttonHeight);
        button4.setWidth(buttonWidth);
        button4.setPosition(80 + button3.getWidth() + button2.getWidth() + button1.getWidth(), MyGdxGame.GAME_HEIGHT/2 - button3.getHeight()/2);
        
        button4.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//Add event later
            	return true;
            }
        });
	}

	public void createC5() {
		skin5 = new Skin();
		buttonAtlas5 = new TextureAtlas("CharacterSelectScreen/c5/c5.pack");
		
		skin5.addRegions(buttonAtlas5);
        textButtonStyle5 = new TextButtonStyle();
        textButtonStyle5.font = font;
        textButtonStyle5.up = skin5.getDrawable("sonic01");
        textButtonStyle5.down = skin5.getDrawable("sonic02");
        button5 = new TextButton("", textButtonStyle5);
        
        button5.setHeight(buttonHeight);
        button5.setWidth(buttonWidth);
        button5.setPosition(100 + button4.getWidth() + button3.getWidth() + button2.getWidth() + button1.getWidth(), MyGdxGame.GAME_HEIGHT/2 - button3.getHeight()/2);
        
        button5.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//Add event later
            	return true;
            }
        });
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	public void createC6() {
		skin6 = new Skin();
		buttonAtlas6 = new TextureAtlas("CharacterSelectScreen/c6/c6.pack");
		
		skin6.addRegions(buttonAtlas6);
        textButtonStyle6 = new TextButtonStyle();
        textButtonStyle6.font = font;
        textButtonStyle6.up = skin6.getDrawable("shulk01");
        textButtonStyle6.down = skin6.getDrawable("shulk02");
        button6 = new TextButton("", textButtonStyle6);
        
        button6.setHeight(buttonHeight);
        button6.setWidth(buttonWidth);
        button6.setPosition(120 + button5.getWidth() + button4.getWidth() + button3.getWidth() + button2.getWidth() + button1.getWidth(), MyGdxGame.GAME_HEIGHT/2 - button3.getHeight()/2);
        
        button6.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//Add event later
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
		
	}
}
