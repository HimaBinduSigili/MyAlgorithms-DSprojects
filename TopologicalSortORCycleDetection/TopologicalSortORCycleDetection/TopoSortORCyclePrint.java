//Program to check whether given directed graph has cycles or not .
	//If there is any cycle print the cycle.
	//else if there is no cycle it prints the topological order.
	//@author Hima Bindu Sigili
package Graphs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TopoSortORCyclePrint {
		static int[][] Adjacencymatrix;
		private static Scanner text;
		private static Scanner temp;
		public static void main(String[] args) throws FileNotFoundException 
		{
			// Enter the total number of nodes present in the input graph
			System.out.println("Enter the total number of vertices");
			text = new Scanner(System.in);	
			int node = text.nextInt();
		
			//the number of nodes is passed as an input to form an adjacency matrix
			Adjacencymatrix = new int[node][node];
			//Here the location of the file is entered
			System.out.println("Enter the location of the file:");
			CreateAdjacencyMatrix(node);
			
			//The number of nodes is passed to the for loop
			for(int i=0;i<node;i++)
			{
				Graphconstruction g = new Graphconstruction(node);
				for(int v=0;v<node;v++)
				{
					ArrayList<Integer> al = new ArrayList<Integer>();
					for(int f=0;f<node;f++)
					{
						if(Adjacencymatrix[v][f] == 1)
						{ 
							//when ever there is an edge add the respective value to the arraylist
							al.add(f);
						}
					}
					
					g.addEdge(v, al);
				}
				g.checkDfs(i);
				//If there is a cycle the GraphConstruction class will print the cycle
			}
			System.out.println("The following is the topological order for the input graph: ");
			//if there is no cycle then it prints topological order for the graph
			for(int z=Graphconstruction.sort_topo.size()-1;z>=0;z--)
			{
				System.out.print(Graphconstruction.sort_topo.get(z)+" ");
			}
		}
		//method CreateAdjacencyMatrix is called to read the file and store it in a adjacency matrix
		public static void CreateAdjacencyMatrix(int node) throws FileNotFoundException
		{
			Scanner loc =null;
			temp = new Scanner(System.in);
			String filePath = temp.nextLine();
				//in my case filePath is "C:\\Users\\LibraryGuest2\\Downloads\\Project2_Testcases\\Project2_Testcases\\Testcase5.csv";
				try{
					loc = new Scanner(new File(filePath));
				}
				catch(FileNotFoundException e)
				{
					System.out.println("Wrong file path");
					System.out.println("Check the file path again");
					System.exit(0);
				}
				for(int l=0;l<node;l++)
				{
					String inputFile = loc.nextLine();
					String[] strlen = inputFile.split(",");
					for(int m=0;m<node;m++)
					{
						Adjacencymatrix[l][m] = Integer.parseInt(strlen[m]);
					}
				}	
		}
}
