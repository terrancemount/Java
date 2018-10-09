class ComputerMove{

	private checkGameOver testGameOver = new checkGameOver();
	private DisplayBoard display = new DisplayBoard();
	private int[][] Board = new int [7][6];
	private int[][] futureBoard = new int [7][6];
	private int Player;//player = 1 or 2
	private int Oponent;//Oponent is the oposite of Player
	private int MOVE=0;//next move
	private int [] MoveValue = new int[7];//will hold the predicted outcome
	private int MaxVal=0;//Max value for the return
	private int MAXLOOK =7;
	private int rand;//random value
	private boolean Win=false;
	private boolean Loose=false;




	//base constructor
	public ComputerMove(){
		//set all MoveValues to 0;
		for (int x=0;x<7;x++){
			MoveValue [x] =0;
		}
	}

	//decide next move by returning the slot to add the token
	public int nextMove (int[][] inBoard, int inPlayer){
		Player = inPlayer;
		Oponent = 3 - Player;

		for (int xx=0;xx<7;xx++){
			for (int yy=0;yy<6;yy++){
				Board[xx][yy] = inBoard[xx][yy];
			}
		}

		//reset all MoveValues to 0;
		for (int x=0;x<7;x++){
			MoveValue [x] =0;
		}

		MOVE = BestMove();
		return MOVE;
	}

	//figure out the best move possible
	private int BestMove(){
		boolean top =false;//column is full
		//if the computer makes the first move, randomly select posistion 1-5
		if (EmptyBoard()){
			return ((int) (Math.random()*5) + 1);
		}

		//test all future move slots
		for (int x=0;x<7;x++){
			for (int xx=0;xx<7;xx++){
				for (int yy=0;yy<6;yy++){
					futureBoard[xx][yy] = Board[xx][yy];
				}
			}
			//test to see if column is full
			if (futureBoard[x][5]==0){
				for (int y=0;y<6;y++){
					if (futureBoard[x][y] == 0){
						futureBoard [x][y] = Player;
						y=6;
					}
				}
				MoveValue[x] = CalcMove(futureBoard,2,1);
			}
			else MoveValue[x] = -1000000000;
		}
		MaxVal = MoveValue[6];
		MOVE=6;
		for (int x=0;x<7;x++){
			//System.out.println("Val for slot "+x+" is "+MoveValue[x]);
			//if MoveVal == MaxVal randomly select one of the two moves
			if (MoveValue[x] == MaxVal){
				rand = (int) (Math.random()*2);
				//System.out.println (rand);
				if (rand==1){
					MOVE=x;
				}
			}
			//if MoveVal > MaxVal,  get new MaxVal
			else if (MoveValue[x] > MaxVal){
				MaxVal=MoveValue[x];
				MOVE=x;
			}
		}
		return MOVE;
	}

	//evaluate the next moves
	private int CalcMove(int[][] inBoard, int inTurn, int inDepth){
		int totalValue=0;
		int[] values = new int[7];
		int[][] tempBoard = new int[7][6];
		boolean full = false;

		//display.showBoard(inBoard);

		for (int x=0;x<7;x++){
			values [x]= 0;
		}

		//check to see if this state is a player win
		if(testGameOver.gameOver(inBoard, Player)){
			Win = true;
			if (inDepth==1){
				//System.out.println("Found a winning solution for this turn!!");
				return 100000;
			}
			else {
				//System.out.println("Found a winning solution!!");
				return (7-(7-7/inDepth));
			}
		}
		//check to see if this state is a oponent win
		if(testGameOver.gameOver(inBoard, Oponent)){
			Loose = true;
			if (inDepth==2){
				//System.out.println("Found a Loosing solution for this turn!!");
				return -100000;
			}
			else {
				//System.out.println("Found a loosing solution!!");
				return ((0+(7/inDepth))*-1);
			}
		}


		//check to see if max depth has been achieved
		if (inDepth > MAXLOOK){
			return 0;
		}

		if (inTurn == 1){//turns = 1 are Player's turns
			//System.out.println("testing this computer's choices");
			//This state was not a player win, so go through possible oponent moves.
			for (int x=0;x<7;x++){
				for (int xx=0;xx<7;xx++){
					for (int yy=0;yy<6;yy++){
						tempBoard[xx][yy] = inBoard[xx][yy];
					}
				}
				//test to see if column is full
				if (tempBoard[x][5]==0){
					for (int y=0;y<6;y++){
						if (tempBoard[x][y] == 0){
							tempBoard [x][y] = Player;
							y=6;
							//System.out.println("Comp Slot "+x+" is valid at depth "+inDepth);
						}
					}
					values[x] = CalcMove(tempBoard,2,inDepth+1);
					if (Loose){
						x=7;
						Loose=false;
					}
				}
			}
			for (int x=0;x<7;x++){
				totalValue = totalValue + values[x];
			}
			return totalValue;
		}
		else if (inTurn == 2) {//turns = 2 are Oponent's turns
			//System.out.println("testing the other computer's choices");

			//This state was not a oponent win, so go through possible player moves.
			for (int x=0;x<7;x++){
				for (int xx=0;xx<7;xx++){
					for (int yy=0;yy<6;yy++){
						tempBoard[xx][yy] = inBoard[xx][yy];
					}
				}
				//test to see if column is full
				if (tempBoard[x][5]==0){
					for (int y=0;y<6;y++){
						if (tempBoard[x][y] == 0){
							tempBoard [x][y] = Oponent;
							y=6;
							//System.out.println("Player Slot "+x+" is valid at depth "+inDepth);
						}
					}
					values[x] = CalcMove(tempBoard,1,inDepth+1);
					if (Win){
						x=7;
						Win=false;
					}
				}
			}
			for (int x=0;x<7;x++){
				totalValue = totalValue + values[x];
			}
			return totalValue;
		}
		return 0;
	}


	//check to see if board is empty
	//board must be fully 0's
	private boolean EmptyBoard(){
		for (int y=0;y<6;y++){
			for (int x=0;x<7;x++){
				if (Board[x][y] != 0){
					return false;
				}
			}
		}
		return true;
	}

}