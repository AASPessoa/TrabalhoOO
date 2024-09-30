/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Entidades;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.ConstantesInimigos.*;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Direcoes.*;



//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class InimigoChefe extends Inimigo{
    
    
    
    public InimigoChefe(float x, float y) {
        super(x, y, TIPO_CHEFE_LARGURA, TIPO_CHEFE_ALTURA, TIPO_CHEFE);
        CriaHitbox( x , y , (int) (22 * Jogo.Escala) , (int) (19 * Jogo.Escala));
        CriaHitboxDeAtaque1( x , y , (int)(80 * Jogo.Escala) , (int)(80 * Jogo.Escala) );
        CriaHitboxDeAtaque2( x , y , (int)(80 * Jogo.Escala) , (int)(80 * Jogo.Escala) );
        ReiniciaHitboxAtaque();
    }
    
    public void Update(int [][] datanivel , Jogador j){
        Comportamento(datanivel , j);
        UpdateAnitick();
        UpdateHitboxDeAtaque();
    }
    
    private void UpdateHitboxDeAtaque(){
        
            if(direcao == DIREITA){
                hitboxataque1.x = hitbox.x;
            }
            else{
                hitboxataque1.x = hitbox.x - hitboxataque1.width + hitbox.width;
            }
            hitboxataque1.y = hitbox.y - hitboxataque1.height + hitbox.height;
        
        
        
            if(direcao == DIREITA){
                hitboxataque2.x = hitbox.x;
            }
            else{
                hitboxataque2.x = hitbox.x - hitboxataque2.width  + hitbox.width;
            }
            hitboxataque2.y = hitbox.y - hitboxataque2.height * 2 + hitbox.height;
        
    }
    
    private void Comportamento(int [][] datanivel , Jogador j){
        if(updateinicial){
            ComecaNoAr(datanivel);
        }
        
        if(noar){
            Cair(datanivel);
        }
        else{
            switch(estadoinimigo){
                case PARADO_INIMIGO:
                    MudaDeEstado(ANDANDO_INIMIGO);
                   
                    break;
                    
                case ANDANDO_INIMIGO:
                    if(PodeVerJogador(datanivel, j)){
                        ViraEmDirecaoAoJogador(j);
                        if(JogadorNoAlcance(j))
                            MudaDeEstado(ATACANDO_INIMIGO);
                    }
                    
                    MovimentoComQueda(datanivel);
                    
                    break;
                    
                case ATACANDO_INIMIGO:
                    if(ataque1){
                        if(aniindex == 0)
                            checouataque = false;
                        if(aniindex == 3 && !checouataque){
                            DaDano(j);
                            ataque1 = false;
                            ataque2 = true;
                        }
                    }
                    
                    if(ataque2){
                        if(aniindex == 0)
                            checouataque = false;
                        if(aniindex == 4 && !checouataque){
                            DaDano(j);
                            ataque2 = false;
                            ataque1 = true;
                        }
                    }
                    
                    break;
                    
                case RECEBENDO_DANO_INIMIGO:
                    break;    
            }
        }
    }
    
    public int ViraX(){
        if(direcao == DIREITA)
            return largura;
        else
            return 0;
    }
    
    public int Vira(){
        if(direcao == DIREITA)
            return -1;
        else
            return 1;
    }
    
}
