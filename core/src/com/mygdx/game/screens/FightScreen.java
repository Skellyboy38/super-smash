package com.mygdx.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.Fighter;
import com.mygdx.game.stages.Map;

public class FightScreen extends Screen{
	private Map map;
	private Stage stage;
	private OrthographicCamera camera;
	private ArrayList<Fighter> characters;
	private SpriteBatch batch;
	private Screen back;
	private MyGdxGame game;
	
	private float cameraWidth;
	private float cameraHeight;
	private float cameraPositionX;
	private float cameraPositionY;

	public FightScreen(MyGdxGame game, SpriteBatch batch, Map map, ArrayList<Fighter> characters, Stage stage) {
		super(game);
		this.map = map;
		this.batch = batch;
		this.characters = characters;
		this.game = game;
		this.stage = stage;
		camera = (OrthographicCamera)stage.getCamera();
		
		cameraWidth = MyGdxGame.GAME_WIDTH;
		cameraHeight = MyGdxGame.GAME_HEIGHT;
		cameraPositionX = 0;
		cameraPositionY = 0;
		
		int counter = 0;
		for(int i = 0; i < characters.size(); i++)
		{
			characters.get(i).setSpawnPoint(map.getSpawnPoints()[counter]);
			counter++;
		}
	}
	
	public void addCharacters(ArrayList<Fighter> characters) {
		this.characters = characters;
	}
	
	public void render() {
		super.render();
		stage.act();
		stage.draw();
		map.render();
		for(Fighter f : characters) {
			f.render();
		}
		update();
	}
	
	public void addScreens(Screen back) {
		this.back = back;
	}
	
	public void clear() {
		map = null;
	}
	
	public void updateCamera() {
		camera.viewportHeight = cameraHeight;
		camera.viewportWidth = cameraWidth;
		camera.translate(1,1);
	}
	
	public void update() {
		for(Fighter f : characters)
		{
			if(Intersector.overlaps(map.getCollisionBoxes()[0], f.getCollisionBoxes()[0])) 
			{
				f.capVerticalPosition();
			}
			else {
				f.uncapVerticalPosition();
			}
			
			if(Intersector.overlaps(map.getCollisionBoxes()[2], f.getCollisionBoxes()[2]))
			{
				f.setPosition(map.getCollisionBoxes()[0].getX() - f.getWidth()*0.75f,
						map.getCollisionBoxes()[0].getY() + map.getCollisionBoxes()[0].getHeight() - f.getHeight()*0.9f);
				f.hangLeft();
			}
			if(Intersector.overlaps(map.getCollisionBoxes()[3], f.getCollisionBoxes()[1]))
			{
				f.setPosition(map.getCollisionBoxes()[0].getX() + map.getCollisionBoxes()[0].getWidth() - f.getWidth()*0.245f,
						map.getCollisionBoxes()[0].getY() + map.getCollisionBoxes()[0].getHeight() - f.getHeight()*0.9f);
				f.hangRight();
			}
		}
		
		
		if(Gdx.input.isKeyPressed(31)) {	
			game.changeScreen(back);
			((CharacterSelect)(back)).lightReset();
		}
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	
	
}
