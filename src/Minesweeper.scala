object Minesweeper extends App {
  def emptyGrid () : Array[Array[Int]]= {
    var line : Array[Int]= Array.fill(9)(0)
    var column : Array[Array[Int]] = Array.fill(9)(line)
    column
  }

  def minesPos (emptyGrid : Array[Array[Int]]): Unit = {
    var mineX: Double = math.random() * 10 / 10
    var mineY : Double= math.random()*10/10
    var Minepos : Array [Double] = (mineX,mineY)

  }

}
