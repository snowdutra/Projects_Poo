package poo.banco;

import java.util.Objects;
import java.util.UUID;

public abstract class Cliente implements Identificavel { 

    private final String id;
    private final String name;

    public Cliente(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Cliente(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public final String getId() {
        return id;
    }

    @Override
    public final String getName() { 
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s]: %s", id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}