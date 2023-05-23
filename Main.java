import java.util.Objects;
import java.lang.*;
import java.util.Scanner;
class Main {
  public static int counter = 0;


  public static void main(String[] args) {
    wgame("6R1/p7/3q1kp1/3BRp2/5QP1/1P3P2/P2P2K1/8 w - - 3 41");
  }
  
  
  //  benchmarks the engine with a couple chess puzzles. times its computation
  public static void benchmark(){
    counter = 0;
    board board = new board("4k3/8/8/8/3n4/8/1B6/R3K3 w - - 0 1");
    piece [][] numero = board.getChecker();
    long a = System.nanoTime();
    System.out.println(altmaxi(5,numero,-99999,99999)[1]);
    System.out.println(counter);
    long b = System.nanoTime();
    System.out.println((double) (b-a) / 1_000_000_000);
    System.out.println();
    counter = 0;
    board = new board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    numero = board.getChecker();
    a = System.nanoTime();
    System.out.println(altmaxi(5,numero,-99999,99999)[1]);
    System.out.println(counter);
    b = System.nanoTime();
    System.out.println((double) (b-a) / 1_000_000_000);
    System.out.println();
    counter = 0;
    board = new board("2kr1b1r/pp2pppp/2n1b3/1B6/4P3/PnN1BN2/1P3PPP/1R1K3R w - - 9 14");
    numero = board.getChecker();
    a = System.nanoTime();
    System.out.println(altmaxi(5,numero,-99999,99999)[1]);
    System.out.println(counter);
    b = System.nanoTime();
    System.out.println((double) (b-a) / 1_000_000_000);
    System.out.println();
    counter = 0;
    board = new board("2k2b1r/pp6/2n3p1/1B1bp3/8/Pn2BN2/1P2NPPP/3K3R w - - 1 22");
    numero = board.getChecker();
    a = System.nanoTime();
    System.out.println(altmaxi(5,numero,-99999,99999)[1]);
    System.out.println(counter);
    b = System.nanoTime();
    System.out.println((double) (b-a) / 1_000_000_000);
    counter = 0;

  }
  // plays a game with the computer as white
  public static void wgame(String fen){
    board board = new board(fen);
    piece [][] numero = board.getChecker();
    Scanner stringScanner = new Scanner(System.in);
    Scanner intScan = new Scanner(System.in);
    int moved = 0;
    while (true){
      System.out.println(board.toFen(numero,true,moved));
      coordinate [] chork = new coordinate[0];
      System.out.println("depth?");
      int tabunga = intScan.nextInt();
      while (tabunga!=0){
        counter = 0;
        chork = altmaxi(tabunga,numero,-99999,99999);
        System.out.println(chork[0]);
        System.out.println(chork[1]);
        System.out.println("pos"+counter);
        System.out.println("depth?");
        tabunga = intScan.nextInt();
      }

      moved++;
      System.out.println(board.toFen(numero,true,moved));
      //System.out.println("What piece does white want to move?");
      //String move = stringScanner.next();
      char ch1 ;//= move.charAt(0);
      //System.out.println("ch1: "+ch1);
      int ch2 ;//= ((int)move.charAt(1)-48);
      //System.out.println("ch2: "+ch2);
      coordinate start; //= new coordinate(ch1,ch2);
      //System.out.println("Where to?");
      //move = stringScanner.next();
      //ch1 = move.charAt(0);
      //System.out.println("ch1: "+ch1);
      //ch2 = ((int)move.charAt(1)-48);
      //System.out.println("ch2: "+ch2);
      coordinate end; //= new coordinate(ch1,ch2);
      numero = board.movePiece(numero,chork[0],chork[1]);
      System.out.println(board.toFen(numero,false,moved));
      //System.out.println("depth?");
      //tabunga = intScan.nextInt();
      //while (tabunga!=0){
      //counter = 0;
      //chork = altmini(tabunga,numero,-99999,99999);
      //System.out.println(chork[0]);
      //System.out.println(chork[1]);
      //System.out.println("pos: "+counter);
      //System.out.println("depth?");
      //tabunga = intScan.nextInt();
      //}


      //chork = altmini(5,numero);
      //System.out.println(chork[0]);
      //System.out.println(chork[1]);
      //System.out.println(counter);

      System.out.println("What piece does black want to move?");
      String move = stringScanner.next();
      ch1 = move.charAt(0);

      //System.out.println("ch1: "+ch1);
      ch2 = ((int)move.charAt(1)-48);

      //System.out.println("ch2: "+ch2);
      start  = new coordinate(ch1,ch2);
      System.out.println("Where to?");
      move = stringScanner.next();
      ch1 = move.charAt(0);
      //System.out.println("ch1: "+ch1);
      ch2 = ((int)move.charAt(1)-48);
      //System.out.println("ch2: "+ch2);
      end = new coordinate(ch1,ch2);
      numero = board.movePiece(numero,start,end);


    }

  }
//  the maximizing part of the algorithm, the part that calculates for white
  public static double maxi(int depth,piece [][] a,double alpha,double beta) {
    if ( depth == 0 ) {
      counter++;
      return board.eval(a,true,depth);
    }
    double max = -9999;
    int thingy = 0;
    for (int i = 0; i < 8; i++) {
      for (int w = 0; w < 8; w++) {
        if (!Objects.isNull(a[i][w])&&a[i][w].getColor()){
          coordinate []legals = board.legals(a,new coordinate((char)(i+97),w+1));
          if (legals.length>0){
            thingy = 1;
          }
          for (coordinate legal : legals) {
            //System.out.println(board.movePiece(a,new coordinate((char)(i+97),w+1),legals[z])[legals[z].getx()][legals[z].gety()]);
            double score = mini(depth - 1, board.movePiece(a, new coordinate((char) (i + 97), w + 1), legal),alpha,beta);

            if (alpha <score){
              alpha = score;
            }
            if (beta <=alpha){
              break;
            }
          }
        }
      }
    }
    if (thingy == 0){
      return 0;
    }
    return alpha;
  }
//  the minimizing part of the algorithm, calculates for black
  public static double mini( int depth,piece [][] a,double alpha, double beta) {
    if ( depth == 0 ) {
      counter++;
      return board.eval(a,false,depth);
      }
    double min = 9999;
    int thingy = 0;
    for (int i = 0; i < 8; i++) {
      for (int w = 0; w < 8; w++) {
        if (!Objects.isNull(a[i][w])&&!a[i][w].getColor()){
          coordinate []legals = board.legals(a,new coordinate((char)(i+97),w+1));
          if (legals.length>0){
            thingy = 1;
          }
          for (coordinate legal : legals) {
            double score = maxi(depth - 1, board.movePiece(a, new coordinate((char) (i + 97), w + 1), legal),alpha,beta);

            if (beta > score){
              beta = score;
            }
            if (beta <= alpha){
              break;
            }
          }
        }
      }
    }
    if (thingy == 0){
      return 0;
    }
    return beta;
  }
  //  the maximizing part but it returns the move and not the evaluation
  public static coordinate [] altmaxi(int depth,piece [][] a,double alpha,double beta) {
    if ( depth == 0 ) {
      counter++;
      return null;
    }
    int thingy = 0;
    coordinate [] dubs = new coordinate [2];
    for (int i = 0; i < 8; i++) {
      for (int w = 0; w < 8; w++) {
        if (!Objects.isNull(a[i][w])&&a[i][w].getColor()){
          coordinate []legals = board.legals(a,new coordinate((char)(i+97),w+1));
          if (thingy ==1 || legals.length>0){
            thingy = 1;
          }
          for (coordinate legal : legals) {
            //System.out.println(board.movePiece(a,new coordinate((char)(i+97),w+1),legals[z])[legals[z].getx()][legals[z].gety()]);
            double score = mini(depth - 1, board.movePiece(a, new coordinate((char) (i + 97), w + 1), legal),alpha,beta);

            if (alpha < score){
              alpha = score;
              dubs[0] = new coordinate((char) (i + 97), w + 1);
              dubs[1] = legal;
            }
            if (beta <=alpha){
              break;
            }
          }
        }
      }
    }
    if (thingy == 0){
      return null;
    }
    System.out.println(alpha);
    return dubs;
  }
//  the minimizing part but it returns a move and not an eval
  public static coordinate [] altmini( int depth,piece [][] a,double alpha, double beta) {
    if ( depth == 0 ) {
      counter++;
      return null;
    }
    double min = 9999;
    coordinate [] dubs = new coordinate [2];
    int thingy = 0;
    for (int i = 0; i < 8; i++) {
      for (int w = 0; w < 8; w++) {
        if (!Objects.isNull(a[i][w])&&!a[i][w].getColor()){
          coordinate []legals = board.legals(a,new coordinate((char)(i+97),w+1));
          if (thingy ==1 || legals.length>0){
            thingy = 1;
          }
          for (coordinate legal : legals) {
            double score = maxi(depth - 1, board.movePiece(a, new coordinate((char) (i + 97), w + 1), legal),alpha,beta);

            if (beta > score){
              beta = score;
              dubs[0] = new coordinate((char) (i + 97), w + 1);
              dubs[1] = legal;
            }
            if (beta <= alpha){
              break;
            }
          }
        }
      }
    }
    if (thingy == 0){
      return null;
    }
    System.out.println(beta);
    return dubs;
  }

  
  
  
  
  

}
