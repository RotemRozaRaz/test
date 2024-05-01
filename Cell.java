package test;

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
