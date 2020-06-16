
/**
 * @ClassName: PizzaStore.java
 * @Description:TODO(用一句话描述该文件做什么) 
 * 
 * @author  jixiang
 * @version v1.0.0 
 * @Date    2019年9月5日 下午9:40:33 
 */
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
