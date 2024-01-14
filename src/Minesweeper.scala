
object Minesweeper extends App {
  // Initialise class cell to get enough info in every cell
  class Cell(var isMine: Boolean, var count: Int, var isVisible: Boolean,var isSafe : Int , var flag: Boolean) {
    override def toString: String = s"($isMine, $count, $isVisible, $isSafe, $flag) "
  }

  def createArray(rows: Int, cols: Int): Array[Array[Cell]] = {
    // Initialize cells with default values
    val array = Array.ofDim[Cell](rows, cols)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        array(i)(j) = new Cell(false, 0, false,0,false)

      }
    }
    array
  }

  def safe (x: Int , y: Int) : Unit = {
    var u: Boolean = true
    var distFromCell : Int = 2
    while (u) {
      var alldone : Int = 0

      if (fullArea(x+i)(y).isSafe == -1 && x+i<=9 && 0<=x+i && fullArea(x+i)(y).isVisible == false) {
        fullArea(x+i)(y).isVisible = true
      }
      else if (fullArea(x+i)(y).isSafe == 0 && x+i<=9 && 0<=x+i) {
        fullArea(x+i)(y).isSafe = distFromCell
        fullArea(x+i)(y).isVisible = true
        alldone+=1
      }

      else if (fullArea(x)(y+i).isSafe == -1 && y+i<=9 && 0<=y+i){
        fullArea(x)(y+i).isVisible = true
      }
      else if (fullArea(x)(y+i).isSafe == 0 && y+i<=9 && 0<=y+i){
        fullArea(x)(y+i).isSafe = distFromCell
        fullArea(x)(y+i).isVisible = true
        alldone+=1
      }
      else if (fullArea(x+i)(y).isSafe == distFromCell-1 && x+i<=9 && 0<=x+i) {
        alldone += 1
      }

      else if (fullArea(x)(y+i).isSafe == distFromCell-1 && y+i<=9 && 0<=y+i) {
        alldone += 1
      }

      if (alldone == 6){u=false}

      distFromCell += 1
    }
  }

  def placeMines(): Unit ={
    while (i < nbmines) {
      var x = (math.random() * 10).toInt
      var y = (math.random() * 10).toInt
      if (!fullArea(x)(y).isMine) {
        fullArea(x)(y).isMine = true
        i += 1
        println(x, y)
      }

      for (posminesx: Int <- -1 until 2) {
        if (0 <= (x + posminesx) && (x + posminesx) <= rows - 1) {
          for (posminesy: Int <- -1 until 2) {
            if (0 <= (y + posminesy) && (y + posminesy) <= cols - 1) {

              fullArea(x + posminesx)(y + posminesy).count += 1
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

  val rows: Int = 10
  val cols: Int = 10
  var nbmines : Int = 10
  var fullArea: Array[Array[Cell]] = createArray(rows, cols)
  var i : Int = 0






  var z : Boolean = true
  while (z) {
    for (row <- fullArea) {
      for (cell <- row) {
        if (cell.isVisible && cell.flag) {
          print("\uF6A9")
        }
        else if (!cell.isVisible) {
          print("\u25A0")
        } else {
          print(s"${cell.count}")
        }

      }
      println()
    }
    println("Choose a cell x pos: ")
    val uncvrx = Input.readInt() - 1

    println("Choose a cell y pos: ")
    val uncvry = Input.readInt() - 1

    if (fullArea(uncvrx)(uncvry).isSafe == 0){
      fullArea(uncvrx)(uncvry).isSafe = 1
      safe(uncvrx,uncvry)
    }
    else {
      while (fullArea(uncvrx)(uncvry).isSafe != 0){
        fullArea = createArray(rows, cols)
        placeMines()
      }
      fullArea(uncvrx)(uncvry).isSafe = 1
      safe(uncvrx,uncvry)
    }

    println("reveal or put flag? r/f")
    var flag: Char = Input.readChar()
    if (flag == 'r') {

      fullArea(uncvrx)(uncvry).isVisible = true
    }
    else {
      fullArea(uncvrx)(uncvry).isVisible = true
      fullArea(uncvrx)(uncvry).flag = true
    }
  }
}
