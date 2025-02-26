package poo.project_colecao_dicionario.colecao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ExemploColecoes {

    public static void main(String[] args) {
        
        List<String> listNomes = new ArrayList<>();
        listNomes.add("Guilherme");
        listNomes.add("Rafael");
        listNomes.add("Sergio");
        listNomes.add("Flavio");
        listNomes.add("Marcelo");
        print(listNomes);

        Set<String> setNomes = new TreeSet<>();
        setNomes.add("Guilherme");
        setNomes.add("Rafael");
        setNomes.add("Sergio");
        setNomes.add("Flavio");
        setNomes.add("Marcelo");
        print(setNomes);

    }

    public static void print(Collection<?> c) {
        c.forEach(e -> System.out.print(e + "  "));
        System.out.println();
    }
    
}