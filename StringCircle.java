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

public class StringCircle {
    
    static boolean canBeChained(String arr[],int n)
    {
        Graph g=new Graph(26);
        
        for(int i=0;i<n;i++)
        {
            String s=arr[i];
          
            g.addEdge(s.charAt(0)-'a',s.charAt(s.length()-1)-'a');
        }
        
        return g.isEulerianCycle();
        
    }
    
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        
        int t,n;
        String arr[];
        
        t=sc.nextInt();
        
        while(t>=1)
        {
            n=sc.nextInt();
            arr=new String[n];
            
            for(int i=0;i<n;i++)
                arr[i]=sc.next();
            
            if(canBeChained(arr,n))
                System.out.println("Head to tail ordering is possible.");
            else
                System.out.println("Head to tail ordering is not  possible.");
            
            t-=1;
        }
        
    }
}

class Graph
{
    boolean visited[];
    int V;
    int in[];
    private HashMap<Integer,List<Integer>> AdjList;
    
    public Graph(int vertices)
    {
        V=vertices;
        
        in=new int[26];
        
        for(int i=0;i<26;i++)
            in[i]=0;
        
        visited=new boolean[26];
        
        AdjList=new HashMap<Integer,List<Integer>>();
        
        for(int i=0;i<vertices;i++)
            AdjList.put(i,new LinkedList<Integer>());
        
    }
    
    public void addEdge(int source,int dest)
    {
        if(source>AdjList.size()-1 || dest>AdjList.size()-1)
            System.out.println("Cannot find the specified");
        else
        {
            List<Integer> sourceList=AdjList.get(source);
            sourceList.add(dest);
            in[dest]+=1;
            
        }
        
    }
    
    public boolean isEulerianCycle()
    {
        if(isSC()==false)
            return false;
        
        int count=0;
        
        for(int i=0;i<V;i++)
        {
            if(AdjList.get(i).size()!=in[i])
                count+=1;
            
            if(count>2)
                return false;
        }
        
        return true;
        
    }
    
    public boolean isSC()
    {
        for(int i=0;i<V;i++)
            visited[i]=false;
        
        int n;
        
        for(n=0;n<V;n++)
            if(AdjList.get(n).size()>0)
                break;
        
        DFSUtil(n);
        
        for(int i=0;i<V;i++)
            if(AdjList.get(i).size()>0 && visited[i]==false)
                return false;  
           
        Graph gr=getTranspose();
        
        for(int i=0;i<V;i++)
            gr.visited[i]=false;
        
        gr.DFSUtil(n);
        
        for(int i=0;i<V;i++)
            if(AdjList.get(i).size()>0 && visited[i]==false)
                return false;
            
        return true;
        
    }
    
    public void DFSUtil(int v)
    {
        visited[v]=true;
        
        List<Integer> temp=AdjList.get(v);
        int size=temp.size();
        
        for(int i=0;i<size;i++)
        {
            int x=temp.get(i);
            if(!visited[x])
                    DFSUtil(x);
                
        }
    }
    
    public Graph getTranspose()
    {
        Graph temp=new Graph(V);
        
        for(int v=0;v<V;v++)
        {
            List<Integer> list=AdjList.get(v);
            int size=list.size();
            
            for(int i=0;i<size;i++)
            {
                temp.addEdge(list.get(i),v);
            }
        }
        
        return temp;
        
    }
}
