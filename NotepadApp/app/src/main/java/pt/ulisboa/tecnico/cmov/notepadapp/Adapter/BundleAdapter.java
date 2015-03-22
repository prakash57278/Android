package pt.ulisboa.tecnico.cmov.notepadapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.cmov.notepadapp.ListNotesActivity;
import pt.ulisboa.tecnico.cmov.notepadapp.R;

/**
 * Created by Diogo on 17-Mar-15.
 */

public class BundleAdapter extends ArrayAdapter<Bundle> {

    public BundleAdapter(Context context, ArrayList<Bundle> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Bundle note = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        // item_note is the .xml create exactly to represent each item of the listView (in this case only the title is exposed)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        }

        // Lookup view for data population
        TextView textView = (TextView) convertView.findViewById(R.id.noteTitle);

        // Populate the data into the template view using the data object
        String noteTitle = note.getString(ListNotesActivity.NOTE_TITLE);
        textView.setText(noteTitle);

        // Return the completed view to render on screen
        return convertView;
    }
}