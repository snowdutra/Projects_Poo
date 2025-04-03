package poo.week06;

public class MeuException extends Exception {

    public MeuException(String descricao) {
        super(descricao);
    }

    public MeuException(String descricao, Throwable t) {
        super(descricao, t);
    }
    
}
