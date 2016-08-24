package net.camtrunet.eventsguay;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;

public class SplashScreen extends Activity {


    public static final int segundos=7;
    public static final int milisegundos=segundos*1000;
    public  static final int delay =2;
    private ProgressBar pbprogreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        pbprogreso=(ProgressBar) findViewById(R.id.pbprogressbar);

        pbprogreso.setMax(maximo_progreso());

        Thread timerTread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, EventsActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerTread.start();

        empezaranimacion();
    }


    public void empezaranimacion() {

        new CountDownTimer(milisegundos,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

                pbprogreso.setProgress(establecer_progreso(millisUntilFinished));


            }

            @Override
            public void onFinish() {




            }
        }.start();
    }

    public int establecer_progreso(long miliseconds)
    {

        return (int)((milisegundos-miliseconds)/1000);

    }



    public int maximo_progreso()
    {

        return segundos-delay;

    }



    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        //getMenuInflater().inflate(R.menu.menu_splash_screen, menu);

        getMenuInflater().inflate(R.menu.activity_events_drawer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.content_event)

            //if (id == R.id.action_settings)
            {

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
