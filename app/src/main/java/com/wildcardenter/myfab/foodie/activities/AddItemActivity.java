package com.wildcardenter.myfab.foodie.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.helpers.Constants;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class AddItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "AddItemActivity";

    private ImageButton imgButton;
    private ImageView close;
    private MaterialEditText name, price, desc;
    private Spinner category;
    private String selectedCategory="All";
    private Uri imageUri = null;
    private StorageTask storageTask;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        imgButton = findViewById(R.id.add_item_image);
        close = findViewById(R.id.add_item_close);
        name = findViewById(R.id.add_item_name);
        price = findViewById(R.id.add_item_price);
        desc = findViewById(R.id.add_item_desc);
        category = findViewById(R.id.add_category_spinner);
        Log.e(TAG, "onCreate: ");
        Toolbar toolbar = findViewById(R.id.add_item_toolbar);
        setSupportActionBar(toolbar);
        //reference
        storageReference = FirebaseStorage.getInstance().getReference("product_img");
        category.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, Constants.category));
        category.setOnItemSelectedListener(this);
        imgButton.setOnClickListener(l ->
                CropImage.activity()
                        .setActivityTitle("Select Image")
                        .start(this));
        close.setOnClickListener(l -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            imgButton.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "Cancelled!!!", Toast.LENGTH_SHORT).show();
        }
    }




    /*
    all menu inflation
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.e(TAG, "onOptionsItemSelected: bsh");
        if (id == R.id.add_item_save) {
            Log.e(TAG, "reached");
            String nam = name.getText().toString().trim();
            String des = desc.getText().toString().trim();
            int price_=0;
            try {
                price_ = Integer.parseInt(price.getText().toString().trim());
            }catch (NumberFormatException e){
                price.setError("Field Required");
                return true;
            }
            if (nam.isEmpty()) {
                name.setError("Field empty");
            } else if (des.isEmpty()) {
                desc.setError("Field Required");
            } else if (price.getText().toString().trim().isEmpty()) {
                price.setError("Field Required");
            } else {
                Log.e(TAG, "onOptionsItemSelected: else reached");
                Product product = new Product(nam, des, null, selectedCategory, 1, price_);
                uploadItemToServer(product);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCategory = (String) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedCategory = "All";
    }


    private void uploadItemToServer(Product product) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading..");
        progressDialog.show();
        if (imageUri != null) {
            String nameInStorage = System.currentTimeMillis() + ".jpeg";
            final StorageReference file = storageReference.child(nameInStorage);

            storageTask = file.putFile(imageUri);
            storageTask.continueWithTask((Continuation) task -> {
                if (!task.isSuccessful()) {
                    progressDialog.dismiss();
                    throw Objects.requireNonNull(task.getException());
                }
                return file.getDownloadUrl();
            }).addOnCompleteListener(this, (OnCompleteListener<Uri>) task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    String myimageUri = downloadUri.toString();
                    CollectionReference ref = FirebaseFirestore.getInstance()
                            .collection(Constants.PRODUCT_COLLECTION);
                    String productId = ref.document().getId();
                    product.setImageUrls(myimageUri);
                    product.setProductId(productId);
                    ref.document(productId).set(product, SetOptions.merge())
                            .addOnCompleteListener(this, task1 -> {
                                if (task1.isSuccessful()) {
                                    Toasty.success(this, "Successfully added", Toast.LENGTH_SHORT,
                                            true).show();
                                    finish();
                                } else {
                                    Toasty.error(this, "Something went wrong.", Toasty.LENGTH_SHORT, true).show();
                                }

                            });
                    progressDialog.dismiss();
                } else {
                    Toasty.error(this, "Something went wrong.", Toasty.LENGTH_SHORT, true).show();
                    progressDialog.dismiss();
                }
            });

        } else {
            Toasty.error(this, "Something went wrong.", Toasty.LENGTH_SHORT, true).show();
        }
    }
}