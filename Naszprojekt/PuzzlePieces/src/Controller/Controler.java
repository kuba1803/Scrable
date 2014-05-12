/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Model;
import View.View;
import javafx.stage.Stage;

/**
 *
 * @author kuba1_000
 */
public class Controler {
    public Model model;
    public View view;
    public Registered reg;
    public Controler(Model model, View view)
    {
        this.model = model;
        this.view = view;
        this.reg=new Action(this);
        view.setReg(reg);
    }
    public Controler()
    {
        this.model=new Model();
        this.reg=new Action(this);
        this.view=new View(reg);
    }
    public void start(Stage primaryStage) throws Exception
    {
        view.start(primaryStage);
    }
}
