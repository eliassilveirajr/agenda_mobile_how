package app.melhoroftheworld.agenda.evento;

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
    private EditText etData;
    private EditText etHora;
    private DatabaseHelper databaseHelper;
    private Evento e;

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
        View v = inflater.inflate(R.layout.evento_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_evento);
        etDescricao = v.findViewById(R.id.editText_descricao_evento);
        etData = v.findViewById(R.id.editText_data_evento);
        etHora = v.findViewById(R.id.editText_hora_evento);

        Bundle b = getArguments();
        int id_evento = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        e = databaseHelper.getByIdEvento(id_evento);

        etNome.setText(e.getNome());
        etDescricao.setText(e.getDescricao());
        etData.setText(e.getData_evento());
        etHora.setText(e.getHora());

        Button btnEditar = v.findViewById(R.id.button_editar_evento);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_evento);
            }
        });
        Button btnExcluir = v.findViewById(R.id.button_excluir_evento);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.excluir_evento_mensagem);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_evento);
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
            Toast.makeText(getActivity(), "Por favor, informe o nome do evento", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição do evento", Toast.LENGTH_LONG).show();
        } else if (etData.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a data do evento", Toast.LENGTH_LONG).show();
        } else {
            e = new Evento();
            e.setId(id);
            e.setNome(etNome.getText().toString());
            e.setDescricao(etDescricao.getText().toString());
            e.setData_evento(etData.getText().toString());
            databaseHelper.updateEvento(e);
            Toast.makeText(getActivity(), "Evento atualizado", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_evento, new ListarFragment()).commit();
        }
    }

    private void excluir (int id) {
        e = new Evento();
        e.setId(id);
        databaseHelper.deleteEvento(e);
        Toast.makeText(getActivity(), "Evento excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_evento, new ListarFragment()).commit();
    }
}
