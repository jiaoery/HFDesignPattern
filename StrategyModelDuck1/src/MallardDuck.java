/**
 * Project Name:StrategyModelDuck
 * File Name:MallardDuck.java
 * Package Name:
 * Date:2018-10-18下午2:57:23
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:MallardDuck <br/>
 * Function: 绿头鸭
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-18 下午2:57:23 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MallardDuck extends Duck{
    
    public MallardDuck(){
        //使用FlyWithWings作为其FlyBehavior类型
        flyBehavior=new FlyWithWings();
        //绿头鸭使用Quck类处理呱呱叫，
        //所以当performQuack被调用时，叫这个行为被委托给Quck对象
        quackBehavior=new Quack();
    }
    
    /*
     * 因为MallardDuck继承自Duck类
     * ，所以具备flyBehavior与quackBehavior实例变量
     */

    public void display() {
        // TODO Auto-generated method stub
        
    }

}
