package com.example.mymobileapplogin;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    Button b1;
    EditText ed1, ed2;
    TextView t1;
    User user;

    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.LoginButton);
        ed1 = (EditText) findViewById(R.id.emailText);
        ed2 = (EditText) findViewById(R.id.passwordText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                // validate user
//                if (ed1.getText().toString().equals("admin") &&
//                        ed2.getText().toString().equals("admin"))
              if (validateUser(user)) {
                    Toast.makeText(getApplicationContext(),
                            "LOGIN SUCCESSFUL", Toast.LENGTH_LONG).show();
                  startActivity(intent);
              }
              else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                    counter--;
                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });

        t1 = (TextView)findViewById(R.id.textCreateUser);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser( user );
            }
        });

    }

    private void createUser(User user) {
        Intent intent=new Intent(this,CreateUserActivity.class);
        startActivity(intent);
    }

    private boolean validateUser(User user) {
        //Call db functions to see if user exists and validate password
        return true;
    }
}
