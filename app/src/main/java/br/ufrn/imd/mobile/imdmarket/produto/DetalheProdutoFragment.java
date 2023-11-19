package br.ufrn.imd.mobile.imdmarket.produto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import br.ufrn.imd.mobile.imdmarket.R;

public class DetalheProdutoFragment extends Fragment {

    View view;
    TextView codigoField, nomeField, descricaoField, estoqueField;

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
        view = inflater.inflate(R.layout.detalhe_produto_fragment, container, false);

        // Preencher os campos com os dados do produto
        Bundle bundle = this.getArguments();
        preencherDados(bundle);

        // Escutando botão de voltar
        view.findViewById(R.id.voltar_button).setOnClickListener(event -> {
            voltarParaListagem();
        });

        return view;
    }

    private void voltarParaListagem() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.app_frame, new ListarProdutoFragment());
        transaction.commit();
    }

    private void preencherDados(Bundle dados) {

        codigoField = view.findViewById(R.id.produto_codigo);
        nomeField = view.findViewById(R.id.produto_nome);
        descricaoField = view.findViewById(R.id.produto_descricao);
        estoqueField = view.findViewById(R.id.produto_estoque);

        if (dados != null) {
            String codigo = dados.getString("codigo");
            String nome = dados.getString("nome");
            String descricao = dados.getString("descricao");
            int estoque = dados.getInt("estoque");

            codigoField.setText(codigo);
            nomeField.setText(nome);
            descricaoField.setText(descricao);
            estoqueField.setText(String.valueOf(estoque));
        }
    }
}