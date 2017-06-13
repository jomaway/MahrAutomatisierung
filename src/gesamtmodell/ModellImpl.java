/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell;

import gesamtmodell.API.Algo;
import gesamtmodell.API.Modell;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dyeske61283
 */
public class ModellImpl implements Modell {
    List<Algo> algos;

    public ModellImpl(List<Algo> algos) {
        this.algos = algos;
    }

    @Override
    public List<Algo> getAlgo() {
        return algos;
    }
    
    
}
