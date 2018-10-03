package henna.desafiomobile.Entidade;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/*
    Classe criada para ser utilizada no metodo Call
 */
public class JogoLista {

    @SerializedName("games")
    private List<Jogo> jogoLista = null;

    public List<Jogo> getJogoLista() {
        return jogoLista;
    }

    public void setJogoLista(List<Jogo> jogoEntities) {
        this.jogoLista = jogoEntities;
    }

}