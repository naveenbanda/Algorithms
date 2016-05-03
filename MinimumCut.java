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

public class MinimumCut {

    public static void main(String args[])
    {
        int n=6;
        int graph[][]=new int[n][n];
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
                graph[i][j]=0;
        }
        
        graph[0][1]=16;
        graph[0][2]=13;
        graph[1][2]=10;
        graph[1][3]=12;
        graph[2][1]=4;
        graph[2][4]=14;
        graph[3][2]=9;
        graph[3][5]=20;
        graph[4][3]=7;
        graph[4][5]=4;
        
        MaxFlow m=new MaxFlow();
        
        System.out.println();
        
        System.out.println(m.fordFulkerson(graph,0,5));
        
    }
    
}

class MaxFlow
{
    static final int V=6;
    //int parent[];
    
    boolean bfs(int rgraph[][],int s,int t,int parent[])
    {
        boolean visited[]=new boolean[V];
        for(int i=0;i<V;i++)
            visited[i]=false;
        
        LinkedList<Integer> queue=new LinkedList<Integer>();
        queue.add(s);
        parent[s]=-1;
        visited[s]=true;
        
        while(queue.size()!=0)
        {
            int u=queue.poll();
            
            for(int v=0;v<V;v++)
            {
                if(visited[v]==false && rgraph[u][v]>0)
                {
                    queue.add(v);
                    visited[v]=true;
                    parent[v]=u;
                }
            }
        }
        
        return (visited[t]==true);
        
    }
    
    int fordFulkerson(int graph[][],int s,int t)
    {
        int rgraph[][]=new int[V][V];
        for(int i=0;i<V;i++)
        {
            for(int j=0;j<V;j++)
                rgraph[i][j]=graph[i][j];
        }
        
        int parent[]=new int[V];
        int max_flow=0;
        
        while(bfs(rgraph,s,t,parent))
        {
            int path_flow=Integer.MAX_VALUE;
            
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                path_flow=Math.min(path_flow,rgraph[u][v]);
            }
            
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                rgraph[u][v]-=path_flow;
                rgraph[v][u]+=path_flow;
            }
            
            max_flow+=path_flow;
        }
        
        return max_flow;
        
    }
    
}
