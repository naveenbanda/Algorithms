package graphs;

/**
 *
 * @author Naveen
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SCC {
    private HashMap<Integer,List<Integer>> AdjList;
    boolean visited[];
    static Stack<Integer> stack=new Stack<Integer>();
    
    public SCC(int vertices){
        visited=new boolean[vertices+1];
        AdjList=new HashMap<Integer,List<Integer>>();
        for(int i=1;i<=vertices;i++)
            AdjList.put(i,new LinkedList<Integer>());
    }
    
    public void setEdge(int source,int destination)
    {
        if(source>AdjList.size() || destination>AdjList.size())
            System.out.println("Cannot find the specified");
        else
        {
            List<Integer> sourceList=AdjList.get(source);
            sourceList.add(destination);
        }
    }
    
    void DFS(int vertex){
        if(!visited[vertex])
        {   
            visited[vertex]=true;
            stack.push(vertex);
            Iterator<Integer> Iter=AdjList.get(vertex).iterator();
            while(Iter.hasNext())
            {
                DFS(Iter.next());
                stack.push(vertex);
            }
        }
    }
    
    void DepthFirstTraversal(int vertices){
        for(int i=1;i<=vertices;i++)
            DFS(i);
    }
    
    void DFS2(int vertex)
    {
        if(!visited[vertex])
        {
            System.out.print(vertex+"->");
            visited[vertex]=true;
            Iterator<Integer> Iter=AdjList.get(vertex).iterator();
            while(Iter.hasNext())
            {
                DFS2(Iter.next());
            }
        }
    }
    
    void DFSSCC(int vertices)
    {
        for(int i=1;i<=vertices;i++)
            visited[i]=false;
        while(!stack.empty())
        {
            DFS2(stack.pop());
            System.out.println();
        }
    }
    
    public static void main(String args[]){
        int source,destination,vertices,edges,temp;
 		
 		Scanner s = new Scanner(System.in); 
 		
 		System.out.println("Enter number of vertices and edges"); 
 		vertices=s.nextInt(); 
		edges=s.nextInt(); 
		temp=edges;
 		SCC graph=new SCC(vertices); 
 		
	    System.out.println("Enter source and destination respectively"); 
 		while(temp>0) 
 		{ 
 		    source=s.nextInt(); 
 		    destination=s.nextInt(); 
 		    graph.setEdge(source,destination); 
 		    temp--; 
 		}
                
                SCC graphT=new SCC(vertices);
                
                for(int i=1;i<=vertices;i++)
                {
                    Iterator<Integer> iter=graph.AdjList.get(i).iterator();
                    while(iter.hasNext())
                    {
                        graphT.setEdge(iter.next(),i);
                    }
                }
                
                graph.DepthFirstTraversal(vertices);
                
                //Printing SCCs
                
                System.out.println("The SCC of the graph are : ");
                graphT.DFSSCC(vertices);
                
 		s.close(); 
    }
}
