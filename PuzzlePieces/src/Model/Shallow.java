/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author kuba1_000
 */
public class Shallow {
    public char Char;
    public int value;
    public Shallow()
    {
        Char='.';
        value=0;
    }
    public Shallow(char a,int  value)
    {
        Char=a;
        this.value=value;
    }
    public char get()
    {
        return Char;
    }
}
