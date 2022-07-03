package com.rudy.utilityapp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.rudy.utilityapp.CalculatorActivity;
import com.rudy.utilityapp.R;
import com.rudy.utilityapp.Tic_tac_toeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment implements View.OnClickListener {


    private ImageButton[] imageButton = new ImageButton[4];


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static Dashboard newInstance(String param1, String param2) {
        Dashboard fragment = new Dashboard();
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

    //==============================================================================================================

    ImageButton ima1, ima2,ima3,ima4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create a View so i can use fields from my fragment

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ima1 = v.findViewById(R.id.imageButtonCalulator);
        ima2 = v.findViewById(R.id.imageButtonTictactoe);
        ima3 = v.findViewById(R.id.imageButtonNotes);
        ima4 = v.findViewById(R.id.imageButtonPlayer);

        for (int i = 0; i < imageButton.length; i++) {
            int resourceID =0;
            switch (i){
                case 0:
                    resourceID = getResources().getIdentifier("imageButtonCalulator","id", getActivity().getPackageName());
                    imageButton[i] = (ImageButton) v.findViewById(resourceID);
                    imageButton[i].setOnClickListener(this);
                    break;
                case 1:
                    resourceID = getResources().getIdentifier("imageButtonTictactoe","id", getActivity().getPackageName());
                    imageButton[i] = (ImageButton) v.findViewById(resourceID);
                    imageButton[i].setOnClickListener(this);
                    break;
                case 2:
                    resourceID = getResources().getIdentifier("imageButtonNotes","id", getActivity().getPackageName());
                    imageButton[i] = (ImageButton) v.findViewById(resourceID);
                    imageButton[i].setOnClickListener(this);
                    break;
                case 3:
                    resourceID = getResources().getIdentifier("imageButtonPlayer","id", getActivity().getPackageName());
                    imageButton[i] = (ImageButton) v.findViewById(resourceID);
                    imageButton[i].setOnClickListener(this);
                    break;
                default:
                    break;
            }
        }



        return v;


    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.i("LOG CAT IS HERE", String.valueOf(id));


        for (ImageButton btnImages : imageButton) {
            if (btnImages.getResources().getResourceEntryName(id).equals("imageButtonCalulator")) {
                startActivity(new Intent(getActivity(), CalculatorActivity.class));

            }
            if (btnImages.getResources().getResourceEntryName(id).equals("imageButtonTictactoe")) {
                startActivity(new Intent(getActivity(), Tic_tac_toeActivity.class));

            }

        }

    }//end on click


    public void makeUsable(String s){

        if (s.equals("txt_calculator")){
            imageButton[0].setClickable(false);

        }
        if (s.equals("switch_tictac")){
            imageButton[1].setClickable(false);

        }
        if (s.equals("switch_notes")){
            imageButton[3].setClickable(false);

        }
        if (s.equals("switch_video")){
            imageButton[4].setClickable(false);

        }

    }






}