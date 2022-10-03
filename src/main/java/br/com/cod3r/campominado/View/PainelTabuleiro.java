package br.com.cod3r.campominado.View;

import br.com.cod3r.campominado.Model.Tabuleiro;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

    public PainelTabuleiro(Tabuleiro tabuleiro){

        setLayout(new GridLayout(
                tabuleiro.getLinhas(), tabuleiro.getColunas()));


        tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));


            tabuleiro.registrarObservador(e -> {
                if (e.isGanhou()) {
                   int text = JOptionPane.showConfirmDialog(this, "Você ganhou \n Deseja Jogar Novamente?");
                   if (text == 0){
                       tabuleiro.reiniciar();
                   } else {
                      System.exit(0);
                   }
                } else {
                    int text = JOptionPane.showConfirmDialog(this, "Você Perdeu \n Deseja Jogar Novamente?");
                    if (text == 0){
                        tabuleiro.reiniciar();
                    } else {
                        System.exit(0);
                    }
                }
            });


    }
}
