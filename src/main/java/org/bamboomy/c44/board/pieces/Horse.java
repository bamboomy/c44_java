package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Horse extends Piece {

	public Horse(Place place, int color) {
		super(place, color);
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/horse_red.png";

		} else if (color == Player.YELLOW) {

			return "../img/horse_yellow.png";
		}

		return "../img/horse.png";
	}

	@Override
	public void click() {

		if (!selected) {

			place.getBoard().getPlayerz()[color].setSelected(this);

			if (place.getX() + 2 < 12) {

				if (place.getY() + 1 < 12) {

					Place otherPlace = place.getBoard().getPlacez()[place.getX() + 2][place.getY() + 1];

					if (otherPlace != null && otherPlace != place) {

						if (otherPlace.getPiece() != null) {

							if (otherPlace.getPiece().getColor() != color) {

								otherPlace.attack(color);
							}

						} else {

							otherPlace.attack(color);
						}
					}
				}

				if (place.getY() - 1 >= 0) {

					Place otherPlace = place.getBoard().getPlacez()[place.getX() + 2][place.getY() - 1];

					if (otherPlace != null && otherPlace != place) {

						if (otherPlace.getPiece() != null) {

							if (otherPlace.getPiece().getColor() != color) {

								otherPlace.attack(color);
							}

						} else {

							otherPlace.attack(color);
						}
					}
				}

				if (place.getX() - 2 >= 0) {

					if (place.getY() + 1 < 12) {

						Place otherPlace = place.getBoard().getPlacez()[place.getX() - 2][place.getY() + 1];

						if (otherPlace != null && otherPlace != place) {

							if (otherPlace.getPiece() != null) {

								if (otherPlace.getPiece().getColor() != color) {

									otherPlace.attack(color);
								}

							} else {

								otherPlace.attack(color);
							}
						}

					}

					if (place.getY() - 1 >= 0) {

						Place otherPlace = place.getBoard().getPlacez()[place.getX() - 2][place.getY() - 1];

						if (otherPlace != null && otherPlace != place) {

							if (otherPlace.getPiece() != null) {

								if (otherPlace.getPiece().getColor() != color) {

									otherPlace.attack(color);
								}

							} else {

								otherPlace.attack(color);
							}
						}
					}
				}

				if (place.getY() + 2 < 12) {

					if (place.getX() + 1 < 12) {

						Place otherPlace = place.getBoard().getPlacez()[place.getX() + 1][place.getY() + 2];

						if (otherPlace != null && otherPlace != place) {

							if (otherPlace.getPiece() != null) {

								if (otherPlace.getPiece().getColor() != color) {

									otherPlace.attack(color);
								}

							} else {

								otherPlace.attack(color);
							}
						}

					}
				}

				/*
				 * }
				 * 
				 * if(!board[i+1][j+2].blank){
				 * 
				 * if(board[i+1][j+2].piece == null){
				 * 
				 * this.placesToMove.push($(board[i+1][j+2].element));
				 * prepareToMove($(board[i+1][j+2].element), this.color, i+1, j+2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * } else if(board[i+1][j+2].piece.player != null &&
				 * board[i+1][j+2].piece.player != this.player){
				 * 
				 * this.placesToMove.push($(board[i+1][j+2].element));
				 * prepareToMove($(board[i+1][j+2].element), this.color, i+1, j+2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * if(board[i+1][j+2].piece == currentKing){
				 * 
				 * currentKing.isCheck = true; } } } }
				 * 
				 * if(i-1 > 0){
				 * 
				 * if(!board[i-1][j+2].blank){
				 * 
				 * if(board[i-1][j+2].piece == null){
				 * 
				 * this.placesToMove.push($(board[i-1][j+2].element));
				 * prepareToMove($(board[i-1][j+2].element), this.color, i-1, j+2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * } else if(board[i-1][j+2].piece.player != null &&
				 * board[i-1][j+2].piece.player != this.player){
				 * 
				 * this.placesToMove.push($(board[i-1][j+2].element));
				 * prepareToMove($(board[i-1][j+2].element), this.color, i-1, j+2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * if(board[i-1][j+2].piece == currentKing){
				 * 
				 * currentKing.isCheck = true; } } } } }
				 * 
				 * if(j-2 >= 0){
				 * 
				 * if(i+1 < 13){
				 * 
				 * if(!board[i+1][j-2].blank){
				 * 
				 * if(board[i+1][j-2].piece == null){
				 * 
				 * this.placesToMove.push($(board[i+1][j-2].element));
				 * prepareToMove($(board[i+1][j-2].element), this.color, i+1, j-2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * } else if(board[i+1][j-2].piece.player != null &&
				 * board[i+1][j-2].piece.player != this.player){
				 * 
				 * this.placesToMove.push($(board[i+1][j-2].element));
				 * prepareToMove($(board[i+1][j-2].element), this.color, i+1, j-2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * if(board[i+1][j-2].piece == currentKing){
				 * 
				 * currentKing.isCheck = true; } } } }
				 * 
				 * if(i-1 > 0){
				 * 
				 * if(!board[i-1][j-2].blank){
				 * 
				 * if(board[i-1][j-2].piece == null){
				 * 
				 * this.placesToMove.push($(board[i-1][j-2].element));
				 * prepareToMove($(board[i-1][j-2].element), this.color, i-1, j-2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * } else if(board[i-1][j-2].piece.player != null &&
				 * board[i-1][j-2].piece.player != this.player){
				 * 
				 * this.placesToMove.push($(board[i-1][j-2].element));
				 * prepareToMove($(board[i-1][j-2].element), this.color, i-1, j-2, this,
				 * this.placesToMove, board[this.x][this.y]);
				 * 
				 * if(board[i-1][j-2].piece == currentKing){
				 * 
				 * currentKing.isCheck = true; } } }
				 */

			}

			selected = true;

		} else

		{

			unselect();
		}

	}

	@Override
	public void unselect() {
		// TODO Auto-generated method stub

	}

}
