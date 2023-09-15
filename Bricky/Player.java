/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import koneksi.Connect;

/**
 *
 * @author ASUS-ROG
 */
public class Player{
    static String name;
    static int exp;
    static int score[] = new int[4];
    
    public Player(){}
    
    public Player(String name) throws SQLException{
        this.name = name;
        //LoadUser();
    }
    
    public Player(String name, int exp, int score1, int score2, int score3, int score4){
        this.name = name;
        this.exp = exp;
        
        score[0] = score1;
        score[1] = score2;
        score[2] = score3;
        score[3] = score4;
    }

    public void LoadUser()throws SQLException{
        Statement statement = (Statement) Connect.GetConnection().createStatement();
        String sql = ("SELECT * FROM tblscore WHERE " + "Username='" + name + "'");
        ResultSet res   = statement.executeQuery(sql);
 
        while(res.next ()){
            //Object[] obj = new Object[7];
            name = res.getString("Username");
            exp = res.getInt("exp");
            score[0] = res.getInt("score1");
            score[1] = res.getInt("score2");
            score[2] = res.getInt("score3");
            score[3] = res.getInt("score4");
        }
    }
    
    public String getName(){
	return name;
    }
	
    public int getExp(){
        return exp;
    }
	
    public int getScore(int sc){
        int a = score[sc];
        return a;
    }
}
