//Graphconstruction checks for the cycle and topological order
	//@author Hima Bindu Sigili
package Graphs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class Graphconstruction {
		private int node;
		private Map<Integer,ArrayList<Integer>> tedges;
		private static boolean[] visit; //create a array visit to store the values that are visited
		private boolean cycle;
		static ArrayList<Integer> input; 
		static ArrayList<Integer> sort_topo = new ArrayList<Integer>();
		public Graphconstruction(int node)
		{
			tedges = new HashMap<Integer,ArrayList<Integer>>();
			input = new ArrayList<Integer>();
			visit = new boolean[node];	
		}
		//get node is used to return the node
		public int getnode() {
			return node;
		}
		// set node is used to set the node
		public void setnode(int node) {
			this.node = node;
		}
		public void addEdge(int h,ArrayList<Integer> p)
		{
			this.tedges.put(h, p);
		}
		//cycle is returned here
		public boolean getcycle() 
		{
			return cycle;	
		}
		public void setcycle(boolean cycle) {
			this.cycle = cycle;	
		}
		public void checkDfs(int beg)
		{
			if(visit[beg]==false && checkinput(beg,input) == false)
			{
				//if the node is not visited add to the array list input and output and mark the visited node as true
				input.add(beg);
				visit[beg] = true;
				//store the beg value in a array list 
				ArrayList<Integer> al = tedges.get(beg);
			//	Recur for all the nodes adjacent to this node
				Iterator<Integer> it = al.iterator();
				while(it.hasNext())
				{
					int var = it.next();
					checkDfs(var);	
				}
				int var = input.remove(input.size()-1);//remove the node 
				if(checkinput(var,sort_topo)==false)
				{
					sort_topo.add(var);
				}
			}
			if(checkinput(beg,input) == true)
			{
				// if the value of the beginning and input is same then the cycle is set
				//method setcycle is called  
			System.out.println("cycle begins at "+beg);
			System.out.println("The given graph is not a DAG, the cycle is");
			for(int t=0;t<input.size();t++)
			{
				if(input.get(t)==beg) {
					for(int u=t;u<input.size();u++)
				System.out.println(input.get(u));
				}
			}
			setcycle(true);	
			System.exit(1);
			}
		}
		//check input method is called
		public boolean checkinput(int i,ArrayList<Integer> arrayList)
		{
			Iterator<Integer> it = arrayList.iterator();
		//	node1=new ArrayList<Integer>();
			while(it.hasNext())
			{
				int d = it.next();
				if(i==d) // if both the nodes are equal then it returns true else returns false
				{
					return true;
				}		
			}
			return false;
		}
}
