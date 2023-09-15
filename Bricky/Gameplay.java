/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import koneksi.Connect;

/**
 *
 * @author ASUS-ROG
 */
public class Gameplay extends javax.swing.JFrame {
    Timer myTimer = new Timer();
    
    Player user;
    int sec, min;
    static int score, exp;
    int question, choice, max, chance = 10;
    int correct[], rid[];
    Integer temp[] = new Integer[3];
    
    Game game;
    QuizGame qu;
    TimeRush tr;
    Riddle rd;
    Memorizing mr;
    
    /**
     * Creates new form Gameplay
     */
    //public Gameplay(){}
    public Gameplay(Player user, int choice) {
        initComponents();
        this.user = user;
        this.choice = choice;

        switch(choice){
            case 1:
                game = new QuizGame();
                break;
            case 2:
                game = new TimeRush();
                break;
            case 3:
                game = new Riddle();
                break;
            case 4:
                game = new Memorizing();
                break;
        }
        
        GamePick();
        
        TimerTask task = new TimerTask(){
            public void run(){
                
                game.timeElapsed();
                sec = game.getTime()%60;
                min = game.getTime()/60;
                
                if(sec < 0)
                        sec = 0;
                if(sec < 10)
                    lblTimer.setText("Time :  " + min + ":0" + sec);
                else
                    lblTimer.setText("Time :  " + min + ":" + sec);
                
                if(game.getTime() < 0 || chance == 0){
                    myTimer.cancel();
                    myTimer.purge();
                    try {
                        gameOver();
                    } catch (SQLException ex) {
                        Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void GamePick(){
        score = 0;
        question = 0;
        
        switch(choice){
            case 1:
                qu = new QuizGame();
                pnlQuizTimeRush.setVisible(true);
                pnlRiddle.setVisible(false);
                pnlMemorize.setVisible(false);
                butSkip.setVisible(false);
                
                correct = new int[] {0,0,0,0,0,0,0,0,0,0};
                max = 10;
                break;
            case 2:
                tr = new TimeRush();
                pnlQuizTimeRush.setVisible(true);
                pnlRiddle.setVisible(false);
                pnlMemorize.setVisible(false);
                butSkip.setVisible(true);
                
                correct = new int[] {0,0,0,0,0,0,0,0,0,0};
                max = -1;
                break;
            case 3:
                rd = new Riddle();
                pnlQuizTimeRush.setVisible(false);
                pnlRiddle.setVisible(true);
                pnlMemorize.setVisible(false);
                butHint.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/lamp.png")).getImage().getScaledInstance(butHint.getWidth(), butHint.getHeight(), Image.SCALE_SMOOTH)));
                butHint.setEnabled(true);
                txtHint.setText("");
                //get array rid[]
                rid = new int[3];
                Random rand = new Random();
                for(int i = 0; i < 3; i++){
                    while(true){
                        boolean ex = false;
                        rid[i] = rand.nextInt(6) + 1;
                        
                        for(int ii = 0; ii < 3; ii++){
                            if(rid[ii] == rid[i]){
                                ex = false;
                                break;
                            }else{
                                ex = true;
                            }
                        }
                        
                        if(ex)
                            break;
                    }
                }
                
                max = 3;
                break;
            case 4:
                mr = new Memorizing();
                pnlQuizTimeRush.setVisible(false);
                pnlRiddle.setVisible(false);
                pnlMemorize.setVisible(true);
                butReady.setVisible(true);
                butOk2.setVisible(false);
                txtAnswer2.setVisible(false);
                lblChance.setVisible(false);
                lblText.setText("Remember these numbers.");
                
                chance = 3;
                max = 3;
                break;
        }
        loadQuestion();
    }
    
//    public void setScore(int score){
//        this.score += score;
//    }
    
    public void loadQuestion(){
        switch(choice){
            case 1:
                qu.Random();
                txtAnswer.setText("");
                txtQuestion.setText(qu.getQuestion());
                lblScore.setText("" + qu.getScore());
                showResult();
                break;
            case 2:
                tr.Random();
                txtAnswer.setText("");
                txtQuestion.setText(tr.getQuestion());
                lblScore.setText("" + tr.getScore());
                showResult();
                break;
            case 3:
                rd.loadQuestion(rid[(question - 1)]);
                lblNumR.setText(question + "/3 <" + rd.getTitle() + ">");
                lblHint.setText("Hint: " + rd.chance);
                txtQuestionR.setText(rd.getQuestion());
                lblScore.setText("" + rd.getScore());
                break;
            case 4:
                mr.Random();
                loadNumber();
                break;
        }
    }
    
    public void loadNumber(){
        Integer[] arr = mr.getArray();
        lblChance.setText("Chances left: " + chance);
        lblScore.setText("" + mr.getScore());
        txtAnswer2.setText("");
        lblNum1.setText("" + arr[0]);
        lblNum2.setText("" + arr[1]);
        lblNum3.setText("" + arr[2]);
        if(mr.getBool(3))
            lblNum4.setText("" + arr[3]);
        else
            lblNum4.setText("...");
        lblNum5.setText("" + arr[4]);
        lblNum6.setText("" + arr[5]);
        if(mr.getBool(6))
            lblNum7.setText("" + arr[6]);
        else
            lblNum7.setText("...");
        lblNum8.setText("" + arr[7]);
        if(mr.getBool(8))
            lblNum9.setText("" + arr[8]);
        else
            lblNum9.setText("...");
        lblNum10.setText("" + arr[9]);
    }
        
    private void showResult(){
        Color[] color = new Color[correct.length];
        for(int i = 0; i < correct.length; i++){
            if(correct[i] == 0){
                color[i] = Color.gray;
            }else if(correct[i] == 1){
                color[i] = Color.green;
            }else if(correct[i] == 2){
                color[i] = Color.red;
            }else{
                color[i] = Color.yellow;
            }
        }
        
        pnlQ1.setBackground(color[0]);
        pnlQ2.setBackground(color[1]);
        pnlQ3.setBackground(color[2]);
        pnlQ4.setBackground(color[3]);
        pnlQ5.setBackground(color[4]);
        pnlQ6.setBackground(color[5]);
        pnlQ7.setBackground(color[6]);
        pnlQ8.setBackground(color[7]);
        pnlQ9.setBackground(color[8]);
        pnlQ10.setBackground(color[9]);
    }
    
    private void answer(int answer){
        switch(choice){
            case 1:
                qu.checkAnswer(answer);
            
                if(qu.Check()){
                    game.updateScore(1000, true);
                    Correction(1);
                }else{
                    game.updateScore(0, false);
                    Correction(2);
                }
                break;
            case 2:
                tr.checkAnswer(answer);
            
                if(tr.Check()){
                    game.updateScore(1000, true);
                    Correction(1);
                }else{
                    game.updateScore(0, false);
                    Correction(2);
                }
                break;
            case 4:
                rd.checkAnswer(answer);
            
                if(rd.Check()){
                    game.updateScore(1000, true);
                }else{
                    game.updateScore(0, false);
                }
                break;
        }
        
    }
    
    private void Correction(int ans){
        if(question >= 10){
            for(int i=0; i < correct.length;i++){
                if(i == correct.length - 1){
                    correct[i] = ans;
                }else{
                    int num = correct[i+1];
                    correct[i] = num;
                }
            }
        }else{
            correct[question] = ans;
        }
        
    }
    
    private void gameOver() throws SQLException{
        String s = "";
        myTimer.cancel();
        myTimer.purge();
        
        score = game.getScore();
        exp = (int) Math.floor(score/10);
        switch(choice){
            case 1:
                s = "Quiz Game";
                break;
            case 2:
                s = "Time Rush Game";
                break;
            case 3:
                s = "Riddle Game";
                break;
            case 4:
                s = "Memorizing Game";
        }

        JOptionPane.showMessageDialog(this, "You have answered " + question + " questions.\nYour final score is : " + score + "\n\n" + lblTimer.getText(), s, HEIGHT);
        
        if ( score > user.getScore(choice - 1)){
            JOptionPane.showMessageDialog(null, "Score baru!" + user.getName());
            String sql = "UPDATE tblogin SET score" + choice + "=" + score + ", exp=" + exp + " WHERE Username='" + user.getName() + "'";
            //UPDATE `tblogin` SET `score1` = '0' WHERE `tblogin`.`Username` = 'auryn';
            try {
                Statement statement = (Statement) Connect.GetConnection().createStatement();
                statement.executeUpdate(sql);
                //Connect.GetConnection();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
            }
        }
        
        Main_menu window = new Main_menu(user);
        this.dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnlQuizTimeRush = new javax.swing.JPanel();
        txtAnswer = new javax.swing.JTextField();
        butOk = new javax.swing.JButton();
        lblValidation = new javax.swing.JLabel();
        txtQuestion = new javax.swing.JTextArea();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        pnlQ1 = new javax.swing.JPanel();
        pnlQ2 = new javax.swing.JPanel();
        pnlQ3 = new javax.swing.JPanel();
        pnlQ5 = new javax.swing.JPanel();
        pnlQ6 = new javax.swing.JPanel();
        pnlQ7 = new javax.swing.JPanel();
        pnlQ8 = new javax.swing.JPanel();
        pnlQ9 = new javax.swing.JPanel();
        pnlQ10 = new javax.swing.JPanel();
        pnlQ4 = new javax.swing.JPanel();
        butSkip = new javax.swing.JButton();
        pnlRiddle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuestionR = new javax.swing.JTextArea();
        lblNumR = new javax.swing.JLabel();
        lblHint = new javax.swing.JLabel();
        butOkR = new javax.swing.JButton();
        txtAnswerR = new javax.swing.JTextField();
        butHint = new javax.swing.JButton();
        txtHint = new javax.swing.JLabel();
        pnlMemorize = new javax.swing.JPanel();
        lblText = new javax.swing.JLabel();
        lblNum1 = new javax.swing.JLabel();
        lblNum2 = new javax.swing.JLabel();
        lblNum3 = new javax.swing.JLabel();
        lblNum4 = new javax.swing.JLabel();
        lblNum5 = new javax.swing.JLabel();
        lblNum6 = new javax.swing.JLabel();
        lblNum7 = new javax.swing.JLabel();
        lblNum8 = new javax.swing.JLabel();
        lblNum9 = new javax.swing.JLabel();
        lblNum10 = new javax.swing.JLabel();
        txtAnswer2 = new javax.swing.JTextField();
        butOk2 = new javax.swing.JButton();
        butReady = new javax.swing.JButton();
        lblChance = new javax.swing.JLabel();
        lblValidation2 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bricky");
        setResizable(false);
        setSize(new java.awt.Dimension(535, 334));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtAnswer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnswer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAnswerKeyPressed(evt);
            }
        });

        butOk.setText("Ok");
        butOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butOkActionPerformed(evt);
            }
        });

