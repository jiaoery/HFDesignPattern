/**
 * Project Name:DecoratorModelBeverage
 * File Name:Espresso.java
 * Package Name:
 * Date:2019-1-14下午6:06:59
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Espresso <br/>
 * Function: 浓缩咖啡
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 下午6:06:59 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Espresso extends Beverage1{
    
    public Espresso(){
        //为了要设置饮料的描述，我 们写了一个构造器。记住， description实例变量继承自Beverage1
        description="Espresso";
    }
    
    public double cost() {
        //最后，需要计算Espresso的价钱，现在不需要管调料的价钱，直接把Espresso的价格$1.99返回即可。
        return 1.99;
    }
}
