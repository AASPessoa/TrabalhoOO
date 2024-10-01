/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Entidades;

import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.MetodosMovimento.*;
import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.ConstantesJogador.*;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Jogando;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Jogador extends Entidade{
    
    private final Jogando jogando;
    
    //animacoes
    private BufferedImage[][] animacoes;
    private int anitick , aniindex ;
    private final int anispeed = 15;
    private int virax = 0;
    private int vira = 1;
    
    //movimento
    private int estadojogador = PARADO_JOGADOR;
    private boolean movendo = false ;
    private boolean  esquerda , direita , pulo;
    private final float velocidadejogador = (float) (2.0 * Jogo.ESCALA);
    
    //ataque
    private boolean ataquechecado , dano = false ,  atacando = false;
    private int direcaoataque = 0;
    
    //nivel
    private int [][] ndata;
    
    //hitbox
    private final float xdrawoffset = 21 * Jogo.ESCALA;
    private final float ydrawoffset = 4 * Jogo.ESCALA;
    
    //pulo e gravidade
    private float velocidadear = 0;
    private final float gravidade = (float) (0.1 * Jogo.ESCALA);
    private final float velocidadeaposacertaroteto = (float) (0.4 * Jogo.ESCALA);
    private final float velocidadedepulo = (float)(-4.0 * Jogo.ESCALA);
    private boolean noar = false;
    
    //vida 
    private BufferedImage vidaimg;
    
    private final int barrasx = (int) (10 * Jogo.ESCALA);
    private final int barrasy = (int) (10 * Jogo.ESCALA);
    private final int barraslargura = (int) (192 * Jogo.ESCALA);
    private final int barrasaltura = (int) (58 * Jogo.ESCALA);
    
    private final int barravidax = (int) (48 * Jogo.ESCALA);
    private final int barraviday = (int) (11 * Jogo.ESCALA);
    private final int barravidalargura = (int) (138 * Jogo.ESCALA);
    private final int barravidaaltura = (int) (31 * Jogo.ESCALA);
    
    private final int vidatotal = 100;
    private int vidaatual = vidatotal;
    private int vidalargura = barravidalargura;

    
    
    public Jogador(float x, float y , int la , int al , Jogando j) {
        super(x, y, la, al);
        this.jogando = j;
        carregaAnimacao();
        criaHitbox( x , y , (int)(20 * Jogo.ESCALA) , (int)(27 * Jogo.ESCALA) );
        criaHitboxAtaque1( x , y , (int)(50 * Jogo.ESCALA) , (int)(27 * Jogo.ESCALA) );
        reiniciaHitboxAtaque();
    }
    
    public void setSpawnJogador(Point spawn){
        this.x = spawn.x;
        this.y = spawn.y;
        hitbox.x = x;
        hitbox.y = y;
    }
    
    public void reiniciaJogador(){
        esquerda =  false;
        direita =  false;
        pulo =  false;
        atacando =  false;
        estadojogador = PARADO_JOGADOR;
        vidaatual = vidatotal;
        reinicianoAr();
        hitbox.x = x;
        hitbox.y = y;
        reiniciaHitboxAtaque();
        if(!estaChao(hitbox,ndata))
            noar = true;
        
    }
    
    private void reiniciaHitboxAtaque(){
        if(vira == 1){
            hitboxataque1.x = hitbox.x;
        }
        else{
            hitboxataque1.x = hitbox.x - hitboxataque1.width + hitbox.width;
        }
    }
    
    public void updateJogador(){
        updateVida();    
        
        if(vidaatual <= 0){
            if(estadojogador != MORRENDO_JOGADOR){
                estadojogador = MORRENDO_JOGADOR;
                anitick = 0;
                aniindex = 0;
                jogando.setjorgadormerrendo(true);
            } else if( ( aniindex == (GetSpritesJogador(MORRENDO_JOGADOR) - 1) ) && ( anitick >= (anispeed - 1) ) ){
                jogando.setFimdejogo(true);
            }else
                updateAnitick();
            
            return;
        }
        
        updateHitboxAtaque();
        movimentacao();
        
        if(atacando){
            checaAtaque();
        }
        
        updateAnitick();       
        setAnimacao(); 
        
    }
    
    public void desenhaJogador(Graphics g , int offsetnivel){
        g.drawImage(animacoes[estadojogador][aniindex], (int)(hitbox.x - xdrawoffset) - offsetnivel + virax, (int)(hitbox.y - ydrawoffset), (int)(this.largura * Jogo.ESCALA) * vira , (int)(this.altura * Jogo.ESCALA) , null);
        desenhaVida(g);
        //DesenhaHitbox(g , offsetnivel);
        //DesenhaHitboxDeAtaque1(g , offsetnivel);
    }
    
    private void desenhaVida(Graphics g){
        g.setColor(Color.red);
        g.fillRect(barravidax + barrasx , barraviday + barrasy , vidalargura , barravidaaltura);
        g.drawImage(vidaimg, barrasx , barrasy , barraslargura , barrasaltura , null);
    }
    
    private void updateVida(){
        vidalargura = (int) ((vidaatual / (float) vidatotal) * barravidalargura);
    }
    
    private void updateHitboxAtaque(){
        if(direita){
            hitboxataque1.x = hitbox.x;
        }
        else if(esquerda){
            hitboxataque1.x = hitbox.x - hitboxataque1.width + hitbox.width;
        }
        
        hitboxataque1.y = hitbox.y - hitboxataque1.height + hitbox.height;
    }
    
    private void checaAtaque(){
        if(ataquechecado || aniindex != 1)
            return;
        ataquechecado = true;
        jogando.checaAcerto(hitboxataque1);
    }
    
    public void recebeDano(int a){
        vidaatual += a;
        
        dano = true;
        
        if(vidaatual <= 0){
            vidaatual = 0;

        }
        else
            if(vidaatual >= vidatotal)
                vidaatual = vidatotal;
    }
    
    private void carregaAnimacao() {
        BufferedImage img = LoadSave.getSpriteat(LoadSave.SPRITES_JOGADOR);
            
        animacoes = new BufferedImage[7][8];
        
        for(int j=0; j < animacoes.length;j++){
            for(int i=0; i < animacoes[j].length ;i++){
                animacoes[j][i] = img.getSubimage(i*64,j*40, 64, 40);
                }
            }
        
        vidaimg = LoadSave.getSpriteat(LoadSave.SPRITES_VIDA);
                      
    }
    
    public void carregaDadosNivel(int [][] nd){
        this.ndata = nd;
        if(!estaChao(hitbox,ndata))
            noar = true;
    }
    
    private void updateAnitick() {
        anitick++;
        if(anitick >= anispeed){
            anitick = 0;
            aniindex++;
            if(aniindex >= GetSpritesJogador(estadojogador)){
                aniindex = 0;
                atacando = false;
                ataquechecado = false;
            }
        }
    }
    
    private void resetaAnitick(){
        anitick = 0;
        aniindex = 0;
    }
    
    private void setAnimacao() {
        
        int animacao = estadojogador;
        
        if(dano){
            estadojogador = RECEBENDO_DANO_JOGADOR;
            dano = false;
            if(animacao != RECEBENDO_DANO_JOGADOR){
                aniindex = 0;
                anitick = 0;
                return;
            }
        }
        
        if(movendo)
            estadojogador = ANDANDO_JOGADOR;
        else
            estadojogador = PARADO_JOGADOR;
        
        if(noar){
            if(velocidadear < 0)
                estadojogador = PULANDO_JOGADOR;
            else
                estadojogador = CAINDO_JOGADOR;
        }
        
        if(atacando){
            estadojogador = ATACANDO_JOGADOR;
            if(animacao != ATACANDO_JOGADOR){
                aniindex = 0;
                anitick = 0;
                return;
            }
        }
        
        if(animacao != estadojogador)
            resetaAnitick();
    }

    private void movimentacao() {
       
        movendo = false;
        
        if(pulo)
            pular();
        
        if(!noar)
            if((!esquerda && !direita) || (esquerda && direita))
                return;
        
        float xvel = 0;
        
        if(esquerda){
            xvel -= velocidadejogador;        
            virax = largura;
            vira = -1;
            direcaoataque = 1;
        }
        if(direita){
            xvel += velocidadejogador;
            virax =0;
            vira = 1;
            direcaoataque = 0;
        }
        
        if(!noar){
            if(!estaChao(hitbox,ndata))
                noar = true;           
        }
        
        
        if(noar){
            if(podeMover(hitbox.x , hitbox.y + velocidadear , hitbox.width , hitbox.height , this.ndata)){
                hitbox.y += velocidadear;
                velocidadear += gravidade;
                moveemX(xvel);
            }else{
                hitbox.y = getyChaoTeto(hitbox , velocidadear);
                if(velocidadear > 0)
                    reinicianoAr();
                else
                    velocidadear = velocidadeaposacertaroteto;
                moveemX(xvel);
            }            
        }else
            moveemX(xvel);
        
        movendo = true;
    }
    
    private void pular(){
        if(noar)
            return;
        
        noar = true;
        velocidadear = velocidadedepulo;

    }
    
    private void reinicianoAr(){
        noar = false;
        velocidadear = 0;
    }
    
    private void moveemX(float xvel){
        if(podeMover(hitbox.x + xvel , hitbox.y , hitbox.width , hitbox.height , this.ndata)){
            hitbox.x += xvel;
        } else{
            hitbox.x = getxParede(hitbox,xvel);
        }
    }
    
    public int getDirecaoAtaque() {
        return direcaoataque;
    }
    
    public void setPulando(boolean a){
        this.pulo = a;
    }
    
    public void setAtacando(boolean a){
        this.atacando = a;
    }

    public boolean getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
    }

    public boolean getDireita() {
        return direita;
    }

    public void setDireita(boolean direita) {
        this.direita = direita;
    }
 
}
