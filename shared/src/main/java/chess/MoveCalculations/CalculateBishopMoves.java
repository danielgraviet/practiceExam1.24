package chess.MoveCalculations;

import java.util.ArrayList;
import java.util.Collection;
import chess.*;
import java.util.HashSet;

public class CalculateBishopMoves implements MoveCalculator {
    public static Collection<ChessMove> getMoves (ChessBoard board,  ChessPosition position) {
        HashSet<ChessMove> moves = new HashSet<>();
        int currentRow = position.getRow();
        int currentColumn = position.getColumn();
        int[][] bishopMoves = {{1,1}, {-1,1}, {1,-1}, {-1,-1}};
        ChessGame.TeamColor teamColor = board.getPiece(position).getTeamColor();

        if (position == null){
            return moves;
        }

        for (int[] move : bishopMoves) {
            int newRow = currentRow;
            int newColumn = currentColumn;

            while (true) {
                newRow += move[0];
                newColumn += move[1];
                ChessPosition newPosition = new ChessPosition(newRow, newColumn);

                // check if on board
                if(!MoveCalculator.isOnBoard(newPosition)){
                    break;
                }

                ChessPiece pieceAtNewPosition = board.getPiece(newPosition);

                // check if piece is there
                if (pieceAtNewPosition != null ){
                    if (teamColor != pieceAtNewPosition.getTeamColor()){
                        moves.add(new ChessMove(position, newPosition, null));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(position, newPosition, null));
                }
            }
        }
        return moves;
    }
}
