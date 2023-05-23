class coordinate{
  char x;
  int y;
  coordinate(char a, int one){
    x = a;
    y = one;
  }

  int gety(){
    return (y-1);
  }
  int getx(){
    return (int)(x-97);
  }
  char litx(){
    return x;
  }
  int lity(){
    return (y);
  }
  public void setx(int a){
    x = (char)(a+97);
  }
  public void sety(int a){
    y = (a+1);
  }
  public String toString(){
    return (litx()+Integer.toString(lity()));
  }
}