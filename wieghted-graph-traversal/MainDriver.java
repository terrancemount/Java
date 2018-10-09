/*****************************************************************
Author: KC Mount
3700 Sharon Gagnon Lane #M094
Anchorage, AK 99508
(907) 252-5055
iskrm3@uaa.alaska.edu


Project Name:MountT CS203 Asmt 7
DateCreated: April 25, 2005
Project purpose: To create a graph with random edge wieghts, then
					find the least cost path to all the vertices and
					back to starting vertex.


File Name: MainDriver.java
DateCreated: April 25, 2005
File Purpose: This class is the driving program.
******************************************************************/

public class MainDriver
{
	public static void main(String[] args)
	{
		int numOfVertices;  //this stores the number of vertices in the graph
		int numOfEdges; //this is the number of random edges
		GraphWithWieght myGraph; //this is the graph object
		int row, column;

		//The title and author for the project.
		System.out.println("KC Mount \nCS203 Asmt 7");

		/*******************************************************************************
		 * Test the findLeastWieghtTour() method of GraphWithWieght and display results
		 *******************************************************************************/

		//Prompt user to enter the number of vertices (warns against numbers above 10)
		System.out.print("\nPlease enter the number of vertices you want in the graph...");

		//this reads in the number of vertices from the user
		numOfVertices = SavitchIn.readLineInt();

		//this creates the a graph with the specified number of vertices
		myGraph = new GraphWithWieght(numOfVertices);

		//this set of nested loops will create random prices for my graph
		//this loop will increament though the rows of the adjacencyMatrix
		for(row = 0; row < numOfVertices; row++)

			//this loop will increament though the columns of the adjacencyMatrix
			for(column = 0; column < numOfVertices; column++)

				//this makes sure this algorithym doesn't create a loop in the graph
				//	(an vertex with a path to its self).
				if(row != column)

					//this is where the random price is set for the row and column
					//	positions in the adjacencyMatrix
					myGraph.addEdgeWieght(row, column, randomPrice());

		System.out.println("The Adjacency Matrix");

		myGraph.printAdjacencyMatrix();

		//This finds the least cost tour for the graph (Starting at 0)
		myGraph.findLeastWieghtTour(0);

		//this prints out the cost of the tour
		System.out.println("\n\nThe cost of the Tour is " + myGraph.getWieght());

		//this prints out the tours path
		System.out.println(myGraph.toString());

		//this is just used to keep the program from ending too soon
		String junk;

		System.out.println("\n\nPress enter to exit");

		junk = SavitchIn.readLine();
	}

	//this method will generate a random price between 50 and 1500
	public static double randomPrice()
	{
		double randomNum;

		do
		{
			randomNum = Math.random() * 1500;
		}
		while(randomNum < 50);

        randomNum = (Math.round(randomNum * 100)) / 100;

		return randomNum;
	}

}
