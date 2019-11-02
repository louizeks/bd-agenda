package br.edu.ifsp.agendasqlite.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.ifsp.agendasqlite.R;
import br.edu.ifsp.agendasqlite.model.Contato;

public class ContatoAdapter
        extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>
        implements Filterable {

    static List<Contato> contatos;
    List<Contato> contatosFiltrados;

    private static ItemClickListener clickListener;


    public void adicionaContatoAdapter(Contato c) {
        contatos.add(0, c);
        Collections.sort(contatos, new Comparator<Contato>() {
            @Override
            public int compare(Contato o1, Contato o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });

        notifyDataSetChanged();
    }

    public void atualizaContatoAdapter(Contato c) {
        contatos.set(contatos.indexOf(c), c);
        notifyItemChanged(contatos.indexOf(c));
    }

    public void apagaContatoAdapter(Contato c) {
        int pos = contatos.indexOf(c);
        contatos.remove(pos);
        notifyItemRemoved(pos);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

    public ContatoAdapter(List<Contato> contatos) {
        this.contatos = contatos;
        this.contatosFiltrados = contatos;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contato_celula, parent, false);

        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {

        holder.nome.setText(contatosFiltrados.get(position).getNome());

        if (contatosFiltrados.get(position).getFavorito() == 1) {
            holder.favorito.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_lock_silent_mode_off, 0);
        }
        else
        {
            holder.favorito.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_lock_silent_mode, 0);
        }
    }

    @Override
    public int getItemCount() {
        return contatosFiltrados.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contatosFiltrados = contatos;
                } else {
                    List<Contato> filteredList = new ArrayList<>();
                    for (Contato row : contatos) {
                        if (row.getNome().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    contatosFiltrados = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contatosFiltrados;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contatosFiltrados = (ArrayList<Contato>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ContatoViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        final TextView nome;
        final Button favorito;

        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome);
            favorito = (Button) itemView.findViewById(R.id.buttonFavorite);
            itemView.setOnClickListener(this);

            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Favoritar(getAdapterPosition(), view.getContext());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition());
        }
    }
    public void Favoritar(int position, Context context) {
        Contato c = contatos.get(position);
        if(c.getFavorito() == 0)
            c.setFavorito(1);
        else
            c.setFavorito(0);

        ContatoDAO dao = new ContatoDAO(context);
        dao.favoritarContato(this.contatosFiltrados.get(position));

        contatos.set(position, c);
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public List<Contato> getContactListenerFiltered() {
        return contatosFiltrados;
    }
}