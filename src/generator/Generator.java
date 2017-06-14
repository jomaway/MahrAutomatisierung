/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

import gesamtmodell.API.Algo;
import gesamtmodell.API.Modell;
import gesamtmodell.GesamtModell;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Builder Class
 * @author Franzichen
 */
public class Generator
{
  public Generator()
  {

  }
  
  private static void generieren(VelocityContext context, String ausgabeDateiname, String templateDateiname)
  {
    VelocityEngine ve = new VelocityEngine();
    try
    {
      ve.init();
      Template template = ve.getTemplate(templateDateiname);
      StringWriter w = new StringWriter();
      template.merge(context, w);
      java.io.File outputFile = new java.io.File(ausgabeDateiname);
      java.io.FileWriter writer = new java.io.FileWriter(outputFile);
      writer.write(w.toString());
      writer.close();

    } 
    catch (IOException e)
    {
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
    //new Generator();
    String pfad = System.getProperty("user.dir");
    Modell modell = GesamtModell.deserialisiereOwnModell("../Wetterstation/ownModell.ser");
    VelocityContext context = new VelocityContext();
    //context.put("modell", modell);
    //generieren(context, pfad + "../../Wetter/algo.java", "../Wetterstation/algo.vm");
    
    for(Algo algo : modell.getAlgo())
    {
      context.put("Algo", algo);
      generieren(context, "../Wetter/" + algo.getName() + ".c", "/src/generator/algo.vm");
    }
    
    for(Algo algo : modell.getAlgo())
    {
      context.put("Algo", algo);
      generieren(context, "../Wetter/" + algo.getName() + ".h", "/src/generator/signal.vm");
    }
  }
}
