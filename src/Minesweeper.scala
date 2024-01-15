import Minesweeper.{fullArea, placeMines, rows}

object Minesweeper extends App {
  // Initialise class cell to get enough info in every cell
  class Cell(var isMine: Boolean, var count: Int, var isVisible: Boolean,var isSafe : Int , var flag: Boolean) {
    override def toString: String = s"($isMine, $count, $isVisible, $isSafe, $flag) "
  }

  def createArray(rows: Int, cols: Int): Array[Array[Cell]] = {
    // Initialize cells with default values
    var array = Array.ofDim[Cell](rows, cols)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        array(i)(j) = new Cell(false, 0, false,0,false)
      }
    }
    array
  }

  def placeMines(): Unit = {
    var i : Int = 0
    while (i < nbmines) {
      var x = (math.random() * 10).toInt
      var y = (math.random() * 10).toInt
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

  def safe(x: Int, y: Int, distFromCell: Int): Unit = {
    for (row <- fullArea) {
      for (cell <- row) {
        if (cell.isSafe == distFromCell) {
          for (offset <- -1 to 1 if offset != 0){
            var newX = x + offset
            var newY = y + offset
            if (newX<=rows-1 && newX>=0 ){
              fullArea(newX)(y).isVisible = true
              if (fullArea(newX)(y).isSafe == 0){
                fullArea(newX)(y).isSafe = distFromCell + 1
                safe(newX,y, distFromCell)
              }
            }
            if (newY<=cols-1 && newY>=0 ){
              fullArea(x)(newY).isVisible = true
              if (fullArea(x)(newY).isSafe == 0) {
                fullArea(x)(newY).isSafe = distFromCell + 1
                safe(x,newY,distFromCell)
              }
            }
            if ((x-1)>= 0 && y-1 >=0){
              fullArea(x-1)(y-1).isVisible = true
              if (fullArea(x-1)(y-1).count == 0 && !fullArea(x-1)(y-1).isVisible){
                safe(x-1,y-1,distFromCell)
              }
            }
            if ((x-1) >= 0 && y+1 <= 9) {
              fullArea(x-1)(y+1).isVisible = true
              if (fullArea(x-1)(y+1).count == 0 && !fullArea(x-1)(y+1).isVisible) {
                safe(x-1, y+1, distFromCell)
              }
            }
            if ((x+1) <= 9 && y-1 >= 0) {
              fullArea(x+1)(y-1).isVisible = true
              if (fullArea(x+1)(y-1).count == 0 && !fullArea(x+1)(y-1).isVisible) {
                safe(x+1, y-1, distFromCell)
              }
            }
            if ((x+1) <= 9 && y+1 <= 9) {
              fullArea(x+1)(y+1).isVisible = true
              if (fullArea(x+1)(y+1).count == 0 && !fullArea(x+1)(y+1).isVisible) {
                safe(x+1, y+1, distFromCell)
              }
            }

          }

        }
      }
    }
  }

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
  }

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

  val rows: Int = 10
  val cols: Int = 10
  var nbmines : Int = 10
  var fullArea: Array[Array[Cell]] = createArray(rows, cols)
  var nbturn : Int =1
  var uncvrx: Int = 0
  var uncvry: Int = 0
  var flag: Char ='0'
  placeMines()

  var game : Boolean = true
  while (game) {
    for (i<-0 to 10) {
      println("\n")
    }
    for (row <- fullArea) {
      for (cell <- row) {
        if (cell.flag) {
          print("🚩")
        }
        else if (!cell.isVisible) {
          print("⬛")
        }
        else {
          print(s"${cell.count}")
        }
      }
      println()
    }
    if (game) {
      println("\nChoose a cell's x pos: ")
      uncvrx = Input.readInt() - 1

      println("Choose a cell's y pos: ")
      uncvry = Input.readInt() - 1

      if (fullArea(uncvrx)(uncvry).flag){
        println("remove flag? y/n")
      }
      else {
        println("reveal or put flag? r/f")
      }
      flag = Input.readChar()
    }


    if (flag == 'r') {
      fullArea(uncvrx)(uncvry).isVisible = true

      if (nbturn == 1 || fullArea(uncvrx)(uncvry).count == 0) {
        uncover(uncvrx, uncvry)
      }
      else if (fullArea(uncvrx)(uncvry).isMine && fullArea(uncvrx)(uncvry).isVisible) {
        println("You hit a mine! Game over!")
        game = false
      }
    }
    else if (flag == 'f'){
      fullArea(uncvrx)(uncvry).flag = true
    }
    else if (flag == 'y'){
      fullArea(uncvrx)(uncvry).flag = false
    }
    nbturn += 1
    if (win()) {
      println("Congratulations! You won!")
      game = false
    }
  }
}