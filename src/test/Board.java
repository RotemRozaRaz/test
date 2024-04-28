package test;
import java.util.ArrayList;
import java.util.List;


public class Board {

    private static Board board_copy = null;
    private Cell[][] board;
    private boolean isEmpty;
    private List<int[]> RedBonus = new ArrayList<>();
    private List<int[]> YellowBonus = new ArrayList<>();
    private List<int[]> BlueBonus = new ArrayList<>();
    private List<int[]> PaleBlueBonus = new ArrayList<>();
    private List<Word> wordsInBoard = new ArrayList<>();

    public Board(){

        board = new Cell[15][15];
        isEmpty = true;
        initBonuses();

        for (int i=0; i < 15; i++){
            for (int j = 0; j < 15; j++) {
                board[i][j] = new Cell(1, 1);
            }
        }

        initBoardBonuses();
    }

    private void initBonuses(){

        //Red
        RedBonus.add(new int[] {0,0});
        RedBonus.add(new int[] {0,7});
        RedBonus.add(new int[] {0,14});
        RedBonus.add(new int[] {7,0});
        RedBonus.add(new int[] {7,14});
        RedBonus.add(new int[] {14,0});
        RedBonus.add(new int[] {14,7});
        RedBonus.add(new int[] {14,14});

        //Yellow
        YellowBonus.add(new int[] {1,1});
        YellowBonus.add(new int[] {1,13});
        YellowBonus.add(new int[] {2,2});
        YellowBonus.add(new int[] {2,12});
        YellowBonus.add(new int[] {3,3});
        YellowBonus.add(new int[] {3,11});
        YellowBonus.add(new int[] {4,4});
        YellowBonus.add(new int[] {4,10});
        YellowBonus.add(new int[] {7,7});
        YellowBonus.add(new int[] {10,4});
        YellowBonus.add(new int[] {10,10});
        YellowBonus.add(new int[] {11,3});
        YellowBonus.add(new int[] {11,11});
        YellowBonus.add(new int[] {12,2});
        YellowBonus.add(new int[] {12,12});
        YellowBonus.add(new int[] {13,1});
        YellowBonus.add(new int[] {13,13});

        //Blue
        BlueBonus.add(new int[] {1, 5});
        BlueBonus.add(new int[] {1, 9});
        BlueBonus.add(new int[] {5, 1});
        BlueBonus.add(new int[] {5, 5});
        BlueBonus.add(new int[] {5, 9});
        BlueBonus.add(new int[] {5, 13});
        BlueBonus.add(new int[] {9, 1});
        BlueBonus.add(new int[] {9, 5});
        BlueBonus.add(new int[] {9, 9});
        BlueBonus.add(new int[] {9, 13});
        BlueBonus.add(new int[] {13, 5});
        BlueBonus.add(new int[] {13, 9});

        //Pale Blue
        PaleBlueBonus.add(new int[] {0, 3});
        PaleBlueBonus.add(new int[] {0, 11});
        PaleBlueBonus.add(new int[] {2, 6});
        PaleBlueBonus.add(new int[] {2, 8});
        PaleBlueBonus.add(new int[] {3, 0});
        PaleBlueBonus.add(new int[] {3, 7});
        PaleBlueBonus.add(new int[] {3, 14});
        PaleBlueBonus.add(new int[] {6, 2});
        PaleBlueBonus.add(new int[] {6, 6});
        PaleBlueBonus.add(new int[] {6, 8});
        PaleBlueBonus.add(new int[] {6, 12});
        PaleBlueBonus.add(new int[] {7, 3});
        PaleBlueBonus.add(new int[] {7, 11});
        PaleBlueBonus.add(new int[] {8, 2});
        PaleBlueBonus.add(new int[] {8, 6});
        PaleBlueBonus.add(new int[] {8, 8});
        PaleBlueBonus.add(new int[] {8, 12});
        PaleBlueBonus.add(new int[] {11, 0});
        PaleBlueBonus.add(new int[] {11, 7});
        PaleBlueBonus.add(new int[] {11, 14});
        PaleBlueBonus.add(new int[] {12, 6});
        PaleBlueBonus.add(new int[] {12, 8});
        PaleBlueBonus.add(new int[] {14, 3});
        PaleBlueBonus.add(new int[] {14, 11});


    }

