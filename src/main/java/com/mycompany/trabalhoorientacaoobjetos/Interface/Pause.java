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
        carregaFundo();
        criaBotoes();
    }
    
    private void criaBotoes(){
        
        int desy = 140;
        int reiy = 240;
        int menuy = 340;
        int bx = (int) (390 * Jogo.ESCALA);
        
        menu = new BotoesPause(bx , menuy , PAUSE_TAMANHO , PAUSE_TAMANHO , 0);
        reinicia = new BotoesPause(bx , reiy , PAUSE_TAMANHO , PAUSE_TAMANHO , 1);
        despause = new BotoesPause(bx , desy , PAUSE_TAMANHO , PAUSE_TAMANHO , 2);
        
    }
    
    private void carregaFundo(){
        imagemfundo = LoadSave.getSpriteat(LoadSave.SPRITES_FUNDO_PAUSE);
        bgl = (int) (imagemfundo.getWidth() * Jogo.ESCALA) ;
        bga = (int) (imagemfundo.getHeight() * Jogo.ESCALA) ;
        bgx = (int) (Jogo.LARGURA_JOGO / 2) - (bgl / 2);
        bgy = (int) (10 * Jogo.ESCALA);
    }
    
    public void update(){
        menu.update();
        despause.update();
    }
    
    public void draw(Graphics g){

        String b = "PRESSIONE ESC PARA VOLTAR AO MENU";
        String c = "PRESSIONE ESPAÇO PARA REINICIAR A FASE";
        String d = "PRESSIONE ENTER PARA DESPAUSAR O JOGO";
        g.drawImage(imagemfundo, bgx, bgy, bgl, bga, null);
        g.setColor(Color.WHITE);
        g.drawString(b, 290 , 120);
        g.drawString(c, 290 , 220);
        g.drawString(d, 290 , 320);
        menu.draw(g);
        reinicia.draw(g);
        despause.draw(g);
    }
    
    public void KeyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                    GameStates.state = GameStates.MENU;
                    jogando.resetaJogo();
                    jogando.despausaJogo();
                    break;
                    
            case KeyEvent.VK_ENTER:
                    jogando.despausaJogo();
                    break; 
                    
            case KeyEvent.VK_SPACE:
                    jogando.resetaFase();
                    jogando.despausaJogo();
                    break;        
        }
    }
    
    
}
