package thirdParty;

import br.com.davesmartins.graphviewlib.ViewGraph;
import br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion;

public abstract class GeradorImagem {

    public static boolean gerarImagem(String nome) {
        try {
            ViewGraph.generateViewGraphByFrame(nome);
            return true;
        } catch (EGraphViewExcpetion ex) {
            System.out.println("-->" + ex.getMensagem());
            return false;
        }
    }
}
