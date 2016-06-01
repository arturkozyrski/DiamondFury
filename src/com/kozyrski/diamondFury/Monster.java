package com.kozyrski.diamondFury;

import java.util.Random;

public class Monster extends Tile {

	Random ran = new Random();

	public  Monster() {
		x = ran.nextInt(13) +1;
		y = ran.nextInt(13) +1;
	}

	public String toString(){
		return "X";
	}
	public String getPath(boolean tile) {
		return "src/com.kozyrski.diamondFury.Graphics/Monster1.jpg";
	}

}

