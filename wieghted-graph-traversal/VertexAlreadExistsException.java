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


File Name: VertexAlreadExistsException.java
DateCreated: April 25, 2005
File Purpose: This class is the exception that is thrown when
				a vertex is being added to a list which that vertex
				already exists in.
******************************************************************/

public class VertexAlreadExistsException extends Exception
{
	public VertexAlreadExistsException()
	{
		super();
	}
}
