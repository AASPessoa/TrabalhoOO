/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.gamestates;

import com.mycompany.trabalhoorientacaoobjetos.Entidades.InimigoManager;
import com.mycompany.trabalhoorientacaoobjetos.Entidades.Jogador;
import com.mycompany.trabalhoorientacaoobjetos.Interface.Pause;
import com.mycompany.trabalhoorientacaoobjetos.Interface.FimdeJogo;
import com.mycompany.trabalhoorientacaoobjetos.Interface.FimdaFase;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.Niveis.FasesManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Jogando extends State implements MetodosState{
    
    private Jogador jogador;
    private FasesManager nivelma;
    private InimigoManager inimigoma;
    private Pause pause;
    private FimdeJogo fimdejogo;
    private FimdaFase fimdafase;
    private BufferedImage fundo;
    
    private boolean pausado = false;
    private boolean fimjogo = false;
    private boolean fimfase = false;
    private boolean jogadormorrendo = false;
    
    private int offsetnivel ;
    private int bordaesq = (int) (0.4 * Jogo.LARGURA_JOGO);
    private int bordadir = (int) (0.5 * Jogo.LARGURA_JOGO);
    private int offsetmaxnivel ;
    
    

    public Jogando(Jogo jogo) {
        super(jogo);
        iniciaClasses();
        calculaOffset();
        carregaFaseInicial();
    }
    
    private void iniciaClasses(){
        nivelma = new FasesManager(this.jogo);
        inimigoma = new InimigoManager(this);
        fundo = LoadSave.getSpriteat(LoadSave.SPRITES_FUNDO_FASE);
        jogador = new Jogador((float) 100 , (float) 100 , (int) (64 * Jogo.ESCALA) , (int) (40 * Jogo.ESCALA) , this);
        jogador.carregaDadosNivel(nivelma.getFaseatual().getDatanivel());
        jogador.setSpawnJogador(nivelma.getFaseatual().getSpawn());
        pause = new Pause(this);
        fimdejogo = new FimdeJogo(this);
        fimdafase = new FimdaFase(this);
    }
        
    @Override
    public void update() {
        
        if(pausado){
            pause.update();
        }
        
        if(fimfase){
            fimdafase.update();
        }
        
        if(fimjogo){
            fimdejogo.update();
        }else if(jogadormorrendo){
            jogador.updateJogador();
        }
        else if(!fimjogo && !fimfase && !pausado){
            nivelma.update();
            jogador.updateJogador();
            inimigoma.update(nivelma.getFaseatual().getDatanivel() , jogador);
            checaDistanciaBorda();
        }
    }

    @Override
    public void draw(Graphics g) {
        
        g.drawImage(fundo, 0, 0, Jogo.LARGURA_JOGO, Jogo.ALTURA_JOGO, null);
        nivelma.draw(g , offsetnivel);
        jogador.desenhaJogador(g , offsetnivel);
        inimigoma.draw(g , offsetnivel);
        
        if(pausado){
            g.setColor(new Color(0 , 0 , 0 , 150));
            g.fillRect(0, 0, Jogo.LARGURA_JOGO, Jogo.ALTURA_JOGO);
            pause.draw(g);
        }
        
        if(fimfase)
            fimdafase.draw(g);
        
        if(fimjogo)
            fimdejogo.draw(g);
        
    }
    
    public void carregaProximaFase(){
        if(!ultimaFase()){
        resetaFase();   
        nivelma.carregaproximoNivel();
        jogador.setSpawnJogador(nivelma.getFaseatual().getSpawn());
        }        
    }
    
    private void carregaFaseInicial(){
        inimigoma.adicionaInimigos(nivelma.getFaseatual());
    }
    
    public boolean ultimaFase(){
        if(nivelma.getNumeroFaseatual() == nivelma.getQuantidadeFases() - 1){
            return true;
        }
        return false;
    }
    
    private void calculaOffset(){
        offsetmaxnivel = nivelma.getFaseatual().getOffsetnivel();
    }
    
    public void checaAcerto(Rectangle2D.Float hitboxataque){
        inimigoma.checaInimigoAcertado(hitboxataque , jogador);
    }
    
    private void checaDistanciaBorda(){
        int xjogador = (int) jogador.getHitbox().x;
        int diferenca = (int) ( xjogador - offsetnivel );
        
        if( diferenca > bordadir){
            offsetnivel += (diferenca - bordadir);
        }
        else{
            if(diferenca < bordaesq){
                offsetnivel += (diferenca - bordaesq);
            }
        }
        
        
        if(offsetnivel > offsetmaxnivel){
            offsetnivel = offsetmaxnivel;
        }
        else{
            if(offsetnivel < 0)
                offsetnivel = 0;
        }
    }
    
    public void despausaJogo(){
        pausado = false;
    }
    
    public void pausaJogo(){
        pausado = true;
    }
    
    public void resetaJogo(){
        fimjogo = false;
        pausado = false;
        fimfase = false;
        jogadormorrendo = false;
        jogador.reiniciaJogador();
        inimigoma.reiniciaInimigos();
        nivelma.resetaNiveis();
    }
    
    public void resetaFase(){
        fimjogo = false;
        pausado = false;
        fimfase = false;
        jogadormorrendo = false;
        jogador.reiniciaJogador();
        inimigoma.reiniciaInimigos();
    }
    
    public void setFimdejogo(boolean f){
        this.fimjogo = f;
    }
    
    public void setOffsetmax(int offset){
        this.offsetmaxnivel = offset;
    }
    
    public void setjorgadormerrendo(boolean m){
        this.jogadormorrendo = m;
    }
    
    public void setFimdefase(boolean a){
        this.fimfase = a;
    }
    
    public Jogador getJogador() {
        return this.jogador;
    }
    
    public InimigoManager getInimigomager(){
        return this.inimigoma;
    }
    
    
    @Override
    public void KeyReleased(KeyEvent e) {
        
        if(!fimjogo){
        switch(e.getKeyCode()){
                
                case KeyEvent.VK_A:
                    jogador.setEsquerda(false);
                    break;
                    
                case KeyEvent.VK_D:
                    jogador.setDireita(false);
                    break;
                    
                case KeyEvent.VK_L:
                    jogador.setPulando(false);
                    break;
            }
        }
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        
        if(fimjogo)
            fimdejogo.KeyPressed(e);
        else{
        switch(e.getKeyCode()){
                
                case KeyEvent.VK_A:
                    jogador.setEsquerda(true);
                    break;
                                        
                case KeyEvent.VK_D:
                    jogador.setDireita(true);
                    break;
                    
                case KeyEvent.VK_J:
                    jogador.setAtacando(true);
                    break;
                    
                case KeyEvent.VK_L:
                    jogador.setPulando(true);
                    break;
                    
                case KeyEvent.VK_ENTER:
                    pausado = !pausado;
                    
                    if(fimfase)
                        fimdafase.KeyPressed(e);
                                
                    break;
                    
                case KeyEvent.VK_ESCAPE:
                    if(pausado)
                        pause.KeyPressed(e);
                    
                    if(fimfase)
                        fimdafase.KeyPressed(e);
                                
                    break;
                    
                case KeyEvent.VK_SPACE:
                    if(pausado)
                        pause.KeyPressed(e);
                    
                    break;    
        }  
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
         
    

    
}
