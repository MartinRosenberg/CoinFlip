package com.martinbrosenberg.coinflip

import com.martinbrosenberg.coinflip.Input.Side.{Heads, Tails}

sealed trait Input
object Input {

  case object Quit extends Input
  case object Invalid extends Input
  sealed trait Side extends Input
  object Side {
    case object Heads extends Side
    case object Tails extends Side
  }

  def apply(c: Char): Input = c match {
    case 'h' => Heads
    case 't' => Tails
    case 'q' => Quit
    case  _  => Invalid
  }

}
