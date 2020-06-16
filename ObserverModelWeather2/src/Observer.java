/**
 * Project Name:ObserverModelWeather
 * File Name:Observer.java
 * Package Name:
 * Date:2018-11-8上午11:29:11
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Observer <br/>
 * Function: 观察者
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 上午11:29:11 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface Observer {

   /**
    * 
    * update:当气象站的观测数据发生改变时，这个方法会被调用
    * @author 吉祥
    * @param temp 温度
    * @param hunmidity 湿度
    * @param pressure  气压
    * @since JDK 1.6
    */
    public void update(float temp,float hunmidity,float pressure);
}
