package poo.banco;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private final String tipo;
    private final double valor;
    private final LocalDateTime dataHora;

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + dataHora.format(formatter) + "] " + tipo + ": R$ " + String.format("%.2f", valor);
    }
}

