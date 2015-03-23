package pt.ulisboa.tecnico.cmov.notepadapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class CreateNoteActivity extends ActionBarActivity {

    private final static String TAG = CreateNoteActivity.class.getSimpleName();
    private Uri imageUri;

    private static final int SELECT_PICTURE = 1;

    ImageView mImageView;
    Bitmap mBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        mImageView = (ImageView) findViewById(R.id.image_loaded);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
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

    // defines the intent to return (constructs the bundle with the info inserted and returns it)
    public void returnToListNotesOk(View view){

        Intent intent = getIntent();

        EditText noteTitle = (EditText) findViewById(R.id.insert_note_title);
        EditText noteText = (EditText) findViewById(R.id.insert_note_text);

        // create bundle
        Bundle bundle = new Bundle();

        bundle.putString(ListNotesActivity.NOTE_TITLE, noteTitle.getText().toString());
        bundle.putString(ListNotesActivity.NOTE_TEXT, noteText.getText().toString());

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
        bundle.putByteArray(ListNotesActivity.NOTE_IMAGE, bs.toByteArray());

        // insert bundle into the intent
        intent.putExtra(ListNotesActivity.NOTE_BUNDLE, bundle);

        // return the intent
        setResult(RESULT_OK, intent);

        //finish this activity
        finish();
    }

    public void returnToListNotesCancel(View view){
        //Call this to set the result that your activity will return to its caller.
        setResult(RESULT_CANCELED);
        //Call this when your activity is done and should be closed.
        finish();
    }

    public void loadImage(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            try {
                imageUri = data.getData();
                Log.w("ACTIVITY__CREATE", "context = " + this.getContentResolver());
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                mImageView.setImageBitmap(mBitmap);
            }catch(IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

}
