class DisplayBoard{

	//basic constructor
	public DisplayBoard(){
	}

	public void showBoard(int[][] inBoard){
		System.out.println(" 0 1 2 3 4 5 6");
		System.out.println("***************");
		for (int y=5;y>=0;y--){
			for(int x=0;x<7;x++){
				if (inBoard[x][y]==0){
					System.out.print("* ");
				}
				else if (inBoard[x][y]==1){
					System.out.print("*B");
				}
				else{
					System.out.print("*G");
				}
			}
			System.out.println("*");
			System.out.println("***************");
		}
	}
}