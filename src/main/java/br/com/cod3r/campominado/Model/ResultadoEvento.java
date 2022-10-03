package br.com.cod3r.campominado.Model;

public class ResultadoEvento {

    private final boolean ganhou;

    public boolean isGanhou() {
        return ganhou;
    }

    public ResultadoEvento(boolean ganhou) {
        this.ganhou = ganhou;
    }
}
