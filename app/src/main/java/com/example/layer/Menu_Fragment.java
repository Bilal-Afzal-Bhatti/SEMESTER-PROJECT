package com.example.layer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class Menu_Fragment extends Fragment {

    // Declare the view holders and counters
    private class MenuItem {
        TextView textView;
        Button plusButton;
        Button minusButton;
        Button billButton;
        ImageView imageView;
        int counter;
        String name;
    }

    private MenuItem[] menuItems = new MenuItem[6];

    public Menu_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_, container, false);

        // Initialize each menu item with their respective names
        initializeMenuItem(rootView, 0, R.id.counter, R.id.plus, R.id.minus, R.id.add_cart, R.id.first_image, R.drawable.one_image, "Cake 1");
        initializeMenuItem(rootView, 1, R.id.counters, R.id.plus_ONE, R.id.minus_one, R.id.add_carts, R.id.second_image, R.drawable.lotus_cake, "Lotus Cake");
        initializeMenuItem(rootView, 2, R.id.counter_two, R.id.plus_two, R.id.minus_two, R.id.add_cart_two, R.id.third_image, R.drawable.redvelvet, "Red Velvet");
        initializeMenuItem(rootView, 3, R.id.counter_three, R.id.plus_three, R.id.minus_three, R.id.add_cart_three, R.id.fourth_image, R.drawable.kitkat_sqaure, "KitKat Square");
        initializeMenuItem(rootView, 4, R.id.counter_four, R.id.plus_four, R.id.minus_four, R.id.add_cart_four, R.id.fifth_image, R.drawable.nuts_cake, "Nuts Cake");
        initializeMenuItem(rootView, 5, R.id.counter_five, R.id.plus_five, R.id.minus_five, R.id.add_cart_five, R.id.sixth_image, R.drawable.nutellacake, "Nutella Cake");

        return rootView;
    }

    private void initializeMenuItem(View rootView, int index, int textViewId, int plusButtonId, int minusButtonId, int billButtonId, int imageViewId, int imageResource, String name) {
        menuItems[index] = new MenuItem();
        menuItems[index].textView = rootView.findViewById(textViewId);
        menuItems[index].plusButton = rootView.findViewById(plusButtonId);
        menuItems[index].minusButton = rootView.findViewById(minusButtonId);
        menuItems[index].billButton = rootView.findViewById(billButtonId);
        menuItems[index].imageView = rootView.findViewById(imageViewId);
        menuItems[index].imageView.setImageResource(imageResource);
        menuItems[index].counter = 0;
        menuItems[index].name = name;

        // Set OnClickListeners for the buttons
        menuItems[index].plusButton.setOnClickListener(v -> {
            menuItems[index].counter++;
            menuItems[index].textView.setText(String.valueOf(menuItems[index].counter));
        });

        menuItems[index].minusButton.setOnClickListener(v -> {
            if (menuItems[index].counter > 0) {
                menuItems[index].counter--;
            }
            menuItems[index].textView.setText(String.valueOf(menuItems[index].counter));
        });

        menuItems[index].billButton.setOnClickListener(v -> {
            if (menuItems[index].counter > 0) {
                // Create a Bundle to pass data to the next Fragment
                Bundle bundle = new Bundle();
                bundle.putString("textViewValue", menuItems[index].textView.getText().toString());
                bundle.putInt("image", imageResource);
                bundle.putString("name", name); // Pass the item name

                // Create an instance of the next Fragment
                BillFragment nextFragment = new BillFragment();
                nextFragment.setArguments(bundle);

                // Perform the fragment transaction
                getFragmentManager().beginTransaction().replace(R.id.container, nextFragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                // Show a toast message indicating the cart is empty
                Toast.makeText(getContext(), "Please add items to the cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
