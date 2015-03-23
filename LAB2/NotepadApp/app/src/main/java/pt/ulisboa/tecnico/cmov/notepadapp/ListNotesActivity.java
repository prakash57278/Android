package pt.ulisboa.tecnico.cmov.notepadapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.cmov.notepadapp.Adapter.BundleAdapter;


public class ListNotesActivity extends ActionBarActivity {

    private static final int NOTE_REQUEST_CODE = 1;  // The request code

    public static  final String NOTE_TITLE = "noteTitle";
    public static  final String NOTE_TEXT = "noteText";
    public static final String NOTE_IMAGE = "noteImage";

    public static final String NOTE_BUNDLE = "noteBundle";

    ArrayList<Bundle> noteList = new ArrayList<Bundle>();
    BundleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        //Create reference to the EditText and the ListView in the main layout
        ListView listView = (ListView) findViewById(R.id.note_list);

        //Create adapter arrayList to adapt to the listView
        noteList = new ArrayList<Bundle>();
        adapter = new BundleAdapter(this, noteList);

        //set adapter to the listView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ReadNoteActivity.class);
                intent.putExtra(ListNotesActivity.NOTE_BUNDLE, (Bundle) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        addNote("Static", "Esta nota é para exemplificar");
        addNote("Home", "The place where you live.");

    }


    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addNote(String noteTitle, String noteText) {

        // create the note bundle
        Bundle bundle = new Bundle();
        bundle.putString(ListNotesActivity.NOTE_TITLE, noteTitle);
        bundle.putString(ListNotesActivity.NOTE_TEXT, noteText);

        // insert the bundle into the array
        noteList.add(bundle);

        // notify the adapter of changes
        adapter.notifyDataSetChanged();
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addNoteBundle(Bundle note) {

        noteList.add(note);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNote(View view) {

        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivityForResult(intent, NOTE_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == NOTE_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                // get the bundle of note info
                Bundle noteBundle = data.getBundleExtra(NOTE_BUNDLE);

                // add the information to the array list of bundles and update
                addNoteBundle(noteBundle);

                return;
            }
            if (resultCode == RESULT_CANCELED) {
                return;
            }
        }
    }
}
