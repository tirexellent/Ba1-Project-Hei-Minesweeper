
import scala.util.Random

object RandomTests extends App {
    def checkDirectSides(array: Array[Array[Int]], row: Int, col: Int): Unit = {
      val numRows = array.length
      val numCols = array(0).length

      // Check top
      if (row > 0) {
        println(s"Top: ${array(row - 1)(col)}")
      }

      // Check bottom
      if (row < numRows - 1) {
        println(s"Bottom: ${array(row + 1)(col)}")
      }

      // Check left
      if (col > 0) {
        println(s"Left: ${array(row)(col - 1)}")
      }

      // Check right
      if (col < numCols - 1) {
        println(s"Right: ${array(row)(col + 1)}")
      }
    }

    // Example usage
    val fullArea: Array[Array[Int]] = Array(
      Array(1, 2, 3),
      Array(4, 5, 6),
      Array(7, 8, 9)
    )

    // Assuming you want to check the neighbors of the element at position (1, 1)
    checkDirectSides(fullArea, 1, 1)



}









