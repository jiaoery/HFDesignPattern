import java.util.Observable;

/**
 * Project Name:ObserverModelWeather
 * File Name:WeatherData2.java
 * Package Name:
 * Date:2018-11-8下午3:39:43
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:WeatherData2 <br/>
 * Function: JAVA 内置观察者模式更改
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 下午3:39:43 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WeatherData2 extends Observable{
    
    private float temperature;
    
    private float humidity;
    
    private float pressure;
    
    //构造器不需要为了记住观察者建立数据模型
    public WeatherData2(){
        
    }
    
    
    public void measurementsChanged(){
        //在调用notifyObserver()需要指示状态已经更改了
        setChanged();
       //这里使用notifyObserver(object),所以数据采用拉的逻辑
        notifyObservers(this);
    }
    
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
    
    //以下方法为pull操作提供
    public float getTemperature() {
        return temperature;
    }
    
    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
