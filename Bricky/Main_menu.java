/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Hanna Rhefa Tegar
 */

public class Main_menu extends javax.swing.JFrame implements ActionListener {
    Player pl;
    
    String user, date;
    int exp, lv;
    int score[] = new int[4];
    int count = 1;
    
    SimpleDateFormat daytime, daydate;
    
    /**
     * Creates new form Main_menu
     */
    public Main_menu(){}
    
    public Main_menu(Player pl) throws SQLException {
        initComponents();
        this.setVisible(true);
        
        this.pl = pl;
        pl.LoadUser();
        
        user = pl.getName();
        exp = pl.getExp();
        
        daydate = new SimpleDateFormat("dd MMMMM, yyyy");
        //daytime = new SimpleDateFormat("hh:mm:ss");
        
        lv = (int)Math.floor(exp/1000) + 1;
        
        for(int i = 0; i < 4; i++)
            this.score[i] = pl.getScore(i);
        
        refresh();
        
        panelControl(1);
        
    }
    
    public void panelControl(int act){
        /*
          1. main menu
          2. pilih game
          3. leaderboard
        */
        
        switch(act){
            case 1:
                pnlMainMenu.setVisible(true);
                pnlPilihGame.setVisible(false);
                pnlLdrboard.setVisible(false);
                pnlTable.setVisible(false);
                break;
            case 2:
                pnlMainMenu.setVisible(false);
                pnlPilihGame.setVisible(true);
                pnlLdrboard.setVisible(false);
                pnlTable.setVisible(false);
                break;
            case 3:
                pnlMainMenu.setVisible(false);
                pnlPilihGame.setVisible(false);
                pnlLdrboard.setVisible(true);
                pnlTable.setVisible(true);
                break;
        }
    }
    
    public void play(){
        panelControl(2);
        lblScore1.setText("Score: " + score[0]);
        lblScore2.setText("Score: " + score[1]);
        lblScore3.setText("Score: " + score[2]);
        lblScore4.setText("Score: " + score[3]);
    }
    
    public void leaderboard() throws SQLException{
        panelControl(3);
        butNext.setText("\u25BA");
        butPrev.setText("\u25C4");
        
        rankGame();
    }
    
    public void rankGame() throws SQLException{
        Leaderboard ld = new Leaderboard(count);
        /*
          1. Quiz
          2. Time Rush
          3. Riddle
          4. Memorizing
        */
        
        switch(count){
            case 1:
                lblGame.setText("QUIZ");
                lblPScore.setText("" + score[0]);
                lblName1.setText(ld.getName(0));
                lblSR1.setText("" + ld.getScore(0));
                lblName2.setText(ld.getName(1));
                lblSR2.setText("" + ld.getScore(1));
                lblName3.setText(ld.getName(2));
                lblSR3.setText("" + ld.getScore(2));
                lblName4.setText(ld.getName(3));
                lblSR4.setText("" + ld.getScore(3));
                lblName5.setText(ld.getName(4));
                lblSR5.setText("" + ld.getScore(4));
                break;
            case 2:
                lblGame.setText("TIME RUSH");
                lblPScore.setText("" + score[1]);
                lblName1.setText(ld.getName(0));
                lblSR1.setText("" + ld.getScore(0));
                lblName2.setText(ld.getName(1));
                lblSR2.setText("" + ld.getScore(1));
                lblName3.setText(ld.getName(2));
                lblSR3.setText("" + ld.getScore(2));
                lblName4.setText(ld.getName(3));
                lblSR4.setText("" + ld.getScore(3));
                lblName5.setText(ld.getName(4));
                lblSR5.setText("" + ld.getScore(4));
                break;
            case 3:
                lblGame.setText("RIDDLE");
                lblPScore.setText("" + score[2]);
                lblName1.setText(ld.getName(0));
                lblSR1.setText("" + ld.getScore(0));
                lblName2.setText(ld.getName(1));
                lblSR2.setText("" + ld.getScore(1));
                lblName3.setText(ld.getName(2));
                lblSR3.setText("" + ld.getScore(2));
                lblName4.setText(ld.getName(3));
                lblSR4.setText("" + ld.getScore(3));
                lblName5.setText(ld.getName(4));
                lblSR5.setText("" + ld.getScore(4));
                break;
            case 4:
                lblGame.setText("MEMORIZING");
                lblPScore.setText("" + score[3]);
                lblName1.setText(ld.getName(0));
                lblSR1.setText("" + ld.getScore(0));
                lblName2.setText(ld.getName(1));
                lblSR2.setText("" + ld.getScore(1));
                lblName3.setText(ld.getName(2));
                lblSR3.setText("" + ld.getScore(2));
                lblName4.setText(ld.getName(3));
                lblSR4.setText("" + ld.getScore(3));
                lblName5.setText(ld.getName(4));
                lblSR5.setText("" + ld.getScore(4));
                break;
        }
    }
        
