package com.inkeox.area11.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.inkeox.area11.Model.Adapter.ExerciceAdapter;
import com.inkeox.area11.Model.Database.DatabaseClient;
import com.inkeox.area11.Model.Entity.Entrainement;
import com.inkeox.area11.Model.Entity.Exercice;
import com.inkeox.area11.Model.Utils.ToastNotification;
import com.inkeox.area11.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CreerEntrainementActivity extends AppCompatActivity {

    // Model
    Entrainement entrainement = new Entrainement();
    ListView listViewExercices;

    // View
    ImageButton closePopupBtn;
    PopupWindow popupWindow;
    ConstraintLayout layoutCreerEntrainement;
    EditText nomEntrainement;
    EditText preparationTemps;
    EditText nbSequences;
    EditText reposSequence;

    // Data
    private DatabaseClient db;

    /**
     * onCreate
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_entrainement);

        db = DatabaseClient.getInstance(getApplicationContext());

        // On utilise notre Adapter pour la liste des exos
        listViewExercices = findViewById(R.id.listViewExercices);
        List<Exercice> exercices = genererExercices();
        ExerciceAdapter adapter = new ExerciceAdapter(CreerEntrainementActivity.this, exercices);
        listViewExercices.setAdapter(adapter);

        // On prépare nos vues
        nomEntrainement = findViewById(R.id.entrainement_nom);
        preparationTemps = findViewById(R.id.entrainement_preparation_temps);
        nbSequences = findViewById(R.id.entrainement_sequence_repetitions);
        reposSequence = findViewById(R.id.entrainement_sequence_repos_temps);

        // On définit certains paramètres d'un entrainement par défaut
        preparationTemps.setText(String.valueOf(entrainement.getPreparationTemps()));
        nbSequences.setText(String.valueOf(entrainement.getSequenceRepetitions()));
        reposSequence.setText(String.valueOf(entrainement.getSequenceReposTemps()));

        // On récupère certains éléments
        closePopupBtn = findViewById(R.id.ib_close);
        layoutCreerEntrainement = findViewById(R.id.layoutCreerEntrainement);
    }


    /**
     * Génère des exercices par défaut
     * @return List<Exercice> exercices
     */
    private List<Exercice> genererExercices() {
        entrainement.addExercice(new Exercice(0, "Pompes", "course", 5, 2));
        entrainement.addExercice(new Exercice(0, "Crunch", "etirements", 4, 3));
        return entrainement.getExercices();
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
        ExerciceAdapter adapter = new ExerciceAdapter(CreerEntrainementActivity.this, entrainement.getExercices());
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
                    ExerciceAdapter adapter = new ExerciceAdapter(CreerEntrainementActivity.this, entrainement.getExercices());
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

            @Override
            protected Void doInBackground(Entrainement... params) {
                // Insertion de l'entrainement
                DatabaseClient.getAppDatabase().entrainementDAO().insert(params[0]);

                int lastId = DatabaseClient.getAppDatabase().entrainementDAO().getLastId();
                entrainement.setId(lastId);

                // Insertion des exercices de l'entrainement
                for (Exercice exercice: entrainement.getExercices()) {
                    exercice.setIdEntrainement(lastId);
                    DatabaseClient.getAppDatabase().exerciceDAO().insert(exercice);
                }

                return null;
            }

            protected void onPostExecute(Void param) {
                onBackPressed();
            }
        }

        UpdateEntrainements ue = new UpdateEntrainements();
        ue.execute(entrainement);
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
