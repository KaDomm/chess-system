package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	/***********************************************************************************/

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {

			// VERTICAL MOVES WHITE
			
			p.setValues(position.getRow() - 1, position.getColumn());

			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;

				if (getMoveCount() == 0) {

					p.setValues(position.getRow() - 2, position.getColumn());

					if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
						mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			// CAPTURE MOVES (NW / NE) WHITE
			
			p.setValues(position.getRow() - 1, position.getColumn() - 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p))
				mat[p.getRow()][p.getColumn()] = true;
			
			
			p.setValues(position.getRow() - 1, position.getColumn() + 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p))
				mat[p.getRow()][p.getColumn()] = true;
			
			
			// #specialMove EN_PASSANT WHITE
			
			if(position.getRow() == 3) {
				
				Position left = new Position (3, position.getColumn()-1);
				
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left)==chessMatch.getEnPassantVulnerable())
					mat[2][left.getColumn()] = true;
				
				Position rigth = new Position (3, position.getColumn()+1);
				
				if(getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth)==chessMatch.getEnPassantVulnerable())
					mat[2][rigth.getColumn()] = true;
			}			
		}
		else {
			
			// VERTICAL MOVES BLACK
			
			p.setValues(position.getRow() + 1, position.getColumn());

			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;

				if (getMoveCount() == 0) {

					p.setValues(position.getRow() + 2, position.getColumn());

					if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
						mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			// CAPTURE MOVES (SW / SE) BLACK
			
			p.setValues(position.getRow() + 1, position.getColumn() - 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p))
				mat[p.getRow()][p.getColumn()] = true;
			
			
			p.setValues(position.getRow() + 1, position.getColumn() + 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p))
				mat[p.getRow()][p.getColumn()] = true;
			
			
			// #SpecialMove EN_PASSANT BLACK
			
			if(position.getRow() == 4) {
				
				Position left = new Position (4, position.getColumn()-1);
				
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left)==chessMatch.getEnPassantVulnerable())
					mat[5][left.getColumn()] = true;
				
				Position rigth = new Position (4, position.getColumn()+1);
				
				if(getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth)==chessMatch.getEnPassantVulnerable())
					mat[5][rigth.getColumn()] = true;
			}
		}
		return mat;
	}
	
	/***********************************************************************************/

	@Override
	public String toString() {
		return "P";
	}

}
