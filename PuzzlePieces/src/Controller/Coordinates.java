/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board;

/**
 *
 * @author kuba1_000
 */
public class Coordinates {

    public int startx;
    public int starty;
    public int stopx;
    public int stopy;
    public int wymiar;

    public Coordinates(int x, int y, Board board, int i) {
        int k = x;
        int l = y;
        wymiar=i;
        if(i==0)
        {
            startx=x;
            starty=y;
            stopx=x;
            stopy=y;
        }
        else if(i==1)
        {
        while (x != 0 && board.get(x - 1, y).isFull) {
            x--;
        }
        startx = x;
        x=k;
        while(x!=14&&board.get(x+1, y).isFull)
        {
            x++;
        }
        stopx=x;
        stopy=y;
        starty=y;
        }
        else
        {
        while (y != 0 && board.get(x , y-1).isFull) {
            y--;
        }
        starty = y;
        y=l;
        while(y!=14&&board.get(x, y+1).isFull)
        {
            y++;
        }
        stopy=y;
        stopx=x;
        startx=x;
        }
    }
    public boolean equals(Coordinates secend)
    {
        if(wymiar==secend.wymiar&&startx==secend.startx&&starty==secend.starty&&stopx==secend.stopx&&stopy==secend.stopy)return true;
        return false;
    }
}
