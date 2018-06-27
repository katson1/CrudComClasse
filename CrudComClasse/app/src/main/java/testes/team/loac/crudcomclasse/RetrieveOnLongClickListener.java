package testes.team.loac.crudcomclasse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Xoi on 25/04/2018.
 */

public class RetrieveOnLongClickListener implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View v) {

        context = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Editar", "Deletar"};

        new AlertDialog.Builder(context).setTitle("Detalhes do Contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if (item == 0){
                            editContatoPeloId(Integer.parseInt(id));
                        }
                        else if (item == 1){
                            // deletar
                        }

                        dialog.dismiss();
                    }
                }).show();


        return false;
    }

    public void editContatoPeloId(final int contatoID){

        Toast.makeText(context, "Editando "+contatoID, Toast.LENGTH_SHORT).show();

        final ContatoController contatoController =
                new ContatoController(context);

        final Contato contato =
                contatoController.buscarPeloId(contatoID);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formContato =
                inflater.inflate(R.layout.contato_form, null, false);

        final EditText editTextContatoNome = (EditText) formContato.findViewById(R.id.editTextContatoNome);
        final EditText editTextContatoEmail = (EditText) formContato.findViewById(R.id.editTextContatoEmail);

        editTextContatoNome.setText(contato.getNome());
        editTextContatoEmail.setText(contato.getEmail());



        new AlertDialog.Builder(context)
                .setView(formContato)
                .setTitle("Editar")
                .setPositiveButton("Atualizar dados",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        }).show();
    }
}
