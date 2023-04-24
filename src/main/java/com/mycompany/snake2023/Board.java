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
import javax.swing.Timer;

/**
 *
 * @author victor
 */
public class Board extends javax.swing.JPanel {
 
    private Snake snake;
    public static final int NUM_ROWS = 20;
    public static final int NUM_COLS = 20;
    private Timer timer;
    private MyKeyAdapter keyAdapter;
    private Food food;
    private FoodFactory foodFactory;
    
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
                    if (snake.getDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
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
        snake = new Snake(4);
        timer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!keyAdapter.isPaused()) {
                    tick();
                }
            }
        });
        timer.start();
        setFocusable(true);
        keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        foodFactory = new FoodFactory();
        food = foodFactory.getFood(snake);
    }
    
    private void tick() {
        if (snake.canMove()) {
            snake.move();
        }
        if (snake.eat(food)) {
            // increment score
            food = foodFactory.getFood(snake);
        }
        if (food.hasToBeErased()) {
            food = foodFactory.getFood(snake);
        }
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        if (snake != null) {
            snake.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (food != null) {
            food.paint(g, getSquareWidth(), getSquareHeight());
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
