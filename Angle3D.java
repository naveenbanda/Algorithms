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
import java.lang.*;
import java.util.*;

public class Angle3D {
    
    double calcAngle(points p1,points p2,points p3)
    {
        points v1=new points(p1.x-p2.x,p1.y-p2.y,p1.z-p2.z);
        points v2=new points(p3.x-p2.x,p3.y-p2.y,p3.z-p2.z);
        
        double v1mag = Math.sqrt(v1.x * v1.x + v1.y * v1.y + v1.z * v1.z);
        points v1norm = new points(v1.x / v1mag, v1.y / v1mag, v1.z / v1mag);

        double v2mag = Math.sqrt(v2.x * v2.x + v2.y * v2.y + v2.z * v2.z);
        points v2norm = new points(v2.x / v2mag, v2.y / v2mag, v2.z / v2mag);
        
       
        
        double res=v1norm.x * v2norm.x + v1norm.y * v2norm.y + v1norm.z * v2norm.z;
        
        double angle;
        
        return angle=Math.acos(res);
        
    }
    
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        
        int n;
        double x,y,z;
        
        n=sc.nextInt();
        points points3D[]=new points[n];
        
        for(int i=0;i<n;i++)
        {
            x=sc.nextDouble();
            y=sc.nextDouble();
            z=sc.nextDouble();
            
            points3D[i]=new points(x,y,z);
        }
        
        Angle3D temp=new Angle3D();
        
        double ans=0;
        
        for(int i=0;i<n-2;i++)
        {
            for(int j=i+1;j<n-1;j++)
            {
                for(int k=j+1;k<n;k++)
                {
                    ans+=temp.calcAngle(points3D[i],points3D[j],points3D[k]);
                }
            }
        }
        
        ans=(6*ans)/(n*(n-1)*(n-2));
        
        System.out.print(ans);
        System.out.println();
    }
}

class points
{
    double x;
    double y;
    double z;
    
    points(double a,double b,double c)
    {
        x=a;
        y=b;
        z=c;
    }
}
