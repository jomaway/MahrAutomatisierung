/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell.API;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author dyeske61283
 */
public interface Modell extends Serializable {
    List<Algo> getAlgo();
}
