package campus.podcast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Homepage extends AppCompatActivity {
    Button b1,b2,b3;
    ImageView img;
    int PICK_IMAGE_REQUEST = 111;
    Uri filepath;
    ProgressDialog pd;
    FirebaseStorage storage =FirebaseStorage.getInstance();
    StorageReference storageRef=storage.getReferenceFromUrl("gs://podcast-ff9d5.appspot.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        b1=(Button)findViewById(R.id.chooseimage);
        b2=(Button)findViewById(R.id.uploadimg);
        b3=(Button) findViewById(R.id.displaybtn);
        img=(ImageView)findViewById(R.id.image1);
        pd=new ProgressDialog(this);
        pd.setMessage("Uploading");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent,"Select image"),PICK_IMAGE_REQUEST);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filepath!=null){
                    pd.show();
                    StorageReference childRef = storageRef.child("image.jpg");
                    UploadTask uploadTask=childRef.putFile(filepath);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(Homepage.this,"Upload Successful",Toast.LENGTH_SHORT).show();


                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(Homepage.this,"Upload Failed"+e,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(Homepage.this,"Select an image",Toast.LENGTH_SHORT).show();
                }

            }
        });
       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Homepage.this,DisplayImages.class);
               startActivity(intent);
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                img.setImageBitmap(bitmap);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
