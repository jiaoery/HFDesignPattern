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
 * Function: 摩卡咖啡
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 下午6:10:40 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Mocha extends CondimentDecorator{
	/**
	 * 要让Mocha能够引用一个Beverage，采用以下做法
	 * 1.用一个实例记录饮料，也就是被装饰者
	 * 2.想办法让被装饰者（饮料）被记录在实例变量中。这里的做法是：
	 * 把饮料当作构造器的参数，再由构造器将此饮料记录在实例变量中
	 */
	Beverage1 beverage;
	
	public Mocha(Beverage1 beverage) {
		this.beverage=beverage;
	}
	
	public String getDescription() {
		//这里将调料也体现在相关参数中
		return beverage.getDescription()+",Mocha";
	}
	
	/**
	 * 想要计算带摩卡的饮料的价格，需要调用委托给被装饰者，以计算价格，
	 * 然后加上Mocha的价格，得到最终的结果。
	 */
	public double cost() {
		return 0.21+beverage.cost();
	}
	
	

}
