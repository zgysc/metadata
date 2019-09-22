package com.demo.controller;
import com.demo.CommonInterceptor;
import com.demo.model.Intf;
import com.demo.model.IntfParams;
import com.demo.model.MainTypes;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.demo.model.Objclasses;
import com.jfinal.kit.StrKit;

@Before(CommonInterceptor.class)
public class ObjclassesController extends Controller {
	public void index() {
		String objName=StrKit.notBlank(getPara("mt"))?getPara("mt"):getSessionAttr("objName");
		if(StrKit.notBlank(objName)){
			setSessionAttr("objName",objName);
			setAttr("ObjclassesList",Objclasses.me.paginate(getParaToInt(0,1),20,"select * "," from objclasses where parentName='"+objName+"'"));
		}else{
			setAttr("ObjclassesList",Objclasses.me.paginate(getParaToInt(0,1),20,"select * "," from objclasses"));
		}
		render("Objclasses.html");
	}

	public void add(){
		setAttr("mts", MainTypes.me.findByMainType(3));
		setAttr("objs",MainTypes.me.findAll());
	}
	public void save() {
		getModel(Objclasses.class).save();
		index();
	}
	public void edit() {
		setAttr("mts", MainTypes.me.findByMainType(3));
		setAttr("objs",MainTypes.me.findAll());
		setAttr("objclasses",Objclasses.me.findById(getParaToInt()));

	}
	public void update() {
		getModel(Objclasses.class).update();
		index();
	}
	public void delete() {
		Objclasses.me.deleteById(getParaToInt());
		index();
	}
}
