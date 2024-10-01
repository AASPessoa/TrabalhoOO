/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Jogo;

import com.mycompany.trabalhoorientacaoobjetos.Interface.Instrucoes;
import com.mycompany.trabalhoorientacaoobjetos.Interface.TelaFinal;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates;
import static com.mycompany.trabalhoorientacaoobjetos.gamestates.GameStates.INSTRUCOES;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Menu;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Jogando;
import java.awt.Graphics;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Jogo implements Runnable{
      
    private final FrameJogo JogoFrame;
    private final PanelJogo JogoPanel;
    private Thread JogoThread;
    private final int FPSset = 60;
    private final int UPSset = 100;
    
    private Jogando jogando;
    private Menu menu;
    private Instrucoes instrucoes;
    private TelaFinal telafinal;
    
    public final static int TAMANHO_PADRAO_TILES = 32;
    public final static float ESCALA = (float) 1.0;
    public final static int TILES_ALTURA = 14;
    public final static int TILES_LARGURA = 26;
    public final static int TAMANHO_TILES = (int)(TAMANHO_PADRAO_TILES * ESCALA);
    public final static int LARGURA_JOGO = (TAMANHO_TILES * TILES_LARGURA);
    public final static int ALTURA_JOGO = (TAMANHO_TILES * TILES_ALTURA);
    
    
    
    public Jogo(){
                
        iniciaClasses();
        
        JogoPanel = new PanelJogo(this);
        JogoFrame = new FrameJogo(JogoPanel);
        JogoPanel.setFocusable(true);
        JogoPanel.requestFocus();
               
        gameloop();        
    }
    
    private void iniciaClasses(){
        menu = new Menu(this);
        jogando = new Jogando(this);
        instrucoes = new Instrucoes(this.jogando);
        telafinal = new TelaFinal(this.jogando);
    }
    
            
    private void gameloop(){
        JogoThread = new Thread(this);
        JogoThread.start();
    }
    
    private void update() {
        switch(GameStates.state){
            
            case MENU:
                menu.update();
                break;
                
            case JOGANDO:
                jogando.update();
                break;
                
            case INSTRUCOES:
                instrucoes.update();
                break;
                
            case TELAFINAL:
                telafinal.update();
                break;
            
            case SAIR:
            default:
                System.exit(0);
                break;
            
        }
    }
    
    public void render(Graphics g){
        switch(GameStates.state){
            
            case MENU:
                menu.draw(g);
                break;
                
            case JOGANDO:
                jogando.draw(g);
                break;
                
            case INSTRUCOES:
                instrucoes.draw(g);
                break;
                
            case TELAFINAL:
                telafinal.draw(g);
                break;
                
            default:
                break;
                
                
            
        }
               
    }
    

    @Override
    public void run() {
        
        double timeperframe = 1000000000.0 / FPSset;
        double timeperupdate = 1000000000.0 / UPSset;
        double deltaU = 0;
        double deltaF = 0;
        
        int frames = 0;
        int updates = 0;
        
        long frame = System.nanoTime();
        long time = System.nanoTime();
        
        long check = System.currentTimeMillis();
        
        while(true){
            
            long currenttime = System.nanoTime();
            
            deltaU += (currenttime - time) / timeperupdate;
            deltaF += (currenttime - time) / timeperframe;
            time = currenttime;
            
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            
            if(deltaF >= 1){
               JogoPanel.repaint();
               frames++;
               deltaF--;
            }
                        
            if(System.currentTimeMillis() - check >= 1000){
            check = System.currentTimeMillis();
            System.out.println("FPS:" + frames + " UPS " + updates);
            frames = 0;
            updates = 0;
            }   
        
        }
    }

    
    public Instrucoes getInstrucoes(){
        return instrucoes;
    }

    public TelaFinal getTelafinal(){
        return telafinal;
    }
    
    public Menu getMenu(){
        return menu;
    }
    
    public Jogando getJogando(){
        return jogando;
    }
    
}
