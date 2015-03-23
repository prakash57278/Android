package pt.ulisboa.tecnico.cmov.notepadapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class ReadNoteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        // get the info from the bundle intent
        Intent intent = getIntent();

        Bundle note = intent.getBundleExtra(ListNotesActivity.NOTE_BUNDLE);

        Log.w("[ACTIVITY__READ]", "Title : " + note.getString(ListNotesActivity.NOTE_TITLE));
        Log.w("[ACTIVITY__READ]", "Text : " + note.getString(ListNotesActivity.NOTE_TEXT));
        Log.w("[ACTIVITY__READ]", "Uri : " + note.getString(ListNotesActivity.NOTE_IMAGE));

        // init title
        String noteTitle = note.getString(ListNotesActivity.NOTE_TITLE);
        TextView viewTitle = (TextView) findViewById(R.id.note_title);
        viewTitle.setText(noteTitle);

        // init text
        String noteText = note.getString(ListNotesActivity.NOTE_TEXT);
        TextView viewText = (TextView) findViewById(R.id.note_text);
        viewText.setText(noteText);

        byte[] noteBitmap = note.getByteArray(ListNotesActivity.NOTE_IMAGE);
        if(noteBitmap != null) {
            ImageView viewImage = (ImageView) findViewById(R.id.image_loaded);
            Bitmap b = BitmapFactory.decodeByteArray(noteBitmap, 0, noteBitmap.length);
            viewImage.setImageBitmap(b);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_note, menu);
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
}
