package net.camtrunet.eventsguay.FragmentsFotos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import android.widget.TextView;
import android.widget.Toast;
import net.camtrunet.eventsguay.R;
import net.camtrunet.eventsguay.Dominio.Evento;
import net.camtrunet.eventsguay.Util.RequestQueueSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventoFragment extends Fragment {

    private Evento evento;



    private TextView edTitulo;
    private TextView edDetalle;
    private Button btnFecha;
    private CheckBox chEstado;

    private Button btnguardar;



    private static final String ARG_ID="id";
    private static final String DIALOG_DATE="DialogDate";
    private static final int REQUEST_DATE=0;

    public static Fragment newInstance(int eventoId){
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_ID,eventoId);
        EventoFragment eventoFragment=new EventoFragment();
        eventoFragment.setArguments(bundle);
        return eventoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_evento, container, false);

        edTitulo=(TextView)view.findViewById(R.id.fragment_evento_edTitulo);
        edDetalle=(TextView)view.findViewById(R.id.fragment_evento_edDetalle);
        btnFecha=(Button)view.findViewById(R.id.fragment_evento_btnFecha);
        chEstado=(CheckBox)view.findViewById(R.id.fragment_evento_chResuelto);
        btnguardar=(Button)view.findViewById(R.id.fragment_evento_btnguardar);

        edTitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                evento.setTitulo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        chEstado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evento.setEstado(isChecked);
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),evento.toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(),"Muy Pronto Add Calendar",Toast.LENGTH_LONG).show();
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getContext(),evento.toString(),Toast.LENGTH_LONG).show();

                Toast.makeText(getContext(),"Muy Pronto Google Maps",Toast.LENGTH_LONG).show();

//                postEvento();
            }
        });

        return view;
    }

    public void fillControls(){
        edTitulo.setText(evento.getTitulo());
        edDetalle.setText(evento.getDetalle());
        chEstado.setChecked(evento.isEstado());
        btnFecha.setText(evento.getFecha().toString());

    }

    @Override
    public void onResume() {
        super.onResume();
        int eventoId=getArguments().getInt(ARG_ID);
        if(eventoId!=0)
            getEvento(eventoId);
        else
            evento=new Evento();
    }

    private void postEvento(){
        String url ="http://eventos.camtrunet.net:8080/eventos/";
        StringRequest getEventotes=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        evento= gson.fromJson(response,Evento.class);
                        fillControls();
                        Log.d("EVENTO",evento.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

        );

        RequestQueueSingleton.getInstance(getActivity()).addToRequestQueue(getEventotes);
    }

    private void getEvento(int id){


        String url ="http://eventos.camtrunet.net:8080/eventos/"+id;


        StringRequest getEventotes=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        evento= gson.fromJson(response,Evento.class);
                        fillControls();
                        Log.d("EVENTO",evento.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueueSingleton.getInstance(getActivity()).addToRequestQueue(getEventotes);
    }


}
