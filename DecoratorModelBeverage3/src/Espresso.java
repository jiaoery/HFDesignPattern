/**
 * Project Name:DecoratorModelBeverage
 * File Name:Espresso.java
 * Package Name:
 * Date:2019-1-14����6:06:59
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Espresso <br/>
 * Function: Ũ������
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 ����6:06:59 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Espresso extends Beverage1{
    
    public Espresso(){
        //Ϊ��Ҫ�������ϵ��������� ��д��һ������������ס�� descriptionʵ�������̳���Beverage1
        description="Espresso";
    }
    
    public double cost() {
        //�����Ҫ����Espresso�ļ�Ǯ�����ڲ���Ҫ�ܵ��ϵļ�Ǯ��ֱ�Ӱ�Espresso�ļ۸�$1.99���ؼ��ɡ�
        return 1.99;
    }
}