        lblValidation.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblValidation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtQuestion.setEditable(false);
        txtQuestion.setColumns(20);
        txtQuestion.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtQuestion.setRows(5);
        txtQuestion.setText("A * B = ?");
        txtQuestion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlQ1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ1.setPreferredSize(new java.awt.Dimension(45, 2));

        javax.swing.GroupLayout pnlQ1Layout = new javax.swing.GroupLayout(pnlQ1);
        pnlQ1.setLayout(pnlQ1Layout);
        pnlQ1Layout.setHorizontalGroup(
            pnlQ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ1Layout.setVerticalGroup(
            pnlQ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlQ2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ2.setPreferredSize(new java.awt.Dimension(45, 2));

        javax.swing.GroupLayout pnlQ2Layout = new javax.swing.GroupLayout(pnlQ2);
        pnlQ2.setLayout(pnlQ2Layout);
        pnlQ2Layout.setHorizontalGroup(
            pnlQ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ2Layout.setVerticalGroup(
            pnlQ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlQ3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ3.setPreferredSize(new java.awt.Dimension(45, 31));

        javax.swing.GroupLayout pnlQ3Layout = new javax.swing.GroupLayout(pnlQ3);
        pnlQ3.setLayout(pnlQ3Layout);
        pnlQ3Layout.setHorizontalGroup(
            pnlQ3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ3Layout.setVerticalGroup(
            pnlQ3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        pnlQ5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlQ5Layout = new javax.swing.GroupLayout(pnlQ5);
        pnlQ5.setLayout(pnlQ5Layout);
        pnlQ5Layout.setHorizontalGroup(
            pnlQ5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ5Layout.setVerticalGroup(
            pnlQ5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        pnlQ6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ6.setPreferredSize(new java.awt.Dimension(45, 31));

        javax.swing.GroupLayout pnlQ6Layout = new javax.swing.GroupLayout(pnlQ6);
        pnlQ6.setLayout(pnlQ6Layout);
        pnlQ6Layout.setHorizontalGroup(
            pnlQ6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ6Layout.setVerticalGroup(
            pnlQ6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        pnlQ7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ7.setPreferredSize(new java.awt.Dimension(45, 31));

        javax.swing.GroupLayout pnlQ7Layout = new javax.swing.GroupLayout(pnlQ7);
        pnlQ7.setLayout(pnlQ7Layout);
        pnlQ7Layout.setHorizontalGroup(
            pnlQ7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ7Layout.setVerticalGroup(
            pnlQ7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        pnlQ8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ8.setPreferredSize(new java.awt.Dimension(45, 31));

        javax.swing.GroupLayout pnlQ8Layout = new javax.swing.GroupLayout(pnlQ8);
        pnlQ8.setLayout(pnlQ8Layout);
        pnlQ8Layout.setHorizontalGroup(
            pnlQ8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ8Layout.setVerticalGroup(
            pnlQ8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        pnlQ9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ9.setPreferredSize(new java.awt.Dimension(45, 31));

        javax.swing.GroupLayout pnlQ9Layout = new javax.swing.GroupLayout(pnlQ9);
        pnlQ9.setLayout(pnlQ9Layout);
        pnlQ9Layout.setHorizontalGroup(
            pnlQ9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ9Layout.setVerticalGroup(
            pnlQ9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        pnlQ10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlQ10.setPreferredSize(new java.awt.Dimension(45, 31));

        javax.swing.GroupLayout pnlQ10Layout = new javax.swing.GroupLayout(pnlQ10);
        pnlQ10.setLayout(pnlQ10Layout);
        pnlQ10Layout.setHorizontalGroup(
            pnlQ10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ10Layout.setVerticalGroup(
            pnlQ10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        pnlQ4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlQ4Layout = new javax.swing.GroupLayout(pnlQ4);
        pnlQ4.setLayout(pnlQ4Layout);
        pnlQ4Layout.setHorizontalGroup(
            pnlQ4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        pnlQ4Layout.setVerticalGroup(
            pnlQ4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jLayeredPane2.setLayer(pnlQ1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnlQ4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(pnlQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQ10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQ1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
            .addComponent(pnlQ2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
            .addComponent(pnlQ10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlQ9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlQ8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlQ7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlQ6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlQ5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlQ4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlQ3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        butSkip.setText("Skip");
        butSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSkipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlQuizTimeRushLayout = new javax.swing.GroupLayout(pnlQuizTimeRush);
        pnlQuizTimeRush.setLayout(pnlQuizTimeRushLayout);
        pnlQuizTimeRushLayout.setHorizontalGroup(
            pnlQuizTimeRushLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuizTimeRushLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuizTimeRushLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlQuizTimeRushLayout.createSequentialGroup()
                        .addComponent(butSkip, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butOk, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtQuestion)
                    .addComponent(jLayeredPane2))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlQuizTimeRushLayout.setVerticalGroup(
            pnlQuizTimeRushLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuizTimeRushLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(pnlQuizTimeRushLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlQuizTimeRushLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(butOk)
                        .addComponent(butSkip)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        txtQuestionR.setColumns(20);
        txtQuestionR.setRows(5);
        jScrollPane1.setViewportView(txtQuestionR);

        lblNumR.setText("1/3");

        lblHint.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblHint.setText("Hint: ");

        butOkR.setText("Ok");
        butOkR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butOkRActionPerformed(evt);
            }
        });

        txtAnswerR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnswerR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAnswerRKeyPressed(evt);
            }
        });

        butHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butHintActionPerformed(evt);
            }
        });

        txtHint.setText("Hint:");
        txtHint.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlRiddleLayout = new javax.swing.GroupLayout(pnlRiddle);
        pnlRiddle.setLayout(pnlRiddleLayout);
        pnlRiddleLayout.setHorizontalGroup(
            pnlRiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiddleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addGroup(pnlRiddleLayout.createSequentialGroup()
                        .addComponent(lblNumR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHint, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRiddleLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtAnswerR, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butOkR))
                    .addGroup(pnlRiddleLayout.createSequentialGroup()
                        .addComponent(butHint, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlRiddleLayout.setVerticalGroup(
            pnlRiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiddleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumR)
                    .addComponent(lblHint))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(butHint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHint, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(pnlRiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butOkR)
                    .addComponent(txtAnswerR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblText.setText("Remember these numbers");

        lblNum1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum1.setText("1");
        lblNum1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum2.setText("2");
        lblNum2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum3.setText("3");
        lblNum3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum4.setText("4");
        lblNum4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum5.setText("5");
        lblNum5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum6.setText("6");
        lblNum6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum7.setText("7");
        lblNum7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum8.setText("8");
        lblNum8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum9.setText("9");
        lblNum9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNum10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum10.setText("10");
        lblNum10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtAnswer2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnswer2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAnswer2KeyPressed(evt);
            }
        });

        butOk2.setText("Ok");
        butOk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butOk2ActionPerformed(evt);
            }
        });

        butReady.setText("Start");
        butReady.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butReadyActionPerformed(evt);
            }
        });

        lblChance.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblChance.setText("Chances left: ");

        lblValidation2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout pnlMemorizeLayout = new javax.swing.GroupLayout(pnlMemorize);
        pnlMemorize.setLayout(pnlMemorizeLayout);
        pnlMemorizeLayout.setHorizontalGroup(
            pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMemorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMemorizeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butOk2))
                    .addGroup(pnlMemorizeLayout.createSequentialGroup()
                        .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMemorizeLayout.createSequentialGroup()
                                .addComponent(lblText)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlMemorizeLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMemorizeLayout.createSequentialGroup()
                                        .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNum6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                        .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNum7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNum8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlMemorizeLayout.createSequentialGroup()
                                        .addComponent(butReady)
                                        .addGap(49, 49, 49)
                                        .addComponent(lblValidation2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(34, 34, 34)
                        .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlMemorizeLayout.createSequentialGroup()
                                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNum9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNum5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNum10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblChance))))
                .addGap(82, 82, 82))
        );
        pnlMemorizeLayout.setVerticalGroup(
            pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMemorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblText)
                    .addComponent(lblChance))
                .addGap(18, 18, 18)
                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNum10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNum6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(pnlMemorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butOk2)
                    .addComponent(butReady)
                    .addComponent(lblValidation2))
                .addGap(34, 34, 34))
        );

        jLayeredPane1.setLayer(pnlQuizTimeRush, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pnlRiddle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pnlMemorize, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuizTimeRush, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlRiddle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlMemorize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuizTimeRush, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlRiddle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlMemorize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        lblTimer.setText("Time :  0:00");

        lblScore.setText("Score :  0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblScore)
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimer)
                    .addComponent(lblScore))
                .addGap(11, 11, 11)
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //QuizGame & TimeRush
    private void butOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOkActionPerformed
        try{
            int ans = Integer.parseInt(txtAnswer.getText());
            answer(ans);
            
            question++;
            loadQuestion();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Invalid number!");
        }
        
        if(question == max){
            try {
                gameOver();
            } catch (SQLException ex) {
                Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butOkActionPerformed

    //QuizGame & TimeRush
    private void txtAnswerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnswerKeyPressed
        Color color = null;
        try{
            int i = Integer.parseInt(txtAnswer.getText());
            lblValidation.setText("");
            color = Color.black;
            txtAnswer.setForeground(color);
        }catch(NumberFormatException e){
            lblValidation.setText("Harap masukkan angka...");
            color = Color.red;
            txtAnswer.setForeground(color);
        }
    }//GEN-LAST:event_txtAnswerKeyPressed

    //TimeRush
    private void butSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSkipActionPerformed
        game.updateScore(0, false);
        Correction(3);
        question++;
        loadQuestion();
    }//GEN-LAST:event_butSkipActionPerformed

    //Memorizing
    private void butReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butReadyActionPerformed
        mr.Shuffle();
        mr.hideNumber();
        loadNumber();
        lblChance.setVisible(true);
        lblText.setText("Find the missing number!");
        txtAnswer2.setVisible(true);
        butOk2.setVisible(true);
        butReady.setVisible(false);
    }//GEN-LAST:event_butReadyActionPerformed

    //Memorizing
    private void txtAnswer2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnswer2KeyPressed
        Color color = null;
        try{
            int i = Integer.parseInt(txtAnswer2.getText());
            lblValidation2.setText("");
            color = Color.black;
            txtAnswer2.setForeground(color);
        }catch(NumberFormatException e){
            lblValidation2.setText("Harap masukkan angka...");
            color = Color.red;
            txtAnswer2.setForeground(color);
        }
    }//GEN-LAST:event_txtAnswer2KeyPressed

    //Memorizing
    private void butOk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOk2ActionPerformed
        try{
            int ans = Integer.parseInt(txtAnswer2.getText());
            //answer(ans);
            if(mr.Check(ans)){
                question++;
                game.updateScore(1000, true);
            }else{
                chance--;
            }
            loadNumber();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Invalid number!");
        }
        
        if(question == max){
            try {
                gameOver();
            } catch (SQLException ex) {
                Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butOk2ActionPerformed

    //Riddle
    private void txtAnswerRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnswerRKeyPressed
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_txtAnswerRKeyPressed

    //Riddle
    private void butHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butHintActionPerformed
        butHint.setEnabled(false);
        txtHint.setText("Hint: " + rd.getHint());
        lblHint.setText("Hint: " + rd.chance);
    }//GEN-LAST:event_butHintActionPerformed

    //Riddle
    private void butOkRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOkRActionPerformed
        try{
            int ans = Integer.parseInt(txtAnswerR.getText());
            answer(ans);
            
            question++;
            loadQuestion();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Invalid number!");
        }
        
        if(question == max){
            try {
                gameOver();
            } catch (SQLException ex) {
                Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butOkRActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Gameplay().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butHint;
    private javax.swing.JButton butOk;
    private javax.swing.JButton butOk2;
    private javax.swing.JButton butOkR;
    private javax.swing.JButton butReady;
    private javax.swing.JButton butSkip;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChance;
    private javax.swing.JLabel lblHint;
    private javax.swing.JLabel lblNum1;
    private javax.swing.JLabel lblNum10;
    private javax.swing.JLabel lblNum2;
    private javax.swing.JLabel lblNum3;
    private javax.swing.JLabel lblNum4;
    private javax.swing.JLabel lblNum5;
    private javax.swing.JLabel lblNum6;
    private javax.swing.JLabel lblNum7;
    private javax.swing.JLabel lblNum8;
    private javax.swing.JLabel lblNum9;
    private javax.swing.JLabel lblNumR;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblText;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JLabel lblValidation;
    private javax.swing.JLabel lblValidation2;
    private javax.swing.JPanel pnlMemorize;
    private javax.swing.JPanel pnlQ1;
    private javax.swing.JPanel pnlQ10;
    private javax.swing.JPanel pnlQ2;
    private javax.swing.JPanel pnlQ3;
    private javax.swing.JPanel pnlQ4;
    private javax.swing.JPanel pnlQ5;
    private javax.swing.JPanel pnlQ6;
    private javax.swing.JPanel pnlQ7;
    private javax.swing.JPanel pnlQ8;
    private javax.swing.JPanel pnlQ9;
    private javax.swing.JPanel pnlQuizTimeRush;
    private javax.swing.JPanel pnlRiddle;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtAnswer2;
    private javax.swing.JTextField txtAnswerR;
    private javax.swing.JLabel txtHint;
    private javax.swing.JTextArea txtQuestion;
    private javax.swing.JTextArea txtQuestionR;
    // End of variables declaration//GEN-END:variables
}
