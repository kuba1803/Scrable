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
    ArrayList<Shallow> set;
    int size;
    public Bag(char[] tab,int []wartosc)
    {
        size=tab.length;
        set = new ArrayList<Shallow>();
        randomize=new Random();
        for(int i=0;i<tab.length;i++)
        {
            set.add(new Shallow(tab[i],wartosc[i]));
        }
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public Shallow rend()
    {
        if(size==0)return null;
        int i =randomize.nextInt(size);
        size--;
        Shallow tmp=set.get(i);
        set.remove(i);
        return tmp;
        
    }
}
