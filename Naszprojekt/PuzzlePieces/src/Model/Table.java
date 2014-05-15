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
public class Table
{
     Field[][] table;
     int [][] mnoznik;
     public Table(int x,int y)
     {
         mnoznik=new int [x][y];
         table= new Field[x][y];
         for(int i=0;i<x;i++)
         {
             for(int j=0;j<y;j++)table[i][j]=new Field();
         }
     }
     public void set(Shallow a, int x, int y)
     {
         table[x][y].set(a);
     }
     public Field get(int x,int y)
     {
         return table[x][y];
     }
     public boolean check(int x,int y)
    {
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
}
