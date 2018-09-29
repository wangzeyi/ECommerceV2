package com.example.wang_.ecommercev2.category;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang_.ecommercev2.R;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class Frag_Category extends Fragment{

    Button button_Back;
    ImageView imageView_prod;
    TextView textView_Prod, textView_Title;
    //SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_category, null);

        imageView_prod = v.findViewById(R.id.imageView_f_prod);
        //textView_Prod = v.findViewById(R.id.textView_f_prod);
        textView_Title = v.findViewById(R.id.textView_title);
        button_Back = v.findViewById(R.id.button_back);

        Bundle bundle = getArguments();
        String cdiscription = bundle.getString("cdiscription");
        String cimagerl = bundle.getString("cimagerl");
        String cname = bundle.getString("cname");
        String cid = bundle.getString("cid");

        //textView_Prod.setText(cdiscription);
        textView_Title.setText(cname);
        Picasso.with(getActivity()).load(cimagerl).into(imageView_prod);

        //editor = getActivity().getSharedPreferences("ServerInfo", MODE_PRIVATE).edit();
        //editor.putString("cid", cid);

        return v;
    }

}
