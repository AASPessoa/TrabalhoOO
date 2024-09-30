/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte;

import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Direcoes.*;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import java.awt.geom.Rectangle2D;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class MetodosSuporte {
    

    //movimento
    public static boolean podesemover(float x, float y, float la, float al, int[][] ndata){
        
        if(!esolido(x,y,ndata))
            if(!esolido( x + la , y + al , ndata))
                if(!esolido( x + la , y , ndata))
                    if(!esolido( x , y +al , ndata))
                        return true;
        
        
        return false;
        
    }
    
    private static boolean esolido(float x , float y, int[][] ndata){
        
        int larguranivel = (ndata[0].length * Jogo.TamanhoDosTiles);
        
        if( x < 0 || x >= larguranivel)
            return true;
        
        if( y < 0 || y >= Jogo.AlturaDoJogo)
            return true;
        
        float xindex = x / Jogo.TamanhoDosTiles;
        float yindex = y / Jogo.TamanhoDosTiles;
        
        return tilesolido((int)xindex , (int)yindex , ndata);
    }
    
    public static boolean tilesolido(int tilex, int tiley , int[][] ndata){
        int a = ndata[tiley][tilex];
        
        if(a >= 48 || a < 0 || a != 11)
            return true;
        
        
        return false;
    }
    
    public static boolean ebeirada(Rectangle2D.Float hitbox , float xvel , int direcao, int[][] ndata){
        if(direcao == ESQUERDA)
            if(!esolido(hitbox.x - xvel , hitbox.y + hitbox.height + 1 , ndata))
                return true;
        
        if(direcao == DIREITA)
            if(!esolido(hitbox.x + xvel + hitbox.width , hitbox.y + hitbox.height + 1 , ndata))
                return true;
        
        return false;
    }
    
    public static boolean estanochao(Rectangle2D.Float hitbox, int[][] ndata){
        if(!esolido(hitbox.x , hitbox.y + hitbox.height + 1 , ndata))
            if(!esolido(hitbox.x + hitbox.width , hitbox.y + hitbox.height + 1 , ndata))
                return false;
        
        return true;
    }
    
    public static float getxparede(Rectangle2D.Float hitbox, float xvel){
        
        int tileatual = (int) hitbox.x / Jogo.TamanhoDosTiles;
        
        if(xvel > 0){
            //direita
            int postilex = tileatual * Jogo.TamanhoDosTiles;
            int offsetx = (int) (Jogo.TamanhoDosTiles - hitbox.width);
            return postilex + offsetx - 1;
        } else {
            //esquerda
            return tileatual * Jogo.TamanhoDosTiles;
        }
        
    }
    
    public static float getychaoteto(Rectangle2D.Float hitbox, float velocidadear){
        int tileatual = (int) hitbox.y / Jogo.TamanhoDosTiles;
        
        if(velocidadear > 0){
            //caindo e no chao
            int postiley = tileatual * Jogo.TamanhoDosTiles;
            int offsety = (int) (Jogo.TamanhoDosTiles - hitbox.height);
            return postiley + offsety - 1;
        } else{
            //pulando
            return tileatual * Jogo.TamanhoDosTiles;    
        }
        
    }
    
}
