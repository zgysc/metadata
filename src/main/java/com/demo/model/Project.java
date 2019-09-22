package com.demo.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class Project extends Model<Project> {
    public static final Project me = new Project();
    public List<Project> findAll() {
        return find("select * from project");
    }
}