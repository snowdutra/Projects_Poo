package poo.project_colecao_dicionario.colecao;

import java.util.HashMap;
import java.util.Map;

public class ExemploDicionario {

    public static void main(String[] args) {
        Map<String, String> agenda = new HashMap<>();
        agenda.put("Gustavo", "1234-5678");
        agenda.put("Beto", "1234-5679");
        agenda.put("Carlos", "1234-5680");

        System.out.println("Agenda: " + agenda);

        String novoTelefone = "1234-5670";
        if (agenda.containsKey("Gustavo")) {
            agenda.put("Gustavo", novoTelefone);
        }

        System.out.println("Agenda Atualizada: " + agenda);

        String telefone = agenda.get("Gustavo");
        System.out.println("Telefone de Gustavo: " + telefone);
    }
}

