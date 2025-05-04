
sealed trait Input
object Input {
  case object Quit extends Input
  case object Invalid extends Input

  sealed trait Side extends Input
  object Side {
    case object Heads extends Side
    case object Tails extends Side
  }
}
