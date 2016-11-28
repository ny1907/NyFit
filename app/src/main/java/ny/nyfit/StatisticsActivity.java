package ny.nyfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by U820319 on 24.11.2016.
 */

public class StatisticsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    int MIN_GEWICHT = 600;
    int MAX_GEWICHT = 900;
    int MIN_FETT = 100;
    int MAX_FETT = 250;
    int MIN_MUSKELN = 350;
    int MAX_MUSKELN = 500;
    int MIN_WASSER = 500;
    int MAX_WASSER = 700;

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

                TgewichtWert.setText(String.valueOf((float) (progress + MIN_GEWICHT) /10));
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
                TfettWert.setText(String.valueOf((float) (progress + MIN_FETT) /10));
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
                TmuskelWert.setText(String.valueOf((float) (progress + MIN_MUSKELN) /10));
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
                TwasserWert.setText(String.valueOf((float) (progress + MIN_WASSER) /10));
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

    public void getDefaultValueFromDB(){
        // TODO Read out last value from statistics DB

        int gewicht = 781;
        int fett = 162;
        int muskeln = 432;
        int wasser = 602;

        this.gewicht = (float) gewicht /10;
        this.fett = (float) fett /10;
        this.muskeln = (float) muskeln /10;
        this.wasser = (float) wasser /10;

    }


    public void setDefaultValues(){
        // TODO Werte dynamisch aus der Datenbank ermitteln
        int gewicht = 750;
        int fett = 175;
        int muskeln = 425;
        int wasser = 600;

        SBgewicht.setProgress(gewicht - MIN_GEWICHT);
        SBfett.setProgress(fett - MIN_FETT);
        SBmuskeln.setProgress(muskeln - MIN_MUSKELN);
        SBwasser.setProgress(wasser - MIN_WASSER);

        TgewichtWert.setText(String.valueOf((float) gewicht/10));
        TfettWert.setText(String.valueOf((float) fett/10));
        TmuskelWert.setText(String.valueOf((float) muskeln/10));
        TwasserWert.setText(String.valueOf((float) wasser/10));

    }

    public void setMaxValues(){
        SBgewicht.setMax(MAX_GEWICHT - MIN_GEWICHT);
        SBfett.setMax(MAX_FETT - MIN_FETT);
        SBmuskeln.setMax(MAX_MUSKELN - MIN_MUSKELN);
        SBwasser.setMax(MAX_WASSER - MIN_WASSER);
    }

    public void saveValues(View view){
        this.gewicht = (float) (SBgewicht.getProgress() + MIN_GEWICHT) /10;
        this.fett = (float) (SBfett.getProgress() + MIN_FETT) /10;
        this.muskeln = (float) (SBmuskeln.getProgress() - MIN_MUSKELN) /10;
        this.wasser = (float) (SBwasser.getProgress() - MIN_WASSER) /10;

        Statistics stat = new Statistics(this.gewicht, this.fett, this.muskeln, this.wasser);
        stat.setDatum();
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.addStat(stat);
        db.close();

        Intent intent = new Intent(this, StatisticsListActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //TODO
        return false;
    }
}
