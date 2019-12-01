package com.inkeox.area11.Model.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.inkeox.area11.Model.Entity.Exercice;
import com.inkeox.area11.R;

import java.util.List;

public class ExerciceAdapter extends ArrayAdapter<Exercice> {

    // exercices est la liste des models à afficher
    public ExerciceAdapter(Context context, List<Exercice> exercices) {
        super(context, 0, exercices);
    }

    /**
     * Récupère la vue avec nos exercices
     * @param position Ordre
     * @param convertView Convertisseur
     * @param parent ViewGroup
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_exercices, parent, false);
        }

        ExerciceViewHolder viewHolder = (ExerciceViewHolder) convertView.getTag();
        if(viewHolder == null) {
            viewHolder = new ExerciceViewHolder();
            viewHolder.nom = convertView.findViewById(R.id.nom);
            viewHolder.temps = convertView.findViewById(R.id.temps);
            viewHolder.icone = convertView.findViewById(R.id.icone);
            viewHolder.removeExerciceButton = convertView.findViewById(R.id.removeExerciceButton);
            convertView.setTag(viewHolder);
        }

        // getItem(position) va récupérer l'item [position] de la List<Exercice> exercices
        Exercice exercice = getItem(position);

        // Il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(exercice.getNom());
        viewHolder.temps.setText((exercice.getTemps() + " secondes"));
        viewHolder.icone.setImageDrawable(new ColorDrawable(Color.parseColor("#575553")));
        viewHolder.removeExerciceButton.setId(exercice.getId());

        return convertView;
    }

    private class ExerciceViewHolder {
        TextView nom;
        TextView temps;
        ImageView icone;
        ImageButton removeExerciceButton;
    }
}