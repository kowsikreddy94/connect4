import java.util.*;

/**
 * This is the AiPlayer class. It simulates a minimax player for the max connect
 * four game. The constructor essentially does nothing.
 * 
 * @author james spargo
 *
 */

public class AiPlayer {
	
	
	
	int depth;

	public AiPlayer(int depth){
		
		this.depth = depth;
	}

	/**
	 * This method plays a piece randomly on the board
	 * @param currentGame The GameBoard object that is currently being used to play
	 *                    the game.
	 * @return an integer indicating which column the AiPlayer would like to play
	 *         in.
	 */
	public int findBestPlay(GameBoard currentGame) {
		int playChoice = 99;

		if (currentGame.getCurrentTurn() == 1) {
			int a;
			int value = 1000000;
			for (int i = 0; i < 7; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard current_move = new GameBoard(currentGame.getGameBoard());
					current_move.playPiece(i);
					a = max_utility_value(current_move, depth, -1000000, 1000000);
					if ( a<value) {
						playChoice = i;
						value = a;
					}
				}
			}
		} else {
			int  a;
			int  value=-1000000;
			for (int i = 0; i < 7; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard current_move = new GameBoard(currentGame.getGameBoard());
					current_move.playPiece(i);
					a = min_utility_value(current_move, depth, -1000000, 1000000);
					if (a>value) {
						playChoice = i;
						value = a;
					}

				}
			}
		}

		return playChoice;
	}  


	public int max_utility_value(GameBoard gBoard,int depth,int alpha,int beta) {
		if((gBoard.getPieceCount()<42)&& depth >0) {
			int pruned = -100000;
			int value;
			for(int i=0;i<7;i++) {
				if(gBoard.isValidPlay(i)) {
					GameBoard currentMove1= new GameBoard(gBoard.getGameBoard());
					currentMove1.playPiece(i);
					value=min_utility_value(currentMove1,depth-1,alpha,beta);
					if(pruned<value) {
						pruned=value;
					}
					if(pruned>=beta) {
						return pruned;
					}
					if(pruned>alpha) {
						alpha=pruned;

					}
				}
			}
			return pruned;
		}

		else{
			
				return (gBoard.eval_func(2)*100)-(gBoard.eval_func(1)*100); 	
					
		}
	}
	public int min_utility_value(GameBoard gBoard,int depth,int alpha,int beta) {
		if((gBoard.getPieceCount()<42)&& depth >0) {
			int ppruned =100000;
			int value;
			for(int i=0;i<7;i++) {
				if(gBoard.isValidPlay(i)) {
					GameBoard currentMove1= new GameBoard(gBoard.getGameBoard());
					currentMove1.playPiece(i);
					value=max_utility_value(currentMove1,depth-1,alpha,beta);
					if(ppruned>value) {
						ppruned=value;
					}
					if(ppruned<=alpha) {
						return ppruned;
					}
					if(ppruned<beta) {
						beta=ppruned;

					}
				}
			}
			return ppruned;
		}

		else{
		
				return (gBoard.eval_func(2)*100)-(gBoard.eval_func(1)*100); 	
					
		}
	}

}
