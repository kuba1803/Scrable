/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Field;
import Model.Shallow;

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
        return contr.model.hand.hand[i];
    }

   

}
