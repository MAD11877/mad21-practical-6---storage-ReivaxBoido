package sg.edu.np.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    public void followclick (View view) {
//        Button btn = (Button)view;
//        btn.setText("Unfollow");
//        Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
//        btn.setOnClickListener(this::unfollowclick);
//    }
//
//    public void unfollowclick (View view) {
//        Button btn = (Button)view;
//        btn.setText("Follow");
//        Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
//
//        btn.setOnClickListener(this::followclick);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textview = (TextView) findViewById(R.id.random);
        Intent myintent = getIntent();
        textview.setText(myintent.getStringExtra("name"));
        textview.setText(myintent.getStringExtra("description"));
        Button followbtn = (Button) findViewById(R.id.btn_follow);
        if (myintent.getIntExtra("follow", 0) == 1) {
            followbtn.setTag("follow");
        } else {
            followbtn.setTag("unfollow");
            followbtn.setText("Unfollow");
        }
        DBHandler db = new DBHandler(this);
        User u = null;
        ArrayList<User> data = db.getUser();
        for (User user : data) {
            if (user.getName() == myintent.getStringExtra("name")) u = user; break;
        }

        User finalU = u;
        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                if (btn.getTag() == "follow") {
                    btn.setText("Unfollow");
                    btn.setTag("unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    finalU.setFollow(false);
                    db.updateUser(finalU);
                } else {
                    btn.setText("Follow");
                    btn.setTag("follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    finalU.setFollow(true);
                    db.updateUser(finalU);
                }
            }
        });
        db.close();
    }
}