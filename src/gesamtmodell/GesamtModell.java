/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesamtmodell;

import gesamtmodell.API.Algo;
import gesamtmodell.API.Modell;
import gesamtmodell.API.Param;
import gesamtmodell.API.Signal;
import gesamtmodell.API.Type;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dyeske61283
 */
public class GesamtModell {
    
    de.tmahr.eap.model.Modell eapModel;
    List<de.tmahr.ods.signal.model.Signal> odsSignals;
    List<gesamtmodell.API.Signal> signals;
    List<Algo> algos;
    

    public GesamtModell(de.tmahr.eap.model.Modell eapModel, List<de.tmahr.ods.signal.model.Signal> odsModel) {
        this.eapModel = eapModel;
        this.odsSignals = odsModel;
        signals = new ArrayList<>();
        algos = new ArrayList<>();
    }

    public Modell generateOwnModel() {
        for(de.tmahr.ods.signal.model.Signal s : odsSignals){
            signals.add(createSignal(s));
        }
        
        for(de.tmahr.eap.model.Element e : eapModel.elemente("Component")){
            algos.add(createAlgo(e));
        }
        
        return new ModellImpl(algos);
        
        
        
    }
    
    public gesamtmodell.API.Signal createSignal(de.tmahr.ods.signal.model.Signal sig){
        ArrayList<Param> params = new ArrayList<>();
        for (de.tmahr.ods.signal.model.Parameter p: sig.parameter()){
            params.add(new ParamImpl(p.bits.anzahlBits,p.name.kurz,new Type(p.typ) ));
        }
        return new SignalImpl(sig.getName(), params);
    }
    
    public Algo createAlgo(de.tmahr.eap.model.Element element){
        String name = element.name();
        String inputName = "";
        String outputName = "";
        
        gesamtmodell.API.Signal inputSig = null;
        gesamtmodell.API.Signal outputSig = null;
        
        for (de.tmahr.eap.model.Element e : element.getSubElements()){
            if(e.typ().equals("RequiredInterface")){
                inputName = e.name();
            } else if(e.typ().equals("ProvidedInterface")){
                outputName = e.name();
            }
        }
        
        for(gesamtmodell.API.Signal s : signals){
            if (s.getName().equals(inputName)){
                inputSig = s;
            } else if (s.getName().equals(outputName)){
                outputSig = s;
            }
        }
        
        if (inputSig != null && outputSig != null){
            return new AlgoImpl(inputSig,outputSig,name);
        } else {
            return null;
        }
        
    }
    
    
    public static void serialisiereOwnModell(Modell ownModell, String dateiName)
    {
        try (FileOutputStream fos = new FileOutputStream(dateiName); ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(ownModell);
        }
        catch (IOException e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
    } 
    
    public static Modell deserialisiereOwnModell(String fileName)
    {
        Modell ownModell = null;
        try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis))
        {
            ownModell = (Modell) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
        return ownModell;
    } 
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String outputFileName= "U:/MahrAutomatisierung/GesamtModell/Wetterstation/ownModell.ser";
        
        //input Files (all serialised)
        String eapModelFileName="U:/MahrAutomatisierung/GesamtModell/Wetterstation/Wetterstation.eap.ser";
        String[] signalFileNames = {"U:/MahrAutomatisierung/GesamtModell/Wetterstation/S1.ods",
            "U:/MahrAutomatisierung/GesamtModell/Wetterstation/S2.ods",
            "U:/MahrAutomatisierung/GesamtModell/Wetterstation/S1.ods"};
        List<de.tmahr.ods.signal.model.Signal> odsSignals = new ArrayList<>();
        int i = 1;
        for (String fileName : signalFileNames){
            odsSignals.add(de.tmahr.ods.signal.extractor.OdsImportApi.signalAuslesen("S" + i , fileName));
            i++;
        }
        
        de.tmahr.eap.model.Modell eapModel = null;
        
        try (FileInputStream fis = new FileInputStream(eapModelFileName);
                ObjectInputStream ois = new ObjectInputStream(fis))
        {
            System.out.println("You won");
            eapModel = (de.tmahr.eap.model.Modell) ois.readObject();
            System.out.println("You really lost");
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Exception " + e.getMessage());
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
        
    
        if(eapModel != null){
           GesamtModell gesModell = new GesamtModell(eapModel, odsSignals);
           Modell ownModell = gesModell.generateOwnModel();
           gesModell.serialisiereOwnModell(ownModell, outputFileName); 
        } else {
            System.out.println("You lost");
        }
        
        
    }
    
}
