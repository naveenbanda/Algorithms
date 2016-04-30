/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

/**
 *
 * @author Naveen
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class FloydWarshall {
    
    public static void main(String args[])
    {
        Graph graph=new Graph(4);
        graph.addEdge(0,1,5);
        graph.addEdge(0,3,10);
        graph.addEdge(1,2,3);
        graph.addEdge(2,3,1);
        
        for(int i=0;i<4;i++)
            graph.addEdge(i,i,0);
        
        graph.floydWarshall();
        
    }
}

class Graph
{
    int V;
    boolean visited[];
    HashMap<Integer,List<Integer>> AdjList;
    int weight[][];
    
    Graph(int v)
    {
        V=v;
        visited=new boolean[v];
        for(int i=0;i<v;i++)
            visited[i]=false;
        
        AdjList=new HashMap<Integer,List<Integer>>();
        
        for(int i=0;i<v;i++)
            AdjList.put(i,new LinkedList<Integer>());
        
        weight=new int[v][v];
        
        for(int i=0;i<v;i++)
        {
            for(int j=0;j<v;j++)
                weight[i][j]=Integer.MAX_VALUE;
        }
        
    }
    
    public void addEdge(int source,int dest,int weig)
    {
        AdjList.get(source).add(dest);
        weight[source][dest]=weig;
    }
    
    void floydWarshall()
    {
        int dist[][]=new int[V][V];
        
        for(int i=0;i<V;i++)
        {
            for(int j=0;j<V;j++)
                dist[i][j]=weight[i][j];
        }
        
        for(int k=0;k<V;k++)
        {
            for(int i=0;i<V;i++)
            {
                for(int j=0;j<V;j++)
                {
                    if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j]!=Integer.MAX_VALUE)
                        if(dist[i][k]+dist[k][j]<dist[i][j])
                            dist[i][j]=dist[i][k]+dist[k][j];
                }
            }
        }
        
        printSolution(dist);
        
    }
    
    void printSolution(int dist[][])
    {
        System.out.println("The following matrix represents the shortest distance between pair of vertices : ");
        
        for(int i=0;i<V;i++)
        {
            for(int j=0;j<V;j++)
            {
                if(dist[i][j]==Integer.MAX_VALUE)
                    System.out.print("INF\t");
                else
                    System.out.print(dist[i][j]+"\t");
                
            }
            
            System.out.println();
            
        }
    }
    
}
