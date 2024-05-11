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

    
    /** 
     * @return Board
     */
    public static Board getBoard(){

        if (board_copy == null) {
            board_copy = new Board();
        }

        return board_copy;
        // return null;
    }

    
    /** 
     * @return Tile[][]
     */
    public Tile[][] getTiles(){

        Tile[][] copy = new Tile[15][15];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copy[i][j] = this.board[i][j].getTile();
            }
        }

        return copy;
        // return null;
    }

    
    /** 
     * @param w0
     * @return boolean
     */
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

    
    /** 
     * @param w0
     * @param curr_row
     * @param curr_col
     * @return boolean
     */
    private boolean checkForNeighbors(Word w0, int curr_row, int curr_col) {

        if (w0.isVertical()) {

            if (curr_col == 0) {
                if (this.board[curr_row][curr_col + 1].getTile() != null) {
                    return true;
                }
            }

            else if ((this.board[curr_row][curr_col + 1].getTile() != null) || (this.board[curr_row][curr_col - 1].getTile() != null)) {
                return true;
            }
        }

        else {

            if (curr_row == 0) {
                if (this.board[curr_row + 1][curr_col].getTile() != null) {
                    return true;
                }
            }

            else if ((this.board[curr_row + 1][curr_col].getTile() != null) || (this.board[curr_row - 1][curr_col].getTile() != null)) {
                return true;
            }
        }

        return false;

    }

    
    /** 
     * @param w0
     * @return boolean
     */
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
                boolean nullLetter = board[w0.getRow()][curr_col].getTile() != null && w0.getTiles()[i] == null;
                boolean lettersNotEqaul = board[w0.getRow()][curr_col].getTile() == w0.getTiles()[i];
                boolean checkForNeighbors = checkForNeighbors(w0, w0.getRow(), curr_col);
                if (nullLetter || lettersNotEqaul || checkForNeighbors) {
                    return true;
                }

                i++;
                curr_col++;
            }
        }

        return false;
    }

    /** 
     * @param w0
     * @return boolean
     */
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
                    
                }

                else if ((this.board[curr_row][w0.getCol()].getTile() != null && (w0.getTiles()[i] != this.board[curr_row][w0.getCol()].getTile())) || (this.board[curr_row][w0.getCol()].getTile() == null && w0.getTiles()[i] == null)) {
                    return false;
                }

                i++;
                curr_row++;
            }
        }

        else {
            i = 0;

            while (curr_col < w0.getCol() + w0.getSize()) {

                if (w0.getTiles()[i] == null && this.board[w0.getRow()][curr_col].getTile() != null) {
                    
                }

                else if (this.board[w0.getRow()][curr_col].getTile() != null && w0.getTiles()[i] != this.board[w0.getRow()][curr_col].getTile()) {
                    return false;
                }

                i++;
                curr_col++;
            }
        }

        return true;
    }

    /** 
     * @param w0
     * @return boolean
     * 
     */
	public boolean boardLegal(Word w0) {
		
        boolean word_in_board = isWordInBoard(w0);
        boolean word_use_word = isWordUsesAnotherWord(w0);
        boolean word_wont_replace = isWordnotReplaceWord(w0);

        if (word_in_board && word_use_word && word_wont_replace) {
            return true;
        }

        return false;
	}

    /** 
     * @param w0
     * @return boolean
     */
    public boolean dictionaryLegal(Word w0){
        return true;
    }

    /** 
     * @param tile_row
     * @param tile_col
     * @param letter
     * @return Word
     */
    private Word findSingleWordHorizontal (int tile_row, int tile_col, Tile letter) {
        
        int i = tile_col - 1;
        while (i >= 0 && this.board[tile_row][i].getTile() != null) {
            i--;
        }

        i++;

        int j = tile_col + 1;
        while (j < this.board.length && this.board[tile_row][j].getTile() != null) {
            j++;
        }

        j--;

        ArrayList<Tile> new_word = new ArrayList<>();

        for (int j2 = i; j2 < j+1; j2++) {

            if (j2 == tile_row || this.board[tile_row][j2].getTile() == null) {
                new_word.add(letter);
            }
            else {
                new_word.add(this.board[tile_row][j2].getTile());
            }
        }

        Tile[] NW = new_word.toArray(new Tile[0]);

        Word w = new Word(NW, tile_row, i, false);

        if (wordsInBoard.contains(w)) {
            return null;
        }

        return w;

        // return null;

    }

    /** 
     * @param tile_row
     * @param tile_col
     * @param letter
     * @return Word
     */
    private Word findSingleWordVertical (int tile_row, int tile_col, Tile letter) {

        int i = tile_row - 1;
        while (i >= 0 && this.board[i][tile_col].getTile() != null) {
            i--;
        }
        i++;

        int j = tile_row + 1;
        while (j <= this.board.length && this.board[j][tile_col].getTile() != null){
            j++;
        }
        j--;

        ArrayList<Tile> new_word = new ArrayList<>();

        for (int j2 = i; j2 < j+1; j2++) {

            if (j2 == tile_row || this.board[j2][tile_col].getTile() == null) {
                new_word.add(letter);
            }

            else {
                new_word.add(this.board[j2][tile_col].getTile());
            }
        }

        Tile[] NW = new_word.toArray(new Tile[0]);

        Word w = new Word(NW, i, tile_col, true);

        if (wordsInBoard.contains(w)) {
            return null;
        }

        return w;

        // return null;
    }
    
    
    /** 
     * @param w0
     * @param curr_row
     * @param word_col
     * @param word_size
     * @param word_row
     * @return ArrayList<Word>
     */
    private ArrayList<Word> findNewWordsVertical(Word w0, int curr_row, int word_col, int word_size, int word_row) {

        ArrayList<Word> new_words = new ArrayList<>();
        int i = 0;
        while (curr_row < word_row + word_size) {

            if (this.board[curr_row][word_col - 1].getTile() != null || this.board[curr_row][word_col + 1].getTile() != null) {
                
                Tile letter = null;

                if (this.board[curr_row][word_col].getTile() == null) {
                    letter = w0.getTiles()[i];
                }
                else if (this.board[curr_row][word_col].getTile() != null) {
                    letter = this.board[curr_row][word_col].getTile();
                }

                Word n_word = findSingleWordHorizontal(curr_row, word_col, letter);
                
                if (n_word != null) {
                    new_words.add(n_word);
                }
                
            }

            i++;
            curr_row++;
        }

        return new_words;
        // return null;
    }

    
    /** 
     * @param w0
     * @param curr_col
     * @param word_col
     * @param word_size
     * @param word_row
     * @return ArrayList<Word>
     */
    private ArrayList<Word> findNewWordsHorizontal(Word w0, int curr_col, int word_col, int word_size, int word_row) {

        
        ArrayList<Word> new_words = new ArrayList<>();
        int i =0;
        while (curr_col < word_col + word_size) {

            if (this.board[word_row - 1][curr_col].getTile() != null || this.board[word_row + 1][curr_col].getTile() != null) {

                Tile letter = null;

                if (this.board[word_row][curr_col].getTile() == null) {
                    letter = w0.getTiles()[i];
                }
                else if (this.board[word_row][curr_col].getTile() != null) {
                    letter = this.board[word_row][curr_col].getTile();
                }

                Word n_word = findSingleWordVertical(word_row, curr_col, letter);

                if (n_word != null) {
                    new_words.add(n_word);
                };
            }

            curr_col++;
            i++;
        }

        return new_words;
        // return null;
    }

    
    /** 
     * @param w0
     * @return ArrayList<Word>
     */
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
            words = findNewWordsHorizontal(w0, curr_col, word_col, word_size, word_row);
        }


        words.add(w0);

        return words;

        // return null;
    }


    
    /** 
     * @return List<Character>
     */
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

    
    /** 
     * @param curr_row
     * @param curr_col
     * @param word_col
     * @param wordBonuses
     * @return ArrayList<Integer>
     */
    private ArrayList<Integer> applyStarVertical(int curr_row, int curr_col, int word_col, ArrayList<Integer> wordBonuses) {

        if (curr_row == 7 && curr_col == 7) {
            if (this.isEmpty) {
                wordBonuses.add(this.board[curr_row][word_col].getWordBonus());
                this.board[curr_row][word_col].setWordBonus(1);
            }
        }
        return wordBonuses;
        // return null;
    }


    
    /** 
     * @param curr_row
     * @param curr_col
     * @param word_row
     * @param wordBonuses
     * @return ArrayList<Integer>
     */
    private ArrayList<Integer> applyStarHorizonal(int curr_row, int curr_col, int word_row, ArrayList<Integer> wordBonuses) {
 
        if (curr_row == 7 && curr_col == 7) {
            if (this.isEmpty) {
                wordBonuses.add(this.board[word_row][curr_col].getWordBonus());
                this.board[word_row][curr_col].setWordBonus(1);
            }
        }
        return wordBonuses;
        // return null;
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
                        curr_col++;
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

        // return 0;
    }

    
    /** 
     * @param w0
     */
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

    
    /** 
     * @param w0
     * @return int
     */
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
        // return 0;
    }

    public class Cell {
    
        private Tile tile;
        private int letterBonus;
        private int wordBonus;
    
    
        public Cell(int inletterBonus, int inwordBonus) {
    
            this.tile = null;
            this.letterBonus = inletterBonus;
            this.wordBonus = inwordBonus;
        }
    
    
        protected Tile getTile() {
            return this.tile;
        }
    
        protected int getLetterBonus() {
    
            return this.letterBonus;
        }
    
        protected int getWordBonus() {
            return this.wordBonus;
        }
    
        protected int applyWordBonus(int sum) {
            return wordBonus * sum;
        }
    
        protected void setTile(Tile inTile) {
    
            if (this.tile != null) {
                
                try {
                    throw new ChangeTileExeption("This tile runs over an existing one");
                } catch (ChangeTileExeption e) {
                    System.out.println(e.getMessage());
                }
    
            }
    
            else {
                this.tile = inTile;
            }
        }
    
        protected void setLetterBonus(int bonus) {
            this.letterBonus = bonus;
        }
    
        protected void setWordBonus(int bonus) {
            this.wordBonus = bonus;
        }
    
    }

    public class ChangeTileExeption extends Exception {
    
    public ChangeTileExeption(String message) {
        super(message);
    }
}

}