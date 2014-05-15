/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.wsp;
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
    public Table tab;

    public Player(String id, Table tab) {
        this.tab = tab;
        score = 0;
        this.id = id;
        hand = new Hand(8);
    }

    public void conter(List<wsp> wspol) {
        List<wsp> start = new ArrayList<wsp>();
        List<Boolean> kierunek = new ArrayList<Boolean>();
        for (wsp e : wspol) {
            if ((e.x == 0 && e.y == 0) || (e.x == 0 && !tab.get(e.x, e.y - 1).isFull) || (e.y == 0 && !tab.get(e.x - 1, e.y).isFull) || !tab.get(e.x - 1, e.y - 1).isFull) {
                start.add(e);
                start.add(e);
                kierunek.add(Boolean.TRUE);
                kierunek.add(Boolean.TRUE);
            } else {
                int x = e.x;
                int y = e.y;
                if (x != 0 && tab.get(x - 1, y).isFull) {
                    while (x != 0 && tab.get(x - 1, y).isFull) {
                        x--;
                    }
                    start.add(new wsp(x, y));
                    kierunek.add(Boolean.TRUE);
                    x = e.x;
                }
                if (y != 0 && tab.get(x, y - 1).isFull) {
                    {
                        while (y != 0 && tab.get(x, y - 1).isFull) {
                            y--;
                        }
                        start.add(new wsp(x, y));
                        kierunek.add(Boolean.FALSE);
                    }
                }
            }
        }
        for (int i = 0; i < start.size(); i++) {
            for (int j = i + 1; j < start.size(); j++) {
                if (start.get(i).equals(start.get(j))) {
                    start.remove(j);
                    kierunek.remove(j);
                    j--;
                }
            }
        }
        for (int i = 0; i < start.size(); i++) {
            int mnoznik = 1;
            int slowo = 0;
            int x = start.get(i).x;
            int y = start.get(i).y;
            if (kierunek.get(i)) {

                do {
                    if (tab.getx(x, y) != 1) {
                        if (tab.getx(x, y) < 4) {
                            slowo += tab.get(x, y).current.value * tab.getx(x, y);
                        } else {
                            slowo += tab.get(x, y).current.value;
                            mnoznik *= tab.getx(x, y) - 2;
                        }
                    } else {
                        slowo += tab.get(x, y).current.value;
                    }
                    x++;
                } while (x < 15 && tab.get(x, y).isFull);
                slowo*=mnoznik;
            }
            else
            {
                do {
                    if (tab.getx(x, y) != 1) {
                        if (tab.getx(x, y) < 4) {
                            slowo += tab.get(x, y).current.value * tab.getx(x, y);
                        } else {
                            slowo += tab.get(x, y).current.value;
                            mnoznik *= tab.getx(x, y) - 2;
                        }
                    } else {
                        slowo += tab.get(x, y).current.value;
                    }
                    y++;
                } while (y < 15 && tab.get(x, y).isFull);
                slowo*=mnoznik;
            }
            score+=slowo;
            for(wsp e:wspol)
            {
                    tab.setx(e.x, e.y);
            }
        }
        
    }
}
