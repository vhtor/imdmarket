package br.ufrn.imd.mobile.imdmarket.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.ufrn.imd.mobile.imdmarket.R;
import br.ufrn.imd.mobile.imdmarket.domain.Produto;

public class ListarProdutoFragment extends Fragment {

    ListView produtosListView;
    List<Produto> produtos = new ArrayList<>();

    public ListarProdutoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.listar_produto_fragment, container, false);

        produtosListView = view.findViewById(R.id.produtos_list_view);

        return view;
    }

    private void popularListaProdutos() {

    }
}