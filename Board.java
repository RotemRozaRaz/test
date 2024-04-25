package test;


public static class Board {

    private static Board board_copy = null;
    private Tile[][] board;

    public Board(){

        board = new Tile[15][15];

        for (int i=0; i < 15; i++){
            for (int j = 0; j < 15; j++) {
                board[i][j] = null;
            }
        }
    }


    public static Bag getBoard(){

        if (board_copy == null) {
            board_copy = new Board();
        }

        return board_copy;
    }

    public Tile[][] getTiles(){

        Board copy = new Board();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copy.board[i][j] = this.board[i][j];
            }
        }

        return copy;
    }
}
