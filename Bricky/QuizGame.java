/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bricky;
import java.util.InputMismatchException;
import java.util.Random;

/**
 *
 * @author ASUS-ROG
 */
public class QuizGame extends Game {
    boolean correct = false;
    int answer = 0;
    String question;
    
    public QuizGame(){
        time = 0;
        score = 0;
        duration = 0;
    }
	
    public void Random() {
        int i = rand.nextInt(6);
	question = "";
        
        switch(i) {
            case 0:
                First();
                break;
            case 1:
                Second();
                break;
            case 2:
                Third();
                break;
            case 3:
                Fourth();
                break;
            case 4:
                Fifth();
                break;
            case 5:
                Sixth();
                break;
        }
    }

    protected void First() {
        int a, b, c, aa, bb, r;
        int ii = rand.nextInt(2);
        a = (rand.nextInt(4) + 8);
        b = (rand.nextInt(3) + 3);
		
        r = (rand.nextInt(7) + 3);
		
        aa = a++;
        bb = b++;
		
        for(int i = 3; i > 0; i--) {
            a -= i;
            b += i;
			
            if(ii == 0)
                c = (a * b) + r;
            else
                c = (a * b) - r;
			
            question += a + ", " + b + " = " + c + "\n" ;
        }
        question += "\n" + aa + ", " + bb + " = ?";
		
        if(ii == 0)
            c = (aa * bb) + r;
        else
            c = (aa * bb) - r;
		
        answer = c;
    }
	
    protected void Second() {
	int a, b, c, aa, bb, r;
	a = (rand.nextInt(4) + 10);
	b = (rand.nextInt(3) + 2);
	r = rand.nextInt(3);
	
	aa = a-3;
	bb = b+2;

	if(aa == bb)
            aa++;

	for(int i = 3; i > 0; i--) {
            a -= ((i+1) + r);
            b += (i + r);
			
            if(a <= 1)
		a += (r + 1);
			
            c = (a * a) + (b * b);
            
            question += a + ", " + b + " = " + c + "\n";
	}
        question += "\n" + aa + ", " + bb + " = ?";

	c = (aa * aa) + (bb * bb);
        answer = c;
    }
	
    protected void Third() {
        int r = rand.nextInt(7);
        int inc = (rand.nextInt(30) + 10);
        int value = (rand.nextInt(25) + 15);
        int[] arr = new int[7];
	
        for(int i = 0; i < 7; i++) {
            int x = i * inc;
            arr[i] = value + x;
            if(i == r)
                question += "...  ";
            else
                question += arr[i] + "  ";
        }
		
        answer = arr[r];
    }
	
    protected void Fourth() {
	int r = rand.nextInt(5);
	int mul = rand.nextInt(3) + 2;
	int value = (rand.nextInt(8) + 4);
	int[] arr = new int[5];
		
	for(int i = 0; i < 5; i++) {
            int a = i + mul;
			
            value *= a;
			
            arr[i] = value;
			
            if(i == r)
                question += "...  ";
            else
                question += arr[i] + "  ";
	}
	answer = arr[r];
    }
	
    protected void Fifth() {
	int a, b, c, r, x = 0;
	a = rand.nextInt(5) + 4;
		
	while(true) {
            b = rand.nextInt(8) + 5;	
			
            if(a != b)
		break;
            }
		
	while(true) {
            c = rand.nextInt(7) + 5;
			
            if(c != a && c != b)
		break;
        }
		
        r = rand.nextInt(4);
		
        switch(r) {
            case 0:
                x = a + c;
                
                question += " A + B = " + (a+b) + "\n B + C = " + (c+b) + "\n B + B = " + (b+b) + "\n\n A + C = ...";
                break;
            case 1:
                x = a * c;
                
                question += " A x B = " + (a*b) + "\n B x C = " + (c*b) + "\n B x B = " + (b*b) + "\n\n A x C = ...";
                break;
            case 2:
                int ii2 = rand.nextInt(3);

                question += " A + B = " + (a+b) + "\n B + C = " + (c+b) + "\n A + C = " + (c+a);
                
                switch(ii2) {
                    case 0:
                        x = a * 2;
                        question += "\n\n A + A = ...";
                        break;
                    case 1:
                        x = b * 2;
                        question += "\n\n B + B = ...";
                        break;
                    case 2:
                        x = c * 2;
                        question += "\n\n C + C = ...";
                        break;
		}
                break;
            case 3:
                int ii3 = rand.nextInt(3);
                
                question += " A x B = " + (a*b) + "\n B x C = " + (c*b) + "\n A x C = " + (c*a);
                
                switch(ii3) {
                    case 0:
                        x = a * a;
                        question += "\n\n A x A = ...";
                        break;
                    case 1:
                        x = b * b;
                        question += "\n\n B x B = ...";
                        break;
                    case 2:
                        x = c * c;
                        question += "\n\n C x C = ...";
                        break;
                }	
            break;
        }
	answer = x;
    }
	
    protected void Sixth() {
	int r = rand.nextInt(6);
	int pl = rand.nextInt(20) + 10;
	int mul = rand.nextInt(2) + 2;
	int value = rand.nextInt(8) + 8;
	int[] arr = new int[6];
		
	arr[0] = value;
	arr[1] = value + pl;
	arr[2] = arr[0] * mul;
	arr[3] = arr[2] + pl;
	arr[4] = arr[2] * mul;
	arr[5] = arr[4] + pl;

	for(int i = 0; i < 6; i++) {
            if(i == r)
                question += "...  ";
            else
                question += arr[i] + "  ";
        }
        answer = arr[r];
    }

    public void checkAnswer(int ans) {
	if(ans == answer)
            correct = true;
	else
            correct = false;
    }
	
    public boolean Check() {
        return correct;
    }
    
    public String getQuestion(){
        return question;
    }
}
