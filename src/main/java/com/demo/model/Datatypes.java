package com.demo.model;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class Datatypes extends Model<Datatypes> {
	public static final Datatypes me = new Datatypes();
	public List<Datatypes> findAll() {
		 return find("select * from datatypes");
	}
}
