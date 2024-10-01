/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Entidades;

import static com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.Constantes.ConstantesInimigos.*;
import com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte.LoadSave;
import com.mycompany.trabalhoorientacaoobjetos.Niveis.Fase;
import com.mycompany.trabalhoorientacaoobjetos.gamestates.Jogando;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class InimigoManager {
    
    private final Jogando jogando;
    private BufferedImage[][] inimigo1 , inimigo2 , inimigo3 , inimigoc;
    private Fase faseatual;
    
    public InimigoManager(Jogando j){
        this.jogando = j;
        carregaImagensInimigos();        
    }
    
    public void adicionaInimigos(Fase n){
        this.faseatual = n;
    }
    
    public void update(int [][] datanivel , Jogador j){
        boolean algumativo = false;
        
        for(Inimigo1 i1 : faseatual.getInimigo1()){
            if(i1.getInimigoAtivo()){
                i1.update(datanivel , j);
                algumativo = true;
            }
        }
        
        for(Inimigo2 i2 : faseatual.getInimigo2()){
            if(i2.getInimigoAtivo()){
                i2.update(datanivel , j);
                algumativo = true;
            }
        }
        
        for(InimigoChefe ic : faseatual.getInimigoC()){
            if(ic.getInimigoAtivo()){
                ic.update(datanivel , j);
                algumativo = true;
            }
        }
        
        for(Inimigo3 i3 : faseatual.getInimigo3()){
            if(!algumativo){
                i3.setInimigo3Inativo();
            }
            
            if(i3.getInimigoAtivo()){
                i3.Update(datanivel , j);
                algumativo = true;
            }
            
        }
                
        if(!algumativo)
            jogando.setFimdefase(true);
    }
    
    public void draw(Graphics g , int offsetnivel){
        desenhaInimigos1(g , offsetnivel);
        desenhaInimigos2(g , offsetnivel);
        desenhaInimigos3(g , offsetnivel);
        desenhaInimigosC(g , offsetnivel);
    }
    
    public void desenhaInimigos1(Graphics g , int offsetnivel){
        for(Inimigo1 i1: faseatual.getInimigo1()){
            if(i1.getInimigoAtivo()){
                g.drawImage(inimigo1[i1.getEstadoInimigo()][i1.getAniindexinimigo()], (int) (i1.getHitbox().x - offsetnivel - TIPO_1_OFFSET_X) + i1.viraX() , (int) (i1.getHitbox().y - TIPO_1_OFFSET_Y),TIPO_1_LARGURA * i1.vira() , TIPO_1_ALTURA, null);
                //i1.DesenhaHitbox(g , offsetnivel);
                //i1.DesenhaHitboxDeAtaque1(g , offsetnivel);
            }
        }
    }
    
    public void desenhaInimigos2(Graphics g , int offsetnivel){
        for(Inimigo2 i2: faseatual.getInimigo2()){
            if(i2.getInimigoAtivo()){
                g.drawImage(inimigo2[i2.getEstadoInimigo()][i2.getAniindexinimigo()], (int) (i2.getHitbox().x - offsetnivel - TIPO_2_OFFSET_X) + i2.viraX() , (int) (i2.getHitbox().y - TIPO_2_OFFSET_Y),TIPO_2_LARGURA * i2.vira() , TIPO_2_ALTURA, null);
                //i2.DesenhaHitbox(g , offsetnivel);
                //i2.DesenhaHitboxDeAtaque1(g , offsetnivel);
            }
        }
    }
    
    public void desenhaInimigos3(Graphics g , int offsetnivel){
        for(Inimigo3 i3: faseatual.getInimigo3()){
            if(i3.getInimigoAtivo()){
                g.drawImage(inimigo3[i3.getEstadoInimigo()][i3.getAniindexinimigo()], (int) (i3.getHitbox().x - offsetnivel - TIPO_3_OFFSET_X) + i3.viraX() , (int) (i3.getHitbox().y - TIPO_3_OFFSET_Y),TIPO_3_LARGURA * i3.vira() , TIPO_3_ALTURA, null);
                //i3.DesenhaHitbox(g , offsetnivel);
                //i3.DesenhaHitboxDeAtaque1(g , offsetnivel);
            }
        }
    }
    
    public void desenhaInimigosC(Graphics g , int offsetnivel){
        for(InimigoChefe ic: faseatual.getInimigoC()){
            if(ic.getInimigoAtivo()){
                g.drawImage(inimigoc[ic.getEstadoInimigo()][ic.getAniindexinimigo()], (int) (ic.getHitbox().x - offsetnivel - TIPO_CHEFE_OFFSET_X) + ic.viraX() , (int) (ic.getHitbox().y - TIPO_CHEFE_OFFSET_Y),TIPO_CHEFE_LARGURA * ic.vira() , TIPO_CHEFE_ALTURA, null);
                //ic.DesenhaHitbox(g , offsetnivel);
                //ic.DesenhaHitboxDeAtaque1(g , offsetnivel);
                //ic.DesenhaHitboxDeAtaque2(g , offsetnivel);
            }
        }
    }
    
    private void carregaImagensInimigos(){
        inimigo1 = getImagensInimigos(LoadSave.getSpriteat(LoadSave.SPRITES_INIMIGO_1), 9, 5, TIPO_1_LARGURA_PADRAO, TIPO_1_ALTURA_PADRAO);
        inimigo2 = getImagensInimigos(LoadSave.getSpriteat(LoadSave.SPRITES_INIMIGO_2), 9, 5, TIPO_2_LARGURA_PADRAO, TIPO_2_ALTURA_PADRAO);
        inimigo3 = getImagensInimigos(LoadSave.getSpriteat(LoadSave.SPRITES_INIMIGO_3), 9, 5, TIPO_3_LARGURA_PADRAO, TIPO_3_ALTURA_PADRAO);
        inimigoc = getImagensInimigos(LoadSave.getSpriteat(LoadSave.SPRITES_INIMIGO_CHEFE), 9, 5, TIPO_CHEFE_LARGURA_PADRAO, TIPO_CHEFE_ALTURA_PADRAO);
    }
    
    private BufferedImage[][] getImagensInimigos(BufferedImage sprite, int x, int y, int spritelar, int spritealt){
        BufferedImage[][] temp = new BufferedImage[y][x];
        
        for(int j = 0 ; j < temp.length ; j++){
            for(int i = 0 ; i < temp[j].length ; i++)
                temp[j][i] = sprite.getSubimage((i * spritelar) , (j * spritealt), spritelar , spritealt);
        }
        
        return temp;
    }
    
    public void checaInimigoAcertado(Rectangle2D.Float hitboxataque , Jogador j){
        for(Inimigo1 i1 : faseatual.getInimigo1()){
            if(i1.getInimigoAtivo()){
                if(i1.getVidaInimigo() > 0){
                    if(hitboxataque.intersects(i1.getHitbox())){
                        i1.recebeDano(5 , faseatual.getDatanivel() , j.getDirecaoAtaque());
                    }
                }
            }
        }
        
        for(Inimigo2 i2 : faseatual.getInimigo2()){
            if(i2.getInimigoAtivo()){
                if(i2.getVidaInimigo() > 0){
                    if(hitboxataque.intersects(i2.getHitbox())){
                        i2.recebeDano(5 , faseatual.getDatanivel() , j.getDirecaoAtaque());
                    }
                }
            }
        }
        
        for(Inimigo3 i3 : faseatual.getInimigo3()){
            if(i3.getInimigoAtivo()){
                if(i3.getVidaInimigo() > 0){
                    if(hitboxataque.intersects(i3.getHitbox())){
                        i3.recebeDano(5 , faseatual.getDatanivel() , j.getDirecaoAtaque());
                    }
                }
            }
        }
        
        for(InimigoChefe ic : faseatual.getInimigoC()){
            if(ic.getInimigoAtivo()){
                if(ic.getVidaInimigo() > 0){
                    if(hitboxataque.intersects(ic.getHitbox())){
                        ic.recebeDano(5 , faseatual.getDatanivel() ,  j.getDirecaoAtaque());
                    }
                }
            }
        }
    }
    
    public void reiniciaInimigos(){
        for(Inimigo1 i1 : faseatual.getInimigo1())
            i1.reiniciaInimigos();
        
        for(Inimigo2 i2 : faseatual.getInimigo2())
            i2.reiniciaInimigos();
        
        for(Inimigo3 i3 : faseatual.getInimigo3())
            i3.reiniciaInimigos();
        
        for(InimigoChefe ic : faseatual.getInimigoC())
            ic.reiniciaInimigos();
    }
    
}
