import hevs.graphics.FunGraphics
import java.awt.event.{MouseAdapter, MouseEvent}
import java.awt.{Color}

object Minesweeper extends App {
  //all the variables
  val rows: Int = 10
  val cols: Int = 10
  var nbmines: Int = 10
  var nbturn: Int = 1
  var uncvrx: Int = 0
  var uncvry: Int = 0
  var posx: Int = 0
  var posy: Int = 0
  var Button: Int = 0


  val gamewindow = new FunGraphics(645, 645, "Ba1 Project, Minesweeper Se7en")

  // Initialise class cell to get enough info in every cell
  class Cell(var isMine: Boolean, var count: Int, var isVisible: Boolean,var isSafe : Int , var flag: Boolean) {
    override def toString: String = s"($isMine, $count, $isVisible, $isSafe, $flag) "
  }

  // Initialize cells with default values
  def createArray(rows: Int, cols: Int): Array[Array[Cell]] = {
    val array = Array.ofDim[Cell](rows, cols)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        array(i)(j) = new Cell(false, 0, false,0,false)
      }
    }
    array
  }

  //Ensures the position of the mines in the right amount
  //+ determines the number of each tile based on the neighboring mine-s
  def placeMines(): Unit = {
    var i : Int = 0
    while (i < nbmines) {
      val x = (math.random() * 10).toInt
      val y = (math.random() * 10).toInt
      if (!fullArea(x)(y).isMine) {
        fullArea(x)(y).isMine = true
        i += 1
        for (posminesx: Int <- -1 to 1) {
          if (0 <= (x + posminesx) && (x + posminesx) <= rows - 1) {
            for (posminesy: Int <- -1 to 1) {
              if (0 <= (y + posminesy) && (y + posminesy) <= cols - 1) {

                fullArea(x + posminesx)(y + posminesy).count += 1
              }
            }
          }
        }
      }
    }
    for (row <- fullArea) {
      for (cell <- row) {
        if (cell.count != 0) cell.isSafe = -1
        else {
          cell.isSafe = 0
        }
      }
    }
  }

  //On the first turn the first discovered tile needs to be a 0
  def uncover(uncvrx: Int, uncvry : Int): Unit ={
    if (fullArea(uncvrx)(uncvry).isSafe == 0) {
      safe(uncvrx, uncvry,0)
    }
    else {
      while (fullArea(uncvrx)(uncvry).isSafe != 0) {
        fullArea = createArray(rows, cols)
        placeMines()
      }
      safe(uncvrx, uncvry,0)
    }
    nbturn += 1
  }

  //If a 0 is discovered, all the neighboring tiles until the first one with a number should be discovered (all the "safe" tiles)
  def safe(x: Int, y: Int, distFromCell: Int): Unit = {
    for (row <- fullArea) {
      for (cell <- row) {
        if (cell.isSafe == distFromCell) {
          for (offset <- -1 to 1 if offset != 0) {
            val newX = x + offset
            val newY = y + offset
            if (newX <= rows - 1 && newX >= 0) {
              fullArea(newX)(y).isVisible = true
              if (fullArea(newX)(y).isSafe == 0) {
                fullArea(newX)(y).isSafe = distFromCell + 1
                safe(newX, y, distFromCell + 1)
              }
            }
            if (newY <= cols - 1 && newY >= 0) {
              fullArea(x)(newY).isVisible = true
              if (fullArea(x)(newY).isSafe == 0) {
                fullArea(x)(newY).isSafe = distFromCell + 1
                safe(x, newY, distFromCell + 1)
              }
            }
            if ((x - 1) >= 0 && y - 1 >= 0) {
              fullArea(x - 1)(y - 1).isVisible = true
              if (fullArea(x - 1)(y - 1).count == 0 && !fullArea(x - 1)(y - 1).isVisible) {
                safe(x - 1, y - 1, distFromCell)
              }
            }
            if ((x - 1) >= 0 && y + 1 <= 9) {
              fullArea(x - 1)(y + 1).isVisible = true
              if (fullArea(x - 1)(y + 1).count == 0 && !fullArea(x - 1)(y + 1).isVisible) {
                safe(x - 1, y + 1, distFromCell)
              }
            }
            if ((x + 1) <= 9 && y - 1 >= 0) {
              fullArea(x + 1)(y - 1).isVisible = true
              if (fullArea(x + 1)(y - 1).count == 0 && !fullArea(x + 1)(y - 1).isVisible) {
                safe(x + 1, y - 1, distFromCell)
              }
            }
            if ((x + 1) <= 9 && y + 1 <= 9) {
              fullArea(x + 1)(y + 1).isVisible = true
              if (fullArea(x + 1)(y + 1).count == 0 && !fullArea(x + 1)(y + 1).isVisible) {
                safe(x + 1, y + 1, distFromCell)
              }
            }

          }

        }
      }
    }
  }

  //displays the game's window
  def display(): Unit ={
    var i: Int = -1
    var j: Int = -1

    gamewindow.setColor(Color.gray)
    gamewindow.drawString(225,40 , "CodeSweeper",Color.DARK_GRAY, 30)

    for (row <- fullArea) {
      i += 1
      for (cell <- row) {
        j += 1

        if (cell.flag) {
          gamewindow.setColor(Color.gray)
          gamewindow.drawFillRect(45 + 50 * j + 5 * j, 45 + 50 * i + i * 5, width = 50, height = 50)
          gamewindow.drawString(60 + 50 * j + j * 5, 80 + 50 * i + 5 * i, "🚩", Color.black, 30)
        }
        else if (!cell.isVisible) {
          gamewindow.setColor(Color.black)
          gamewindow.drawFillRect(45 + 50 * j + 5 * j, 45 + 50 * i + i * 5, width = 50, height = 50)
        }
        else {
          gamewindow.setColor(Color.gray)
          gamewindow.drawFillRect(45 + 50 * j + 5 * j, 45 + 50 * i + i * 5, width = 50, height = 50)
          gamewindow.drawString(60 + 50 * j + j * 5, 80 + 50 * i + 5 * i, s"${cell.count}", Color.black, 30)
        }
      }
      j = -1
    }
    i = 0
  }

  //Winning conditions
  def win(): Boolean = {
    var win : Boolean = false
    var count : Int = 0

    for (row <- fullArea) {
      for (cell <- row) {
        if (!cell.isMine && cell.isVisible) {
          count += 1
        }
      }
    }
    if (count == ((rows * cols)-nbmines)){
      win = true
    }
    win
  }


  //Waiting for User to use the Mouse to flag or discover a tile in order to get the button + the position
  gamewindow.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e
      Button = e.getButton
      // Get the mouse position from the event
      posy = event.getX
      posx = event.getY

      if (posx >= 45 && posx <= 590 && posy >= 45 && posy <= 590) {
        uncvrx = (posx - 45) / 55
        uncvry = (posy - 45) / 55
      }
    }
  })

  //preparing the game's grid with tiles + placing mines
  var fullArea: Array[Array[Cell]] = createArray(rows, cols)
  placeMines()

  //Beginning of the game loop
  var game : Boolean = true
  while (game) {

    display()

    if (Button == 1) {
      fullArea(uncvrx)(uncvry).isVisible = true

      if (nbturn == 1 || fullArea(uncvrx)(uncvry).count == 0) {
        uncover(uncvrx, uncvry)
      }
      else if (fullArea(uncvrx)(uncvry).isMine && fullArea(uncvrx)(uncvry).isVisible) {
        display()
        gamewindow.setColor(Color.gray)
        gamewindow.drawString(225, 630, "You hit a mine! Game over!", Color.DARK_GRAY, 30)
        game = false
      }
      Button = 0
    }

    else if (Button == 3 && !fullArea(uncvrx)(uncvry).flag){
      fullArea(uncvrx)(uncvry).flag = true
      Button =0
    }
    else if (Button == 3 && fullArea(uncvrx)(uncvry).flag){
      fullArea(uncvrx)(uncvry).flag = false
      Button =0
    }

    if (win()) {
      gamewindow.setColor(Color.gray)
      gamewindow.drawString(225, 630, "Congratulations! You won!", Color.DARK_GRAY, 30)
      display()
      game = false
    }
    gamewindow.syncGameLogic(60)
  }
}