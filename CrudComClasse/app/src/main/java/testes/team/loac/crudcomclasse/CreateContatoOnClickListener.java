package testes.team.loac.crudcomclasse;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Xoi on 25/04/2018.
 */

public class CreateContatoOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.contato_form, null, false);

        final EditText editTextContatoNome = (EditText) formElementsView.findViewById(R.id.editTextContatoNome);
        final EditText editTextContatoEmail = (EditText) formElementsView.findViewById(R.id.editTextContatoEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Criar Contato")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String contatoNome = editTextContatoNome.getText().toString();
                                String contatoEmail = editTextContatoEmail.getText().toString();

                                Contato contato = new Contato();
                                contato.setNome(contatoNome);
                                contato.setEmail(contatoEmail);

                                boolean criadoComSucesso = new ContatoController(context).create(contato);

                                if(criadoComSucesso){
                                    Toast.makeText(context, "Contato incluído com sucesso.", Toast.LENGTH_SHORT).show();
                                    MainActivity m = (MainActivity) getRequiredActivity(formElementsView);
                                    m.contadorDeRegistros();
                                    m.atualizarListaDeContatos();
                                }
                                else{
                                    Toast.makeText(context, "Nã foi possível incluir contato.", Toast.LENGTH_SHORT).show();
                                }

                                dialog.cancel();
                            }
                        }).show();

    }

    private Activity getRequiredActivity(View req_view) {
        Context context = req_view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
}
