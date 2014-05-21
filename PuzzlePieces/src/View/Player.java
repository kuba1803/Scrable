package View;

import View.View.Piece;
import java.util.ArrayList;
import java.util.List;

public class Player {
List<Piece> Hand;   
int count;
String name;
boolean onGame;
public Player(int count, String name)
{
    Hand=new ArrayList<Piece>();
    this.count=count;
    this.name=name;
    onGame=true;
}
}