    private void initBoardBonuses() {

        initRedBonuses();
        initYellowBonuses();
        initBlueBonuses();
        initPaleBlueBonuses(); 
    }

    private void initRedBonuses() {

        for (int[] cell : RedBonus) {
            board[cell[0]][cell[1]].setWordBonus(3); 
        }
    }

    private void initYellowBonuses() {

        for (int[] cell : YellowBonus) {
            board[cell[0]][cell[1]].setWordBonus(2);
        }
    }

    private void initBlueBonuses() {

        for (int[] cell : BlueBonus) {
            board[cell[0]][cell[1]].setLetterBonus(3); 
        }
    }

    private void initPaleBlueBonuses() {

        for (int[] cell : PaleBlueBonus) {
            board[cell[0]][cell[1]].setLetterBonus(2);; 
        }
    }

    public static Board getBoard(){

        if (board_copy == null) {
            board_copy = new Board();
        }

        return board_copy;
    }

    public Tile[][] getTiles(){

        Tile[][] copy = new Tile[15][15];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copy[i][j] = this.board[i][j].getTile();
            }
        }

        return copy;
    }

    private boolean isWordInBoard(Word w0) {
        
        if ((w0.getRow() < 0) || (w0.getCol() < 0) || (w0.getRow() > 14) || (w0.getCol() > 14)) {
            return false;
        }

        if (w0.isVertical()) {
            if (w0.getRow() + w0.getSize() < 15) {
                return true;
            }
        }

        else {
            if (w0.getCol() + w0.getSize() < 15) {
                return true;
            }
        }
        
        return false;
    }

    private boolean checkForNeighbors(Word w0, int curr_row, int curr_col) {

        if (w0.isVertical()) {
            if ((this.board[curr_row][curr_col + 1] != null) || (this.board[curr_row][curr_col - 1] != null)) {
                return true;
            }
        }

        else {
            if ((this.board[curr_row + 1][curr_col] != null) || (this.board[curr_row - 1][curr_col] != null)) {
                return true;
            }
        }

        return false;

    }

    private boolean isWordUsesAnotherWord(Word w0) {
         
        int curr_row = w0.getRow();
        int curr_col = w0.getCol();
        int i = 0;

        if (isEmpty) {
            if (w0.isVertical()) {
                if ((curr_col == 7) && (curr_row <= 7) && (curr_row + w0.getSize() > 7)) {
                    return true;
                }
            }
            else {
                if ((curr_row == 7) && (curr_col <= 7) && (curr_col + w0.getSize() > 7)) {
                    return true;
                }
            }
        }

        else if (w0.isVertical()) {
            
            while (curr_row < w0.getRow() + w0.getSize()) {
                if ((this.board[curr_row][curr_col].getTile() != null && w0.getTiles()[i] == null) || (this.board[curr_row][curr_col].getTile() == w0.getTiles()[i]) || (checkForNeighbors(w0, curr_row, curr_col))) {
                    return true;
                }

                i++;
                curr_row++;
            }
        }

        else {
            i = 0;

            while (curr_col < w0.getCol() + w0.getSize()) {
                if ((this.board[w0.getRow()][curr_col].getTile() != null && w0.getTiles()[i] == null) || (this.board[w0.getRow()][curr_col].getTile() == w0.getTiles()[i])  || (checkForNeighbors(w0, w0.getRow(), curr_col))) {
                    return true;
                }

                i++;
                curr_col++;
            }
        }

        return false;
    }

    private boolean isWordnotReplaceWord(Word w0) {

        int curr_row = w0.getRow();
        int curr_col = w0.getCol();
        int i = 0;

        if (isEmpty) {
            return true;
        }

        else if (w0.isVertical()) {
            
            while (curr_row < w0.getRow() + w0.getSize()) {
                if (w0.getTiles()[i] == null && this.board[curr_row][w0.getCol()].getTile() != null) {
                    i++;
                    curr_row++;
                    continue;
                }

                if (this.board[curr_row][w0.getCol()].getTile() != null && (w0.getTiles()[i] != this.board[curr_row][w0.getCol()].getTile())) {
                    return false;
                }

                i++;
                curr_row++;
            }
        }

        else {
            i = 0;

            while (curr_col < w0.getCol() + w0.getSize()) {
                if (this.board[w0.getRow()][curr_col].getTile() != null && w0.getTiles()[i] != this.board[w0.getRow()][curr_col].getTile()) {
                    return false;
                }

                i++;
                curr_col++;
            }
        }

        return true;
    }

	public boolean boardLegal(Word w0) {
		
        boolean word_in_board = isWordInBoard(w0);
        boolean word_use_word = isWordUsesAnotherWord(w0);
        boolean word_wont_replace = isWordnotReplaceWord(w0);

        if (word_in_board && word_use_word && word_wont_replace) {
            return true;
        }

        return false;
	}

    public boolean dictionaryLegal(Word w0){
        return true;
    }

    private Word findWordRight(int curr_row, int curr_col, int i, Word w0) {
        ArrayList<Tile> new_word = new ArrayList<>();
        int sCol = curr_col;

        while (this.board[curr_row][curr_col + 1].getTile() != null) {
            new_word.add(this.board[curr_row][curr_col].getTile());
            curr_col++;
        }

        new_word.add(w0.getTiles()[i]);

        Tile[] NW = new_word.toArray(new Tile[0]);

        Word w = new Word(NW, curr_row, sCol, false);
        if (wordsInBoard.contains(w)){
            return null;
        }

        return w;
    }

    private Word findWordleft(int curr_row, int curr_col , int i, Word w0) {
        ArrayList<Tile> new_word = new ArrayList<>();
        int sCol = 0;

        while (this.board[curr_row][curr_col - 1].getTile() != null) {
            curr_col--;
        }

        sCol = curr_col;

        while (this.board[curr_row][curr_col].getTile() != null) {
            new_word.add(this.board[curr_row][curr_col].getTile());
            curr_col++;
        }

        new_word.add(w0.getTiles()[i]);

        Tile[] NW = new_word.toArray(new Tile[0]);

        Word w = new Word(NW, curr_row, sCol, false);
        if (wordsInBoard.contains(w)){
            return null;
        }

        return w;
    }

    private ArrayList<Word> findNewWordsVertical(Word w0, int curr_row, int word_col, int word_size, int word_row) {

        ArrayList<Word> new_words = new ArrayList<>();
        int i = 0;
        while (curr_row < word_row + word_size) {

            if (this.board[curr_row][word_col - 1].getTile() != null && this.board[curr_row][word_col + 1].getTile() != null) {
                i++;
                curr_row++;
                continue;
            }

            else if (this.board[curr_row][word_col - 1].getTile() != null) {
                
                Word n_word = findWordleft(curr_row, word_col, i, w0);
                
                if (n_word != null) {
                    new_words.add(n_word);
                }
                
            }

            else if (this.board[curr_row][word_col + 1].getTile() != null) {

                Word n_word = findWordRight(curr_row, word_col, i, w0);

                if (n_word != null) {
                    new_words.add(n_word);
                }

                
            }
            i++;
            curr_row++;
        }

        return new_words;
    }

    
    private Word findWordUp(int curr_row, int curr_col, int i, Word w0) {
        ArrayList<Tile> new_word = new ArrayList<>();
        int sRow = 0;

        while (this.board[curr_row - 1][curr_col].getTile() != null) {
            curr_row--;
        }

        sRow = curr_row;

        while (this.board[curr_row][curr_col].getTile() != null) {
            new_word.add(this.board[curr_row][curr_col].getTile());
            curr_row++;
        }

        new_word.add(w0.getTiles()[i]);

        Tile[] NW = new_word.toArray(new Tile[0]);

        Word w = new Word(NW, sRow, curr_col, true);
        if (wordsInBoard.contains(w)){
            return null;
        }

        return w;
    }

    private Word findWordDown(int curr_row, int curr_col, int i, Word w0) {
        ArrayList<Tile> new_word = new ArrayList<>();
        int sRow = curr_row;

        while (this.board[curr_row][curr_col].getTile() != null) {
            new_word.add(this.board[curr_row][curr_col].getTile());
            curr_col--;
        }

        new_word.add(w0.getTiles()[i]);

        Tile[] NW = new_word.toArray(new Tile[0]);

        Word w = new Word(NW, sRow, curr_col, true);
        if (wordsInBoard.contains(w)){
            return null;
        }

        return w;
    }

    private ArrayList<Word> findNewWordsHorizonal(Word w0, int curr_col, int word_col, int word_size, int word_row) {

        
        ArrayList<Word> new_words = new ArrayList<>();
        int i = 0;

        while (curr_col < word_col + word_size) {

            if (this.board[word_row - 1][curr_col].getTile() != null && this.board[word_row + 1][curr_col].getTile() != null) {
                i++;
                curr_col++;
                continue;
            }

            else if (this.board[word_row - 1][curr_col].getTile() != null) {
                Word n_word = findWordUp(word_row, curr_col, i, w0);

                if (n_word != null) {
                    new_words.add(n_word);
                };
            }

            else if (this.board[word_row + 1][curr_col].getTile() != null) {
                Word n_word = findWordDown(word_row, curr_col, i, w0);

                if (n_word != null) {
                    new_words.add(n_word);
                }
            }

            curr_col++;
            i++;
        }

        return new_words;
    }

    public ArrayList<Word> getWords(Word w0){

        ArrayList<Word> words = new ArrayList<>();
        
        int word_row = w0.getRow();
        int word_col = w0.getCol();
        int word_size = w0.getSize();
        int curr_row = w0.getRow();
        int curr_col = w0.getCol();


        if (w0.isVertical()) {
            words = findNewWordsVertical(w0, curr_row, word_col, word_size, word_row);
        }

        else {
            words = findNewWordsHorizonal(w0, curr_col, word_col, word_size, word_row);
        }


        words.add(w0);

        return words;
    }


    private List<Character> ABCs(){
        List<Character> ABCs = new ArrayList<>();

        ABCs.add('A');
        ABCs.add('B');
        ABCs.add('C');
        ABCs.add('D');
        ABCs.add('E');
        ABCs.add('F');
        ABCs.add('G');
        ABCs.add('H');
        ABCs.add('I');
        ABCs.add('J');
        ABCs.add('K');
        ABCs.add('L');
        ABCs.add('M');
        ABCs.add('O');
        ABCs.add('P');
        ABCs.add('Q');
        ABCs.add('R');
        ABCs.add('S');
        ABCs.add('T');
        ABCs.add('U');
        ABCs.add('V');
        ABCs.add('W');
        ABCs.add('X');
        ABCs.add('Y');
        ABCs.add('Z');

        return ABCs;
    }

    private ArrayList<Integer> applyStarVertical(int curr_row, int curr_col, int word_col, ArrayList<Integer> wordBonuses) {

        if (curr_row == 7 && curr_col == 7) {
            if (this.isEmpty) {
                wordBonuses.add(this.board[curr_row][word_col].getWordBonus());
                this.board[curr_row][word_col].setWordBonus(1);
            }
        }
        return wordBonuses;
    }

    private ArrayList<Integer> applyStarHorizonal(int curr_row, int curr_col, int word_row, ArrayList<Integer> wordBonuses) {
 
        if (curr_row == 7 && curr_col == 7) {
            if (this.isEmpty) {
                wordBonuses.add(this.board[word_row][curr_col].getWordBonus());
                this.board[word_row][curr_col].setWordBonus(1);
            }
        }
        return wordBonuses;
    }

    /**
     * @param w0
     * @return
     */
    public int getScore(Word w0){

        int score = 0;
        int word_row = w0.getRow();
        int word_col = w0.getCol();
        int word_size = w0.getSize();
        int curr_col = w0.getCol();
        int curr_row = w0.getRow();
        Tile[] word_tiles = w0.getTiles();
        List<Character> ABCs = ABCs();

        int i = 0;
        ArrayList<Integer> wordBonuses = new ArrayList<>();
        int CheckSize = wordBonuses.size();

        if (w0.isVertical()) {
            
            while (curr_row < word_row + word_size) {

                if (word_tiles[i] == null) {
                    if (ABCs.contains(this.board[curr_row][word_col].getTile().letter)) {
                        score += this.board[curr_row][word_col].getTile().score * this.board[curr_row][word_col].getLetterBonus();
                        
                        CheckSize = wordBonuses.size();

                        wordBonuses = applyStarVertical(curr_row, curr_col, word_col, wordBonuses);

                        if (this.board[curr_row][word_col].getWordBonus() != 1  && (CheckSize == wordBonuses.size())) {
                            wordBonuses.add(this.board[curr_row][word_col].getWordBonus());
                        }
            
                        i++;
                        curr_row++;
                        continue;
                    }
            
                    else {
                        try {
                            throw new ChangeTileExeption("This tile is empty");
                        } catch (Exception e) {
                            System.out.println("This tile is empty");
                        }
                    } 
                }

                score += word_tiles[i].score * this.board[curr_row][word_col].getLetterBonus();

                CheckSize = wordBonuses.size();

                wordBonuses = applyStarVertical(curr_row, curr_col, word_col, wordBonuses);

                if (this.board[curr_row][word_col].getWordBonus() != 1 && (CheckSize == wordBonuses.size())) {
                    wordBonuses.add(this.board[curr_row][word_col].getWordBonus());
                }

                i++;
                curr_row++;
            }
        }

        else {
            while (curr_col < word_col + word_size) {

                if (word_tiles[i] == null) {
                    if (ABCs.contains(this.board[word_row][curr_col].getTile().letter)) {
                        score += this.board[word_row][curr_col].getTile().score * this.board[word_row][curr_col].getLetterBonus();

                        CheckSize = wordBonuses.size();
                        wordBonuses = applyStarHorizonal(curr_row, curr_col, word_row, wordBonuses);
            
                        if (this.board[curr_row][word_col].getWordBonus() != 1  && (CheckSize == wordBonuses.size())) {
                            wordBonuses.add(this.board[curr_row][word_col].getWordBonus());
                        }
            
                        i++;
                        curr_row++;
                        continue;
                    }

                    else {
                        try {
                            throw new ChangeTileExeption("This tile is empty");
                        } catch (Exception e) {
                            System.out.println("This tile is empty");
                        }
                    }
                }
                
                score += word_tiles[i].score * this.board[word_row][curr_col].getLetterBonus();

                CheckSize = wordBonuses.size();

                wordBonuses = applyStarHorizonal(curr_row, curr_col, word_row, wordBonuses);

                if (this.board[word_row][curr_col].getWordBonus() != 1 && (CheckSize == wordBonuses.size())) {
                    wordBonuses.add(this.board[word_row][curr_col].getWordBonus());
                }

                i++;
                curr_col++;
            }
        }

        for (int WordBonus : wordBonuses) {
            score *= WordBonus;
        }

        return score;
    }

    private void placeTheWord(Word w0) {

        int curr_col = w0.getCol();
        int curr_row = w0.getRow();
        int i = 0;

        if (w0.isVertical()) {
            
            while (curr_row < w0.getRow() + w0.getSize()) {
                
                if (w0.getTiles()[i] == null) {
                    i++;
                    curr_row++;
                    continue;
                }

                this.board[curr_row][w0.getCol()].setTile(w0.getTiles()[i]);

                i++;
                curr_row++;
            }
        }

        else {
            i = 0;

            while (curr_col < w0.getCol() + w0.getSize()) {
                
                if (w0.getTiles()[i] == null) {
                    i++;
                    curr_col++;
                    continue;
                }

                this.board[w0.getRow()][curr_col].setTile(w0.getTiles()[i]);

                i++;
                curr_col++;
            }
        }

        this.isEmpty = false;
    }

    public int tryPlaceWord(Word w0) {
        
        int word_score = 0;


        if (boardLegal(w0)) {
            ArrayList<Word> new_words = getWords(w0);

            for (int i = 0; i < new_words.size(); i++) {
                if (!dictionaryLegal(new_words.get(i)) || !boardLegal(new_words.get(i))) {
                    return 0;
                }
            }

            for (Word w : new_words) {
                word_score += getScore(w);
                wordsInBoard.add(w);
            }

            placeTheWord(w0);
            
        }

        return word_score;
    }


}
