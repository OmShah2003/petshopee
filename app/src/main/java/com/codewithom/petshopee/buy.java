package com.codewithom.petshopee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.codewithom.petshopee.databinding.ActivityBuyBinding;

public class buy extends AppCompatActivity {
    private ActivityBuyBinding binding;
    int GOOGLE_PAY_REQUEST_CODE = 123;
    int totalAmount;
    String name = "om chaudhari";
    String transactionNote = "Payment for Pet Shop";
    String upiId = "chaudhariom59@okhdfcbank";
    String status;
    Uri uri;
    TextView totalAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        totalAmountTextView = findViewById(R.id.total_amount_text_view);

        // Receive the total amount from the cart activity
        totalAmount = getIntent().getIntExtra("totalAmount", 0); // Remove the int declaration

        // Set the total amount to the TextView
        totalAmountTextView.setText("Total Bill: " + totalAmount);

        // Initialize views and set click listeners
        binding.googleIcon.setOnClickListener(view -> {
            if (totalAmount > 0) {
                String amount = String.valueOf(totalAmount);
                uri = getUpiPaymentUri(name, upiId, transactionNote, amount);
                payWithGpay();
            } else {
                Toast.makeText(buy.this, "Amount should be greater than zero!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to handle payment with Google Pay or any other UPI app
    private void payWithGpay() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
        } else {
            Toast.makeText(this, "No UPI app found. Please install one to continue.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to handle the result of the payment process
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // Handle successful transaction
            Toast.makeText(this, "Transaction Successful", Toast.LENGTH_SHORT).show();
        } else {
            // Handle failed transaction
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to construct the UPI payment URI
    private Uri getUpiPaymentUri(String name, String UpiId, String transactionNote, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", UpiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", transactionNote)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
    }
}
