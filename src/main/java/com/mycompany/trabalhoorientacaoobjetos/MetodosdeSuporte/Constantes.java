/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoorientacaoobjetos.MetodosdeSuporte;

import com.mycompany.trabalhoorientacaoobjetos.Jogo.Jogo;


//Aluno: Andrei Augusto Silva Pessoa  Matricula: 202265124A


public class Constantes {
    
    public static class Interface{
        
        public static class Botoes {
            public static final int LARGURA_PADRAO_BOTOES = 140;
            public static final int ALTURA_PADRAO_BOTOES = 56;
            public static final int LARGURA_BOTOES = (int) (LARGURA_PADRAO_BOTOES * Jogo.ESCALA);
            public static final int ALTURA_BOTOES = (int) (ALTURA_PADRAO_BOTOES * Jogo.ESCALA);
            
        }
        
        public static class Pause{
            public static final int PAUSE_TAMANHO_PADRAO = 55;
            public static final int PAUSE_TAMANHO = (int) (PAUSE_TAMANHO_PADRAO * Jogo.ESCALA);
        }
    }
    
    public static class Direcoes{
            public static final int ESQUERDA = 0;
            public static final int DIREITA = 2;
    }
    
    public static class ConstantesInimigos{
        
        public static final int TIPO_1 = 0;
        public static final int TIPO_2 = 1;
        public static final int TIPO_3 = 2;
        public static final int TIPO_CHEFE = 3;
        
        public static final int PARADO_INIMIGO = 0;
        public static final int ANDANDO_INIMIGO = 1;
        public static final int ATACANDO_INIMIGO = 2;
        public static final int RECEBENDO_DANO_INIMIGO = 3;
        public static final int MORRENDO_INIMIGO = 4;
        
        public static final int TIPO_1_LARGURA_PADRAO = 72;
        public static final int TIPO_1_ALTURA_PADRAO = 32;
        public static final int TIPO_1_LARGURA =  (int) (TIPO_1_LARGURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_1_ALTURA =  (int) (TIPO_1_ALTURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_1_OFFSET_X = (int) (26 * Jogo.ESCALA);
        public static final int TIPO_1_OFFSET_Y = (int) (9 * Jogo.ESCALA);
        
        public static final int TIPO_2_LARGURA_PADRAO = 72;
        public static final int TIPO_2_ALTURA_PADRAO = 32;
        public static final int TIPO_2_LARGURA =  (int) (TIPO_2_LARGURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_2_ALTURA =  (int) (TIPO_2_ALTURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_2_OFFSET_X = (int) (26 * Jogo.ESCALA);
        public static final int TIPO_2_OFFSET_Y = (int) (9 * Jogo.ESCALA);
        
        public static final int TIPO_3_LARGURA_PADRAO = 72;
        public static final int TIPO_3_ALTURA_PADRAO = 32;
        public static final int TIPO_3_LARGURA =  (int) (TIPO_3_LARGURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_3_ALTURA =  (int) (TIPO_3_ALTURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_3_OFFSET_X = (int) (26 * Jogo.ESCALA);
        public static final int TIPO_3_OFFSET_Y = (int) (9 * Jogo.ESCALA);
        
        public static final int TIPO_CHEFE_LARGURA_PADRAO = 72;
        public static final int TIPO_CHEFE_ALTURA_PADRAO = 32;
        public static final int TIPO_CHEFE_LARGURA =  (int) (TIPO_CHEFE_LARGURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_CHEFE_ALTURA =  (int) (TIPO_CHEFE_ALTURA_PADRAO * Jogo.ESCALA);
        public static final int TIPO_CHEFE_OFFSET_X = (int) (26 * Jogo.ESCALA);
        public static final int TIPO_CHEFE_OFFSET_Y = (int) (9 * Jogo.ESCALA);
        
        public static int GetVidaInimigos(int tipo){
            switch(tipo){
                case TIPO_1:
                    return 10;
                    
                case TIPO_2:
                    return 20;
                    
                case TIPO_3:
                    return 500;
                    
                case TIPO_CHEFE:
                    return 100;
                    
                default: 
                    return 1;
            }
        }
        
        public static int GetDanoInimigos(int tipo){
            switch(tipo){
                case TIPO_1:
                    return 5;
                    
                case TIPO_2:
                    return 10;
                    
                case TIPO_3: 
                    return 100;
                    
                case TIPO_CHEFE:
                    return 20;
                    
                default: 
                    return 0;
            }
        }
        
        public static int GetsSpritesInimigos(int tipo, int estado ){
            
            switch(tipo){
                case TIPO_1:
                    switch(estado){

                        case PARADO_INIMIGO:
                            return 9;

                        case ANDANDO_INIMIGO:
                            return 6;

                        case ATACANDO_INIMIGO:
                            return 6;

                        case RECEBENDO_DANO_INIMIGO:
                            return 4;

                        case MORRENDO_INIMIGO:
                            return 5;

                    }
                    
                case TIPO_2:
                    switch(estado){

                        case PARADO_INIMIGO:
                            return 9;

                        case ANDANDO_INIMIGO:
                            return 6;

                        case ATACANDO_INIMIGO:
                            return 6;

                        case RECEBENDO_DANO_INIMIGO:
                            return 4;

                        case MORRENDO_INIMIGO:
                            return 5;

                    }
                    
                case TIPO_3:
                    switch(estado){

                        case PARADO_INIMIGO:
                            return 9;

                        case ANDANDO_INIMIGO:
                            return 6;

                        case ATACANDO_INIMIGO:
                            return 6;

                        case RECEBENDO_DANO_INIMIGO:
                            return 4;

                        case MORRENDO_INIMIGO:
                            return 5;

                    }    
                    
                case TIPO_CHEFE:
                    switch(estado){

                        case PARADO_INIMIGO:
                            return 9;

                        case ANDANDO_INIMIGO:
                            return 6;

                        case ATACANDO_INIMIGO:
                            return 6;

                        case RECEBENDO_DANO_INIMIGO:
                            return 4;

                        case MORRENDO_INIMIGO:
                            return 5;

                    }
                    
                default: return 0;
            }
            
        }
        
    }
    
    public static class ConstantesJogador{
        
        public static final int PARADO_JOGADOR = 0;
        public static final int ANDANDO_JOGADOR = 1;
        public static final int PULANDO_JOGADOR = 2;
        public static final int CAINDO_JOGADOR = 3;
        public static final int ATACANDO_JOGADOR = 4;
        public static final int RECEBENDO_DANO_JOGADOR = 5;
        public static final int MORRENDO_JOGADOR = 6;
        
        public static int GetSpritesJogador(int acao){
            
            switch(acao){
                
                case MORRENDO_JOGADOR:
                return 8;
                
                case PARADO_JOGADOR:
                    return 5;
                
                case PULANDO_JOGADOR:
                    return 3;
                    
                case CAINDO_JOGADOR:
                    return 1;
                    
                case ANDANDO_JOGADOR:
                    return 6;
                
                case ATACANDO_JOGADOR:
                    return 3;
                    
                case RECEBENDO_DANO_JOGADOR:
                return 4;
                    
                default:
                    return 1;
            }
            
        }
        
    }
}
