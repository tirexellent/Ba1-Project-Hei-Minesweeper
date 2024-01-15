import RandomTests.fg
import hevs.graphics.FunGraphics

import java.awt.event.{MouseAdapter, MouseEvent}
import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}
import scala.util.Random

object RandomTests extends App {
  val fg = new FunGraphics(645, 645, "Ba1 Project, Minesweeper Se7en")


  while (true) {
    //draw our object
    for (i <- 0 until 10) {
      for (j <- 0 until 10) {
        fg.setColor(Color.black)
        fg.drawFillRect(45 + 50 * i + 5 * i, 45 + 50 * j + j * 5, width = 50, height = 50)
      }
    }
    fg.setColor(Color.gray)
    fg.drawFillRect(45, 45, 50, 50)
    fg.drawFillRect(100, 100, 50, 50)
    fg.drawString(60,80,"7",Color.black,30)

    //refresh the screen at 60 FPS
    fg.syncGameLogic(60)
  }


}








