package sg.edu.np.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView description;
    public View view;

    public UserViewHolder (View v) {
        super(v);
        name = v.findViewById(R.id.txtvwName);
        description = v.findViewById(R.id.txtvwDescription);
        view = v;
    }
}
