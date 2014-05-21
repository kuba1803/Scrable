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
public class Tail {
    public char Char;
    public int value;
    public int id;
    public Tail()
    {
        Char='.';
        value=0;
        id=0;
    }
    public Tail(char a,int  value,int id)
    {
        Char=a;
        this.value=value;
        this.id=id;
    }
    public char get()
    {
        return Char;
    }
}
