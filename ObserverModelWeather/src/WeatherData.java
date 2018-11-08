/**
 * Project Name:ObserverModelWeather
 * File Name:WeatherData.java
 * Package Name:
 * Date:2018-10-31下午3:17:12
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:WeatherData <br/>
 * Function: 天气数据
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-31 下午3:17:12 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WeatherData {
    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压
    
    private CurrentConditionsDisplay currentConditionsDisplay;//目前状态布告板
    private StatisticsDisplay statisticsDisplay;//统计布告板
    private ForecastDisplay forecastDisplay;//预测布告板
    
    
    public float getTemperature() {
        return temperature;
    }
    
    public float getHumidity(){
        return humidity;
    }
    
    public float getPressure(){
        return pressure;
    }

    //实例变量声明
    public  void measurementsChanged(){
        //调用WeatherData的三个getter方法获取最近的测量值
        float temp=getTemperature();
        float humidity=getHumidity();
        float pressure=getPressure();
        
        currentConditionsDisplay.update(temp,humidity,pressure);
        statisticsDisplay.update(temp,humidity,pressure);
        forecastDisplay.update(temp,humidity,pressure);
    }
    
    //通知发生变化
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
}
