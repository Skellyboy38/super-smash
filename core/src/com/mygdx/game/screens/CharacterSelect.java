package com.mygdx.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.Fighter;
import com.mygdx.game.characters.Lonrk;

public class CharacterSelect extends Screen{
	public static final int NUM_CHARACTERS = 20;		//The number of characters to choose from.
	public static final float ICON_WIDTH = MyGdxGame.GAME_WIDTH/12.5F;
	public static final float ICON_HEIGHT = MyGdxGame.GAME_HEIGHT/9F;
	
	private Texture picture;
	private Image background;
	private MyGdxGame game;
	
	private Texture player1, player2, player3, player4;
	private Image player1_image, player2_image, player3_image, player4_image;
	private int p1Chosen, p2Chosen, p3Chosen, p4Chosen;
	
	private Texture[] iconTextures;
	private Image[] iconImages;
	private Texture[] characterTextures;
	private Image[] characterImages;
	private Rectangle[] iconCollisionBoxes;
	private Rectangle[] coinCollisionBoxes;
	private int[] characterChoices;
	private int numberCharacters;
	
	private Texture startMessageTexture;
	private Image startMessageImage;
	
	private Image[] playerOneChoices;
	private Image[] playerTwoChoices;
	private Image[] playerThreeChoices;
	private Image[] playerFourChoices;
	
	private boolean showBoxes;
	private boolean keyHolding;
	private ShapeRenderer shapeRenderer;

	private BitmapFont font;
	private Screen back;			//The play screen.
	private StageSelectScreen next;
	private SpriteBatch batch;
	private Array<Actor> addRemoveActors;
	private ArrayList<Fighter> characters;
	private Stage stage;
	
	private Group first;
	private Group second;
	private Group third;
	private Group fourth; 	
	
	private TextButton add1;		//Buttons to add a character
	private TextButton add2;
	private TextButton add3;
	private TextButton add4;
	
	private TextButton remove1;		//Buttons to remove a character
	private TextButton remove2;
	private TextButton remove3;
	private TextButton remove4;
	
	private TextButton token1;		//Moveable tokens
	private TextButton token2;
	private TextButton token3;
	private TextButton token4;
	
	private TextButton backButton;
	
	private boolean[] characterExists;	//Keeps track of which players are playing (1 to 4).
	private boolean[] tokenMovable;
	public final String START_MESSAGE = "Press Enter to start"; //When all characters are chosen, this message appears.
	private boolean canStart;	//Determines whether or not the game can begin.
	
	private TextButtonStyle token1Style;
	private TextButtonStyle token2Style;
	private TextButtonStyle token3Style;
	private TextButtonStyle token4Style;
	private TextButtonStyle textButtonStyleBack;
	private TextButtonStyle addStyle;
	private TextButtonStyle removeStyle;
	
	private Skin token1Skin;
	private Skin token2Skin;
	private Skin token3Skin;
	private Skin token4Skin;
	private Skin addSkin;
	private Skin removeSkin;
	private Skin skinBack;
	
	private TextureAtlas token1Atlas;
	private TextureAtlas token2Atlas;
	private TextureAtlas token3Atlas;
	private TextureAtlas token4Atlas;
	private TextureAtlas buttonAtlasBack;		//Anything with "back" at the end pertains to the back button.
	private TextureAtlas addAtlas;
	private TextureAtlas removeAtlas;

