package app.melhoroftheworld.agenda.evento;

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
    private EditText etData;
    private EditText etHora;

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
        View v = inflater.inflate(R.layout.evento_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editText_nome_evento);
        etDescricao = v.findViewById(R.id.editText_descricao_evento);
        etData = v.findViewById(R.id.editText_data_evento);
        etHora = v.findViewById(R.id.editText_hora_evento);

        Button btnSalvar = v.findViewById(R.id.button_salvar_evento);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarEvento();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    private void adicionarEvento() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do evento", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição do evento", Toast.LENGTH_LONG).show();
        } else if (etData.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a data do evento", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Evento e = new Evento();
            e.setNome(etNome.getText().toString());
            e.setDescricao(etDescricao.getText().toString());
            e.setData_evento(etData.getText().toString());
            e.setHora(etHora.getText().toString());
            databaseHelper.createEvento(e);
            Toast.makeText(getActivity(), "Evento salvo!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_evento, new ListarFragment()).commit();
        }
    }
}