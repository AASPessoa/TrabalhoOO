/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Interface;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Interface.Pause.*;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Jogando;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class FimdaFase {
    
    private final Jogando jogando;
    private BotoesPause menu , proximafase;
    private BufferedImage imgfundo;
    private int bgx , bgy , bgl , bga;
    
    public FimdaFase(Jogando j){
        this.jogando = j;
        iniciaImagens();
        criaBotoes();
    }
    
    private void iniciaImagens(){
        imgfundo = LoadSave.getSpriteat(LoadSave.SPRITES_FUNDO_FIM_FASE);
        bgl = (int) (imgfundo.getWidth() * Jogo.ESCALA) ;
        bga = (int) (imgfundo.getHeight() * Jogo.ESCALA) ;
        bgx = (int) (Jogo.LARGURA_JOGO / 2) - (bgl / 2);
        bgy = (int) (10 * Jogo.ESCALA);
    }
    
    private void criaBotoes(){
        int menuy = 200;
        int proxy = 300;
        int bx = (int) (390 * Jogo.ESCALA);
        
        proximafase = new BotoesPause(bx , proxy , PAUSE_TAMANHO , PAUSE_TAMANHO , 0);
        menu = new BotoesPause(bx , menuy , PAUSE_TAMANHO , PAUSE_TAMANHO , 2);
        
    }
    
    public void update(){
        menu.update();
        proximafase.update();
    }
    
    public void draw(Graphics g){
        String a = "FIM DO JOGO PRESSIONE ESC OU ENTER PARA VOLTAR AO MENU";
        String b = "PRESSIONE ESC PARA VOLTAR AO MENU";
        String c = "PRESSIONE ENTER PARA IR PARA A FASE";
        g.drawImage(imgfundo, bgx, bgy, bgl, bga, null);
        g.setColor(Color.WHITE);
        g.drawString(b, 290 , 190);
        g.drawString(c, 290 , 290);
        if(jogando.ultimaFase()){
            g.drawString(a, 250 , 90);
        }
        menu.draw(g);
        proximafase.draw(g);
    }
    
    public void KeyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                    GameStates.state = GameStates.MENU;
                    jogando.resetaJogo();
                    jogando.despausaJogo();
                    break;
                    
            case KeyEvent.VK_ENTER:
                    if(jogando.ultimaFase()){
                        GameStates.state = GameStates.TELAFINAL;
                    }
                    else{
                        jogando.carregaProximaFase();
                        jogando.despausaJogo();
                    }
                    break;        
        }
    }
}
