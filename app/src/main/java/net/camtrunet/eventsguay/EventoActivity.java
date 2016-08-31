package net.camtrunet.eventsguay;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;


import net.camtrunet.eventsguay.FragmentsFotos.EventoFragment;

public class EventoActivity extends SingleFragmentActivity {


    private static final String EXTRA_EVENTO_ID = "ec.com.crimen.crimendroid_fragmentarguments.crimen_id";

    public static Intent newIntent(Context context, int eventoId) {
        Intent intent = new Intent(context, EventoActivity.class);
        intent.putExtra(EXTRA_EVENTO_ID, eventoId);
        return intent;
    }


    @Override
    public Fragment CreateFragment() {


        int eventoId = getIntent().getIntExtra(EXTRA_EVENTO_ID, 0);
        return EventoFragment.newInstance(eventoId);
    }


}
