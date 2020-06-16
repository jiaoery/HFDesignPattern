/**
 * Project Name:DecoratorModelBeverage
 * File Name:Beverage.java
 * Package Name:
 * Date:2019-1-14ÏÂÎç2:36:13
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Beverage <br/>
 * Function: »ùÀà
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 ÏÂÎç2:36:13 <br/>
 * @author   ¼ªÏé
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Beverage {
    protected String description;//ÒûÁÏ¼ò½é
    
    protected boolean milk=false;//ÊÇ·ñÓÐÅ£ÄÌ
    
    protected boolean soy=false;//ÊÇ·ñÓÐ¶¹½¬
    
    protected boolean cocha=false;//ÊÇ·ñÓÐÄ¦¿¨
    
    protected boolean whip=false;//ÊÇ·ñÓÐÄÌÅÝ
    
    protected double milkCost=1.01;//Å£ÄÌ¼Û¸ñ
    
    protected double soyCost=1.03;//¶¹½¬¼Û¸ñ
    
    protected double cochaCost=2.23;//Ä¦¿¨¼Û¸ñ
    
    protected double whipCost=0.89;//ÄÌÅÝ¼Û¸ñ
    
    
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean hasMilk() {
        return milk;
    }


    public void setMilk(boolean milk) {
        this.milk = milk;
    }


    public boolean hasSoy() {
        return soy;
    }


    public void setSoy(boolean soy) {
        this.soy = soy;
    }


    public boolean hasCocha() {
        return cocha;
    }


    public void setCocha(boolean cocha) {
        this.cocha = cocha;
    }


    public boolean hasWhip() {
        return whip;
    }


    public void setWhip(boolean whip) {
        this.whip = whip;
    }
    
    


    public double getCochaCost() {
        return cochaCost;
    }


    public void setCochaCost(double cochaCost) {
        this.cochaCost = cochaCost;
    }


    public double getWhipCost() {
        return whipCost;
    }


    public void setWhipCost(double whipCost) {
        this.whipCost = whipCost;
    }


   

    public double cost(){
        
        double condiments=0.0;
        if(hasMilk()){//ÊÇ·ñÐèÒªÅ£ÄÌ
            condiments+=milkCost;
        }
        if(hasSoy()){//ÊÇ·ñÐèÒª¶¹½¬
            condiments+=soyCost;
        }
        if(hasCocha()){//ÊÇ·ñÐèÒªÄ¦¿¨
            condiments+=cochaCost;
        }
        if(hasWhip()){//ÊÇ·ñÐèÒªÄÌÅÝ
            condiments+=whipCost;
        }
        return condiments;
    }

}
