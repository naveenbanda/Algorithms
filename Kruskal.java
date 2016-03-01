package graphs;

/**
 *
 * @author Naveen
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.BufferedReader;

public class kruskalsalgo {
    DisJointSet A=new DisJointSet();
    edges edgeSet[];
    
    public kruskalsalgo(int e){
        edgeSet=new edges[e+1];
    }
    
    
    public void MSTKruskal(int vertices,int edges)
    {
        for(int i=1;i<=vertices;i++){
            A.makeSet(i);
        }
        
        for(int i=1;i<=edges;i++){
            for(int j=i+1;j<=edges;j++){
                edges temp=new edges();
                if(edgeSet[i].weight>edgeSet[j].weight){
                    temp=edgeSet[i];
                    edgeSet[i]=edgeSet[j];
                    edgeSet[j]=temp;
                }
            }
        }     
        int w=0;
        
        for(int i=1;i<=vertices;i++){
            if(A.findSet(edgeSet[i].u)!=A.findSet(edgeSet[i].v)){
                A.union(edgeSet[i].u,edgeSet[i].v);
                System.out.println("The edge "+edgeSet[i].u+","+edgeSet[i].v+" is selected");
                w+=edgeSet[i].weight;
            }
            else{
                System.out.println("The edge "+edgeSet[i].u+","+edgeSet[i].v+" is not selected");
            }
        }
        
        System.out.println("The weight of MST is: "+w);
        
        
    }
    public static void main(String args[]){
        int source,destination,vertices,edges,w;
 		
 		Scanner s=new Scanner(System.in); 
 		
 		System.out.println("Enter number of vertices and edges"); 
 		vertices=s.nextInt(); 
		edges=s.nextInt(); 
 		kruskalsalgo graph=new kruskalsalgo(edges); 
 		
	    System.out.println("Enter source and destination and weight respectively"); 
 		for(int i=1;i<=edges;i++) 
 		{ 
 		    source=s.nextInt(); 
 		    destination=s.nextInt(); 
                    w=s.nextInt();
 		    graph.edgeSet[i]=new edges();
                    graph.edgeSet[i].u=source;
                    graph.edgeSet[i].v=destination;
                    graph.edgeSet[i].weight=w;
 		}
                
                graph.MSTKruskal(vertices,edges);
                
 		s.close(); 
    }
}

class edges{
    int u;
    int v;
    int weight;
    
    public edges()
    {
        u=0;
        v=0;
        weight=0;
    }
    
}

class DisJointSet {
    
    private Map<Integer,Node> map=new HashMap<>();
    
    class Node{
        int data;
        Node parent;
        int rank;
    }
    
    public void makeSet(int data){
        Node node=new Node();
        node.data=data;
        node.parent=node;
        node.rank=0;
        map.put(data, node);
    }
    
    public void union(int data1,int data2){
        Node node1=map.get(data1);
        Node node2=map.get(data2);
        Node parent1=findSet(node1);
        Node parent2=findSet(node2);
        
        if(parent1.data==parent2.data){
            return;
        }
        
        if(parent1.rank>=parent2.rank){
            parent1.rank=(parent1.rank==parent2.rank)?parent1.rank+1:parent1.rank;
            parent2.parent=parent1;
        }
        else
        {
            parent1.parent=parent2;
        }
    }
    
    public long findSet(int data){
        return findSet(map.get(data)).data;
    }
    
    private Node findSet(Node node){
        Node parent=node.parent;
        if(parent==node){
            return parent;
        }
        node.parent=findSet(node.parent);
        return node.parent;
    }
    
}
