package com.martinbrosenberg

import cats.effect.IO

package object coinflip {

  def print(obj: Any): IO[Unit] = IO(Console.print(obj))
  def println(obj: Any): IO[Unit] = IO(Console.println(obj))

}
