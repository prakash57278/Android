package pt.ulisboa.tecnico.cmov.servicesimple;

import android.app.Application;

/**
 * Created by Diogo on 13-Mar-15.
 */
public class GlobalContext extends Application {


    private int nStarted = 0;

    public int getNStarted() {return nStarted;}

    public void setNStarted() { nStarted++; }


}
