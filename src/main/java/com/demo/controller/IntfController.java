package com.demo.controller;
import com.demo.CommonInterceptor;
import com.demo.model.Project;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.demo.model.Intf;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;

@Before(CommonInterceptor.class)
public class IntfController extends Controller {
	public void index() {
		int projectId=getSessionAttr("projectId",0);
		set("projects",Project.me.findAll());
		set("IntfList",Intf.me.paginate(getParaToInt(0,1), PropKit.getInt("pageSize"),"select *"," from intf where project="+projectId));
		set("projectId",projectId);
		render("Intf.html");
	}

	public void add(){}
	public void save() {
		Intf intf=getModel(Intf.class);
		intf.set("project",getSessionAttr("projectId"));
		intf.save();
		index();
	}
	public void edit() {
		setAttr("intf",Intf.me.findById(getParaToInt()));

	}
	public void update() {
		getModel(Intf.class).update();
		index();
	}
	public void delete() {
		Intf.me.deleteById(getParaToInt());
		index();
	}


}
