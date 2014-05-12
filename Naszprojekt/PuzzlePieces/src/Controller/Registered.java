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
public interface Registered {
    public boolean checkmap(int x, int y);
    public void setmap(Shallow id, int x, int y);
    public Field gethand(int i);
}
