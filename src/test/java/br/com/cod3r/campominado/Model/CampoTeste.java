package br.com.cod3r.campominado.Model;

import br.com.cod3r.campominado.Exception.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoRealDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.AdicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia1Direita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.AdicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia1Emcima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.AdicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia1Embaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.AdicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.AdicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.AdicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.altenarmarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.altenarmarcacao();
        campo.altenarmarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNãoMinadoNãoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNãoMinadoMarcado() {
        campo.altenarmarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNãoMarcado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.minar();
        campo.altenarmarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirComVizinhos1() {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.AdicionarVizinho(campo11);
        campo.AdicionarVizinho(campo22);

        campo.abrir();


        assertTrue(campo22.isAberto() && campo11.isAberto());

    }

    @Test
    void testeAbrirComVizinhos2() {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);
        Campo campo22 = new Campo(2, 2);

        campo12.minar();

        campo22.AdicionarVizinho(campo11);
        campo22.AdicionarVizinho(campo12);

        campo.AdicionarVizinho(campo22);
        campo.abrir();


        assertTrue(campo22.isAberto() && campo11.isFechado());

    }

    @Test
    void testeGetLinhaEColuna() {
        Campo campo = new Campo(1, 3);
        int linha = campo.getLinha();
        int coluna = campo.getColuna();

        assertEquals(1, linha);
        assertEquals(3, coluna);
    }

    @Test
    void testeObjetivoAlcancadoAbertoNãoMinado() {
        Campo campo = new Campo(1, 3);

        campo.abrir();

        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancado2AbertoMinado() {
        Campo campo = new Campo(1, 3);

        campo.abrir();
        campo.minar();

        assertFalse(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancado3ProtegidoNãoMinado() {
        Campo campo = new Campo(1, 3);

        campo.altenarmarcacao();

        assertFalse(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancado4ProtegidoMinado() {
        Campo campo = new Campo(1, 3);

        campo.altenarmarcacao();
        campo.minar();

        assertFalse(campo.objetivoAlcancado());
    }
}

