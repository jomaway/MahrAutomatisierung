/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell.API;

import java.io.Serializable;

/**
 *
 * @author dyeske61283
 */
public interface Algo extends Serializable {
    Signal getInput();
    Signal getOutput();
    String getName();
}