	public CharacterSelect(MyGdxGame game, SpriteBatch batch) {
		super(game);
		this.game = game;
		this.batch = batch;
		canStart = false;
		
		shapeRenderer = new ShapeRenderer();
		showBoxes = false;
		keyHolding = false;
		
		iconTextures = new Texture[NUM_CHARACTERS];
		iconImages = new Image[NUM_CHARACTERS];
		characterTextures = new Texture[NUM_CHARACTERS];
		characterImages = new Image[NUM_CHARACTERS];
		iconCollisionBoxes = new Rectangle[NUM_CHARACTERS];
		coinCollisionBoxes = new Rectangle[4];
		characterChoices = new int[4];
		characterChoices = new int[] {-1,-1,-1,-1};
		
		startMessageTexture = new Texture("CharacterSelectScreen/start_message.png");
		startMessageImage = new Image(startMessageTexture);
		startMessageImage.setWidth(MyGdxGame.GAME_WIDTH/3.22581f);
		startMessageImage.setHeight(MyGdxGame.GAME_HEIGHT/20.416667f);
		startMessageImage.setPosition(MyGdxGame.GAME_WIDTH/5.85938f, MyGdxGame.GAME_HEIGHT/1.09253f);
		
		playerOneChoices = new Image[NUM_CHARACTERS];
		playerTwoChoices = new Image[NUM_CHARACTERS];
		playerThreeChoices = new Image[NUM_CHARACTERS];
		playerFourChoices = new Image[NUM_CHARACTERS];
		
		iconTextures[0] = new Texture("CharacterSelectScreen/icons/lonrk.png");
		iconTextures[1] = new Texture("CharacterSelectScreen/icons/bawsur.png");
		iconTextures[2] = new Texture("CharacterSelectScreen/icons/chulk.png");
		iconTextures[3] = new Texture("CharacterSelectScreen/icons/rosalina.png");
		iconTextures[4] = new Texture("CharacterSelectScreen/icons/samuz.png");
		iconTextures[5] = new Texture("CharacterSelectScreen/icons/slammindorf.png");
		iconTextures[6] = new Texture("CharacterSelectScreen/icons/sanic.png");
		
		iconImages[0] = new Image(iconTextures[0]);
		iconImages[0].setWidth(ICON_WIDTH);
		iconImages[0].setHeight(ICON_HEIGHT);
		iconImages[1] = new Image(iconTextures[1]);
		iconImages[1].setWidth(ICON_WIDTH);
		iconImages[1].setHeight(ICON_HEIGHT);
		iconImages[2] = new Image(iconTextures[2]);
		iconImages[2].setWidth(ICON_WIDTH);
		iconImages[2].setHeight(ICON_HEIGHT);
		iconImages[3] = new Image(iconTextures[3]);
		iconImages[3].setWidth(ICON_WIDTH);
		iconImages[3].setHeight(ICON_HEIGHT);
		iconImages[4] = new Image(iconTextures[4]);
		iconImages[4].setWidth(ICON_WIDTH);
		iconImages[4].setHeight(ICON_HEIGHT);
		iconImages[5] = new Image(iconTextures[5]);
		iconImages[5].setWidth(ICON_WIDTH);
		iconImages[5].setHeight(ICON_HEIGHT);
		iconImages[6] = new Image(iconTextures[6]);
		iconImages[6].setWidth(ICON_WIDTH);
		iconImages[6].setHeight(ICON_HEIGHT);
		
		characterTextures[0] = new Texture("CharacterSelectScreen/character_images/lonrk.png");
		characterImages[0] = new Image(characterTextures[0]);
		for(int i = 1; i < NUM_CHARACTERS; i++) {
			characterTextures[i] = new Texture("CharacterSelectScreen/character_images/not_available.png");
			characterImages[i] = new Image(characterTextures[i]);
		}
		
		for(int i = 7; i < NUM_CHARACTERS; i++) {
			iconTextures[i] = new Texture("CharacterSelectScreen/icons/locked.png");
			iconImages[i] = new Image(iconTextures[i]);
			iconImages[i].setWidth(ICON_WIDTH);
			iconImages[i].setHeight(ICON_HEIGHT);
		}
		
		for(int i = 0; i < NUM_CHARACTERS; i++) {
			iconCollisionBoxes[i] = new Rectangle();
			iconCollisionBoxes[i].setHeight(ICON_HEIGHT);
			iconCollisionBoxes[i].setWidth(ICON_WIDTH);
		}
		
		player1 = new Texture("CharacterSelectScreen/player_backgrounds/player1.png");
		player1_image = new Image(player1);
		player1_image.setHeight(MyGdxGame.GAME_HEIGHT/2.5789F);
		player1_image.setWidth(MyGdxGame.GAME_WIDTH/4.46428F);
		player1_image.setPosition(MyGdxGame.GAME_WIDTH/9.80392F, MyGdxGame.GAME_HEIGHT/15.07692F);
		
		player2 = new Texture("CharacterSelectScreen/player_backgrounds/player2.png");
		player2_image = new Image(player2);
		player2_image.setHeight(MyGdxGame.GAME_HEIGHT/2.5789F);
		player2_image.setWidth(MyGdxGame.GAME_WIDTH/4.46428F);
		player2_image.setPosition(MyGdxGame.GAME_WIDTH/3.37078F, MyGdxGame.GAME_HEIGHT/15.07692F);
		
		player3 = new Texture("CharacterSelectScreen/player_backgrounds/player3.png");
		player3_image = new Image(player3);
		player3_image.setHeight(MyGdxGame.GAME_HEIGHT/2.5789F);
		player3_image.setWidth(MyGdxGame.GAME_WIDTH/4.46428F);
		player3_image.setPosition(MyGdxGame.GAME_WIDTH/2.03252F, MyGdxGame.GAME_HEIGHT/15.07692F);
		
		player4 = new Texture("CharacterSelectScreen/player_backgrounds/player4.png");
		player4_image = new Image(player4);
		player4_image.setHeight(MyGdxGame.GAME_HEIGHT/2.5789F);
		player4_image.setWidth(MyGdxGame.GAME_WIDTH/4.46428F);
		player4_image.setPosition(MyGdxGame.GAME_WIDTH/1.45631F, MyGdxGame.GAME_HEIGHT/15.07692F);
		
		stage = new Stage();
		first = new Group();
		second = new Group();
		third = new Group();
		fourth = new Group();
		
		for(int i = 0; i < NUM_CHARACTERS; i++) {
			playerOneChoices[i] = new Image(characterTextures[i]);
			playerTwoChoices[i] = new Image(characterTextures[i]);
			playerThreeChoices[i] = new Image(characterTextures[i]);
			playerFourChoices[i] = new Image(characterTextures[i]);
		}
		
		characters = new ArrayList<Fighter>();
		
		tokenMovable = new boolean[] {false, false, false, false};
		characterExists = new boolean[] {false, false, false, false};
		addRemoveActors = new Array<Actor>();
		font = new BitmapFont();
		font.setScale(2);
		font.setColor(255,255,255,1);
		
		create();
		addRemoveActors.add(add1);
		addRemoveActors.add(add2);
		addRemoveActors.add(add3);
		addRemoveActors.add(add4);
		addRemoveActors.add(remove1);
		addRemoveActors.add(remove2);
		addRemoveActors.add(remove3);
		addRemoveActors.add(remove4);
		first.addActor(background);

		for(int i = 0; i < NUM_CHARACTERS; i++) {
			second.addActor(iconImages[i]);
		}
		
		third.addActor(backButton);
		third.addActor(add1);
		third.addActor(add2);
		third.addActor(add3);
		third.addActor(add4);
		
		stage.addActor(first);
		stage.addActor(fourth);
		stage.addActor(second);
		stage.addActor(third);
	}

