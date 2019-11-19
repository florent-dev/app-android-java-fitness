package com.inkeox.area11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.inkeox.area11.Database.DatabaseClient;
import com.inkeox.area11.Model.Entrainement;
import com.inkeox.area11.Model.Exercice;
import com.inkeox.area11.Model.ExerciceAdapter;

import java.util.List;

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

        // On définit les paramètres d'un entrainement par défaut
        nomEntrainement.setText(String.valueOf(entrainement.getNom()));
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
        entrainement.addExercice(new Exercice(0, "Pas croisés", "course", 4, 2));
        return entrainement.getExercices();
    }

    /**
     * Ouvre un pop-up window pour ajouter un exercice
     * @param view vue
     */
    public void ajouterExercice(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_conception_exercice,null);

        closePopupBtn = customView.findViewById(R.id.ib_close);

        // Instancier la popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Afficher la popup window
        popupWindow.showAtLocation(layoutCreerEntrainement, Gravity.CENTER, 0, 0);

        // Fermer la popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * Lance l'activité LancerEntrainement pour tester celui en cours de création
     * @param view vue
     */
    public void testerEntrainement(View view) {
        updateEntrainementDatas();
        Intent intent = new Intent(this, LancerEntrainementActivity.class);
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
     * @param view vue
     */
    public void enregisterEntrainement(View view) {
        updateEntrainementDatas();

        class UpdateEntrainements extends AsyncTask<Entrainement, Void, Void> {

            @Override
            protected Void doInBackground(Entrainement... params) {
                // Insertion de l'entrainement
                DatabaseClient.getAppDatabase().entrainementDAO().insert(params[0]);

                int lastId = DatabaseClient.getAppDatabase().entrainementDAO().getLastId();
                entrainement.setId(lastId);
                Log.d("DB_LAST_ID", Integer.toString(lastId));

                // Insertion des exercices de l'entrainement
                for (Exercice exercice: entrainement.getExercices()) {
                    exercice.setIdEntrainement(lastId);
                    DatabaseClient.getAppDatabase().exerciceDAO().insert(exercice);
                }

                return null;
            }
        }

        UpdateEntrainements ue = new UpdateEntrainements();
        ue.execute(entrainement);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
