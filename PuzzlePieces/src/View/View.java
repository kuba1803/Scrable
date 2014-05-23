package View;

import Controller.Registered;
import Model.Tail;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application {

    private Timeline timeline;
    private static Piece buf;
    private static Registered reg;
    private boolean[][] matrix;
    private boolean[] isToChange;
    private final user us[];
    private final List<Player> player;
    private final List<Piece> plansza;
    private final List<Piece> change;
    private final List<Piece> onHand;
    int turn;

    public View(Registered a) {
        reg = a;
        us = new user[4];
        plansza = new ArrayList<Piece>();
        change = new ArrayList<Piece>();
        onHand = new ArrayList<Piece>();
        player = new ArrayList<Player>();
        for (int i = 0; i < reg.getPlayerCount(); i++) {
            System.out.print(reg.getPlayer(i).id);
            player.add(new Player(reg.getPlayer(i).score, reg.getPlayer(i).id));
        }
        turn = 0;

    }

    public class user extends Pane {

        int score;
        Text text;
        public Text wyn;

        public user(int i) {
            score = 0;
            Shape tmp = CreateBackground(0, 0, 120, 120, Color.CORNFLOWERBLUE);
            if (i < reg.getPlayerCount()) {
                text = new Text(50, 40, reg.getPlayer(i).id);
                wyn = new Text(50, 60, "" + score);
            } else {
                text = new Text(50, 40, "Puste");
                wyn = new Text(50, 60, "0");
            }
            getChildren().addAll(tmp, text, wyn);
        }
    }

    public static Shape CreateUser(int x, int y, double vectx, double vecty, Paint value, int countPlayer) {
        Text id;
        Shape tmp = CreateBackground(x, y, vectx, vecty, value);
        if (countPlayer < reg.getPlayerCount()) {
            id = new Text(x + vectx / 2, y + vecty / 2, reg.getPlayer(countPlayer).id);
        }

        return null;
    }

    public static Shape CreateBackground(int x, int y, double vectx, double vecty, Paint value) {
        Rectangle background = new Rectangle();
        background.setX(x);
        background.setY(y);
        background.setWidth(vectx);
        background.setHeight(vecty);
        Shape shape = background;
        shape.setFill(value);
        return shape;
    }

    public class bonus extends Pane{
        Shape bonus;
        Text text;
        Text wyn;
        public bonus(int i,int j) {
            System.out.print(reg.getBonus(i, j));
                    if(reg.getBonus(i, j)==2)
                    {
                         bonus =CreateBackground(i*25, j*25, 25, 25, Color.LIGHTBLUE);
                         text = new Text(i*25+4,j*25+10,"lier");
                         wyn = new Text(i*25+6,j*25+20,"x2");
                        getChildren().addAll(bonus, text, wyn);
                    }
                    else if(reg.getBonus(i, j)==3)
                    {
                       bonus =CreateBackground(i*25, j*25, 25, 25, Color.BLUE);
                         text = new Text(i*25+4,j*25+10,"lier");
                         wyn = new Text(i*25+6,j*25+20,"x3");
                        getChildren().addAll(bonus, text, wyn);  
                    }
                    else if( reg.getBonus(i, j)==4)
                    {
                        bonus =CreateBackground(i*25, j*25, 25, 25, Color.LIGHTSALMON);
                         text = new Text(i*25+4,j*25+10,"slo");
                         wyn = new Text(i*25+6,j*25+20,"x2");
                        getChildren().addAll(bonus, text, wyn);
                    }
                    else if(reg.getBonus(i, j)==5)
                    {bonus =CreateBackground(i*25, j*25, 25, 25, Color.SALMON);
                         text = new Text(i*25+4,j*25+10,"slo");
                         wyn = new Text(i*25+6,j*25+20,"x3");
                        getChildren().addAll(bonus, text, wyn);
                    }
        }
        bonus()
        {bonus =CreateBackground(7*25, 7*25, 25, 25, Color.LIGHTSALMON);
                         text = new Text(7*25,7*25+15,"START");
                         text.setFont(Font.font(9));
                         wyn = new Text(7*25+6,7*25+20,"");
                        getChildren().addAll(bonus, text, wyn);
        }
    }

    private void init(final Stage primaryStage) {
        Pane main = new Pane();
        main.getChildren().addAll(CreateBackground(0, 0, 800, 600, Color.LIGHTBLUE/*(41, 57, 73)*/));
        main.setPrefSize(800, 600);
        main.setStyle("-fx-border-color: #464646;");
        buf = null;
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        // load puzzle image
        int numOfColumns = 15;//(int) (image.getWidth() / Piece.SIZE);
        int numOfRows = 15;//(int) (image.getHeight() / Piece.SIZE);
        // create desk
        matrix = new boolean[15][15];
        isToChange = new boolean[7];
        final Desk desk = new Desk(numOfColumns, numOfRows, Color.rgb(0, 153, 0),1);

        // creat-e  pieces
        final Desk hand = new Desk(7, 1, Color.DARKBLUE,0);
        final Desk toChange = new Desk(7, 1, Color.DEEPPINK,0);
        final List<Piece> pieces = new ArrayList<Piece>();
        for (int i = 0; i < reg.getTailSize(); i++) {
            final Piece piece = new Piece(reg.getTail(i), 0, 0,
                    desk.getWidth(), desk.getHeight(), 1);
            piece.toBack();
            pieces.add(piece);
        }
        main.getChildren().addAll(pieces);

        /*   for (int col = 0; col < 7; col++) {
         for (int row = 0; row < 1; row++) {
         int x = col * Piece.SIZE;
         int y = 0;
         final Piece piece = new Piece(reg.gethand(col),x, y,
         desk.getWidth(), desk.getHeight(),1);
         pieces.add(piece);
         onHand.add(piece);
         }
         }*/
        // create vbox for desk and buttons*/
        VBox vb = new VBox(10);
        HBox addictField = new HBox(25);
        addictField.getChildren().addAll(hand, toChange);
        vb.getChildren().addAll(desk, addictField);
        HBox hb = new HBox(50);
        VBox rightvb = new VBox(10);
        HBox user1 = new HBox(10);
        HBox user2 = new HBox(10);
        us[0] = new user(0);
        us[1] = new user(1);
        us[2] = new user(2);
        us[3] = new user(3);
        final Button confirm = new Button("Zatwierdz");
        confirm.setPrefSize(100, 40);
        confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                confirm.setDisable(true);
                for (Piece e : player.get(turn).Hand) {
                    e.toBack();
                }
                for (Piece e : plansza) {
                    System.out.println(e.tail.Char);
                    reg.setmap(e.tail, e.x, e.y, turn, (int) e.correctX / 25);
                    e.setInactive();
                    e.toFront();
                }
                for (Piece e : change) {
                    // e.setInactive();
                    e.toBack();
                    reg.change(e.tail);
                }
                for(int i=0;i<7;i++)isToChange[i]=false;
                if (reg.checkWords(plansza)) {

                    reg.policz(plansza, turn);
                    us[turn].score = reg.getScore(turn);
                    us[turn].wyn.setText("" + us[turn].score);
                    plansza.clear();
                    change.clear();
                    player.get(turn).Hand.clear();

                } else {
                    for (Piece e : plansza) {
                        reg.unsetmap(e.tail, e.x, e.y);
                        matrix[e.x][e.y]=false;
                        
                        e.setInactive();
                        e.toBack();
                    }
                    change.clear();
                    plansza.clear();
                }
                player.get(turn).Hand.clear();
                plansza.clear();
                turn = (turn + 1) % reg.getPlayerCount();
                Stage stage =new Stage();
                VBox temp=new VBox(20);
                final Button button =new Button("Potwierdzam");
                button.setAlignment(Pos.CENTER);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
            }
                });
                Text text=new Text("Czy gracz  "+reg.getPlayer(turn).id+" się zjawił ?");
                temp.getChildren().addAll(text,button);
                Scene scen= new Scene(temp);
                stage.setScene(scen);
                stage.showAndWait();
                System.out.println(turn + " " + reg.getPlayerCount());
                reg.rend(turn);
                for (int i = 0; i < 7; i++) {
                    Tail tail = reg.getHand(i, turn);
                    System.out.print(tail.Char);
                    Piece tmp = null;
                    for (Piece e : pieces) {
                        if (e.equals(tail)) {
                            //             tmp=e;
                            if (!player.get(turn).Hand.contains(e)) {
                                player.get(turn).Hand.add(e);
                            }
                            e.setTranslateX(i * 25);
                            e.correctX = i * 25;
                            e.correctY = 385;
                            e.setTranslateY(385);
                            e.toFront();
                            e.setActive();
                            break;
                        }
                    }
                }
                confirm.setDisable(false);

            }
        });
        final Button end = new Button("Zakończ gre");
        end.setPrefSize(100, 40);
        end.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage finish = new Stage();
                HBox hbox= new HBox(5);
                for(int i=0;i<reg.getPlayerCount();i++)
                {
                    VBox vbox= new VBox(5);
                    Text id =new  Text(reg.getPlayer(i).id);
                    Text value = new Text(""+reg.getPlayer(i).score);
                    vbox.getChildren().addAll(id,value);
                    hbox.getChildren().addAll(vbox);
                   
                }
                 Scene scane= new Scene(hbox);
                 finish.setScene(scane);
                 finish.show();
                 Stage stage = (Stage) end.getScene().getWindow();
            stage.close();
            }});
        user1.getChildren().addAll(us[0], us[1]);
        user2.getChildren().addAll(us[2], us[3]);
        rightvb.getChildren().addAll(user1, user2, confirm,end);
        hb.getChildren().addAll(vb, rightvb);
        main.getChildren().addAll(hb);
        //  main.getChildren().addAll(bonus);
        root.getChildren().addAll(main);
        reg.rend(turn);
        for (int i = 0; i < 7; i++) {
            Tail tail = reg.getHand(i, turn);
            System.out.print(tail.Char);
            Piece tmp = null;
            for (Piece e : pieces) {
                if (e.equals(tail)) {
                    //             tmp=e;
                    player.get(turn).Hand.add(e);
                    e.setTranslateX(i * 25);
                    e.correctX = i * 25;
                    e.correctY = 385;
                    e.setTranslateY(385);
                    e.toFront();
                    e.setActive();
                    break;
                }
            }
            //       pieces.remove(tmp);
        }

    }

    /**
     * Node that represents the playing area/ desktop where the puzzle pices sit
     */
    public class Desk extends Pane {

        Desk(int numOfColumns, int numOfRows, Paint value, int mode) {
            setStyle("-fx-border-color: #464646; "
                    + "-fx-effect: innershadow( two-pass-box , rgba(0,0,0,0.8) , 15, 0.0 , 0 , 4 );");
            double DESK_WIDTH = numOfColumns * Piece.SIZE;
            double DESK_HEIGHT = numOfRows * Piece.SIZE;
            setPrefSize(DESK_WIDTH, DESK_HEIGHT);
            setMaxSize(DESK_WIDTH, DESK_HEIGHT);
            autosize();
            // create path for lines
            getChildren().addAll(View.CreateBackground(0, 0, DESK_WIDTH, DESK_HEIGHT, value));
            final List<bonus> extra = new ArrayList<bonus>();
            if(mode==1){
        for(int i=0;i<15;i++)
                {
                    for(int j=0;j<15;j++)
                    {
                        if(reg.getBonus(i, j)>1)
                        {
                           final bonus bon = new bonus(i,j);
                            extra.add(bon);
                            getChildren().add(bon);
                        }
                        if(i==7&&j==7)
                        {
                            final bonus bon = new bonus();
                            extra.add(bon);
                            getChildren().add(bon);
                        
                        }
                    }
                } }
            // create vertical lines
            Path grid = new Path();
            grid.setStroke(Color.rgb(50, 50, 50));
            getChildren().addAll(grid);
            //getChildren().add(grid);
            
            for (int col = 0; col < numOfColumns - 1; col++) {
                grid.getElements().addAll(
                        new MoveTo(Piece.SIZE + Piece.SIZE * col, 5),
                        new LineTo(Piece.SIZE + Piece.SIZE * col, Piece.SIZE * numOfRows - 5)
                );
            }
            // create horizontal lines
            for (int row = 0; row < numOfRows - 1; row++) {
                grid.getElements().addAll(
                        new MoveTo(5, Piece.SIZE + Piece.SIZE * row),
                        new LineTo(Piece.SIZE * numOfColumns - 5, Piece.SIZE + Piece.SIZE * row)
                );
            }
            
        
            /* setOnMouseClicked(new EventHandler<MouseEvent>(){
             public void handle(MouseEvent me) {
             int x=((int)me.getSceneX())/25;
             int y=(int) (me.getSceneY()/25);
             if(buf!=null&&/*contr.model.tab.check(x, y)matrix[x][y]==false){
             reg.setmap(reg.gethand((int) (buf.correctX/25)).current, x, y);
             buf.setTranslateX((me.getSceneX()-buf.correctX)-(me.getSceneX()%25));
             buf.setTranslateY((me.getSceneY()-buf.correctY)-(me.getSceneY()%25));
             //buf.setInactive();
             matrix[x][y]=true;
             buf=null;
             }
             }
             });*/
        }

        @Override
        protected void layoutChildren() {
        }
    }

    public class Piece extends Parent {

        public static final int SIZE = 25;
        public double correctX;
        public double correctY;
        private double startDragX;
        private double startDragY;
        private Shape pieceStroke;
        private Shape pieceClip;
        private Point2D dragAnchor;
        private Text text;
        public int mod;
        private Piece that;
        public int x;
        public int y;
        public Tail tail;

        public boolean equals(Tail tail) {
            return tail.id == this.tail.id;
        }

        public Piece(final Tail tail, final double correctX, final double correctY,
                final double deskWidth, final double deskHeight, int mod) {
            this.mod = mod;
            this.that = this;
            this.correctX = correctX;
            this.correctY = correctY;
            // configure clip
            pieceClip = createPiece();
            pieceClip.setFill(Color.WHITE);
            pieceClip.setStroke(null);
            // add a stroke
            pieceStroke = createPiece();
            pieceStroke.setFill(Color.rgb(43, 85, 66));
            pieceStroke.setStroke(Color.BLACK);
            this.tail = tail;
            text = new Text(this.correctX + 6, this.correctY + 19, "" + tail.Char);
            text.setFont(Font.font(20));
            // create image view
            setFocusTraversable(true);
            getChildren().addAll(pieceStroke, text);
            // turn on caching so the jigsaw piece is fasr to draw when dragging
            setCache(true);
            // start in inactive state
            setActive();
            // add listeners to support dragging
            setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    toFront();
                    that.toFront();
                    int x = ((int) me.getSceneX()) / 25;
                    int y = (int) (me.getSceneY() / 25);
                    if (change.contains(that)) {
                        isToChange[x - 8] = false;
                        that.mod = 0;
                        change.remove(that);
                    } else if (plansza.contains(that)) {
                        matrix[x][y] = false;
                        that.mod = 2;
                        plansza.remove(that);
                    } else {
                        that.mod = 1;
                        onHand.remove(that);
                    }
                    startDragX = getTranslateX();
                    startDragY = getTranslateY();
                    dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                }
            });
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    that.toFront();
                    int x = ((int) me.getSceneX()) / 25;
                    int y = (int) (me.getSceneY() / 25);
                    if (me.getSceneX() <= 375 && me.getSceneY() < 375 && matrix[x][y] == false) {
                        plansza.add(that);
                        //setInactive();
                        matrix[x][y] = true;
                        setTranslateX((me.getSceneX() - correctX) - (me.getSceneX() % 25));
                        setTranslateY((me.getSceneY() - correctY) - (me.getSceneY() % 25));
                        that.x = x;
                        that.y = y;
                        //buf=null;
                    } else if (me.getSceneX() >= 200 && me.getSceneX() < 375 && me.getSceneY() < 410 && me.getSceneY() >= 385 && isToChange[x - 8] == false) {
                        change.add(that);
                        setTranslateX((me.getSceneX() - correctX) - (me.getSceneX() % 25));
                        setTranslateY(385);
                        isToChange[x - 8] = true;
                    } else {
                        if (me.getSceneX() <= 375 && me.getSceneY() < 375) {
                            matrix[x][y] = false;
                        }
                        setTranslateX(that.correctX);
                        setTranslateY(that.correctY);
                        onHand.add(that);
                    }
                }
            });
            /*setOnMouseClicked(new EventHandler<MouseEvent>(){
             public void handle(MouseEvent me) {
             that.text.setId(that.getId()+1);
             if(buf!=null)
             {
             buf.setTranslateX(0);
             buf.setTranslateY(0);
             buf=null;
             }
             else
             {
             int x=((int)me.getSceneX())/25;
             int y=(int) (me.getSceneY()/25);
             if(me.getSceneX()<=375&& me.getSceneY()<375)matrix[x][y]=false;
             buf=(Piece) me.getSource();
             }
                    
             }
             });*/
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    double newTranslateX = startDragX
                            + me.getSceneX() - dragAnchor.getX();
                    double newTranslateY = startDragY
                            + me.getSceneY() - dragAnchor.getY();
                    double minTranslateX = -45f - correctX;
                    double maxTranslateX = (deskWidth - Piece.SIZE + 50f) - correctX;
                    double minTranslateY = -30f - correctY - 385;
                    double maxTranslateY = (deskHeight - Piece.SIZE + 70f) - correctY;
                    if ((newTranslateX > minTranslateX)
                            && (newTranslateX < maxTranslateX)
                            && (newTranslateY > minTranslateY)
                            && (newTranslateY < maxTranslateY)) {
                        setTranslateX(newTranslateX);
                        setTranslateY(newTranslateY);
                    }
                }
            });
        }

        private Shape createPiece() {
            Shape shape = createPieceRectangle();
            shape.setTranslateX(correctX);
            shape.setTranslateY(correctY);
            shape.setLayoutX(50f);
            shape.setLayoutY(50f);
            return shape;
        }

        private Rectangle createPieceRectangle() {
            Rectangle rec = new Rectangle();
            rec.setX(-50);
            rec.setY(-50);
            rec.setWidth(25);
            rec.setHeight(25);
            return rec;
        }

        public void setActive() {
            setDisable(false);
            setEffect(new DropShadow());
            toFront();
        }

        public void setInactive() {
            setEffect(null);
            setDisable(true);
            toFront();
        }

        public double getCorrectX() {
            return correctX;
        }

        public double getCorrectY() {
            return correctY;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        init(primaryStage);
        primaryStage.show();
    }
    /*   public void start()
     {
     launch();
     }*/

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
