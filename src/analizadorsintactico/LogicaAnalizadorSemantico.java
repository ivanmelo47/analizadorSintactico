package analizadorsintactico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class LogicaAnalizadorSemantico {

    private ArrayList<String> ParteCodigo = new ArrayList();
    private ArrayList<String> CodigoOperaciones = new ArrayList();
    private ArrayList<String> Errores = new ArrayList();

    private ArrayList<String> VariablesInteger = new ArrayList();
    private ArrayList<String> VariablesString = new ArrayList();
    private ArrayList<String> VariablesChar = new ArrayList();
    private ArrayList<String> VariablesDouble = new ArrayList();
    private ArrayList<String> VariablesFloat = new ArrayList();
    private ArrayList<String> VariablesBoolean = new ArrayList();

    private ArrayList<String> TipoString = new ArrayList();
    private ArrayList<Integer> TipoInterger = new ArrayList();
    private ArrayList<char[]> TipoChar = new ArrayList();
    private ArrayList<Double> TipoDouble = new ArrayList();
    private ArrayList<Float> TipoFloat = new ArrayList();
    private ArrayList<Boolean> TipoBoolean = new ArrayList();

    int caracter = 0;

    public void Reset() {
        ParteCodigo.clear();
        Errores.clear();
        CodigoOperaciones.clear();

        VariablesInteger.clear();
        VariablesString.clear();
        VariablesChar.clear();
        VariablesDouble.clear();
        VariablesFloat.clear();
        VariablesBoolean.clear();

        TipoString.clear();
        TipoInterger.clear();
        TipoChar.clear();
        TipoDouble.clear();
        TipoFloat.clear();
        TipoBoolean.clear();
    }

    public void Separa_CodigoOperaciones(String Texto) {
        String LineasCodigo[] = Texto.split("[ \n]");
        String Codigo = "";
        boolean Auxiliar = false;

        for (int i = 0; i < LineasCodigo.length; i++) {
            for (int j = 0; j < LineasCodigo[i].length(); j++) {
                caracter = LineasCodigo[i].substring(j, j + 1).charAt(0);
                if (caracter == 59) {
                    CodigoOperaciones.add(Codigo);
                    Codigo = "";
                } else {
                    Codigo += LineasCodigo[i].substring(j, j + 1);
                }
            }

            if (Auxiliar) {
                Auxiliar = false;
            } else {
                Codigo += " ";
            }

            if (i + 1 < LineasCodigo.length) {
                for (int j = 0; j < LineasCodigo[i + 1].length(); j++) {
                    caracter = LineasCodigo[i + 1].substring(j, j + 1).charAt(0);
                    if (caracter == 59) {
                        Auxiliar = true;
                    }
                }
            }

        }

        for (int i = 0; i < CodigoOperaciones.size(); i++) {
            ValidarVariable(CodigoOperaciones.get(i));
        }

        if (Errores.isEmpty()) {
            for (int i = 0; i < CodigoOperaciones.size(); i++) {
                HacerOperaciones(CodigoOperaciones.get(i));
            }
            if (Errores.isEmpty()) {
                for (int i = 0; i < CodigoOperaciones.size(); i++) {
                    ImprimirOperaciones(CodigoOperaciones.get(i));
                }
            } else {
                String auxiliar = "";
                for (int j = 0; j < Errores.size(); j++) {
                    auxiliar += Errores.get(j);
                }
                JOptionPane.showMessageDialog(null, auxiliar);
            }

        } else {
            String auxiliar = "";
            for (int j = 0; j < Errores.size(); j++) {
                auxiliar += Errores.get(j);
            }
            JOptionPane.showMessageDialog(null, auxiliar);
        }

    }

    public void ValidarVariable(String codigo) {
        String LineaCodigo[] = codigo.split(" ");

        for (int i = 0; i < LineaCodigo.length; i++) {
            if (!LineaCodigo[i].equals("imprimir") && !LineaCodigo[i].equals("=") && !LineaCodigo[i].equals("+") && !LineaCodigo[i].equals("-") && !LineaCodigo[i].equals("/") && !LineaCodigo[i].equals("*")) {
                if (VariablesInteger.contains(LineaCodigo[i])) {

                } else if (VariablesString.contains(LineaCodigo[i])) {

                } else if (VariablesChar.contains(LineaCodigo[i])) {

                } else if (VariablesBoolean.contains(LineaCodigo[i])) {

                } else if (VariablesDouble.contains(LineaCodigo[i])) {

                } else if (VariablesFloat.contains(LineaCodigo[i])) {

                } else {
                    Errores.add("Error no ha sido declarada la siguiente variable: " + LineaCodigo[i] + "\n");
                }
            }
        }

    }

    public void ImprimirOperaciones(String codigo) {
        String LineaCodigo[] = codigo.split(" ");

        if (LineaCodigo[0].equals("imprimir")) {
            if (VariablesInteger.contains(LineaCodigo[1])) {
                JOptionPane.showMessageDialog(null, TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[1])));
            } else if (VariablesString.contains(LineaCodigo[1])) {
                JOptionPane.showMessageDialog(null, TipoString.get(VariablesString.indexOf(LineaCodigo[1])));
            } else if (VariablesChar.contains(LineaCodigo[1])) {
                JOptionPane.showMessageDialog(null, TipoChar.get(VariablesChar.indexOf(LineaCodigo[1])));
            } else if (VariablesBoolean.contains(LineaCodigo[1])) {
                JOptionPane.showMessageDialog(null, TipoBoolean.get(VariablesBoolean.indexOf(LineaCodigo[1])));
            } else if (VariablesDouble.contains(LineaCodigo[1])) {
                JOptionPane.showMessageDialog(null, TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[1])));
            } else if (VariablesFloat.contains(LineaCodigo[1])) {
                JOptionPane.showMessageDialog(null, TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[1])));
            }
        }
    }

    public String Separar_Codigo(String Texto) {
        Reset();
        boolean bandera = false;
        String Codigo = "";

        String LineasCodigo[] = Texto.split("[ \n]");

        for (int i = 0; i < LineasCodigo.length; i++) {
            switch (LineasCodigo[i]) {
                case "integer":
                    Codigo += LineasCodigo[i] + " ";
                    break;

                case "string":
                    Codigo += LineasCodigo[i] + " ";
                    break;

                case "char":
                    Codigo += LineasCodigo[i] + " ";
                    break;

                case "boolean":
                    Codigo += LineasCodigo[i] + " ";
                    break;

                case "float":
                    Codigo += LineasCodigo[i] + " ";
                    break;

                case "double":
                    Codigo += LineasCodigo[i] + " ";
                    break;

                default:
                    for (int j = 0; j < LineasCodigo[i].length(); j++) {
                        caracter = LineasCodigo[i].substring(j, j + 1).charAt(0);
                        if (caracter == 59) {
                            bandera = true;
                        }
                    }

                    if (bandera) {
                        Codigo += LineasCodigo[i];
                        ParteCodigo.add(Codigo);
                        Codigo = "";
                        bandera = false;
                    } else {
                        Codigo += LineasCodigo[i] + " ";
                    }
                    break;
            }
        }

        for (int i = 0; i < ParteCodigo.size(); i++) {
            ValidarLineaCodigo(ParteCodigo.get(i));
        }

        //Se puede poner mas para los diferentes tipos que puse aqui es solo para integer pero hay mas        
        if (VariablesInteger.size() > 0) {
            for (int j = 0; j < VariablesInteger.size(); j++) {
                if (VariablesString.contains(VariablesInteger.get(j))) {
                    Errores.add("La variable " + VariablesInteger.get(j) + " ya sido declara en una variable string\n");
                }
                if (VariablesBoolean.contains(VariablesInteger.get(j))) {
                    Errores.add("La variable " + VariablesInteger.get(j) + " ya sido declara en una variable boolean\n");
                }
                if (VariablesChar.contains(VariablesInteger.get(j))) {
                    Errores.add("La variable " + VariablesInteger.get(j) + " ya sido declara en una variable char\n");
                }
                if (VariablesFloat.contains(VariablesInteger.get(j))) {
                    Errores.add("La variable " + VariablesInteger.get(j) + " ya sido declara en una variable float\n");
                }
                if (VariablesDouble.contains(VariablesInteger.get(j))) {
                    Errores.add("La variable " + VariablesInteger.get(j) + " ya sido declara en una variable double\n");
                }
            }
        }

        String auxiliar = "";
        for (int j = 0; j < Errores.size(); j++) {
            auxiliar += Errores.get(j);
        }

        return auxiliar;
    }

    public void HacerOperaciones(String codigo) {
        String LineaCodigo[] = codigo.split(" ");

        if (!LineaCodigo[0].equals("imprimir")) {

            if (VariablesInteger.contains(LineaCodigo[0])) {//Entero operaciones

                if (LineaCodigo[3].equals("+")) {
                    try {
                        int Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                Total += TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[i]));
                            }
                        }

                        System.out.println("*");
                        TipoInterger.set(VariablesInteger.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("-")) {
                    try {
                        int Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[i]));
                                } else {
                                    Total -= TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoInterger.set(VariablesInteger.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("*")) {
                    try {
                        int Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[i]));
                                } else {
                                    Total *= TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoInterger.set(VariablesInteger.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("/")) {
                    try {
                        int Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[i]));
                                } else {
                                    Total /= TipoInterger.get(VariablesInteger.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoInterger.set(VariablesInteger.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                }

            } else if (VariablesString.contains(LineaCodigo[0])) {//Cadenas

                if (LineaCodigo[3].equals("+")) {
                    try {
                        String Total = "";
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                Total += TipoString.get(VariablesString.indexOf(LineaCodigo[i])) + " ";
                            }
                        }

                        TipoString.set(VariablesString.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                }

            } else if (VariablesChar.contains(LineaCodigo[0])) {//Char No realizar ninguna operacion
                System.out.println("l");
            } else if (VariablesBoolean.contains(LineaCodigo[0])) {//Boolean No tiene operaciones que hacer
                System.out.println("s");
            } else if (VariablesDouble.contains(LineaCodigo[0])) {//double

                if (LineaCodigo[3].equals("+")) {
                    try {
                        double Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                Total += TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[i]));
                            }
                        }

                        TipoDouble.set(VariablesDouble.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("-")) {

                    try {
                        double Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[i]));
                                } else {
                                    Total -= TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoDouble.set(VariablesDouble.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("*")) {
                    try {
                        double Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[i]));
                                } else {
                                    Total *= TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoDouble.set(VariablesDouble.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("/")) {

                    try {
                        double Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[i]));
                                } else {
                                    Total /= TipoDouble.get(VariablesDouble.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoDouble.set(VariablesDouble.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                }

            } else if (VariablesFloat.contains(LineaCodigo[0])) {//float 

                if (LineaCodigo[3].equals("+")) {
                    try {
                        float Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                Total += TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[i]));
                            }
                        }

                        TipoFloat.set(VariablesFloat.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("-")) {

                    try {
                        float Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[i]));
                                } else {
                                    Total -= TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoFloat.set(VariablesFloat.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("*")) {

                    try {
                        float Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[i]));
                                } else {
                                    Total *= TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoFloat.set(VariablesFloat.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                } else if (LineaCodigo[3].equals("/")) {

                    try {
                        float Total = 0;
                        for (int i = 2; i < LineaCodigo.length; i++) {
                            if (i % 2 == 0) {
                                if (i == 2) {
                                    Total = TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[i]));
                                } else {
                                    Total /= TipoFloat.get(VariablesFloat.indexOf(LineaCodigo[i]));
                                }
                            }
                        }

                        TipoFloat.set(VariablesFloat.indexOf(LineaCodigo[0]), Total);
                    } catch (Exception ex) {
                        Errores.add("Error las variables no tienen el mismo tipo de dato\n");
                    }

                }
            }
        }
    }

    public boolean ValidarLineaCodigo(String Codigo) {
        boolean Bandera = true;
        String LineaCodigo[] = Codigo.split(" ");

        for (int i = 0; i < LineaCodigo.length; i++) {

            if (LineaCodigo[i].equals("integer") && i == 0) {

                for (int j = 1; j < LineaCodigo.length; j++) {
                    if (LineaCodigo[j].equals("string")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("char")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("boolena")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("float")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("double")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else {

                        String Variable = "";
                        for (int k = 0; k < LineaCodigo[j].length(); k++) {
                            caracter = LineaCodigo[j].substring(k, k + 1).charAt(0);
                            if (caracter != 44 && caracter != 59) {
                                Variable += LineaCodigo[j].substring(k, k + 1);
                            }
                        }
                        if (Variable == "") {
                            Errores.add("Te falta declarar alguna variable\n");
                            Bandera = false;
                        } else {
                            VariablesInteger.add(Variable);
                            TipoInterger.add(0);
                        }
                    }
                }

            } else if (LineaCodigo[i].equals("string") && i == 0) {

                for (int j = 1; j < LineaCodigo.length; j++) {
                    if (LineaCodigo[j].equals("integer")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("char")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("boolena")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("float")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("double")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else {

                        String Variable = "";
                        for (int k = 0; k < LineaCodigo[j].length(); k++) {
                            caracter = LineaCodigo[j].substring(k, k + 1).charAt(0);
                            if (caracter != 44 && caracter != 59) {
                                Variable += LineaCodigo[j].substring(k, k + 1);
                            }
                        }
                        if (Variable == "") {
                            Errores.add("Te falta declarar alguna variable\n");
                            Bandera = false;
                        } else {
                            VariablesString.add(Variable);
                            TipoString.add("");
                        }
                    }
                }

            } else if (LineaCodigo[i].equals("char") && i == 0) {

                for (int j = 1; j < LineaCodigo.length; j++) {
                    if (LineaCodigo[j].equals("integer")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("string")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("boolena")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("float")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("double")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else {

                        String Variable = "";
                        for (int k = 0; k < LineaCodigo[j].length(); k++) {
                            caracter = LineaCodigo[j].substring(k, k + 1).charAt(0);
                            if (caracter != 44 && caracter != 59) {
                                Variable += LineaCodigo[j].substring(k, k + 1);
                            }
                        }
                        if (Variable == "") {
                            Errores.add("Te falta declarar alguna variable\n");
                            Bandera = false;
                        } else {
                            VariablesChar.add(Variable);
                            //TipoChar.add('s','2');
                        }
                    }
                }

            } else if (LineaCodigo[i].equals("boolean") && i == 0) {

                for (int j = 1; j < LineaCodigo.length; j++) {
                    if (LineaCodigo[j].equals("integer")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("char")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("string")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("float")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("double")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else {

                        String Variable = "";
                        for (int k = 0; k < LineaCodigo[j].length(); k++) {
                            caracter = LineaCodigo[j].substring(k, k + 1).charAt(0);
                            if (caracter != 44 && caracter != 59) {
                                Variable += LineaCodigo[j].substring(k, k + 1);
                            }
                        }
                        if (Variable == "") {
                            Errores.add("Te falta declarar alguna variable\n");
                            Bandera = false;
                        } else {
                            VariablesBoolean.add(Variable);
                            TipoBoolean.add(true);
                        }
                    }
                }

            } else if (LineaCodigo[i].equals("float") && i == 0) {

                for (int j = 1; j < LineaCodigo.length; j++) {
                    if (LineaCodigo[j].equals("integer")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("char")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("boolena")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("string")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("double")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else {

                        String Variable = "";
                        for (int k = 0; k < LineaCodigo[j].length(); k++) {
                            caracter = LineaCodigo[j].substring(k, k + 1).charAt(0);
                            if (caracter != 44 && caracter != 59) {
                                Variable += LineaCodigo[j].substring(k, k + 1);
                            }
                        }
                        if (Variable == "") {
                            Errores.add("Te falta declarar alguna variable\n");
                            Bandera = false;
                        } else {
                            VariablesFloat.add(Variable);
                            TipoFloat.add(0.0f);
                        }
                    }
                }

            } else if (LineaCodigo[i].equals("double") && i == 0) {

                for (int j = 1; j < LineaCodigo.length; j++) {
                    if (LineaCodigo[j].equals("integer")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("char")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("boolena")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("float")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else if (LineaCodigo[j].equals("string")) {
                        Errores.add("Error al poner dos tipo de datos juntos: " + LineaCodigo[i] + " y " + LineaCodigo[j] + "\n");
                        Bandera = false;
                    } else {

                        String Variable = "";
                        for (int k = 0; k < LineaCodigo[j].length(); k++) {
                            caracter = LineaCodigo[j].substring(k, k + 1).charAt(0);
                            if (caracter != 44 && caracter != 59) {
                                Variable += LineaCodigo[j].substring(k, k + 1);
                            }
                        }
                        if (Variable == "") {
                            Errores.add("Te falta declarar alguna variable\n");
                            Bandera = false;
                        } else {
                            VariablesDouble.add(Variable);
                            TipoDouble.add(0.0d);
                        }
                    }
                }

            } else if (i == 0) {
                if (LineaCodigo[1].equals("=") && !LineaCodigo[2].equals(";")) {
                    if (VariablesInteger.contains(LineaCodigo[0])) {

                        int indice = 0;
                        String valor = "";
                        for (int j = 0; j < VariablesInteger.size(); j++) {
                            if (VariablesInteger.get(j).equals(LineaCodigo[0])) {
                                indice = j;
                            }
                        }

                        for (int k = 0; k < LineaCodigo[2].length(); k++) {
                            caracter = LineaCodigo[2].substring(k, k + 1).charAt(0);
                            if (caracter != 59) {
                                valor += LineaCodigo[2].substring(k, k + 1);
                            }
                        }

                        try {
                            TipoInterger.set(indice, Integer.parseInt(valor));
                        } catch (Exception ex) {
                            Errores.add("Error: la variable " + LineaCodigo[0] + " no se le puede asignar el valor " + valor + "\n");
                            Bandera = false;
                        }

                    } else if (VariablesString.contains(LineaCodigo[0])) {

                        int indice = 0;
                        String valor = "";
                        for (int j = 0; j < VariablesString.size(); j++) {
                            if (VariablesString.get(j).equals(LineaCodigo[0])) {
                                indice = j;
                            }
                        }

                        for (int k = 0; k < LineaCodigo[2].length(); k++) {
                            caracter = LineaCodigo[2].substring(k, k + 1).charAt(0);
                            if (caracter != 59) {
                                valor += LineaCodigo[2].substring(k, k + 1);
                            }
                        }

                        try {
                            TipoString.set(indice, valor);
                        } catch (Exception ex) {
                            Errores.add("Error: la variable " + LineaCodigo[0] + " no se le puede asignar el valor " + valor + "\n");
                            Bandera = false;
                        }

                    } else if (VariablesChar.contains(LineaCodigo[0])) {

                        int indice = 0;
                        String valor = "";
                        for (int j = 0; j < VariablesChar.size(); j++) {
                            if (VariablesChar.get(j).equals(LineaCodigo[0])) {
                                indice = j;
                            }
                        }

                        for (int k = 0; k < LineaCodigo[2].length(); k++) {
                            caracter = LineaCodigo[2].substring(k, k + 1).charAt(0);
                            if (caracter != 59) {
                                valor += LineaCodigo[2].substring(k, k + 1);
                            }
                        }

                        try {
                            //TipoChar.set(indice, valor);
                        } catch (Exception ex) {
                            Errores.add("Error: la variable " + LineaCodigo[0] + " no se le puede asignar el valor " + valor + "\n");
                            Bandera = false;
                        }

                    } else if (VariablesBoolean.contains(LineaCodigo[0])) {

                        int indice = 0;
                        String valor = "";
                        for (int j = 0; j < VariablesBoolean.size(); j++) {
                            if (VariablesBoolean.get(j).equals(LineaCodigo[0])) {
                                indice = j;
                            }
                        }

                        for (int k = 0; k < LineaCodigo[2].length(); k++) {
                            caracter = LineaCodigo[2].substring(k, k + 1).charAt(0);
                            if (caracter != 59) {
                                valor += LineaCodigo[2].substring(k, k + 1);
                            }
                        }

                        try {
                            TipoBoolean.set(indice, Boolean.valueOf(valor));
                        } catch (Exception ex) {
                            Errores.add("Error: la variable " + LineaCodigo[0] + " no se le puede asignar el valor " + valor + "\n");
                            Bandera = false;
                        }

                    } else if (VariablesDouble.contains(LineaCodigo[0])) {

                        int indice = 0;
                        String valor = "";
                        for (int j = 0; j < VariablesDouble.size(); j++) {
                            if (VariablesDouble.get(j).equals(LineaCodigo[0])) {
                                indice = j;
                            }
                        }

                        for (int k = 0; k < LineaCodigo[2].length(); k++) {
                            caracter = LineaCodigo[2].substring(k, k + 1).charAt(0);
                            if (caracter != 59) {
                                valor += LineaCodigo[2].substring(k, k + 1);
                            }
                        }

                        try {
                            TipoDouble.set(indice, Double.parseDouble(valor));
                        } catch (Exception ex) {
                            Errores.add("Error: la variable " + LineaCodigo[0] + " no se le puede asignar el valor " + valor + "\n");
                            Bandera = false;
                        }

                    } else if (VariablesFloat.contains(LineaCodigo[0])) {

                        int indice = 0;
                        String valor = "";
                        for (int j = 0; j < VariablesFloat.size(); j++) {
                            if (VariablesFloat.get(j).equals(LineaCodigo[0])) {
                                indice = j;
                            }
                        }

                        for (int k = 0; k < LineaCodigo[2].length(); k++) {
                            caracter = LineaCodigo[2].substring(k, k + 1).charAt(0);
                            if (caracter != 59) {
                                valor += LineaCodigo[2].substring(k, k + 1);
                            }
                        }

                        try {
                            TipoFloat.set(indice, Float.parseFloat(valor));
                        } catch (Exception ex) {
                            Errores.add("Error: la variable " + LineaCodigo[0] + " no se le puede asignar el valor " + valor + "\n");
                            Bandera = false;
                        }

                    } else {
                        Errores.add("La variable " + LineaCodigo[0] + " no  ha sido declarada\n");
                        Bandera = false;
                    }
                } else if (LineaCodigo[2].equals(";")) {
                    Errores.add("Error en la variable: " + LineaCodigo[0] + " no hay ningun valor valor\n");
                    Bandera = false;
                } else {
                    Errores.add("Error al asignar un valor a la variable " + LineaCodigo[0] + "\n");
                    Bandera = false;
                }

            }
        }
        return Bandera;
    }

}
