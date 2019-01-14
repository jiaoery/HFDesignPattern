/**
 * Project Name:DecoratorModelBeverage
 * File Name:Beverage.java
 * Package Name:
 * Date:2019-1-14����2:36:13
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Beverage <br/>
 * Function: ����
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 ����2:36:13 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Beverage {
    protected String description;//���ϼ��
    
    protected boolean milk=false;//�Ƿ���ţ��
    
    protected boolean soy=false;//�Ƿ��ж���
    
    protected boolean cocha=false;//�Ƿ���Ħ��
    
    protected boolean whip=false;//�Ƿ�������
    
    protected double milkCost=1.01;//ţ�̼۸�
    
    protected double soyCost=1.03;//�����۸�
    
    protected double cochaCost=2.23;//Ħ���۸�
    
    protected double whipCost=0.89;//���ݼ۸�
    
    
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
        if(hasMilk()){//�Ƿ���Ҫţ��
            condiments+=milkCost;
        }
        if(hasSoy()){//�Ƿ���Ҫ����
            condiments+=soyCost;
        }
        if(hasCocha()){//�Ƿ���ҪĦ��
            condiments+=cochaCost;
        }
        if(hasWhip()){//�Ƿ���Ҫ����
            condiments+=whipCost;
        }
        return condiments;
    }

}
