/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Interface;

import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Interface.Botoes.*;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class BotoesMenu {

    private final int xpos;
    private final int ypos;
    private final int indeximg;
    private int index;
    private final int xoffsetcentro = (LARGURA_BOTOES / 2);
    private boolean mouseapertado , mouseacima;
    private Rectangle areabotao;
    private final GameStates state;
    private BufferedImage[] img;
    
    public BotoesMenu(int x, int y, int i , GameStates s ){
        this.xpos = x;
        this.ypos = y;
        this.indeximg = i;
        this.state = s;
        carregaImagens();
        areaBotao();
    }
    
    private void carregaImagens(){
        img = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteat(LoadSave.SPRITES_BOTOES_MENU);
        for(int i = 0; i < img.length; i++){
            img[i] = temp.getSubimage(i * LARGURA_PADRAO_BOTOES, indeximg * ALTURA_PADRAO_BOTOES, LARGURA_PADRAO_BOTOES , ALTURA_PADRAO_BOTOES);
        }
    }
    
    public void areaBotao(){
        areabotao = new Rectangle( xpos - xoffsetcentro , ypos , LARGURA_BOTOES , ALTURA_BOTOES);
    }
    
    
    public void aplicaGamestate(){
        GameStates.state = this.state;
    }
    
    public void reiniciaBotoes(){
        mouseacima = false;
        mouseapertado = false;
    }
    
    public void update(){
        index = 0;
        
        if(this.mouseacima)
            index = 1;
        
        
        if(this.mouseapertado)
            index = 2;
    }
    
    public void draw(Graphics g){
        g.drawImage(img[index] , xpos - xoffsetcentro , ypos , LARGURA_BOTOES , ALTURA_BOTOES , null);
    }

    
    public boolean isMouseapertado() {
        return mouseapertado;
    }

    public void setMouseapertado(boolean mouseapertado) {
        this.mouseapertado = mouseapertado;
    }

    public boolean isMouseacima() {
        return mouseacima;
    }

    public void setMouseacima(boolean mouseacima) {
        this.mouseacima = mouseacima;
    }

    public Rectangle getAreabotao() {
        return areabotao;
    }

    public void setAreabotao(Rectangle areabotao) {
        this.areabotao = areabotao;
    }
    
    
    
}
