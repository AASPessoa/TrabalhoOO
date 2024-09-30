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
    private int indexfase = 5;
    
    public FasesManager(Jogo j){
        this.jogo = j;
        fases = new ArrayList<>();
        ImportaSprites();
        ConstroiFases();
    }
    
    private void ImportaSprites(){
        BufferedImage img = LoadSave.getspriteat(LoadSave.nivelSp);
        nivelsprite = new BufferedImage[48];
        
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 12 ; i++){
                int index = j*12 + i;
                nivelsprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
        
    }
    
    private void ConstroiFases(){
        BufferedImage[] todasasfases = LoadSave.getfases();
        for(BufferedImage img : todasasfases){
            fases.add(new Fase(img));
        }
    }
        
    public void Draw(Graphics g , int offsetnivel){
        
        for(int j = 0; j < Jogo.TilesdeAltura; j++)
            for(int i = 0; i < fases.get(indexfase).getdatanivel()[0].length ; i++){
                int index = fases.get(indexfase).getspriteindex(i,j);
                g.drawImage(nivelsprite[index], (Jogo.TamanhoDosTiles * i) - offsetnivel , Jogo.TamanhoDosTiles * j, Jogo.TamanhoDosTiles, Jogo.TamanhoDosTiles, null);
            }
        
    }
    
    public void Update(){
        
    }
    
    public void CarregaProximoNivel(){
        indexfase = indexfase + 1;
        
        if(indexfase == nivelsprite.length){
            return;
        }
        
        Fase seguinte = fases.get(indexfase);
        
        jogo.getjogando().getinimigomager().AdicionaInimigos(seguinte);
        jogo.getjogando().getjogador().CarregaDadosNivel(seguinte.getdatanivel());
        jogo.getjogando().setoffsetmax(seguinte.getoffsetnivel());
        
    }
    
    public void ResetaNiveis(){
        indexfase = 0;
        Fase inicial = fases.get(indexfase);
        jogo.getjogando().getinimigomager().AdicionaInimigos(inicial);
        jogo.getjogando().getjogador().CarregaDadosNivel(inicial.getdatanivel());
        jogo.getjogando().setoffsetmax(inicial.getoffsetnivel());
    }
    
    public int getquantidadedefases(){
        return fases.size();
    }
    
    public int getnumerodafaseatual(){
        return this.indexfase;
    }
    
    public Fase getnivelatual(){
        return fases.get(indexfase);
    }
    
}
