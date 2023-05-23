class piece{
  char type;
  boolean color;
  coordinate location;
  int moves = 0;
  boolean recent;

  piece(char pawn, boolean white,char a, int one){
    type = pawn;
    color = white;
    location = new coordinate(a,one);
  }
  piece(char pawn, boolean white,char a, int one,int far){
    type = pawn;
    color = white;
    location = new coordinate(a,one);
    moves = far;
  }
  piece(){
  }
  public static piece clone (piece a){
    return new piece(a.type,a.color,a.location.litx(),a.location.lity(),a.getMoves());
  }
  public String toString(){
    String word;
    String black;
    if (type == 'k')
      word = "King";
    else if (type == 'q')
      word = "Queen";
    else if (type == 'p')
      word = "Pawn";
    else if (type == 'r')
      word = "Rook";
    else if (type == 'n')
      word = "Knight";
    else
      word = "Bishop";
    if (color == true)
      black = "White";
    else
      black = "Black";
    return black+" "+word+" on "+location.litx()+location.lity()+". And has moved "+moves+" times.";
  }
  public char getType() {
    return type;
  }
  public boolean getColor(){
    return color;
  }
  public coordinate getLocation() {
    return location;
  }
  public int getMoves(){
    return moves;
  }
  public void addMove(){
    moves+=1;
  }
  public void setRecent(boolean a){
    recent = a;
  }
  public boolean getRecent(){
    return recent;
  }
  public void setLocation(coordinate a){
    location = a;
  }
  public void setType(char tor){
    type = tor;
  }

}