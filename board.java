import java.util.Objects;
import java.util.Arrays;

class board {
    piece[][] checker = new piece[8][8];

    board() {
        checker[0][0] = new piece('r', true, 'a', 1);
        checker[1][0] = new piece('n', true, 'b', 1);
        checker[2][0] = new piece('b', true, 'c', 1);
        checker[3][0] = new piece('q', true, 'd', 1);
        checker[4][0] = new piece('k', true, 'e', 1);
        checker[5][0] = new piece('b', true, 'f', 1);
        checker[6][0] = new piece('n', true, 'g', 1);
        checker[7][0] = new piece('r', true, 'h', 1);
        checker[0][1] = new piece('p', true, 'a', 2);
        checker[1][1] = new piece('p', true, 'b', 2);
        checker[2][1] = new piece('p', true, 'c', 2);
        checker[3][1] = new piece('p', true, 'd', 2);
        checker[4][1] = new piece('p', true, 'e', 2);
        checker[5][1] = new piece('p', true, 'f', 2);
        checker[6][1] = new piece('p', true, 'g', 2);
        checker[7][1] = new piece('p', true, 'h', 2);

        checker[0][6] = new piece('p', false, 'a', 7);
        checker[1][6] = new piece('p', false, 'b', 7);
        checker[2][6] = new piece('p', false, 'c', 7);
        checker[3][6] = new piece('p', false, 'd', 7);
        checker[4][6] = new piece('p', false, 'e', 7);
        checker[5][6] = new piece('p', false, 'f', 7);
        checker[6][6] = new piece('p', false, 'g', 7);
        checker[7][6] = new piece('p', false, 'h', 7);
        checker[0][7] = new piece('r', false, 'a', 8);
        checker[1][7] = new piece('n', false, 'b', 8);
        checker[2][7] = new piece('b', false, 'c', 8);
        checker[3][7] = new piece('q', false, 'd', 8);
        checker[4][7] = new piece('k', false, 'e', 8);
        checker[5][7] = new piece('b', false, 'f', 8);
        checker[6][7] = new piece('n', false, 'g', 8);
        checker[7][7] = new piece('r', false, 'h', 8);


    }


