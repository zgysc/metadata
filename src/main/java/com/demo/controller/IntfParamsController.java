package com.demo.controller;
import com.demo.CommonInterceptor;
import com.demo.model.Intf;
import com.demo.model.MainTypes;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.demo.model.IntfParams;
import com.jfinal.kit.StrKit;

@Before(CommonInterceptor.class)
public class IntfParamsController extends Controller {
	public void index() {
		String intfName=StrKit.notBlank(getPara("intf"))?getPara("intf"):getSessionAttr("intfName");
		if(StrKit.notBlank(intfName)){
			setSessionAttr("intfName",intfName);
			setAttr("IntfParamsList",IntfParams.me.paginate(getParaToInt(0,1),20,"select * "," from intfParams where intfName='"+intfName+"'"));
		}else{
			setAttr("IntfParamsList",IntfParams.me.paginate(getParaToInt(0,1),20,"select * "," from intfParams"));
		}
		render("IntfParams.html");
	}

	public void add(){

		setAttr("intfs", Intf.me.findAll());
		setAttr("objs",MainTypes.me.findAll());
	}
	public void save() {
		IntfParams intfParams=getModel(IntfParams.class);
		intfParams.save();
		index();
	}
	public void edit() {
		setAttr("intfs", Intf.me.findAll());
		setAttr("objs",MainTypes.me.findAll());
		setAttr("intfParams",IntfParams.me.findById(getParaToInt()));

	}
	public void update() {
		getModel(IntfParams.class).update();
		index();
	}
	public void delete() {
		IntfParams.me.deleteById(getParaToInt());
		index();
	}
}
