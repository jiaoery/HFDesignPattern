/**
 * Project Name:ObserverModelWeather
 * File Name:ForecastDisplay.java
 * Package Name:
 * Date:2018-10-31����3:42:47
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:ForecastDisplay <br/>
 * Function: Ԥ�Ⲽ���
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-31 ����3:42:47 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ForecastDisplay implements Observer,DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;
    
    public ForecastDisplay(Subject weatherData){
        this.weatherData=weatherData;
        weatherData.registerObserver(this);
    }
    
    /**
     * 
     * update:���²��������
     * @author ����
     * @param temperature
     * @param humidity
     * @param pressure
     * @since JDK 1.6
     */
    public void update(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        display();
    }

    /**
     * 
     * display:չʾ���������
     * @author ����
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Forecast: More of the same");
    }

}
