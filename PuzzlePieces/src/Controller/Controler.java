/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Model;
import View.View;
import dictionary.Dictionary;
import java.io.IOException;
import javafx.stage.Stage;

/**
 *
 * @author kuba1_000
 */
public class Controler {
    public Model model;
    public View view;
    public Registered reg;
    public Dictionary dictionary;
   /* public Controler(Model model, View view)
    {
        this.model = model;
        this.view = view;
        this.reg=new Action(this);
        view.setReg(reg);
    }*/
    public Controler() throws IOException
    {
        String []tab={"Aga", "Kuba","gracz","gracz"};
        this.model=new Model(2,tab);
        this.reg=new Action(this);
        this.view=new View(reg);
        this.dictionary = new Dictionary();
    }
    public void start(Stage primaryStage) throws Exception
    {
        view.start(primaryStage);
    }
}
