/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ASUS-ROG
 */
public class Memorizing extends Game {
    Integer arr[], temp[];
    Boolean bool[];
    boolean correct = false;
    
    public Memorizing(){
        time = 0;
        duration = 0;
        score = 0;
        
        arr = new Integer[] {0,0,0,0,0,0,0,0,0,0};
        bool = new Boolean[] {true, true, true, true, true, true, true, true, true, true};
    }
    
    public void Random(){
        for(int i = 0; i < arr.length; i++){
            while(true){
                int val = rand.nextInt(90) + 10;
                
                if(!isExist(val)){
                    arr[i] = val;
                    break;
                }
            }
        }
    }
    
    public void Shuffle(){
        List<Integer> arrList = Arrays.asList(arr);
        Collections.shuffle(arrList);
    }
    
    public void hideNumber(){
        temp = new Integer[] {arr[3], arr[6], arr[8]};
        bool[3] = false;
        bool[6] = false;
        bool[8] = false;
    }
    
    public Integer[] getArray(){
        return arr;
    }
    
    public boolean getBool(int i){
        return bool[i];
    }
    
    public boolean Check(int ans){
        for(int i = 0; i < temp.length; i++){
            if(ans == temp[i]){
                correct = true;
                switch(i){
                    case 0:
                        bool[3] = true;
                        break;
                    case 1:
                        bool[6] = true;
                        break;
                    case 2:
                        bool[8] = true;
                        break;
                }
                return correct;
            }
        }
        correct = false;
        
        return correct;
    }
    
    public boolean isExist(int val){
        for(int i=0; i < arr.length; i++){
            if(val == arr[i])
                return true;
        }
        return false;
    }
}
