package com.kozyrski.diamondFury;

import java.util.ArrayList;

public class Player extends Tile {
	ArrayList diamonds = new ArrayList();
    String name=null;

public Player() {
	x=6;
	y=6;
}
public int  diamondCount(){
    int diamondCount;
    diamondCount=diamonds.size();
        return diamondCount;
    }

	public String toString(){
		return "@";
		
	}
	public String getPath(boolean timer) {
		if(timer ==false){
			return "src/com.kozyrski.diamondFury.Graphics/Player1.jpg";
		}
		return "src/com.kozyrski.diamondFury.Graphics/Player12.jpg";
	}


	public boolean checkDead(int x, int y, Board board) {

        for (Monster monster : board.monsters) {
            if (monster.x == x && monster.y == y) {
                board.musicAndSound.playSound(2);

                return true;
            }
        }
        return false;
    }
}
