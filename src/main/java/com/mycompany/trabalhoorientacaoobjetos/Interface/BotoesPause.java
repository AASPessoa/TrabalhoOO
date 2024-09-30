/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Interface;

import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Interface.Pause.PAUSE_TAMANHO;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Interface.Pause.PAUSE_TAMANHO_PADRAO;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class BotoesPause {
    
    private final int imgindex;
    private int index;
    private BufferedImage[] imgs;

    
    protected int x , y ,largura , altura;
    protected Rectangle area;
    
    public BotoesPause(int x, int y, int l, int a , int i ){
        this.x = x;
        this.y = y;
        this.largura = l;
        this.altura = a;
        this.imgindex = i;

        AreaBotoes();
        CarregaImagens();
    }
    
        
    private void CarregaImagens(){
        BufferedImage temp = LoadSave.getspriteat(LoadSave.botoespausefimdefase);
        imgs = new BufferedImage[3];
        for(int i = 0 ; i< imgs.length ; i++){
            imgs[i] = temp.getSubimage(i * PAUSE_TAMANHO_PADRAO , imgindex * PAUSE_TAMANHO_PADRAO , PAUSE_TAMANHO_PADRAO, PAUSE_TAMANHO_PADRAO);
            
        }      
    }
    
    public void Update(){
        
    }
    
    public void Draw(Graphics g){
        g.drawImage(imgs[index], this.x, this.y, PAUSE_TAMANHO , PAUSE_TAMANHO, null);
    }
    
    
    private void AreaBotoes(){
        area = new Rectangle( x , y , largura , altura );
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }
    
        
}
