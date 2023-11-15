package br.ufrn.imd.mobile.imdmarket.produto;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import br.ufrn.imd.mobile.imdmarket.MenuFragment;
import br.ufrn.imd.mobile.imdmarket.R;
import br.ufrn.imd.mobile.imdmarket.database.BancoProdutosAdmin;
import br.ufrn.imd.mobile.imdmarket.utils.ValidatorUtils;

public class AlterarProdutoFragment extends Fragment {

    TextView codigoInput, nomeInput, descricaoInput, estoqueInput;

    public AlterarProdutoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alterar_produto_fragment, container, false);

        codigoInput = view.findViewById(R.id.codigo_produto_input);
        nomeInput = view.findViewById(R.id.nome_produto_input);
        descricaoInput = view.findViewById(R.id.descricao_produto_input);
        estoqueInput = view.findViewById(R.id.estoque_produto_input);

        // Capturar o evento de clique no botão de alterar
        Button loginBtn = view.findViewById(R.id.alterar_button);
        loginBtn.setOnClickListener(event -> {
            boolean formularioValido = validate(
                    codigoInput.getText().toString(),
                    nomeInput.getText().toString(),
                    descricaoInput.getText().toString(),
                    estoqueInput.getText().toString()
            );

            if (formularioValido) {
                alterarProduto();
            }
        });

        return view;
    }

    private void alterarProduto() {
        BancoProdutosAdmin admin = new BancoProdutosAdmin(this.getActivity(), "bancoProdutos", null, 1);
        SQLiteDatabase banco = admin.getWritableDatabase();

        String codigo = codigoInput.getText().toString();
        String whereSql = "codigo = " + codigo;

        ContentValues registroSalvar = new ContentValues();
        registroSalvar.put("codigo", codigoInput.getText().toString());
        registroSalvar.put("nome", nomeInput.getText().toString());
        registroSalvar.put("descricao", descricaoInput.getText().toString());
        registroSalvar.put("estoque", estoqueInput.getText().toString());
        banco.update("produtos", null, whereSql, null);
        banco.close();

        Toast.makeText(this.getActivity(), "Produto alterado com sucesso", Toast.LENGTH_SHORT).show();
        goToMenu();
    }

    public boolean validate(String... inputs) {
        if (ValidatorUtils.anyEmpty(inputs)) {
            Toast.makeText(this.getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        };

        return true;
    }

    public void goToMenu() {
        FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.app_frame, new MenuFragment());
        fragTransaction.commit();
    }
}