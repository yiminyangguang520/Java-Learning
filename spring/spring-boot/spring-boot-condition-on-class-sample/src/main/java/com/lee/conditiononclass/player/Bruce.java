package com.lee.conditiononclass.player;


import com.lee.conditiononclass.fighter.Fighter;

/**
 * @author bruce
 */
public class Bruce {

  private Fighter fighter;

  public Bruce(Fighter fighter) {
    this.fighter = fighter;
  }

  public void fight() {
    System.out.println("bruce：boy next door,do you like 玩游戏");
    fighter.fight();
  }
}
