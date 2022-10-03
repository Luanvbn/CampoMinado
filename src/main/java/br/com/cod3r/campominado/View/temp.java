package br.com.cod3r.campominado.View;

import br.com.cod3r.campominado.Model.Tabuleiro;

public class temp {

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);

        tabuleiro.registrarObservador(e->{
            if (e.isGanhou()){
                System.out.println("ganhou");
            } else {
                System.out.println("perdeu");
            }

        });

         tabuleiro.altenarmarcacao(0, 0);
        tabuleiro.altenarmarcacao(0, 1);
        tabuleiro.altenarmarcacao(0, 2);
        tabuleiro.altenarmarcacao(1, 0);
        tabuleiro.altenarmarcacao(1, 1);
        tabuleiro.altenarmarcacao(1, 2);
        tabuleiro.altenarmarcacao(2, 0);
        tabuleiro.altenarmarcacao(2, 1);
        tabuleiro.abrir(2, 2);

    }
}
