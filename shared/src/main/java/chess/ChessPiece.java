package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    public final ChessGame.TeamColor color;
    public final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.color = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if(type == ChessPiece.PieceType.KING) {
            return chess.MoveCalculations.CalculateKingMoves.getMoves(board, myPosition);
        } else if(type == ChessPiece.PieceType.QUEEN) {
            return chess.MoveCalculations.CalculateQueenMoves.getMoves(board, myPosition);
        } else if(type == ChessPiece.PieceType.BISHOP) {
            return chess.MoveCalculations.CalculateBishopMoves.getMoves(board, myPosition);
        } else if(type == ChessPiece.PieceType.KNIGHT) {
            return chess.MoveCalculations.CalculateKnightMoves.getMoves(board, myPosition);
        } else if(type == ChessPiece.PieceType.ROOK) {
            return chess.MoveCalculations.CalculateRookMoves.getMoves(board, myPosition);
        } else if (type == ChessPiece.PieceType.PAWN) {
            return chess.MoveCalculations.CalculatePawnMoves.getMoves(board, myPosition);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece piece = (ChessPiece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String toString() {
        return (color + " " + type);
    }
}
