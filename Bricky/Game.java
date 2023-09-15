/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;

import java.util.Random;

/**
 *
 * @author ASUS-ROG
 */
public class Game {
    Random rand = new Random();
    public static int time, score, duration, bonus;
    
    public Game(){}
    
    public void timeElapsed(){
        time++;
        duration++;
    }
    
    public int getTime(){
        return time;
    }
    
    public void updateScore(int score, boolean ans){
        bonus = 120 - duration;
        
        if(bonus < 0)
            bonus = 0;
        
        if(ans)
            this.score += score + (bonus * 15); 
        
        duration = 0;
    }
    
    public int getScore(){
        return score;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public int getBonus(){
        return bonus;
    }
    
    public void clear(){
        time = 0;
    }
    
}
