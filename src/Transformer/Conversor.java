/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transformer;

import Estructuras.TStack;
import Estructuras.TElemento;
import javax.swing.JOptionPane;

/**
 *
 * @author MaicolB
 */
public class Conversor {

    private String original;
    public String seudo = "";
    private String[][] palabrasDeLlaves;
    public Conversor(String total,boolean stacker,boolean comentarios,boolean reemplazador) {
        original = total;
        seudo = original + "";
        quitarImports();
        if(comentarios){
            quitarComentarios();
        }
        //llenarArrPalabrasClaves();
        if(stacker){
            stacker();
        }
        if(reemplazador){
            replacer();
        }
        
    }

    private void quitarImports() {
        if(seudo.indexOf("class ")!=-1){
            seudo = seudo.substring(seudo.indexOf("class "));
        }
    }

    private void quitarComentarios() {
        while (seudo.indexOf("//") != -1) {
            seudo = seudo.substring(0, seudo.indexOf("//")) + seudo.substring(seudo.indexOf("\r\n", seudo.indexOf("//")) + 1);
        }
        while (seudo.indexOf("/*") != -1) {
            seudo = seudo.substring(0, seudo.indexOf("/*")) + seudo.substring(seudo.indexOf("*/", seudo.indexOf("/*")) + 1);
        }
    }
	
