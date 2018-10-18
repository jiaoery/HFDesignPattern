/**
 * Project Name:StrategyModelDuck
 * File Name:ModelDuck.java
 * Package Name:
 * Date:2018-10-18下午3:36:43
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:ModelDuck <br/>
 * Function: 模型鸭
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-18 下午3:36:43 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ModelDuck extends Duck{
    
    public ModelDuck(){
        flyBehavior=new FlyNoWay();
        quackBehavior=new Quack();
    }

    public void display() {
       System.out.println("I'm a model duck");
        
    }

}
