package www.fhrzdty31.sch.id.personprofile_room.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import www.fhrzdty31.sch.id.personprofile_room.R;
import www.fhrzdty31.sch.id.personprofile_room.helper.entitas.User;

public class Adapter extends BaseAdapter {

    private final Activity activity;
    private LayoutInflater inflater;
    private final List<User> list;

    public Adapter(Activity activity, List<User> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null) {
            view = inflater.inflate(R.layout.list_user, null);
        }
        if (view != null) {
            User data = list.get(i);
            CircleImageView image = view.findViewById(R.id.avatar);
            Glide.with(view)
                    .load("https://picsum.photos/id/7"+data.id+"/200")
                    .into(image);
            TextView name = view.findViewById(R.id.name);
            name.setText(data.name);
            TextView email = view.findViewById(R.id.email);
            email.setText(data.email);
        }
        return view;
    }
}
