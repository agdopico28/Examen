/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2023;

/**
 *
 * @author victor
 */
public class FoodFactory {
    
    public Food getFood(Snake snake) {
        double aleat = Math.random();
        if (aleat < 0.2) {
            return new SpecialFood(snake);
        } else {
            return new Food(snake);
        }
    }
}