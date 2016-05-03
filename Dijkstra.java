/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insanity;

/**
 *
 * @author Naveen
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class Dijkstra {
    
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        
        int vertices,edges;
        
        System.out.println("Enter the no of vertices and edges in the graph");
        vertices=sc.nextInt();
        edges=sc.nextInt();
        
        int weight[][]=new int[vertices][vertices];
        
        graph g=new graph(vertices);
        
        System.out.println("Enter source, destination and weight of all the edges");
        
        for(int i=1;i<=edges;i++)
        {
            int source=sc.nextInt();
            int dest=sc.nextInt();
            weight[dest][source]=weight[source][dest]=sc.nextInt();
            
            g.addEdge(source,dest);
            
        }
        
        System.out.println("Enter the vertex from which you want to find the shortest path");
        int root=sc.nextInt();
        
        int arr[]=g.shortestPath(root,weight);
        
        for(int i=0;i<vertices;i++)
        {
            System.out.println("The shortest path from vertex "+root+" to vertex "+i+" is : "+arr[i]);
        }
        
    }
}

class graph
{
    private HashMap<Integer,List<Integer>> AdjList;
    public boolean visited[];
    int V;
    public boolean SPTSet[];
    
    public graph(int vertices)
    {
        visited=new boolean[vertices];
        V=vertices;
        SPTSet=new boolean[vertices];
        AdjList=new HashMap<Integer,List<Integer>>();
        
        for(int i=0;i<vertices;i++)
        {
            AdjList.put(i,new LinkedList<Integer>());
        }
        
        for(int i=0;i<vertices;i++)
        {
            visited[i]=false;
            SPTSet[i]=false;
        }
        
    }
    
    public void addEdge(int source,int dest)
    {
        AdjList.get(source).add(dest);
        AdjList.get(dest).add(source);
        
    }
    
    public int[] shortestPath(int root,int weight[][])
    {
        int temp[]=new int[V];
        
        for(int i=0;i<V;i++)
            temp[i]=Integer.MAX_VALUE;
        
        temp[root]=0;
        
        for(int i=0;i<V;i++)
        {
            int u=minDistance(temp);
            
            SPTSet[u]=true;
           
            int size=AdjList.get(u).size();
                
            for(int j=0;j<size;j++)
            {
                int val=AdjList.get(u).get(j);
                if(!SPTSet[val] && temp[u]+weight[u][val]<temp[val])
                    temp[val]=temp[u]+weight[u][val];
            }
                
        }
        
        return temp;
        
    }
    
    public int minDistance(int temp[])
    {
        int min=Integer.MAX_VALUE,min_index=-1;
        
        for(int v=0;v<V;v++)
        {
            if(SPTSet[v]==false && temp[v]<=min)
            {
                min=temp[v];
                min_index=v;
            }
        }
        
        return min_index;
        
    }
    
}
