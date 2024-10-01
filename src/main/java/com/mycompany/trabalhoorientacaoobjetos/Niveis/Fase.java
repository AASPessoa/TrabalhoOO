/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Niveis;

import com.mycompany.trabalhoorientacaoobjetos.Entidades.Inimigo1;
import com.mycompany.trabalhoorientacaoobjetos.Entidades.Inimigo2;
import com.mycompany.trabalhoorientacaoobjetos.Entidades.Inimigo3;
import com.mycompany.trabalhoorientacaoobjetos.Entidades.InimigoChefe;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.ConstantesInimigos.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Point;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Fase {
    
    private final BufferedImage img;
    private final int[][] niveldata;
    
    private final ArrayList<Inimigo1> inimigo1 = new ArrayList<>();
    private final ArrayList<Inimigo2> inimigo2 = new ArrayList<>();
    private final ArrayList<Inimigo3> inimigo3 = new ArrayList<>();
    private final ArrayList<InimigoChefe> chefe = new ArrayList<>();
    
    private int larguraniveltiles;
    private int offsetmaxtiles;
    private int offsetmaxnivel;
    private Point spawn;
    
    public Fase(BufferedImage img){
        this.img = img;
        niveldata = new int[img.getHeight()][img.getWidth()];
        criaFase();
        calculaOffsets();                
    }
    
    private void criaFase() {
        
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x ++){
                
                Color cor = new Color(img.getRGB(x, y));
                int r = cor.getRed();
                int g = cor.getGreen();
                int b = cor.getBlue();
                
                carregaCenario(r,x,y);
                carregaInimigos(g,x,y);
                carregaJogador(b,x,y);
            }
        }
        
    }
    
    private void carregaCenario(int r , int x, int y){
        if(r >= 50)
            niveldata[y][x] = 0;
        else
            niveldata[y][x] = r;
    }
    
    private void carregaInimigos(int g , int x, int y) {
        switch(g){
            case TIPO_1:
                inimigo1.add(new Inimigo1(x * Jogo.TAMANHO_TILES , y * Jogo.TAMANHO_TILES));
                break;
                
            case TIPO_2:
                inimigo2.add(new Inimigo2(x * Jogo.TAMANHO_TILES , y * Jogo.TAMANHO_TILES));
                break;
                
            case TIPO_3:
                inimigo3.add(new Inimigo3(x * Jogo.TAMANHO_TILES , y * Jogo.TAMANHO_TILES));
                break;
            
            case TIPO_CHEFE:
                chefe.add(new InimigoChefe(x * Jogo.TAMANHO_TILES , y * Jogo.TAMANHO_TILES));
                break;
        }
        
    }
    
    private void carregaJogador(int b, int x, int y){
        if(b == 0)
            this.spawn = new Point(x * Jogo.TAMANHO_TILES , y * Jogo.TAMANHO_TILES);
    }
     
    public void calculaOffsets() {
        larguraniveltiles = img.getWidth();
        offsetmaxtiles = larguraniveltiles - Jogo.TILES_LARGURA;
        offsetmaxnivel = Jogo.TAMANHO_TILES * offsetmaxtiles;
    }
    
    public int getSpriteindex(int x, int y){
        return niveldata[y][x];
    }
    
    public int[][] getDatanivel(){
        return this.niveldata;
    }
    
    public int getOffsetnivel(){
        return this.offsetmaxnivel;
    }
    
    public ArrayList<Inimigo1> getInimigo1(){
        return this.inimigo1;
    }
    
    public ArrayList<Inimigo2> getInimigo2(){
        return this.inimigo2;
    }
    
    public ArrayList<Inimigo3> getInimigo3(){
        return this.inimigo3;
    }
    
    public ArrayList<InimigoChefe> getInimigoC(){
        return this.chefe;
    }
    
    public Point getSpawn(){
        return this.spawn;
    }
    
}
