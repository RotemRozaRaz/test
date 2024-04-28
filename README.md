
<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/RotemRozaRaz/test">
    <img src="logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Book Scrabble Project</h3>

  <p align="center">
    Fun version of the good old Scrrable game!
    <br />
    <a href="https://github.com/RotemRozaRaz/test"><strong>Explore the docs »</strong></a>
    <br />
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#the-rules-of-the-game">Built With</a></li>
      </ul>
    </li>
    <li><a href="#Classes">Usage</a></li>
    <ul>
        <li><a href="#Tile">Built With</a></li>
        <li><a href="#Bag">Built With</a></li>
        <li><a href="#Word">Built With</a></li>
        <li><a href="#Cell">Built With</a></li>
        <li><a href="#Board">Built With</a></li>
      </ul>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

The project purpuse is to create the game called Book Scrabble.
In this project, the students learn about desing patterns in Java and earning new tools for programming. 

This project is part of the Advanced Programming course that has two parts:
* Part 1 - learning design patterns, learning Java, learning backend.
* Part 2 - learning more design patterns, learning frontend and desinging the front app for the game.

This git repository is for the srudents to save and share their code using platforms that used in the Hi-Tech indestry.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With


* Java

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### The Rules of The Game

1. Each player randomly draws a tile from the bag
2. The order of the players is determined by the order of the letters drawn (from smallest to largest)
  * If an empty tile is drawn, we will return it to the bag and draw another one.
3. All the tiles return to the bag
4. Each player randomly draws 7 tiles
5. The first player (the one who drew the smallest letter in the lottery) must form a legal word
which passes through the central slot (the star) in the board.
  * Only he gets a double score for her.
  * He completes from the bag so that he has 7 tiles again.
6. Gradually, each player, in turn, assembles a legal word from the tiles in his possession
  * Where as in a crossword puzzle, each word must rest on one of the tiles that exist on the board.
  * After writing the word, the player adds 7 tiles from the sack
  * His score is accumulated according to all the words created on the board following the placement of the tiles
    - Tiles that are placed on double or triple letter tiles will double or triple their value respectively
    - The word then receives the sum of its tile value
    - This sum will be doubled or tripled for each doubling or tripling word slot that is one of the tiles superimposed on it (that is, it is possible, for example, to multiply by 4 or 9 if the word took two).
    (double word or triple word slots respectively)
    - The above calculation is true for each *new word* created on the board following the placement in the queue
7. A player who cannot form a legal word forfeits his turn.
8. The game will end after N rounds.
  A legal word must meet all of the following conditions:
  Written from left to right or from top to bottom (and not in any other way)
  * A word that appears in one of the books chosen for the game
  * Leans on one of the existing tiles on the board
  * Does not produce other illegal words on the board

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- Classes -->
## Classes

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap
### Tile

- We would like objects of the class type to be immutable - that is, they cannot be changed.
  - We will achieve this result by having its fields be defined as final.
  - The constructor will have to initialize these variables
- Define the fields letter char for a letter, and the score int for the score.
  - Since they are final, we have no problem with them being defined as public
- Add automatically using your IDE
  - A constructor that initializes these fields, equals, and hashCode

We don't want anyone who wants to be able to produce tiles. We want to control their quantities for the good of the game. so
The permission of the builder will be private!

However, we will implement a public and static class called *Bag* within the Tile class, and thus this class will be the only
with the option to create tiles.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Bag

- Hold an array of 26 ints representing the amount of each letter according to the game settings.
  - For example cell 0 represents A and in it the value 9 which represents that there are 9 tiles of type A
  - in cell 1 that represents B will be 2 and so on. .. in cell 25 representing Z will be .1
- Hold an array of 26 tiles, arranged according to the ABC
  - Each tile with its letter and value according to the game settings (all capital letters)
  - In fact, we don't need any more Tile objects other than those defined in the array.
- The getRand() method will return a random tile from the bag
  - It actually returns a value by (reference) to one of the cells in the tile array.
  - It subtracts the appropriate quantity from the array of quantities
  - Of course it is not possible to get any tile if its quantity has dropped to 0
  - If the bag is empty it will simply return null
- The getTile() method will work similarly to getRand, except that it will receive a char and output a tile that is its signal from the bag if given, otherwise return null.
- The put() method given a tile is "return it to the bag"
  - Actually you just need to update the quantity.
  - In any case, this method will not allow income beyond the amount defined in the game rules
