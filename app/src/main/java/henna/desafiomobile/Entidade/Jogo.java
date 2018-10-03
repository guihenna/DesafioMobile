package henna.desafiomobile.Entidade;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/*
    Classe para armazenar as informacoes de cada Acao Social
 */
public class Jogo implements Serializable {
    /*
        Campos presentes em sociais.json
        A anotacao SerializedName indica que essas variaveis devem
        ser ligadas aos campos dos objetos Json de mesmo nome.
     */
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("trailer")
    private String trailer;

    @SerializedName("platforms")
    private List<String> platforms;

    /*
        Construtor
     */
    public Jogo(int id, String name, String image, String release_date, String trailer, List<String> platforms) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.release_date = release_date;
        this.trailer = trailer;
        this.platforms = platforms;
    }

    /*
        Getters e Setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public String getPlatformsComma() {
        StringBuilder csvBuilder = new StringBuilder();

        for(String plat : platforms){
            csvBuilder.append(plat);
            csvBuilder.append(", ");
        }

        String csv = csvBuilder.toString();

        csv = csv.substring(0, csv.length() - ", ".length());

        return csv;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }
}
