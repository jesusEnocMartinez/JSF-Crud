package com.empresaurios.crud.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.empresaurios.crud.model.Auto;
import com.empresaurios.crud.dao.AutoDao;
import com.empresaurios.crud.util.CommonUtils;

@ManagedBean
@SessionScoped
public class AutoBean implements Serializable {
    
    private List<Auto> autos;
    private Auto item = new Auto();
    private Auto beforeEditItem = null;
    private boolean edit;

    @ManagedProperty(value = "#{commonUtils}")
    private CommonUtils util;
    private AutoDao autoDao;

    public void setUtil(CommonUtils util) {
        this.util = util;
    }

    @PostConstruct
    public void init() {
        autoDao = new AutoDao();
        updateList();
    }

    private void updateList() {
        autos = new ArrayList<>();
        autos.addAll(autoDao.select());
    }

    public void add() {
        // DAO save the add
        autoDao.insert(item);
        item = new Auto();
        util.redirectWithGet();
        updateList();
    }

    public void resetAdd() {
        item = new Auto();

        util.redirectWithGet();
    }

    public void edit(Auto item) {
        beforeEditItem = item.copy();
        this.item = item;
        edit = true;

        util.redirectWithGet();
    }

    public void cancelEdit() {
        this.item.restore(beforeEditItem);
        this.item = new Auto();
        edit = false;

        util.redirectWithGet();
    }

    public void saveEdit() {
        // DAO save the edit
        autoDao.update(item);
        this.item = new Auto();
        edit = false;

        util.redirectWithGet();
        updateList();
    }

    public void delete(Auto item) throws IOException {
        // DAO save the delete
        autoDao.delete(item.getId());

        util.redirectWithGet();
        updateList();
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public Auto getItem() {
        return this.item;
    }

    public boolean isEdit() {
        return this.edit;
    }
}
