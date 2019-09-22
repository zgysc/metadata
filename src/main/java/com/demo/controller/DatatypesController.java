package com.demo.controller;
import com.demo.CommonInterceptor;
import com.demo.model.MainTypes;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.demo.model.Datatypes;
import com.jfinal.kit.StrKit;

@Before(CommonInterceptor.class)
public class DatatypesController extends Controller {
	public void index() {
		String dtName=StrKit.notBlank(getPara("mt"))?getPara("mt"):getSessionAttr("dtName");
		if(StrKit.notBlank(dtName)){
			setSessionAttr("dtName",dtName);
			setAttr("DatatypesList",Datatypes.me.paginate(getParaToInt(0,1),20,"select * "," from datatypes where parentName='"+dtName+"'"));
		}else{
			setAttr("DatatypesList",Datatypes.me.paginate(getParaToInt(0,1),20,"select * "," from datatypes"));
		}
		render("Datatypes.html");
	}

	public void add(){
		setAttr("dts",MainTypes.me.findByMainType(2));
		setAttr("objs",MainTypes.me.findByMainType(1));
	}
	public void save() {
		getModel(Datatypes.class).save();
		index();
	}
	public void edit() {
		setAttr("dts",MainTypes.me.findByMainType(2));
		setAttr("objs",MainTypes.me.findByMainType(1));
		setAttr("datatypes",Datatypes.me.findById(getParaToInt()));

	}
	public void update() {
		getModel(Datatypes.class).update();
		index();
	}
	public void delete() {
		Datatypes.me.deleteById(getParaToInt());
		index();
	}
}
