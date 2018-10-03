package henna.desafiomobile.Main;

import java.util.List;

import henna.desafiomobile.Controle.JogoAPI;
import henna.desafiomobile.Entidade.JogoLista;
import henna.desafiomobile.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JogoPresenter {

    private JogoView jogoView;

    /*
        Construtor que recebe uma View
     */
    public JogoPresenter(JogoView jogoView){
        this.jogoView = jogoView;
    }

    /*
        Se o aparelho estiver conectado, vai acessar os dados pelo json
        Senao, vai pegar os dados do BD armazenado no aparelho
     */
    public void CarregarInfo(Boolean conectado){
        if(conectado){
            acessaDados();
        }
        else{
            jogoView.retornaBD();
        }

    }

    /*
        Se essa lista estiver vazia, significa que o BD nao esta carregado no aparelho
     */
    public void listaVazia(List list){
        if(list.size() == 0) {
            jogoView.mensagemDeErro(jogoView.getTextFromR(R.string.erro_bd));
        }

        else{
            jogoView.cliqueRecycler();
        }
    }

    /*
        Acessa os dados atraves do metodo GET e os salva na lista
     */
    public void acessaDados(){
        jogoView.initProgressBar();
        final JogoAPI jogoAPI = JogoAPI.getInstance();

        jogoAPI.getJogos().enqueue(new Callback<JogoLista>() {
            /*
                Se a lista possuir algum elemento, vamos atualizar a lista da View
                Senao, vamos mostrar uma mensagem de erro e nada sera carregado
             */
            @Override
            public void onResponse(Call<JogoLista> call, Response<JogoLista> response) {
                JogoLista jogoLista = response.body();

                if(jogoLista.getJogoLista() != null){
                    jogoView.stopProgressBar();
                    jogoView.atualizaLista(jogoLista.getJogoLista());
                }
                else{
                    jogoView.stopProgressBar();
                    jogoView.mensagemDeErro(jogoView.getTextFromR(R.string.erro_servidor));
                }
            }

            @Override
            public void onFailure(Call<JogoLista> call, Throwable t) {
                jogoView.stopProgressBar();
                jogoView.mensagemDeErro(jogoView.getTextFromR(R.string.erro_servidor));
            }
        });
    }
}
