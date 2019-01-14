/**
 * Project Name:DecoratorModelBeverage
 * File Name:HouseBlend.java
 * Package Name:
 * Date:2019-1-14下午6:10:40
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:HouseBlend <br/>
 * Function: 主测试类
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 下午6:10:40 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StarbuzzCoffe {
	
	public static void main(String[] args) {
		//订购一杯Espresso，不需要调料，打印他的价格和描述
		Beverage1 beverage=new Espresso();
		System.out.println(beverage.getDescription()+"$"
				+beverage.cost());
		
		//开始装饰双倍摩卡+奶泡咖啡
		Beverage1 beverage2=new DarkRoast1();
		beverage2=new Mocha(beverage2);
		beverage2=new Mocha(beverage2);
		beverage2=new Whip(beverage2);
		
		System.out.println(beverage2.getDescription()+"$"
				+beverage2.cost());
		
		//
		Beverage1 beverage3=new HouseBlend();
		beverage3=new Soy(beverage3);
		beverage3=new Mocha(beverage3);
		beverage3=new Whip(beverage3);
		
		System.out.println(beverage3.getDescription()+"$"
				+beverage3.cost());
		
		
	}
}
