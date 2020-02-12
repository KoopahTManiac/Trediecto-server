/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.variables;

/**
 *
 * @author TE10ZoOs
 */
public class Rectangle
{
    public double X;
    public double Y;
    public double Width;
    public double Height;
    //Constructor For The Rectange In Int
    public Rectangle(int x , int y , int width , int height)
    {
        X=x;
        Y=y;
        Width=width;
        Height=height;
    }
    //Constructor For The Rectange In Double
    public Rectangle(double x , double y , double width , double height)
    {
        X=x;
        Y=y;
        Width=width;
        Height=height;
    }
    //Function To Cheack Collison Of Two Rectangles
    public boolean CheckCollision(Rectangle Target)
    {
        for (double i = X; i < (Width+X); i++)
        {
            for (double j = Y; j < (Height+Y); j++)
            {
                if (i >Target.X && i < Target.Width+Target.X && j > Target.Y && j <Target.Height+Target.Y)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
