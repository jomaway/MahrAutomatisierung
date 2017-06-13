/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell;

import de.tmahr.ods.signal.model.Typ;
import gesamtmodell.API.Param;
import gesamtmodell.API.Type;

/**
 *
 * @author dyeske61283
 */
public class ParamImpl implements Param {
    
    int bits;
    String name;
    Type type;

    public ParamImpl(int bits, String name, Type type) {
        this.bits = bits;
        this.name = name;
        this.type = type;
    }
    

    @Override
    public int getBits() {
        return bits;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }
    
}
