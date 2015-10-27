package com.mygdx.game.characters;

public class CharacterControls {
	public static final int PLAYER_1_LEFT = 29;
	public static final int PLAYER_1_RIGHT = 32;
	public static final int PLAYER_1_UP = 51;
	public static final int PLAYER_1_DOWN = 47;
	public static final int PLAYER_1_JUMP = 62;
	
	public static final int PLAYER_2_LEFT = 21;
	public static final int PLAYER_2_RIGHT = 22;
	public static final int PLAYER_2_UP = 20;
	public static final int PLAYER_2_DOWN = 19;
	public static final int PLAYER_2_JUMP = 144;
	
	public static final int[] PLAYER_1_CONTROLS = {PLAYER_1_LEFT, PLAYER_1_RIGHT, PLAYER_1_UP, PLAYER_1_DOWN, PLAYER_1_JUMP};
	public static final int[] PLAYER_2_CONTROLS = {PLAYER_2_LEFT, PLAYER_2_RIGHT, PLAYER_2_UP, PLAYER_2_DOWN, PLAYER_2_JUMP};
	
	public static final int[][] CONTROLS = {PLAYER_1_CONTROLS, PLAYER_2_CONTROLS};
}
