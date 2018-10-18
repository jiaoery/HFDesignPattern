# Head First 设计模式(1)-----策略模式

本文参照《Head First 设计模式》，转载请注明出处
对于整个系列，我们按照这本书的设计逻辑，使用情景分析的方式来描述，并且穿插使用一些问题，总结的方式来讲述。并且所有的开发源码，都会托管到github上。
项目地址：https://github.com/jixiang52002/StrategyModel
原文地址：https://www.jianshu.com/p/ea1631931893
# 引文
Joe的公司是做模拟鸭子活动的游戏而出名，这款游戏取名为SimUDuck，这款游戏具有非常多的鸭子，一边游泳一边呱呱叫。这里的设计采用了标准的OO（Object Oriented，面向对象）的方式编写，这里有一个鸭子的超类（SuperClass），后续所有的鸭子都必须继承这个超类。

* OO面向对象模型

![鸭子OO模型](https://upload-images.jianshu.io/upload_images/2326194-9b9a85a89f8a85e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 继承

这时，公司的高层们想要通过模拟会飞的鸭子来追求行业的领先。然后Joe的项目经理拍着胸脯告诉主管，Joe很快就可以搞定，“有了OO什么都不怕”
Joe接收到任务后，想出了一个办法：“我仅需要在Duck这个超类中加上Fly()的方法，然后所有的鸭子都可以飞了”。然后他的设计模型就改成以下的样子
![增加fly方法后的模型](https://upload-images.jianshu.io/upload_images/2326194-edb552aa20035aea.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
这样看起来貌似没有什么问题的，然后可怕的问题发生了。。。。
用户反馈，自己的橡皮鸭和木鸭居然也可以飞起来！！！
那么，到底是为何导致了这个可怕的问题？
我们来分析一下：由于Joe在Duck超类中加上fly方法，导致所有的子类都会继承该方法，这就导致了原本不会飞的橡皮鸭和木鸭也具有飞行能力，显然为了提高复用性使用的继承方式，并未达到完美得结果。

* 继承+覆盖

Joe在思考后，又得出一个方案，那就是在橡皮鸭中将fly方法覆盖掉，不做任何操作，这样原有的RubberDuck类中架构就变成如下：
![RubberDuck](https://upload-images.jianshu.io/upload_images/2326194-1aa286c1c706de54.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
然后，业务的需求又需要加入木鸭（DecoyDuck），它不会叫也不会飞。苦逼的Joe又要把木鸭（DecoyDuck)中quark方法覆盖，这样DecoyDuck中的类结构就变成如下：
![DecoyDuck](https://upload-images.jianshu.io/upload_images/2326194-7646c38ce3b2a8e9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

但是我们会发觉，如果以后有新的业务，甚至于fly方法中出现一个bug，或者需要删除fly相关的业务，所有相关代码都需要修改，在大型项目中，这都导致非常可怕的维护问题。那么，到底该怎么办


* 接口

“这不就相当于让我根据用户手机壳颜色换主题吗？”，可怜的Joe在快被想要冲上去打产品经理的时候（皮一下。。。）。脑子突然想起一件神器，他决定试试。他的方法就是使用接口（Interface），将fly分离出来，放进一个Flyable接口中。针对于quark方法，也可以这样分离进Quarkable接口中，
![接口](https://upload-images.jianshu.io/upload_images/2326194-ed9998418998aa90.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


虽然这样看起来满足了我们之前所提到的所有需求，但是这样一来重复代码量会非常可怕，如果有上万个Duck子类，Joe一定会发疯的。所有，这个方法虽然看起来很好，但是一旦某个方法或者行为发生改变，我们需要定位到所有实现该方法的类中去修改对应的代码，这很容易导致bug的发生。

那么，到底有没有一种能够建立软件的有效方法，能够让我们可以对既有的项目在影响最小的情况下修改他的业务逻辑，这样我们能够花很少的时间去修改代码。

#2.策略模式
>第一设计原则
  找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混合在一起。

按照以上的原则进行设计，代码发生变化引起的后果会非常小，整个项目会特别具有弹性。
这个原则不仅仅适用于策略模式，对于之后讲解的模式同样也是核心的指导方向。那么我们继续Joe所遇到的Duck问题。

## 2.1分开变化的和不会变化的
我们很清楚，Duck类内部的fly()和quark()伴随着鸭子的不同会发生改变，其他模块是不变的。为了要把这两个变化的行为从Duck类中分开，我们把它们从Duck类中抽离出来，建议一组新类用来代表每一个行为。
![Duck可变不可变分析](https://upload-images.jianshu.io/upload_images/2326194-bb290feee532ede5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

那么，问题来了。如何设计那组实现飞行行为和呱呱叫行为的一组类呢？
这里就需要提及第二设计原则
>第二设计原则
  针对于接口编程，不针对实现编程

这里我们希望的是在创建具体的Duck类的时候，可以动态的生成对应的行为。打个比方，我们想要产生一个新的绿头鸭，将制定类型的飞行行为赋予给它。这就说明，在Duck类中，我们需要包含定义Duck行为的方法，这样在运行的时候，我们就可以动态去改变绿头鸭的行为。
所以这里我们使用两个接口来代表两个行为，这里定义为 FlyBehavior和QuarkBehavior，行为的每次实现，都将实现对应的接口。

但是接口类是没有方法体的，也就是说，我们需要一组类实现对应的行为，这些专用来实现类似FlyBehavior和QuarkBehavior的一组类，我们称为**行为类**。

这里提到的接口类并非严格意义上Java中的接口（Interface），可以理解为抽象类或接口。这里我们可以理解为：
>"针对接口编程"真正的意思是“针对超类型（supertype）编程”

这里讲得有点难以理解，我们对比一下针对实现编程和针对接口编程的区别：
```
//针对实现编程
Dog dog=new Dog();
dog.bark();

//针对接口编程
Animal animal=new Dog();
animal.makeSoud();
```
# 2.2实现鸭子的行为（代码）
从上面的讲解，我们可以理解可变部分是fly和quack两种方法。不可变为Duck类。那么这里我们需要使用两个接口FlyBehavior和QuackBehavior，还有一些列他们对应的行为类，具体的结构逻辑如下图：
![鸭子行为](https://upload-images.jianshu.io/upload_images/2326194-9678ca24253814c3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这设计有两个很明显的优势：
* 可以让飞行和呱呱的叫的动作可以被其他对象复用，因为这些行为已经与鸭子类无关。也就是**解耦**

* 我们能够在不影响原有的行为类的情况下新增一些行为。也就是具备了**弹性**和**可拓展性**

拓展几个概念：
>**耦合**指的就是两个类之间的联系的紧密程度
  **解耦**指的是解除类之间的直接关系，将直接关系转换成间接关系
想要了解的可以参考这篇文章：https://blog.csdn.net/qq_24499615/article/details/77821896
接下来分别将FlyBehavior,FlyWithWings,FlyNoWay分别贴下
```
public interface FlyBehavior {

    //飞行
    public void fly();
}
```

```
public class FlyWithWings implements FlyBehavior{

    public void fly() {
      System.out.println("I am flying!");  
    }

}
```
```
public class FlyNoWay implements FlyBehavior {

    public void fly() {
       System.out.println("I can't fly!"); 

    }

}
```
接下来将QuackBehavior，Quack，MuteQuack，Squeak类的代码分别贴下：
```
public interface QuackBehavior {
    //呱呱叫
    public void quack();
}
```
```
public class Quack implements QuackBehavior {

    public void quack() {
       System.out.println("Quack");

    }

}
```

```
public class MuteQuack implements QuackBehavior {

    public void quack() {
        System.out.println("<< Slience>>");

    }

}
```

```
public class Squeak implements QuackBehavior {

    public void quack() {
       System.out.println("Squeak");
    }

}
```
到这里将fly和quack的接口类和行为类完成。

#2.3组合鸭子行为
在前面2.2我们将飞行（fly）和呱呱叫（quack）的动作"委托"（delegate）给其他接口类处理，而并非在Duck类（或者子类）中定于fly和quack方法。那么到底该怎么把行为组合进Duck中？
* 1.首先在Duck类中增加两个“实例变量”，分别为flyBehavior和quackBehavior，声明为接口类型（注意不是具体类的实现类型），每个Duck（或其子类）会动态的设置这些变量以在运行时引用正确的行为类型（如FlyWithWings，Squeak等）。Duck类的类结构如下
![Duck类结构](https://upload-images.jianshu.io/upload_images/2326194-3677614f0bd46ade.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 2.那么，就开始实现Duck类
```
public abstract class Duck {
    //为行为接口类型声明两个引用变量，所有的鸭子（或子类）都继承它们
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    
    public Duck(){
        
    }
    
    public abstract void display();
    
    public void performQuck(){
        //委托给行为处理
        quackBehavior.quack();
    }
    
    public void performFly(){
      //委托给行为处理
        flyBehavior.fly();
    }
    
    public void swim(){
        System.out.println("All ducks float");
    }
}
```
然后我们实现一个MallardDuck类来实现组合，
```
public class MallardDuck extends Duck{
    
    public MallardDuck(){
        //使用FlyWithWings作为其FlyBehavior类型
        flyBehavior=new FlyWithWings();
        //绿头鸭使用Quck类处理呱呱叫，
        //所以当performQuack被调用时，叫这个行为被委托给Quck对象
        quackBehavior=new Quack();
    }
    
    /*
     * 因为MallardDuck继承自Duck类
     * ，所以具备flyBehavior与quackBehavior实例变量
     */

    public void display() {
        // TODO Auto-generated method stub
        
    }
}
```
当然构造器内还是需要实现具体行为类，这在之后的模式中会提供相应的解决方案，之后我们会回归到这个问题继续解决这个问题。

到这里，组合鸭子类已经实现。

* 3.测试效果
这里我们编译测试类
```
public class MiniDuckSimilator {
    
    public static void main(String[] args) {
        Duck mallerdDuck=new MallardDuck();
        //一下代码是将具体的行为委托给对应的行为类处理行为
        mallerdDuck.performQuck();
        mallerdDuck.performFly();
                
    }

}
```
![运行结果](https://upload-images.jianshu.io/upload_images/2326194-d4b68c3aeb1df28f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#2.4 动态行为设定
在之前的实现中我们是在Duck的具体子类中实现FlyBehavior和QuackBehavior的行为，但是Duck失去了动态设定的功能，对于追求完美的程序员来说是不可饶恕的。所以急切需要通过一个方法动态设定行为，而并非是在鸭子（Duck）的构造器中去实例化。这里推荐一个方法-----**设定方法（setter method）**

* 1. 在Duck类中增加两个新方法 setFlyBehavior()和setQuckBehavior().对于Duck的类结构修改如下
![image.png](https://upload-images.jianshu.io/upload_images/2326194-7d5c581118a4372b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
具体修改如下
```
public abstract class Duck {
    //为行为接口类型声明两个引用变量，所有的鸭子（或子类）都继承它们
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    
    public Duck(){
        
    }
    
    public abstract void display();
    
    public void performQuck(){
        //委托给行为处理
        quackBehavior.quack();
    }
    
    public void performFly(){
      //委托给行为处理
        flyBehavior.fly();
    }
    
    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior=flyBehavior;
    }
    
    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior=quackBehavior;
    }
    
    public void swim(){
        System.out.println("All ducks float");
    }
}
```
* 2.创建一个新的鸭子模型：模型鸭（ModelDuck）
```
public class ModelDuck extends Duck{
    
    public ModelDuck(){
        flyBehavior=new FlyNoWay();
        quackBehavior=new Quack();
    }

    public void display() {
       System.out.println("I'm a model duck");
        
    }

}
```
* 3.新建立一个新的FlyBehavior类型 FlyRocketPowered

```
public class FlyRocketPowered implements FlyBehavior{

    public void fly() {
      System.out.println("I'm flying with a rocket!");
        
    }

}
```
* 4.修改测试类MiniDuckSimulator，加上模型鸭，并令模型鸭具备火箭动力
```
public class MiniDuckSimilator {
    
    public static void main(String[] args) {
        Duck mallerdDuck=new MallardDuck();
        //一下代码是将具体的行为委托给对应的行为类处理行为
        mallerdDuck.performQuck();
        mallerdDuck.performFly();
        
        Duck modelDuck=new ModelDuck();
        //第一次会使用构造参数里的飞行模式
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        //模型鸭具备火箭飞行能力
        modelDuck.performFly();
                
    }

}
```

运行结果：
![运行结果](https://upload-images.jianshu.io/upload_images/2326194-9c0d5985ffd0655a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


到这里我们发现鸭子模型中我们使用到类的组合使用，而这里我们涉及到第三个设计原则：
>第三个设计原则
  多用组合，少用继承

正如我们所见，组合所建立的系统具备极大的弹性，不仅仅可以将行为封装为一系列的行为类，更可以动态改变行为，只需要组合的行为对象是符合正确的行为接口标准的。

# 3.策略模式讲解

总结之前的三个设计原则：
>第一设计原则
找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混合在一起。

>第二设计原则
针对于接口编程，不针对实现编程

>第三设计原则
多用组合，少用继承

总结这三条原则结合起来就是我们学习的第一个模式：
>**策略模式** 定义了算法族，分别封装起来，让它们之间可以相互替换，此模式让算法的变化独立于使用算法的客户。