- The size method will return as an int the amount of tiles inside the bag.
- For testing purposes, the method getQuantities will return a copy of the array of quantities

And to ensure that there is only one bag in the program, here too the constructor of Bag will be private, and in addition we will create a public method and static () getBag which will return us an instance of Bag according to Singleton logic:

We will create a static and private variable of type Bag that is initialized to null. In the getBag method we will check whether this variable is null,
If so, reboot it. And in any case we will return the reference to it. Thus the first to invoke the method produces the the object and everything else will get a reference to that object. This pattern is called Singleton.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Word

This class represents a possible placement of a word on the game board. We will define the following fields:
- tiles – an array of the tiles that make up the word
- col, row - which define the position (row, column) of the first tile in the word on the game board
- vertical – a boolean representing whether the word is written vertically (from top to bottom). If it is 'false' then the word is written horizontally (from left to right).

The class constructor will initialize all the fields according to the order above. Each field will have its own getter. In addition, we will need he equals method. You can write everything automatically using your IDE.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Cell

This class represnts a single cell in the board. 
The data members of this class are:
- tile - Tile object that repesents the letter and its score.
- letterBonus - Int value that contains 1 if the cell has no letter bonuses and 2,3 according to the board.
- wordBonus - Int value that contains 1 if the cell has no word bonuses and 2,3 according to the board.

In this calss there's getters and setters for each data members to remain the data members private.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Board

- For the purpose of practice, this class will also implement the Singleton pattern we saw above, with the static method
getBoard() will return us the reference to the single instance of the game board.
- This department holds the game board (choose how)
- The getTiles() method will return a 2D array of tiles according to the state of the board.
  - Where there is no tile on the board will simply be null.
  - Pay attention! The tiles are immutable but the array is not. Someone will be able to add tiles to it that are not through Board and therefore here too we would like to return a copy of the array.
    - And it's not bad because in the end it's just voters.

The following methods refer to the placement of a possible word on the board. Notice how instead of one method of placeWord that had to do the actual placement and check that the word is valid on the board and according to the dictionary and calculate the score for each word created, etc., we break it down into several different methods according to the *Single Responsibility* principle.
- The boardLegal() method will receive an instance of Word and return 'true' if:
  - The whole word is inside the board
  - Rest on one of the existing tiles on the board as in a crossword puzzle (adjacent or overlapping tile)
    - As you remember, the first placement is based on the star slot
  - Did not require replacement of existing tiles.
Otherwise it will return 'false'.

For example, from the example above, in the first line (HORN) we saw that the entire word entered the board, and indeed one of the tiles rests on the star.
For the placement of FARM we will additionally make sure that one of the tiles is adjacent or overlaps with one of the existing tiles on the board. The R tile provides this requirement. Also, we will have to make sure that R is the same as R that existed already on the board in the placement of HORN so the word HORN has not been replaced.

- The dictionaryLegal() method will check if the word is legal in terms of the game dictionary (words that appear in the selected books). For now it will always return true.
- The getWords () method - given a Word, it will return us an array of all the new words that will be created on the board includes the same word, if there was such a placement on the board. Examples:
  - For PASTE in line 3 above, an array containing PASTE and FARMS will be returned.
  - For MOB line 4 above, an array will be returned that contains BE, NOT, MOB.
  - The order of the words in the array does not matter
  - Instead of a primitive array []Word, this time you will return an object of type ArrayList<Word>.
    - This object allows the array it holds to grow dynamically.
  - The getScore() method given a word will calculate the total score of the word, including all squares the bonus on which it rests.

Pay attention! So far, no method performs a placement on the board. These were auxiliary methods.

Now, given a possible word for placement, we can check using the methods above, whether it is legal in terms of the board, if so then demand all the new words that would have been created what is the possible placement of the word, and for each word one to check if it is legal in terms of the game dictionary. If all words are indeed valid then we can finally execute the
The actual placement on the board and therefore we will return the cumulative score for each new word created. In any other case, no a placement will be made and we will return a score of 0
This is exactly what you must do in the tryPlaceWord() method, which given a Word will return an appropriate score.
Note that the placement contains only the new tiles to be placed on the board, while the various tests contain the whole word. For example, when we placed FARM in the second row, we placed only M_FA on the board (in place of the R there is a null tile) but all the various tests before placing it on the board checked the word FARM in full.

<p align="right">(<a href="#readme-top">back to top</a>)</p>




