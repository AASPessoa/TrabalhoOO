/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.gamestates;

import com.mycompany.trabalhoorientacaoobjetos.Interface.BotoesMenu;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Menu extends State implements MetodosState{
    
    private BotoesMenu jogar , sair;
    private final BufferedImage fundomenu;
    private BufferedImage menu;
    private int fmx , fmy , fmla , fmal;
    
    public Menu(Jogo j) {
        super(j);
        fundomenu = LoadSave.getSpriteat(LoadSave.SPRITES_FUNDO_MENU);
        carregaFundo();
        criaBotoes();
    }
    
    private void carregaFundo(){
        menu = LoadSave.getSpriteat(LoadSave.SPRITES_MENU);
        fmla = (int) (menu.getWidth() * Jogo.ESCALA);
        fmal = (int) (menu.getHeight() * Jogo.ESCALA);
        fmx = (int) (Jogo.LARGURA_JOGO / 2) - (fmla / 2);
        fmy = (int) (50 * Jogo.ESCALA);
    }
    
    private void criaBotoes(){
        jogar = new BotoesMenu((int) (Jogo.LARGURA_JOGO / 2) , (int)(180 * Jogo.ESCALA), 0 , GameStates.JOGANDO);
        sair = new BotoesMenu((int) (Jogo.LARGURA_JOGO / 2) , (int)(260 * Jogo.ESCALA), 1 , GameStates.SAIR);
    }
    
    private void reiniciaBotoes(){
        jogar.reiniciaBotoes();
        sair.reiniciaBotoes();
    }
    
    @Override
    public void update() {
        jogar.update();
        sair.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(fundomenu, 0, 0, fundomenu.getWidth(), fundomenu.getHeight(), null);
        
        g.drawImage(menu, fmx, fmy, fmla, fmal, null);
        
        jogar.draw(g);
        sair.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(estaDentro(e,jogar)){
            jogar.setMouseapertado(true);
        }
        
        if(estaDentro(e,sair)){
            sair.setMouseapertado(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(estaDentro(e,jogar)){
            jogar.setMouseapertado(true);
        }
        
        if(estaDentro(e,sair)){
            sair.setMouseapertado(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(estaDentro(e,jogar)){
            if(jogar.isMouseapertado())
                jogar.aplicaGamestate();
        }
        
        if(estaDentro(e,sair)){
            if(sair.isMouseapertado())
                sair.aplicaGamestate();
        }
        
        reiniciaBotoes();
    }
    
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        jogar.setMouseacima(false);
        sair.setMouseacima(false);
        
        if(estaDentro(e,jogar)){
            jogar.setMouseacima(true);
        }
        
        if(estaDentro(e,sair)){
            sair.setMouseacima(true);
        }
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                    GameStates.state = GameStates.INSTRUCOES;
                    break;        
        }
    }

    @Override
    public void KeyReleased(KeyEvent e) {
    }
    
}
