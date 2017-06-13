/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell.API;

import de.tmahr.ods.signal.model.Typ;
import java.io.Serializable;

/**
 *
 * @author dyeske61283
 */
public interface Param extends Serializable {
   int getBits();
   String getName();
   Type getType();
}
