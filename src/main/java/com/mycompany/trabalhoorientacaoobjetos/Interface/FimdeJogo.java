/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Interface;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Jogando;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Interface.Pause.PAUSE_TAMANHO;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class FimdeJogo {
    
    private final Jogando jogando;
    private BotoesPause menu , reinicia;
    private BufferedImage imgfundo;
    private int bgx , bgy , bgl , bga;
    
    public FimdeJogo(Jogando j){
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
        int proxy = 300;
        int menuy = 200;
        int bx = (int) (390 * Jogo.ESCALA);
        
        reinicia = new BotoesPause(bx , proxy , PAUSE_TAMANHO , PAUSE_TAMANHO , 0);
        menu = new BotoesPause(bx , menuy , PAUSE_TAMANHO , PAUSE_TAMANHO , 2);
        
    }
    
    public void update(){
        menu.update();
        reinicia.update();
    }
    
    public void draw(Graphics g){
        String b = "PRESSIONE ESC PARA VOLTAR AO MENU";
        String c = "PRESSIONE ENTER PARA REINICIAR A FASE"; 
        g.setColor(new Color(0 , 0 , 0 , 230));
        g.fillRect(0, 0, Jogo.LARGURA_JOGO, Jogo.ALTURA_JOGO);
        g.drawImage(imgfundo, bgx, bgy, bgl, bga, null);
        g.setColor(Color.WHITE);
        g.drawString(b, 290 , 190);
        g.drawString(c, 290 , 290);
        menu.draw(g);
        reinicia.draw(g);
    }
    
    public void KeyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                    GameStates.state = GameStates.MENU;
                    jogando.resetaJogo();
                    jogando.despausaJogo();
                    break;
                    
            case KeyEvent.VK_ENTER:
                    jogando.resetaFase();
                    jogando.despausaJogo();
                    break;        
        }
    }
}
