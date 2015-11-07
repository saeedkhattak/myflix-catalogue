/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myflix.myflix.stores;

import java.util.HashMap;

/**
 *
 * @author andy
 */
public class Categorie {
     HashMap<String,String> hl=null;
    public void Categorie(){
        
    }
    
    public void setFields(HashMap hl){
        this.hl=hl;
    }
    
    public HashMap<String,String> getfields(){
        return hl;
    }
    
}
