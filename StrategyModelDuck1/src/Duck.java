/**
 * Project Name:StrategyModelDuck
 * File Name:Duck.java
 * Package Name:
 * Date:2018-10-18下午2:47:26
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Duck <br/>
 * Function: 鸭子类
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-18 下午2:47:26 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class Duck {
    //为行为接口类型声明两个引用变量，所有的鸭子（或子类）都继承它们
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    
    public Duck(){
        
    }
    
    public abstract void display();
    
    public void performQuck(){
        //委托给行为处理
        quackBehavior.quack();
    }
    
    public void performFly(){
      //委托给行为处理
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
