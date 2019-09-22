package com.demo.model;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class MainTypes extends Model<MainTypes> {
	public static final MainTypes me = new MainTypes();
	public List<MainTypes> findAll() {
		 return find("select * from mainTypes");
	}

	public List<MainTypes> findByMainType(int type){
		return find("select * from mainTypes where mainType=?",type);
	}
}
