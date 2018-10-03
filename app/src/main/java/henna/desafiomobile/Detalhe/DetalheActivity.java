package henna.desafiomobile.Detalhe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import henna.desafiomobile.Entidade.Jogo;
import henna.desafiomobile.Main.JogoActivity;
import henna.desafiomobile.R;

public class DetalheActivity extends AppCompatActivity {

    /*
        Bindings
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.as_image)
    ImageView circleImageView;

    @BindView(R.id.as_rel)
    TextView releaseDateJogo;

    @BindView(R.id.as_plat)
    TextView platformsJogo;

    private Jogo jogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        ButterKnife.bind(this);

        /*
            Inicia a variavel jogo com o objeto passado pela outra Activity
         */
        Intent intent = getIntent();
        jogo = (Jogo) intent.getSerializableExtra(JogoActivity.CHAVE);


        /*
            Configura a ActionBar
            Insere o nome do Jogo como titulo
         */
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(jogo.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*
            Carrega a imagem
            Impede que ela seja cortada
         */
        Picasso.with(this)
                .load(jogo.getImage())
                .fit()
                .into(circleImageView);

        /*
            Insere a data de lancamento na TextView
         */
        releaseDateJogo.setText(jogo.getReleaseDate());

        /*
            Insere a lista de plataformas na TextView
         */
        platformsJogo.setText(jogo.getPlatformsComma());
    }

    /*
        Ao clicar no botao, cria-se uma intent que abre o navegador
     */
    @OnClick(R.id.botao)
    public void onClickButtonSite() {
        Uri uri = Uri.parse(jogo.getTrailer());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}