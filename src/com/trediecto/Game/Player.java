/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.variables.Rectangle;
import com.trediecto.variables.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zoran Ostojic
 */
public class Player
{
    //Player Variables
    public int HP;
    public int MP;
    public int HP_MAX;
    public int MP_MAX;
    public int XP;
    public int LEVEL;
    public int status;
    public Vector2 POS = new Vector2(0, 0);
    public Rectangle BoundsRectangle;
    public double speed = 2;
    public boolean moving = false;
    public double rotation =0;
    public double rotationT =0;
    public Vector2 FieldOfView = new Vector2(800,600);
    public float speed_multiplier = 1;
    public boolean shoot = false;
    public long ShootTime =0;
    public long ShootSpeed = (1000000*1000)/60;
    public Vector2 Mouse = new Vector2(10, 10);
    public String username ="";
    public String passwords = "";
    public List<bullet> bullets = new ArrayList<>();
    public int ID;
    public List<Integer> VisiblePlayers = new ArrayList<>();
    //Player Constructor
    public Player(int Hp, int Mp, int Hp_MAX,int Mp_MAX, int Xp, int Level, Vector2 Pos,String User, String Pass, int ID) 
    {
        HP = Hp;
        MP = Mp;
        HP_MAX = Hp_MAX;
        MP_MAX = Mp_MAX;
        XP = Xp;
        LEVEL = Level;
        POS = Pos;
        speed_multiplier =1;
        this.username = User;
        this.passwords = Pass;
        this.ID = ID;
        UpdateBound();
        VisiblePlayers.add(0);
    }
    public void UpdateBound()
    {
        BoundsRectangle = new Rectangle(POS.X, POS.Y, 50, 50);
    }
    //Player Move Function
    public void Move(double distance, double rotation)
    {
        double radian = ((rotation+270)/57.2957795d);
        double rot = (radian % 6.28318531d);
        double vol_X = Math.cos(rot) * distance * speed_multiplier;
        double vol_Y = Math.sin(rot) * distance * speed_multiplier;
        POS.X += (int)vol_X;
        POS.Y += (int)vol_Y;
        UpdateBound();
    }
    public void Move(String direction)
    {
        switch (direction)
        {
            case "Up":
            {
                speed_multiplier =1;
                rotation = 0;
                moving = true;
                break;
            }
            case "RightUp":
            {
                speed_multiplier =1.5f;
                rotation = 45;
                moving = true;
                break;
            }
            case "Right":
            {
                speed_multiplier =1;
                rotation = 90;
                moving = true;
                break;
            }
            case "RightDown":
            {
                speed_multiplier =1.5f;
                rotation = 135;
                moving = true;
                break;
            }
            case "Down":
            {
                speed_multiplier =1;
                rotation = 180;
                moving = true;
                break;
            }
            case "LeftDown":
            {
                speed_multiplier =1.5f;
                rotation = 225;
                moving = true;
                break;
            }
            case "Left":
            {
                speed_multiplier =1;
                rotation = 270;
                moving = true;
                break;
            }
            case "LeftUp":
            {
                speed_multiplier =1.5f;
                rotation = 315;
                moving = true;
                break;
            }
            case "Stop":
            {
                speed_multiplier =1;
                moving = false;
                break;
            }
        }
    }
    public boolean CheackRage(Player Target, Vector2 rage)
    {
        int temp_pos_x = this.POS.X;
        int temp_pos_y = this.POS.Y;
        int temp_pos2_x = Target.POS.X;
        int temp_pos2_y = Target.POS.Y;
        if (temp_pos2_x <= temp_pos_x + rage.X && temp_pos2_x >= temp_pos_x - rage.X && temp_pos2_y <= temp_pos_y + rage.Y && temp_pos2_y >= temp_pos_y - rage.Y)
        {
            return true;
        }
        else
        {
            return false;
        }    
        
    }
}
