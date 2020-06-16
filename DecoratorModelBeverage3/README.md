本文参照《Head First 设计模式》，转载请注明出处
对于整个系列，我们按照这本书的设计逻辑，使用情景分析的方式来描述，并且穿插使用一些问题，总结的方式来讲述。并且所有的开发源码，都会托管到github上。
项目地址：https://github.com/jixiang52002/HFDesignPattern

回顾上一篇文章讲解了设计模式中常用的一种模式------观察者模式。并结合气象站设计进行实战解析，并且从自己设计到JAVA自带设计模式做了讲解。想要了解的朋友可以回去回看一下。

本章我们会继续前面的话题，有关典型的继承滥用问题。这一章会讲解如何使用对象组合的方式，如何在运行时候做装饰类。在熟悉装饰技巧后，我们能够在原本不修改任何底层的代码，却可以给原有对象赋予新的职能。你会说，这不就是“装饰者模式”。没错，接下来就是装饰者模式的ShowTime时间。

# 前言
欢迎来到星巴兹咖啡，该公司是世界上以扩张速度最快而闻名的咖啡连锁店。但是最近这家著名的咖啡公司遇到一个巨大的问题，因为扩展速度太快了，他们准备更新订单系统，以合乎他们的饮料供应需求。

他们本来的设计方式如下：
![Beverage类结构](https://upload-images.jianshu.io/upload_images/2326194-bc6c1196cd174e8a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后客户购买咖啡时，可以要求在其中加入任何调料，例如：奶茶，牛奶，豆浆。星巴兹根据业务需求会计算相应的费用。这就要求订单系统必须考虑到这些调料的部分。

然后我们就看到他们的第一个尝试设计：

![各种饮料的类关系图](https://upload-images.jianshu.io/upload_images/2326194-62802e17429bf8d4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

是不是有一种犯了密集恐惧症的感觉，整完全就是“类爆炸”。
那么我们分析一下，这种设计方式违反了什么设计原则?没错，违反了以下两个原则:
>第二设计原则
针对于接口编程，不针对实现编程

>第三设计原则
多用组合，少用继承

那么我们应该怎么修改这个设计呢？

#利用继承对Beverage类进行改造
首先，我们考虑对基类Beverage类进行修改，我们根据前面“类爆炸”进行分析。主要饮料包含各种调料（牛奶，豆浆，摩卡，奶泡。。。。）。
所以修改后的Beverage类的结构如下：

![Beverage带调料后的实现](https://upload-images.jianshu.io/upload_images/2326194-67bd45aac40a781b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Beverage类具体实现如下：
```
public class Beverage {
    protected String description;//饮料简介
    
    protected boolean milk=false;//是否有牛奶
    
    protected boolean soy=false;//是否有豆浆
    
    protected boolean cocha=false;//是否有摩卡
    
    protected boolean whip=false;//是否有奶泡
    
    protected double milkCost=1.01;//牛奶价格
    
    protected double soyCost=1.03;//豆浆价格
    
    protected double cochaCost=2.23;//摩卡价格
    
    protected double whipCost=0.89;//奶泡价格
    
    
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean hasMilk() {
        return milk;
    }


    public void setMilk(boolean milk) {
        this.milk = milk;
    }


    public boolean hasSoy() {
        return soy;
    }


    public void setSoy(boolean soy) {
        this.soy = soy;
    }


    public boolean hasCocha() {
        return cocha;
    }


    public void setCocha(boolean cocha) {
        this.cocha = cocha;
    }


    public boolean hasWhip() {
        return whip;
    }


    public void setWhip(boolean whip) {
        this.whip = whip;
    }
    
    


    public double getCochaCost() {
        return cochaCost;
    }


    public void setCochaCost(double cochaCost) {
        this.cochaCost = cochaCost;
    }


    public double getWhipCost() {
        return whipCost;
    }


    public void setWhipCost(double whipCost) {
        this.whipCost = whipCost;
    }


   

    public double cost(){
        
        double condiments=0.0;
        if(hasMilk()){//是否需要牛奶
            condiments+=milkCost;
        }
        if(hasSoy()){//是否需要豆浆
            condiments+=soyCost;
        }
        if(hasCocha()){//是否需要摩卡
            condiments+=cochaCost;
        }
        if(hasWhip()){//是否需要奶泡
            condiments+=whipCost;
        }
        return condiments;
    }

}
```
实现其中一个子类DarkRoast：
```
public class DarkRoast extends Beverage{

    public DarkRoast(){
        description="Most Excellent Dark Roast!";
    }
    
    public double cost(){
        return 1.99+super.cost();
    }
}
```
看起来很完美，也能满足现有的业务需求，但是仔细思考一下，真的这样设计不会出错？

回答肯定是会出错。
* 第一，一旦调料的价格发生变化，会导致我们队原有代码进行大改。
* 第二，一旦出现新的调料，我们就需要加上新的方法，并需要改变超类Beverage类中cost（）方法。
* 第三，如果星巴兹咖啡研发新的饮料。对于这些饮料而言，某些调料可能并不合适，但是子类仍然会继承那些本就不合适的方法，例如我就想要一杯水，加奶泡（hasWhip）就不合适。
* 第四，如果用户需要双倍的摩卡咖啡，又应该怎么办呢？

#开放-关闭原则
到这里，我们可以推出最重要的设计原则之一：
>第五设计原则
类应该对拓展开放，对修改关闭。

那么什么是开放，什么又是关闭？**开放**就是允许你使用任何行为来拓展类，如果需求更改（这是无法避免的），就可以进行拓展！**关闭**在于我们花费很多时间完成开发，并且已经测试发布，针对后续更改，我们必须关闭原有代码防止被修改，避免造成已经测试发布的源码产生新的bug。

综合上述说法，我们的目标在于允许类拓展，并且在不修改原有代码的情况下，就可以搭配新的行为。如果能实现这样的目标，带来的好处将相当可观。在于代码会具备弹性来应对需求改变，可以接受增加新的功能用来实现改变的需求。没错，这就是拓展开放，修改关闭。

那么有没有可以参照的实例可以分析呢？有，就在第二篇我们介绍观察者模式时，我们介绍到可以通过增加新的观察者用来拓展主题，并且无需向原主题进行修改。

我们是否需要每个模块都设计成开放--关闭原则？不用，也很难办到（这样的人我们称为“不用设计模式会死病”）。因为想要完全符合开放-关闭原则，会引入大量的抽象层，增加原有代码的复杂度。我们应该区分设计中可能改变的部分和不改变的部分（第一设计原则），针对改变部分使用开放--关闭原则。

# 装饰模式
这里，就到了开放--关闭原则的运用模式-----装饰者模式。首先我们还是从星巴兹咖啡的案例来做一个简单的分析。
 分析之前两个版本（类爆炸和继承大法）的实现方式，并不能适用于所有的子类。

这就需要一个新的设计思路。这里，我们将以饮料为主，然后运行的时候以饮料来“装饰”饮料。举个栗子，如果影虎需要摩卡和奶泡深焙咖啡，那么要做的是：

* 拿一个深焙咖啡（DarkRosat）对象

* 以摩卡（Mocha）对象装饰它

*  以奶泡（Whip）对象装饰它

* 调用cost（）方法，并依赖委托（delegate）将调料的价钱加上去。

具体的实现我们用一张图来展示

* 首先我们构建DarkRoast对象
![DarkRoast对象](https://upload-images.jianshu.io/upload_images/2326194-3176fd16d2f020c7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 假如顾客需要摩卡（Mocha），再建立一个Mocha对象，并用DarkRoast对象包起来。
![Mocha对象](https://upload-images.jianshu.io/upload_images/2326194-c5ceba31b94d40b7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 如果顾客也想要奶泡（Whip）,就建立一个Whip装饰者，并将它用Mocha对象包起来。
![Mocha对象](https://upload-images.jianshu.io/upload_images/2326194-e9c973f1121901de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 最后运算客户的账单的时候，通过最外层的装饰者Whip的cost（）就可以办得到。Whip的cost（）会委托他的装饰对象（Mocha）计算出价格，再加上奶泡（Whip）的价格。

![计算用户的账单](https://upload-images.jianshu.io/upload_images/2326194-759e692492c670f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


通过对星巴兹咖啡的设计方案分析，我们可以发现，所有的装饰类都具备以下几个特点：
* 装饰者和被装饰对象有相同的超类型。

* 你可以用一个或多个装饰者包装一个对象。

*  既然装饰者和被装饰对象有相同的超类型，所以在任何需要原始对象（被包装的）的场合，可以用装饰过的对象代替它。

*  装饰者可以在所委托被装饰者的行为之前与/或之后，加上自己的行为，以达到特定的目的。

*  对象可以在任何时候被装饰，所以可以在运行时动态地、不限量地用你喜欢的装饰者来装饰对象

什么是装饰模式呢？我们首先来看看装饰模式的定义：
>装饰者模式动态地将责任附加到对象上。
若要扩展功能，装饰者提供了比继承更有弹性
的替代方案。

定义虽然已经定义了装饰者模式的“角色”，但是未说明怎么在我们的实现中如何使用它们。我们继续在星巴兹咖啡中来熟悉相关的操作。

![装饰者模式类图](https://upload-images.jianshu.io/upload_images/2326194-197796d008164a40.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其中装饰者层级可以无限发展下去，不是如图中一般两层关系。并且组件也并非只有一个，可以存在多个。

现在我们就在星巴兹咖啡里运用装饰者模式：
![使用装饰模式的星巴兹咖啡](https://upload-images.jianshu.io/upload_images/2326194-c51fa3ca50a8d61b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

到这里，我们队装饰者模式已经有了一个基本的认识。那么我们已经解决了上面提到的四个问题：

* 第一，一旦调料的价格发生变化，会导致我们队原有代码进行大改。
* 第二，一旦出现新的调料，我们就需要加上新的方法，并需要改变超类Beverage类中cost（）方法。
* 第三，如果星巴兹咖啡研发新的饮料。对于这些饮料而言，某些调料可能并不合适，但是子类仍然会继承那些本就不合适的方法，例如我就想要一杯水，加奶泡（hasWhip）就不合适。
* 第四，如果用户需要双倍的摩卡咖啡，又应该怎么办呢？

那么根据第四个问题，假如我们需要双倍摩卡豆浆奶泡拿铁咖啡时，该如何去运算账单呢？首先，我们先把前面的深度烘焙摩卡咖啡的设计图放在这里。
![深度烘焙摩卡咖啡](https://upload-images.jianshu.io/upload_images/2326194-869db9fc579dd24a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后我们只需要将Mocha的装饰者加一，即可
![双倍摩卡豆浆奶泡拿铁咖啡](https://upload-images.jianshu.io/upload_images/2326194-5f9df7c253078652.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 实现星巴兹咖啡代码

前面已经把设计思想都设计出来了，接下来是将其具体实现了。首先从Beverage类下手
```
public abstract class Beverage1 {
    String description="Unknown Beverage";
    
    public String getDescription(){
        return description;
    }
    
    public abstract double cost();
}
```
Beverage类非常简单，然后再实现Condiment（调料类），该类为抽象类，也为装饰者类

```
public abstract class CondimentDecorator extends Beverage1{

    //所有的调料装饰者都必须重新实现 getDescription()方法。 
    public abstract String getDescription();
}
```

前面已经有了饮料的基类，那么我们来实现一些具体的饮料类。首先从浓缩咖啡（Espresso））开始，这里需要重写cost()方法和getDescription（）方法
```
public class Espresso extends Beverage1{
    
    public Espresso(){
        //为了要设置饮料的描述，我 们写了一个构造器。记住， description实例变量继承自Beverage1
        description="Espresso";
    }
    
    public double cost() {
        //最后，需要计算Espresso的价钱，现在不需要管调料的价钱，直接把Espresso的价格$1.99返回即可。
        return 1.99;
    }
}
```

再实现一个类似的饮料HouseBlend类。
```
public class HouseBlend extends Beverage1{

    public HouseBlend(){
        description="HouseBlend";
    }
    
    public double cost() {
      
        return 0.89;
    }
}
```

重新设计DarkRoast1
 ```
public class DarkRoast1 extends Beverage1{
	
	public DarkRoast1(){
        description="DarkRoast1";
    }
    
    public double cost() {
      
        return 0.99;
    }

}
```

接下来就是调料的代码，我们一开始已经实现了抽象组件类（Beverage），有了具体的组件（HouseBlend），也有了已经完成抽象装饰者（CondimentDecorator）。现在只需要实现具体的装饰者。首先我们先完成摩卡（Mocha）
```
public class Mocha extends CondimentDecorator{
	/**
	 * 要让Mocha能够引用一个Beverage，采用以下做法
	 * 1.用一个实例记录饮料，也就是被装饰者
	 * 2.想办法让被装饰者（饮料）被记录在实例变量中。这里的做法是：
	 * 把饮料当作构造器的参数，再由构造器将此饮料记录在实例变量中
	 */
	Beverage1 beverage;
	
	public Mocha(Beverage1 beverage) {
		this.beverage=beverage;
	}
	
	public String getDescription() {
		//这里将调料也体现在相关参数中
		return beverage.getDescription()+",Mocha";
	}
	
	/**
	 * 想要计算带摩卡的饮料的价格，需要调用委托给被装饰者，以计算价格，
	 * 然后加上Mocha的价格，得到最终的结果。
	 */
	public double cost() {
		return 0.21+beverage.cost();
	}
	
	

}
```

还有奶泡（Whip）类
```
public class Whip extends CondimentDecorator{
	/**
	 * 要让Whip能够引用一个Beverage，采用以下做法
	 * 1.用一个实例记录饮料，也就是被装饰者
	 * 2.想办法让被装饰者（饮料）被记录在实例变量中。这里的做法是：
	 * 把饮料当作构造器的参数，再由构造器将此饮料记录在实例变量中
	 */
	Beverage1 beverage;
	
	public Whip(Beverage1 beverage) {
		this.beverage=beverage;
	}
	
	public String getDescription() {
		//这里将调料也体现在相关参数中
		return beverage.getDescription()+",Whip";
	}
	
	/**
	 * 想要计算带奶泡的饮料的价格，需要调用委托给被装饰者，以计算价格，
	 * 然后加上Whip的价格，得到最终的结果。
	 */
	public double cost() {
		return 0.22+beverage.cost();
	}
}
```

豆浆Soy类
```
public class Soy extends CondimentDecorator{
	/**
	 * 要让Soy能够引用一个Beverage，采用以下做法
	 * 1.用一个实例记录饮料，也就是被装饰者
	 * 2.想办法让被装饰者（饮料）被记录在实例变量中。这里的做法是：
	 * 把饮料当作构造器的参数，再由构造器将此饮料记录在实例变量中
	 */
	Beverage1 beverage;
	
	public Soy(Beverage1 beverage) {
		this.beverage=beverage;
	}
	
	public String getDescription() {
		//这里将调料也体现在相关参数中
		return beverage.getDescription()+",Soy";
	}
	
	/**
	 * 想要计算带豆浆的饮料的价格，需要调用委托给被装饰者，以计算价格，
	 * 然后加上Soy的价格，得到最终的结果。
	 */
	public double cost() {
		return 0.21+beverage.cost();
	}
}
```

接下来就是调用测试类，具体实现如下：

```
public class StarbuzzCoffe {
	
	public static void main(String[] args) {
		//订购一杯Espresso，不需要调料，打印他的价格和描述
		Beverage1 beverage=new Espresso();
		System.out.println(beverage.getDescription()+"$"
				+beverage.cost());
		
		//开始装饰双倍摩卡+奶泡咖啡
		Beverage1 beverage2=new DarkRoast1();
		beverage2=new Mocha(beverage2);
		beverage2=new Mocha(beverage2);
		beverage2=new Whip(beverage2);
		
		System.out.println(beverage2.getDescription()+"$"
				+beverage2.cost());
		
		//
		Beverage1 beverage3=new HouseBlend();
		beverage3=new Soy(beverage3);
		beverage3=new Mocha(beverage3);
		beverage3=new Whip(beverage3);
		
		System.out.println(beverage3.getDescription()+"$"
				+beverage3.cost());
		
		
	}
}
```
运行结果：
![运行结果](https://upload-images.jianshu.io/upload_images/2326194-c31c4a0afaa48052.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
到这里，我们已经完成装饰者模式对于星巴兹咖啡的改造。


#Java中的真实装饰者
前面已经研究了装饰者模式的原理和实现方式，那么在JAVA语言本身是否有装饰者模式的使用范例呢，答案是肯定有的，那就是I/O流。

第一次查阅I/O源码，都会觉得类真多,而且一环嵌一环，阅读起来会非常麻烦。但是只要清楚I/O是根据装饰者模式设计，就很容易理解。我们先来看一下一个范例：

![读取文件](https://upload-images.jianshu.io/upload_images/2326194-9ac32711ab32acd4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

分析一下,其中BufferedInputStream及LineNumberInputStream都扩展自
FilterInputStream，而FilterInputStream是一个抽象的装饰类。这样看有些抽象，我们将其中的类按照装饰者模式进行结构化，方便理解。

![java.io类](https://upload-images.jianshu.io/upload_images/2326194-ac4892d3b3ccaaf7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们发现，和星巴兹的设计相比，java.io其实并没有多大的差异。但是从java.io流我们也会发现装饰者模式一个非常严重的"缺点"：使用装饰者模式，常常会造成设计中有大量的小类，数量还非常多，这对于学习API的程序员来说就增加了学习难度和学习成本。但是，懂得装饰者模式以后会非常容易理解和设计相关的类。

# 设计自己的IO类
在理解装饰者模式和java.io的设计后，我们将磨炼下自己的熟悉程度，没错，就是自己设计一个Java I/O装饰者，需求如下：
>编写一个装饰者，把输入流内的所有大写字符转成小写。举例：当读
取“ ASDFGHJKLQWERTYUIOPZXCVBNM”，装饰者会将它转成“ asdghjklqwertyuiopzxcvbnm”。具体的办法在于扩展FilterInputStream类，并覆盖read()方法就行了。


```
public class LowerCaseInputStream extends FilterInputStream{
    
    public LowerCaseInputStream(InputStream inputStream){
        super(inputStream);
    }
    
    
    public int read() throws IOException {
       int c=super.read();
       //判断相关的字符是否为大写，并转为小写
       return (c==-1?c:Character.toLowerCase((char)c));
    }
    
    /**
     * 
     *针对字符数组进行大写转小写操作
     * @see java.io.FilterInputStream#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        int result=super.read(b,off,len);
        for(int i=off;i<off+result;i++){
            b[i]=(byte) Character.toLowerCase((char)b[i]);
        }
        return result;
    }

}
```
接下来我们构建测试类InputTest
```
public class InputTest {
    public static void main(String[] args) {
        int c;
        try {
            InputStream inputStream=new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
            while((c=inputStream.read())>=0){
                System.out.print((char)c);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```
其中test.txt的内容可以自行编辑，放在项目根目录下我的内容原文为：
![test.txt原文](https://upload-images.jianshu.io/upload_images/2326194-a3fcd25e25acd44f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

运行结果为：

![运行结果](https://upload-images.jianshu.io/upload_images/2326194-bdbc47795d141ed8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#5.总结
至此，我们已经掌握了装饰者模式的相关知识点。总结一下：
>第五设计原则
类应该对拓展开放，对修改关闭。

>装饰者模式动态地将责任附加到对象上。
若要扩展功能，装饰者提供了比继承更有弹性
的替代方案。

相应的资料和代码托管地址[https://github.com/jixiang52002/HFDesignPattern](https://github.com/jixiang52002/HFDesignPattern)










