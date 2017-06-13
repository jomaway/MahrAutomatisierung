/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell;

import gesamtmodell.API.Algo;
import gesamtmodell.API.Signal;

/**
 *
 * @author dyeske61283
 */
public class AlgoImpl implements Algo {
    
    Signal input;
    Signal output;
    String name;

    public AlgoImpl(Signal input, Signal output, String name) {
        this.input = input;
        this.output = output;
        this.name = name;
    }

    @Override
    public Signal getInput() {
        return input;
    }

    @Override
    public Signal getOutput() {
        return output;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
