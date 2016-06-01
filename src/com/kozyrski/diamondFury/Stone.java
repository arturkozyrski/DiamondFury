package com.kozyrski.diamondFury;

import java.util.Random;

public class Stone extends Tile {
	Random ran = new Random();

	public Stone(){
		x = ran.nextInt(13) +1;
		y = ran.nextInt(13) +1;

	}



	public String toString() {
		return "W";

	}

	public String getPath(boolean tile) {
		return "src/com.kozyrski.diamondFury.Graphics/com.kozyrski.diamondFury.Stone.jpg";
	}
}
