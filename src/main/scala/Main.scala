package com.martinbrosenberg.coinflip

import Input.*
import Input.Side.{Heads, Tails}
import cats.effect.{ExitCode, IO, IOApp}

import scala.io.StdIn.readLine
import scala.util.Random

object Main extends IOApp {

  val getUserInput: IO[Input] = IO(
    readLine("\n(h)eads, (t)ails, or (q)uit: ").trim.headOption
      .map(_.toLower)
      .map {
        case 'h' => Heads
        case 't' => Tails
        case 'q' => Quit
        case _   => Invalid
      }
      .getOrElse(Invalid)
  )

  def printGameState(gameState: GameState): IO[Unit] =
    println(s"#Flips: ${gameState.numFlips}, #Correct: ${gameState.numCorrect}")

  def flipCoin(r: Random): IO[Side] = IO(if (r.nextBoolean()) Heads else Tails)

  def handleGuess(
      state: GameState,
      random: Random,
      guess: Side
  ): IO[GameState] = for {
    flip    <- flipCoin(random)
    newState = state.addFlip(guess, flip)
    _       <- print(s"Flip was $flip. ")
    _       <- printGameState(newState)
  } yield newState

  def mainLoop(state: GameState, random: Random): IO[Unit] = for {
    input <- getUserInput
    _     <- input match {
               case guess: Side =>
                 for {
                   newState <- handleGuess(state, random, guess)
                   _        <- mainLoop(newState, random)
                 } yield ()
               case Invalid     =>
                 for {
                   _ <- println("Invalid selection.")
                   _ <- mainLoop(state, random)
                 } yield ()
               case Quit        =>
                 for {
                   _ <- println("\n=== GAME OVER ===")
                   _ <- printGameState(state)
                 } yield ()
             }
  } yield ()

  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- println("COIN FLIP\nWhy are you wasting your time on this?™")
    s  = GameState(0, 0)
    r  = new Random
    _ <- mainLoop(s, r)
  } yield ExitCode.Success

}
