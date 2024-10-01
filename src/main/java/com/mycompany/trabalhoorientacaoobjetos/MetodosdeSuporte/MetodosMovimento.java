/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte;

import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Direcoes.*;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import java.awt.geom.Rectangle2D;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class MetodosMovimento {
    


    public static boolean podeMover(float x, float y, float la, float al, int[][] ndata){
        
        if(!eSolido(x,y,ndata))
            if(!eSolido( x + la , y + al , ndata))
                if(!eSolido( x + la , y , ndata))
                    if(!eSolido( x , y +al , ndata))
                        return true;
        
        
        return false;
        
    }
    
    private static boolean eSolido(float x , float y, int[][] ndata){
        
        int larguranivel = (ndata[0].length * Jogo.TAMANHO_TILES);
        
        if( x < 0 || x >= larguranivel)
            return true;
        
        if( y < 0 || y >= Jogo.ALTURA_JOGO)
            return true;
        
        float xindex = x / Jogo.TAMANHO_TILES;
        float yindex = y / Jogo.TAMANHO_TILES;
        
        return tileSolido((int)xindex , (int)yindex , ndata);
    }
    
    public static boolean tileSolido(int tilex, int tiley , int[][] ndata){
        int a = ndata[tiley][tilex];
        
        if(a >= 48 || a < 0 || a != 11)
            return true;
        
        
        return false;
    }
    
    public static boolean eBeirada(Rectangle2D.Float hitbox , float xvel , int direcao, int[][] ndata){
        if(direcao == ESQUERDA)
            if(!eSolido(hitbox.x - xvel , hitbox.y + hitbox.height + 1 , ndata))
                return true;
        
        if(direcao == DIREITA)
            if(!eSolido(hitbox.x + xvel + hitbox.width , hitbox.y + hitbox.height + 1 , ndata))
                return true;
        
        return false;
    }
    
    public static boolean estaChao(Rectangle2D.Float hitbox, int[][] ndata){
        if(!eSolido(hitbox.x , hitbox.y + hitbox.height + 1 , ndata))
            if(!eSolido(hitbox.x + hitbox.width , hitbox.y + hitbox.height + 1 , ndata))
                return false;
        
        return true;
    }
    
    public static float getxParede(Rectangle2D.Float hitbox, float xvel){
        
        int tileatual = (int) hitbox.x / Jogo.TAMANHO_TILES;
        
        if(xvel > 0){
            //direita
            int postilex = tileatual * Jogo.TAMANHO_TILES;
            int offsetx = (int) (Jogo.TAMANHO_TILES - hitbox.width);
            return postilex + offsetx - 1;
        } else {
            //esquerda
            return tileatual * Jogo.TAMANHO_TILES;
        }
        
    }
    
    public static float getyChaoTeto(Rectangle2D.Float hitbox, float velocidadear){
        int tileatual = (int) hitbox.y / Jogo.TAMANHO_TILES;
        
        if(velocidadear > 0){
            //caindo e no chao
            int postiley = tileatual * Jogo.TAMANHO_TILES;
            int offsety = (int) (Jogo.TAMANHO_TILES - hitbox.height);
            return postiley + offsety - 1;
        } else{
            //pulando
            return tileatual * Jogo.TAMANHO_TILES;    
        }
        
    }
    
}
