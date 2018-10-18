/**
 * Project Name:StrategyModelDuck
 * File Name:MiniDUCK.java
 * Package Name:
 * Date:2018-10-18下午3:12:43
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:MiniDUCK <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-18 下午3:12:43 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MiniDuckSimilator {
    
    public static void main(String[] args) {
        Duck mallerdDuck=new MallardDuck();
        //一下代码是将具体的行为委托给对应的行为类处理行为
        mallerdDuck.performQuck();
        mallerdDuck.performFly();
        
        Duck modelDuck=new ModelDuck();
        //第一次会使用构造参数里的飞行模式
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        //模型鸭具备火箭飞行能力
        modelDuck.performFly();
                
    }

}
