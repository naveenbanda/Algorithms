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

public class FordFulkerson {
    
    static int parent[];
    
    static boolean bfs(int rGraph[][],int s,int t)
    {
        parent=new int[6];
        boolean visited[]=new boolean[6];
        for(int i=0;i<6;i++)
            visited[i]=false;
        
        Queue<Integer> q=new LinkedList<Integer>();
        
        q.add(s);
        visited[s]=true;
        parent[s]=-1;
        
        while(!q.isEmpty())
        {
            int u=q.peek();
            q.remove();
            
            for(int v=0;v<6;v++)
            {
                if(visited[v]==false && rGraph[u][v]>0)
                {
                    q.add(v);
                    parent[v]=u;
                    visited[v]=true;
                }
            }
        }
        
        return (visited[t]==true);
        
    }
    
    static int fordFulkerson(int graph[][],int s,int t)
    {
        int rGraph[][]=new int[6][6];
        
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
                rGraph[i][j]=graph[i][j];
        }
        
        int max_flow=0;
        
        while(bfs(rGraph,s,t))
        {
            int path_flow=Integer.MAX_VALUE;
            
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                path_flow=Math.min(path_flow,rGraph[u][v]);
            }
            
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                rGraph[u][v]-=path_flow;
                rGraph[v][u]+=path_flow;
                
            }
            
            max_flow+=path_flow;
            
        }    
        
        return max_flow;
        
    }
    
    public static void main(String args[])
    {
        int graph[][]=new int[6][6];
        
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
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
        System.out.println("The maximum possible flow is : "+fordFulkerson(graph,0,5));
        
    }
    
    
}
