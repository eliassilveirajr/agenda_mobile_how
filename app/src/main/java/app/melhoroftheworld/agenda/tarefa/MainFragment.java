package app.melhoroftheworld.agenda.tarefa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.melhoroftheworld.agenda.R;

public class MainFragment extends Fragment {

    public MainFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tarefa_fragment_main, container, false);


        Button btnAdicionar = v.findViewById(R.id.button_adicionar_tarefa);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tarefa, new AdicionarFragment()).commit();
            }
        });


        Button btnListar = v.findViewById(R.id.button_listar_tarefa);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tarefa, new ListarFragment()).commit();
            }
        });

        return v;
    }
}