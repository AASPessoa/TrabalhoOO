/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Jogo;

import javax.swing.JFrame;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class FrameJogo {
    private final JFrame telaJF;
    
    public FrameJogo(PanelJogo telapanel){
        
        telaJF = new JFrame();
        
        telaJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaJF.add(telapanel);
        telaJF.setResizable(false);
        telaJF.pack();
        telaJF.setLocationRelativeTo(null);
        telaJF.setVisible(true);
    }
}
