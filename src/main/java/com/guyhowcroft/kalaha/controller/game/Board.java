package com.guyhowcroft.kalaha.controller.game;

public class Board {

	private int rows = 2;
	private int columns = 7;
	private int [][] board = new int[rows][columns];
	private int startStones = 6;
	private int playerRow = 1;
	private int winner = -1;
	
	public Board(){}
	
	//set all 
	public void initialiseBoard(){
		//last column will be the pits
		for(int i=0; i< rows;i++){
			for(int j =0; j< columns-1;j++){
				board[i][j] = startStones;
			}
		}
		
		//set the pits to 0 stones
		board[0][6] = 0;
		board[1][6] = 0;

	}
	
	public Boolean makeTurn(int position){
		
		int positionInArray = position-1;
		
		//if there are stones in the given position
		if(checkStonesAreInGivenPosition(positionInArray)){
			
			//play the game- pass in position in array
			moveStones(positionInArray);
			return true;
		}
		
		//if there are not stones in the given position
		return false;
	}
	
	private void moveStones(int positionInArray){
		
		int initialPosition = positionInArray;
		int numberOfStones = getStonesAtPositionInArray(positionInArray);
		int side;
		int lastPlacedSeed = 0;
			
		//set the stones in the position chosen to 0
		setPositionValue(0,positionInArray);

		//if player 1
		if(playerRow == 1){
			side = 1;
			positionInArray++;
		}else{
			side = 0;
			positionInArray--;
		}
		
		//for each of the stones
		for(int i = 0; i<numberOfStones; i++){
			//if on player 1 side
			if(side == 1){
				//if position to add to is on players side (including pit)
				if(positionInArray<columns){
					//if player 0 then dont add to player 1 pit
					if(playerRow == 0 && positionInArray == 6){
						side = 0;
						positionInArray = 5;
						addStonesToPositionInArray(1,side,positionInArray);
						lastPlacedSeed = positionInArray;
						positionInArray--;
					}
					//otherwise if not player 0 then for both players add - even to pit
					addStonesToPositionInArray(1,side,positionInArray);
					lastPlacedSeed = positionInArray;
					positionInArray++;
				}else{
					//go to other players side skipping their pit
					side = 0;
					positionInArray = 5;
					addStonesToPositionInArray(1,side,positionInArray);
					lastPlacedSeed = positionInArray;
					positionInArray--;
				}	
				//if on other players side
			}else if(side == 0){
				//going in to position on other players side
				if(positionInArray >= 0 ){
					addStonesToPositionInArray(1,side,positionInArray);
					lastPlacedSeed = positionInArray;
					positionInArray--;
				}else{
					//if player 0 then add to their pit before adding to player 1 side
					if(playerRow == 0){
						//add to pit
						addStonesToPositionInArray(1,side,columns-1);
						lastPlacedSeed = columns-1;
						side = 1;
						positionInArray = 0;
						//start from position 0 on players side again
					}else{
						side = 1;
						positionInArray = 0;
						addStonesToPositionInArray(1,side,positionInArray);
						lastPlacedSeed = positionInArray;
						positionInArray++;
					}
				}
			}
		}
		
		//check all rules for movement and set player turn
		checkRules(side, lastPlacedSeed);
		
	}
	
	private void checkRules(int side, int lastPlacedSeed){
		//if the last stone is in an empty cup on your side
		//take that piece and any pieces in your opponents side to you pit
		int seedsToMove = 0;
		//if last seed placed on player side
		if(playerRow == side && (lastPlacedSeed !=6)){
			
			if(getStonesAtPositionInArray(lastPlacedSeed) == 1){
				seedsToMove++;
				
				setPositionValue(0,lastPlacedSeed);
				//add the opponents seeds
				seedsToMove += getOponentStonesAtPositionInArray(lastPlacedSeed);
			
				//clear opponents stones in this position
				clearOponentStonesAtPositionInArray(lastPlacedSeed);
				//add to players pit
				addStonesToPlayersPit(seedsToMove);
			}
		}
	
		checkForWinner();
		
		checkForAnotherGo(lastPlacedSeed);
		
	}
	
	private void checkForAnotherGo(int lastPlacedSeed){
		//if last stone falls in your pit take another go 
		//else change the player
		if(lastPlacedSeed != 6){
			//change the player
			if(playerRow == 0){
				playerRow = 1;
			}else{
				playerRow = 0;
			}
		}
	}
	
	private void checkForWinner(){
		//if player row is now empty add all other players seeds to their pit
		if(checkRowEmpty()){
			System.out.println("row empty ="+checkRowEmpty());
			//move other players seeds to thier pit
			moveOtherPlayersSeedsToPit();
			//compare the seeds in each pit
			
			int player1Pit = board[1][6];
			int player2Pit = board[0][6];
			
			//draw
			if(player1Pit == player2Pit){
				winner = 2;
				//player 1
			}else if(player1Pit> player2Pit){
				winner = 1;
				//player 2
			}else{
				winner = 0;
			}			
		}
	}
	
	private void moveOtherPlayersSeedsToPit(){
		int row=0;
		if(playerRow == 0){
			row = 1;
		}
		
		int seedsToMove=0;
		
		//collect all the seeds in the row and delete from the row
		for(int i = 0;i<columns-1;i++){
			if(board[row][i]>0){
				seedsToMove += board[row][i];
				board[row][i] = 0;
			}
		}
		
		board[row][6] += seedsToMove;
	}
	
	private Boolean checkRowEmpty(){
		for(int i=0;i<columns -1 ;i++){
			if(board[playerRow][i] !=0)return false;
		}
		return true;
	}
	
	private Boolean checkStonesAreInGivenPosition(int positionInArray){
		
		int stones = getStonesAtPositionInArray(positionInArray);
		if(stones>0)return true;
		return false;
	}
	
	private void addStonesToPlayersPit(int stones){
			board[playerRow][6] += stones;		
	}
	
	private void addStonesToPositionInArray(int stones, int row, int column){
		board[row][column] += stones;
	}
	
	private int getStonesAtPositionInArray(int positionInArray){

		return board[playerRow][positionInArray];
	}
	
	private int getOponentStonesAtPositionInArray(int positionInArray){
	
		int row = 1;
		if(playerRow == 1)row=0;
		return board[row][positionInArray];
	}
	
	private void clearOponentStonesAtPositionInArray(int positionInArray){

		int row = 1;
		if(playerRow == 1)row=0;
		board[row][positionInArray] = 0;
	}
	
	
	//set position to a given value, position must be on the board due to validation in JS
	private boolean setPositionValue(int stones, int column){
		board[playerRow][column] = stones;
		return true;
	}
	
	public void print(int data){
		
		System.out.println("" + data);
	}
	
	public void printBoard(){
		
		for(int i=0;i<7;i++){
			System.out.print(board[0][i]+",");
		}
		System.out.println("");
		for(int i=0;i<7;i++){
			System.out.print(board[1][i]+",");
		}
		System.out.println("");
		System.out.println("");
	}
	
	public int getStartStones() {
		return startStones;
	};
	public int[][] getBoard(){
		return board;
	}
	public void setPlayerRow(int playerRow){
		this.playerRow = playerRow;
	}
	public int getPlayerRow(){
		//0 is player 2
		//1 is player 1
		return playerRow;
	}
	public int getWinner(){
		return winner;
	}
	
	
}
