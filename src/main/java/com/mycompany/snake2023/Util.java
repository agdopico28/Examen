/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2023;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author victor
 */
public class Util {
    public static void drawSquare(Graphics g, int row, int col,
            NodeType nodeType, int squareWidth, int squareHeight) {
        Color colors[] = {
            new Color(204, 102, 102),
            new Color(102, 204, 102), new Color(102, 102, 204) ,  
                new Color(102, 200, 204)
        };
        int x = col * squareWidth;
        int y = row * squareHeight;
        Color color = colors[nodeType.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2,
                squareHeight - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1,
                x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1,
                y + squareHeight - 1,
                x + squareWidth - 1, y + 1);
    }
}
