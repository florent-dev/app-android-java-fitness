package com.inkeox.area11.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.inkeox.area11.Model.Adapter.ExerciceAdapter;
import com.inkeox.area11.Model.Database.DatabaseClient;
import com.inkeox.area11.Model.Entity.Entrainement;
import com.inkeox.area11.Model.Entity.Exercice;
import com.inkeox.area11.Model.Utils.ToastNotification;
import com.inkeox.area11.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class EditeurEntrainementActivity extends AppCompatActivity {

    /** Mode création ou édition, création par défaut */
    private int editionMode = 0;

    // Model
    Entrainement entrainement = new Entrainement();
    ListView listViewExercices;

    // View
    ConstraintLayout layoutCreerEntrainement;
    EditText nomEntrainement;
    EditText preparationTemps;
    EditText nbSequences;
    EditText reposSequence;

    // Data
    private DatabaseClient db;

    /**
     * Initialisation de l'entrainement
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_entrainement);

        db = DatabaseClient.getInstance(getApplicationContext());

        // On prépare nos vues
        nomEntrainement = findViewById(R.id.entrainement_nom);
        preparationTemps = findViewById(R.id.entrainement_preparation_temps);
        nbSequences = findViewById(R.id.entrainement_sequence_repetitions);
        reposSequence = findViewById(R.id.entrainement_sequence_repos_temps);

        // Cas de l'édition d'un entrainement existant
        if (getIntent().getExtras() != null) {
            Log.d("DEBUG", "EDITION EXISTANT");
            entrainement = (Entrainement) getIntent().getExtras().getSerializable("entrainement");
            editionMode = 1;
            nomEntrainement.setText(String.valueOf(entrainement.getNom()));
        }

        // On utilise notre Adapter pour la liste des exos
        listViewExercices = findViewById(R.id.listViewExercices);
        ExerciceAdapter adapter = new ExerciceAdapter(EditeurEntrainementActivity.this, entrainement.getExercices());
        listViewExercices.setAdapter(adapter);

        // On définit certains paramètres d'un entrainement
        preparationTemps.setText(String.valueOf(entrainement.getPreparationTemps()));
        nbSequences.setText(String.valueOf(entrainement.getSequenceRepetitions()));
        reposSequence.setText(String.valueOf(entrainement.getSequenceReposTemps()));

        // On récupère certains éléments
        layoutCreerEntrainement = findViewById(R.id.layoutCreerEntrainement);
    }

    /**
     * Déclenche une nouvelle activité pour ajouter un exercice
     * @param view -
     */
    public void ajouterExercice(View view) {
        if (this.entrainement.getExercicesCount() < Entrainement.NB_EXERCICE_MAX) {
            Intent intent = new Intent(this, CreerExerciceActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 1);
        } else {
            ToastNotification.afficher(getApplicationContext(), "Vous ne pouvez pas ajouter autant d'exercices.");
        }
    }

    /**
     * Supprime un exercice
     * @param view -
     */
    public void supprimerExercice(View view) {
        if (this.entrainement.getExercicesCount() > Entrainement.NB_EXERCICE_MIN) {
            entrainement.removeExercice(entrainement.getExercices().get(view.getId()));
        } else {
            ToastNotification.afficher(getApplicationContext(), "Votre entrainement doit avoir au minimum un exercice.");
        }

        // Update de l'adapter
        ExerciceAdapter adapter = new ExerciceAdapter(EditeurEntrainementActivity.this, entrainement.getExercices());
        listViewExercices.setAdapter(adapter);
    }

    /**
     * Récupère l'exercice à ajouter dans l'entrainement
     * @param requestCode -
     * @param resultCode -
     * @param data -
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Récupération et insertion du nouvel entrainement
                if (data != null) {
                    Exercice nouvelExercice = (Exercice) data.getSerializableExtra("exercice");
                    entrainement.addExercice(nouvelExercice);

                    // Update de l'adapter
                    ExerciceAdapter adapter = new ExerciceAdapter(EditeurEntrainementActivity.this, entrainement.getExercices());
                    listViewExercices.setAdapter(adapter);
                }
            }
        }
    }

    /**
     * Lance l'activité LancerEntrainement pour tester celui en cours de création
     * @param view -
     */
    public void testerEntrainement(View view) {
        updateEntrainementDatas();
        Intent intent = new Intent(this, JouerEntrainementActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Bundle bundle = new Bundle();
        bundle.putSerializable("entrainement", entrainement);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Mets à jour l'entrainement avec les données saisies
     */
    public void updateEntrainementDatas() {
        entrainement.setNom(nomEntrainement.getText().toString());
        entrainement.setPreparationTemps(Integer.parseInt(preparationTemps.getText().toString()));
        entrainement.setSequenceRepetitions(Integer.parseInt(nbSequences.getText().toString()));
        entrainement.setSequenceReposTemps(Integer.parseInt(reposSequence.getText().toString()));
    }

    /**
     * Enregistre l'entrainement en base de données
     * @param view -
     */
    public void enregisterEntrainement(View view) {
        updateEntrainementDatas();

        // Obligation de renseigner nom de l'entrainement
        if (entrainement.getNom().equals("")) {
            ToastNotification.afficher(getApplicationContext(), "Le nom de l'entrainement doit être renseigné");
            return;
        }

        @SuppressLint("StaticFieldLeak")
        class UpdateEntrainements extends AsyncTask<Entrainement, Void, Void> {
            private Entrainement entrainement;
            private Integer editionMode;

            UpdateEntrainements(Entrainement entrainement, Integer editionMode) {
                this.entrainement = entrainement;
                this.editionMode = editionMode;
            }

            @Override
            protected Void doInBackground(Entrainement... params) {

                // Si l'entrainement existe on le met à jour avec les exercices
                // Autrement on les insert en BDD
                if (editionMode == 1) {
                    DatabaseClient.getAppDatabase().entrainementDAO().update(entrainement);

                    for (Exercice exercice: entrainement.getExercices()) {
                        DatabaseClient.getAppDatabase().exerciceDAO().update(exercice);
                    }

                } else {
                    DatabaseClient.getAppDatabase().entrainementDAO().insert(entrainement);

                    int lastId = DatabaseClient.getAppDatabase().entrainementDAO().getLastId();
                    entrainement.setId(lastId);

                    for (Exercice exercice : entrainement.getExercices()) {
                        exercice.setIdEntrainement(lastId);
                        DatabaseClient.getAppDatabase().exerciceDAO().insert(exercice);
                    }
                }

                return null;
            }

            protected void onPostExecute(Void param) {
                onBackPressed();
            }
        }

        new UpdateEntrainements(entrainement, editionMode).execute();
    }

    /**
     Retourne à l'activité hiérarchiquement en amont
     * @param view -
     */
    public void retourActivitePrecedente(View view) {
        this.onBackPressed();
    }

    /**
     * Touche retour, on retire l'animation
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
