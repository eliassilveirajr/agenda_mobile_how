package app.melhoroftheworld.agenda.tarefa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.melhoroftheworld.agenda.R;
import app.melhoroftheworld.agenda.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etDescricao;
    private EditText etPeriodo;

    public AdicionarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tarefa_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editText_nome_tarefa);
        etDescricao = v.findViewById(R.id.editText_descricao_tarefa);
        etPeriodo = v.findViewById(R.id.editText_periodo_tarefa);

        Button btnSalvar = v.findViewById(R.id.button_salvar_tarefa);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarTarefa();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    private void adicionarTarefa() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome da tarefa", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição da tarefa", Toast.LENGTH_LONG).show();
        } else if (etPeriodo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o periodo da tarefa", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Tarefa t = new Tarefa();
            t.setNome(etNome.getText().toString());
            t.setDescricao(etDescricao.getText().toString());
            t.setPeriodo(etPeriodo.getText().toString());
            databaseHelper.createTarefa(t);
            Toast.makeText(getActivity(), "Tarefa salva!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tarefa, new ListarFragment()).commit();
        }
    }
}