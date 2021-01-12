package uk.ac.tees.w9312536.PodMe;


import android.Manifest;
import android.content.Intent;


import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import uk.ac.tees.w9312536.bukolafatunde.R;

public class LocationActivity extends AppCompatActivity {


    Button loadMap, loadLocation;
    TextView longitude, latitude, country, locality, f_address;
    FusedLocationProviderClient fusedLocationProviderClient;
    View v;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        loadMap = (Button) findViewById(R.id.load_map);
//        loadLocation = (Button) findViewById(R.id.load_address);
//        longitude = (TextView) findViewById(R.id.longi);
//        latitude = (TextView) findViewById(R.id.lati);
//        country = (TextView) findViewById(R.id.countri);
//        locality = (TextView) findViewById(R.id.localty);
//        f_address = (TextView) findViewById(R.id.city);
//
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        loadLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ActivityCompat.checkSelfPermission(LocationActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // when permission is granted
//                    //get location
//                    getLocation();
//                } else {
//                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//                }
//            }
//
//        });
//    }
//
//    private void getLocation() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
//            @Override
//            public void onComplete(@NonNull Task<Location> task) {
//                Location location = task.getResult();
//                if (location != null) {
//
//                    Geocoder geocoder = new Geocoder(LocationActivity.this, Locale.getDefault());
//                    try {
//                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLatitude(),1);
//                        latitude.setText(Html.fromHtml("font color = '#6200EE'><b>Latitude : </b> <br></font>" +
//                                addresses.get(0).getLatitude()
//                        ));
//                        longitude.setText(Html.fromHtml("font color = '#6200EE'><b>Longitude : </b> <br></font>" +
//                                addresses.get(0).getLongitude()
//                        ));
//                        country.setText(Html.fromHtml("font color = '#6200EE'><b>Country Name: </b> <br></font>" +
//                                addresses.get(0).getCountryName()
//                        ));
//                        locality.setText(Html.fromHtml("font color = '#6200EE'><b>Locality : </b> <br></font>" +
//                                addresses.get(0).getLocality()
//                        ));
//                        f_address.setText(Html.fromHtml("font color = '#6200EE'><b> Current Address: </b> <br></font>" +
//                                addresses.get(0).getAddressLine(0)
//                        ));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        });
        loadMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* navigate to map activity */
                Intent map = new Intent(LocationActivity.this, MapActivity.class);
                startActivity(map);
            }
        });
        }

        }



