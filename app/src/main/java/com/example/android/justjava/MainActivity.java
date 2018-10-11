package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {


    int quantity = 0;
    int price;
    int priceOfCoffee;

    boolean hasChocolate;
    boolean hasWhippedCream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();
        EditText nameText = findViewById(R.id.name_edit_text_view);
        String name = nameText.getText().toString();
        calculatePrice();



        String priceMessage = orderOfSummary(price, hasWhippedCream, hasChocolate, name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for: " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // This increases the quantity by 1.
    public void increment(View view) {
        quantity = quantity + 1;
        if(quantity > 100){
            quantity = 99;
        }
        displayQuantity(quantity);
    }

    // This decreases the quantity by 1.
    public void decrement(View view) {
        quantity = quantity - 1;
        if(quantity < 0){
            quantity = 0;
        }
        displayQuantity(quantity);
    }

    private int calculatePrice(){

        if((hasChocolate && hasWhippedCream)){
            priceOfCoffee = 5;
            priceOfCoffee = priceOfCoffee + 3;
        }
        else if(hasWhippedCream == true){
            priceOfCoffee = 5;
            priceOfCoffee = priceOfCoffee + 1;
        }

       else if(hasChocolate == true){
            priceOfCoffee = 5;
            priceOfCoffee = priceOfCoffee + 2;
        }

        else{
            priceOfCoffee = 5;
        }


        price = priceOfCoffee * quantity;
        return price;
    }

    private String orderOfSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name){
             String priceMessage= "Name: " + name +
                "\nAdd Whipped Cream: " + hasWhippedCream +
                "\nAdd Chocolate: " + hasChocolate +
                "\nQuantity : " + quantity +
                "\nThank you!!" + "\n Total Price: $" + price;
        displayMessage(priceMessage);
        return priceMessage;
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }



    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }




}
