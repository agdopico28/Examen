/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2023;

import java.awt.Graphics;
import java.util.*;

/**
 *
 * @author victor
 */
public class Snake {
    private List<Node> list;
    private Direction direction;
    private int toGrow;
    private int numJugador;
 
    public Snake(int numNodes, int numJugador) {
        list = new ArrayList<>();
        this.numJugador = numJugador;
        if(numJugador == 0){
            int row = Board.NUM_ROWS / 2;
            int col = Board.NUM_COLS / 3;
            for (int i = 0; i < numNodes; i ++) {
                list.add(new Node(row, col - i));
            }
        }else if (numJugador == 1){
            int row = Board.NUM_ROWS / 3;
            int col = Board.NUM_COLS / 3;
            for (int i = 0; i < numNodes; i ++) {
                list.add(new Node(row, col - i));
            }
        }
        direction = Direction.RIGHT;
        toGrow = 0;
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        boolean first1 = true;
        if(numJugador == 0){
            for (Node node: list) {
                int row = node.getRow();
                int col = node.getCol();            
                Util.drawSquare(g, row, col, 
                        first1 ? NodeType.HEAD : NodeType.BODY, 
                        squareWidth, squareHeight);
                first1 = false;
            }
        }
        boolean first2 = true;
        if (numJugador == 1){
            for (Node node: list) {
                int row = node.getRow();
                int col = node.getCol();            
                Util.drawSquare(g, row, col, 
                        first2 ? NodeType.HEAD2 : NodeType.BODY2, 
                        squareWidth, squareHeight);
                first2 = false;
            }
        }
    }
    
    public int getHeaderRow() {
        return list.get(0).getRow();
    }
    
    public int getHeaderCol() {
        return list.get(0).getCol();
    }
    
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public boolean contains1(int row, int col, Snake snake1) {       
        for (Node node: snake1.list) {
            if (node.getRow() == row && node.getCol() == col) {
                return true;
            }
        }
        return false;
        
    }
    
    public boolean contains2(int row, int col, Snake snake2) {       
        for (Node node: snake2.list) {
            if (node.getRow() == row && node.getCol() == col) {
                return true;
            }
        }
        return false;
        
    }
    
    public boolean canMove(Snake snake1,Snake snake2) {
        int row = getHeaderRow();
        int col = getHeaderCol();
        switch (direction) {
            case UP:
                if (row - 1 < 0 || contains1(row - 1, col, snake1) || contains2(row - 1, col, snake2)) {
                    return false;
                }
                break;
            case DOWN:
                if (row + 1 >= Board.NUM_ROWS || contains1(row + 1, col, snake1) || contains2(row + 1, col, snake2)) {
                    return false;
                }
                break;
            case LEFT:
                if (col - 1 < 0 || contains1(row, col - 1, snake1) || contains2(row, col - 1, snake2)) {
                    return false;
                }
                break;
            case RIGHT:
                if (col + 1 >= Board.NUM_COLS || contains1(row, col + 1, snake1) || contains2(row, col + 1, snake2)) {
                    return false;
                }
                break;
            default:
                throw new AssertionError();
        }
        return true;
    }
    
    public void move() {
        int row = getHeaderRow();
        int col = getHeaderCol();
        switch (direction) {
            case UP:
                list.add(0, new Node(row - 1, col));
                break;
            case DOWN:
                list.add(0, new Node(row + 1, col));
                break;
            case LEFT:
                list.add(0, new Node(row, col - 1));
                break;
            case RIGHT:
                list.add(0, new Node(row, col + 1));
                break;
            default:
                throw new AssertionError();
        }
        if (toGrow <= 0) {
            list.remove(list.size() - 1);
        } else {
            toGrow --;
        }
    }
    
    
    public boolean eat(Food food) {
        if (getHeaderRow() == food.getRow() && getHeaderCol() == food.getCol()) {
            toGrow += food.getNodesWhenEat();
            return true;
        }
        return false;
    }
}
