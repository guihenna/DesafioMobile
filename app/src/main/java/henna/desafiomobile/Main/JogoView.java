package henna.desafiomobile.Main;

import java.util.List;

import henna.desafiomobile.Entidade.Jogo;

public interface JogoView {
    /*
        Atualiza a lista com a lista montada a partir do Json
     */
    public void atualizaLista(List<Jogo> lista);

    /*
        Mensagens de Erro
     */
    public void mensagemDeErro(String mensagem);

    public String getTextFromR(int id);

    /*
        Seta a lista com o conteudo do BD
     */
    public void retornaBD();

    /*
        Funcoes de controle da ProgressBar
     */
    public void initProgressBar();
    public void stopProgressBar();

    /*
        Tratamento do clique em um item
     */
    public void cliqueRecycler();
}