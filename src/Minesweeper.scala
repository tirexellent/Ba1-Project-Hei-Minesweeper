object Minesweeper extends App {
  class Cell(val isMine: Boolean, val count: Int, var isVisible: Boolean) {
    override def toString: String = s"($isMine,$count,$isVisible)"
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

  val rows = 9
  val cols = 9
  var fullArea: Array[Array[Cell]] = createArray(rows, cols)


  for (row <- fullArea) {
    for (cell <- row) {
      print(cell + " ")
    }
    println()
  }


}
