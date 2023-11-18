package br.ufrn.imd.mobile.imdmarket.produto;

import static br.ufrn.imd.mobile.imdmarket.utils.CampoUtils.limparCampos;

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
import br.ufrn.imd.mobile.imdmarket.database.BancoProdutosManager;
import br.ufrn.imd.mobile.imdmarket.utils.ValidatorUtils;

public class DeletarProdutoFragment extends Fragment {

    TextView codigoInput;

    public DeletarProdutoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.deletar_produto_fragment, container, false);

        // Deixando botão Home visível apenas na telas fora do login e Menu
        this.getActivity().findViewById(R.id.back_button).setVisibility(Button.VISIBLE);

        codigoInput = view.findViewById(R.id.codigo_produto_input);

        // Capturar o evento de clique no botão de deletar
        Button loginBtn = view.findViewById(R.id.deletar_button);
        loginBtn.setOnClickListener(event -> {
            boolean formValido = validate(codigoInput.getText().toString());

            if (formValido) {
                deletarProduto();
            }
        });

        // Capturar o evento de clique no botão de limpar
        Button limparBtn = view.findViewById(R.id.limpar_button);
        limparBtn.setOnClickListener(event -> {
            limparCampos(codigoInput);
        });

        return view;
    }

    private void deletarProduto() {
        BancoProdutosManager bancoProdutosManager = new BancoProdutosManager(this.getActivity(), "bancoProdutos", null, 1);
        SQLiteDatabase banco = bancoProdutosManager.getWritableDatabase();

        String codigo = codigoInput.getText().toString();
        int qtdApagados = banco.delete("produtos", "codigo = " + codigo, null);
        banco.close();

        limparCampos(codigoInput);

        if (qtdApagados > 0) {
            Toast.makeText(this.getActivity(), "Produto deletado com sucesso", Toast.LENGTH_SHORT).show();
            goToMenu();
        } else {
            Toast.makeText(this.getActivity(), "Produto não encontrado", Toast.LENGTH_SHORT).show();
        }
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