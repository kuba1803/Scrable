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
public class Hand {
    public Field[] hand;
    int isFiald;
    int max;
    
    public Hand(int max)
    {
        hand= new Field[max];
        for(int i=0;i<max;i++)hand[i]=new Field();
        isFiald=0;
        this.max=max;
    }
    public void push(Shallow a)
    {
        hand[isFiald].set(a);
        isFiald++;
    }
    public boolean check(int i)
    {
       return !hand[i].check();
    }
    public Shallow pop(int i)
    {
        hand[i].isFull=false;
        return hand[i].current;
    }
}