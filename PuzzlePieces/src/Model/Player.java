/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Coordinates;
import Controller.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kuba1_000
 */
public class Player {

    public int score;
    public String id;
    public Hand hand;
    public Board tab;

    public Player(String id, Board tab) {
        this.tab = tab;
        score = 0;
        this.id = id;
        hand = new Hand(8);
    }

    public void conter(List<Pair> wspol) {
        List<Coordinates> start = new ArrayList<Coordinates>();
        for (Pair e : wspol) {

            if ((e.x > 0 && tab.get(e.x - 1, e.y).isFull) || (e.x < 14 && tab.get(e.x + 1, e.y).isFull)) {
                start.add(new Coordinates(e.x, e.y, tab, 1));
            }
            if ((e.y > 0 && tab.get(e.x, e.y - 1).isFull) || (e.y < 14 && tab.get(e.x, e.y + 1).isFull)) {
                start.add(new Coordinates(e.x, e.y, tab, 2));
            }
            if ((e.x == 0 || !tab.get(e.x - 1, e.y).isFull) && (e.x == 14 || !tab.get(e.x + 1, e.y).isFull) && (e.y == 0 || !tab.get(e.x, e.y - 1).isFull) && (e.y == 14 || !tab.get(e.x, e.y + 1).isFull)) {
                start.add(new Coordinates(e.x, e.y, tab, 0));
            }
        }
        for(int i=0;i<start.size();i++)
        {
            Coordinates e=start.get(i);
            for(int j=i+1;j<start.size();j++)
            {
                if(start.get(j).equals(start.get(i)))
                {
                    start.remove(j);
                    j--;
                }
            }
        }
        for (Coordinates e : start) {
            int mnoznik = 1;
            int slowo = 0;
            int x = e.startx;
            int y = e.starty;
            if (e.wymiar == 0) {
                if (tab.mnoznik[x][y] > 3) {
                    slowo += tab.get(x, y).current.value * (tab.getx(x, y) - 2);
                } else {
                    slowo += tab.get(x, y).current.value * tab.getx(x, y);
                }
            } else if (e.wymiar == 1) {
                do {
                    if (tab.mnoznik[x][y] > 3) {
                        slowo += tab.get(x, y).current.value;
                        mnoznik *= (tab.getx(x, y) - 2);

                    } else {
                        slowo += tab.get(x, y).current.value * tab.getx(x, y);
                    }
                    x++;
                } while (x <= e.stopx);
            } else {
                do {
                    if (tab.mnoznik[x][y] > 3) {
                        slowo += tab.get(x, y).current.value;
                        mnoznik *= (tab.getx(x, y) - 2);

                    } else {
                        slowo += tab.get(x, y).current.value * tab.getx(x, y);
                    }
                    y++;
                } while (y <= e.stopy);

            }
            slowo *= mnoznik;
            score += slowo;
            mnoznik = 1;
            slowo=0;
        }
        for (Pair e : wspol) {
            tab.setx(e.x, e.y);
        }
    }

}
