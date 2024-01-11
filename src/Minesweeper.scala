
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

  def safe (x: Int , y: Int) : Unit = {
    var u: Boolean = true
    var randomInt : Int = 1
    while (u) {
      var alldone : Int = 0
      for (i<- -1 to 1){
        if (fullArea(x+i)(y).isSafe == -1) {
          fullArea(x+i)(y).isVisible = true
        }
        else if (fullArea(x+i)(y).isSafe == 0 ) {
          fullArea(x+i)(y).isSafe = randomInt
          fullArea(x+i)(y).isVisible = true
        }
      }

      randomInt += 1
    }
  }
  val rows: Int = 10
  val cols: Int = 10
  var nbmines : Int = 10
  var fullArea: Array[Array[Cell]] = createArray(rows, cols)
  var i : Int = 0


  //place mines
  while (i<nbmines) {
    var x = (math.random() * 10).toInt
    var y = (math.random() * 10).toInt
    if (!fullArea(x)(y).isMine){
      fullArea(x)(y).isMine = true
      i += 1
      println(x, y)
    }

    for (posminesx: Int <- -1 until   2){
      if (0<=(x+posminesx) && (x+posminesx)<=rows-1){
        for (posminesy: Int <- -1 until 2) {
          if (0<=(y+posminesy) && (y+posminesy)<=cols-1){

            fullArea(x+posminesx)(y+posminesy).count += 1
          }
        }
      }
    }
  }

  for (row <- fullArea) {
    for (cell <- row) {
      if (cell.count != 0) cell.isSafe = -1
    }
  }


  var z : Boolean = true
  var nbturn : Int = 0
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
    var uncovercellx = Input.readInt() - 1

    println("Choose a cell y pos: ")
    var uncovercelly = Input.readInt() - 1

    if (fullArea(uncovercellx)(uncovercelly).isSafe == 0){
      safe(uncovercellx,uncovercelly)
    }

    println("reveal or put flag? r/f")
    var flag: Char = Input.readChar()
    if (flag == 'r') {
      fullArea(uncovercellx)(uncovercelly).isVisible = true
    }
    else {
      fullArea(uncovercellx)(uncovercelly).isVisible = true
      fullArea(uncovercellx)(uncovercelly).flag = true

    }


  }
}
