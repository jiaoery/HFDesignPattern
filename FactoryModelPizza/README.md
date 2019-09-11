# Head First 设计模式（4）----- 工厂方法模式

本文参照《Head First 设计模式》，转载请注明出处
对于整个系列，我们按照这本书的设计逻辑，使用情景分析的方式来描述，并且穿插使用一些问题，总结的方式来讲述。并且所有的开发源码，都会托管到github上。
项目地址：https://github.com/jixiang52002/HFDesignPattern
![](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388bcc63e8c?w=598&h=393&f=png&s=288265)


回顾上一篇文章讲解了设计模式中常用的一种模式------装饰者模式。并结合星巴兹咖啡设计进行实战解析，并且从自己设计到JAVA自带设计模式做了讲解。想要了解的朋友可以回去回看一下。

本章将着重于在开发中最常遇到的初始化的问题，让你不会在new一个新的对象中感觉头疼。虽然本章取名是将工厂模式，但是这里会讲解两个设计模式，分别为工厂模式和抽象工厂设计模式。本章对于JAVA中抽象会大量运用到，未了解这方面的知识的朋友可以去查阅相关的资料。

# 1.前言
作为一个合格的JAVA开发程序员，我们知道要实例化一个类为对象，我们会利用类中的构造函数，使用new这个关键字。比如：
```
Duck duck=new MallardDuck();
```
这里使用接口的方式为的使代码具备弹性，但是如果我们需要根据属性值去赋值，比如下面这样的表达
```
        Duck duck;
		if(picnic) {
			duck=new MallardDuck();
		}else if (hunting) {
			duck=new DecoyDuck();
		}else if (inBathTub) {
			duck=new RubberDuck();
		}
```
这里对于Duck有一系列的实现类。但是具体使用哪一个类，却还需要通过属性条件来决定。
到这里，我们就可以提出问题：如果这部分属性发生很大的变化，甚至被取代掉了，是否我们对于这样受到影响的代码不都要做相关的操作？那这不就是违背了前面提到的一个概念**解耦性**。这段代码的耦合度就非常高。

那么问题来了：使用“new”到底有什么不对劲？（new大法简单粗暴啊，小姐姐最爱啊）

回答：前面提到一个概念“设计应该对拓展开放，对修改关闭”，new大法本身没有错误，毕竟JAVA最基础的组成架构。问题就是，这里的方式没做到拓展，也没有做到对修改闭环。

在开始说优化的方式之前，我们先来认识一下拓展和修改。
到这里，Duck将退出舞台
![image](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388bd0405ae?w=440&h=346&f=jpeg&s=9222)
接下来，将由PIZZA上台
![image](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388bd97153c?w=610&h=420&f=jpeg&s=38171)
爱吃的吃货都不会陌生，生产一份披萨需要经过以下几个步骤：
* 准备（prepare）
* 烤制  （bake）
* 切片 （cut）
* 装盘 （box）
用代码来表示应该是如下步骤：
```
public Pizza orderPizza() {
		Pizza pizza=new Pizza();

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}
```
但是我们知道PIZZA有很多种类，比如榴莲馅，奥尔良馅，芝士馅等，那为了能够适应这个需求，我们对于源代码需要更改：
```
public Pizza orderPizza(String type) {
		Pizza pizza = null;
		if(type.equals("cheese")) {
			pizza=new CheesePizza();
		}else if (type.equals("greek")) {
			pizza=new GreekPizza();
		}else if (type.equals("pepperoni")) {
			pizza=new PepperoniPizza();
		}
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}
```
到这里就是熟悉的配方，熟悉的new大法，如果只有两三个类型都还好，但是如果有上万个类型选项呢，甚至可能由于CheesePizza卖的不是很好，我们需要将其去掉呢？是不是想要拓展或者修改，对于原有业务的修改会非常惊人。这时候，封装上马了。

# 2.封装
从前面分析，我们清楚需要将创建的代码从原有的业务中抽离出来。
首先，分析一下，其中准备到切片的过程是不变的（固定模块），是类型type不同会导致结果不同（拓展/修改模块）。所以我们将type初始化部分提取出来单独作为一个模块。
```
public class SimplePizzaFactory {
       /**
	 * 所有的客户使用该方法来实例化对象
	 * @param type 类型
	 * @return PIZZA
	 */
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		if(type.equals("cheese")) {
			pizza=new CheesePizza();
		}else if (type.equals("greek")) {
			pizza=new GreekPizza();
		}else if (type.equals("pepperoni")) {
			pizza=new PepperoniPizza();
		}
		return pizza;
	}
}
```
这个用以实例化对象的类，我们称之为“工厂（factory）”
>question：这不就是把问题从一个地方移动到另一个地方了吗，问题依然存在。
>answer:虽然是移动了，但是SimplePizzaFactory 可不仅仅只能为oderPizza提供对象初始化服务，还可以为其他对象服务。这里做一个比喻：一个业务部门（阿里支付部门）从公司分出成为分公司（蚂蚁金服），它既可以为原来的业务服务（淘宝,天猫），也可以为新的业务服务（移动支付，企业服务）。

>question:将工厂方法定义为静态方法有什么好处和坏处？
>answer:利用静态方法定义一个简单的工厂模式，最为常见（不一定是Factory类），常称为静态工厂。
>    好处：不需要实例化对象，不占据多少内存
>    坏处：无法通过继承来改变内部的实现方法

