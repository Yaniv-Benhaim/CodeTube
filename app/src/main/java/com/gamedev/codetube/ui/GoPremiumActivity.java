package com.gamedev.codetube.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.gamedev.codetube.Common.Config;
import com.gamedev.codetube.R;
import com.gamedev.codetube.currentuser.User;
import com.gamedev.codetube.models.PremiumUsers;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;


import org.json.JSONObject;

import java.math.BigDecimal;

public class GoPremiumActivity extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE = 9999;
    static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);
    Button paymentBtn;
    //Firebase
    private DatabaseReference UserRef;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_premium);

        paymentBtn = findViewById(R.id.pay_premium_btn);


        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayment();
            }
        });


    }

    private void processPayment() {

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        PayPalPayment payment = new PayPalPayment(new BigDecimal(4.99), "USD"
                , "Upgrade to Premium, CodeTube",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent1 = new Intent(getApplicationContext(), PaymentActivity.class);
        intent1.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        Intent intent2 = intent1.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent1, PAYPAL_REQUEST_CODE);

        UserRef = FirebaseDatabase.getInstance().getReference().child("PremiumUsers");
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        //String paymentDetail = confirmation.toJSONObject().toString(4);
                        //JSONObject jsonObject = new JSONObject(paymentDetail);

                        PremiumUsers premiumUser = new PremiumUsers(User.CurrentLoggedIn.personEmail);
                        UserRef.child(User.CurrentLoggedIn.personId).setValue(premiumUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                User.CurrentLoggedIn.isPremium = true;
                                Toast.makeText(GoPremiumActivity.this, "Your account has been upgraded to Premium!", Toast.LENGTH_SHORT).show();
                            }
                        });



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "The payment was canceled", Toast.LENGTH_SHORT).show();
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
                Toast.makeText(this, "Invalid Payment", Toast.LENGTH_SHORT).show();
        }

    }
}
