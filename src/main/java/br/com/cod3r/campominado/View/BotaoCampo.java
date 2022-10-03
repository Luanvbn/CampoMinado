package br.com.cod3r.campominado.View;

import br.com.cod3r.campominado.Model.Campo;
import br.com.cod3r.campominado.Model.CampoEvento;
import br.com.cod3r.campominado.Model.CampoObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

    private final Color BG_PADRAO =  new Color(89, 88, 88);
    private final Color BG_MARCAR =  new Color(8, 161, 222);
    private final Color BG_EXPLOSAO =  new Color(201, 32, 36);
    private final Color TXT_VERDE =  new Color(3, 152, 3);
    private Campo campo;

    public BotaoCampo(Campo campo){
        this.campo = campo;
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        setOpaque(true);
        addMouseListener(this);
        campo.registrarObservador(this);

    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento){
        switch (evento){
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            case default:
                aplicarEstiloPadrao();

        }
    }

    private void aplicarEstiloPadrao() {
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
    }

    private void aplicarEstiloExplodir() {
        setBackground(BG_EXPLOSAO);
        setForeground(Color.white);
        setText("X");
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_MARCAR);
        setForeground(Color.black);
        setText("M");
    }

    private void aplicarEstiloAbrir() {

        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if(campo.isMinado()){
            setBackground(BG_EXPLOSAO);
            return;
        }
        setBackground(BG_PADRAO);
        switch ((int) campo.minasNaVizinhanca()){
            case 1:
                setForeground(TXT_VERDE);
                break;
            case 2:
                setForeground(Color.blue);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
                case 4:
                case 5:
                case 6:
                    setForeground(Color.red);
                    break;
                default:
                    setForeground(Color.PINK);
        }
        String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";

        setText(valor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            campo.abrir();
        } else {
            campo.altenarmarcacao();
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
