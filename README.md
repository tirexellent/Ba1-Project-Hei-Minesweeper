Codesweeper, Se7en:

A simple implementation of the classic Minesweeper game in Scala using the FunGraphics library. The game consists of a grid of cells, some of which contain mines. The objective of the game is to uncover all the cells that do not contain mines without triggering any of them.

Getting Started:

To run the game, compile and run the Minesweeper.scala file using the Scala compiler.

Gameplay:

-The game is played on a 10x10 grid.

-The number of mines in the grid is set to 10 by default but can be changed by modifying the nbmines variable.

-To uncover a cell, click on it with the left mouse button.

-To flag a cell as containing a mine, right-click on it.

-The game is won when all the cells that do not contain mines have been uncovered.

-The game is lost when a cell containing a mine is uncovered.
  
  Code Structure:

  
The code is organized as follows:

The Variables are all together at the beginning of the code, followed by the functions in a logical order of use and lastly, the game's loop is at the end and uses everything mentioned before.
    
-The "Cell" class represents a single cell in the grid. It contains information about whether the cell contains a mine, the number of neighboring mines, whether the cell is visible, and whether the cell is flagged as containing a mine.

-The "createArray" function initializes the grid with default values.

-The "placeMines" function places the mines in the grid and calculates the number of neighboring mines for each cell.

-The "uncover" function uncovers a cell and all its neighboring cells that do not contain mines.

-The "safe" function uncovers all the neighboring cells of a cell until the first one with a number is reached.

-The "display" function displays the game's window.

-The "win" function checks if the game has been won.

-The "mouseClicked" function handles mouse clicks and updates the game state accordingly.

Dependencies:
  
The code depends on the following libraries:

FunGraphics: A simple graphics library for Scala.
AWT: A standard Java library for creating user interfaces.

License:

This project is licensed under the HEVS, ISC, 1st Semester end-Project.

![empty_Minesweeper](https://github.com/tirexellent/Ba1-Project-Hei-Minesweeper/assets/95232629/c9064fb9-ef09-46e5-aca1-c27b8d77a6e2)
![you_Win_Minesweeper](https://github.com/tirexellent/Ba1-Project-Hei-Minesweeper/assets/95232629/b30bece7-e74d-4daf-b644-ddda638ffba8)
![you_Lose_Minesweeper](https://github.com/tirexellent/Ba1-Project-Hei-Minesweeper/assets/95232629/e01fb84e-593c-4636-bdff-2da13c52eaf1)
