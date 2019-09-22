package com.demo.model;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class IntfParams extends Model<IntfParams> {
	public static final IntfParams me = new IntfParams();
	public List<IntfParams> findAll() {
		 return find("select * from intfParams");
	}
}
