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
        fundomenu = LoadSave.getspriteat(LoadSave.fundomenu);
        CarregaFundoMenu();
        CriaBotoes();
    }
    
    private void CarregaFundoMenu(){
        menu = LoadSave.getspriteat(LoadSave.menu);
        fmla = (int) (menu.getWidth() * Jogo.Escala);
        fmal = (int) (menu.getHeight() * Jogo.Escala);
        fmx = (int) (Jogo.LarguraDoJogo / 2) - (fmla / 2);
        fmy = (int) (50 * Jogo.Escala);
    }
    
    private void CriaBotoes(){
        jogar = new BotoesMenu((int) (Jogo.LarguraDoJogo / 2) , (int)(180 * Jogo.Escala), 0 , GameStates.JOGANDO);
        sair = new BotoesMenu((int) (Jogo.LarguraDoJogo / 2) , (int)(260 * Jogo.Escala), 1 , GameStates.SAIR);
    }
    
    private void ReiniciaBotoes(){
        jogar.ReiniciaBotoes();
        sair.ReiniciaBotoes();
    }
    
    @Override
    public void Update() {
        jogar.Update();
        sair.Update();
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(fundomenu, 0, 0, fundomenu.getWidth(), fundomenu.getHeight(), null);
        
        g.drawImage(menu, fmx, fmy, fmla, fmal, null);
        
        jogar.Draw(g);
        sair.Draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(EstaDentro(e,jogar)){
            jogar.setMouseapertado(true);
        }
        
        if(EstaDentro(e,sair)){
            sair.setMouseapertado(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(EstaDentro(e,jogar)){
            jogar.setMouseapertado(true);
        }
        
        if(EstaDentro(e,sair)){
            sair.setMouseapertado(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(EstaDentro(e,jogar)){
            if(jogar.isMouseapertado())
                jogar.AplicaGamestate();
        }
        
        if(EstaDentro(e,sair)){
            if(sair.isMouseapertado())
                sair.AplicaGamestate();
        }
        
        ReiniciaBotoes();
    }
    
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        jogar.setMouseacima(false);
        sair.setMouseacima(false);
        
        if(EstaDentro(e,jogar)){
            jogar.setMouseacima(true);
        }
        
        if(EstaDentro(e,sair)){
            sair.setMouseacima(true);
        }
    }

    @Override
    public void KeyPressed(KeyEvent e) {

    }

    @Override
    public void KeyReleased(KeyEvent e) {
    }
    
}
