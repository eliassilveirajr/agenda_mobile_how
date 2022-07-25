package app.melhoroftheworld.agenda.tarefa;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditarFragment extends Fragment {

    private EditText etNome;
    private EditText etDescricao;
    private EditText etPeriodo;
    private DatabaseHelper databaseHelper;
    private Tarefa t;

    public EditarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.tarefa_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_tarefa);
        etDescricao = v.findViewById(R.id.editText_descricao_tarefa);
        etPeriodo = v.findViewById(R.id.editText_periodo_tarefa);

        Bundle b = getArguments();
        int id_tarefa = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        t = databaseHelper.getByIdTarefa(id_tarefa);

        etNome.setText(t.getNome());
        etDescricao.setText(t.getDescricao());
        etPeriodo.setText(t.getPeriodo());

        Button btnEditar = v.findViewById(R.id.button_editar_tarefa);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_tarefa);
            }
        });
        Button btnExcluir = v.findViewById(R.id.button_excluir_tarefa);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.excluir_tarefa_mensagem);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_tarefa);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void editar(int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome da tarefa", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição da tarefa", Toast.LENGTH_LONG).show();
        } else if (etPeriodo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o periodo da tarefa", Toast.LENGTH_LONG).show();
        } else {
            t = new Tarefa();
            t.setId(id);
            t.setNome(etNome.getText().toString());
            t.setDescricao(etDescricao.getText().toString());
            t.setPeriodo(etPeriodo.getText().toString());
            databaseHelper.updateTarefa(t);
            Toast.makeText(getActivity(), "Tarefa atualizada", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tarefa, new app.melhoroftheworld.agenda.tarefa.ListarFragment()).commit();
        }
    }

    private void excluir (int id) {
        t = new Tarefa();
        t.setId(id);
        databaseHelper.deleteTarefa(t);
        Toast.makeText(getActivity(), "Tarefa excluída", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_tarefa, new ListarFragment()).commit();
    }
}
