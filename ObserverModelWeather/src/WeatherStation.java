/**
 * Project Name:ObserverModelWeather
 * File Name:WeatherStation.java
 * Package Name:
 * Date:2018-11-8下午2:22:06
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:WeatherStation <br/>
 * Function: 测试类气象站
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 下午2:22:06 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WeatherStation {
    public static void main(String[] args){
        WeatherData1 weatherData=new WeatherData1();
        
        CurrentConditionsDisplay currentConditionsDisplay=new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay=new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay=new ForecastDisplay(weatherData);
        
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
       
    }
}
