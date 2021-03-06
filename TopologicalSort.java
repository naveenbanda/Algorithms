//package implementation; 
//undirected graph 
//@Naveen 
 
 
import java.io.*; 
import java.lang.*; 
import java.util.*; 
 
 class Graph 
 { 
   private HashMap<Integer, List<Integer>>AdjList; 
   boolean visited[];
   LinkedList<Integer> topological=new LinkedList<Integer>();
    
   //initialize graph with number of vertices 
   //Constructor 
   public Graph(int vertices) 
   { 
      visited=new boolean[vertices+1];
 	  AdjList = new HashMap<Integer, List<Integer>>(); 
       for(int i=1;i<=vertices;i++) 
        AdjList.put(i,new LinkedList<Integer>()); 
   } 
    
   //setting edge 
   public void setEdge(int source, int destination ) 
   { 
       if(source>AdjList.size()||destination>AdjList.size()) 
       System.out.println("Cannot find the specified "); 
       else 
       { 
           List<Integer>sourceList=AdjList.get(source); 
           sourceList.add(destination); 
           List<Integer>destinationList=AdjList.get(destination); 
           destinationList.add(source); 
       } 
   } 
   
   public void DFS(int vertex)
   {
       if(!visited[vertex])
       {
           visited[vertex]=true;
           Iterator<Integer> iter=AdjList.get(vertex).iterator();
           
           while(iter.hasNext())
           {
               Integer l=iter.next();
               DFS(l);
           }
           
           topological.add(vertex);
       }
   }
   
   public void DepthFirstTraversal(int vertices)
   {
       for(int i=1;i<=vertices;i++)
       {
           DFS(i);
       }
   }
   
   public void TopologicalSort()
   {
   		Iterator<Integer> iter=topological.iterator();
   		while(iter.hasNext()){
   			System.out.print(iter.next()+"->");
   		}
   }
    
    
 	public static void main (String[] args) throws java.lang.Exception 
 	{ 
 		int source,destination,vertices,edges,temp;
 		
 		Scanner s = new Scanner(System.in); 
 		
 		System.out.println("Enter number of vertices and edges"); 
 		vertices=s.nextInt(); 
		edges=s.nextInt(); 
		temp=edges;
 		Graph graph=new Graph(vertices); 
 		
	    System.out.println("Enter source and destination respectively"); 
 		while(temp>0) 
 		{ 
 		    source=s.nextInt(); 
 		    destination=s.nextInt(); 
 		    graph.setEdge(source,destination); 
 		    temp--; 
 		}
             
             graph.DepthFirstTraversal(vertices);
             graph.TopologicalSort();
             
 		s.close(); 
 	} 
 } 
