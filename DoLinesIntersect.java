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

public class DoLinesIntersect {
    
    static boolean onSegment(Point p,Point q,Point r)
    {
        if(q.x<=Math.max(p.x,r.x) && q.x>=Math.min(p.x,r.x) && q.y<=Math.max(p.y,r.y) && q.y>=Math.min(p.y,r.y))
            return true;
        return false;
    }
    
    static int orientation(Point p,Point q,Point r)
    {
        int val=(q.y-p.y)*(r.x-q.x)-(q.x-p.x)*(r.y-q.y);
        
        if(val==0)
            return 0;
        return (val>0)?1:2;
    }
    
    static boolean doIntersect(Point p1,Point q1,Point p2,Point q2)
    {
        int o1=orientation(p1,q1,p2);
        int o2=orientation(p1,q1,q2);
        int o3=orientation(p2,q2,p1);
        int o4=orientation(p2,q2,q1);
        
        if(o1!=o2 && o3!=o4)
            return true;
        if(o1==0 && onSegment(p1,p2,q1))
                return true;
        if(o2==0 && onSegment(p1,q2,q1))
            return true;
        if(o3==0 && onSegment(p2,p1,q2))
            return true;
        if(o4==0 && onSegment(p2,q1,q2))
            return true;
        
        return false;
    }
    
    public static void main(String args[])
    {
        Point p1=new Point(1,1),q1=new Point(10,1);
        Point p2=new Point(1,2),q2=new Point(10,2);
        
        if(doIntersect(p1,q1,p2,q2)==true)
            System.out.println("Yes");
        else
            System.out.println("No");
        
        p1=new Point(10,0);
        q1=new Point(0,10);
        p2=new Point(0,0);
        q2=new Point(10,10);
        
        if(doIntersect(p1,q1,p2,q2)==true)
            System.out.println("Yes");
        else
            System.out.println("No");
        
        p1=new Point(-5,-5);
        q1=new Point(0,0);
        p2=new Point(1,1);
        q2=new Point(10,10);
        
         if(doIntersect(p1,q1,p2,q2)==true)
            System.out.println("Yes");
        else
            System.out.println("No");
        
    }
}

class Point
{
    int x;
    int y;
    
    Point(int a,int b)
    {
        x=a;
        y=b;
    }
    
}
