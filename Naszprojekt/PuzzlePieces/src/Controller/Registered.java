/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Field;
import Model.Player;
import Model.Shallow;
import View.View.Piece;
import java.util.List;

/**
 *
 * @author kuba1_000
 */
public interface Registered {
    public boolean checkmap(int x, int y);
    public void setmap(Shallow id, int x, int y);
    public Field gethand(int i);
    public Player getPlayer();
    public int getPlayerCount();
    public void policz(List<Piece> news,int player);
    public int getScore(int i);
}