    board(String fen){
        boolean K = false;
        boolean Q = false;
        boolean k = false;
        boolean q = false;
        int counta = 0;
        for (int x = fen.length()-1;x>-1;x--){
            if (fen.charAt(x)==' '){
                counta++;
            }
            if (counta ==3 ){
                if (fen.charAt(x)=='K'){
                    K = true;
                }
                if (fen.charAt(x)=='Q'){
                    Q = true;
                }
                if (fen.charAt(x)=='k'){
                    k = true;
                }
                if (fen.charAt(x)=='q'){
                    q = true;
                }
            }
        }



        int counter = 0;
        for (int w = 7; w > -1; w--) {
            for (int i = 0; i < 8; i++) {
                if (fen.charAt(counter)=='R'){
                    checker[i][w] = new piece('r', true, (char)(i+97), w+1);
                    if (i!=0|| i !=7){
                        checker[i][w].addMove();
                    }
                    else if (i==0){
                        if(!Q){
                            checker[i][w].addMove();
                        }
                    }
                    else{
                        if(!K){
                            checker[i][w].addMove();
                        }
                    }
                }
                else if (fen.charAt(counter)=='r'){
                    checker[i][w] = new piece('r', false, (char)(i+97), w+1);
                    if (i!=0|| i !=7){
                        checker[i][w].addMove();
                    }
                    else if (i==0){
                        if(!q){
                            checker[i][w].addMove();
                        }
                    }
                    else{
                        if(!k){
                            checker[i][w].addMove();
                        }
                    }
                }
                else if (fen.charAt(counter)=='N'){
                    checker[i][w] = new piece('n', true, (char)(i+97), w+1);
                }
                else if (fen.charAt(counter)=='n'){
                    checker[i][w] = new piece('n', false, (char)(i+97), w+1);

                }
                else if (fen.charAt(counter)=='B'){
                    checker[i][w] = new piece('b', true, (char)(i+97), w+1);
                }
                else if (fen.charAt(counter)=='b'){
                    checker[i][w] = new piece('b', false, (char)(i+97), w+1);
                }
                else if (fen.charAt(counter)=='K'){
                    checker[i][w] = new piece('k', true, (char)(i+97), w+1);
                    if (!Q&&!K){
                        checker[i][w].addMove();
                    }
                }
                else if (fen.charAt(counter)=='k'){
                    checker[i][w] = new piece('k', false, (char)(i+97), w+1);
                    if (!q&&!k){
                        checker[i][w].addMove();
                    }
                }
                else if (fen.charAt(counter)=='Q'){
                    checker[i][w] = new piece('q', true, (char)(i+97), w+1);
                }
                else if (fen.charAt(counter)=='q'){
                    checker[i][w] = new piece('q', false, (char)(i+97), w+1);
                }
                else if (fen.charAt(counter)=='P'){
                    checker[i][w] = new piece('p', true, (char)(i+97), w+1);
                }
                else if (fen.charAt(counter)=='p'){
                    checker[i][w] = new piece('p', false, (char)(i+97), w+1);
                }
                else if (fen.charAt(counter)=='/'){
                    i=-1;
                }
                else if (fen.charAt(counter)==' '){
                    break;
                }
                else {
                    i += (int) fen.charAt(counter)-49;
                }
                counter++;
            }
        }
    }
    board(piece[][] a) {
        for (int i = 0; i < 8; i++) {
            for (int w = 0; w < 8; w++) {
                checker[i][w] = a[i][w];
            }
        }
    }
    public static String toFen(piece[][] a,boolean color,int move){
        String fen = new String();
        String en = new String();
        boolean Wking = false;
        boolean Bking = false;
        boolean WQB = false;
        boolean WKB = false;
        boolean BQB = false;
        boolean BKB = false;
        for (int w = 7; w > -1; w--) {
            int counter = 0;
            for (int i = 0; i < 8; i++) {
                if (Objects.isNull(a[i][w])){
                    counter++;
                }
                else{
                    if (counter!=0){
                        fen += Integer.toString(counter);
                        counter = 0;
                    }
                    if (a[i][w].getType() == 'k'){
                        if (a[i][w].getColor()){
                            if (a[i][w].getMoves()==0){
                                Wking = true;
                            }
                            fen+= 'K';
                        }
                        else{
                            if (a[i][w].getMoves()==0){
                                Bking = true;
                            }
                            fen+= 'k';
                        }
                    }
                    if (a[i][w].getType() == 'q'){
                        if (a[i][w].getColor()){
                            fen+= 'Q';
                        }
                        else{
                            fen+= 'q';
                        }
                    }
                    if (a[i][w].getType() == 'b'){
                        if (a[i][w].getColor()){
                            fen+= 'B';
                        }
                        else{
                            fen+= 'b';
                        }
                    }
                    if (a[i][w].getType() == 'n'){
                        if (a[i][w].getColor()){
                            fen+= 'N';
                        }
                        else{
                            fen+= 'n';
                        }
                    }
                    if (a[i][w].getType() == 'r'){
                        if (a[i][w].getColor()){
                            if (i==0&&w==0&&a[i][w].getMoves()==0){
                                WQB = true;
                            }
                            else if (i==7&&w==0&&a[i][w].getMoves()==0){
                                WKB = true;
                            }
                            fen+= 'R';
                        }
                        else{
                            if (i==0&&w==7&&a[i][w].getMoves()==0){
                                BQB = true;
                            }
                            else if (i==7&&w==7&&a[i][w].getMoves()==0){
                                BKB = true;
                            }
                            fen+= 'r';
                        }
                    }
                    else if (a[i][w].getType() == 'p'){
                        if (a[i][w].getColor()){
                            if (w==3&&a[i][w].getRecent()){
                                en += (char)(i+97);
                                en+=w;
                            }
                            fen+= 'P';
                        }
                        else {
                            if ((w==4) && a[i][w].getRecent()){
                                System.out.println(i);
                                System.out.println(w);
                                en += (char)(i+97);
                                en+= (w+2);
                            }
                            fen+= 'p';
                        }
                    }
                }
            }
            if (counter!=0){
                fen += Integer.toString(counter);
            }
            if ((w-1>-1)){
                fen+="/";
            }
        }
        fen+=" ";
        if (color){
            fen+='w';
        }
        else{
            fen +='b';
        }
        fen+=" ";
        if(WKB&&Wking){
            fen+= "K";
        }
        if(WQB&&Wking){
            fen+= "Q";
        }
        if(BKB&&Bking){
            fen+= "k";
        }
        if(BQB&&Bking){
            fen+= "q";
        }
        if (fen.charAt(fen.length()-1) ==' '){
            fen+= '-';
        }
        fen+=" ";
        if (en.length()>0){
            fen+=en;
        }
        else{
            fen+="-";
        }
        fen+=" ";
        fen+="0";
        fen+=" ";
        fen+=move;
        return fen;
    }
    public static double eval(piece[][] a,boolean color,int depth) {
        double num = 0;
        for (int i = 0; i < 8; i++) {
            for (int w = 0; w < 8; w++) {
                if (!Objects.isNull(a[i][w])) {
                    if (a[i][w].getType() == 'p') {
                        if (a[i][w].getColor()) {
                            num += 1;
                            if (i>1&&i<6){
                                num+=.1;
                                if (i>2&&i<5){
                                    num+=.1;
                                }
                            }
                            if (w>2){
                                num+=.1;
                                if (w>3){
                                    num+=.1;
                                }
                            }
                        } else {
                            num -= 1;
                            if (i>1&&i<6){
                                num-=.1;
                                if (i>2&&i<5){
                                    num-=.1;
                                }
                            }
                            if (w<5){
                                num-=.1;
                                if (w<4){
                                    num-=.1;
                                }
                            }
                        }
                    } else if (a[i][w].getType() == 'b') {
                        if (a[i][w].getColor()) {
                            num += 3;
                            if (w>1){
                                num+= .2;
                            }
                        } else {
                            num -= 3;
                            if (w<6){
                                num-= .2;
                            }
                        }
                    } else if (a[i][w].getType() == 'n') {
                        if (a[i][w].getColor()) {
                            num += 3;
                            if (w>0){
                                num+= .2;
                            }
                        } else {
                            num -= 3;
                            if (w<7){
                                num-= .2;
                            }
                        }
                    } else if (a[i][w].getType() == 'r') {
                        if (a[i][w].getColor()) {
                            num += 5;
                        } else {
                            num -= 5;
                        }
                    } else if (a[i][w].getType() == 'q') {
                        if (a[i][w].getColor()) {
                            num += 9;
                        } else {
                            num -= 9;
                        }
                    }
                    else{
                        if (i==2||i==6){
                            if(a[i][w].getColor()){
                                num += .5;
                            }
                            else{
                                num -= .5;
                            }
                        }
                    }
                }
            }
        }
        if (isCheck(a,color)){
            if (isStale(a,color)){
                return ((color ? 1 : -1)*(-999-depth));
            }
        }
        return num;
    }

