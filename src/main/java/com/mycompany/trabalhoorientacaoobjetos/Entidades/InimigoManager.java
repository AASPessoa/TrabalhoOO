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
        CarregaImagensInimigo();        
    }
    
    public void AdicionaInimigos(Fase n){
        this.faseatual = n;
    }
    
    public void Update(int [][] datanivel , Jogador j){
        boolean algumativo = false;
        
        for(Inimigo1 i1 : faseatual.getinimigo1())
            if(i1.GetInimigoAtivo()){
                i1.Update(datanivel , j);
                algumativo = true;
            }
        
        for(Inimigo2 i2 : faseatual.getinimigo2())
            if(i2.GetInimigoAtivo()){
                i2.Update(datanivel , j);
                algumativo = true;
            }
        
        for(Inimigo3 i3 : faseatual.getinimigo3())
            if(i3.GetInimigoAtivo()){
                i3.Update(datanivel , j);
                algumativo = true;
            }
        
        for(InimigoChefe ic : faseatual.getinimigoc())
            if(ic.GetInimigoAtivo()){
                ic.Update(datanivel , j);
                algumativo = true;
            }
                
        if(!algumativo)
            jogando.setfimdefase(true);
    }
    
    public void Draw(Graphics g , int offsetnivel){
        DesenhaOsInimigos1(g , offsetnivel);
        DesenhaOsInimigos2(g , offsetnivel);
        DesenhaOsInimigos3(g , offsetnivel);
        DesenhaOsInimigosc(g , offsetnivel);
    }
    
    public void DesenhaOsInimigos1(Graphics g , int offsetnivel){
        for(Inimigo1 i1: faseatual.getinimigo1()){
            if(i1.GetInimigoAtivo()){
                g.drawImage(inimigo1[i1.GetEstadoInimigo()][i1.GetAniindexinimigo()], (int) (i1.GetHitbox().x - offsetnivel - TIPO_1_OFFSET_X) + i1.ViraX() , (int) (i1.GetHitbox().y - TIPO_1_OFFSET_Y),TIPO_1_LARGURA * i1.Vira() , TIPO_1_ALTURA, null);
                i1.DesenhaHitbox(g , offsetnivel);
                i1.DesenhaHitboxDeAtaque1(g , offsetnivel);
            }
        }
    }
    
    public void DesenhaOsInimigos2(Graphics g , int offsetnivel){
        for(Inimigo2 i2: faseatual.getinimigo2()){
            if(i2.GetInimigoAtivo()){
                g.drawImage(inimigo2[i2.GetEstadoInimigo()][i2.GetAniindexinimigo()], (int) (i2.GetHitbox().x - offsetnivel - TIPO_2_OFFSET_X) + i2.ViraX() , (int) (i2.GetHitbox().y - TIPO_2_OFFSET_Y),TIPO_2_LARGURA * i2.Vira() , TIPO_2_ALTURA, null);
                i2.DesenhaHitbox(g , offsetnivel);
                i2.DesenhaHitboxDeAtaque1(g , offsetnivel);
            }
        }
    }
    
    public void DesenhaOsInimigos3(Graphics g , int offsetnivel){
        for(Inimigo3 i3: faseatual.getinimigo3()){
            if(i3.GetInimigoAtivo()){
                g.drawImage(inimigo3[i3.GetEstadoInimigo()][i3.GetAniindexinimigo()], (int) (i3.GetHitbox().x - offsetnivel - TIPO_3_OFFSET_X) + i3.ViraX() , (int) (i3.GetHitbox().y - TIPO_3_OFFSET_Y),TIPO_3_LARGURA * i3.Vira() , TIPO_3_ALTURA, null);
                i3.DesenhaHitbox(g , offsetnivel);
                i3.DesenhaHitboxDeAtaque1(g , offsetnivel);
            }
        }
    }
    
    public void DesenhaOsInimigosc(Graphics g , int offsetnivel){
        for(InimigoChefe ic: faseatual.getinimigoc()){
            if(ic.GetInimigoAtivo()){
                g.drawImage(inimigoc[ic.GetEstadoInimigo()][ic.GetAniindexinimigo()], (int) (ic.GetHitbox().x - offsetnivel - TIPO_CHEFE_OFFSET_X) + ic.ViraX() , (int) (ic.GetHitbox().y - TIPO_CHEFE_OFFSET_Y),TIPO_CHEFE_LARGURA * ic.Vira() , TIPO_CHEFE_ALTURA, null);
                ic.DesenhaHitbox(g , offsetnivel);
                ic.DesenhaHitboxDeAtaque1(g , offsetnivel);
                ic.DesenhaHitboxDeAtaque2(g , offsetnivel);
            }
        }
    }
    
    private void CarregaImagensInimigo(){
        inimigo1 = GetImagensDosInimigos(LoadSave.getspriteat(LoadSave.inimigosp), 9, 5, TIPO_1_LARGURA_PADRAO, TIPO_1_ALTURA_PADRAO);
        inimigo2 = GetImagensDosInimigos(LoadSave.getspriteat(LoadSave.inimigosp), 9, 5, TIPO_2_LARGURA_PADRAO, TIPO_2_ALTURA_PADRAO);
        inimigo3 = GetImagensDosInimigos(LoadSave.getspriteat(LoadSave.inimigosp), 9, 5, TIPO_3_LARGURA_PADRAO, TIPO_3_ALTURA_PADRAO);
        inimigoc = GetImagensDosInimigos(LoadSave.getspriteat(LoadSave.inimigosp), 9, 5, TIPO_CHEFE_LARGURA_PADRAO, TIPO_CHEFE_ALTURA_PADRAO);
    }
    
    private BufferedImage[][] GetImagensDosInimigos(BufferedImage sprite, int x, int y, int spritelar, int spritealt){
        BufferedImage[][] temp = new BufferedImage[y][x];
        
        for(int j = 0 ; j < temp.length ; j++){
            for(int i = 0 ; i < temp[j].length ; i++)
                temp[j][i] = sprite.getSubimage((i * spritelar) , (j * spritealt), spritelar , spritealt);
        }
        
        return temp;
    }
    
    public void ChecaInimigoAcertado(Rectangle2D.Float hitboxataque , Jogador j){
        for(Inimigo1 i1 : faseatual.getinimigo1()){
            if(i1.GetInimigoAtivo()){
                if(i1.GetVidaInimigo() > 0){
                    if(hitboxataque.intersects(i1.GetHitbox())){
                        i1.RecebeDano(5 , faseatual.getdatanivel() , j.getdirecaoataque());
                    }
                }
            }
        }
        
        for(Inimigo2 i2 : faseatual.getinimigo2()){
            if(i2.GetInimigoAtivo()){
                if(i2.GetVidaInimigo() > 0){
                    if(hitboxataque.intersects(i2.GetHitbox())){
                        i2.RecebeDano(5 , faseatual.getdatanivel() , j.getdirecaoataque());
                    }
                }
            }
        }
        
        for(Inimigo3 i3 : faseatual.getinimigo3()){
            if(i3.GetInimigoAtivo()){
                if(i3.GetVidaInimigo() > 0){
                    if(hitboxataque.intersects(i3.GetHitbox())){
                        i3.RecebeDano(5 , faseatual.getdatanivel() , j.getdirecaoataque());
                    }
                }
            }
        }
        
        for(InimigoChefe ic : faseatual.getinimigoc()){
            if(ic.GetInimigoAtivo()){
                if(ic.GetVidaInimigo() > 0){
                    if(hitboxataque.intersects(ic.GetHitbox())){
                        ic.RecebeDano(5 , faseatual.getdatanivel() ,  j.getdirecaoataque());
                    }
                }
            }
        }
    }
    
    public void ReiniciaOsInimigos(){
        for(Inimigo1 i1 : faseatual.getinimigo1())
            i1.ReiniciaOsInimigos();
        
        for(Inimigo2 i2 : faseatual.getinimigo2())
            i2.ReiniciaOsInimigos();
        
        for(Inimigo3 i3 : faseatual.getinimigo3())
            i3.ReiniciaOsInimigos();
        
        for(InimigoChefe ic : faseatual.getinimigoc())
            ic.ReiniciaOsInimigos();
    }
    
}
