package br.ufrn.imd.mobile.imdmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.ufrn.imd.mobile.imdmarket.R;
import br.ufrn.imd.mobile.imdmarket.domain.Produto;

public class ProdutoAdapter extends ArrayAdapter<Produto> {
    public ProdutoAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Produto produto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.produto, parent, false);
        }

        TextView produtoNome = convertView.findViewById(R.id.produto_nome);
        produtoNome.setText(produto.getNome());

        TextView produtoDescricao = convertView.findViewById(R.id.produto_descricao);
        produtoDescricao.setText(produto.getDescricao());

        return convertView;
    }
}
