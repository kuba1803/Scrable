/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

import Controller.Coordinates;
import Controller.Pair;
import Model.Board;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Dictionary {
    Map<String,Integer> map = new HashMap<String,Integer>();
    int size;
    public Dictionary() throws IOException
    {
        Scanner scr=new Scanner(new File("src/dictionary/slowa-win.txt"));
        int i=0;
        while(scr.hasNext())
        {
            map.put(scr.next(), 0);
            i++;
        }
        size=i;
    }
     public boolean cheack(List<Pair> list, Board board) throws Exception
     {
         if(!board.get(7, 7).isFull)return false;
         List<Coordinates> start = new ArrayList<Coordinates>();
        for (Pair e : list) {

            if ((e.x > 0 && board.get(e.x - 1, e.y).isFull) || (e.x < 14 && board.get(e.x + 1, e.y).isFull)) {
                start.add(new Coordinates(e.x, e.y, board, 1));
            }
            if ((e.y > 0 && board.get(e.x, e.y - 1).isFull) || (e.y < 14 && board.get(e.x, e.y + 1).isFull)) {
                start.add(new Coordinates(e.x, e.y, board, 2));
            }
            if ((e.x == 0 || !board.get(e.x - 1, e.y).isFull) && (e.x == 14 || !board.get(e.x + 1, e.y).isFull) && (e.y == 0 || !board.get(e.x, e.y - 1).isFull) && (e.y == 14 || !board.get(e.x, e.y + 1).isFull)) {
                start.add(new Coordinates(e.x, e.y, board, 0));
            }
        }
        List<String> words = new ArrayList<String>();
        StringBuilder build=new StringBuilder();
        for(Coordinates e:start)
        {
            if(e.wymiar==0)
            {
             build.append(board.get(e.startx, e.starty).current.Char);
            }
            else if(e.wymiar==1)
            {
                int x=e.startx;
                do
                {
                    build.append(board.get(x, e.starty).current.Char);
                    x++;
                }
                while(x<=e.stopx);
            }
            else
            {
                int y=e.starty;
                do
                {
                    build.append(board.get(e.startx, y).current.Char);
                    y++;
                }
                while(y<=e.stopy);
            }
            words.add(build.toString().toLowerCase());
            build=new StringBuilder();    
        }
        boolean corect=true;
        for(String e:words)
            {
                System.out.println(e);
                if(!map.containsKey(e))
                {
                    corect=false;
                    break;
                }
            }
        return corect;
     }
     /*public static void main(String[] args) throws IOException {
         Scanner scr=new Scanner(System.in);
        Dictionary dir = new Dictionary("C://Users/kuba1_000/Documents/GitHub/Scrable/Scrable/PuzzlePieces/src/dictionary/slowa-win.txt");
       // String a=scr.next();
        System.out.print(dir.map.containsKey("hel"));
        
        
                
    }*/
}
