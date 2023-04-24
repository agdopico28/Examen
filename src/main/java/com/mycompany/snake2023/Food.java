/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2023;

import java.awt.Graphics;

/**
 *
 * @author victor
 */
public class Food extends Node {
    public Food(Snake snake) {
        super(0,0);
        int row = (int) (Math.random() * Board.NUM_ROWS);
        int col = (int) (Math.random() * Board.NUM_COLS);
        while (snake.contains(row, col)) {
            row = (int) (Math.random() * Board.NUM_ROWS);
            col = (int) (Math.random() * Board.NUM_COLS);
        }
        setRow(row);
        setCol(col);
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, getRow(), getCol(), NodeType.FOOD, squareWidth, squareHeight);
    }
    
    public int getNodesWhenEat() {
        return 1;
    }
    
    public boolean hasToBeErased() {
        return false;
    }
}
