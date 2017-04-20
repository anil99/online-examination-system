/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class user {
    private String name;
    private String institute;
    private String degree;
   
  public user(String nam,String ins,String deg)
  {
   this.name = nam;
   this.institute = ins;
   this.degree = deg;
  }
  
  public String getName()
  {
   return name;
  }
  
  public String getIns()
  {
   return institute;
  }
  
  public String getDeg()
  {
   return degree;
  }
}
