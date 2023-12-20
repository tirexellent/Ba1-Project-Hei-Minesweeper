


object RandomTests extends App {
  class Cell(val isMine: Boolean, val count: Int, var isVisible: Boolean) {
    override def toString: String = s"($isMine,$count,$isVisible)"
  }
  // Function to create a 2D array of cells
  def createArray(rows: Int, cols: Int): Array[Array[Cell]] = {
    val array = Array.ofDim[Cell](rows, cols)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        array(i)(j) = new Cell(false, 0, false) // Initialize cells with default values
      }
    }
    array
  }

  // Creating a 3x3 array of cells
  val rows = 9
  val cols = 9
  var arrayOfArrays: Array[Array[Cell]] = createArray(rows, cols)

  // Printing the array of arrays
  for (row <- arrayOfArrays) {
    for (cell <- row) {
      print(cell + " ")
    }
    println()
  }
}


