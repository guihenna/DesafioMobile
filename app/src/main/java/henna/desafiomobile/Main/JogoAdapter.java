package henna.desafiomobile.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import henna.desafiomobile.Entidade.Jogo;
import henna.desafiomobile.R;

public class JogoAdapter extends RecyclerView.Adapter<JogoAdapter.JogoViewHolder> {

    OnRecyclerItemClick onRecyclerItemClick;

    public class JogoViewHolder extends RecyclerView.ViewHolder{

        /*
            Bindings
         */
        @BindView(R.id.as_image)
        ImageView imgJogo;

        @BindView(R.id.as_name)
        TextView txtNome;

        public JogoViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /*
            Tratamento do clique, pegando posicao e a View
         */
        @OnClick(R.id.item_recycler)
        void onItemClick(View view){
            if(onRecyclerItemClick != null){
                onRecyclerItemClick.onClick(view, getAdapterPosition());
            }
        }
    }

    private List<Jogo> lista;
    private Context context;

    /*
        Construtor do Adaptador
     */
    public JogoAdapter(List<Jogo> lista, Context context){
        this.lista = lista;
        this.context = context;
    }

    @Override
    public JogoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.jogo_view, parent, false);
        JogoViewHolder jogoViewHolder = new JogoViewHolder(layout);
        return jogoViewHolder;
    }

    @Override
    public void onBindViewHolder(final JogoViewHolder holder, final int position) {
        Jogo jogo = lista.get(position);
        holder.txtNome.setText(jogo.getName());

        /*
            Configura a imagem, sem corta-la
         */
        Picasso.with(context)
                .load(jogo.getImage())
                .fit()
                .into(holder.imgJogo);
    }

    /*
        Retorna a quantidade de itens
     */
    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnRecyclerItemClick(OnRecyclerItemClick onRecyclerItemClick){
        this.onRecyclerItemClick = onRecyclerItemClick;
    }
}