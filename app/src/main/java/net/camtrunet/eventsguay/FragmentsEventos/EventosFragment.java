package net.camtrunet.eventsguay.FragmentsEventos;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import net.camtrunet.eventsguay.R;
import net.camtrunet.eventsguay.Dominio.Evento;
import net.camtrunet.eventsguay.Util.RequestQueueSingleton;
import net.camtrunet.eventsguay.EventoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventosFragment extends Fragment {



    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private boolean subTituloVisible;
    private static final String SAVED_SUBTITLE_VISIBLE="subtitle";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState!=null){
            subTituloVisible=savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        View view=inflater.inflate(R.layout.fragment_eventos, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_event_list,menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_event:
                Evento evento=new Evento();


                // Me pide llamar a una actividad
                //Intent intent=CrimenActivity.newIntent(getActivity(),crimen.getCrimenId());
                //startActivity(intent);

             //   Intent intent= NFotosActivity.newIntent(getActivity(),evento.getEventoId());
             //   startActivity(intent);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getEventos(){

        String url ="http://eventos.camtrunet.net:8080/eventos/";


        StringRequest getEventos=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<Evento> result= gson.fromJson(response,
                                new TypeToken<List<Evento>>(){}.getType());
                        eventAdapter=new EventAdapter(result);
                        recyclerView.setAdapter(eventAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueueSingleton.getInstance(getActivity()).addToRequestQueue(getEventos);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE,subTituloVisible);
    }

    @Override
    public void onResume() {
        super.onResume();
        getEventos();
    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder>{

        private List<Evento> eventos;

        public EventAdapter(List<Evento> events){
            this.eventos=events;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.list_item_event,parent,false);
            return new EventHolder(view);
        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position) {
            Evento evento=eventos.get(position);
            holder.bindEvent(evento);
        }

        @Override
        public int getItemCount() {
            return eventos.size();
        }
    }

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CheckBox chEstado;
        TextView txtTitulo;
        TextView txtDescripcion;
        TextView txtFecha;


        private Evento evento;

        public EventHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtTitulo=(TextView)itemView.findViewById(R.id.list_item_event_txtTitulo);
            chEstado =(CheckBox)itemView.findViewById(R.id.list_item_event_chEstado);
            txtDescripcion=(TextView)itemView.findViewById(R.id.list_item_event_txtDescripcion);
            txtFecha=(TextView)itemView.findViewById(R.id.list_item_event_txtFecha);
        }

        public void bindEvent(Evento evento){
            this.evento=evento;
            txtTitulo.setText(evento.getTitulo());
            txtDescripcion.setText(evento.getUbicacion());
            chEstado.setChecked(evento.isEstado());
            txtFecha.setText(evento.getFecha().toString());
        }

        @Override
        public void onClick(View v) {

            // Me pide llamar Actividad
            //Intent intent= CrimenActivity.newIntent(getContext(),crimen.getCrimenId());
            //startActivity(intent);

            Intent intent= EventoActivity.newIntent(getContext(),evento.getEventoId());
            startActivity(intent);

        }
    }



    /*
    public EventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eventos, container, false);
    }

*/




}
