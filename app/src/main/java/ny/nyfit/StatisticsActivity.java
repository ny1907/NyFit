package ny.nyfit;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by U820319 on 24.11.2016.
 */

public class StatisticsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    SeekBar SBgewicht;
    SeekBar SBfett;
    SeekBar SBmuskeln;
    SeekBar SBwasser;
    Button Bsave;

    TextView TgewichtWert;
    TextView TfettWert;
    TextView TmuskelWert;
    TextView TwasserWert;

    float gewicht;
    float fett;
    float muskeln;
    float wasser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (drawer != null){
            toggle.syncState();
        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        SBgewicht = (SeekBar)findViewById(R.id.seekBarGewicht);
        SBfett = (SeekBar)findViewById(R.id.seekBarFett);
        SBmuskeln = (SeekBar)findViewById(R.id.seekBarMuskeln);
        SBwasser = (SeekBar)findViewById(R.id.seekBarWasser);
        Bsave = (Button)findViewById(R.id.saveStats);

        TgewichtWert = (TextView)findViewById(R.id.gewichtWert);
        TfettWert = (TextView)findViewById(R.id.fettWert);
        TmuskelWert = (TextView)findViewById(R.id.muskelWert);
        TwasserWert = (TextView)findViewById(R.id.wasserWert);

        setDefaultValues();
        setMaxValues();

        SBgewicht.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TgewichtWert.setText(String.valueOf(Float.valueOf(progress)/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SBfett.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TfettWert.setText(String.valueOf(Float.valueOf(progress)/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SBmuskeln.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TmuskelWert.setText(String.valueOf(Float.valueOf(progress)/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SBwasser.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TwasserWert.setText(String.valueOf(Float.valueOf(progress)/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null){
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }


    public void setDefaultValues(){
        // TODO Werte dynamisch aus der Datenbank ermitteln
        int gewicht = 781;
        int fett = 162;
        int muskeln = 432;
        int wasser = 602;

        this.gewicht = Float.valueOf(gewicht)/10;
        this.fett = Float.valueOf(fett)/10;
        this.muskeln = Float.valueOf(muskeln)/10;
        this.wasser = Float.valueOf(wasser)/10;

        SBgewicht.setProgress(gewicht);
        SBfett.setProgress(fett);
        SBmuskeln.setProgress(muskeln);
        SBwasser.setProgress(wasser);

        TgewichtWert.setText(String.valueOf(this.gewicht));
        TfettWert.setText(String.valueOf(this.fett));
        TmuskelWert.setText(String.valueOf(this.muskeln));
        TwasserWert.setText(String.valueOf(this.wasser));

    }

    public void setMaxValues(){
        // TODO Werte dynamisch aus Datenbank ermitteln.
        int gewicht = 800;
        int fett = 200;
        int muskeln = 500;
        int wasser = 700;

        SBgewicht.setMax(gewicht);
        SBfett.setMax(fett);
        SBmuskeln.setMax(muskeln);
        SBwasser.setMax(wasser);
    }

    public void saveValues(){
        this.gewicht = SBgewicht.getProgress();
        this.fett = SBfett.getProgress();
        this.muskeln = SBmuskeln.getProgress();
        this.wasser = SBwasser.getProgress();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //TODO
        return false;
    }
}
