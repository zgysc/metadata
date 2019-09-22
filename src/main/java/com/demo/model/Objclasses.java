package com.demo.model;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class Objclasses extends Model<Objclasses> {
	public static final Objclasses me = new Objclasses();
	public List<Objclasses> findAll() {
		 return find("select * from objclasses");
	}
}
