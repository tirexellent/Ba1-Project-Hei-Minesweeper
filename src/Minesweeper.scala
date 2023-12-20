import scala.util.Random
object Minesweeper extends App {
  class Cell(var isMine: Boolean, var count: Int, var isVisible: Boolean) {
    override def toString: String = s"($isMine,$count,$isVisible) "
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

  //place mines
  for ( i: Int <- 0 until nbmines) {
    var x = (math.random() * 10).toInt
    var y = (math.random() * 10).toInt
    println(x, y)
    if (fullArea(x)(y).isMine== true){
      nbmines += 1
    }
    else{
      fullArea(x)(y).isMine = true
    }
  }

  for (row <- fullArea) {
    for (cell <- row) {
      print(cell.toString)
    }
    println()
  }

}
