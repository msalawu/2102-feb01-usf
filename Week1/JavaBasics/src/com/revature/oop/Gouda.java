package com.revature.oop;

// POJO: plain old java object
// Bean: a fully encapsulated class with a no-args constructor
public class Gouda {
	private String color;
	private Integer size;
	private String name;
	
	public Gouda() {
		color = "yellow";
		size = 2;
		name = "Gouda";
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String color) {
		if (color != "blue")
			this.color = color;
		else
			System.out.println("no! no blue");
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		if (size > 0)
			this.size = size;
		else
			System.out.println("there has to be cheese");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gouda other = (Gouda) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gouda [color=" + color + ", size=" + size + ", name=" + name + "]";
	}
	
	
}
