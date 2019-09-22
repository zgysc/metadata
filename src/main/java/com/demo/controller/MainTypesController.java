package com.demo.controller;
import com.demo.CommonInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.demo.model.MainTypes;

@Before(CommonInterceptor.class)
public class MainTypesController extends Controller {
	public void index() {
		setAttr("MainTypesList",MainTypes.me.paginate(getParaToInt(0,1),20,"select * from mainTypes",""));
		render("MainTypes.html");
	}

	public void add(){}
	public void save() {
		getModel(MainTypes.class).save();
		index();
	}
	public void edit() {
		setAttr("mainTypes",MainTypes.me.findById(getParaToInt()));

	}
	public void update() {
		getModel(MainTypes.class).update();
		index();
	}
	public void delete() {
		MainTypes.me.deleteById(getParaToInt());
		index();
	}
}
