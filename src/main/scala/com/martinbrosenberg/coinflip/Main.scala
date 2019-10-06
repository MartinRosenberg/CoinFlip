package com.martinbrosenberg.coinflip

import com.martinbrosenberg.coinflip.Input.Side._
import com.martinbrosenberg.coinflip.Input._
import scalaz.zio.console._
import scalaz.zio.random._
import scalaz.zio.{App, ZIO}
import scala.util.control.Exception.catching

object Main extends App {

  val whatever: Either[Exception, Int] = Either("hello".toInt)

  val getUserInput: ZIO[Console, Exception, Input] =
    for {
      _  <- putStr("\n(h)eads, (t)ails, or (q)uit: ")
      in <- getChar
    } yield Input(in)

  def printGameState(state: GameState): ZIO[Console, Nothing, Unit] =
    putStrLn(s"#Flips: ${state.numFlips}, #Correct: ${state.numCorrect}")

  val flipCoin: ZIO[Random, Nothing, Side] =
    nextBoolean.map(if (_) Heads else Tails)

  def handleGuess(state: GameState, guess: Side): ZIO[Console with Random, Nothing, GameState] =
    for {
      flip     <- flipCoin
      newState =  state.addFlip(guess, flip)
      _        <- putStr(s"Flip was $flip. ")
      _        <- printGameState(newState)
    } yield newState

  def mainLoop(state: GameState): ZIO[Environment, Nothing, Unit] =
    for {
      input <- getUserInput.fold(_ => Invalid, identity)
      _     <- input match {
        case guess: Side => for {
          newState <- handleGuess(state, guess)
          _        <- mainLoop(newState)
        } yield ()
        case Invalid => for {
          _ <- putStrLn("Invalid selection.")
          _ <- mainLoop(state)
        } yield ()
        case Quit => for {
          _ <- putStrLn("\n=== GAME OVER ===")
          _ <- printGameState(state)
        } yield ()
      }
    } yield ()

  def mainLoop2(state: GameState): ZIO[Environment, Nothing, Unit] =
    for {
      n <- nextInt
      _ <- mainLoop2(state)
    } yield ()

  override def run(args: List[String]): ZIO[Main.Environment, Nothing, Int] =
    (for {
      _ <- putStrLn("COIN FLIP\nWhy are you wasting your time on this?™")
      _ <- mainLoop2(GameState(0, 0))
    } yield ()).fold(_ => 1, _ => 0)

}
