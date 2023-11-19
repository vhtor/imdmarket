package br.ufrn.imd.mobile.imdmarket.produto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.mobile.imdmarket.R;
import br.ufrn.imd.mobile.imdmarket.adapter.ProdutoAdapter;
import br.ufrn.imd.mobile.imdmarket.database.BancoProdutosManager;
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

        // Deixando botão Home visível apenas na telas fora do login e Menu
        this.getActivity().findViewById(R.id.back_button).setVisibility(Button.VISIBLE);

        produtosListView = view.findViewById(R.id.produtos_list_view);
        popularListaProdutos();
        setListaProdutosAdapter();

        // Mostrar detalhes do produto ao clicar em um item da lista
        produtosListView.setOnItemClickListener((adapterView, view1, position, id) -> {
            Produto produto = (Produto) adapterView.getItemAtPosition(position);
            DetalheProdutoFragment produtoDetalhesFragment = new DetalheProdutoFragment();

            Bundle bundle = new Bundle();
            bundle.putString("codigo", produto.getCodigo());
            bundle.putString("nome", produto.getNome());
            bundle.putString("descricao", produto.getDescricao());
            bundle.putInt("estoque", produto.getEstoque());

            produtoDetalhesFragment.setArguments(bundle);

            FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.app_frame, produtoDetalhesFragment);
            fragTransaction.commit();
        });

        return view;
    }

    private void popularListaProdutos() {
        BancoProdutosManager dbManager = new BancoProdutosManager(this.getActivity(), "bancoProdutos", null, 1);
        SQLiteDatabase database = dbManager.getReadableDatabase();

        try (Cursor result = database.rawQuery("SELECT * FROM produtos", null)) {
            if (result.getCount() == 0) {
                return;
            }

            while (result.moveToNext()) {
                Produto produto = new Produto();
                produto.setCodigo(result.getString(0));
                produto.setNome(result.getString(1));
                produto.setDescricao(result.getString(2));
                produto.setEstoque(result.getInt(3));
                produtos.add(produto);
            }
        }
    }

    private void setListaProdutosAdapter() {
        produtosListView.setAdapter(new ProdutoAdapter(this.getActivity(), produtos));
    }
}