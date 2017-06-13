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
public class Type implements Serializable {
    
    public final boolean istKonstant;
    public final boolean istKomplex;
    public final boolean hatVorzeichen;
    public final boolean istGanzzahl;

    public Type(de.tmahr.ods.signal.model.Typ typ) {
        this.istKonstant = typ.istKonstant;
        this.istKomplex = typ.istKomplex;
        this.hatVorzeichen = typ.hatVorzeichen;
        this.istGanzzahl = typ.istGanzzahl;
    }
    
}
