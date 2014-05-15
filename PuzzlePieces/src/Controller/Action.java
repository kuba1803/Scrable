/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Field;
import Model.Player;
import Model.Shallow;
import View.View;
import java.util.ArrayList;
import java.util.List;

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
    public void setmap(Shallow id, int x, int y) {
        contr.model.tab.set(id, x, y);
    }

    @Override
    public Field gethand(int i) {
        return contr.model.player.hand.hand[i];
    }

    @Override
    public int getPlayerCount() {
        return 1; //To change body of generated methods, choose Tools | Templates.
    }
    public Player getPlayer()
    {
        return contr.model.player;
    }

    @Override
    public void policz(List<View.Piece> news,int player) {
        List<wsp> wspolrz = new ArrayList<wsp>();
        for(View.Piece e:news)
        {
            wspolrz.add(new wsp(e.x,e.y));
        }
        contr.model.player.conter(wspolrz);
        
    }

    @Override
    public int getScore(int i) {
        return contr.model.player.score;
    }

   

}