    private void stacker() {
        String[][] palabrasDeLlaves = {
            {"while", " mientras ", " finmientras "},//0
            //{" while(", " mientras "," finmientras "},
            {"for", " para ", " finpara "},//1
            //{" for("," para "," finpara "},
            {"if", " si ", " finsi "},//{" if(", " si "," finsi "},//2
            {"else", " sino "}, {"}else", " sino "},{"} else", " sino "},{"do{", "hacer"},//{" else(", " sino "}
            {"do {", "hacer"},
            {"class", "clase", "finclase"}, {"public", "\r\n COMIENZO", "\r\n FIN"}, {"private", "\r\n COMIENZO", "\r\n FIN"}, {"}"}
        };
        TStack stack = new TStack();
        
        int posicion = 0;//contador que indica la posicion actual en el codigo
        int posAnt=-1;int posAnt2=-1;
        int contadorMinimo = 0;
        String anteriorAPalabra = "";
        while (posicion < seudo.length() - 1||posicion==-1) {
            int contadorPalabrasClaves = 0;// se usa para contar las palabras de llaves que existen , probando todas las alternativas
            //min=max;
            int min = seudo.length();
            whileDePalabrasClaves:
            while (contadorPalabrasClaves < palabrasDeLlaves.length) {

                if (seudo.indexOf(palabrasDeLlaves[contadorPalabrasClaves][0]) != -1) {//pregunto si existe esa palabraLlave desde 0 hasta la posicion actual
                    posicion = seudo.indexOf(palabrasDeLlaves[contadorPalabrasClaves][0]);//ahora la posicion pasa a ser partir de la primera palabraLlave encontrada
                    
                    if (posicion < min) {
                        min = posicion;
                        contadorMinimo = contadorPalabrasClaves;
                        if(contadorPalabrasClaves==3||contadorPalabrasClaves==4||contadorPalabrasClaves==5){
                        break whileDePalabrasClaves;
                        }
                    }
                }
                contadorPalabrasClaves++;

            }
           if(posAnt==posicion){
               //int posAnt2=-1;
                if(posAnt2==posicion){
                    JOptionPane.showMessageDialog(null, "Hubo errores al convertir");
                    return;
                }
                posAnt2=posicion;
            }
            posAnt=posicion;

            contadorPalabrasClaves = contadorMinimo;
            posicion = min;
            posicion = seudo.indexOf(palabrasDeLlaves[contadorPalabrasClaves][0], min);
            if(posicion==-1){
                return;
            }
            //while (contadorPalabrasClaves < palabrasDeLlaves.length) {//voy probando todas las alternativas de palabras de llaves


            String apa=seudo.substring(0,72);
            if (posicion > 0) {
                anteriorAPalabra = seudo.substring(0, posicion - 1);
            } else {
                anteriorAPalabra = "";
            }
            String despuesAPalabra = seudo.substring(posicion + palabrasDeLlaves[contadorPalabrasClaves][0].length(), seudo.length());
            if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("}")) {
                try {
                    if ((stack.obtenerUltimo() != null) && stack.obtenerUltimo().imprimirEtiqueta().equals("finclase")) {
                        return;
                    }
                    if(stack.obtenerUltimo().imprimirEtiqueta().equals("\r\n FIN")&&
                            (despuesAPalabra.indexOf(";") < despuesAPalabra.indexOf("\n"))&&(despuesAPalabra.indexOf(";")!=-1)){
                            
                               seudo=seudo.replaceFirst("}", "");
                            
                    }else{
                        //despuesAPalabra=despuesAPalabra.replaceFirst(palabrasDeLlaves[contadorPalabrasClaves][0], stack.obtenerUltimo().imprimirEtiqueta());
                        // String a=stack.obtenerUltimo().imprimirEtiqueta();
                        seudo = anteriorAPalabra + stack.obtenerUltimo().imprimirEtiqueta() + despuesAPalabra;
                        stack.quitarDeStack();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Aparecieron bucles desconocidos");
                    return;
                }
            }
            if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("for")) {

                despuesAPalabra = despuesAPalabra.replaceFirst("\\{", " hacer ");
                seudo = anteriorAPalabra + palabrasDeLlaves[contadorPalabrasClaves][1] + despuesAPalabra;
                stack.ponerEnStack(new TElemento(palabrasDeLlaves[contadorPalabrasClaves][2], null));
            }
            if (palabrasDeLlaves[contadorPalabrasClaves][0].indexOf("do")!=-1) {

                seudo = anteriorAPalabra + palabrasDeLlaves[contadorPalabrasClaves][1] + despuesAPalabra;
            }
            if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("class")) {

                despuesAPalabra = despuesAPalabra.replaceFirst("\\{", " ");
                seudo = anteriorAPalabra + despuesAPalabra;
                stack.ponerEnStack(new TElemento(palabrasDeLlaves[contadorPalabrasClaves][2], null));
            }
            if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("public") || palabrasDeLlaves[contadorPalabrasClaves][0].equals("private")) {

                int posParentesis = despuesAPalabra.indexOf("(");
                int posPuntoYComa = despuesAPalabra.indexOf(";");
                if(posPuntoYComa<0){posPuntoYComa=seudo.length();}
                int posLlave = despuesAPalabra.indexOf("\\{");
                if(posLlave<0){posLlave=seudo.length();}
//                String firma=despuesAPalabra.substring(0, posParentesis);
//                String [] palabrasQueContienenLaFirma=firma.split(" ");
                if (posParentesis < posPuntoYComa && posParentesis < posLlave) {
                    despuesAPalabra = despuesAPalabra.replaceFirst("\\{", palabrasDeLlaves[contadorPalabrasClaves][1]);
                    seudo = anteriorAPalabra + despuesAPalabra;
                    stack.ponerEnStack(new TElemento(palabrasDeLlaves[contadorPalabrasClaves][2], null));
                }else{
                     if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("public")){
                        seudo=seudo.replaceFirst("public", "");
                    }else{
                         seudo=seudo.replaceFirst("private", "");
                    }
                }
              
            }

            if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("while")) {
                if (despuesAPalabra.indexOf(";") > despuesAPalabra.indexOf("{")&&despuesAPalabra.indexOf("{")!=-1) {
                    despuesAPalabra = despuesAPalabra.replaceFirst("\\{", " hacer ");
                    stack.ponerEnStack(new TElemento(palabrasDeLlaves[contadorPalabrasClaves][2], null));
                }else{
                    //secuencia do..while
                    //anteriorAPalabra=anteriorAPalabra.substring(0, anteriorAPalabra.length()-1);
                }
                seudo = anteriorAPalabra + palabrasDeLlaves[contadorPalabrasClaves][1] + despuesAPalabra;
            }

            if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("if")) {

                despuesAPalabra = despuesAPalabra.replaceFirst("\\{", " entonces ");
                seudo = anteriorAPalabra + palabrasDeLlaves[contadorPalabrasClaves][1] + despuesAPalabra;
                stack.ponerEnStack(new TElemento(palabrasDeLlaves[contadorPalabrasClaves][2], null));
            }
            if (palabrasDeLlaves[contadorPalabrasClaves][0].equals("else")||palabrasDeLlaves[contadorPalabrasClaves][0].equals("} else")||palabrasDeLlaves[contadorPalabrasClaves][0].equals("}else")) {

                int anteriorAElse=anteriorAPalabra.indexOf("}");
                if(anteriorAElse==-1){ anteriorAElse=anteriorAPalabra.length()-1;}
                
                seudo = anteriorAPalabra.substring(0,anteriorAElse) + palabrasDeLlaves[contadorPalabrasClaves][1] + despuesAPalabra;
            }

            posicion++;
        }


    }

    private void replacer() {
        seudo = seudo.replace(" class ", " clase ");
        seudo = seudo.replaceAll(" void ", " vacio ");
        seudo = seudo.replaceAll(".toString()", ".pasarACadena()");
        seudo = seudo.replaceAll("String ", "Cadena ");
        seudo = seudo.replaceAll("int ", "entero ");
        seudo = seudo.replaceAll(";", "");
        seudo = seudo.replaceAll("return ", "devolver ");
        seudo = seudo.replaceAll(".length()", ".largo ");
        seudo = seudo.replaceAll(".length", ".largo ");
        seudo = seudo.replaceAll(".count()", ".largo ");
        seudo = seudo.replaceAll("private ", "");
        seudo = seudo.replaceAll("public ", "");
        seudo = seudo.replaceAll("protected ", "");
        seudo = seudo.replaceAll("implements ", "hereda de ");
        seudo = seudo.replaceAll("=new ", "= nuevo ");
        seudo = seudo.replaceAll(" new ", " nuevo ");
        seudo = seudo.replaceAll("get", "obtener");
        seudo = seudo.replaceAll("set", "setear");
        seudo = seudo.replaceAll("Get", "obtener");
        seudo = seudo.replaceAll("Set", "setear");
        seudo = seudo.replaceAll(" = ", " -> ");
        seudo = seudo.replaceAll("System.out.println", "imprimirEnPantalla");
        seudo = seudo.replaceAll("boolean ", "valor logico ");
        //seudo = seudo.replaceAll("["+"]"," array ");
        //seudo = seudo.replaceAll("["+"]"+"["+"]"," matriz ");
        seudo = seudo.replaceAll("integer.ParseInt", "convertirAEntero");
        seudo = seudo.replaceAll(" static ", "");
        seudo = seudo.replaceAll("static ", "");
        seudo = seudo.replaceAll("false","falso");
        seudo = seudo.replaceAll("true","verdadero");
        seudo = seudo.replaceAll("!="," es distinto a ");
        seudo = seudo.replaceAll("!"," no es ");
    }
}