    public void refresh(){
        lblUser.setText(user);
        lblLevel.setText("Lv " + lv);
        txtDate.setText(date);
        expBar.setValue(exp%1000);
        expBar.setString((exp%1000) + "/1000");
        
        date = daydate.format(Calendar.getInstance().getTime());
        txtDate.setText(date);
        
        pnlMainMenu.setVisible(false);
        pnlPilihGame.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lblUser = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        expBar = new javax.swing.JProgressBar();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnlMainMenu = new javax.swing.JPanel();
        lblMainMenu = new javax.swing.JLabel();
        butPlay = new javax.swing.JButton();
        butLdbrd = new javax.swing.JButton();
        butExit = new javax.swing.JButton();
        pnlPilihGame = new javax.swing.JPanel();
        lblPilihGame = new javax.swing.JLabel();
        butGame1 = new javax.swing.JButton();
        lblScore1 = new javax.swing.JLabel();
        butGame2 = new javax.swing.JButton();
        lblScore2 = new javax.swing.JLabel();
        butGame3 = new javax.swing.JButton();
        lblScore3 = new javax.swing.JLabel();
        butGame4 = new javax.swing.JButton();
        lblScore4 = new javax.swing.JLabel();
        butBack = new javax.swing.JButton();
        pnlLdrboard = new javax.swing.JPanel();
        lblLdboard = new javax.swing.JLabel();
        pnlTable = new javax.swing.JPanel();
        lblRank = new javax.swing.JLabel();
        lblRank1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        butPrev = new javax.swing.JButton();
        butNext = new javax.swing.JButton();
        lblName1 = new javax.swing.JLabel();
        lblName2 = new javax.swing.JLabel();
        lblName3 = new javax.swing.JLabel();
        lblName4 = new javax.swing.JLabel();
        lblName5 = new javax.swing.JLabel();
        lblSR1 = new javax.swing.JLabel();
        lblSR2 = new javax.swing.JLabel();
        lblSR3 = new javax.swing.JLabel();
        lblSR4 = new javax.swing.JLabel();
        lblSR5 = new javax.swing.JLabel();
        lblGame = new javax.swing.JLabel();
        lblYourSc = new javax.swing.JLabel();
        lblPScore = new javax.swing.JLabel();
        butBack2 = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        jButton2.setText("jButton2");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bricky");
        setName("Main menu"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(551, 320));

        lblUser.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblUser.setText("User");
        lblUser.setName("username"); // NOI18N

        lblLevel.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblLevel.setText("Level");

        expBar.setBackground(new java.awt.Color(255, 255, 255));
        expBar.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        expBar.setForeground(new java.awt.Color(0, 0, 255));
        expBar.setMaximum(1000);
        expBar.setValue(578);
        expBar.setString("0/1000");
        expBar.setStringPainted(true);

        lblDate.setText("Date :");

        txtDate.setText("date");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(527, 207));

        pnlMainMenu.setPreferredSize(new java.awt.Dimension(527, 207));

        lblMainMenu.setText("Main Menu");

