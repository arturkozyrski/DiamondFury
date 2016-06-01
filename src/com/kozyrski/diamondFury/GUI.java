package com.kozyrski.diamondFury;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


public class GUI extends Board {

    public GUI(int a, int b) {
        super(a, b);
    }
    JLabel displayResult = new JLabel();

    public void createAndShowGUI() throws IOException {
        UIManager.put("OptionPane.messageFont", font());
        UIManager.put("OptionPane.buttonFont", font());
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);

        displayResult.setBackground(Color.BLACK);
        player.name = JOptionPane.showInputDialog(frame, "Enter your name", "Name", JOptionPane.PLAIN_MESSAGE);

        putItemOnEmptyTile(player);

        for (int i = 0; i < stones.length; i++) {
            putItemOnEmptyTile(stones[i]);
        }
        putItemOnEmptyTile(diamond);

        JTable table = new JTable(width, height);
        frame.addKeyListener(this);
        for (int x = 0; x < board.length; x++) {
            TableColumn tc = table.getColumnModel().getColumn(x);
            tc.setCellRenderer(new ImageRenderer(this));
        }
        table.setRowHeight(50);
        TableColumnModel columnModel = table.getColumnModel();
        for (int x = 0; x < board.length; x++) {
            columnModel.getColumn(x).setPreferredWidth(50);
        }

        table.setTableHeader(null);
        table.setGridColor(Color.black);
        table.setIntercellSpacing(new Dimension(0, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setPreferredSize(new Dimension(160, 100));
        panel.setBackground(Color.DARK_GRAY);
        panel.setVisible(true);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        ImageIcon icon = new ImageIcon("src/com.kozyrski.diamondFury.Graphics/Logo.png");
        frame.setIconImage(icon.getImage());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(770, 980);
        frame.getContentPane().add(BorderLayout.WEST, table);
        JPanel mainPanel = new JPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, mainPanel);

        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPanel.setPreferredSize(new Dimension(100, 175));

        JLabel picLabel1 = new JLabel(new ImageIcon(ImageIO.read(new File("src/com.kozyrski.diamondFury.Graphics/title.png"))));
        JLabel exit = new JLabel(new ImageIcon(ImageIO.read(new File("src/com.kozyrski.diamondFury.Graphics/Quit.png"))));
        JLabel result1 = new JLabel(new ImageIcon(ImageIO.read(new File("src/com.kozyrski.diamondFury.Graphics/Result.png"))));
        JLabel highestScore = new JLabel(new ImageIcon(ImageIO.read(new File("src/com.kozyrski.diamondFury.Graphics/Score.png"))));
        JLabel rules = new JLabel(new ImageIcon(ImageIO.read(new File("src/com.kozyrski.diamondFury.Graphics/Rules.png"))));
        UIManager.put(result, font());

        mainPanel.setBackground(Color.BLACK);
        displayResult.setFont(font());
        displayResult.setForeground(new Color(255, 173, 0));
        exit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        result1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Your score: " + player.diamondCount(), "Score", JOptionPane.PLAIN_MESSAGE);
            }
        });
        highestScore.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Highest score: " + getText("src/com.kozyrski.diamondFury.Misc/Result.txt"), "Highest score", JOptionPane.PLAIN_MESSAGE);
            }
        });
        rules.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "com.kozyrski.diamondFury.Diamond Fury Rules: " + getText("src/com.kozyrski.diamondFury.Misc/Rules.txt"), "Rules", JOptionPane.PLAIN_MESSAGE);
            }
        });
        mainPanel.add(result1);
        mainPanel.add(displayResult);

        mainPanel.add(highestScore);
        mainPanel.add(rules);
        mainPanel.add(exit);
        mainPanel.add(picLabel1);
        String playerResult = "" + player.diamondCount();

         displayResult.setText(playerResult);


    }

    public Font font() {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Windows\\Fonts\\04B_30_.TTF")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Windows\\Fonts\\04B_30_.TTF")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }
}




