package pt.ulisboa.tecnico.cmov.basicthreading;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.logging.Handler;


public class UIThread extends ActionBarActivity {

    Runnable tLogic = null;
    Thread t = null;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uithread);

        tLogic = new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while(true){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                    counter++;
                    Log.d("[worker THREAD]", "Counter = " + counter);

//                    Needs handler to talk to UIThread
//                    TextView textView = (TextView) findViewById(R.id.counter);
//                    textView.setText(counter);
                }


            }
        };
    }

    public void onStart(View view){
        t = new Thread(tLogic, "CounterT");
        t.start();
    }

    public void onStop(View view){
        if(t != null)
            t.interrupt();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uithread, menu);
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
