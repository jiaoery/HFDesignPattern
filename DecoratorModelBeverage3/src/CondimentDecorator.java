/**
 * Project Name:DecoratorModelBeverage
 * File Name:CondimentDecorator.java
 * Package Name:
 * Date:2019-1-14下午6:02:18
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:CondimentDecorator <br/>
 * Function: 调料装饰类
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 下午6:02:18 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class CondimentDecorator extends Beverage1{

    //所有的调料装饰者都必须重新实现 getDescription()方法。 
    public abstract String getDescription();
}
