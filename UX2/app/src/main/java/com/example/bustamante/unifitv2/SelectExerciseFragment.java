package com.example.bustamante.unifitv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectExerciseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectExerciseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button musculo;
    ListView lista_pormusculo;
    ArrayList<String> rutinas;
    MyAdapter adaptador;
    Intent intent;
    String nombre_rutina;
    String musculo_seleccionado;

    public SelectExerciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectExerciseFragment newInstance(String param1, String param2) {
        SelectExerciseFragment fragment = new SelectExerciseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_exercise, container, false);

        lista_pormusculo = (ListView)rootView.findViewById(R.id.listViewMusculo);
        setListView(lista_pormusculo);

        musculo = (Button)rootView.findViewById(R.id.selectedMuscle);
        Bundle bundle = this.getArguments();
        musculo_seleccionado = bundle.getString("musculo");
        setSelectedMuscle(musculo,musculo_seleccionado);

        return rootView;
    }

    public boolean setSelectedMuscle(Button muscle, String selected_muscle){

        muscle.setText("Músculo seleccionado: "+selected_muscle);

        return true;
    }

    public boolean setListView(ListView lista){
        rutinas = new ArrayList<>();
        //ArrayAdapter<String> adaptador;
        //adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        rutinas.add("Flexiones de brazo");
        rutinas.add("Fondos");
        rutinas.add("Jalones");
        rutinas.add("Lagartijas");
        rutinas.add("Press en banca");
        adaptador = new MyAdapter(getActivity(),R.layout.custom_row,rutinas);
        lista.setAdapter(adaptador);
        lista.setClickable(true);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        intent = new Intent(getActivity(),AddExerciseActivity.class);
                        nombre_rutina = adapterView.getItemAtPosition(i).toString();
                        intent.putExtra("nombre",nombre_rutina);
                        startActivity(intent);
                }

            }
        });

        return true;
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        public View getView(int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.custom_row, null);
            }

            TextView rutina_view = (TextView) fila.findViewById(R.id.routine);

            String rutina = getItem(position);
            rutina_view.setText(rutina);

            return fila;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
