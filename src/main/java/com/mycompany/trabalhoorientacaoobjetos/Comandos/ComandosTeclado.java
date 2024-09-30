/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Comandos;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.PanelJogo;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class ComandosTeclado implements KeyListener{

    private final PanelJogo telapanel;
    
    public ComandosTeclado(PanelJogo tp){
        this.telapanel = tp;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch(GameStates.state) {
            case MENU -> telapanel.getjogo().getmenu().KeyPressed(e);
            
            case JOGANDO -> telapanel.getjogo().getjogando().KeyPressed(e);
            
            default -> {
            }
        }
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        switch(GameStates.state) {
            case MENU -> telapanel.getjogo().getmenu().KeyReleased(e);
            
            case JOGANDO -> telapanel.getjogo().getjogando().KeyReleased(e);
            
            default -> {
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

}
