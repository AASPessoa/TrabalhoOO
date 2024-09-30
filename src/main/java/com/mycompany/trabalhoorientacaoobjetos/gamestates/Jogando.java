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
    private int bordaesq = (int) (0.4 * Jogo.LarguraDoJogo);
    private int bordadir = (int) (0.5 * Jogo.LarguraDoJogo);
    private int offsetmaxnivel ;
    
    

    public Jogando(Jogo jogo) {
        super(jogo);
        IniciaClasses();
        Calculaoffset();
        CarregaFaselInicial();
    }
    
    private void IniciaClasses(){
        nivelma = new FasesManager(this.jogo);
        inimigoma = new InimigoManager(this);
        fundo = LoadSave.getspriteat(LoadSave.fundonivel);
        jogador = new Jogador((float) 100 , (float) 100 , (int) (64 * Jogo.Escala) , (int) (40 * Jogo.Escala) , this);
        jogador.CarregaDadosNivel(nivelma.getnivelatual().getdatanivel());
        jogador.SetSpawnJogador(nivelma.getnivelatual().getspawn());
        pause = new Pause(this);
        fimdejogo = new FimdeJogo(this);
        fimdafase = new FimdaFase(this);
    }
        
    @Override
    public void Update() {
        
        if(pausado){
            pause.Update();
        }
        
        if(fimfase){
            fimdafase.Update();
        }
        
        if(fimjogo){
            fimdejogo.Update();
        }else if(jogadormorrendo){
            jogador.UpdateJogador();
        }
        else if(!fimjogo && !fimfase && !pausado){
            nivelma.Update();
            jogador.UpdateJogador();
            inimigoma.Update(nivelma.getnivelatual().getdatanivel() , jogador);
            ChecaDistanciDaBorda();
        }
    }

    @Override
    public void Draw(Graphics g) {
        
        g.drawImage(fundo, 0, 0, Jogo.LarguraDoJogo, Jogo.AlturaDoJogo, null);
        nivelma.Draw(g , offsetnivel);
        jogador.DesenhaJogador(g , offsetnivel);
        inimigoma.Draw(g , offsetnivel);
        
        if(pausado){
            g.setColor(new Color(0 , 0 , 0 , 150));
            g.fillRect(0, 0, Jogo.LarguraDoJogo, Jogo.AlturaDoJogo);
            pause.Draw(g);
        }
        
        if(fimfase)
            fimdafase.Draw(g);
        
        if(fimjogo)
            fimdejogo.Draw(g);
        
    }
    
    public void CarregaProximaFase(){
        nivelma.CarregaProximoNivel();
        jogador.SetSpawnJogador(nivelma.getnivelatual().getspawn());
        ResetaJogo();
    }
    
    private void CarregaFaselInicial(){
        inimigoma.AdicionaInimigos(nivelma.getnivelatual());
    }
    
    private void Calculaoffset(){
        offsetmaxnivel = nivelma.getnivelatual().getoffsetnivel();
    }
    
    public void ChecaAcerto(Rectangle2D.Float hitboxataque){
        inimigoma.ChecaInimigoAcertado(hitboxataque , jogador);
    }
    
    private void ChecaDistanciDaBorda(){
        int xjogador = (int) jogador.GetHitbox().x;
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
    
    public void DespausaJogo(){
        pausado = false;
    }
    
    public void PausaJogo(){
        pausado = true;
    }
    
    public void ResetaJogo(){
        fimjogo = false;
        pausado = false;
        fimfase = false;
        jogadormorrendo = false;
        jogador.ReiniciaJogador();
        inimigoma.ReiniciaOsInimigos();
        nivelma.ResetaNiveis();
    }
    
    public void ResetaFase(){
        fimjogo = false;
        pausado = false;
        fimfase = false;
        jogadormorrendo = false;
        jogador.ReiniciaJogador();
        inimigoma.ReiniciaOsInimigos();
    }
    
    public void setfimdejogo(boolean f){
        this.fimjogo = f;
    }
    
    public void setoffsetmax(int offset){
        this.offsetmaxnivel = offset;
    }
    
    public void setjorgadormerrendo(boolean m){
        this.jogadormorrendo = m;
    }
    
    public void setfimdefase(boolean a){
        this.fimfase = a;
    }
    
    public Jogador getjogador() {
        return this.jogador;
    }
    
    public InimigoManager getinimigomager(){
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
                    jogador.setpulando(false);
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
                    jogador.setatacando(true);
                    break;
                    
                case KeyEvent.VK_L:
                    jogador.setpulando(true);
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
