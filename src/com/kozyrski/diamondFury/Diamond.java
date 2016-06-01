package com.kozyrski.diamondFury;

import java.util.Random;

public class Diamond extends Tile {
	Random ran = new Random();

	public Diamond(){
		x = ran.nextInt(13) +1;
		y = ran.nextInt(13) +1;
	}
	public String toString() {
		return "D";
	}

	public String getPath(boolean tile) {
		return "src/com.kozyrski.diamondFury.Graphics/Diamond1.jpg";
	}
}
