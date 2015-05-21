package com.mygdx.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

public class FightScreen extends Screen{
	private Stage stage;
	private OrthographicCamera camera;
	private ArrayList<Character> characters;

	public FightScreen(MyGdxGame game) {
		super(game);
		stage = new Stage();
		camera = (OrthographicCamera) stage.getCamera();
	}
	
	public void create() {
		
	}
	
	public void addCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}
	
	public void render() {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.act();
		stage.draw();
	}
	
	public void addScreens() {
		
	}
	
	public void clear() {
		for(int i = 0; i < characters.size(); i++) {
			characters.remove(i);
		}
	}
	
	public void update() {
		
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	
	
}
