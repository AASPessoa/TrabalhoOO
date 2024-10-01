/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class LoadSave {
    
    private Jogo jogo;
    //personagem
    public static final String SPRITES_JOGADOR = "player_sprites.png";
    public static final String SPRITES_VIDA = "barradevida.png";
    //inimigos
    public static final String SPRITES_INIMIGO_1 = "inimigo1.png";
    public static final String SPRITES_INIMIGO_2 = "inimigo2.png";
    public static final String SPRITES_INIMIGO_3 = "inimigo3.png";
    public static final String SPRITES_INIMIGO_CHEFE = "inimigoc.png";
    //fase
    public static final String SPRITES_FUNDO_FASE = "fundofase.png";
    public static final String SPRITES_FASE = "outside_sprites.png";
    //hud
    public static final String SPRITES_FIM_JOGO = "fimdejogo.png";
    public static final String SPRITES_INSTRUCOES = "instrucoes.png";
    public static final String SPRITES_MENU = "menu_background.png";
    public static final String SPRITES_FUNDO_MENU = "background_menu.png";
    public static final String SPRITES_BOTOES_MENU = "botoesmenu.png";
    public static final String SPRITES_FUNDO_PAUSE = "pausefundo.png";
    public static final String SPRITES_BOTOES_PAUSE = "botoespause.png";
    public static final String SPRITES_FUNDO_FIM_FASE = "pausefundo.png";
    
    public static BufferedImage[] getFases(){
        URL url = LoadSave.class.getResource("/fases");
        File arquivo = null;
        
        try {
            arquivo = new File(url.toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File[] arquivos = arquivo.listFiles();
        File[] arquivosordenados = new File[arquivos.length];
        
        for(int i = 0 ; i < arquivosordenados.length ; i++){
            for(int j = 0 ; j < arquivos.length ; j++){
                if(arquivos[j].getName().equals((i) + ".png"))
                    arquivosordenados[i] = arquivos[j];
            }
        }
        
        BufferedImage[] imgs = new BufferedImage[arquivosordenados.length];
        
        for(int i = 0 ; i < imgs.length ; i++){
            try {
                imgs[i] = ImageIO.read(arquivosordenados[i]);
            } catch (IOException ex) {
                Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return imgs;
    }
    
    public static BufferedImage getSpriteat(String nomearquivo){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + nomearquivo);
        
        try {
            img = ImageIO.read(is);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch(IOException e){
                e.printStackTrace();
            }
            } 
        
        return img;
    }
    
    
    
    
    
}
