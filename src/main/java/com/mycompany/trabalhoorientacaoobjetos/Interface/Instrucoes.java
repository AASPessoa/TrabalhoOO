/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Interface;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Jogando;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Note2540p
 */
public class Instrucoes {
    
    private final Jogando jogando;
    private BufferedImage imagemfundo;
    private int  bgl , bga;
    
    public Instrucoes(Jogando j){
        this.jogando = j;
        CarregaFundo();
    }
    
    public void update(){
    }
    
    private void CarregaFundo(){
        imagemfundo = LoadSave.getspriteat(LoadSave.instrucoes);
        bgl = (int) (imagemfundo.getWidth() * Jogo.Escala) ;
        bga = (int) (imagemfundo.getHeight() * Jogo.Escala) ;
    }
    
     public void Draw(Graphics g){
        g.drawImage(imagemfundo, 0, 0, bgl, bga, null);    
    }
     
     public void KeyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                    GameStates.state = GameStates.MENU;
                    jogando.ResetaJogo();
                    jogando.DespausaJogo();
                    break;        
        }
    }
}