    public static boolean isMate(piece[][] a, boolean color) {
        if (isCheck(a, color)) {
            coordinate[] arr = new coordinate[0];
            for (int i = 0; i < 8; i++) {
                for (int w = 0; w < 8; w++) {
                    if (!Objects.isNull(a[i][w])) {
                        if (a[i][w].getColor() == color) {
                            coordinate[] opp = legals(a, new coordinate((char) (i + 97), w + 1));

                            for (int y = 0; y < opp.length; y++) {
                                arr = append(arr, opp[y]);
                            }
                        }
                    }
                }
            }
            if (arr.length == 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean isStale(piece[][] a, boolean color) {
        coordinate[] arr = new coordinate[0];
        for (int i = 0; i < 8; i++) {
            for (int w = 0; w < 8; w++) {
                if (!Objects.isNull(a[i][w])) {
                    if (a[i][w].getColor() == color) {
                        coordinate[] opp = legals(a, new coordinate((char) (i + 97), w + 1));
                        if (opp.length>0){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean isCheck(piece[][] a, boolean color) {

        boolean kingfound = false;
        coordinate[] arr = new coordinate[0];
        coordinate king = new coordinate('i', 9);
        for (int i = 0; i < 8; i++) {
            for (int w = 0; w < 8; w++) {
                if (!Objects.isNull(a[i][w])) {
                    if (a[i][w].getColor() != color) {
                        coordinate[] opp = legal(a, new coordinate((char) (i + 97), w + 1));
                        for (int y = 0; y < opp.length; y++) {
                            if (opp[y].getx() == 4 && opp[y].gety() == 0) {
                                //System.out.println("RED ALERT RED ALERT CULPRIT FOUND:"+a[i][w]);
                            }
                            arr = append(arr, opp[y]);
                        }
                    } else if (a[i][w].getType() == 'k' && !kingfound) {

                        king = new coordinate((char) (i + 97), w + 1);
                        //System.out.println("king found at:"+king);
                        kingfound = true;
                    }
                }
            }
        }
        boolean found = false;
        for (int x = 0; x < arr.length; x++) {
            if ((arr[x].getx() == king.getx()) && (arr[x].gety() == king.gety())) {
                //System.out.println("king:"+king+" enemy attack:"+arr[x]+" "+x);
                found = true;
                break;
            }
        }
        //System.out.println();
        //System.out.println("end");
        return found;
    }

    public piece[][] getChecker() {
        return checker;
    }


    public piece getPiece(coordinate thing) {
        return checker[thing.getx()][thing.gety()];
    }

    public void setPiece(coordinate a, piece b) {
        checker[a.getx()][a.gety()] = b;
    }

    public void nullPiece(coordinate a) {
        checker[a.getx()][a.gety()] = null;
    }

    public static piece[][] movePiece(piece[][] boar, coordinate a, coordinate b) {
        piece[][] board = new piece[8][8];
        for (int x = 0; x < 8; x++) {
            for (int i = 0; i < 8; i++) {
                if (!Objects.isNull(boar[x][i])) {
                    board[x][i] = piece.clone(boar[x][i]);
                    if (board[x][i].getRecent()) {
                        board[x][i].setRecent(false);
                    }
                }
            }
        }
        piece temp = null;
        temp = piece.clone(board[a.getx()][a.gety()]);
        if (board[a.getx()][a.gety()].getType() == 'k') {
            //System.out.println("king");
            if (b.getx() == 6 && (a.getx() - b.getx() == -2)) {
                //System.out.println("short castle");
                piece rook = piece.clone(board[a.getx() + 3][a.gety()]);
                board[a.getx()][a.gety()] = null;
                board[b.getx()][b.gety()] = temp;
                board[b.getx() - 1][b.gety()] = rook;
                board[b.getx() + 1][b.gety()] = null;

                board[b.getx() - 1][b.gety()].setLocation(new coordinate(((char) (b.getx() - 1 + 97)), b.gety() + 1));
                board[b.getx()][b.gety()].setLocation(b);
                board[b.getx()][b.gety()].addMove();
                board[b.getx()][b.gety()].setRecent(true);
                board[b.getx() - 1][b.gety()].addMove();
                board[b.getx() - 1][b.gety()].setRecent(true);
            } else if (b.getx() == 2 && (a.getx() - b.getx() == 2)) {
                //System.out.println("long castle");
                piece rook = piece.clone(board[a.getx() - 4][a.gety()]);
                board[a.getx()][a.gety()] = null;
                board[b.getx()][b.gety()] = temp;
                board[b.getx() + 1][b.gety()] = rook;
                board[b.getx() - 2][b.gety()] = null;

                board[b.getx() + 1][b.gety()].setLocation(new coordinate(((char) (b.getx() + 1 + 97)), b.gety() + 1));
                board[b.getx()][b.gety()].setLocation(b);
                board[b.getx()][b.gety()].addMove();
                board[b.getx()][b.gety()].setRecent(true);
                board[b.getx() + 1][b.gety()].addMove();
                board[b.getx() + 1][b.gety()].setRecent(true);
            } else {
                board[a.getx()][a.gety()] = null;
                board[b.getx()][b.gety()] = temp;

                board[b.getx()][b.gety()].setLocation(b);
                board[b.getx()][b.gety()].addMove();
                board[b.getx()][b.gety()].setRecent(true);
            }
        } else if (board[a.getx()][a.gety()].getType() == 'p' ) {
            if(a.getx() != b.getx() && Objects.isNull(board[b.getx()][b.gety()])){
                //System.out.println("en passant");
                board[b.getx()][a.gety()] = null;
                board[a.getx()][a.gety()] = null;
                board[b.getx()][b.gety()] = temp;

                board[b.getx()][b.gety()].setLocation(b);
                board[b.getx()][b.gety()].addMove();
                board[b.getx()][b.gety()].setRecent(true);
            }
            else {
                board[a.getx()][a.gety()] = null;
                board[b.getx()][b.gety()] = temp;

                board[b.getx()][b.gety()].setLocation(b);
                board[b.getx()][b.gety()].addMove();
                board[b.getx()][b.gety()].setRecent(true);
                if (b.gety()==7||b.gety()==0){
                    board[b.getx()][b.gety()].setType('q');
                }
            }
        } else {
            board[a.getx()][a.gety()] = null;
            board[b.getx()][b.gety()] = temp;

            board[b.getx()][b.gety()].setLocation(b);
            board[b.getx()][b.gety()].addMove();
            board[b.getx()][b.gety()].setRecent(true);

        }

        return board;
    }

    public static piece[][] movePiece2(piece[][] boar, coordinate a, coordinate b) {
        piece[][] board = new piece[8][8];
        for (int x = 0; x < 8; x++) {
            for (int i = 0; i < 8; i++) {
                if (!Objects.isNull(boar[x][i])) {
                    board[x][i] = piece.clone(boar[x][i]);
                    if (board[x][i].getRecent()) {
                        board[x][i].setRecent(false);
                    }
                }
            }
        }
        piece temp = piece.clone(board[a.getx()][a.gety()]);

        board[a.getx()][a.gety()] = null;
        board[b.getx()][b.gety()] = temp;
        board[b.getx()][b.gety()].setLocation(b);
        board[b.getx()][b.gety()].addMove();
        board[b.getx()][b.gety()].setRecent(true);


        return board;
    }


    static coordinate[] legals(piece[][] checker, coordinate a) {
        piece thing = checker[a.getx()][a.gety()];
        coordinate[] arr = new coordinate[0];
        if (Objects.isNull(checker[a.getx()][a.gety()])) {
            return arr;
        }
        int x = thing.getLocation().getx();
        int y = thing.getLocation().gety();
        piece[][] temp = checker;
        if (thing.getType() == 'r') {
            int i = 1;
            while (true) {
                if (x + i < 8 && x + i > -1) {
                    if (Objects.isNull(checker[x + i][y])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        }
                        i++;
                    } else if (checker[x + i][y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }


            i = 1;
            while (true) {
                if (y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x][y + i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 + i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97), y + 1 + i));
                        }
                        i++;
                    } else if (checker[x][y + i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 + i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }


            i = 1;
            while (true) {
                if (x - i < 8 && x - i > -1) {
                    if (Objects.isNull(checker[x - i][y])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        }
                        i++;
                    } else if (checker[x - i][y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            while (true) {
                if (y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x][y - i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 - i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        }
                        i++;
                    } else if (checker[x][y - i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 - i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

        } else if (thing.getType() == 'n') {
            if (x > 1 && y > 0 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - 2), y)), thing.getColor())) {
                if (Objects.isNull(checker[x - 2][y - 1]) || (checker[x - 2][y - 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 2), y));
                }
            }
            if (x > 0 && y > 1 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - 1), y + 1 - 2)), thing.getColor())) {
                if (Objects.isNull(checker[x - 1][y - 2]) || (checker[x - 1][y - 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1 - 2));
                }
            }
            if (x < 7 && y > 1 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + 1), y + 1 - 2)), thing.getColor())) {
                if (Objects.isNull(checker[x + 1][y - 2]) || (checker[x + 1][y - 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1 - 2));
                }
            }
            if (x < 6 && y > 0 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + 2), y + 1 - 1)), thing.getColor())) {
                if (Objects.isNull(checker[x + 2][y - 1]) || (checker[x + 2][y - 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 2), y + 1 - 1));
                }
            }
            if (x < 6 && y < 7 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + 2), y + 1 + 1)), thing.getColor())) {
                if (Objects.isNull(checker[x + 2][y + 1]) || (checker[x + 2][y + 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 2), y + 1 + 1));
                }
            }
            if (x < 7 && y < 6 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + 1), y + 1 + 2)), thing.getColor())) {
                if (Objects.isNull(checker[x + 1][y + 2]) || (checker[x + 1][y + 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1 + 2));
                }
            }
            if (x > 0 && y < 6 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - 1), y + 1 + 2)), thing.getColor())) {
                if (Objects.isNull(checker[x - 1][y + 2]) || (checker[x - 1][y + 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1 + 2));
                }
            }
            if (x > 1 && y < 7 && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - 2), y + 1 + 1)), thing.getColor())) {
                if (Objects.isNull(checker[x - 2][y + 1]) || (checker[x - 2][y + 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 2), y + 1 + 1));
                }
            }
        } else if (thing.getType() == 'b') {
            int i = 1;
            while (true) {
                if (x + i < 8 && x + i > -1 && y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x + i][y + i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 + i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        }
                        i++;
                    } else if (checker[x + i][i + y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 + i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            while (true) {
                if (x + i < 8 && x + i > -1 && y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x + i][y - i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 - i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        }
                        i++;
                    } else if (checker[x + i][y - i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 - i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            while (true) {
                if (x - i < 8 && x - i > -1 && y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x - i][y + i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 + i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 + i));
                        }

                        i++;
                    } else if (checker[x - i][i + y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 + i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 + i));
                        break;
                    } else {

                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            while (true) {
                if (x - i < 8 && x - i > -1 && y - i < 8 && y - i > -1) {

                    if (Objects.isNull(checker[x - i][y - i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 - i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        }

                        i++;
                    } else if (checker[x - i][y - i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 - i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        break;
                    } else {

                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (thing.getType() == 'q') {
            int i = 1;
            while (true) {
                if (x + i < 8 && x + i > -1) {
                    if (Objects.isNull(checker[x + i][y])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        }
                        i++;
                    } else if (checker[x + i][y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }


            i = 1;
            while (true) {
                if (y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x][y + i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 + i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97), y + 1 + i));
                        }
                        i++;
                    } else if (checker[x][y + i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 + i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }


            i = 1;
            while (true) {
                if (x - i < 8 && x - i > -1) {
                    if (Objects.isNull(checker[x - i][y])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        }
                        i++;
                    } else if (checker[x - i][y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            while (true) {
                if (y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x][y - i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 - i)), thing.getColor())) {

                            arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        }
                        i++;
                    } else if (checker[x][y - i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97), y + 1 - i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            while (true) {
                if (x + i < 8 && x + i > -1 && y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x + i][y + i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 + i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        }
                        i++;
                    } else if (checker[x + i][i + y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 + i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            while (true) {
                if (x + i < 8 && x + i > -1 && y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x + i][y - i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 - i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        }
                        i++;
                    } else if (checker[x + i][y - i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 + i), y + 1 - i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            while (true) {
                if (x - i < 8 && x - i > -1 && y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x - i][y + i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 + i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 + i));
                        }

                        i++;
                    } else if (checker[x - i][i + y].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 + i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 + i));
                        break;
                    } else {

                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            while (true) {
                if (x - i < 8 && x - i > -1 && y - i < 8 && y - i > -1) {

                    if (Objects.isNull(checker[x - i][y - i])) {
                        if (!isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 - i)), thing.getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        }

                        i++;
                    } else if (checker[x - i][y - i].getColor() != thing.getColor() && !isCheck(movePiece(checker, a, new coordinate((char) (x + 97 - i), y + 1 - i)), thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        break;
                    } else {

                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (thing.getType() == 'k') {

            for (int c = -1; c < 2; c++) {

                for (int b = -1; b < 2; b++) {

                    if (x + c < 8 && x + c > -1 && y + b < 8 && y + b > -1 && (Objects.isNull(checker[x + c][y + b]) || checker[x + c][y + b].getColor() != thing.getColor()) && !(c == 0 && b == 0)) {

                        if (!isCheck(movePiece2(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 + c), y + 1 + b)), thing.getColor())) {

                            arr = append(arr, new coordinate((char) (x + 97 + c), y + 1 + b));
                        }
                    }

                }
            }
            if (thing.getMoves() == 0 && !isCheck(checker, thing.getColor())&&(y==8||y==0)) {
                if (Objects.isNull(checker[x + 1][y]) && Objects.isNull(checker[x + 2][y]) && !Objects.isNull(checker[x + 3][y]) && checker[x + 3][y].getMoves() == 0 && !isCheck(movePiece2(checker, new coordinate((char) (x + 97), y + 1), new coordinate(((char) (x + 97 + 1)), y + 1)), thing.getColor()) && !isCheck(movePiece2(checker, new coordinate((char) (x + 97), y + 1), new coordinate(((char) (x + 97 + 2)), y + 1)), thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 2), y + 1));
                } else if (Objects.isNull(checker[x - 1][y]) && Objects.isNull(checker[x - 2][y]) && Objects.isNull(checker[x - 3][y]) && !Objects.isNull(checker[x - 4][y]) && checker[x - 4][y].getMoves() == 0 && !isCheck(movePiece2(checker, new coordinate((char) (x + 97), y + 1), new coordinate(((char) (x + 97 - 1)), y + 1)), thing.getColor()) && !isCheck(movePiece2(checker, new coordinate((char) (x + 97), y + 1), new coordinate(((char) (x + 97 - 2)), y + 1)), thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 2), y + 1));
                }
            }

        } else {
            if (thing.getColor()) {
                if (x < 7) {  // checking if one square to the right is on the board
                    //System.out.println(checker[x + 1][y]);
                    if ((!Objects.isNull(checker[x + 1][y])) && (!checker[x + 1][y].getColor()) && (checker[x + 1][y].getType() == 'p') && (checker[x + 1][y].getMoves() == 1) && (checker[x + 1][y].getRecent()) && (checker[x + 1][y].getRecent()) && (checker[x + 1][y].getMoves() == 1)) {
                        if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 + 1), y + 2)), thing.getColor()))
                            arr = append(arr, new coordinate((char) (x + 97 + 1), y + 2));
                    }
                    if (y < 7) {  // checking if one square up is on the board
                        if ((!Objects.isNull(checker[x + 1][y + 1])) && (!checker[x + 1][y + 1].getColor())) {
                            if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 + 1), y + 2)), thing.getColor()))
                                arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1 + 1));
                        }
                    }
                }
                if (x > 0) {  // checking if one square to the left is on the board
                    //System.out.println(checker[x - 1][y]);
                    if ((!Objects.isNull(checker[x - 1][y])) && (!checker[x - 1][y].getColor()) && (checker[x - 1][y].getType() == 'p') && (checker[x - 1][y].getMoves() == 1) && (checker[x - 1][y].getRecent()) && (checker[x - 1][y].getMoves() == 1)) {
                        if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 - 1), y + 2)), thing.getColor()))
                            arr = append(arr, new coordinate((char) (x + 97 - 1), y + 2));
                    }
                    if (y < 7) {  // checking if one square up is on the board
                        if ((!Objects.isNull(checker[x - 1][y + 1])) && (!checker[x - 1][y + 1].getColor())) {
                            if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 - 1), y + 2)), thing.getColor()))
                                arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1 + 1));
                        }
                    }
                }
                if (y < 7) {  //  checking if one square up is on the board
                    if (Objects.isNull(checker[x][y + 1])) {
                        if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97), y + 2)), thing.getColor()))
                            arr = append(arr, new coordinate((char) (x + 97), y + 1 + 1));
                        if (y == 1) {  //  checking if the piece sits on its home square (x2)
                            if (Objects.isNull(checker[x][y + 2])) {
                                if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97), y + 2 + 1)), thing.getColor()))
                                    arr = append(arr, new coordinate((char) (x + 97), y + 2 + 1));
                            }
                        }
                    }
                }
            } else {
                if (x < 7) {  // checking if one square to the right is on the board
                    //System.out.println(checker[x + 1][y]);
                    if ((!Objects.isNull(checker[x + 1][y])) && (checker[x + 1][y].getColor()) && (checker[x + 1][y].getType() == 'p') && (checker[x + 1][y].getMoves() == 1) && (checker[x + 1][y].getRecent()) && (checker[x + 1][y].getRecent()) && (checker[x + 1][y].getMoves() == 1)) {
                        if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 + 1), y + 1)), thing.getColor()))
                            arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1));
                    }
                    if (y > 0) {  //  checking if one square down is on the board
                        if ((!Objects.isNull(checker[x + 1][y - 1])) && (checker[x + 1][y - 1].getColor())) {
                            if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 + 1), y + 1 - 1)), thing.getColor()))
                                arr = append(arr, new coordinate((char) (x + 97 + 1), y - 1 + 1));
                        }
                    }
                }
                if (x > 0) {  // checking if one square to the left is on the board
                    //System.out.println(checker[x - 1][y]);
                    if ((!Objects.isNull(checker[x - 1][y])) && (checker[x - 1][y].getColor()) && (checker[x - 1][y].getType() == 'p') && (checker[x - 1][y].getMoves() == 1) && (checker[x - 1][y].getRecent()) && (checker[x - 1][y].getRecent()) && (checker[x - 1][y].getMoves() == 1)) {
                        if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 - 1), y + 1)), thing.getColor()))
                            arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1));
                    }
                    if (y > 0) {  //  checking if one square down is on the board
                        if ((!Objects.isNull(checker[x - 1][y - 1])) && (checker[x - 1][y - 1].getColor())) {
                            if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97 - 1), y + 1 - 1)), thing.getColor()))
                                arr = append(arr, new coordinate((char) (x + 97 - 1), y - 1 + 1));
                        }
                    }
                }

                if (y > 0) {  //  checking if one square down is on the board
                    if (Objects.isNull(checker[x][y - 1])) {
                        if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97), y)), thing.getColor()))
                            arr = append(arr, new coordinate((char) (x + 97), y - 1 + 1));
                        if (y == 6) {  //  checking if the piece sits on its home square (x6)
                            if (Objects.isNull(checker[x][y - 2])) {
                                if (!isCheck(movePiece(checker, new coordinate((char) (x + 97), y + 1), new coordinate((char) (x + 97), y - 2 + 1)), thing.getColor()))
                                    arr = append(arr, new coordinate((char) (x + 97), y - 2 + 1));
                            }
                        }
                    }
                }
            }
        }
        return arr;
    }

    static coordinate[] append(coordinate[] arr, coordinate a) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = a;
        return arr;
    }


    static coordinate[] legal(piece[][] checker, coordinate a) {
        piece thing = checker[a.getx()][a.gety()];
        coordinate[] arr = new coordinate[0];
        if (Objects.isNull(checker[a.getx()][a.gety()])) {
            return arr;
        }
        //System.out.println(thing);
        int x = thing.getLocation().getx();
        int y = thing.getLocation().gety();
        piece[][] temp = checker;
        //System.out.print((char)(x+97));
        //System.out.println(a.lity());
        if (thing.getType() == 'r') {
            boolean legal = true;
            int i = 1;

            while (legal) {
                if (x + i < 8 && x + i > -1) {
                    if (Objects.isNull(checker[x + i][y])) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        i++;
                    } else if (checker[x + i][y].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            legal = true;
            while (legal) {
                if ((y + i < 8 && y + i > -1)) {
                    if (Objects.isNull(checker[x][y + i])) {
                        arr = append(arr, new coordinate((char) (x + 97), y + i + 1));
                        i++;
                    } else if (checker[x][y + i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            legal = true;
            i = 1;
            while (legal) {
                if (x - i < 8 && x - i > -1) {
                    if (Objects.isNull(checker[x - i][y])) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        i++;
                    } else if (checker[x - i][y].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            legal = true;
            while (legal) {
                if (y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x][y - i])) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        i++;
                    } else if ((checker[x][y - i].getColor() && !thing.getColor()) || (!checker[x][y - i].getColor() && thing.getColor())) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        break;
                    } else {
                        legal = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (thing.getType() == 'n') {
            if (x > 1 && y > 0) {
                if (Objects.isNull(checker[x - 2][y - 1]) || (checker[x - 2][y - 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 2), y));
                }
            }
            if (x > 0 && y > 1) {
                if (Objects.isNull(checker[x - 1][y - 2]) || (checker[x - 1][y - 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1 - 2));
                }
            }
            if (x < 7 && y > 1) {
                if (Objects.isNull(checker[x + 1][y - 2]) || (checker[x + 1][y - 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1 - 2));
                }
            }
            if (x < 6 && y > 0) {
                if (Objects.isNull(checker[x + 2][y - 1]) || (checker[x + 2][y - 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 2), y + 1 - 1));
                }
            }
            if (x < 6 && y < 7) {
                if (Objects.isNull(checker[x + 2][y + 1]) || (checker[x + 2][y + 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 2), y + 1 + 1));
                }
            }
            if (x < 7 && y < 6) {
                if (Objects.isNull(checker[x + 1][y + 2]) || (checker[x + 1][y + 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1 + 2));
                }
            }
            if (x > 0 && y < 6) {
                if (Objects.isNull(checker[x - 1][y + 2]) || (checker[x - 1][y + 2].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1 + 2));
                }
            }
            if (x > 1 && y < 7) {
                if (Objects.isNull(checker[x - 2][y + 1]) || (checker[x - 2][y + 1].getColor() != thing.getColor())) {
                    arr = append(arr, new coordinate((char) (x + 97 - 2), y + 1 + 1));
                }
            }
        } else if (thing.getType() == 'b') {
            boolean legal = true;
            int i = 1;
            while (legal) {
                if (x + i < 8 && x + i > -1 && y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x + i][y + i])) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        i++;
                    } else if (checker[x + i][i + y].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            legal = true;
            while (legal) {
                if ((y + i < 8 && y + i > -1 && x - i < 8 && x - i > -1)) {
                    if (Objects.isNull(checker[x - i][y + i])) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + i + 1));
                        i++;
                    } else if (checker[x - i][y + i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            legal = true;
            i = 1;
            while (legal) {
                if (y - i < 8 && y - i > -1 && x - i < 8 && x - i > -1) {
                    if (Objects.isNull(checker[x - i][y - i])) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        i++;
                    } else if (checker[x - i][y - i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            legal = true;
            while (legal) {
                if (x + i < 8 && x + i > -1 && y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x + i][y - i])) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        i++;
                    } else if (checker[x + i][y - i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (thing.getType() == 'q') {
            boolean legal = true;
            int i = 1;
            while (legal) {
                if (x + i < 8 && x + i > -1) {
                    if (Objects.isNull(checker[x + i][y])) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        i++;
                    } else if (checker[x + i][y].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            legal = true;
            while (legal) {
                if ((y + i < 8 && y + i > -1)) {
                    if (Objects.isNull(checker[x][y + i])) {
                        arr = append(arr, new coordinate((char) (x + 97), y + i + 1));
                        i++;
                    } else if (checker[x][y + i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            legal = true;
            i = 1;
            while (legal) {
                if (x - i < 8 && x - i > -1) {
                    if (Objects.isNull(checker[x - i][y])) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        i++;
                    } else if (checker[x - i][y].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            legal = true;
            while (legal) {
                if (y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x][y - i])) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        i++;
                    } else if (checker[x][y - i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            legal = true;
            i = 1;
            while (legal) {
                if (x + i < 8 && x + i > -1 && y + i < 8 && y + i > -1) {
                    if (Objects.isNull(checker[x + i][y + i])) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        i++;
                    } else if (checker[x + i][i + y].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            i = 1;
            legal = true;
            while (legal) {
                if ((y + i < 8 && y + i > -1 && x - i < 8 && x - i > -1)) {
                    if (Objects.isNull(checker[x - i][y + i])) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + i + 1));
                        i++;
                    } else if (checker[x - i][y + i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 + i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            legal = true;
            i = 1;
            while (legal) {
                if (y - i < 8 && y - i > -1 && x - i < 8 && x - i > -1) {
                    if (Objects.isNull(checker[x - i][y - i])) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        i++;
                    } else if (checker[x - i][y - i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 - i), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            i = 1;
            legal = true;
            while (legal) {
                if (x + i < 8 && x + i > -1 && y - i < 8 && y - i > -1) {
                    if (Objects.isNull(checker[x + i][y - i])) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        i++;
                    } else if (checker[x + i][y - i].getColor() != thing.getColor()) {
                        arr = append(arr, new coordinate((char) (x + 97 + i), y + 1 - i));
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (thing.getType() == 'k') {

            for (int c = -1; c < 2; c++) {

                for (int b = -1; b < 2; b++) {

                    if (x + c < 8 && x + c > -1 && y + b < 8 && y + b > -1 && (Objects.isNull(checker[x + c][y + b]) || checker[x + c][y + b].getColor() != thing.getColor()) && !(c == 0 && b == 0)) {

                        arr = append(arr, new coordinate((char) (x + 97 + c), y + 1 + b));
                    }

                }
            }


        } else {
            if (thing.getColor() == true) {
                if (x < 7) {  // checking if one square to the right is on the board
                    if ((!Objects.isNull(checker[x + 1][y])) && (!checker[x + 1][y].getColor()) && (checker[x + 1][y].getType() == 'p') && (checker[x + 1][y].getMoves() == 1) && (checker[x + 1][y].getRecent())) {
                        arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1));
                    }
                    if (y < 7) {  // checking if one square up is on the board
                        if ((!Objects.isNull(checker[x + 1][y + 1])) && (!checker[x + 1][y + 1].getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1 + 1));
                        }
                    }
                }
                if (x > 0) {  // checking if one square to the left is on the board
                    if ((!Objects.isNull(checker[x - 1][y])) && (!checker[x - 1][y].getColor()) && (checker[x - 1][y].getType() == 'p') && (checker[x - 1][y].getMoves() == 1) && (checker[x - 1][y].getRecent())) {
                        arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1));
                    }
                    if (y < 7) {  // checking if one square up is on the board
                        if ((!Objects.isNull(checker[x - 1][y + 1])) && (!checker[x - 1][y + 1].getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1 + 1));
                        }
                    }
                }
                if (y < 7) {  //  checking if one square up is on the board
                    if (Objects.isNull(checker[x][y + 1])) {
                        arr = append(arr, new coordinate((char) (x + 97), y + 1 + 1));
                        if (y == 1) {  //  checking if the piece sits on its home square (x2)
                            if (Objects.isNull(checker[x][y + 2])) {
                                arr = append(arr, new coordinate((char) (x + 97), y + 2 + 1));
                            }
                        }
                    }
                }
            } else {
                if (x < 7) {  // checking if one square to the right is on the board
                    if ((!Objects.isNull(checker[x + 1][y])) && (checker[x + 1][y].getColor()) && (checker[x + 1][y].getType() == 'p') && (checker[x + 1][y].getMoves() == 1) && (checker[x + 1][y].getRecent())) {
                        arr = append(arr, new coordinate((char) (x + 97 + 1), y + 1));
                    }
                    if (y > 0) {  //  checking if one square down is on the board
                        if ((!Objects.isNull(checker[x + 1][y - 1])) && (checker[x + 1][y - 1].getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 + 1), y - 1 + 1));
                        }
                    }
                }
                if (x > 0) {  // checking if one square to the left is on the board
                    if ((!Objects.isNull(checker[x - 1][y])) && (checker[x - 1][y].getColor()) && (checker[x - 1][y].getType() == 'p') && (checker[x - 1][y].getMoves() == 1) && (checker[x - 1][y].getRecent())) {
                        arr = append(arr, new coordinate((char) (x + 97 - 1), y + 1));
                    }
                    if (y > 0) {  //  checking if one square down is on the board
                        if ((!Objects.isNull(checker[x - 1][y - 1])) && (checker[x - 1][y - 1].getColor())) {
                            arr = append(arr, new coordinate((char) (x + 97 - 1), y - 1 + 1));
                        }
                    }
                }

                if (y > 0) {  //  checking if one square down is on the board
                    if (Objects.isNull(checker[x][y - 1])) {
                        arr = append(arr, new coordinate((char) (x + 97), y - 1 + 1));
                        if (y == 6) {  //  checking if the piece sits on its home square (x6)
                            if (Objects.isNull(checker[x][y - 2])) {
                                arr = append(arr, new coordinate((char) (x + 97), y - 2 + 1));
                            }
                        }
                    }
                }
            }
        }
        return arr;
    }
}
