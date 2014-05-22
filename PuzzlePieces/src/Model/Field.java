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
public class Field {
    public Tail current;
    public boolean isFull;
    public int mnoznik;
            public Field()
            {
                current=new Tail();
                isFull=false;
            }
            public boolean set(Tail forHend)
            {
                System.out.print(forHend.Char);
                if(isFull)return false;
                current=forHend;
               // current.Char=forHend.Char;
               // current.value=forHend.value;
                isFull=true;
                return true;
            }
            public boolean check()
            {
                return !isFull;
            }
}
