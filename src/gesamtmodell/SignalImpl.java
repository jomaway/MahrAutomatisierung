/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell;

import gesamtmodell.API.Param;
import gesamtmodell.API.Signal;
import java.util.List;

/**
 *
 * @author dyeske61283
 */
public class SignalImpl implements Signal {
    
    String name;
    List<Param> params;

    public SignalImpl(String name, List<Param> params) {
        this.name = name;
        this.params = params;
    }
    
    

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Param> getParams() {
        return params;
    }
    
}
