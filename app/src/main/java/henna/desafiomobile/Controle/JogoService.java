package henna.desafiomobile.Controle;

import retrofit2.Call;
import retrofit2.http.GET;
import henna.desafiomobile.Entidade.JogoLista;

/*
    Interface para chamada do metodo GET
 */
public interface JogoService {

    /*
        Chamada para pegar o games.json e transforma-lo em uma lista
        O GET vai pegar o baseURL para juntar com a String passada
     */
    @GET("s/1b7jlwii7jfvuh0/games")
    Call<JogoLista> getJogos();
}