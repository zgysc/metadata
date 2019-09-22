package com.demo.controller;

import com.demo.model.Intf;
import com.demo.model.IntfParams;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		redirect("/Intf");
	}

	public void changeProject(){
		setSessionAttr("projectId",getInt(0));
		renderJson("result","ok");
	}
	public void outputMD() {
		List<Intf> intfs = Intf.me.findAll();
		for (Intf intf : intfs) {
			System.out.println(String.format("##%s. %s", intf.getInt("seq"), intf.getStr("name")));
			System.out.println();
			System.out.println("**接口名称**: " + intf.getStr("intfName"));
			System.out.println("");
			System.out.println("**接口描述**: " + intf.getStr("desc"));
			System.out.println("");
			System.out.println("**入参**:");
			System.out.println("");
			System.out.println("```");
			System.out.println("{");
			System.out.println("\tdata:{");
			List<IntfParams> params = IntfParams.me.find("select * from intfParams where intfName=? and inOutFlag=1", intf.getStr("intfName"));
			for (IntfParams p : params) {
				formatLine(p);
			}
			System.out.println("\t}");
			System.out.println("}");
			System.out.println("```");
			System.out.println("");
			System.out.println("**出参**:");
			System.out.println("");
			System.out.println("```");
			System.out.println("{");
			System.out.println("\tdata:{");
			List<IntfParams> params2 = IntfParams.me.find("select * from intfParams where intfName=? and inOutFlag=2", intf.getStr("intfName"));
			for (IntfParams p : params2) {
				formatLine(p);
			}
			System.out.println("\t}");
			System.out.println("}");
			System.out.println("```");
		}


	}

	private String genPrefix(int x, String name) {
		if (x == 2) return "! " + name;
		if (x == 3) return "? " + name;
		return name;
	}


	private void formatLine(IntfParams record) {
		String pname = genPrefix(record.getInt("existence"), record.getStr("name"));
		if (record.getInt("maxOccur") == 1) {
			if (record.getInt("mainType") == 1) {
				System.out.println("\t\t" + pname + ": " + record.getStr("subType") + " " + record.getStr("desc"));
			} else if (record.getInt("mainType") == 2) {
				System.out.println("\t\t" + pname + ": " + record.getStr("subType") + " (枚举:" + record.getStr("objName") + ") " + record.getStr("desc"));
			} else if (record.getInt("mainType") == 3) {
				System.out.println("\t\t" + pname + ": {" + record.getStr("objName") + " 结构体 } " + record.getStr("desc"));
			} else {
				if (record.getInt("refType") == 1) {
					System.out.println("\t\t" + pname + ": " + record.getStr("subType") + " (Ref：" + record.getStr("objName") + ") " + record.getStr("desc"));
				} else {
					System.out.println("\t\t" + pname + ": {" + record.getStr("objName") + " 结构体 } " + record.getStr("desc"));
				}
			}
		} else {
			if (record.getInt("mainType") == 1) {
				System.out.println("\t\t" + pname + ": [" + record.getStr("subType") + ",...] " + record.getStr("desc"));
			} else if (record.getInt("mainType") == 2) {
				System.out.println("\t\t" + pname + ": [枚举：" + record.getStr("objName") + ",...] " + record.getStr("desc"));
			} else if (record.getInt("mainType") == 3) {
				System.out.println("\t\t" + pname + ": [{" + record.getStr("objName") + " 结构体 },...] " + record.getStr("desc"));
			} else {
				if (record.getInt("refType") == 1) {
					System.out.println("\t\t" + pname + ": [" + record.getStr("subType") + " (Ref：" + record.getStr("objName") + "),... ]" + record.getStr("desc"));
				} else {
					System.out.println("\t\t" + pname + ": [{" + record.getStr("objName") + " 结构体 },...] " + record.getStr("desc"));
				}
			}
		}


	}
}