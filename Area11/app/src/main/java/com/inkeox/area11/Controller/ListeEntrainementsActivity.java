package com.inkeox.area11.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.inkeox.area11.Model.Database.DatabaseClient;
import com.inkeox.area11.Model.Entity.Entrainement;
import com.inkeox.area11.Model.Adapter.EntrainementAdapter;
import com.inkeox.area11.R;

import java.util.List;

public class ListeEntrainementsActivity extends AppCompatActivity {

    // Model
    ListView listViewEntrainements;
    List<Entrainement> entrainements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_entrainements);

        DatabaseClient.getInstance(getApplicationContext());
        listViewEntrainements = findViewById(R.id.listViewEntrainements);

        afficherEntrainements();
    }

    /**
     * Afficher la liste des entrainements en asynctask
     */
    public void afficherEntrainements() {
        class GetEntrainements extends AsyncTask<Void, Void, List<Entrainement>> {

            @Override
            protected List<Entrainement> doInBackground(Void... voids) {

                // Liste des entrainements
                entrainements = DatabaseClient.getAppDatabase().entrainementDAO().getAll();

                // On alimente les entrainements par leurs exercices
                for (Entrainement entrainement: entrainements) {
                    entrainement.setExercices(DatabaseClient.getAppDatabase().exerciceDAO().getByEntrainementId(entrainement.getId()));
                }

                return entrainements;
            }

            @Override
            protected void onPostExecute(List<Entrainement> entrainements) {
                super.onPostExecute(entrainements);

                // On utilise notre Adapter pour la liste des entrainements
                EntrainementAdapter adapter = new EntrainementAdapter(ListeEntrainementsActivity.this, entrainements);
                listViewEntrainements.setAdapter(adapter);
            }
        }

        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetEntrainements ge = new GetEntrainements();
        ge.execute();
    }

    /**
     * Lancer le compteur
     * @param view view.getId() => directement l'id de l'entrainement
     */
    public void jouerEntrainement(View view) {
        Intent intent = new Intent(this, JouerEntrainementActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        Bundle bundle = new Bundle();
        bundle.putSerializable("entrainement", entrainements.get( (int) view.getId() ));

        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Suppression de l'entrainement
     * @param view -
     */
    public void supprimerEntrainement(View view) {
        Entrainement entrainement = entrainements.get( (int) view.getId() );
        entrainements.remove(entrainement);

        @SuppressLint("StaticFieldLeak")
        class DeleteEntrainement extends AsyncTask<Entrainement, Void, Void> {
            @Override
            protected Void doInBackground(Entrainement... params) {
                DatabaseClient.getAppDatabase().entrainementDAO().delete(params[0]);
                return null;
            }
        }

        DeleteEntrainement de = new DeleteEntrainement();
        de.execute(entrainement);

        // Update de l'adapter
        EntrainementAdapter adapter = new EntrainementAdapter(ListeEntrainementsActivity.this, entrainements);
        listViewEntrainements.setAdapter(adapter);
    }

    /**
     * Edition d'un entrainement
     * @param view -
     */
    public void editerEntrainement(View view) {
        Intent intent = new Intent(this, EditeurEntrainementActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        Bundle bundle = new Bundle();
        bundle.putSerializable("entrainement", entrainements.get( (int) view.getId() ));
        Log.d("EDITER?", entrainements.get( (int) view.getId() ).getNom());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     Retourne à l'activité hiérarchiquement en amont
     * @param view -
     */
    public void retourActivitePrecedente(View view) {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}