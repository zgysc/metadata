package com.demo.controller;

import com.demo.CommonInterceptor;
import com.demo.model.Project;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(CommonInterceptor.class)
public class ProjectController extends Controller {
    public void index() {
        setAttr("ProjectList",Project.me.paginate(getParaToInt(0,1),20,"select * from project",""));
        render("Project.html");
    }

    public void add(){}
    public void save() {
        Project intf=getModel(Project.class);
        intf.save();
        index();
    }
    public void edit() {
        setAttr("project",Project.me.findById(getParaToInt()));

    }
    public void update() {
        getModel(Project.class).update();
        index();
    }
    public void delete() {
        Project.me.deleteById(getParaToInt());
        redirect("/Project");
    }
}
