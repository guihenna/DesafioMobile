package henna.desafiomobile.Controle;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import henna.desafiomobile.Entidade.Jogo;
import retrofit2.http.DELETE;

/*
    Classe para controlar o Banco de Dados
    Documentacao: https://goo.gl/4mXuBS
 */
public class BancoDeDados extends SQLiteOpenHelper{

    /*
        Constantes para o Banco de Dados
     */
    public static final String BD_Name = "bd";
    public static final String Table_Name = "jogos";
    public static final String Column1 = "id";
    public static final String Column2 = "name";
    public static final String Column3 = "image";
    public static final String Column4 = "release_date";
    public static final String Column5 = "trailer";
    public static final String Column6 = "platforms";

    /*
        Construtor principal
        ctx vai representar o contexto atual do sistema
     */
    public BancoDeDados(Context ctx) {
        // (Contexto, Nome do BD, Factory, Version)
        super(ctx, BD_Name, null, 1);
    }

    /*
        Construtores Padroes (Gerados automaticamente)
     */
    public BancoDeDados(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public BancoDeDados(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
            CREATE TABLE jogos (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL,
            image TEXT NOT NULL, release_date TEXT NOT NULL, trailer TEXT NOT NULL,
            platforms TEXT NOT NULL)
         */
        sqLiteDatabase.execSQL("CREATE TABLE " + Table_Name + " (" +
        Column1 + " INTEGER PRIMARY KEY NOT NULL, " + Column2 + " TEXT NOT NULL, " +
        Column3 + " TEXT NOT NULL, " + Column4 + " TEXT NOT NULL, " +
        Column5 + " TEXT NOT NULL, " + Column6 + " TEXT NOT NULL)");
    }

    /*
        Ao atualizar o BD, apaga a tabela existente e cria uma nova, com as novas informacoes
        Referencia: https://goo.gl/cKKU56
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Apaga a tabela
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        // Recria a tabela
        onCreate(sqLiteDatabase);
    }

    /*
        Adicionar um Jogo no BD
        Retorna: - true se deu tudo certo
                 - false se nao foi possivel inserir
     */
    public boolean addJogo(Jogo jogo) {

        /*
            Cria um objeto BD para escrita
         */
        SQLiteDatabase sqlBD = getWritableDatabase();
        ContentValues cv = new ContentValues();

        /*
            Nao precisa inserir a primeira coluna, pois a chave vai ser gerada sequencialmente
         */
        cv.put(Column2, jogo.getName());
        cv.put(Column3, jogo.getImage());
        cv.put(Column4, jogo.getReleaseDate());
        cv.put(Column5, jogo.getTrailer());
        cv.put(Column6, jogo.getPlatformsComma());

        long id = sqlBD.insert(Table_Name, null, cv);
        sqlBD.close();

        if(id == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<Jogo> getLista() {
        ArrayList<Jogo> lista = new ArrayList<Jogo>();

        /*
            Seleciona todos os registros ordenados pelos nomes
         */
        String sql = "SELECT * FROM " + Table_Name + " ORDER BY " + Column2;

        /*
            Cria um objeto BD para leitura
         */
        SQLiteDatabase sqlBD = getReadableDatabase();

        /*
            Cria um cursor para passar por todos os registros
         */
        Cursor cursor = sqlBD.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Column1));
                String name = cursor.getString(cursor.getColumnIndex(Column2));
                String image = cursor.getString(cursor.getColumnIndex(Column3));
                String release_date = cursor.getString(cursor.getColumnIndex(Column4));
                String trailer = cursor.getString(cursor.getColumnIndex(Column5));
                String platforms = cursor.getString(cursor.getColumnIndex(Column6));

                List<String> platformsLista = Arrays.asList(platforms.split(", "));

                Jogo nova = new Jogo(id, name, image, release_date, trailer, platformsLista);
                lista.add(nova);
            } while(cursor.moveToNext());
        }
        cursor.close();

        /*System.out.println("Teste BD");
        for(Jogo acao: lista) {
            System.out.println(jogo.getDescription());
        }*/
        return lista;
    }

    /*
        Apaga todos os dados do BD
     */
    public void limparBD() {
        SQLiteDatabase sqlBD = getWritableDatabase();
        // Apaga a tabela
        sqlBD.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        // Recria a tabela
        onCreate(sqlBD);
    }
}
