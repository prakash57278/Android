package pt.ulisboa.tecnico.cmov.helloworld;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

//    For the next activity to query the extra data, you should define the key for your
//    intent's extra using a public constant. It's generally a good practice to define keys for
//    intent extras using your app's package name as a prefix. This ensures the keys are unique,
//    in case your app interacts with other apps.
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /** Called when the user clicks the Send button */
    // Sends message to a new activity
    public void sendMessage(View view) {
        //create the intent
        Intent intent = new Intent(this, DisplayMessageActivity.class); //Intent( context we're in, class where to send the intent)

        //Put the inserted message into the intent
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message); // putExtra(key_name, value)

        // start the new activity with the created intent, with the extra message
        startActivity(intent); //The system receives this call and starts an instance of the Activity specified by the Intent.
    }
}
