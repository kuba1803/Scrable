/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kuba1_000
 */
public class Bag {
    Random randomize;
    ArrayList<Tail> set;
    public int size;
    public Bag(char[] tab,int []wartosc)
    {
        size=tab.length;
        set = new ArrayList<Tail>();
        randomize=new Random();
        for(int i=0;i<tab.length;i++)
        {
            set.add(new Tail(tab[i],wartosc[i],i+1));
        }
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public Tail rend()
    {
        if(size==0)return null;
        int i =randomize.nextInt(size);
        size--;
        Tail tmp=set.get(i);
        set.remove(i);
        return tmp;
    }
    public Tail getTail(int i)
    {
        return set.get(i);
    }
    public void setBack(Tail tail)
    {
        set.add(tail);
        size++;
    }
}