按照简单工厂我们可以修改自己的代码
```
public class PizzaStore {

	
	SimplePizzaFactory factory;
	
	public PizzaStore(SimplePizzaFactory factory) {
		this.factory=factory;
	}
	
	/**
	 * 根据类型生成PIZZA
	 * @param type
	 * @return PIZZA
	 */
	public Pizza orderPizza(String type) {
		Pizza pizza;
		//orderPizza通过传入type类型，使用工厂完成创建
		pizza=factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}


}
```
# 3.简单工厂（非设计模式）
简单工厂其实严格意义上不是设计模式，而是一种编程习惯，还是最常用的那种。。。。（哎，你别动手）
![image](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388c44422cf?w=450&h=338&f=jpeg&s=13562)
（屈服于大佬的淫威）但是呢，并不能说它不是一种设计模式就不重视它，从前面的PIZZA类图分析：

![PIZZA类图](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388c475a628?w=1240&h=655&f=png&s=123526)

其实这时候，我们已经接近了设计模式。所以接下来，就是最重要的设计模式。

# 4.新业务需求：加盟店模式

由于经营合理加上口味独特，PIZZA店终于大获成功。现在所有的消费者和餐饮投资人都希望在自己的附近能够有一家加盟店。作为经营者，你可以按照以下两种模式来选择加盟方式：
* 所有加盟店使用与总店一起使用相同的配方（直营店模式）
* 每家加盟店可以根据当地风味的不同，自己更改其中的配方和风味（加盟商模式）
从现在做的比较大的必胜客，肯德基，麦当劳的经营模式来看，后者会让经营模式更加灵活。但是前者可以保证加盟店的产品质量和口碑，比如京东线下店，苏宁易购等。所以我们来看看不同的加盟模式，会对原有的业务需求造成什么影响。
## 4.1 直营店模式
首先是设计结构，PizzaStore
![直营店模式](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388d8994f06?w=1240&h=565&f=png&s=102649)
所以这里我们需要拓展SimplePizzaFactory，这里需要类似于NYPizzaFactory、ChicagoPizzaFactory、ChinaPizzaFactory（甚至可以再细分)，那么准备一份Pizza的流程就需要更改
```
NYPizzaFactory factory=new NYPizzaFactory();
PizzaStore nyStore=new PizzaStore();
nyStore.oderPizza("Veggie");
```
相对来说就非常简单。那么如果是加盟商模式呢
# 4.2 加盟商模式
在某些加盟商里有一些经验很丰富的厨师，他们在做Pizza的时候，会加入自己的一些思路和想法，比如：
过量的芝士，本地风味的榴莲，甚至可能存在双拼的情况。但是我们知道，我们在一开始是将这些操作放在SimplePizzaFactory里，这就使得代码结构不具备活性。就是前面提到的，拓展和修改方面不满足需求。
那么，如何修改现有的结构可以满足需求呢？

按照前面的经验：尽量拓展，少修改。我们发现有区别的地方在于各地的PizzaStore不同，那么是否可以在PizzaStore这里做拓展呢。

这里可以尝试把createPizza放回PizzaStore类里面，但是不需要具体方法体，使用抽象类和抽象方法。让具体的PizzaStore去实现具体的createPizza方法。看一下修改后的效果：
```
public abstract class AbstactPizzaStore {
	
	/**
	 * 根据类型生成PIZZA
	 * @param type
	 * @return PIZZA
	 */
	public Pizza orderPizza(String type) {
		Pizza pizza;
		//orderPizza通过传入type类型，使用工厂完成创建
		pizza=createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}
	
	/**
	 * 工厂对象功能移到这里
	 * @param type 
	 * @return PIZZA
	 */
	abstract Pizza createPizza(String type);

}
```
这里有了超类AbstractPizzaStore，就可以实现NYPizzaStore、ChicagoPizzaStore、ChinaPizzaStore等。
这里这些子类就具备决定权。
```
public class NYPizzaStore extends AbstactPizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		if(type.equals("cheese")) {
			pizza=new NYStyleCheesePizza();
		}else if (type.equals("greek")) {
			pizza=new NYStyleGreekPizza();
		}else if (type.equals("pepperoni")) {
			pizza=new NYStylePepperoniPizza();
		}
		return pizza;
	}

}
```

分析：为何这样做会更好，好处就在于，PizzaStore的oderPizza不需要关注createPizza的Pizza是怎么来的。它只知道createPizza返回的Pizza可以做后续操作。这种对于其他业务有哪些类参与进来的方法，我们称为**解耦**。
而这个抽象方法我们称为**工厂方法**，就是实现了工厂类效果的抽象方法。

```
	 /* 工厂对象功能移到这里
	 * @param type 
	 * @return PIZZA
	 */
	abstract Pizza createPizza(String type);
```
工厂方法必须具备以下几个条件：
* 工厂方法是抽象的，依赖子类来处理具体逻辑
* 工厂方法必须返回一个产品对象，通常定义在返回值（也可以使用回调）
* 工厂方法需要将修改部分从稳定部分中抽离出来。
* 工厂方法中必须有创建者类（稳定部分）和产品类（修改部分）

![创建者类](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388fd4fc1ce?w=1240&h=671&f=png&s=94354)

![产品类](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388ebe51114?w=1240&h=450&f=png&s=58298)

到这里，就完成了加盟店的设计。

# 5.工厂方法模式
从前面我们认识了工厂方法，那么到这里就可以推出我们第一个工厂模式------工厂方法模式。
>**工厂方法模式**定义了一个创建对象的接口，但是由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。

抽象结构如下：
![工厂模式抽象结构](https://user-gold-cdn.xitu.io/2019/9/10/16d1a388f30009bb?w=1240&h=645&f=png&s=90427)