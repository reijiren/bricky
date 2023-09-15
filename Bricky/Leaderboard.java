/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import koneksi.Connect;

/**
 *
 * @author ASUS-ROG
 */
public class Leaderboard {
    String[] name = new String[5];
    int[] score = new int[5];
    
    public Leaderboard(int ch) throws SQLException{
        String pil = "score" + ch;
        String sql = "SELECT Username, " + pil + " FROM tblogin ORDER BY " + pil + " DESC";
        
        try{
            Statement statement = (Statement) Connect.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            int i = 0;
            while(res.next()){
                name[i] = res.getString("Username");
                score[i] = res.getInt(pil);
                i++;
            }
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, "Data gagal diambil!");
        }
    }

    public String getName(int arr){
        return name[arr];
    }
    
    public int getScore(int arr){
        return score[arr];
    }
}
