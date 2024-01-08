
object Minesweeper extends App {
  // Initialise class cell to get enough info in every cell
  class Cell(var isMine: Boolean, var count: Int, var isVisible: Boolean, var flag: Boolean) {
    override def toString: String = s"($isMine, $count, $isVisible, $flag) "
  }

  def createArray(rows: Int, cols: Int): Array[Array[Cell]] = {
    // Initialize cells with default values
    var array = Array.ofDim[Cell](rows, cols)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        array(i)(j) = new Cell(false, 0, false,false)

      }
    }
    array
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
    if (fullArea(x)(y).isMine== false){
      fullArea(x)(y).isMine = true
      i += 1
      println(x, y)
    }





    for (nbminesx: Int <- -1 until   2){
      if (0<=(x+nbminesx) && (x+nbminesx)<=rows){
        for (nbminesy: Int <- -1 until 2) {
          if (0<=(y+nbminesy) && (y+nbminesy)<=cols){
            println(s"${x + nbminesx}, ${y + nbminesy}")
            fullArea(x+nbminesx)(y+nbminesy).count += 1
          }
        }
      }
    }
  }


  for (row <- fullArea) {
    for (cell <- row) {
      print(cell.toString)
    }
    println()
  }
  for (row <- fullArea) {
    for (cell <- row) {
      print(s"(${cell.count}) ")
    }
    println()
  }

  var z : Boolean = true
  var nbturn : Int = 0
  while (z==true) {
    for (row <- fullArea) {
      for (cell <- row) {
        if (cell.isVisible == true && cell.flag==true) {
          print("\uF6A9")
        }
        else if (cell.isVisible == true){
          print(s"${cell.count}")
        }

        print("\u25A0")

      }
      println()
    }
    println("Choose a cell x pos: ")
    var uncovercellx = Input.readInt() - 1

    println("Choose a cell y pos: ")
    var uncovercelly = Input.readInt() - 1

    if (nbturn ==0){
      fullArea(uncovercellx)(uncovercelly).isVisible = true
      var turn : Boolean = true

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
    nbturn += 1


  }
}
