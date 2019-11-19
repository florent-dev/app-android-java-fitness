package com.inkeox.area11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.inkeox.area11.Database.DatabaseClient;
import com.inkeox.area11.Model.Entrainement;
import com.inkeox.area11.Model.EntrainementAdapter;
import com.inkeox.area11.Database.AppDatabase;

import java.util.List;

public class MesEntrainementsActivity extends AppCompatActivity {

    // Model
    ListView listViewEntrainements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_entrainements);

        DatabaseClient.getInstance(getApplicationContext());
        listViewEntrainements = findViewById(R.id.listViewEntrainements);

        afficherEntrainements();
    }

    public void afficherEntrainements() {
        class GetEntrainements extends AsyncTask<Void, Void, List<Entrainement>> {

            @Override
            protected List<Entrainement> doInBackground(Void... voids) {

                // Liste des entrainements
                List<Entrainement> entrainements = DatabaseClient.getAppDatabase().entrainementDAO().getAll();

                // On alimente les entrainements par leurs exercices
                for (Entrainement entrainement: entrainements) {
                    entrainement.setExercices(DatabaseClient.getAppDatabase().exerciceDAO().getByEntrainementId(entrainement.getId()));
                }

                return entrainements;
            }

            @Override
            protected void onPostExecute(List<Entrainement> entrainements) {
                super.onPostExecute(entrainements);

                Log.d("DB_NB_ENT", Integer.toString(entrainements.size()));

                // On utilise notre Adapter pour la liste des entrainements
                EntrainementAdapter adapter = new EntrainementAdapter(MesEntrainementsActivity.this, entrainements);
                listViewEntrainements.setAdapter(adapter);
            }
        }

        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetEntrainements ge = new GetEntrainements();
        ge.execute();
    }
}