import Controller.Controler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class StartPageContraller extends VBox{

   int i=2;
   int lenguage=0;
     @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField player1;

    @FXML
    private TextField player2;

     @FXML
    private Button button; 
    @FXML
    private TextField player3;

    @FXML
    private TextField player4;

    @FXML
    private ToggleGroup setcountPlayer;

    @FXML
    private ToggleGroup setlenguage;

    @FXML
    private TitledPane x1;

    

    @FXML
    void setEnglish(MouseEvent event) {
        lenguage=1;
    }

    @FXML
    void setPolish(MouseEvent event) {
    lenguage=0;
            }
    
    @FXML
    void setfour(MouseEvent event) {
        i=4;
        player1.setDisable(false);
        player2.setDisable(false);
        player3.setDisable(false);
        player4.setDisable(false);
    }

    @FXML
    void setone(MouseEvent event) {
        i=1;
        player1.setDisable(false);
        player2.setDisable(true);
        player3.setDisable(true);
        player4.setDisable(true);
    
    }

    @FXML
    void setthree(MouseEvent event) {
        i=3;
        player1.setDisable(false);
        player2.setDisable(false);
        player3.setDisable(false);
        player4.setDisable(true);
    
    }

    @FXML
    void settwo(MouseEvent event) {
        i=2;
        player1.setDisable(false);
        player2.setDisable(false);
        player3.setDisable(true);
        player4.setDisable(true);
    
    }

    @FXML
    void startGame(MouseEvent event) throws IOException, Exception {
        String []tab={player1.getText(),player2.getText(),player3.getText(),player4.getText()};
        Controler contr=new Controler(tab,i,lenguage);
        contr.start(new Stage());
        Stage stage=(Stage) button.getScene().getWindow();
        stage.close();
        
    }

    
}
