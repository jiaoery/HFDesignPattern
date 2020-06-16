/**
 * Project Name:ObserverModelWeather
 * File Name:WeatherStation1.java
 * Package Name:
 * Date:2018-11-8����4:08:34
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:WeatherStation1 <br/>
 * Function: ���� JAVA���ù۲���ģʽ
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 ����4:08:34 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WeatherStation1 {
    public static void main(String[] args){
        WeatherData2 weatherData=new WeatherData2();
        
        CurrentConditionsDisplay1 currentConditionsDisplay=new CurrentConditionsDisplay1(weatherData);
        StatisticsDisplay1 statisticsDisplay=new StatisticsDisplay1(weatherData);
        ForecastDisplay1 forecastDisplay=new ForecastDisplay1(weatherData);
        
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
       
    }
}
