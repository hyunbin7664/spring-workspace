package demo;

import java.io.Serializable;

public class Role implements Serializable {

	private String name;
	private boolean isEnable;
	
	public Role() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	
	
}