	public void addScreens(Screen back, StageSelectScreen next) {
		this.back = back;
		this.next = next;
	}

	public void create() {
		
		addStyle = new TextButtonStyle();
		addSkin = new Skin();
		addAtlas = new TextureAtlas("CharacterSelectScreen/add_button/add.pack");
		addSkin.addRegions(addAtlas);
		addStyle.font = font;
		addStyle.up = addSkin.getDrawable("Add");
		removeStyle = new TextButtonStyle();
		removeSkin = new Skin();
		removeAtlas = new TextureAtlas("CharacterSelectScreen/remove_button/remove.pack");
		removeSkin.addRegions(removeAtlas);
		removeStyle.font = font;
		removeStyle.up = removeSkin.getDrawable("Remove");
		characterExists = new boolean[]{false, false, false, false};
		
		picture = new Texture("CharacterSelectScreen/background.png");
		background = new Image(picture);
		background.setHeight(MyGdxGame.GAME_HEIGHT);
		background.setWidth(MyGdxGame.GAME_WIDTH);
		background.setPosition(0, 0);
		background.setZIndex(0);
		createBackButton();
		createButtons();
	}

	public void createButtons() {
		
		float positionX = MyGdxGame.GAME_WIDTH/50;
		float positionY = MyGdxGame.GAME_HEIGHT/1.38462F;
		float gapX = MyGdxGame.GAME_WIDTH/56.24999F;		//There will be 9 gaps
		float gapY = MyGdxGame.GAME_HEIGHT/40.49999F;
		
		for(int i = 0; i < NUM_CHARACTERS; i++) {
			iconImages[i].setPosition(positionX, positionY);
			iconCollisionBoxes[i].setPosition(positionX, positionY);
			positionX += (ICON_WIDTH + gapX);
			if(positionX + iconImages[i].getWidth() >= MyGdxGame.GAME_WIDTH) {
				positionX = MyGdxGame.GAME_WIDTH/50;
				positionY -= (ICON_HEIGHT + gapY);
			}
		}
		
		add1 = new TextButton("", addStyle);
		add1.setPosition(MyGdxGame.GAME_WIDTH/8.287F, MyGdxGame.GAME_HEIGHT/12.25F);
		add1.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		add1.setWidth(MyGdxGame.GAME_WIDTH/9F);
		add1.setZIndex(100);
		add1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.addActor(player1_image);
				addRemoveActors.get(0).remove();
				third.addActor(remove1);
				third.addActor(token1);
				characterExists[0] = true;
				return true;
			}
		});
		
		add2 = new TextButton("", addStyle);
		add2.setPosition(MyGdxGame.GAME_WIDTH/3.125F, MyGdxGame.GAME_HEIGHT/12.25F);
		add2.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		add2.setWidth(MyGdxGame.GAME_WIDTH/9F);
		add2.setZIndex(100);
		add2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.addActor(player2_image);
				addRemoveActors.get(1).remove();
				third.addActor(remove2);
				third.addActor(token2);
				characterExists[1] = true;
				return true;
			}
		});
		
		add3 = new TextButton("", addStyle);
		add3.setPosition(MyGdxGame.GAME_WIDTH/1.948F, MyGdxGame.GAME_HEIGHT/12.25F);
		add3.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		add3.setWidth(MyGdxGame.GAME_WIDTH/9F);
		add3.setZIndex(100);
		add3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.addActor(player3_image);
				addRemoveActors.get(2).remove();
				third.addActor(remove3);
				third.addActor(token3);
				characterExists[2] = true;
				return true;
			}
		});
		
		add4 = new TextButton("", addStyle);
		add4.setPosition(MyGdxGame.GAME_WIDTH/1.411F, MyGdxGame.GAME_HEIGHT/12.25F);
		add4.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		add4.setWidth(MyGdxGame.GAME_WIDTH/9F);
		add4.setZIndex(100);
		add4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.addActor(player4_image);
				addRemoveActors.get(3).remove();
				third.addActor(remove4);
				third.addActor(token4);
				characterExists[3] = true;
				return true;
			}
		});
		
		remove1 = new TextButton("", removeStyle);
		remove1.setPosition(MyGdxGame.GAME_WIDTH/8.287F, MyGdxGame.GAME_HEIGHT/12.25F);
		remove1.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		remove1.setWidth(MyGdxGame.GAME_WIDTH/9F);
		remove1.setZIndex(100);
		remove1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.removeActor(player1_image);
				addRemoveActors.get(4).remove();
				third.addActor(add1);
				token1.remove();
				characterExists[0] = false;
				token1.setPosition(MyGdxGame.GAME_WIDTH/3.6855F, MyGdxGame.GAME_HEIGHT/2.78409F);
				return true;
			}
		});
		
		remove2 = new TextButton("", removeStyle);
		remove2.setPosition(MyGdxGame.GAME_WIDTH/3.125F, MyGdxGame.GAME_HEIGHT/12.25F);
		remove2.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		remove2.setWidth(MyGdxGame.GAME_WIDTH/9F);
		remove2.setZIndex(100);
		remove2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.removeActor(player2_image);
				addRemoveActors.get(5).remove();
				third.addActor(add2);
				token2.remove();
				characterExists[1] = false;
				token2.setPosition(MyGdxGame.GAME_WIDTH/2.14286F, MyGdxGame.GAME_HEIGHT/2.78409F);
				return true;
			}
		});
		
		remove3 = new TextButton("", removeStyle);
		remove3.setPosition(MyGdxGame.GAME_WIDTH/1.948F, MyGdxGame.GAME_HEIGHT/12.25F);
		remove3.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		remove3.setWidth(MyGdxGame.GAME_WIDTH/9F);
		remove3.setZIndex(100);
		remove3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.removeActor(player3_image);
				addRemoveActors.get(6).remove();
				third.addActor(add3);
				token3.remove();
				characterExists[2] = false;
				token3.setPosition(MyGdxGame.GAME_WIDTH/1.51057F, MyGdxGame.GAME_HEIGHT/2.78409F);
				return true;
			}
		});
		
		remove4 = new TextButton("", removeStyle);
		remove4.setPosition(MyGdxGame.GAME_WIDTH/1.411F, MyGdxGame.GAME_HEIGHT/12.25F);
		remove4.setHeight(MyGdxGame.GAME_HEIGHT/20F);
		remove4.setWidth(MyGdxGame.GAME_WIDTH/9F);
		remove4.setZIndex(100);
		remove4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fourth.removeActor(player4_image);
				addRemoveActors.get(7).remove();
				third.addActor(add4);
				token4.remove();
				characterExists[3] = false;
				token4.setPosition(MyGdxGame.GAME_WIDTH/1.1664F, MyGdxGame.GAME_HEIGHT/2.78409F);
				return true;
			}
		});
		
		token1Skin = new Skin();
		token2Skin = new Skin();
		token3Skin = new Skin();
		token4Skin = new Skin();
		token1Atlas = new TextureAtlas("CharacterSelectScreen/token/player_1/token_one.pack");
		token2Atlas = new TextureAtlas("CharacterSelectScreen/token/player_2/token_two.pack");
		token3Atlas = new TextureAtlas("CharacterSelectScreen/token/player_3/token_three.pack");
		token4Atlas = new TextureAtlas("CharacterSelectScreen/token/player_4/token_four.pack");
		token1Skin.addRegions(token1Atlas);
		token2Skin.addRegions(token2Atlas);
		token3Skin.addRegions(token3Atlas);
		token4Skin.addRegions(token4Atlas);
		token1Style = new TextButtonStyle();
		token2Style = new TextButtonStyle();
		token3Style = new TextButtonStyle();
		token4Style = new TextButtonStyle();
		token1Style.font = font;
		token2Style.font = font;
		token3Style.font = font;
		token4Style.font = font;
		token1Style.up = token1Skin.getDrawable("Token");
		token2Style.up = token2Skin.getDrawable("Token");
		token3Style.up = token3Skin.getDrawable("Token");
		token4Style.up = token4Skin.getDrawable("Token");
		
		token1 = new TextButton("", token1Style);
		token1.setPosition(MyGdxGame.GAME_WIDTH/3.6855F, MyGdxGame.GAME_HEIGHT/2.78409F);
		token1.setHeight(50);
		token1.setWidth(50);
		token1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[0] = !tokenMovable[0];
				return true;
			}
		});
		token2 = new TextButton("", token2Style);
		token2.setPosition(MyGdxGame.GAME_WIDTH/2.14286F, MyGdxGame.GAME_HEIGHT/2.78409F);
		token2.setHeight(50);
		token2.setWidth(50);
		token2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[1] = !tokenMovable[1];
				return true;
			}
		});
		token3 = new TextButton("", token3Style);
		token3.setPosition(MyGdxGame.GAME_WIDTH/1.51057F, MyGdxGame.GAME_HEIGHT/2.78409F);
		token3.setHeight(50);
		token3.setWidth(50);
		token3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[2] = !tokenMovable[2];
				return true;
			}
		});
		token4 = new TextButton("", token4Style);
		token4.setPosition(MyGdxGame.GAME_WIDTH/1.1664F, MyGdxGame.GAME_HEIGHT/2.78409F);
		token4.setHeight(50);
		token4.setWidth(50);
		token4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[3] = !tokenMovable[3];
				return true;
			}
		});
		
		coinCollisionBoxes[0] = new Rectangle();
		coinCollisionBoxes[1] = new Rectangle();
		coinCollisionBoxes[2] = new Rectangle();
		coinCollisionBoxes[3] = new Rectangle();
		coinCollisionBoxes[0].setHeight(token1.getHeight()*0.6f);
		coinCollisionBoxes[1].setHeight(token1.getHeight()*0.6f);
		coinCollisionBoxes[2].setHeight(token1.getHeight()*0.6f);
		coinCollisionBoxes[3].setHeight(token1.getHeight()*0.6f);
		coinCollisionBoxes[0].setWidth(token1.getWidth()*0.6f);
		coinCollisionBoxes[1].setWidth(token1.getWidth()*0.6f);
		coinCollisionBoxes[2].setWidth(token1.getWidth()*0.6f);
		coinCollisionBoxes[3].setWidth(token1.getWidth()*0.6f);
		coinCollisionBoxes[0].setPosition(token1.getX()+token1.getWidth()*0.2f, token1.getY()+token1.getHeight()*0.2f);
		coinCollisionBoxes[1].setPosition(token2.getX()+token2.getWidth()*0.2f, token2.getY()+token2.getHeight()*0.2f);
		coinCollisionBoxes[2].setPosition(token3.getX()+token3.getWidth()*0.2f, token3.getY()+token3.getHeight()*0.2f);
		coinCollisionBoxes[3].setPosition(token4.getX()+token4.getWidth()*0.2f, token4.getY()+token4.getHeight()*0.2f);	
	}

	public void createBackButton() {
		skinBack = new Skin();
		buttonAtlasBack = new TextureAtlas("back/goBack.pack");

		skinBack.addRegions(buttonAtlasBack);
		textButtonStyleBack = new TextButtonStyle();
		textButtonStyleBack.font = font;
		textButtonStyleBack.up = skinBack.getDrawable("backButton");
		backButton = new TextButton("", textButtonStyleBack);

		backButton.setHeight(100);
		backButton.setWidth(100);
		backButton.setPosition(50,50);

		backButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				game.changeScreen(back);
				clear();
				return true;
			}
		});
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public void clear() {
		remove1.remove();
		remove2.remove();
		remove3.remove();
		remove4.remove();
		
		second.addActor(add1);
		second.addActor(add2);
		second.addActor(add3);
		second.addActor(add4);
		
		token1.setPosition(MyGdxGame.GAME_WIDTH/3.926F, MyGdxGame.GAME_HEIGHT/3.091F);
		token2.setPosition(MyGdxGame.GAME_WIDTH/2.228F, MyGdxGame.GAME_HEIGHT/3.091F);
		token3.setPosition(MyGdxGame.GAME_WIDTH/1.554F, MyGdxGame.GAME_HEIGHT/3.091F);
		token4.setPosition(MyGdxGame.GAME_WIDTH/1.191F, MyGdxGame.GAME_HEIGHT/3.091F);
		canStart = false;
		characterExists[0] = false;
		characterExists[1] = false;
		characterExists[2] = false;
		characterExists[3] = false;
		
	}

	public void render() {
		super.render();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.act();
		stage.draw();
		update();	//Call update last so that the background does not cover the play message.
		
		if(showBoxes) {			//Draw all the collision boxes
			shapeRenderer.begin(ShapeType.Line);
			for(int i = 0; i < coinCollisionBoxes.length; i++) {
				shapeRenderer.rect(coinCollisionBoxes[i].getX(), coinCollisionBoxes[i].getY(), coinCollisionBoxes[i].getWidth(), coinCollisionBoxes[i].getHeight());
			}
			for(int i = 0; i < iconCollisionBoxes.length; i++) {
				shapeRenderer.rect(iconCollisionBoxes[i].getX(), iconCollisionBoxes[i].getY(), iconCollisionBoxes[i].getWidth(), iconCollisionBoxes[i].getHeight());
			}
			shapeRenderer.end();
		}
	}

	public void update() {
		
		numberCharacters = 0;
		
		if(tokenMovable[0]) {
			token1.setPosition(Gdx.input.getX() - token1.getWidth()/2, MyGdxGame.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		if(tokenMovable[1]) {
			token2.setPosition(Gdx.input.getX() - token1.getWidth()/2, MyGdxGame.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		if(tokenMovable[2]) {
			token3.setPosition(Gdx.input.getX() - token1.getWidth()/2, MyGdxGame.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		if(tokenMovable[3]) {
			token4.setPosition(Gdx.input.getX() - token1.getWidth()/2, MyGdxGame.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		
		coinCollisionBoxes[0].setPosition(token1.getX()+token1.getWidth()*0.2f, token1.getY()+token1.getHeight()*0.2f);
		coinCollisionBoxes[1].setPosition(token2.getX()+token2.getWidth()*0.2f, token2.getY()+token2.getHeight()*0.2f);
		coinCollisionBoxes[2].setPosition(token3.getX()+token3.getWidth()*0.2f, token3.getY()+token3.getHeight()*0.2f);
		coinCollisionBoxes[3].setPosition(token4.getX()+token4.getWidth()*0.2f, token4.getY()+token4.getHeight()*0.2f);
		
		p1Chosen = 0;
		p2Chosen = 0;
		p3Chosen = 0;
		p4Chosen = 0;
		
		for(int i = 0; i < NUM_CHARACTERS; i++) {
			
			if(Intersector.overlaps(coinCollisionBoxes[0], iconCollisionBoxes[i])) {
				playerOneChoices[i].setPosition(player1_image.getX(), player1_image.getY());
				second.addActor(playerOneChoices[i]);
				characterChoices[0] = i;
				p1Chosen = 1;
			}
			else {
				playerOneChoices[i].remove();
			}
			if(Intersector.overlaps(coinCollisionBoxes[1], iconCollisionBoxes[i])) {
				playerTwoChoices[i].setPosition(player2_image.getX(), player2_image.getY());
				second.addActor(playerTwoChoices[i]);
				characterChoices[1] = i;
				p2Chosen = 1;
			}
			else {
				playerTwoChoices[i].remove();
			}
			if(Intersector.overlaps(coinCollisionBoxes[2], iconCollisionBoxes[i])) {
				playerThreeChoices[i].setPosition(player3_image.getX(), player3_image.getY());
				second.addActor(playerThreeChoices[i]);
				characterChoices[2] = i;
				p3Chosen = 1;
			}
			else {
				playerThreeChoices[i].remove();
			}
			if(Intersector.overlaps(coinCollisionBoxes[3], iconCollisionBoxes[i])) {
				playerFourChoices[i].setPosition(player4_image.getX(), player4_image.getY());
				second.addActor(playerFourChoices[i]);
				characterChoices[3] = i;
				p4Chosen = 1;
			}
			else {
				playerFourChoices[i].remove();
			}
		}
		
		if(p1Chosen == 0)
			characterChoices[0] = -1;
		if(p2Chosen == 0)
			characterChoices[1] = -1;
		if(p3Chosen == 0)
			characterChoices[2] = -1;
		if(p4Chosen == 0)
			characterChoices[3] = -1;
		
		for(int i = 0; i < characterChoices.length; i++) {
			if(characterChoices[i] >= 0) {
				numberCharacters++;
			}
		}
		
		if((numberCharacters >= 2) && !tokenMovable[0] && !tokenMovable[1] && !tokenMovable[2] && !tokenMovable[3]) {
			canStart = true;
		}
		else {
			canStart = false;
		}
		
		if(Gdx.input.isKeyPressed(48) && !keyHolding) {		//If "T" is pressed, testing mode is entered.
			showBoxes = !showBoxes;
			keyHolding = true;
		}
		else if(!Gdx.input.isKeyPressed(48))
			keyHolding = false;
		
		if(canStart) {
			stage.addActor(startMessageImage);
			if(Gdx.input.isKeyPressed(66)) {
				characters.add(new Lonrk(batch, MyGdxGame.GAME_WIDTH/2, MyGdxGame.GAME_HEIGHT/2));	
				next.passCharacters(characters);
				game.changeScreen(next);
			}
		}
		else {
			startMessageImage.remove();
		}
	}
}
