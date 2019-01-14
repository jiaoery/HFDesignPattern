/**
 * Project Name:DecoratorModelBeverage
 * File Name:HouseBlend.java
 * Package Name:
 * Date:2019-1-14����6:10:40
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:HouseBlend <br/>
 * Function: ���ݿ���
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 ����6:10:40 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Whip extends CondimentDecorator{
	/**
	 * Ҫ��Whip�ܹ�����һ��Beverage��������������
	 * 1.��һ��ʵ����¼���ϣ�Ҳ���Ǳ�װ����
	 * 2.��취�ñ�װ���ߣ����ϣ�����¼��ʵ�������С�����������ǣ�
	 * �����ϵ����������Ĳ��������ɹ������������ϼ�¼��ʵ��������
	 */
	Beverage1 beverage;
	
	public Whip(Beverage1 beverage) {
		this.beverage=beverage;
	}
	
	public String getDescription() {
		//���ｫ����Ҳ��������ز�����
		return beverage.getDescription()+",Whip";
	}
	
	/**
	 * ��Ҫ��������ݵ����ϵļ۸���Ҫ����ί�и���װ���ߣ��Լ���۸�
	 * Ȼ�����Whip�ļ۸񣬵õ����յĽ����
	 */
	public double cost() {
		return 0.22+beverage.cost();
	}
}
