/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Model.Field;

/**
 *
 * @author kuba1_000
 */
public class Board
{
     Field[][] table;
     int [][] mnoznik;
     public Board(int x,int y)
     {
         mnoznik=new int [x][y];
         table= new Field[x][y];
         for(int i=0;i<x;i++)
         {
             for(int j=0;j<y;j++)table[i][j]=new Field();
         }
         mnoznik[0][0]=4;
         mnoznik[7][0]=4;
         mnoznik[0][7]=4;
         mnoznik[14][14]=4;
         mnoznik[14][7]=4;
         mnoznik[7][14]=4;
         mnoznik[0][14]=4;
         mnoznik[14][0]=4;
         mnoznik[6][2]=1;
         mnoznik[8][2]=1;
         mnoznik[7][3]=1;
         mnoznik[6][6]=1;
         mnoznik[8][8]=1;
         mnoznik[8][6]=1;
         mnoznik[6][8]=1;
         mnoznik[6][12]=1;
         mnoznik[8][12]=1;
         mnoznik[7][11]=1;
         mnoznik[0][3]=1;
         mnoznik[0][11]=1;
         mnoznik[14][3]=1;
         mnoznik[14][11]=1;
         mnoznik[3][0]=1;
         mnoznik[11][0]=1;
         mnoznik[3][14]=1;
         mnoznik[11][14]=1;
         for(int i=1;i<14;i+=4)
         {
             mnoznik[5][i]=2;
             mnoznik[9][i]=2;
         }
         for(int i=1;i<14;i++)
         {
             if(i<5||i>9){
             mnoznik[i][i]=3;
             if(i<4){
             if((i==1))
             {
                 mnoznik[i][i+4]=2;
                 mnoznik[i][14-i-4]=2;
             }
             else 
             {
                 mnoznik[i][i+4]=1;
                 mnoznik[i][14-i-4]=1;
             }
             }
             else if(i>10)
             {
                 if(i==13)
                 {
                     mnoznik[i][14-i+4]=2;
                     mnoznik[i][i-4]=2;
                 }
                 else
                 {
                     mnoznik[i][14-i+4]=1;
                     mnoznik[i][i-4]=1;
                 
                 }
                 
             }
             mnoznik[i][14-i]=3;
            /* if(i==1||i==13)mnoznik[i][14-i-3]=2;
             else mnoznik[i][14-i-3]=1;*/
             }
         }
     }
     public void set(Tail a, int x, int y)
     {
         table[x][y].set(a);
     }
     public Field get(int x,int y)
     {
         return table[x][y];
     }
     public boolean check(int x,int y)
    {
        if(x<0||y<0) return false;
        if(x>14||y>14)return false;
       return table[x][y].check();
    }
     public int getx(int x,int y)
     {
         return mnoznik[x][y]+1;
     }
     public void setx(int x,int y)
     {
         mnoznik[x][y]=0;
     }
     public void ret(int x,int y)
     {
         table[x][y].current=null;
         table[x][y].isFull=false;
     }
}
