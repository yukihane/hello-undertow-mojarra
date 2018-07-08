package hello;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="hello")
public class Hello {
    final String world = "Hello World!";
    
    public Hello() {
    	System.out.println("const");
    }

    public String getWorld() {
        return world;
    }

}
