package henna.desafiomobile.Main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import henna.desafiomobile.Controle.BancoDeDados;
import henna.desafiomobile.Detalhe.DetalheActivity;
import henna.desafiomobile.Entidade.Jogo;
import henna.desafiomobile.R;

public class JogoActivity extends AppCompatActivity implements JogoView {

    /*
        Chave ligada a passagem de parametros entre activities
     */
    public final static String CHAVE = "jogo";

    /*
        Binding
     */
    @BindView(R.id.asRV)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public JogoAdapter jogoAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private JogoPresenter jogoPresenter;
    private List<Jogo> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /*
            Configuracao da ActionBar
         */
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title);

        lista = new ArrayList<Jogo>();

        /*
            Configura o adaptador e o gerenciador de layout do RecyclerView
         */
        recyclerView.setAdapter(new JogoAdapter(lista, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
            Adiciona decoracao - Linha entre os RecyclerViews
         */
        dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                LinearLayoutManager.VERTICAL
        );
        recyclerView.addItemDecoration(dividerItemDecoration);

        /*
            Variavel acessa os dados do aparelho e verifica se ele esta conectado a internet
         */
        boolean online = estaOnline();

        jogoPresenter = new JogoPresenter(this);
        jogoPresenter.CarregarInfo(online);
    }

    /*
        Retorna se o aparelho esta conectado a internet.
        Necessario fornecer permissao pelo AndroidManifest
     */
    public boolean estaOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return (netInfo != null && netInfo.isConnectedOrConnecting());
    }

    @Override
    public void retornaBD(){
        stopProgressBar();

        /*
            Carrega a lista vinda do Banco de Dados
         */
        BancoDeDados bd = new BancoDeDados(this);
        lista = bd.getLista();

        /*
            Verificacao para saber se o BD possui algum elemento
         */
        jogoPresenter.listaVazia(lista);
    }

    @Override
    public void atualizaLista(List<Jogo> l) {
        /*
            l = Lista que veio do json
         */
        this.lista = l;

        BancoDeDados bd = new BancoDeDados(this);

        /*
            Apagar os dados do BD para poder reescreve-lo
         */
        bd.limparBD();

        for(Jogo jogo: lista) {
            bd.addJogo(jogo);
        }

        /*
            Tratamento do clique em um item
         */
        cliqueRecycler();
    }

    public void cliqueRecycler() {
        jogoAdapter = new JogoAdapter(lista, this);
        jogoAdapter.setOnRecyclerItemClick(new OnRecyclerItemClick() {
            @Override
            public void onClick(View view, int position) {

                /*
                    No clique do elemento, criamos um novo Intent
                 */
                Intent intent = new Intent(JogoActivity.this, DetalheActivity.class);

                /*
                    Passagem de parametros entre Activities
                 */
                intent.putExtra(CHAVE, lista.get(position));

                /*
                    Mudanca de Activity
                 */
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(jogoAdapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    /*
        Mensagens de erro utilizando Toast
     */
    @Override
    public void mensagemDeErro(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    /*
        Funcao gerada automaticamente
     */
    @Override
    public String getTextFromR(int id) {
        return null;
    }

    /*
        Funcoes para controle da barra de progresso
     */
    @Override
    public void initProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
