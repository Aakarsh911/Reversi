Overview: This is a codebase that implements a hexagonal Reversi game. The game assumes that the only possible board
sizes are odd and are greater than 3. This codebase is designed in a way that it can be extended to allow to a single
player mode against an AI.

Quick start: All you bro!

Key components:
    1. ReversiModel: This is the model interface that contains all the functions that BasicReversiModel needs for
    implementing the game logic. This interface extends the ReadOnlyReversiModel interface.
    2.ReadOnlyReversiModel: This is a read only version of the ReversiModel. It is used by the view to display the board,
    the score, and the current player.
    3. ReversiView: This is the view interface that contains all the functions that BasicReversiView needs for
    displaying the board, the score, and the current player.
    4. BasicReversiModel: This is the model class that implements the ReversiModel interface. It contains all the
    functions that are needed to implement the game logic. This class also contains all the methods that are needed to
    render the game state and other information such as the score and the current player.
    5. ReversiTextualView: This is the view class that contains the toString() method that displays the board, the score,
    and the current player. This function calls the functions on a private model class to get information about the
    current state of the game.
    6. CellState: This is an enum that represents the state of a cell on the board. It can be either EMPTY, BLACK, or
    WHITE.
    7. Hex: This is the interface that contains all the functions that the ReversiCell class needs. This interface
    defines functions that are essential for setting up the board in the BasicReversi class.
    8. ReversiCell: This is the class that implements the Hex interface. This class is responsible for making all
    the functions in the BasicReversi class work. This class gives crucial information about the cell such as its
    position, its state, and its neighbors. This class uses the cubic coordinate system to represent the position of
    the cell on the board.

 Key subcomponents: I might have written both the components and the subcomponents in the same section. You can split them
 and add more if you feel.

 Source organization: Honestly bro, do not know what to write here.