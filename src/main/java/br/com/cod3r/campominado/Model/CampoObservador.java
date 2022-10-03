package br.com.cod3r.campominado.Model;

@FunctionalInterface
public interface CampoObservador {

    public void eventoOcorreu(Campo c, CampoEvento e);
}
