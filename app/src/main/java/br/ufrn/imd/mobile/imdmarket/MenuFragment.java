package br.ufrn.imd.mobile.imdmarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import br.ufrn.imd.mobile.imdmarket.produto.AlterarProdutoFragment;
import br.ufrn.imd.mobile.imdmarket.produto.CadastrarProdutoFragment;
import br.ufrn.imd.mobile.imdmarket.produto.DeletarProdutoFragment;
import br.ufrn.imd.mobile.imdmarket.produto.ListarProdutoFragment;

public class MenuFragment extends Fragment {

    Button cadastrarProdutoBtn, listarProdutosBtn, alterarProdutoBtn, deletarProdutoBtn;

    public MenuFragment() {}

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
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        // Deixando botão Home visível apenas nas telas fora do Login e Menu
        this.getActivity().findViewById(R.id.back_button).setVisibility(Button.INVISIBLE);

        cadastrarProdutoBtn = view.findViewById(R.id.cadastrar_produto_btn);
        listarProdutosBtn = view.findViewById(R.id.listar_button);
        alterarProdutoBtn = view.findViewById(R.id.alterar_produto_btn);
        deletarProdutoBtn = view.findViewById(R.id.deletar_produto_btn);

        cadastrarProdutoBtn.setOnClickListener(event -> {
            renderFragment(new CadastrarProdutoFragment());
        });

        listarProdutosBtn.setOnClickListener(event -> {
            renderFragment(new ListarProdutoFragment());
        });

        alterarProdutoBtn.setOnClickListener(event -> {
            renderFragment(new AlterarProdutoFragment());
        });

        deletarProdutoBtn.setOnClickListener(event -> {
            renderFragment(new DeletarProdutoFragment());
        });

        return view;
    }

    private void renderFragment(Fragment fragment) {
        FragmentTransaction fragTransaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.app_frame, fragment);
        fragTransaction.commit();
    }
}