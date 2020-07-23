package com.madhusmita.classstructure.Setclasses;

import java.util.ArrayList;

/**
 * Created by Mithlesh Kumar Sharma on 12,December,2019
 * NTCS Company
 */
public class UserLoginSet {

    private ArrayList<String> objarray=new ArrayList<String>();

    public void Add(Object object){
        this.objarray=objarray;
    }
    public int getCount(){

        return objarray.size();
    }
    public String getItems(int i){

        int lastdata=this.getCount();
        if (i<0||i>lastdata-1){

            return null;
        }
        return this.objarray.get(i);
    }


}
