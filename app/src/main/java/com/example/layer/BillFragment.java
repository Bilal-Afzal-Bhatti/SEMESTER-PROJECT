package com.example.layer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BillFragment extends Fragment {

    private TextView textView;
    private TextView itemNameTextView;
    private ImageView imageView;

    public BillFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        // Find the TextViews and ImageView
        textView = view.findViewById(R.id.total);
        TextView calTextView = view.findViewById(R.id.CAL);
        itemNameTextView = view.findViewById(R.id.cake);
        imageView = view.findViewById(R.id.image);

        // Retrieve text from arguments bundle
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String msg = bundle.getString("textViewValue");
            String itemName = bundle.getString("name");
            int imageResource = bundle.getInt("image");

            // Set the item name and image
            itemNameTextView.setText(itemName);
            imageView.setImageResource(imageResource);

            // Set the text to the TextView
            textView.setText(msg);

            // Convert the string to integer and perform multiplication
            int msgValue = Integer.parseInt(msg);
            int total = msgValue * 2100;

            // Set the result to the CAL TextView
            calTextView.setText(String.valueOf(total));
        }

        return view; // Return the inflated view
    }
}
