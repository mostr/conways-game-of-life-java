import org.specs2.mutable._
import pl.michalostruszka.gameoflife.board.Board
import pl.michalostruszka.gameoflife.{EndToEndTest, GameOfLife}

class GameOfLifeTest extends SpecificationWithJUnit {

  "Game" should {
    val board = new Board();
    val game = new GameOfLife(board)
    "be seeded with initial board state" in {
      game.currentBoardState().mustEqual(board)
    }

    "have new state after time ticks" in {
      val newState = game.tick(1)
      newState.mustNotEqual(board)
      newState.mustNotEqual(null)
    }

  }


  "Blinker" should {

    "game should be initialized with blinker initial state" in {
      val game = new GameOfLife(EndToEndTest.FIRST_BLINKER_STATE)
      game.currentBoardState().mustEqual(EndToEndTest.FIRST_BLINKER_STATE)
    }

    "game should change state after time ticks" in {
      val game = new GameOfLife(EndToEndTest.FIRST_BLINKER_STATE)
      game.tick(1)
      game.currentBoardState().mustEqual(EndToEndTest.SECOND_BLINKER_STATE)
    }
  }

}
