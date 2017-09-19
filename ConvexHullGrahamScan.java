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
import java.lang.*;
import java.math.*;

public class ConvexHullGrahamScan {
    
    static point p0;
    
    point nextToTop(Stack <point> S)
    {
        point p=S.peek();
        S.pop();
        point res=S.peek();
        S.push(p);
        return res;
    }
    
    int distSq(point p1,point p2)
    {
        return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
    }
    
    int orientation(point p,point q,point r)
    {
        int val=(q.y-p.y)*(r.x-q.x)-(q.x-p.x)*(r.y-q.y);
        
        if(val==0)
            return 0;
        
        return (val>0)?1:2;
    }
    
    void convexHull(point points[],int n)
    {
        int ymin=points[0].y,min=0;
        
        for(int i=1;i<n;i++)
        {
            int y=points[i].y;
            
            if((y<ymin) || (ymin==y && points[i].x<points[min].x))
            {
                ymin=points[i].y;
                min=i;
            }    
        }
        
        point temp=points[0];
        points[0]=points[min];
        points[min]=temp;
        
        p0=points[0];
        
        Arrays.sort(points,new pointsCompare());
        
        int m=1;
        for(int i=1;i<n;i++)
        {
            while(i<n-1 && orientation(p0,points[i],points[i+1])==0)
                i++;
            points[m]=points[i];
            m+=1;
        }
        
        if(m<3)
            return;
        
        Stack<point> S=new Stack<point>();
        
        S.push(points[0]);
        S.push(points[1]);
        S.push(points[2]);
        
        for(int i=3;i<m;i++)
        {
            while(orientation(nextToTop(S),S.peek(),points[i])!=2)
                S.pop();
            S.push(points[i]);
        }
        
        while(!S.empty())
        {
            point p=S.peek();
            System.out.println("("+p.x+", "+p.y+")");
            S.pop();
        }
    }
    
    public static void main(String args[])
    {
        point points[]=new point[8];
        points[0]=new point(0,3);
        points[1]=new point(1,1);
        points[2]=new point(2,2);
        points[3]=new point(4,4);
        points[4]=new point(0,0);
        points[5]=new point(1,2);
        points[6]=new point(3,1);
        points[7]=new point(3,3);
        
        ConvexHullGrahamScan temp=new ConvexHullGrahamScan();
        
        temp.convexHull(points,(int)8);
        
    }
}

class point
{
    int x;
    int y;
    
    point(int a,int b)
    {
        x=a;
        y=b;
    }
}

class pointsCompare implements Comparator<point>
{
    @Override
    public int compare(point p1,point p2){
        
        ConvexHullGrahamScan temp=new ConvexHullGrahamScan();
        int O=temp.orientation(temp.p0,p1,p2);
        
         if(O==0)
            return(temp.distSq(temp.p0,p2)>=temp.distSq(temp.p0,p1))?-1:1;
        return (O==2)?-1:1;
        
    }
}
