package campus.podcast;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akhila on 8/29/2018.
 */
public class DisplayImages extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ProgressDialog pd1;
    List<ImageUploadInfo> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_images);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayImages.this));
        pd1=new ProgressDialog(DisplayImages.this);
        pd1.setMessage("Loading....");
        pd1.show();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot:dataSnapshot.getChildren()){
                    ImageUploadInfo imageUploadInfo=postsnapshot.getValue(ImageUploadInfo.class);
                    list.add(imageUploadInfo);
                }
                adapter=new RecyclerViewAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);
                pd1.dismiss();
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd1.dismiss();

            }
        });
    }
}
