case class GameState(numFlips: Int, numCorrect: Int) {

  def addFlip(guess: Input, flip: Input): GameState =
    copy(numFlips + 1, numCorrect + (if (guess == flip) 1 else 0))

}
