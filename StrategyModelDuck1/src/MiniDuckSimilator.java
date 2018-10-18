/**
 * Project Name:StrategyModelDuck
 * File Name:MiniDUCK.java
 * Package Name:
 * Date:2018-10-18����3:12:43
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:MiniDUCK <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-18 ����3:12:43 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MiniDuckSimilator {
    
    public static void main(String[] args) {
        Duck mallerdDuck=new MallardDuck();
        //һ�´����ǽ��������Ϊί�и���Ӧ����Ϊ�ദ����Ϊ
        mallerdDuck.performQuck();
        mallerdDuck.performFly();
        
        Duck modelDuck=new ModelDuck();
        //��һ�λ�ʹ�ù��������ķ���ģʽ
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        //ģ��Ѽ�߱������������
        modelDuck.performFly();
                
    }

}
