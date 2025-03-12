package poo.colecao_dicionario;

import java.util.HashMap;
import java.util.Map;

public class ExemploDicionario {

    public static void main(String[] args) {

        Map<String, String> agenda = new HashMap<>();
        agenda.put("Sergio", "+385 23343");
        agenda.put("Flavio", "+55 11 98234.3452");
        agenda.put("Jose", "+75 2345");
        System.out.println(agenda);

        agenda.put("Sergio", "+55 21 455454");
        System.out.println(agenda);

        System.out.println(agenda.get("Jose"));

    }

}
