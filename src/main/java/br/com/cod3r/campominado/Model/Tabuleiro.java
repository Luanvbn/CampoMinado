package br.com.cod3r.campominado.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador {

    private final int linhas;
    private final int colunas;
    private final int minas;

    private final List<Campo> campos = new ArrayList<>();
    private final List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void paraCadaCampo(Consumer<Campo> funcao){
        campos.forEach(funcao);
    }


    public void registrarObservador(Consumer<ResultadoEvento> observador){
        observadores.add(observador);
    }

    private void notificarObservadores(boolean resultado){
        observadores.stream()
                .forEach(o -> o.accept(new ResultadoEvento(resultado)));
    }

    public void abrir(int linha, int coluna) {
        try {
            campos.parallelStream()
                    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(Campo::abrir);
        } catch (Exception e) {
            //FIXME Ajustar a implementação do método abrir.
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }

    public void altenarmarcacao(int linha, int coluna) {
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(Campo::altenarmarcacao);
    }


    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Campo campo = new Campo(linha,coluna);
                campo.registrarObservador(this);
                campos.add(campo);
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.AdicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minada = Campo::isMinado;
        do {
            int aleatorio = (int) (Math.random() * campos.size());
            minasArmadas = campos.stream().filter(minada).count();
            campos.get(aleatorio).minar();
        } while (minasArmadas < minas);
    }

    public boolean objetivosAlcancado() {
        return campos.stream().allMatch(Campo::objetivoAlcancado);
    }

    public void reiniciar() {
        campos.forEach(Campo::reiniciar);
        sortearMinas();
    }

    @Override
    public void eventoOcorreu(Campo c, CampoEvento e) {
        if(e == CampoEvento.EXPLODIR){
            mostrarMinas();
            System.out.println("Perdeu... :");
            notificarObservadores(false);
        } else if (objetivosAlcancado()){
            System.out.println("Ganhou... :D");
            notificarObservadores(true);
        }
    }

    private void mostrarMinas(){

        campos.stream()
                .filter(c -> c.isMinado())
                .filter(c -> !c.isMarcado())
                .forEach(c -> c.setAberto(true));
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
}
