package com.demo.model;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class Intf extends Model<Intf> {
	public static final Intf me = new Intf();
	public List<Intf> findAll() {
		 return find("select * from intf");
	}
}
