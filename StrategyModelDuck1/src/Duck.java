/**
 * Project Name:StrategyModelDuck
 * File Name:Duck.java
 * Package Name:
 * Date:2018-10-18����2:47:26
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Duck <br/>
 * Function: Ѽ����
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-18 ����2:47:26 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class Duck {
    //Ϊ��Ϊ�ӿ����������������ñ��������е�Ѽ�ӣ������ࣩ���̳�����
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    
    public Duck(){
        
    }
    
    public abstract void display();
    
    public void performQuck(){
        //ί�и���Ϊ����
        quackBehavior.quack();
    }
    
    public void performFly(){
      //ί�и���Ϊ����
        flyBehavior.fly();
    }
    
    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior=flyBehavior;
    }
    
    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior=quackBehavior;
    }
    
    public void swim(){
        System.out.println("All ducks float");
    }
}
