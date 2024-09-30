/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Interface;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import  static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Interface.Pause.PAUSE_TAMANHO;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Jogando;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Pause {
    
    private BufferedImage imagemfundo;
    private int bgx , bgy , bgl , bga;
    private BotoesPause despause , menu , reinicia;
    private final Jogando jogando;
    
    public Pause(Jogando j){
        this.jogando = j;
        CarregaFundo();
        CriaBotoes();
    }
    
    private void CriaBotoes(){
        
        int desy = 140;
        int reiy = 240;
        int menuy = 340;
        int bx = (int) (390 * Jogo.Escala);
        
        menu = new BotoesPause(bx , menuy , PAUSE_TAMANHO , PAUSE_TAMANHO , 0);
        reinicia = new BotoesPause(bx , reiy , PAUSE_TAMANHO , PAUSE_TAMANHO , 1);
        despause = new BotoesPause(bx , desy , PAUSE_TAMANHO , PAUSE_TAMANHO , 2);
        
    }
    
    private void CarregaFundo(){
        imagemfundo = LoadSave.getspriteat(LoadSave.pause);
        bgl = (int) (imagemfundo.getWidth() * Jogo.Escala) ;
        bga = (int) (imagemfundo.getHeight() * Jogo.Escala) ;
        bgx = (int) (Jogo.LarguraDoJogo / 2) - (bgl / 2);
        bgy = (int) (10 * Jogo.Escala);
    }
    
    public void Update(){
        menu.Update();
        despause.Update();
    }
    
    public void Draw(Graphics g){

        String b = "PRESSIONE ESC PARA VOLTAR AO MENU";
        String c = "PRESSIONE ESPAÇO PARA REINICIAR A FASE";
        String d = "PRESSIONE ENTER PARA DESPAUSAR O JOGO";
        g.drawImage(imagemfundo, bgx, bgy, bgl, bga, null);
        g.setColor(Color.WHITE);
        g.drawString(b, 290 , 120);
        g.drawString(c, 290 , 220);
        g.drawString(d, 290 , 320);
        menu.Draw(g);
        reinicia.Draw(g);
        despause.Draw(g);
    }
    
    public void KeyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                    GameStates.state = GameStates.MENU;
                    jogando.ResetaJogo();
                    jogando.DespausaJogo();
                    break;
                    
            case KeyEvent.VK_ENTER:
                    jogando.DespausaJogo();
                    break; 
                    
            case KeyEvent.VK_SPACE:
                    jogando.ResetaFase();
                    jogando.DespausaJogo();
                    break;        
        }
    }
    
    
}
