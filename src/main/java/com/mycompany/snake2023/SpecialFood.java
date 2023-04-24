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
public class SpecialFood extends Food {
    
    public SpecialFood(Snake snake) {
        super(snake);
    }
    
    @Override
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, getRow(), getCol(), NodeType.FOOD, squareWidth, squareHeight);
    }
    
    @Override
    public int getNodesWhenEat() {
        return 3;
    }
    
}
