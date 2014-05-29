/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Player;
import Model.Tail;
import View.View;
import View.View.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kuba1_000
 */
public class Action implements Registered {

    
    Controler contr;
    public Action(Controler a)
    {
        contr=a;
    }
    @Override
    public boolean checkmap(int x, int y) {
        return contr.model.tab.check(x, y);
    }

    @Override
    public void setmap(Tail id, int x, int y,int player, int position) {
        contr.model.player[player].hand.hand[position].isFull=false;
        contr.model.tab.set(id, x, y);
    }

    @Override
    public Tail getTail(int i) {
        return contr.model.bag.getTail(i);
    }

    @Override
    public int getPlayerCount() {
        return contr.model.count; //To change body of generated methods, choose Tools | Templates.
    }
    public Player getPlayer(int i)
    {
        return contr.model.player[i];
    }

    @Override
    public void policz(List<View.Piece> news,int player) {
        List<Pair> wspolrz = new ArrayList<Pair>();
        for(View.Piece e:news)
        {
            wspolrz.add(new Pair(e.x,e.y));
        }
        contr.model.player[player].conter(wspolrz);
        
    }

    @Override
    public int getScore(int i) {
        return contr.model.player[i].score;
    }

    public boolean checkWords(List<Piece> list) {
        List<Pair> wspolrz = new ArrayList<Pair>();
        for(View.Piece e:list)
        {
            wspolrz.add(new Pair(e.x,e.y));
            
        }
        try {
            return contr.dictionary.cheack(wspolrz, contr.model.tab); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int getTailSize() {
        return contr.model.bag.size;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tail getHand(int i,int player) {
        return  contr.model.player[player].hand.pop(i);
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rend(int i) {
        for(int j=0;j<7;j++)
        {
         if(!contr.model.player[i].hand.hand[j].isFull)
         {
             contr.model.player[i].hand.hand[j].current=contr.model.bag.rend();
             contr.model.player[i].hand.hand[j].isFull=true;
         }
        }
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unsetmap(Tail id, int x, int y) {
        contr.model.tab.ret(x, y);
        contr.model.bag.setBack(id);
    }

    @Override
    public void change(Tail tail,int player,int x) {
        contr.model.bag.setBack(tail);
        contr.model.player[player].hand.hand[x].isFull=false;
    }

    @Override
    public int getBonus(int x, int y) {
       return  contr.model.tab.getx(x, y); //To change body of generated methods, choose Tools | Templates.
    }

    


}
