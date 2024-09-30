/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Comandos;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.PanelJogo;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates;
import static com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates.MENU;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class ComandosMouse implements MouseListener, MouseMotionListener{
    
    private final PanelJogo telapanel;
    
    public ComandosMouse(PanelJogo tp){
        this.telapanel = tp;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(GameStates.state){
            case MENU -> telapanel.getjogo().getmenu().mousePressed(e);
                
            default -> {
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(GameStates.state){
            case MENU -> telapanel.getjogo().getmenu().mousePressed(e);
            
            default -> {
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(GameStates.state){
            case MENU -> telapanel.getjogo().getmenu().mouseReleased(e);
                
            default -> {
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(GameStates.state){
            case MENU -> telapanel.getjogo().getmenu().mouseMoved(e);
              
            default -> {
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {   
    }

    
    
}
