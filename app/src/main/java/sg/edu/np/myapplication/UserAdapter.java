package sg.edu.np.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context context;
    ArrayList<User> data;

    public UserAdapter(Context c, ArrayList<User> l) {
        context = c;
        data = l;
    }

    @Override
    public int getItemViewType(int position) {
        String s = data.get(position).getName();
        if (s.charAt(s.length() - 1) == '7') {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = null;
        if (viewType == 0) {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.vh_user_big,
                    parent,
                    false);
        } else {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.vh_user,
                    parent,
                    false);
        }
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User u = data.get(position);
        holder.name.setText(u.getName());
        holder.description.setText((u.getDescription()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Profile")
                        .setMessage(u.getName())
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent myIntent = new Intent(context, MainActivity.class);
                                myIntent.putExtra("name", u.getName());
                                myIntent.putExtra("description", u.getDescription());
                                final int random = new Random().nextInt(2);
                                myIntent.putExtra("follow", random);
                                context.startActivity(myIntent);
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("userInput", u.getName());
                //context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
