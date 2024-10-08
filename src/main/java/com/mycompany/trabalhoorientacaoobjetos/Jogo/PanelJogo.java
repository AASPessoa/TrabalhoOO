/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Jogo;

import com.mycompany.trabalhoorientacaoobjetos.Comandos.*;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class PanelJogo extends JPanel{       
        
    private final Jogo jogo;
    private final ComandosMouse mouse;
    
    
    public PanelJogo(Jogo j){
        this.jogo = j;
        mouse = new ComandosMouse(this);
        addKeyListener(new ComandosTeclado(this));   
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        setSize();          
    }
        
    private void setSize(){
        Dimension size = new Dimension(Jogo.LARGURA_JOGO,Jogo.ALTURA_JOGO);
        setPreferredSize(size);
    }
       
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        jogo.render(g);
    }

    public Jogo getJogo() {
        return this.jogo;
    }
    
}
