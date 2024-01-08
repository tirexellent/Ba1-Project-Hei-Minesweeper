
object Minesweeper extends App {
  // Initialise class cell to get enough info in every cell
  class Cell(var isMine: Boolean, var count: Int, var isVisible: Boolean) {
    override def toString: String = s"($isMine, $count, $isVisible) "
  }

  def createArray(rows: Int, cols: Int): Array[Array[Cell]] = {
    // Initialize cells with default values
    var array = Array.ofDim[Cell](rows, cols)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        array(i)(j) = new Cell(false, 0, false)

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
      if (0<=(x+nbminesx) && (x+nbminesx)<=9){
        for (nbminesy: Int <- -1 until 2) {
          if (0<=(y+nbminesy) && (y+nbminesy)<=9){
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
  while (z==true) {
    for (row <- fullArea) {
      for (cell <- row) {
        if (cell.isVisible == false) {
          print(s"\u25A0")
        }
        else {
          print(s"${cell.count}")
        }

      }
      println()
    }
    println("Choose a cell to uncover x: ")
    var uncovercellx = Input.readInt() - 1

    println("Choose a cell to uncover y: ")
    var uncovercelly = Input.readInt() - 1

    fullArea(uncovercellx)(uncovercelly).isVisible = true



  }




}
