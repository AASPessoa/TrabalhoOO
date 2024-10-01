/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.gamestates;

import com.mycompany.trabalhoorientacaoobjetos.Interface.BotoesMenu;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import java.awt.event.MouseEvent;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class State {
    
    protected Jogo jogo;
    
    public State(Jogo j){
        this.jogo = j;
    }
    
    public Jogo getJogo(){
        return this.jogo;
    }
    
    public boolean estaDentro(MouseEvent e , BotoesMenu bm){
       return bm.getAreabotao().contains(e.getX(),e.getY());
    }
    
}
