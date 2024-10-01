/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public abstract class Entidade {
    
    protected float x,y;
    protected int largura , altura;
    protected Rectangle2D.Float hitbox , hitboxataque1 , hitboxataque2;
    
    public Entidade(float x, float y , int l , int a){
        
        this.x = x;
        this.y = y;
        this.largura = l;
        this.altura = a;
        
    }
    
    protected void criaHitbox(float x , float y , int lar , int alt ){
        hitbox = new Rectangle2D.Float(x , y , lar , alt);
    }
    
    protected void criaHitboxAtaque1(float x , float y , int lar , int alt ){
        hitboxataque1 = new Rectangle2D.Float(x , y , lar , alt);
    }
    
    protected void criaHitboxAtaque2(float x , float y , int lar , int alt ){
        hitboxataque2 = new Rectangle2D.Float(x , y , lar , alt);
    }
    
    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
    
    protected void desenhaHitbox(Graphics g , int offsetnivel )
    {
        g.setColor(Color.BLACK);
        g.drawRect((int)hitbox.x - offsetnivel , (int)hitbox.y , (int)hitbox.width , (int)hitbox.height);
    }
    
    protected void desenhaHitboxAtaque1(Graphics g , int offsetnivel )
    {
        g.setColor(Color.BLACK);
        g.drawRect((int)hitboxataque1.x - offsetnivel , (int)hitboxataque1.y , (int)hitboxataque1.width , (int)hitboxataque1.height);
    }
    
    protected void desenhaHitboxAtaque2(Graphics g , int offsetnivel )
    {
        g.setColor(Color.BLACK);
        g.drawRect((int)hitboxataque2.x - offsetnivel , (int)hitboxataque2.y , (int)hitboxataque2.width , (int)hitboxataque2.height);
    }
    
}
