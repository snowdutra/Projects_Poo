package poo.project_colecao_dicionario.colecao;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class ExemploColecoes {
    public static void main(String[] args) {
        

        List<String> listNomes = new ArrayList<>();
        listNomes.add("Gustavo");
        listNomes.add("Beto");
        listNomes.add("Carlos");
        print(listNomes);


        Set<String> setNomes = new HashSet<>();
        setNomes.add("Gustavo");
        setNomes.add("Beto");
        setNomes.add("Carlos");
        print(setNomes);
       
    }

    public static void print(Collection<?> c) {
        for(Object item : c) {
            System.out.println(item);
        }
    }
}
