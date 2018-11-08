/**
 * Project Name:ObserverModelWeather
 * File Name:Subject.java
 * Package Name:
 * Date:2018-11-8上午11:25:54
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Subject <br/>
 * Function: 主题接口，被观察者
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 上午11:25:54 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface Subject {

    //注册观察者
    public void registerObserver(Observer o);
    
    //删除观察者
    public void removeObserver(Observer o);
    
    //当主题发生数据变化时，通知所有观察
    public void notifyObservers();
    
}
