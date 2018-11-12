
本文参照《Head First 设计模式》，转载请注明出处
对于整个系列，我们按照这本书的设计逻辑，使用情景分析的方式来描述，并且穿插使用一些问题，总结的方式来讲述。并且所有的开发源码，都会托管到github上。
项目地址：https://github.com/jixiang52002/HFDesignPattern

前一章主要讲解了设计模式入门和最常用的一个模式-----策略模式，并结合Joe的鸭子模型进行分析，想要了解的朋友可以回去回看一下。
这里我们将继续介绍一种可以帮助对象知悉现状，不会错过该对象感兴趣的事。甚至对象可以自己决定是都要继续接受通知。有过设计模式学习经验的人会脱口而出-----**观察者模式**。对的，接下来我们将了解一个新的设计模式，也就是观察者模式。

# 1.引言
最近你的团队获取了一个新的合约，需要负责建立一个Weather-O-Rama公司的下一代气象站----Internet气象观测站。
合约内容如下：
>恭喜贵公司获选为敝公司建立下一代Internet气象观测站！该气象站必须建立在我们专利申请的WeatherData对象上，由WeatherData对象负责追踪目前的天气状况（温度、湿度、气压）。我们希望贵公司能建立一个应用，有三种布告板，分别显示目前的状况、气象统计及简单的预报。当WeatherData对象获取到最新的测量数据时，三种布告板必须实时更新。
而且，这是一个可以拓展的气象站，Weather-O-Rama气象站希望公布一组API，让其他开发人员可以写出自己的气象布告板，并插入此应用中我们希望贵公司可以提供这样的API。
Weather-O-Rama气象站有很好的商业运营模式：一旦客户上钩，他们使用每个布告板都要付钱最好的部分就是，为了感谢贵公司建立此系统，我们将以公司的认股权支付你。
我们期待看到你的设计和应用的alpha版本。
附注：我们正在通宵整理WeatherData源文件给你们。

## 1.1需求分析
根据开发的经验，我们首先分析Weather-O-Rama公司的需求：
* 此系统有三个部分组成：气象站（获取实际的气象数据的物理组成），WeatherData对象（追踪来自气象站的数据，并更新布告板）和布告板（显示目前天气状况展示给用户）
* 项目应用中，开发者需要利用WeatherData去实时获取气象数据，并且更新三个布告板：目前气象，气象统计和天气预报。
* 系统必须具备很高的可拓展性，让其他的开发人员可以建立定制的布告板，用户可以随心所欲地添加或删除任何布告板。

