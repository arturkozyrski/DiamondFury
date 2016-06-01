package com.kozyrski.diamondFury;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Board implements KeyListener {
    Tile[][] board;
    int width;
    int height;
    Player player;
    Blank blank;
    JFrame frame = new JFrame("Diamond Fury 1.0");
    JPanel panel = new JPanel();
    Random random;
    Diamond diamond;
    ArrayList<Monster> monsters = new ArrayList<>();

    Stone[] stones;
    JTextArea incoming;
    JTextArea result;
    JTextField outgoing;
    JScrollPane qScroller;
    int numberOfDiamonds;
    MusicAndSound musicAndSound = new MusicAndSound();

    public Board(int a, int b) {
        musicAndSound.playMusic();
        player = new Player();
        board = new Tile[a][b];
        height = a;
        width = b;
        blank = new Blank();
        random = new Random();
        diamond = new Diamond();
        outgoing = new JTextField(20);
        incoming = new JTextArea(50, 50);
        qScroller = new JScrollPane(incoming);
        result = new JTextArea();
        stones = new Stone[13];
        for (int i = 0; i < stones.length; i++) {
            stones[i] = new Stone();
        }

        monsters.add(0, new Monster());

        for (int x = 0; x < height; x++) {

            for (int y = 0; y < width; y++) {
                board[x][y] = new Tile();

            }
        }
        putItemOnEmptyTile(monsters.get(0));
    }

    class ImageRenderer extends DefaultTableCellRenderer {
        Board board;

        public ImageRenderer(Board board) {
            this.board = board;

        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int x, int y) {
            board.board[x][y].getPath(powerUp());

            JLabel lbl;
            lbl = new JLabel(new ImageIcon(board.board[x][y].getPath(powerUp())));
            lbl.grabFocus();
            return lbl;
        }

    }
    public boolean checkIfEmptyTile(Tile tile) {
        return (board[tile.x][tile.y].toString().equals("#"));
    }

    public void makeEmptyTile(Tile tile) {
        board[tile.x][tile.y] = tile;
    }

    public void putTileOnBoard(Tile tile) {

        board[tile.x][tile.y] = tile;
    }

    public void putItemOnEmptyTile(Tile tile) {
        while (!checkIfEmptyTile(tile)) {
            tile.x = random.nextInt(13) + 1;
            tile.y = random.nextInt(13) + 1;
        }
        board[tile.x][tile.y] = tile;

    }
    public void nextLevel(int numberOfDiamonds) {
        if (numberOfDiamonds % 1 == 0) {
            musicAndSound.playSound(3);
            monsters.add(+1, new Monster());
            putItemOnEmptyTile(monsters.get(+1));
        }
    }
    public boolean checkDiamond(int x, int y) {
        if (x == diamond.x && y == diamond.y) {
            musicAndSound.playSound(1);
            if (powerUp() == false) {
            }
            return true;
        }
        return false;
    }

    public boolean checkIfWin(int x) {
        return x == 100;
    }

    public void saveResult() {
        try {
            FileWriter writer = new FileWriter("src/com.kozyrski.diamondFury.Misc/Result.txt", true);
            String result = "" + player.name + " " + player.diamondCount();
            writer.write("\r\n");
            writer.write(result);
            writer.close();
        } catch (IOException a) {
            a.printStackTrace();
        }

    }

    PowerUpTimer powerUpTimer = new PowerUpTimer();

    public boolean powerUp() {
        return powerUpTimer.isStillOn;
    }

    public boolean checkBorder(int x, int y) {
        for (Stone stone : stones) {
            if (x < 0 || x > 14 || y < 0 || y > 14 || (x == stone.x && y == stone.y)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision(Monster monsterToCheck, int x, int y) {
        for (Stone stone : stones) {
            if (stone.x == x && stone.y == y)
                return true;
            if (diamond.x == x && diamond.y == y)
                return true;

        }
        for (Monster monster : monsters) {
            if (monster != monsterToCheck && x == monster.x && y == monster.y) {
                return true;
            }
        }
        return false;
    }


    private void placeMonster(int number, int addx, int addy) {
        monsters.get(number).x = monsters.get(number).x + addx;
        monsters.get(number).y = monsters.get(number).y + addy;
    }


    public void moveMonsters() {
        //TODO
        for (int i = 0; i < monsters.size(); i++) {
            if (player.x > monsters.get(i).x && !powerUp()) {
                if (checkCollision(monsters.get(i), monsters.get(i).x + 1, monsters.get(i).y)) {
                    if (player.y > monsters.get(i).y) {
                        placeMonster(i, 0, +1);
                    } else {
                        placeMonster(i, 0, -1);
                    }
                } else {
                    placeMonster(i, +1, 0);
                }

            } else if (player.x < monsters.get(i).x && !powerUp()) {
                if (checkCollision(monsters.get(i), monsters.get(i).x - 1, monsters.get(i).y)) {
                    if (player.y > monsters.get(i).y) {
                        placeMonster(i, 0, +1);

                    } else {
                        placeMonster(i, 0, -1);
                    }
                } else {
                    placeMonster(i, -1, 0);
                }
            } else if (player.y > monsters.get(i).y & !powerUp()) {
                if (checkCollision(monsters.get(i), monsters.get(i).x, monsters.get(i).y + 1)) {
                    if (player.x > monsters.get(i).x) {
                        placeMonster(i, +1, 0);

                    } else {
                        placeMonster(i, -1, 0);
                    }
                } else {
                    placeMonster(i, 0, +1);
                }
            } else if (player.y < monsters.get(i).y && !powerUp()) {
                if (checkCollision(monsters.get(i), monsters.get(i).x, monsters.get(i).y - 1)) {
                    if (player.x > monsters.get(i).x) {
                        placeMonster(i, +1, 0);
                    } else {
                        placeMonster(i, -1, 0);
                    }
                }
                placeMonster(i, 0, -1);
            }
            putTileOnBoard(monsters.get(i));
        }
    }


    public void movePlayer(KeyEvent e) {
        blank.x = player.x;
        blank.y = player.y;


        switch (e.getKeyCode()) {
            case (KeyEvent.VK_W):

                if (!checkBorder(player.x - 1, player.y)) {
                    player.x = player.x - 1;
                }
                break;
            case (KeyEvent.VK_S):
                //TODO
                player.x = player.x + 1;
                if (checkBorder(player.x, player.y)) {
                    player.x = player.x - 1;
                }
                break;
            case (KeyEvent.VK_A):
                player.y = player.y - 1;
                if (checkBorder(player.x, player.y)) {
                    player.y = player.y + 1;
                }
                break;
            case (KeyEvent.VK_D):
                player.y = player.y + 1;
                if (checkBorder(player.x, player.y)) {
                    player.y = player.y - 1;
                }
                break;
        }
        if (checkDiamond(player.x, player.y)) {
            player.diamonds.add(diamond);

            diamond = new Diamond();
            putItemOnEmptyTile(diamond);
            powerUpTimer = new PowerUpTimer();
            powerUpTimer.timer(3);
            nextLevel(player.diamondCount());

        }
        if (checkIfWin(player.diamonds.size())) {
            numberOfDiamonds = player.diamonds.size();
            JOptionPane.showMessageDialog(frame, "You made it! You got " + numberOfDiamonds + " diamonds", "Diament$$$",
                    JOptionPane.PLAIN_MESSAGE);
        }
        makeEmptyTile(blank);
        putTileOnBoard(player);
        emptyFields();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    private void emptyFields() {
        Blank blank = new Blank();
        for (Monster monster : monsters) {
            blank.x = monster.x;
            blank.y = monster.y;
            makeEmptyTile(blank);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S ||
                e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            movePlayer(e);
            moveMonsters();
        }

        if (player.checkDead(player.x, player.y, this)) {
            numberOfDiamonds = player.diamonds.size();
            JOptionPane.showMessageDialog(frame, "You are dead!" + " your result: " + numberOfDiamonds, "DEAD", JOptionPane.PLAIN_MESSAGE);
            if (numberOfDiamonds > 5) {
                saveResult();

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public String getText(String path) {
        String scores = null;
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

}

