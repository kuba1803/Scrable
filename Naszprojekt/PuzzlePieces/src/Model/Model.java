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
    public Player player;
    private int count; 
    private Bag bag;
    private static final char []set={' ',' ',
    'A','A','A','A','A','A','A','A','A',
    'E','E','E','E','E','E','E',
    'I','I','I','I','I','I','I','I',
    'N','N','N','N','N',
    'O','O','O','O','O','O',
    'R','R','R','R',
    'S','S','S','S',
    'W','W','W','W',
    'Z','Z','Z','Z','Z',
    'C','C','C',
    'D','D','D',
    'K','K','K',
    'L','L','L',
    'M','M','M',
    'P','P','P',
    'T','T','T',
    'Y','Y','Y','Y',
    'B','B','G','G','H','H','J','J','Ł','Ł','U','U','Ą','Ę','F','Ó','Ś','Ż','Ć','Ń','Ź'};
    private static final int [] swartosc={0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
    ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2
    ,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,6,7,9}; 
    public Model(int players)
    {
        tab=new Table(15,15);
        count=players;
        player=new Player("Aga",tab);
        bag=new Bag(set,swartosc);
        for(int i=0;i<8;i++)
        {
            player.hand.push(bag.rend());
        }
       
    }
    public boolean set(int i, int x,int y)
    {
        if(tab.get(x, y).check()&&player.hand.check(i))
        {
            tab.set(player.hand.pop(i), x, y);
            return true;
        }
        return false;
    }
    /*public static void main(String[] args) {
        Model mod =new Model();
        for(int i=0;i<8;i++)
        {
            System.out.print(mod.player.hand.hand[i].current.Char);
        }
    }*/
}
