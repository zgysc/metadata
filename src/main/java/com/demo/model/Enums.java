package com.demo.model;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class Enums extends Model<Enums> {
	public static final Enums me = new Enums();
	public List<Enums> findAll() {
		 return find("select * from enums");
	}

	public void newEnumValue(String name,String v){
		Enums e=new Enums();
		e.set("enumName",name);
		e.set("paramValue",v);
		e.save();
	}
}
