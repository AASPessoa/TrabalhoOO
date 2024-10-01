/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Entidades;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.ConstantesInimigos.*;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Direcoes.*;



//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Inimigo2 extends Inimigo{
    
    public Inimigo2(float x, float y) {
        super(x, y, TIPO_2_LARGURA , TIPO_2_ALTURA , TIPO_2);
        criaHitbox( x , y , (int) (22 * Jogo.ESCALA) , (int) (19 * Jogo.ESCALA));
        criaHitboxAtaque1( x , y , (int)(100 * Jogo.ESCALA) , (int)(19 * Jogo.ESCALA) );
        reiniciaHitboxAtaque();
    }
    
    public void update(int [][] datanivel , Jogador j){
        comportamento(datanivel , j);
        updateAnitick();
        updateHitboxAtaque();
    }
    
    private void updateHitboxAtaque(){
        if(direcao == DIREITA){
            hitboxataque1.x = hitbox.x;
        }
        else{
            hitboxataque1.x = hitbox.x - hitboxataque1.width + hitbox.width;
        }
        
        hitboxataque1.y = hitbox.y - hitboxataque1.height + hitbox.height;
    }
    
    private void comportamento(int [][] datanivel , Jogador j){
        if(updateinicial){
            comecanoAr(datanivel);
        }
        
        if(noar){
            cair(datanivel);
        }
        else{
            switch(estadoinimigo){
                case PARADO_INIMIGO:
                    mudaEstado(ANDANDO_INIMIGO);
                   
                    break;
                    
                case ANDANDO_INIMIGO:
                    if(jogadorVisivel(datanivel, j)){
                        viraaoJogador(j);
                        if(jogadorAlcanceAtaque(j))
                            mudaEstado(ATACANDO_INIMIGO);
                    }
                    
                    movimentoComQueda(datanivel);
                    
                    break;
                    
                case ATACANDO_INIMIGO:
                    if(aniindex == 0)
                        checouataque = false;
                    
                    if(aniindex == 3 && !checouataque)
                        inflingeDano(j);
                    
                    break;
                    
                case RECEBENDO_DANO_INIMIGO:
                    break;    
            }
        }
    }
    
    public int viraX(){
        if(direcao == DIREITA)
            return largura;
        else
            return 0;
    }
    
    public int vira(){
        if(direcao == DIREITA)
            return -1;
        else
            return 1;
    }
    
}
