package com.inkeox.area11.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inkeox.area11.R;

import java.util.List;

public class EntrainementAdapter extends ArrayAdapter<Entrainement> {

    // entrainements est la liste des models à afficher
    public EntrainementAdapter(Context context, List<Entrainement> entrainements) {
        super(context, 0, entrainements);
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

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_entrainements, parent, false);
        }

        EntrainementViewHolder viewHolder = (EntrainementViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new EntrainementViewHolder();
            viewHolder.nom = convertView.findViewById(R.id.nom);
            viewHolder.nbSequences = convertView.findViewById(R.id.nb_sequences);
            viewHolder.playEntrainementButton = convertView.findViewById(R.id.removeExerciceButton);
            convertView.setTag(viewHolder);
        }

        // getItem(position) va récupérer l'item [position] de la List<Entrainement> entrainements
        Entrainement entrainement = getItem(position);

        // Il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(entrainement.getNom());
        viewHolder.nbSequences.setText((entrainement.getSequenceRepetitions() + " séquences comportant " + entrainement.getExercicesCount() + " exercices"));
        viewHolder.playEntrainementButton.setId(entrainement.getId());

        return convertView;
    }

    private class EntrainementViewHolder {
        TextView nom;
        TextView nbSequences;
        ImageButton playEntrainementButton;
    }
}