/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 *
 * @author kuba1_000
 */
public class wsp {
        
        public int x;
        public int y;
        public wsp(int x,int y)
        {
            this.x=x;
            this.y=y;
        }
        
        public boolean equals(wsp a)
        {
            if(x==a.x&&y==a.y)return true;
            else return false;
        }
 
}
