package chess.MoveCalculations;

import java.util.ArrayList;
import java.util.Collection;
import chess.*;
import java.util.HashSet;

public class CalculatePawnMoves implements MoveCalculator {
    public static Collection<ChessMove> getMoves (ChessBoard board,  ChessPosition position) {
        HashSet<ChessMove> moves = new HashSet<>();
        int currentRow = position.getRow();
        int currentColumn = position.getColumn();
        ChessGame.TeamColor teamColor = board.getPiece(position).getTeamColor();

        // get direction
        // get startingRow
        int direction = (teamColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int startingRow = (teamColor == ChessGame.TeamColor.WHITE) ? 2 : 7;

        // check if starting row
        if (currentRow == startingRow) {
            moves.addAll(CalculatePawnMoves.startingMoves(board, position, direction));
        } else { // else regular moves
            moves.addAll(CalculatePawnMoves.regularMoves(board, position, direction));
        }

        // always run attack moves.
        moves.addAll(CalculatePawnMoves.attackMoves(board, position, direction));

        return moves;
    }

    public static Collection<ChessMove> startingMoves(ChessBoard board, ChessPosition position, int direction) {
        HashSet<ChessMove> moves = new HashSet<>();
        // get current piece
        // if there are any obstructions, do not add.
        int currentRow = position.getRow();
        int currentColumn = position.getColumn();
        int[][] startingMoves = {{direction, 0}, {direction * 2, 0}};

        for (int[] move : startingMoves) {
            int newRow = currentRow;
            int newColumn = currentColumn;

            for(int i = 0; i < 1; i++) {
                newRow += move[0];
                newColumn += move[1];

                ChessPosition newPosition = new ChessPosition(newRow, newColumn);

                if (!MoveCalculator.isOnBoard(newPosition)){
                    return moves;
                }

                ChessPiece pieceAtNewPosition = board.getPiece(newPosition);
                if (pieceAtNewPosition != null) {
                    return moves;
                } else {
                    moves.add(new ChessMove(position, newPosition, null));
                }
            }
        }
        return moves;
    }

    public static Collection<ChessMove> regularMoves(ChessBoard board, ChessPosition position, int direction) {
        HashSet<ChessMove> moves = new HashSet<>();
        int currentRow = position.getRow();
        int currentColumn = position.getColumn();
        ChessGame.TeamColor teamColor = board.getPiece(position).getTeamColor();

        int newRow = currentRow + direction;

        ChessPosition newPosition = new ChessPosition(newRow, currentColumn);
        ChessPiece pieceAtNewPosition = board.getPiece(newPosition);

        if (!MoveCalculator.isOnBoard(newPosition)){
            return moves;
        }

        if (pieceAtNewPosition != null) {
            return moves;
        } else {
            if (teamColor == ChessGame.TeamColor.WHITE && newRow == 8 || teamColor == ChessGame.TeamColor.BLACK && newRow == 1) {
                // add promotion pieces
                for (ChessPiece.PieceType pieceType : ChessPiece.PieceType.values()) {
                    if (pieceType != ChessPiece.PieceType.PAWN && pieceType != ChessPiece.PieceType.KING) {
                        moves.add(new ChessMove(position, newPosition, pieceType));
                    }
                }
            } else {
                moves.add(new ChessMove(position, newPosition, null));
            }
        }
        // get current piece
        // move one row up
        // check if it is at the final row for upgrades
        return moves;
    }

    public static Collection<ChessMove> attackMoves(ChessBoard board, ChessPosition position, int direction) {
        HashSet<ChessMove> moves = new HashSet<>();
        // get current position
        int currentRow = position.getRow();
        int currentColumn = position.getColumn();
        int[][] attackMoves = {{direction, 1}, {direction, -1}};
        ChessGame.TeamColor teamColor = board.getPiece(position).getTeamColor();

        for(int[] move : attackMoves) {
            int newRow = currentRow + move[0];
            int newColumn = currentColumn + move[1];
            ChessPosition newPosition = new ChessPosition(newRow, newColumn);

            if (!MoveCalculator.isOnBoard(newPosition)){
                continue;
            }

            ChessPiece pieceAtNewPosition = board.getPiece(newPosition);
            if (pieceAtNewPosition != null) {
                if (teamColor != board.getPiece(newPosition).getTeamColor()) {
                    if (teamColor == ChessGame.TeamColor.WHITE && newRow == 8 || teamColor == ChessGame.TeamColor.BLACK && newRow == 1) {
                        for (ChessPiece.PieceType pieceType : ChessPiece.PieceType.values()) {
                            if (pieceType != ChessPiece.PieceType.KING && pieceType != ChessPiece.PieceType.PAWN) {
                                moves.add(new ChessMove(position, newPosition, pieceType));
                            }
                        }
                    } else {
                        moves.add(new ChessMove(position, newPosition, null));
                    }
                }
            }
        }
        // use the direction to attack in correct ways
        // get piece at the new location
        // if it is the different team, check if it is the promotion row
        // if not, just add one piece.

        return moves;
    }
}
