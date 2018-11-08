import java.util.Observable;

/**
 * Project Name:ObserverModelWeather
 * File Name:WeatherData2.java
 * Package Name:
 * Date:2018-11-8����3:39:43
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:WeatherData2 <br/>
 * Function: JAVA ���ù۲���ģʽ����
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 ����3:39:43 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WeatherData2 extends Observable{
    
    private float temperature;
    
    private float humidity;
    
    private float pressure;
    
    //����������ҪΪ�˼�ס�۲��߽�������ģ��
    public WeatherData2(){
        
    }
    
    
    public void measurementsChanged(){
        //�ڵ���notifyObserver()��Ҫָʾ״̬�Ѿ�������
        setChanged();
       //����ʹ��notifyObserver(object),�������ݲ��������߼�
        notifyObservers(this);
    }
    
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
    
    //���·���Ϊpull�����ṩ
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
