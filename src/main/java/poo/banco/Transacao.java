package poo.banco;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private final String tipo;
    private final double valor;
    private final String dataHora;

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "[" + dataHora + "] " + tipo + ": R$ " + String.format("%.2f", valor);
    }
}