        butPlay.setText("Mulai Bermain");
        butPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPlayActionPerformed(evt);
            }
        });

        butLdbrd.setText("Leaderboards");
        butLdbrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLdbrdActionPerformed(evt);
            }
        });

        butExit.setText("Keluar");
        butExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainMenuLayout = new javax.swing.GroupLayout(pnlMainMenu);
        pnlMainMenu.setLayout(pnlMainMenuLayout);
        pnlMainMenuLayout.setHorizontalGroup(
            pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainMenuLayout.createSequentialGroup()
                .addGroup(pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMainMenu))
                    .addGroup(pnlMainMenuLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butLdbrd, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                            .addComponent(butExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlMainMenuLayout.setVerticalGroup(
            pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMainMenu)
                .addGap(18, 18, 18)
                .addComponent(butPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butLdbrd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pnlPilihGame.setPreferredSize(new java.awt.Dimension(527, 207));

        lblPilihGame.setText("Pilih Permainan:");

        butGame1.setText("Quiz");
        butGame1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGame1ActionPerformed(evt);
            }
        });

        lblScore1.setText("Score:");

        butGame2.setText("Time Rush");
        butGame2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGame2ActionPerformed(evt);
            }
        });

        lblScore2.setText("Score:");

        butGame3.setText("Riddle");
        butGame3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGame3ActionPerformed(evt);
            }
        });

        lblScore3.setText("Score:");

        butGame4.setText("Memorizing");
        butGame4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGame4ActionPerformed(evt);
            }
        });

        lblScore4.setText("Score:");

        butBack.setText("Back");
        butBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPilihGameLayout = new javax.swing.GroupLayout(pnlPilihGame);
        pnlPilihGame.setLayout(pnlPilihGameLayout);
        pnlPilihGameLayout.setHorizontalGroup(
            pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPilihGameLayout.createSequentialGroup()
                .addGroup(pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPilihGameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPilihGame)
                            .addGroup(pnlPilihGameLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(butBack)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlPilihGameLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPilihGameLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblScore1)
                                    .addComponent(lblScore2)))
                            .addComponent(butGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(butGame2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(butGame3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlPilihGameLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(lblScore3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(butGame4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlPilihGameLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(lblScore4))))))
                .addGap(55, 55, 55))
        );
        pnlPilihGameLayout.setVerticalGroup(
            pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPilihGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPilihGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPilihGameLayout.createSequentialGroup()
                        .addComponent(butGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScore1))
                    .addGroup(pnlPilihGameLayout.createSequentialGroup()
                        .addComponent(butGame3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScore3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPilihGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPilihGameLayout.createSequentialGroup()
                        .addComponent(butGame2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScore2))
                    .addGroup(pnlPilihGameLayout.createSequentialGroup()
                        .addComponent(butGame4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScore4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlLdrboard.setPreferredSize(new java.awt.Dimension(525, 207));

        lblLdboard.setText("Leaderboard");

        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRank.setText("Rank");

        lblRank1.setText("Name");

        jLabel2.setText("Score");

        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl1.setText("1");

        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl2.setText("2");

        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl3.setText("3");

        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl4.setText("4");

        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl5.setText("5");

        butPrev.setText("Prev");
        butPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPrevActionPerformed(evt);
            }
        });

        butNext.setText("Next");
        butNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNextActionPerformed(evt);
            }
        });

        lblName1.setText("Nama 1");

        lblName2.setText("Nama 2");

        lblName3.setText("Nama 3");

        lblName4.setText("Nama 4");

        lblName5.setText("Nama 5");

        lblSR1.setText("0");

        lblSR2.setText("0");

        lblSR3.setText("0");

        lblSR4.setText("0");

        lblSR5.setText("0");

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRank, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRank1)
                            .addComponent(lblName1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(lblSR1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblSR2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSR3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSR4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSR5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addComponent(butPrev)
                        .addGap(18, 18, 18)
                        .addComponent(butNext)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRank)
                    .addComponent(lblRank1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(lblName1)
                    .addComponent(lblSR1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2)
                    .addComponent(lblName2)
                    .addComponent(lblSR2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3)
                    .addComponent(lblName3)
                    .addComponent(lblSR3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4)
                    .addComponent(lblName4)
                    .addComponent(lblSR4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl5)
                    .addComponent(lblName5)
                    .addComponent(lblSR5))
                .addGap(18, 18, 18)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(butNext, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblGame.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblGame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGame.setText("Permainan");

        lblYourSc.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblYourSc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYourSc.setText("Your Score :");

        lblPScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPScore.setText("0");

        butBack2.setText("Back");
        butBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBack2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLdrboardLayout = new javax.swing.GroupLayout(pnlLdrboard);
        pnlLdrboard.setLayout(pnlLdrboardLayout);
        pnlLdrboardLayout.setHorizontalGroup(
            pnlLdrboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLdrboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLdrboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLdboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGame, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(lblYourSc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPScore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlLdrboardLayout.createSequentialGroup()
                        .addComponent(butBack2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlLdrboardLayout.setVerticalGroup(
            pnlLdrboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLdrboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLdrboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlLdrboardLayout.createSequentialGroup()
                        .addComponent(lblLdboard)
                        .addGap(25, 25, 25)
                        .addComponent(lblGame, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblYourSc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPScore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butBack2)))
                .addContainerGap())
        );

        jLayeredPane1.setLayer(pnlMainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pnlPilihGame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pnlLdrboard, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(pnlPilihGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(pnlLdrboard, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlPilihGame, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlLdrboard, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLevel)
                                .addGap(18, 18, 18)
                                .addComponent(expBar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDate)))
                        .addGap(18, 18, 18)
                        .addComponent(txtDate)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(lblDate)
                    .addComponent(txtDate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel)
                    .addComponent(expBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void butExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_butExitActionPerformed

    private void butPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPlayActionPerformed
        play();
    }//GEN-LAST:event_butPlayActionPerformed

    private void butBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBackActionPerformed
        panelControl(1);
    }//GEN-LAST:event_butBackActionPerformed

    private void butLdbrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLdbrdActionPerformed
        try {
            leaderboard();
        } catch (SQLException ex) {
            Logger.getLogger(Main_menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_butLdbrdActionPerformed

    private void butNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNextActionPerformed
        count++;
        if(count == 5)
        count = 1;
        try {
            rankGame();
        } catch (SQLException ex) {
            Logger.getLogger(Main_menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_butNextActionPerformed

    private void butPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPrevActionPerformed
        count--;
        if(count == 0)
        count = 4;
        try {
            rankGame();
        } catch (SQLException ex) {
            Logger.getLogger(Main_menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_butPrevActionPerformed

    private void butBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBack2ActionPerformed
        panelControl(1);
    }//GEN-LAST:event_butBack2ActionPerformed

    private void butGame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGame1ActionPerformed
        int respon = JOptionPane.showConfirmDialog(this, "You want to play Quiz game.\n\n Are you ready?", "Bricky Quiz", JOptionPane.YES_NO_OPTION);
        if(respon == JOptionPane.YES_OPTION){
            Gameplay qu = new Gameplay(pl, 1); 
            qu.pack();
            qu.setVisible(true);
            this.setVisible(false);
        }
            
    }//GEN-LAST:event_butGame1ActionPerformed

    private void butGame2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGame2ActionPerformed
        int respon = JOptionPane.showConfirmDialog(this, "You want to play Time Rush game.\n\n Are you ready?", "Bricky Time Rush", JOptionPane.YES_NO_OPTION);
        if(respon == JOptionPane.YES_OPTION){
            Gameplay tr = new Gameplay(pl, 2); 
            tr.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_butGame2ActionPerformed

    private void butGame4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGame4ActionPerformed
        int respon = JOptionPane.showConfirmDialog(this, "You want to play Memorizing game.\n\n Are you ready?", "Bricky Memorizing", JOptionPane.YES_NO_OPTION);
        if(respon == JOptionPane.YES_OPTION){
            Gameplay tr = new Gameplay(pl, 4); 
            tr.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_butGame4ActionPerformed

    private void butGame3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGame3ActionPerformed
        int respon = JOptionPane.showConfirmDialog(this, "You want to play Riddle game.\n\n Are you ready?", "Bricky Riddle", JOptionPane.YES_NO_OPTION);
        if(respon == JOptionPane.YES_OPTION){
            Gameplay tr = new Gameplay(pl, 3); 
            tr.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_butGame3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butBack;
    private javax.swing.JButton butBack2;
    private javax.swing.JButton butExit;
    private javax.swing.JButton butGame1;
    private javax.swing.JButton butGame2;
    private javax.swing.JButton butGame3;
    private javax.swing.JButton butGame4;
    private javax.swing.JButton butLdbrd;
    private javax.swing.JButton butNext;
    private javax.swing.JButton butPlay;
    private javax.swing.JButton butPrev;
    private javax.swing.JProgressBar expBar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblGame;
    private javax.swing.JLabel lblLdboard;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblMainMenu;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblName3;
    private javax.swing.JLabel lblName4;
    private javax.swing.JLabel lblName5;
    private javax.swing.JLabel lblPScore;
    private javax.swing.JLabel lblPilihGame;
    private javax.swing.JLabel lblRank;
    private javax.swing.JLabel lblRank1;
    private javax.swing.JLabel lblSR1;
    private javax.swing.JLabel lblSR2;
    private javax.swing.JLabel lblSR3;
    private javax.swing.JLabel lblSR4;
    private javax.swing.JLabel lblSR5;
    private javax.swing.JLabel lblScore1;
    private javax.swing.JLabel lblScore2;
    private javax.swing.JLabel lblScore3;
    private javax.swing.JLabel lblScore4;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblYourSc;
    private javax.swing.JPanel pnlLdrboard;
    private javax.swing.JPanel pnlMainMenu;
    private javax.swing.JPanel pnlPilihGame;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JLabel txtDate;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
