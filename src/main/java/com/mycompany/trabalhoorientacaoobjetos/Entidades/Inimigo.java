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
    protected float velocidadequeda = 0 , gravidade  = (float) (0.05 * Jogo.Escala);
    protected float velocidadeinimigos = (float) (1.0 * Jogo.Escala);
    protected int direcao = ESQUERDA;
    protected int tiley;
    protected float distanciaataque = Jogo.TamanhoDosTiles;
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
    protected void ReiniciaOsInimigos(){
        vidaatual = vidatotal;
        velocidadequeda = 0;
        updateinicial = true;
        ativo = true;
        hitbox.x = x;
        hitbox.y = y;
        MudaDeEstado(PARADO_INIMIGO);
        ReiniciaHitboxAtaque();
    }
    
    protected void ReiniciaHitboxAtaque(){
        if(estadoinimigo == DIREITA){
            hitboxataque1.x = hitbox.x;
        }
        else{
            hitboxataque1.x = hitbox.x - hitboxataque1.width + hitbox.width;
        }
    }
    
    
    //COMPORTAMENTO
    protected void ComecaNoAr(int[][] datanivel){
        if(!estanochao(this.hitbox , datanivel))
                noar = true;
            updateinicial = false;
    }
    
    protected void MudaDeEstado(int estado){
        this.estadoinimigo = estado;
        anitick = 0;
        aniindex = 0;
    }
    
    
    protected void MudaDeDirecao(){
        if(direcao == ESQUERDA)
            direcao = DIREITA;
        else
            direcao = ESQUERDA;
      
    }
    
    protected void Cair(int[][] datanivel){
        if(podesemover(this.hitbox.x , this.hitbox.y + velocidadequeda, this.hitbox.width , this.hitbox.height , datanivel)){
                hitbox.y += velocidadequeda;
                velocidadequeda += gravidade;
            }
            else{
                noar = false;
                hitbox.y = getychaoteto(this.hitbox, velocidadequeda);
                tiley = (int) (hitbox.y / Jogo.TamanhoDosTiles);
            }
    }
    
    protected void Recua(int[][] datanivel , int direcaoataque){
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
                    
        if(!podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            MudaDeDirecao();
        
        if(!noar){
            if(!estanochao(hitbox , datanivel))
                noar = true;           
        }
        
        if((noar) && (podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel)))
            Cair(datanivel);
                    
        if(podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            this.hitbox.x += velx;
    }
    
    protected void MovimentoSemQueda(int[][] datanivel){
        float velx = 0;
                    
        if(direcao == ESQUERDA)
            velx = -velocidadeinimigos;
        else{ 
            if(direcao == DIREITA)
                velx = velocidadeinimigos;}
                    
        if(!podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            MudaDeDirecao();
                    
        if(ebeirada(hitbox , velx , direcao, datanivel))
            MudaDeDirecao();
                    
        if(podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            this.hitbox.x += velx;
    }
    
    protected void MovimentoComQueda(int[][] datanivel){
        float velx = 0;
                    
        if(direcao == ESQUERDA)
            velx = -velocidadeinimigos;
        else{ 
            if(direcao == DIREITA)
                velx = velocidadeinimigos;}
                    
        if(!podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            MudaDeDirecao();
        
        if(!noar){
            if(!estanochao(hitbox , datanivel))
                noar = true;           
        }
        
        if((noar) && (podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel)))
            Cair(datanivel);
                    
        if(podesemover((float)(this.hitbox.x + velx), this.hitbox.y , this.hitbox.width , this.hitbox.height , datanivel))
            this.hitbox.x += velx;
    }
      
    
    protected boolean PodeVerJogador(int[][] datanivel , Jogador jogador){
        int jogadortiley = (int) (jogador.GetHitbox().y / Jogo.TamanhoDosTiles);
        if(jogadortiley == tiley){
            if(JogadorNaVisao(jogador)){
                if(VisaoEstaLimpa(datanivel , hitbox, jogador.hitbox , tiley)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    protected boolean JogadorNaVisao(Jogador j){
        int distancia = (int) Math.abs(j.hitbox.x - hitbox.x);
        return distancia <= distanciaataque * 5;
    }
    
    protected boolean JogadorNoAlcance(Jogador j){
        int distancia = (int) Math.abs(j.hitbox.x - hitbox.x);
        return distancia <= distanciaataque ;
    }
    
    protected boolean VisaoEstaLimpa(int[][] datanivel , Rectangle2D.Float hitboxi , Rectangle2D.Float hitboxj, int tiley){
        int tilexinimigo = (int) (hitboxi.x / Jogo.TamanhoDosTiles);
        int tilexjogador = (int) (hitboxj.x / Jogo.TamanhoDosTiles);
        
        if(tilexinimigo > tilexjogador){
            for(int i = 0 ; i < (tilexinimigo - tilexjogador) ; i++){
                if(tilesolido(tilexjogador + i, tiley,datanivel))
                    return false;
            }
        }
        else{
            for(int i = 0 ; i < (tilexjogador - tilexinimigo) ; i++){
                if(tilesolido(tilexinimigo + i, tiley,datanivel))
                    return false;
            }
        }
        
        if(tilexinimigo > tilexjogador){
            for(int i = 0 ; i < (tilexinimigo - tilexjogador) ; i++){
                if(tilesolido(tilexjogador , tiley + i ,datanivel))
                    return false;
            }
        }
        else{
            for(int i = 0 ; i < (tilexjogador - tilexinimigo) ; i++){
                if(tilesolido(tilexinimigo , tiley + i ,datanivel))
                    return false;
            }
        }
        
        return true;
    }
    
    protected void ViraEmDirecaoAoJogador(Jogador j){
        if(j.hitbox.x > hitbox.x)
            direcao = DIREITA;
        else
            direcao = ESQUERDA;  
    }
    
    protected void DaDano(Jogador j){
        if(ataque1){
            if(hitboxataque1.intersects(j.hitbox))
                j.RecebeDano(-GetDanoInimigos(tipoinimigo));
            checouataque = true;}
        
        if(ataque2){
            if(hitboxataque2.intersects(j.hitbox))
                j.RecebeDano(-GetDanoInimigos(tipoinimigo));
            checouataque = true;
        }        
    }
    
    protected void RecebeDano(int dano , int[][] datanivel , int direcaoataque){
        this.vidaatual -= dano;
        
        if(vidaatual <= 0)
            MudaDeEstado(MORRENDO_INIMIGO);
        else{
            MudaDeEstado(RECEBENDO_DANO_INIMIGO);
            Recua(datanivel , direcaoataque);
        }
    }
    
    
    
    //ANIMAÇAO
    
    protected void UpdateAnitick() {
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
    
    
    
    public void SetInmigo3Inativo(){
        this.estadoinimigo = MORRENDO_INIMIGO;
    }
    
    public boolean GetInimigoAtivo(){
        return this.ativo;
    }
    
    public int GetVidaInimigo(){
        return this.vidaatual;
    }
    
    public int GetAniindexinimigo(){
        return this.aniindex;
    }
    
    public int GetEstadoInimigo(){
        return this.estadoinimigo;
    }
}
