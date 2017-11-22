package com.example.bustamante.unifitv2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectAreaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectAreaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectAreaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView image;

    public SelectAreaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectAreaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectAreaFragment newInstance(String param1, String param2) {
        SelectAreaFragment fragment = new SelectAreaFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_select_area, container, false);

        image = (ImageView)rootView.findViewById(R.id.bodyImage);
        setImage(image);
        imageClickListener(image);

        return rootView;
    }

    public boolean setImage(ImageView imagen){
        imagen.setImageResource(R.mipmap.body);
        return true;
    }

    public boolean imageClickListener(ImageView imagen){
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(SelectAreaActivity.this,AddRoutinesActivity.class);
                startActivity(intent); */
                Bundle bundle = new Bundle();
                bundle.putString("musculo","Biceps");
                SelectExerciseFragment sef = new SelectExerciseFragment();
                sef.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.container2,sef).commit();
            }
        });
        return true;
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
