package com.example.imola.project;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity  {

    FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();



    private static final int RC_SIGN_IN =0;
    private Fragment fragment;

    private static final String TAG = "PhoneAuthActivity";

    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;


    private FirebaseAuth mAuth;


    private boolean mVerificationInProgress = false;
    private String mVerificationId;


    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;



    private TextView mPersName;
    private TextView mLastName;
    private Button mStartButton;

    private EditText mPhoneNumberField;
    private EditText mVerificationField;
    private TextView text;
    private Button mSignedInButton, btnSignUp;





    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }


        FirebaseApp.initializeApp(this);

        mPersName=findViewById(R.id.persName);
        mLastName=findViewById(R.id.persFirstName);
        mVerificationField=findViewById(R.id.fieldVerification);
        text = findViewById(R.id.TextSignUp);
        mStartButton=findViewById(R.id.buttonStartVerification);
        mSignedInButton = (Button) findViewById(R.id.buttonSignIn);
        btnSignUp = (Button) findViewById(R.id.buttonSignUp);
        mPhoneNumberField=findViewById(R.id.checkNumber);


        mSignedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mVerificationField.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    mVerificationField.setError("Cannot be empty.");
                    return;
                }

                verifyPhoneNumberWithCode(mVerificationId, code);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePhoneNumber()) {
                    return;
                }

                startPhoneNumberVerification(mPhoneNumberField.getText().toString());
                savingData();
                mPersName.setVisibility(View.INVISIBLE);
                mLastName.setVisibility(View.INVISIBLE);
                //mPhoneNumberField.setVisibility(View.INVISIBLE);
                btnSignUp.setVisibility(View.INVISIBLE);
                mVerificationField.setVisibility(View.VISIBLE);
                //mStartButton.setVisibility(View.VISIBLE);
                mSignedInButton.setVisibility(View.VISIBLE);
            }

        });
    mStartButton.setOnClickListener(new View.OnClickListener() {
    @Override

        public void onClick(View v) {
        startPhoneNumberVerification(mPhoneNumberField.getText().toString());

        }
    });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPersName.setVisibility(View.VISIBLE);
                mLastName.setVisibility(View.VISIBLE);
                btnSignUp.setVisibility(View.VISIBLE);
                text.setVisibility(View.INVISIBLE);
                mVerificationField.setVisibility(View.INVISIBLE);
                mStartButton.setVisibility(View.INVISIBLE);
                mSignedInButton.setVisibility(View.INVISIBLE);

            }
        });

        //SIGN IN






        ///login


        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                mVerificationInProgress = false;
                signInWithPhoneAuthCredential(credential);
            }


            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                mVerificationInProgress = false;

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mPhoneNumberField.setError("Invalid phone number.");
                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

            }
        };

    }
// [END phone_auth_callbacks]




    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
         currentUser = mAuth.getCurrentUser();

        if (mVerificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(mPhoneNumberField.getText().toString());
        }

    }
// [END on_start_check_user]

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    public void onComplete( Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            if (currentUser.getPhoneNumber().toString() != null) {
                                Toast.makeText(getApplicationContext(),
                                        "Login Successfull" + currentUser.getPhoneNumber().toString(), Toast.LENGTH_LONG).show();
                                FirebaseUser user = task.getResult().getUser();
                            }

/*
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();*/

                            } else {
                                // Sign in failed, display a message
                                Log.w(TAG, "signInWithCredential:failure", task.getException());

                            }
                        }

                    });

    }

// [END sign_in_with_phone]

    /*private void signOut() {
        mAuth.signOut();
        updateUI(STATE_INITIALIZED);
    }*/


    private boolean validatePhoneNumber() {
        String phoneNumber = mPhoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }

        return true;
    }

    private void savingData(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //DatabaseReference ref = database.getReference("users");

        String firstNameText = mPersName.getText().toString();
        String lastNameText = mLastName.getText().toString();
        String phoneNumberText = mPhoneNumberField.getText().toString();


        DatabaseReference usersRef = database.getReference("user").child(phoneNumberText);

        User user = new User(firstNameText, lastNameText,phoneNumberText);

        usersRef.setValue(user);


    }





}

