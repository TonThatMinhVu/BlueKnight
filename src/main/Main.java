package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Bam dau X thi tat luon chuong trinh
        window.setResizable(true);
        //Doi kich thuoc man hinh game
        window.setTitle("HAHAHA");
        //Ten Game
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();//cause window to be sized fit to the referred size and layout fit to its  subcomponent
        //Thieu 3 cai nay thi game ko hien len man hinh duoc
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gp.startGameThread();
    }
}
