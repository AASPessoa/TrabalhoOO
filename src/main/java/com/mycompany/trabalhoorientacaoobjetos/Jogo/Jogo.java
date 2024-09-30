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
    
    public final static int TamanhoPadraoDosTiles = 32;
    public final static float Escala = (float) 1.0;
    public final static int TilesdeAltura = 14;
    public final static int TilesdeLargura = 26;
    public final static int TamanhoDosTiles = (int)(TamanhoPadraoDosTiles * Escala);
    public final static int LarguraDoJogo = (TamanhoDosTiles * TilesdeLargura);
    public final static int AlturaDoJogo = (TamanhoDosTiles * TilesdeAltura);
    
    
    
    public Jogo(){
                
        IniciaClasses();
        
        JogoPanel = new PanelJogo(this);
        JogoFrame = new FrameJogo(JogoPanel);
        JogoPanel.setFocusable(true);
        JogoPanel.requestFocus();
               
        GameLoop();        
    }
    
    private void IniciaClasses(){
        menu = new Menu(this);
        jogando = new Jogando(this);
        instrucoes = new Instrucoes(this.jogando);
        telafinal = new TelaFinal(this.jogando);
    }
    
            
    private void GameLoop(){
        JogoThread = new Thread(this);
        JogoThread.start();
    }
    
    private void update() {
        switch(GameStates.state){
            
            case MENU:
                menu.Update();
                break;
                
            case JOGANDO:
                jogando.Update();
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
                menu.Draw(g);
                break;
                
            case JOGANDO:
                jogando.Draw(g);
                break;
                
            case INSTRUCOES:
                instrucoes.Draw(g);
                break;
                
                case TELAFINAL:
                telafinal.Draw(g);
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

    
    public Instrucoes getinstrucoes(){
        return instrucoes;
    }

    public TelaFinal gettelafinal(){
        return telafinal;
    }
    
    public Menu getmenu(){
        return menu;
    }
    
    public Jogando getjogando(){
        return jogando;
    }
    
}
