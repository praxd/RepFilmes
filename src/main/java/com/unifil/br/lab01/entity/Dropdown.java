package com.unifil.br.lab01.entity;

public class Dropdown {
	private Long id;
	private String name;
	private String selected;
	
	public Dropdown(Long id, String name, String selected) {
		this.id=id;
		this.name = name;
		this.selected = selected;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	
}
