package com.martinbrosenberg

import java.io.IOException

import scalaz.zio.IO

import scala.io.StdIn

package object coinflip {

  val getChar: IO[Exception, Char] =
    IO(StdIn.readChar).refineOrDie {
      case e: IOException => e
      case e: IndexOutOfBoundsException => e
    }

}
