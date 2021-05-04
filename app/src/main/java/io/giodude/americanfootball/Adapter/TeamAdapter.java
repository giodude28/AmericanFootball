package io.giodude.americanfootball.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.americanfootball.Model.Team;
import io.giodude.americanfootball.R;
import io.giodude.americanfootball.View.TeamView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    Context context;
    private List<Team> data;
    TextView name, taon, laro, tdesc,fb,twitter,intsa;
    ImageView jimg;
    public TeamAdapter(Context context, List<Team> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.teamitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getStrTeam());

        Uri imageRequest = Uri.parse(data.get(position).getStrTeamJersey());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageRequest)
                .setResizeOptions(new ResizeOptions(150, 150))
                .build();
        holder.jer.setController(
                Fresco.newDraweeControllerBuilder()
                        .setOldController(holder.jer.getController())
                        .setImageRequest(request)
                        .build());

        Uri imageRequest1 = Uri.parse(data.get(position).getStrTeamBadge());
        ImageRequest request1 = ImageRequestBuilder.newBuilderWithSource(imageRequest1)
                .setResizeOptions(new ResizeOptions(150, 150))
                .build();
        holder.image.setController(
                Fresco.newDraweeControllerBuilder()
                        .setOldController(holder.image.getController())
                        .setImageRequest(request1)
                        .build());

        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.teamdetails);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        name = myDialog.findViewById(R.id.name);
        taon = myDialog.findViewById(R.id.year);
        laro = myDialog.findViewById(R.id.sport);
        fb = myDialog.findViewById(R.id.fbb);
        tdesc = myDialog.findViewById(R.id.teamdesc);
        twitter = myDialog.findViewById(R.id.twiiter);
        jimg = myDialog.findViewById(R.id.jerseyimg);
        intsa = myDialog.findViewById(R.id.insta);
        for (int i = 0; i < data.size(); i++) {
            if (holder.name.getText() == data.get(position).getStrTeam()) {
                name.setText(data.get(position).getStrTeam());
                taon.setText(data.get(position).getIntFormedYear());
                tdesc.setText(data.get(position).getStrDescriptionEN());
                laro.setText(data.get(position).getStrSport());
                fb.setText(data.get(position).getStrFacebook());
                twitter.setText(data.get(position).getStrTwitter());
                intsa.setText(data.get(position).getStrInstagram());
                Picasso.get().load(data.get(position).getStrTeamJersey()).into(jimg);
            }
        }
        holder.itemView.setOnClickListener(v -> myDialog.show());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        SimpleDraweeView jer;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jer = itemView.findViewById(R.id.teamjersey);
            image = itemView.findViewById(R.id.teamimg);
            name = itemView.findViewById(R.id.teamname);
        }
    }

    public void updateList(List<Team> updatedList) {
        data = updatedList;
        notifyDataSetChanged();
    }
}
