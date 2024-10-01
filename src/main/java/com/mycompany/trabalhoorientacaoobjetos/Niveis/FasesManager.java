/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Niveis;

import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class FasesManager {
    
    private final Jogo jogo;
    private BufferedImage[] nivelsprite;   
    private final ArrayList<Fase> fases;
    private int indexfase = 0;
    
    public FasesManager(Jogo j){
        this.jogo = j;
        fases = new ArrayList<>();
        importaSprites();
        constroiFases();
    }
    
    private void importaSprites(){
        BufferedImage img = LoadSave.getSpriteat(LoadSave.SPRITES_FASE);
        nivelsprite = new BufferedImage[48];
        
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 12 ; i++){
                int index = j*12 + i;
                nivelsprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
        
    }
    
    private void constroiFases(){
        BufferedImage[] todasasfases = LoadSave.getFases();
        for(BufferedImage img : todasasfases){
            fases.add(new Fase(img));
        }
    }
        
    public void draw(Graphics g , int offsetnivel){
        
        for(int j = 0; j < Jogo.TILES_ALTURA; j++)
            for(int i = 0; i < fases.get(indexfase).getDatanivel()[0].length ; i++){
                int index = fases.get(indexfase).getSpriteindex(i,j);
                g.drawImage(nivelsprite[index], (Jogo.TAMANHO_TILES * i) - offsetnivel , Jogo.TAMANHO_TILES * j, Jogo.TAMANHO_TILES, Jogo.TAMANHO_TILES, null);
            }
        
    }
    
    public void update(){
        
    }
    
    public void carregaproximoNivel(){
        indexfase = indexfase + 1;
        
        if(indexfase == fases.size() - 1){
            return;
        }
        
        Fase seguinte = fases.get(indexfase);
        
        jogo.getJogando().getInimigomager().adicionaInimigos(seguinte);
        jogo.getJogando().getJogador().carregaDadosNivel(seguinte.getDatanivel());
        jogo.getJogando().setOffsetmax(seguinte.getOffsetnivel());
        
    }
    
    public void resetaNiveis(){
        indexfase = 0;
        Fase inicial = fases.get(indexfase);
        jogo.getJogando().getInimigomager().adicionaInimigos(inicial);
        jogo.getJogando().getJogador().carregaDadosNivel(inicial.getDatanivel());
        jogo.getJogando().setOffsetmax(inicial.getOffsetnivel());
    }
    
    public int getQuantidadeFases(){
        return fases.size();
    }
    
    public int getNumeroFaseatual(){
        return this.indexfase;
    }
    
    public Fase getFaseatual(){
        return fases.get(indexfase);
    }
    
}
