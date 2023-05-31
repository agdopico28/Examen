/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snake2023;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author victor
 */
public class Board extends javax.swing.JPanel implements InitGamer{
 
    private Snake snake1;
    private Snake snake2;
    public static final int NUM_ROWS = 20;
    public static final int NUM_COLS = 20;
    private Timer timer;
    private MyKeyAdapter keyAdapter;
    private Food food1;
    private FoodFactory foodFactory;
    private ScoreInterface scoreInterface;

    @Override
    public void continueGame() {
        timer.start();
    }
    
    class MyKeyAdapter extends KeyAdapter {
        
        private boolean paused = false;

        public boolean isPaused() {
            return paused;
        }

        public void setPaused(boolean paused) {
            this.paused = paused;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake1.getDirection() != Direction.RIGHT) {
                        snake1.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake1.getDirection() != Direction.LEFT) {
                        snake1.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake1.getDirection() != Direction.DOWN) {
                        snake1.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake1.getDirection() != Direction.UP) {
                        snake1.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (snake2.getDirection() != Direction.RIGHT) {
                        snake2.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (snake2.getDirection() != Direction.LEFT) {
                        snake2.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_W:
                    if (snake2.getDirection() != Direction.DOWN) {
                        snake2.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (snake2.getDirection() != Direction.UP) {
                        snake2.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    paused = !paused;
                    break;
                default:
                    break;
            }
            repaint();
        }
    }
    
    public Board() {
        initComponents();

        timer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!keyAdapter.isPaused()) {
                    tick();
                }
            }
        });
        
        setFocusable(true);
        keyAdapter = new MyKeyAdapter();
        foodFactory = new FoodFactory();
        initGame();
    }
    
    public void setScoreInterface(ScoreInterface scoreInterface) {
        this.scoreInterface = scoreInterface;
    }
    
    @Override
    public void initGame() {
        snake1 = new Snake(4,0);
        snake2 = new Snake(4, 1);
        food1 = foodFactory.getFood(snake1);
        addKeyListener(keyAdapter);
        if (scoreInterface != null) {
            scoreInterface.reset();
        }
        timer.start();
    }
    
    private void tick() {
        if (snake1.canMove(snake1, snake2) ) {
            snake1.move();
            if (snake1.eat(food1)) {
                scoreInterface.increment1(food1.getPoints());
                food1 = foodFactory.getFood(snake1);
            }
            if (food1.hasToBeErased()) {
                food1 = foodFactory.getFood(snake1);
            }        
        } else {
            scoreInterface.increment2(50);
            processGameOver();
            
        }
        if(snake2.canMove(snake1, snake2)){
            snake2.move();
            if (snake2.eat(food1)) {
                scoreInterface.increment2(food1.getPoints());
                food1 = foodFactory.getFood(snake2);
            }
            if (food1.hasToBeErased()) {
                food1 = foodFactory.getFood(snake1);
            } 
        }else {
            scoreInterface.increment1(50);
            processGameOver();
            
        }
        
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void processGameOver() {
        timer.stop();
        removeKeyListener(keyAdapter);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        GameOverDialog gameOverDialog = new GameOverDialog(topFrame, true);
        gameOverDialog.setInitGamer(this);
        gameOverDialog.setScore1(scoreInterface.getScore1());
        gameOverDialog.setScore2(scoreInterface.getScore2());
        gameOverDialog.setVisible(true);
        /*int answer = JOptionPane.showConfirmDialog(
            this,  scoreInterface.getScore() + " points\nNew Game?",
                   "Game Over", JOptionPane.YES_NO_OPTION);
        if (answer == 0) {
            initGame();
        } else {
            System.exit(0);
        }*/
        // JFrame parentJFrame = (JFrame) SwingUtilities.getWindowAncestor(this); 
        // HighScoresDialog dialog = new HighScoresDialog(parentJFrame ,true);
        // dialog.setGetScorer(getScorer);
        // dialog.setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    int getSquareWidth() {
        return getWidth() / NUM_COLS;        
    }
    
    int getSquareHeight() {
        return getHeight() / NUM_ROWS;
    }
  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        if (snake1 != null) {
            snake1.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (snake2 != null) {
            snake2.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (food1 != null) {
            food1.paint(g, getSquareWidth(), getSquareHeight());
        }        
    }
    
    public void drawBackground(Graphics g) {        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, getWidth()/ NUM_COLS * NUM_COLS, 
                getHeight() / NUM_ROWS * NUM_ROWS);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
