package puzzlepieces;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private static Controler contr= new Controler();

    private void init(Stage primaryStage) {
        Pane main= new Pane();
        main.setPrefSize(800, 600);
        buf=null;
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        // load puzzle image
        int numOfColumns = 15;//(int) (image.getWidth() / Piece.SIZE);
        int numOfRows = 15;//(int) (image.getHeight() / Piece.SIZE);
        // create desk
        final Desk desk = new Desk(numOfColumns, numOfRows);
        // create puzzle pieces
        final Desk hand= new Desk(8, 1);
        final List<Piece> pieces  = new ArrayList<Piece>();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 1; row++) {
                int x = col * Piece.SIZE;
                int y = 0;
                final Piece piece = new Piece(contr.model.hand.hand[col],x, y,
                        desk.getWidth(), desk.getHeight());
                pieces.add(piece);
            }
        }
        // create vbox for desk and buttons*/
        VBox vb = new VBox(10);
        vb.getChildren().addAll(desk,hand);
         main.getChildren().addAll(vb);
         main.getChildren().addAll(pieces);
        root.getChildren().addAll(main);
    }

    /**
     * Node that represents the playing area/ desktop where the puzzle pices sit
     */
    public static class Desk extends Pane {
        Desk(int numOfColumns, int numOfRows) {
            setStyle("-fx-border-image-source: PuzzlePieces-picture.jpg; " +
                    "-fx-border-color: #464646; " +
                    "-fx-effect: innershadow( two-pass-box , rgba(0,0,0,0.8) , 15, 0.0 , 0 , 4 );");
            double DESK_WIDTH = numOfColumns*Piece.SIZE;
            double DESK_HEIGHT = numOfRows*Piece.SIZE;
            setPrefSize(DESK_WIDTH,DESK_HEIGHT);
            setMaxSize(DESK_WIDTH, DESK_HEIGHT);
            autosize();
            // create path for lines
            Path grid = new Path();
            grid.setStroke(Color.rgb(50, 50, 50));
            getChildren().add(grid);
            
            // create vertical lines
             for (int col = 0; col < numOfColumns - 1; col++) {
                 grid.getElements().addAll(
                     new MoveTo(Piece.SIZE +Piece.SIZE * col, 5),
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
            
            setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me) {
                    int x=((int)me.getSceneX())/25;
                    int y=(int) (me.getSceneY()/25);
                    if(buf!=null&&contr.model.tab.check(x, y)){
                        contr.model.tab.set(contr.model.hand.pop((int) (buf.correctX/25)), x, y);
                    buf.setTranslateX((me.getSceneX()-buf.correctX)-(me.getSceneX()%25));
                    buf.setTranslateY((me.getSceneY()-buf.correctY)-(me.getSceneY()%25));
                    buf.setInactive();
                    buf=null;}
                }
            });
        }
        @Override protected void layoutChildren() {}
    }

    /**
     * Node that represents a puzzle piece
     */
    public static class Piece extends Parent {
        public static final int SIZE = 25;
        private double correctX;
        private double correctY;
        private double startDragX;
        private double startDragY;
        private Shape pieceStroke;
        private Shape pieceClip;
        private Point2D dragAnchor;
        private Text text;

        public Piece(final Field field,final double correctX, final double correctY,
                     final double deskWidth, final double deskHeight) {
            this.correctX = correctX;
            this.correctY = correctY+385;
            // configure clip
            pieceClip = createPiece();
            pieceClip.setFill(Color.WHITE);
            pieceClip.setStroke(null);
            // add a stroke
            pieceStroke = createPiece();
            pieceStroke.setFill(Color.AQUA);
            pieceStroke.setStroke(Color.BLACK);
            text=new Text(this.correctX+ 6,this.correctY+19,""+field.current.Char);
            text.setFont(Font.font(20));
            // create image view
            setFocusTraversable(true);
            getChildren().addAll( pieceStroke,text);
            // turn on caching so the jigsaw piece is fasr to draw when dragging
            setCache(true);
            // start in inactive state
            setActive();
            // add listeners to support dragging
            setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    toFront();
                    startDragX = getTranslateX();
                    startDragY = getTranslateY();
                    dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                }
            });
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    int x=((int)me.getSceneX())/25;
                    int y=(int) (me.getSceneY()/25);
                    if(me.getSceneX()<=375&& me.getSceneY()<375&&contr.model.tab.check(x, y))
                    {
                        setInactive();
                        setTranslateX((me.getSceneX()-correctX)-(me.getSceneX()%25));
                        setTranslateY((me.getSceneY()-correctY)-(me.getSceneY()%25)-385);
                        
                        contr.model.tab.set(contr.model.hand.pop((int) (correctX/25)), x, y);
                        
                        buf=null;
                    }
                    else
                    {
                        setTranslateX(0);
                        setTranslateY(0);
                        
                    }
                    
                }
            });
           setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me) {
                    if(buf!=null)buf.setActive();
                    buf=(Piece) me.getSource();
                }
            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    double newTranslateX = startDragX
                                            + me.getSceneX() - dragAnchor.getX();
                    double newTranslateY = startDragY
                                            + me.getSceneY() - dragAnchor.getY();
                    double minTranslateX = - 45f - correctX;
                    double maxTranslateX = (deskWidth - Piece.SIZE + 50f ) - correctX;
                    double minTranslateY = - 30f - correctY-385;
                    double maxTranslateY = (deskHeight - Piece.SIZE + 70f ) - correctY-385;
                    if ((newTranslateX> minTranslateX ) &&
                            (newTranslateX< maxTranslateX) &&
                            (newTranslateY> minTranslateY) &&
                            (newTranslateY< maxTranslateY)) {
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
            toBack();
        }

        public double getCorrectX() { return correctX; }

        public double getCorrectY() { return correctY; }
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
