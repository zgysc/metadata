package com.demo.controller;
import com.demo.CommonInterceptor;
import com.demo.model.MainTypes;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.demo.model.Enums;
import com.jfinal.kit.StrKit;

@Before(CommonInterceptor.class)
public class EnumsController extends Controller {
	public void index() {
		String enumName=StrKit.notBlank(getPara("mt"))?getPara("mt"):getSessionAttr("enumName");
		if(StrKit.notBlank(enumName)){
			setSessionAttr("enumName",enumName);
			setAttr("EnumsList",Enums.me.paginate(getParaToInt(0,1),20,"select * "," from enums where enumName='"+enumName+"'"));
		}else{
			setAttr("EnumsList",Enums.me.paginate(getParaToInt(0,1),20,"select * "," from enums"));
		}
		render("Enums.html");
	}

	public void add(){
		setAttr("objs",MainTypes.me.findByMainType(1));
	}
	public void save() {
		Enums e=getModel(Enums.class);
		if(e.getStr("paramValue").contains(",")){
			String[] values=e.getStr("paramValue").split(",");
			for(String v:values){
				Enums.me.newEnumValue(e.getStr("enumName"),v);
			}
		}else{
			e.save();
		}

		index();
	}
	public void edit() {
		setAttr("objs",MainTypes.me.findByMainType(1));
		setAttr("enums",Enums.me.findById(getParaToInt()));

	}
	public void update() {
		getModel(Enums.class).update();
		index();
	}
	public void delete() {
		Enums.me.deleteById(getParaToInt());
		index();
	}
}
