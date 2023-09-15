/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;

/**
 *
 * @author ASUS-ROG
 */
public class TimeRush extends QuizGame{
    public TimeRush(){
        time = 180;
        score = 0;
        duration = 0;
    }
    
    /**
     *
     */
    @Override
    public void timeElapsed(){
        time--;
        duration++;
    }
}
