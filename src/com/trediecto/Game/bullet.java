/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.variables.Vector2;

/**
 *
 * @author Zoran
 */
public class bullet
{
    public double X;
    public double Y;
    double speed =3;
    double rage = 500;
    public double rotation = 0;
    public bullet(double X, double Y, double rotation)
    {
        this.X = X;
        this.Y = Y;
        this.rotation = rotation;
    }
    public void Move()
    {
        double radian = ((rotation+270)/57.2957795d);
        double rot = (radian % 6.28318531d);
        double volX = Math.cos(rot) * speed;
        double volY = Math.sin(rot) * speed;
        if (volX > 0)
        {
            rage -= volX;
        }
        if (volY > 0)
        {
            rage -= volY;
        }
        if (volX < 0)
        {
            rage += volX;
        }
        if (volY < 0)
        {
            rage += volY;
        }
        
        X += volX;
        Y += volY;
    }
    public boolean Collision(Vector2 pos, Vector2 size)
    {
        for (int i =pos.X; i < pos.X +size.X; i++)
        {
            for (int y =pos.Y; y < size.Y+pos.Y;y++)
            {
                if ((int)this.X == i && (int)this.Y== y)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
