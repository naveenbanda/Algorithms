/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

/**
 *
 * @author Naveen
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ConvexHullJarvisAlgo {
    
    static int orientation(Points p,Points q,Points r)
    {
        int val=(q.y-p.y)*(r.x-q.x)-(q.x-p.x)*(r.y-q.y);
        if(val==0)
            return 0;
        return (val>0)?1:2;
    }
    
    static void convexHull(Points points[],int n)
    {
        if(n<3)
            return;
        
        Vector<Points> hull=new Vector<Points>();
        
        int l=0;
        for(int i=1;i<n;i++)
            if(points[i].x<points[l].x)
                l=i;
        
        int p=l,q;
        
        do
        {
            hull.add(points[p]);
            
            q=(p+1)%n;
            
            for(int i=0;i<n;i++)
            {
                if(orientation(points[p],points[i],points[q])==2)
                    q=i;
            }
            
            p=q;
        }while(p!=l);
        
        for(int i=0;i<hull.size();i++)
        {
            System.out.println("("+hull.get(i).x+", "+hull.get(i).y+")");
        }
    }
    
    public static void main(String args[])
    {
        Points points[]=new Points[7];
        points[0]=new Points(0,3);
        points[1]=new Points(2,2);
        points[2]=new Points(1,1);
        points[3]=new Points(2,1);
        points[4]=new Points(3,0);
        points[5]=new Points(0,0);
        points[6]=new Points(3,3);
      
        int n=7;
        
        convexHull(points,n);
        
    }
}

class Points
{
    int x;
    int y;
    
    Points(int a,int b)
    {
        x=a;
        y=b;
    }
}