我们初始设计结构如下：
![初始设计结构](https://upload-images.jianshu.io/upload_images/2326194-789a518852773f29.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 1.2WeatherData类
第二天，Weather-O-Rama公司发送过来WeatherData的源码，其结构如下图
![WeatherData数据结构](https://upload-images.jianshu.io/upload_images/2326194-c89b6120eb179b7d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



其中measurementsChanged（）方法在气象测试更新时，被调用。

## 1.3错误的编码方式
首先，我们从大部分不懂设计模式的开发者常用的设计方式开始。
根据Weather-O-Rama气象站开发人员的需求暗示，在measurementsChanged()方法中添加相关的代码：
```
public class WeatherData {
    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压
    
    private CurrentConditionsDisplay currentConditionsDisplay;//目前状态布告板
    private StatisticsDisplay statisticsDisplay;//统计布告板
    private ForecastDisplay forecastDisplay;//预测布告板
    
    public WeatherData(CurrentConditionsDisplay currentConditionsDisplay
            ,StatisticsDisplay statisticsDisplay
            ,ForecastDisplay forecastDisplay){
        this.currentConditionsDisplay=currentConditionsDisplay;
        this.statisticsDisplay=statisticsDisplay;
        this.forecastDisplay=forecastDisplay;
    }
    
    
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
```

回顾第一章的三个设计原则，我们发现这里违反了几个原则
>第一设计原则
找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混合在一起。

>第二设计原则
针对于接口编程，不针对实现编程

>第三设计原则
多用组合，少用继承

在这里我们使用了针对实现编程，并且没有将变化部分独立出来，这样会导致我们以后在增加或删除布告板时必须修改应用程序。而且，最重要的是，我们牺牲了可拓展性。
![分析错误点](https://upload-images.jianshu.io/upload_images/2326194-0ca3c0968a6443d9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
既然这里我们提到了要使用观察者模式来解决问题，那么该如何下手。并且，什么是观察者模式？

# 2.观察者模式
## 2.1认识观察者模式
为了方便理解，我们从日常生活中常遇到的情形来理解观察者模式，这里我们使用生活常见的报纸和杂志订阅业务逻辑来理解：

* 报社的业务在于出版报纸
* 订阅报纸的用户，只要对应报社有新的报纸出版，就会给你送来
* 当用户不想继续订阅报纸，可以直接取消订阅。那么之后就算有新的报纸出版，也不会送给对应用户了。
* 只要报社一直存在，任何用户都可以自由订阅或取消订阅报纸

从上面的逻辑我们分析出，这里由以下部分组成，报社，用户，订阅。将其抽象出来就i是：出版者，订阅者，订阅。这里观察者模式的雏形已经出来了。
>**出版者+订阅者=观察者模式**

如果上面已经理解了报社报纸订阅的逻辑，也可以很快知道观察者模式是什么。只是在其中名称会有差异，前面提到的“出版者”我们可以称为**“主题（Subject）”**或**“被观察者（Observable）”**（后一个更加常用），“订阅者”我们称为**“观察者（Observer）”**，这里我们采用类UML的结构图来解释：

![观察者模式结构图](https://upload-images.jianshu.io/upload_images/2326194-6fcc2738ea8b3420.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 2.2 观察者模式注册/取消注册
场景1：
某一天，鸭子对象觉得自己的朋友都订阅了主题，自己也想称为一个观察者。于是告诉主题，它想当一个观察者。完成订阅后，鸭子也成为一个观察者了。
![鸭子成为观察者后的结构图](https://upload-images.jianshu.io/upload_images/2326194-39c4a58cfbd1f181.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
这样当主题数据发生变化时，鸭子对象也可以得到通知了！！

场景2：
老鼠对象厌烦了每天都被主题烦，决定从观察者序列离开，于是它告诉主题它想离开观察者行列，主题将它从观察者中除名。
![老鼠离开观察者后的结构图](https://upload-images.jianshu.io/upload_images/2326194-0996c2773bce66b3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
之后主题数据发生变化时，不会再通知老鼠对象。

上面的两个情形分别对应了注册和取消注册，这也是观察者模式最重要的两个概念。注册后的对象我们才可以称为**观察者**。观察者取消注册后也不能称为观察者。

## 2.3 观察者模式定义
通过报纸业务和对象订阅的例子，我们可以勾勒出观察者模式的基本概念。
>**观察者模式**定义了对象之间的一对多的依赖，这样一来，当一个对象改变状态时，它所有的依赖者都会收到通知并自动更新。

主题/被观察者和观察者之间定义了一对多的关系。观察者依赖于主题/被观察者。一旦主题/被观察者数据发生改变的时候，观察者就会收到通知。那么，如何实现观察者和主题/被观察者呢？

## 2.4 观察者模式实现
由于网络上的实现观察者的方式非常多，我们这里采取比较容易理解的方式Subject和Observer。对于更高级的使用方式，可以百度。
接下来我们来看看基于Subject和Observer的类图结构：
![Subject和Observer的类图结构](https://upload-images.jianshu.io/upload_images/2326194-56991af10d00cf84.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
# 3. 设计气象站
到这里我们再回到当初的问题，气象站中结构模型为一对多模型，其中WeatherData为气象模型中的“一”，而“多”也就对应了这里用来展示天气监测数据的各种布告板。相对于之前的针对实现的方式，使用观察者模式来设计会更加符合需求。优先我们给出新的气象站模型。
![气象站数据模型](https://upload-images.jianshu.io/upload_images/2326194-15ecd8c75bcaaf27.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 3.1实现气象站

依照前面的设计结构图，最终来实现具体代码结构

1.Subject
```
public interface Subject {

    //注册观察者
    public void registerObserver(Observer o);
    
    //删除观察者
    public void removeObserver(Observer o);
    
    //当主题发生数据变化时，通知所有观察
    public void notifyObservers();
    
}
```
2.Observer
```
public interface Observer {

   /**
    * 
    * update:当气象站的观测数据发生改变时，这个方法会被调用
    * @param temp 温度
    * @param hunmidity 湿度
    * @param pressure  气压
    * @since JDK 1.6
    */
    public void update(float temp,float hunmidity,float pressure);
}
```
3.DisplayElement
```
public interface DisplayElement {
    //当布告板需要展示时，调用此方法时
    public void display();
}
```
4.新的WeatherData1
```
public class WeatherData1 implements Subject{
    
    private ArrayList<Observer> observers;
    
    private float temperature;
    
    private float humiditty;
    
    private float pressure;
    
    public WeatherData1(){
        observers=new ArrayList<Observer>();
    }

   //注册
    public void registerObserver(Observer o) {
        observers.add(o);
        
    }

    //删除
    public void removeObserver(Observer o) {
       int i=observers.indexOf(o);
       if(i>=0){
           observers.remove(i);
       }
        
    }

    //通知观察者数据变化
    public void notifyObservers() {
        for(int i=0;i<observers.size();i++){
            Observer observer=observers.get(i);
            observer.update(temperature, humiditty, pressure);
        }
        
    }
    
    public void measurementsChanged(){
        notifyObservers();
    }
    
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humiditty=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }

}
```
5.CurrentConditionsDisplay 
```
public class CurrentConditionsDisplay implements Observer,DisplayElement{
    
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;
    
    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData=weatherData;
        weatherData.registerObserver(this);
    }
    
    /**
     * 
     * update:更新布告板内容
     * @author 吉祥
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
     * display:展示布告板内容
     * @author 吉祥
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Current conditons:"+temperature
                +"F degrees and "+humidity+"% humidity");
    }
}
```
6.ForecastDisplay
```
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
     * update:更新布告板内容
     * @author 吉祥
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
     * display:展示布告板内容
     * @author 吉祥
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Forecast: More of the same");
    }

}
```
7.StatisticsDisplay 
public class StatisticsDisplay implements Observer,DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;
    
    public StatisticsDisplay(SubjectweatherData){
        this.weatherData=weatherData;
        weatherData.registerObserver(this);
    }
    
    /**
     * 
     * update:更新布告板内容
     * @author 吉祥
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
     * display:展示布告板内容
     * @author 吉祥
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Avg/Max/Min temperature= "+temperature
                +"/"+temperature+"/"+temperature);
    }

}

ps:这里在Observer中使用Subject原因在于方便以后的取消注册。

最后我们建立一个测试类WeatherStation来进行测试

```
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
```
最终结果如下
![测试结果](https://upload-images.jianshu.io/upload_images/2326194-9b117a3c48769650.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
到这里我们已经讲解完观察者模式的一种实现方式。但是这我们也提出一个问题，用来发散。

>是否能够在主题中提供向外的可以让观察者自己获取自己想要数据，而并非将所有的数据都推送给观察者？也就是在Push（推）的同时我们也可以pull（拉）。

#4.Java内置的观察者模式
刚才的问题，其实熟悉Java语言的开发者会发现，在Java中已经有相应的模式，如果熟悉的可以直接跳过本章。
在java.util包下有Observer和Observable类，这两个类的结构跟我们遇到的Subject和Observer模型有些类似。甚至可是随意使用push（推）或者pull（拉）
这里我们使用在线的Java API网站[在线Java API文档](http://tool.oschina.net/apidocs/apidoc?api=jdk-zh)
首先查询Observer的API
![Observer API文档](https://upload-images.jianshu.io/upload_images/2326194-2e234f1a4e7b9103.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这个与我们所写的Observer结构几乎相同，只是在推送是把Observable类一起推送，这样用户既可以push也可以使用pull的方式。那么Observable的结构呢

![Observable API 介绍](https://upload-images.jianshu.io/upload_images/2326194-44e31b7b47e039db.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Observable API 方法介绍](https://upload-images.jianshu.io/upload_images/2326194-d0e0b8162e9602a9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们发现这里Observable是类与我们之前Subject作为接口的方式稍微有区别；并且Observable类其他方法更全。那么使用类的方式和使用接口的影响我们在后面会继续讲。并且这里我们关注setChanged（）方法告诉被观察者的数据发生改变
那么，如果要使用Java中自带的观察者模式来修改原有气象站业务会如何。

首先，我们来分析更改后气象站的模型：
![Java内置观察者模式 气象站](https://upload-images.jianshu.io/upload_images/2326194-63eb0bcfedb5346d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 4.1Java内置观察者模式运作模式
相对于于之前Subject和Observer的模式，Java内置自带的观察者模式运行稍微有些差异。

* 将对象变成观察者只需要实现Observer（java.util.Observer）接口，然后调用任何Observable的addObserver()方法即可。如果要删除观察者，调用deleteObserver()即可。

* 被观察者若要推送通知，需要对象继承Observable（java.util.Observable）类，并先调用setChanged(),首先标记状态已经改变。然后调用notifyObservers()方法中的一个：notifyObservers()(通知观察者pull数据)或notifyObserers(Object object)(通知观察者push数据)

那么作为观察者如何处理被观察者推送出的数据呢。
这里逻辑如下：

* 观察者（Observer）必须在update（Observable o,Object object）.前一个参数用来让观察者知道是哪个被观察者推送数据。后一个object为推送数据，允许为null。

## 4.2 setChanged()
在Observable类中setChanged()方法一开始我也有疑惑，为何在推送之前需要调用该方法。后来查阅资料和Java API发现它很好的一个用处。我们先来查看java的源码
![Observable类中setChanged（）方法](https://upload-images.jianshu.io/upload_images/2326194-881e913ec9d35d94.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![Observable类中notifyObservers()方法](https://upload-images.jianshu.io/upload_images/2326194-45401c2afb859213.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
这里必须标记为true才会推送消息，那么这个到底有何好处，我们拿气象站模型来分析。
如果没有setChanged方法，也是之前的Subject和Observer模型里，一旦数据发生细微的变化，我们都会对所有的观察者进行推送。如果我们需要在温度变化1摄氏度以上才发送推送，调用setChanged（）方法更加有效。当然，这个功能使用场景很少，但是也不排除会用到。当然更改Object和Observer模型也是可以做到这个效果的！！！


## 4.3 Java内置观察者更改气象站
那么利用气象站模型来实际操作一下，依照之前的模型我们代码应该如下
1.WeatherData2 
```
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
       //这里未使用notifyObserver(object),所以数据采用拉的逻辑
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
```
2.CurrentConditionsDisplay1 
```
public class CurrentConditionsDisplay1 implements Observer,DisplayElement{
    
    private Observable observable;
    
    private float temperature;
    
    private float humidity;
    
    private float pressure;
    
    //构造器需要传入Observable参数，并登记成为观察者
    public CurrentConditionsDisplay1(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }
    
    //update方法增加Observable和数据对象作为参数
    public void update(Observable o, Object arg) {
        if(arg instanceof WeatherData2){
            WeatherData2 weatherData2=(WeatherData2) arg;
            this.temperature=weatherData2.getTemperature();
            this.humidity=weatherData2.getHumidity();
            this.pressure=weatherData2.getPressure();
            display();
        }
        
    }
    
    /**
     * 
     * display:展示布告板内容
     * @author 吉祥
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Current conditons:"+temperature
                +"F degrees and "+humidity+"% humidity");
    }
}
```
3.ForecastDisplay1 
```
public class ForecastDisplay1 implements Observer,DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private Observable observable;
    
    public ForecastDisplay1(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }
    
   
    public void update(Observable o,Object arg){
        if(arg instanceof WeatherData2){
            WeatherData2 weatherData2=(WeatherData2) arg;
            this.temperature=weatherData2.getTemperature();
            this.humidity=weatherData2.getHumidity();
            this.pressure=weatherData2.getPressure();
            display();
        }
    }
        

    /**
     * 
     * display:展示布告板内容
     * @author 吉祥
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Forecast: More of the same");
    }

}
```
4.StatisticsDisplay1 
```
public class StatisticsDisplay1 implements Observer,DisplayElement{

    private float temperature;
    private float humidity;
    private float pressure;
    private Observable observable;
    
    public StatisticsDisplay1(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }
    
   
    public void update(Observable o,Object arg){
        if(arg instanceof WeatherData2){
            WeatherData2 weatherData2=(WeatherData2) arg;
            this.temperature=weatherData2.getTemperature();
            this.humidity=weatherData2.getHumidity();
            this.pressure=weatherData2.getPressure();
            display();
        }
    }
        

    /**
     * 
     * display:展示布告板内容
     * @author 吉祥
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Avg/Max/Min temperature= "+temperature
                +"/"+temperature+"/"+temperature);
    }
}
```
最后进行测试：
WeatherStation1 
```
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
```
结果最终如下：
![Java 内置观察者模式](https://upload-images.jianshu.io/upload_images/2326194-e7a88d119f635a93.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们对比之前Subject和Observer的观察者模式会发现两者输出顺序不一样，这是为什么？

其实java.util.Observable不依赖于观察者被通知的顺序的，并且实现了他的notifyObserver()方法，这会导致通知观察者的顺序不同于Subject和Observer模型在具体类实现notifyObserver()方法。其实两者都没有任何的代码误差，只是实现的方式不同导致不同的结果。

但是java.util.Observable类却违背了之前第一章中针对接口编程，而非针对实现编程。恐怖的是，它也没有接口实现，这就导致它的使用具有很高的局限性和低复用性。如果一个对象不仅仅是被观察者，同时还是另一个超类的子类的时候，我们无法使用多继承的方式来实现。我们如果自行拓展的话，你会发现setChanged()方法是protected方法，这就表示只有java.util.Observable自身和其子类才可以使用这个方法。这就违反了第二个设计原则---------"多用组合，少用继承"。这也是我一般不会使用Java自带的设计者模式的原因。

现在比较流行的观察者模式，也就是RxJava，但是由于这个框架涉及不仅仅有观察这模式，在之后整个设计模式整理玩不后，我会集中再讲。

#5.总结
到此，观察者模式的讲解已经全部讲解完成。总结一下。
>第四设计原则
  为交互对象之间的松耦合涉及而努力

>观察者模式
  在对象之间定义一对多的依赖，这样一来，当一个对象改变状态，依赖它的对象都会收到通知，并自动更新。

相应的资料和代码托管地址[https://github.com/jixiang52002/HFDesignPattern](https://github.com/jixiang52002/HFDesignPattern)
























