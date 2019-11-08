package com.inkeox.area11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

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

    /**
     * onCreate
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_entrainement);

        // On utilise notre Adapter pour la liste des exos
        listViewExercices = findViewById(R.id.listViewExercices);
        List<Exercice> exercices = genererExercices();
        ExerciceAdapter adapter = new ExerciceAdapter(CreerEntrainementActivity.this, exercices);
        listViewExercices.setAdapter(adapter);

        // On définit les paramètres d'un entrainement par défaut
        ( (EditText) findViewById(R.id.entrainement_preparation_temps) ).setText(String.valueOf(entrainement.getPreparationTemps()));
        ( (EditText) findViewById(R.id.entrainement_sequence_repetitions) ).setText(String.valueOf(entrainement.getSequenceRepetitions()));
        ( (EditText) findViewById(R.id.entrainement_sequence_repos_temps) ).setText(String.valueOf(entrainement.getSequenceReposTemps()));

        // On récupère certains éléments
        closePopupBtn = findViewById(R.id.ib_close);
        layoutCreerEntrainement = findViewById(R.id.layoutCreerEntrainement);
    }


    /**
     * Génère des exercices par défaut
     * @return List<Exercice> exercices
     */
    private List<Exercice> genererExercices() {
        entrainement.addExercice(new Exercice("Pompes", "nn", 10, 10));
        entrainement.addExercice(new Exercice("Crunch", "nn", 15, 15));
        entrainement.addExercice(new Exercice("Pas croisés", "nn", 10, 18));
        entrainement.addExercice(new Exercice("Pas croisés", "nn", 10, 18));
        entrainement.addExercice(new Exercice("Pas croisés", "nn", 10, 18));
        entrainement.addExercice(new Exercice("Pas croisés", "nn", 10, 18));
        entrainement.addExercice(new Exercice("Pas croisés", "nn", 10, 18));
        return entrainement.getExercices();
    }

    /**
     * Ouvre un pop-up window pour ajouter un exercice
     * @param view vue
     */
    public void ajouterExercice(View view) {

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_conception_exercice,null);

        closePopupBtn = (ImageButton) customView.findViewById(R.id.ib_close);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(layoutCreerEntrainement, Gravity.CENTER, 0, 0);

        //close the popup window on button click
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
        EditText entrainement_preparation_temps = (EditText) findViewById(R.id.entrainement_preparation_temps);
        EditText entrainement_sequence_repetitions = (EditText) findViewById(R.id.entrainement_sequence_repetitions);
        EditText entrainement_sequence_repos_temps = (EditText) findViewById(R.id.entrainement_sequence_repos_temps);

        entrainement.setPreparationTemps(Integer.parseInt(entrainement_preparation_temps.getText().toString()));
        entrainement.setSequenceRepetitions(Integer.parseInt(entrainement_sequence_repetitions.getText().toString()));
        entrainement.setSequenceReposTemps(Integer.parseInt(entrainement_sequence_repos_temps.getText().toString()));


        Intent intent = new Intent(this, LancerEntrainementActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("entrainement", entrainement);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
