package com.martinbrosenberg.coinflip

import cats.effect.IO

def print(obj: Any): IO[Unit]   = IO(Console.print(obj))
def println(obj: Any): IO[Unit] = IO(Console.println(obj))
