/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Entidades;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.MetodosMovimento.*;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.ConstantesInimigos.*;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.Direcoes.*;
import java.awt.geom.Rectangle2D;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public abstract class  Inimigo extends Entidade{
    
    protected int  estadoinimigo = PARADO_INIMIGO , tipoinimigo;
    protected int anitick , aniindex , anispeed = 15;
    protected boolean updateinicial  = true , noar = false;
    protected float velocidadequeda = 0 , gravidade  = (float) (0.05 * Jogo.ESCALA);
    protected float velocidadeinimigos = (float) (1.0 * Jogo.ESCALA);
    protected int direcao = ESQUERDA;
    protected int tiley;
    protected float distanciaataque = Jogo.TAMANHO_TILES;
    protected int vidatotal;
    protected int vidaatual;
    protected int dano;
    protected boolean ativo = true;
    protected boolean checouataque;
    protected boolean ataque1 = true;
    protected boolean ataque2 = false;
    
    
    public Inimigo(float x, float y, int l, int a , int tipo) {
        super(x, y, l, a);
        this.tipoinimigo = tipo;   
        this.dano = GetDanoInimigos(tipo);
        this.vidatotal = GetVidaInimigos(tipo);
        this.vidaatual = vidatotal;
    }
    
    //RESET
    protected void reiniciaInimigos(){
        vidaatual = vidatotal;
        velocidadequeda = 0;
        updateinicial = true;
        ativo = true;
        hitbox.x = x;
        hitbox.y = y;
        mudaEstado(PARADO_INIMIGO);
        reiniciaHitboxAtaque();
    }
    
    protected void reiniciaHitboxAtaque(){
        if(estadoinimigo == DIREITA){
            hitboxataque1.x = hitbox.x;
        }
        else{
            hitboxataque1.x = hitbox.x - hitboxataque1.width + hitbox.width;
        }
    }
    
    
    //COMPORTAMENTO
    protected void comecanoAr(int[][] datanivel){
        if(!estaChao(this.hitbox , datanivel))
                noar = true;
            updateinicial = false;
    }
    
    protected void mudaEstado(int estado){
        this.estadoinimigo = estado;
        anitick = 0;
        aniindex = 0;
    }
    
    
    protected void mudarDirecao(){
        if(direcao == ESQUERDA)
            direcao = DIREITA;
        else
            direcao = ESQUERDA;
      
    }
    
    protected void cair(int[][] datanivel){
        if(podeMover(this.hitbox.x , this.hitbox.y + velocidadequeda, this.hitbox.width , this.hitbox.height , datanivel)){
                hitbox.y += velocidadequeda;
                velocidadequeda += gravidade;
            }
            else{
                noar = false;
                hitbox.y = getyChaoTeto(this.hitbox, velocidadequeda);
                tiley = (int) (hitbox.y / Jogo.TAMANHO_TILES);
            }
    }
    
    protected void recuar(int[][] datanivel , int direcaoataque){
        float velx = 0;
        int recuo;
        
        if(this.tipoinimigo == 3)
            recuo = 10;
        else
            recuo = 20;
            
        
        if(direcaoataque == 1)
            velx = -velocidadeinimigos * recuo;
        else{ 
            if(direcaoataque == 0)
                velx = velocidadeinimigos * recuo;
        }
                    
        if(!podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            mudarDirecao();
        
        if(!noar){
            if(!estaChao(hitbox , datanivel))
                noar = true;           
        }
        
        if((noar) && (podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel)))
            cair(datanivel);
                    
        if(podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            this.hitbox.x += velx;
    }
    
    protected void movimentoSemQueda(int[][] datanivel){
        float velx = 0;
                    
        if(direcao == ESQUERDA)
            velx = -velocidadeinimigos;
        else{ 
            if(direcao == DIREITA)
                velx = velocidadeinimigos;}
                    
        if(!podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            mudarDirecao();
                    
        if(eBeirada(hitbox , velx , direcao, datanivel))
            mudarDirecao();
                    
        if(podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            this.hitbox.x += velx;
    }
    
    protected void movimentoComQueda(int[][] datanivel){
        float velx = 0;
                    
        if(direcao == ESQUERDA)
            velx = -velocidadeinimigos;
        else{ 
            if(direcao == DIREITA)
                velx = velocidadeinimigos;}
                    
        if(!podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            mudarDirecao();
        
        if(!noar){
            if(!estaChao(hitbox , datanivel))
                noar = true;           
        }
        
        if((noar) && (podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel)))
            cair(datanivel);
                    
        if(podeMover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            this.hitbox.x += velx;
    }
      
    
    protected boolean jogadorVisivel(int[][] datanivel , Jogador jogador){
        int jogadortiley = (int) (jogador.getHitbox().y / Jogo.TAMANHO_TILES);
        if(jogadortiley == tiley){
            if(jogadorDistanciaVisivel(jogador)){
                if(visaoLimpa(datanivel , hitbox, jogador.hitbox , tiley)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    protected boolean jogadorDistanciaVisivel(Jogador j){
        int distancia = (int) Math.abs(j.hitbox.x - hitbox.x);
        return distancia <= distanciaataque * 5;
    }
    
    protected boolean jogadorAlcanceAtaque(Jogador j){
        int distancia = (int) Math.abs(j.hitbox.x - hitbox.x);
        return distancia <= distanciaataque ;
    }
    
    protected boolean visaoLimpa(int[][] datanivel , Rectangle2D.Float hitboxi , Rectangle2D.Float hitboxj, int tiley){
        int tilexinimigo = (int) (hitboxi.x / Jogo.TAMANHO_TILES);
        int tilexjogador = (int) (hitboxj.x / Jogo.TAMANHO_TILES);
        
        if(tilexinimigo > tilexjogador){
            for(int i = 0 ; i < (tilexinimigo - tilexjogador) ; i++){
                if(tileSolido(tilexjogador + i, tiley,datanivel))
                    return false;
            }
        }
        else{
            for(int i = 0 ; i < (tilexjogador - tilexinimigo) ; i++){
                if(tileSolido(tilexinimigo + i, tiley,datanivel))
                    return false;
            }
        }
        
        if(tilexinimigo > tilexjogador){
            for(int i = 0 ; i < (tilexinimigo - tilexjogador) ; i++){
                if(tileSolido(tilexjogador , tiley + i ,datanivel))
                    return false;
            }
        }
        else{
            for(int i = 0 ; i < (tilexjogador - tilexinimigo) ; i++){
                if(tileSolido(tilexinimigo , tiley + i ,datanivel))
                    return false;
            }
        }
        
        return true;
    }
    
    protected void viraaoJogador(Jogador j){
        if(j.hitbox.x > hitbox.x)
            direcao = DIREITA;
        else
            direcao = ESQUERDA;  
    }
    
    protected void inflingeDano(Jogador j){
        if(ataque1){
            if(hitboxataque1.intersects(j.hitbox))
                j.recebeDano(-GetDanoInimigos(tipoinimigo));
            checouataque = true;}
        
        if(ataque2){
            if(hitboxataque2.intersects(j.hitbox))
                j.recebeDano(-GetDanoInimigos(tipoinimigo));
            checouataque = true;
        }        
    }
    
    protected void recebeDano(int dano , int[][] datanivel , int direcaoataque){
        this.vidaatual -= dano;
        
        if(vidaatual <= 0)
            mudaEstado(MORRENDO_INIMIGO);
        else{
            mudaEstado(RECEBENDO_DANO_INIMIGO);
            recuar(datanivel , direcaoataque);
        }
    }
    
    
    
    //ANIMAÇAO
    
    protected void updateAnitick() {
        anitick++;
        if(anitick >= anispeed){
            anitick = 0;
            aniindex++;
            if(aniindex >= GetsSpritesInimigos(tipoinimigo , estadoinimigo)){
                aniindex = 0;
                
                switch(estadoinimigo){
                    case ATACANDO_INIMIGO , RECEBENDO_DANO_INIMIGO -> estadoinimigo = PARADO_INIMIGO;
                                                
                    case MORRENDO_INIMIGO -> ativo = false;
                }
                                
            }
        }
    }
    
    
    
    public void setInimigo3Inativo(){
        this.estadoinimigo = MORRENDO_INIMIGO;
    }
    
    public boolean getInimigoAtivo(){
        return this.ativo;
    }
    
    public int getVidaInimigo(){
        return this.vidaatual;
    }
    
    public int getAniindexinimigo(){
        return this.aniindex;
    }
    
    public int getEstadoInimigo(){
        return this.estadoinimigo;
    }
}
