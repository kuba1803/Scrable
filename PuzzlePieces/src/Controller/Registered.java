/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Field;
import Model.Player;
import Model.Tail;
import View.View.Piece;
import java.util.List;

/**
 *
 * @author kuba1_000
 */
public interface Registered {
    public boolean checkmap(int x, int y);
    public void setmap(Tail id, int x, int y,int player, int position);
    public void unsetmap(Tail id, int x, int y);
    public Tail getTail(int i);
    public Player getPlayer(int i);
    public int getPlayerCount();
    public void policz(List<Piece> news,int player);
    public int getScore(int i);
    public boolean checkWords(List<Piece> list);
    public int getTailSize();
    public Tail getHand(int i, int player);
    public void rend(int i);
    public void change(Tail tail);
    public int getBonus(int x,int y);
}
