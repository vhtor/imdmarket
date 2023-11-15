package br.ufrn.imd.mobile.imdmarket.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrn.imd.mobile.imdmarket.R;
import br.ufrn.imd.mobile.imdmarket.utils.ValidatorUtils;

public class CadastrarProdutoFragment extends Fragment {

    TextView codigoInput, nomeInput, descricaoInput, estoqueInput;

    public CadastrarProdutoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cadastrar_produto_fragment, container, false);

        codigoInput = view.findViewById(R.id.codigo_produto_input);
        nomeInput = view.findViewById(R.id.nome_produto_input);
        descricaoInput = view.findViewById(R.id.descricao_produto_input);
        estoqueInput = view.findViewById(R.id.estoque_produto_input);

        // Capturar o evento de clique no botão de salvar
        Button loginBtn = view.findViewById(R.id.salvar_button);
        loginBtn.setOnClickListener(event -> {
            boolean cadastroValido = validate(
                    codigoInput.getText().toString(),
                    nomeInput.getText().toString(),
                    descricaoInput.getText().toString(),
                    estoqueInput.getText().toString()
            );

            if (cadastroValido) {
                cadastrarProduto();
            }
        });

        return view;
    }

    private void cadastrarProduto() {
    }

    public boolean validate(String... inputs) {
        if (ValidatorUtils.anyEmpty(inputs)) {
            Toast.makeText(this.getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        };

        return true;
    }
}