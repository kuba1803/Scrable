
import Controller.Controler;
import Model.Model;
import View.View;
import Controller.Controler;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author kuba1_000
 */
public class Main extends Application{
    private static Controler Controler;
    
   /* public static void main(String[] args) throws Exception {
        
    Controler control= new Controler();
       control.start();
    }*/
    @Override public void start(Stage primaryStage) throws Exception {
        
        Controler control= new Controler();
       control.start(primaryStage);
    }
}
