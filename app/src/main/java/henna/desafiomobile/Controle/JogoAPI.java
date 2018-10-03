package henna.desafiomobile.Controle;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import henna.desafiomobile.Entidade.JogoLista;

/*
    Classe API, responsavel por transformar o Json em objetos Jogo
    Referencia: Projeto MoviesRoad - Tópicos 12 (UFSCar - São Carlos)
 */
public class JogoAPI {

    private static JogoAPI instancia;
    private JogoService jogoService;

    public static JogoAPI getInstance(){
        if(instancia == null)
            instancia = new JogoAPI();

        return instancia;
    }

    /*
        Construtor da Classe
     */
    private JogoAPI(){
        Retrofit retrofit = new Retrofit
            .Builder()

            /*
                Configura a Url base para pegar o arquivo do Dropbox
             */
            .baseUrl("https://dl.dropboxusercontent.com/")
            .addConverterFactory(defaultConvertFactory())
            .build();

        this.jogoService = retrofit.create(JogoService.class);
    }

    /*
        Criacao do Gson
     */
    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
            .create();

        return GsonConverterFactory.create(gson);
    }

    /*
        Chamada para o GET da internet, retornando a lista de Jogos
     */
    public Call<JogoLista> getJogos(){
        return jogoService.getJogos();
    }
}
