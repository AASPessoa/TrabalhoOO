/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.gamestates;

import java.awt.Graphics;
import java.awt.event.*;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public interface MetodosState {
    
    public void update();
    
    public void draw(Graphics g);
    
    public void mouseClicked(MouseEvent e);
    
    public void mousePressed(MouseEvent e);
    
    public void mouseReleased(MouseEvent e);
    
    public void mouseMoved(MouseEvent e);
    
    public void KeyPressed(KeyEvent e);
    
    public void KeyReleased(KeyEvent e);
}
