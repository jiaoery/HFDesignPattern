/**
 * Project Name:StrategyModelDuck
 * File Name:MallardDuck.java
 * Package Name:
 * Date:2018-10-18����2:57:23
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:MallardDuck <br/>
 * Function: ��ͷѼ
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-18 ����2:57:23 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MallardDuck extends Duck{
    
    public MallardDuck(){
        //ʹ��FlyWithWings��Ϊ��FlyBehavior����
        flyBehavior=new FlyWithWings();
        //��ͷѼʹ��Quck�ദ�����ɽУ�
        //���Ե�performQuack������ʱ���������Ϊ��ί�и�Quck����
        quackBehavior=new Quack();
    }
    
    /*
     * ��ΪMallardDuck�̳���Duck��
     * �����Ծ߱�flyBehavior��quackBehaviorʵ������
     */

    public void display() {
        // TODO Auto-generated method stub
        
    }

}
