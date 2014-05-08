/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

/**
 *
 * @author kuba1_000
 */
public class Field {
    Shallow current;
    boolean isFull;
            public Field()
            {
                current=new Shallow();
                isFull=false;
            }
            public boolean set(Shallow forHend)
            {
                if(isFull)return false;
                current.Char=forHend.Char;
                current.value=forHend.value;
                isFull=true;
                return true;
            }
            public boolean check()
            {
                return !isFull;
            }
}
