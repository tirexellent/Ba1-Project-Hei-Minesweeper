object Minesweeper extends App {
  class Cell(val isMine: Boolean, val count: Int, var isVisible: Boolean) {
    override def toString: String = s"($isMine,$count,$isVisible)"
  }




}
