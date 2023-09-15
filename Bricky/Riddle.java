/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;

import java.sql.ResultSet;
import java.sql.Statement;
import koneksi.Connect;

/**
 *
 * @author ASUS-ROG
 */
public class Riddle extends Game{
    String title, question, hint;
    int chance;
    int answer = 0;
    boolean correct = false;
    
    public Riddle(){
        time = 0;
        duration = 0;
        score = 0;
        chance = 1;
    }
    
    public void loadQuestion(int id){
        String sql = "SELECT judul, isi_soal, hint, jawaban FROM tblriddle WHERE id_soal = " + id;
        try{
            Statement statement = (Statement) Connect.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                title = res.getString("judul");
                question = res.getString("isi_soal");
                hint = res.getString("hint");
                answer = res.getInt("jawaban");
            }
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, "Data gagal diambil!");
        }
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
    
    public String getTitle(){
        return title;
    }
    
    public String getQuestion(){
        return question;
    }
    
    public String getHint(){
        chance--;
        return hint;
    }

}
