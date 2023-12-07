Provider Features:
We were able to integrate the provider's strategy into our codebase.
We were able to integrate the provider's view into our codebase, with a few bugs from their code.
The added commandline arguments to our codebase are: ps1 and ps2 to represent the provider's strategies.

Overview: This is a codebase that implements a hexagonal Reversi game. The game assumes that the only possible board
sizes are odd and are greater than 3. This codebase is designed in a way that it can be extended to allow to a single
player mode against an AI.

Example Game:
        m = new BasicReversi();
        m.move(4, 1);
        m.pass()
     These are the basic commands that a player would give to the model to play the game. There is a default constructor
     that creates a game with 7 rows, the game starts on turn 0 with white playing first, a player has two options on their
     turn: move or pass.

Key components:
    1. ReversiModel: This is the model interface that contains all the functions that BasicReversiModel needs for
    implementing the game logic. This interface extends the ReadOnlyModel interface.
    2.ReadOnlyModel: This is a read only version of the ReversiModel. It is used by the view to display the board,
    the score, and the current player.
    3. ReversiGraphicalView: This is the view interface that contains all the functions that BasicReversiView needs for
    displaying the board, the score, and the current player.
    4. Hex: This is the interface that contains all the functions that the ReversiCell class needs. This interface
    defines functions that are essential for setting up the board in the BasicReversi class.
    5. ReversiStrategy: This is the interface for ReversiStrategy classes. This represents a strategy that will return either
    the best move according to that strategy or Optional.empty() to indicate a pass.
    6. ViewFeatures: This is the interface that represents the user's interaction with the game.
    7. ReversiController: This is the controller class that implements the ViewFeatures and ModelListener interfaces.
    This class is responsible for mediating actions between a player and the model.
    8. ModelListener: This is the interface that represents the model's interaction with the controller.
    9. Player: This is an interface that represents the type of player.
    10. Reversi: This is the main class responsible for running the game. This class contains the main method that
    can take in command line arguments to run the game.

 Key subcomponents:
    1. BasicReversiModel: This is the model class that implements the ReversiModel interface. It contains all the
    functions that are needed to implement the game logic. This class also contains all the methods that are needed to
    render the game state and other information such as the score and the current player.
    2. ReversiTextualView: This is a textual based view class that contains the toString() method that displays the board, the score,
    and the current player. This function calls the functions on a private model class to get information about the
    current state of the game.
    3. CellState: This is an enum that represents the state of a cell on the board. It can be either EMPTY, BLACK, or
    WHITE.
    4. ReversiCell: This is the class that implements the Hex interface. This class is responsible for making all
    the functions in the BasicReversi class work. This class gives crucial information about the cell such as its
    position, its state, and its neighbors. This class uses the cubic coordinate system to represent the position of
    the cell on the board.
    5. ReversiFrame: This is the class that implements the ReversiGraphicalView interface. This class is responsible for
    the visual representation of the game window and inner panel that represents the game using Java Swing.
    6. ReversiPanel: This is a class that extends JPanel to visually represent the game. This class is responsible for
    updating the view based on mouse clicks and any features listeners with any key presses.
    7. SimpleHexagon: This is a class to represent a hexagon as a Path2D.double.
    8. BasicAI: This is a class that implements the ReversiStrategy interface. This strategy will return the move that will
    result in the highest score for the current player if there is one.
    9. AvoidAdjacentCornerCellsAI: This is a class that implements the ReversiStrategy interface. This strategy will return
    the move that will result in the highest score for the current player. This strategy will avoid placing a piece next to the corners.
    10. CornerAI: This is a class that implements the ReversiStrategy interface. This strategy will return the move that will prioritize placing
    a piece in the corners.
    11. MiniMaxAI: This is a class that implements the ReversiStrategy interface. This strategy will return the move that will minimize the max score for
    the opponent.
    12. BasicReversiController: This is a class that implements the ReversiController interface.
    This class gets notified by the model when it is its turn to play. This class is responsible for
    mediating actions between a player and the model.
    13. Person: This is a class that implements the Player interface. This class represents a human player.
    14. AIPlayer: This is a class that implements the Player interface. This class represents an AI player.
    It will take in a strategy and then use that strategy to determine the best move.

 Source organization:
    ReversiModel - reversi\src\model\ReversiModel
    ReadOnlyModel - reversi\src\model\ReadOnlyModel
    ReversiGraphicalView - reversi\src\view\ReversiView
    Hex - reversi\src\model\Hex
    ReversiStrategy - reversi\src\model\ReversiStrategy
    ViewFeatures - reversi\src\view\ViewFeatures
    ModelListener - reversi\src\model\ModelListener
    ReversiController - reversi\src\controller\ReversiController
    Player - reversi\src\model\Player

***EXTRA CREDIT***: AvoidAdjacentAI, CornerAI, MiniMaxAI, and TryTwo classes are part of the extra credit. These classes are
located in the reversi\src\model folder.

Changes for Part 2:
We changed the getColor method in the ReadOnly interface to return a String instead of a CellState to avoid exposing a concrete class.
We also added a isLegalMove method to the interface
We also added a method to check if there are any legal moves left for the current player.
We also added a method to return a mutable copy of the board.


Hover: When the mouse hovers over a cell, the cell will be highlighted with the current player's color if it's a legal move. This was just to enhance the view a little bit.

Changes for Part 3:
We changed our view so that when the window is resized the background will be resized as well and board centered accordingly.

Command Line Args:
Argument 1: Either "human" or the difficulty of the AI.
Argument 2: Either "human" or the difficulty of the AI.
The difficulty of the AI can be either "easy", "medium", "hard", or "extreme".