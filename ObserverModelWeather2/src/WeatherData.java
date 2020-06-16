/**
 * Project Name:ObserverModelWeather
 * File Name:WeatherData.java
 * Package Name:
 * Date:2018-10-31����3:17:12
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:WeatherData <br/>
 * Function: ��������
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-31 ����3:17:12 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WeatherData {
    private float temperature;//�¶�
    private float humidity;//ʪ��
    private float pressure;//��ѹ
    
    private CurrentConditionsDisplay currentConditionsDisplay;//Ŀǰ״̬�����
    private StatisticsDisplay statisticsDisplay;//ͳ�Ʋ����
    private ForecastDisplay forecastDisplay;//Ԥ�Ⲽ���
    
    
    public float getTemperature() {
        return temperature;
    }
    
    public float getHumidity(){
        return humidity;
    }
    
    public float getPressure(){
        return pressure;
    }

    //ʵ����������
    public  void measurementsChanged(){
        //����WeatherData������getter������ȡ����Ĳ���ֵ
        float temp=getTemperature();
        float humidity=getHumidity();
        float pressure=getPressure();
        
        currentConditionsDisplay.update(temp,humidity,pressure);
        statisticsDisplay.update(temp,humidity,pressure);
        forecastDisplay.update(temp,humidity,pressure);
    }
    
    //֪ͨ�����仯
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
}
