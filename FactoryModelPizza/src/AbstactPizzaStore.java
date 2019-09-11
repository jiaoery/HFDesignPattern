
/**
 * @ClassName: AbstactPizzaStore.java
 * @Description:抽象PizzaStore类
 * 
 * @author  jixiang
 * @version v1.0.0 
 * @Date    2019年9月10日 下午3:24:28 
 */
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
	public abstract Pizza createPizza(String type);

}
