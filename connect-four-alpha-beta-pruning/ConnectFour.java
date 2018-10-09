import java.util.*;


public class ConnectFour{

	static checkGameOver GameOver = new checkGameOver();
	static int player1;//number of player1 1 or 2
	static int player2;//number of player2 1 or 2

	public static void main (String [] args){

		Scanner in = new Scanner(System.in);
		DisplayBoard Show = new DisplayBoard();
		ComputerMove CompMove = new ComputerMove();
		int choice;//user responces
		int mplayer1;//player 1 move
		int mplayer2;//player 2 move
		int[][] Board = new int[7][6];//the playing board
		int Winner = 0;//winner
		boolean insert =false;//valid human input entered

		//initialize the board to empty
		for (int x=0;x<7;x++){
			for (int y=0;y<6;y++){
				Board[x][y] = 0;
			}
		}


		do{
			System.out.println();
			System.out.println("Please choose either:");
			System.out.println("  1. One Player");
			System.out.println("  2. No Players");
			choice = in.nextInt();
		}while ((choice !=1)&&(choice !=2));


		//set up one player game
		if (choice == 1){
			do{
				System.out.println();
				System.out.println("Please choose either:");
				System.out.println("  1. I go first");
				System.out.println("  2. Computer goes first");
				choice = in.nextInt();
				player1=choice;
				player2=3-player1;
			}while ((choice !=1)&&(choice !=2));

			if (player1 == 1){
				do{
					System.out.println("Choose your first move. Column 0-6?");
					choice = in.nextInt();
				}while ((choice<0)||(choice>6));
				Board[choice][0]=player1;
			}

			do{
				System.out.println("The current board looks like this:");
				System.out.println();
				Show.showBoard(Board);
				System.out.println();
				System.out.println("The computer is thinking.......");
				mplayer2=CompMove.nextMove(Board,player2);
				System.out.println("The computer has dropped a tile in column "+mplayer2+".");
				for (int y=0;y<6;y++){
					if (Board[mplayer2][y]==0){
						Board[mplayer2][y]=player2;
						y=6;
					}
				}
				System.out.println();
				System.out.println("The current board looks like this:");
				System.out.println();
				Show.showBoard(Board);
				System.out.println();
				if(GameOver.gameOver(Board, player2)){
					Winner = 2;
					System.out.println("The computer has beaten you this time.");
				}
				if(!GameOver.gameOver(Board, player2)){
					insert = false;
					do{
						do{
							System.out.println("Choose your move. Column 0-6?");
							choice = in.nextInt();
						}while ((choice<0)||(choice>6));
						if (Board[choice][5] == 0){
							for (int y=0;y<6;y++){
								if (Board[choice][y]==0){
									Board[choice][y]=player1;
									insert = true;
									y=6;
								}
							}
						}
						else{
							System.out.println("That column is full!");
						}
					}while (!insert);
				}
				if(GameOver.gameOver(Board, player1)){
					Winner = 1;
					System.out.println("You have beaten the computer this time.  Good Job!");
				}
			}while(!GameOver.gameOver(Board, player2)&&!GameOver.gameOver(Board, player1));
		}
//set up no player game
		if (choice == 2){
			player1=1;
			player2=3-player1;


			do{
				System.out.println("The computer is thinking.......");
				mplayer1=CompMove.nextMove(Board,player1);
				System.out.println("Computer 1 has dropped a tile in column "+mplayer1+".");
				for (int y=0;y<6;y++){
					if (Board[mplayer1][y]==0){
						Board[mplayer1][y]=player1;
						y=6;
					}
				}
				System.out.println();
				System.out.println("The current board looks like this:");
				System.out.println();
				Show.showBoard(Board);
				if (!GameOver.gameOver(Board, player1)){
					System.out.println();
					System.out.println("The computer is thinking.......");
					mplayer2=CompMove.nextMove(Board,player2);
					System.out.println("Computer 2 has dropped a tile in column "+mplayer2+".");
					for (int y=0;y<6;y++){
						if (Board[mplayer2][y]==0){
							Board[mplayer2][y]=player2;
							y=6;
						}
					}
					System.out.println();
					System.out.println("The current board looks like this:");
					System.out.println();
					Show.showBoard(Board);
					System.out.println();
				}
				if(GameOver.gameOver(Board, player1)){
					Winner = 2;
					System.out.println("Computer 1 has won.");
				}
				if(!GameOver.gameOver(Board, player2)){
					//System.out.println("enter any key for next move");
					//choice = in.nextInt();
				}
				if(GameOver.gameOver(Board, player2)){
					Winner = 1;
					System.out.println("Computer 2 has won.");
				}
			}while(!GameOver.gameOver(Board, player2)&&!GameOver.gameOver(Board, player1)&&!Tie(Board));
			if (Winner ==0){
				System.out.println("Game ended in a tie.");
			}

			System.out.println("Final board looks like this:");
			Show.showBoard(Board);
		}
	}

	public static boolean Tie (int[][] inBoard){
		boolean full = true;
		for (int x=0;x<7;x++){
			if (inBoard[x][5]==0){
				full = false;
				x=7;
			}
		}
		if (full){
			if (!GameOver.gameOver(inBoard, player2)&&!GameOver.gameOver(inBoard, player1)){
				return true;
			}
		}
		return false;
	}
}