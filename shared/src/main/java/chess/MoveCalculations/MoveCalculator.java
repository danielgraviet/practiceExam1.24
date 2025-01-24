package chess.MoveCalculations;

import java.util.HashSet;
import chess.ChessMove;
import chess.ChessPosition;

public interface MoveCalculator {
    static HashSet<ChessMove> getMoves(){
        return null;
    }

    static boolean isOnBoard(ChessPosition position){
        int row = position.getRow();
        int column = position.getColumn();
        return row >= 1 && column >= 1 && row <= 8 && column <= 8;
    }
}
