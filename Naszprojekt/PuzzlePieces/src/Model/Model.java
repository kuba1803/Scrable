/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author kuba1_000
 */
public class Model {
    public Table tab;
    public Hand hand;
    public Model()
    {
        tab=new Table(15,15);
        hand=new Hand(8);
        hand.push(new Shallow('A',1));
        hand.push(new Shallow('B',1));
        hand.push(new Shallow('C',1));
        hand.push(new Shallow('D',1));
        hand.push(new Shallow('E',1));
        hand.push(new Shallow('F',1));
        hand.push(new Shallow('G',1));
       
    }
    public boolean set(int i, int x,int y)
    {
        if(tab.get(x, y).check()&&hand.check(i))
        {
            tab.set(hand.pop(i), x, y);
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Model mod =new Model();
    }
}
