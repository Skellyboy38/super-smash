package com.mygdx.game.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SUPER SMASH";	//The title that appears when the game opens
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();	//The width of the computer screen
		double height = screenSize.getHeight();	//Height of the computer screen
		config.width = (int)width;		//Sets the width and height of the game 
		config.height = (int)height;	
		config.fullscreen = true;		//Starts the game i`n full screen mode
		new LwjglApplication(new MyGdxGame(), config);
	}
}
