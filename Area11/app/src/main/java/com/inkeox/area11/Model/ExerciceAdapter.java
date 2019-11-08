package com.inkeox.area11.Model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_entrainement_exercices,parent, false);
        }

        ExerciceViewHolder viewHolder = (ExerciceViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ExerciceViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.nom);
            viewHolder.repetitions = (TextView) convertView.findViewById(R.id.repetitions);
            viewHolder.icone = (ImageView) convertView.findViewById(R.id.icone);
            convertView.setTag(viewHolder);
        }

        // getItem(position) va récupérer l'item [position] de la List<Exercice> exercices
        Exercice exercice = getItem(position);

        // il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(exercice.getNom());
        viewHolder.repetitions.setText((exercice.getRepetitions() + " répétitions"));
        viewHolder.icone.setImageDrawable(new ColorDrawable(Color.parseColor("#575553")));

        return convertView;
    }

    private class ExerciceViewHolder {
        TextView nom;
        TextView repetitions;
        ImageView icone;
    }
}