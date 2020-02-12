/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.variables.Vector2;

/**
 *
 * @author TE10ZoOs
 */
public class AI
{
   public Vector2 Position;
   public int Hp;
   public int HpMax;
   //AI Constructor
   public AI(Vector2 Pos, int hp, int hpMax)
   {
       Position=Pos;
       Hp=hp;
       HpMax=hpMax;
   }
   //Move Function For The Ai
   public void Move(double distance, double direktion)
   {
       //emty
   }
}
