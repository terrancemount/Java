class checkGameOver{

	//Basic Constructor
	public checkGameOver(){
	}

	public boolean gameOver(int[][] inArray, int inPlayer){

		if (checkUpDown(inArray,inPlayer)){
			return true;
		}

		else if (checkAcross(inArray,inPlayer)){
			return true;
		}

		else if (checkDiagDown(inArray,inPlayer)){
			return true;
		}

		else if (checkDiagUp(inArray,inPlayer)){
			return true;
		}

		else {
			return false;
		}
	}

	//check for the win condition for up down wins
	private boolean checkUpDown (int[][] Board, int Player){
		boolean win = false;
		int Tally=0;

		for (int y =0;y<3;y++){
			for (int x=0;x<7;x++){
				//if the current location = player check for a win
				if (Board[x][y] == Player){
					Tally=0;
					do{
						Tally++;
					}while (((Tally+y)<6)&&(Board[x][y+Tally]==Player));
					if (Tally >3){
						return true;
					}
				}
			}
		}
		return false;
	}

	//check for across wins
	private boolean checkAcross (int[][] Board, int Player){
		boolean win = false;
				int Tally=0;

				for (int x =0;x<4;x++){
					for (int y=0;y<6;y++){
						//if the current location = player check for a win
						if (Board[x][y] == Player){
							Tally=0;
							do{
								Tally++;
							}while (((Tally+x)<7)&&(Board[x+Tally][y]==Player));
							if (Tally >3){
								return true;
							}
						}
					}
				}
		return false;
	}

	//check for Diagle Down wins
	private boolean checkDiagDown (int[][] Board, int Player){
		boolean win = false;
				int Tally=0;

				for (int x =0;x<4;x++){
					for (int y=3;y<6;y++){
						//if the current location = player check for a win
						if (Board[x][y] == Player){
							Tally=0;
							do{
								Tally++;
							}while (((Tally+x)<7)&&((y-Tally)>-1)&&(Board[x+Tally][y-Tally]==Player));
							if (Tally >3){
								return true;
							}
						}
					}
				}
		return false;
	}

	//check for Diagle Up wins
	private boolean checkDiagUp (int[][] Board, int Player){
		boolean win = false;
				int Tally=0;

				for (int x =0;x<4;x++){
					for (int y=0;y<3;y++){
						//if the current location = player check for a win
						if (Board[x][y] == Player){
							Tally=0;
							do{
								Tally++;
							}while (((Tally+x)<7)&&((y+Tally)<6)&&(Board[x+Tally][y+Tally]==Player));
							if (Tally >3){
								return true;
							}
						}
					}
				}
		return false;
	}


}