package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Tile {

    public final char letter;
    public final int score;
    private final Map<Character, Integer> scores = init_score_dict();

    private Tile(char letter) {
        this.letter = letter;
        this.score = init_score(letter);
    }

   
    
    /**
     * Initialize dictionary of each letter and its score
     * @return Map<Character, Integer>
     */
    private Map<Character, Integer> init_score_dict(){

        Map<Character, Integer> scores = new HashMap<>();

        scores.put('A', 1);
        scores.put('B', 3);
        scores.put('C', 3);
        scores.put('D', 2);
        scores.put('E', 1);
        scores.put('F', 4);
        scores.put('G', 2);
        scores.put('H', 4);
        scores.put('I', 1);
        scores.put('J', 8);
        scores.put('K', 5);
        scores.put('L', 1);
        scores.put('M', 3);
        scores.put('N', 1);
        scores.put('O', 1);
        scores.put('P', 3);
        scores.put('Q', 10);
        scores.put('R', 1);
        scores.put('S', 1);
        scores.put('T', 1);
        scores.put('U', 1);
        scores.put('V', 4);
        scores.put('W', 4);
        scores.put('X', 8);
        scores.put('Y', 4);
        scores.put('Z', 10);

        return scores;
    }

    
    /** 
     * Initialize the score data member
     * @param letter
     * @return int
     */
    private int init_score(char letter) {

        return this.scores.get(letter);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + score;
        return result;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (letter != other.letter)
            return false;
        if (score != other.score)
            return false;
        return true;
    };

    public static class Bag {

        final int[] tiles_count_init;
        protected int[] tiles_count;
        protected Tile[] tiles;
        private static Bag bag_copy = null;


        private Bag() {

            tiles_count_init = new int[] {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};

            tiles_count = new int[] {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};

            tiles = new Tile[] {new Tile('A'), new Tile('B'), new Tile('C'), new Tile('D'), new Tile('E'), new Tile('F'), new Tile('G'), new Tile('H'), new Tile('I'), new Tile('J'), new Tile('K'), new Tile('L'), new Tile('M'), new Tile('N'), new Tile('O'), new Tile('P'), new Tile('Q'), new Tile('R'), new Tile('S'), new Tile('T'), new Tile('U'), new Tile('V'), new Tile('W'), new Tile('X'), new Tile('Y'), new Tile('Z')};
        }


        /** 
        * Checking if the current bag has no tiles
        if item != 0 return false
        * @return boolean
        */
        private boolean check_if_bag_empty() {

            for (int item : tiles_count) {
                if (item != 0){
                    return false;
                }
            }

            return true;
        }

        /**
         * Sends random tile to the user, if bag is empty - return null
         * READ AGAIN
         * @return Tile
         */
        public Tile getRand() {

            if (check_if_bag_empty()){
                return null;
            }

            Tile NewTile = null;
            Random random = new Random();
            int RandomTile = random.nextInt(this.tiles.length);

            if (tiles_count[RandomTile] != 0){
                
                tiles_count[RandomTile]--;

                NewTile = new Tile(tiles[RandomTile].letter);
            }

            return NewTile;
        }


        /**
         * Sends tile by request from the user, if it doesn't exists in the bag - return null
         * @param inletter
         * @return Tile
         */
        public Tile getTile(char inletter) {

            if (inletter == '_' || !Character.isUpperCase(inletter)) {
                return null;
            }
            return tiles[inletter - 'A'];
        }

        /**
         * insert tile back to the bag
         * @param tile
         */
        public void put(Tile tile) {

            // char inLetter = Character.toUpperCase(tile.letter);
            int tileIndex = tile.letter - 'A';

            if (tiles_count[tileIndex] == tiles_count_init[tileIndex]) {
                return;
            }

            tiles_count[tileIndex]++;
        }
        
        /**
         * return the number of tiles in the bag
         * @return int
         */
        public int size() {

            int num_of_tiles = 0;
            for (int tile_count : tiles_count){
                num_of_tiles += tile_count;
            }

            return num_of_tiles;
        }


        /**
         * returns the initialized values of the tiles count
         * clone
         * @return int[]
         */
        public int[] getQuantities() {
            
            int[] tiles_count_copy = tiles_count.clone();

            return tiles_count_copy;
        }


        /**
         * return copy of Bag to implemnt singletone design pattern
         * @return Bag
         */
        public static Bag getBag(){

            if (bag_copy == null) {
                bag_copy = new Bag();
            }

            return bag_copy;
        }
    }    

}