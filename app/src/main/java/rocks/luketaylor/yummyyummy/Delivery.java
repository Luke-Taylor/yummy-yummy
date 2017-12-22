package rocks.luketaylor.yummyyummy;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Delivery extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private FusedLocationProviderClient mFusedLocationClient;

    private double lon = 0;
    private double lat = 0;

    private boolean mapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try{

            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                lon = location.getLongitude();
                                lat = location.getLatitude();
                                updateMap("Estimated current location");
                                updateAddress(getAddressDetails());
                            }
                        }
                    });
        } catch(SecurityException ex){
            Log.d("yummy yummy",ex.toString());
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                lat = latLng.latitude;
                lon = latLng.longitude;

                updateMap("Selected Location");
                updateAddress(getAddressDetails());
            }
        });

        mapReady = true;

        updateMap("Estimated Current Location");
        updateAddress(getAddressDetails());
    }

    public void updateMap(String locationName){

        if(mapReady){
            mMap.clear();

            LatLng currLoc = new LatLng(lat,lon);
            mMap.addMarker(new MarkerOptions().position(currLoc).title(locationName));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLoc,12));
        }
    }

    public List<String> getAddressDetails(){
        ArrayList<String> address = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try{
            addresses = geocoder.getFromLocation(lat,lon,1);

            Address a = addresses.get(0);

            String addressLine = a.getAddressLine(0);
            String data[] = addressLine.split(", ");

            String city = a.getLocality();

            String postCode = a.getPostalCode();

            city = (city == null || city.isEmpty()) ? data[1].replace(postCode,"") : city;

            address.add(data[0]);
            address.add(city);
            address.add(a.getAdminArea());
            //address.add(a.getCountryName());
            address.add(a.getPostalCode());

            return address;
        } catch(Exception ex){
            Log.d("yummy yummy",ex.toString());
            return new ArrayList<>(0);
        }

    }

    public void updateAddress(List<String> details){
        if(details.size() < 4){
            return;
        }

        ((EditText)findViewById(R.id.addressLine1)).setText(details.get(0));
        ((EditText)findViewById(R.id.city)).setText(details.get(1));
        ((EditText)findViewById(R.id.county)).setText(details.get(2));
        ((EditText)findViewById(R.id.postcode)).setText(details.get(3));
    }

    public String getAddressName(){
        return ((EditText)findViewById(R.id.addressLine1)).getText() + ", " + ((EditText)findViewById(R.id.city)).getText() + ", " + ((EditText)findViewById(R.id.county)).getText() + ", " + ((EditText)findViewById(R.id.postcode)).getText();
    }

    public void updateMapFromAddress(View v){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String locationName = getAddressName();

        try{
            Address a = geocoder.getFromLocationName(locationName,1).get(0);
            lat = a.getLatitude();
            lon = a.getLongitude();

            updateMap("Entered address");
        } catch(Exception ex){
            Log.d("yummy yummy",ex.toString());
        }
    }

    public void viewBasket(View v){
        Intent intent = new Intent(getApplicationContext(), ViewBasket.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
        finish();
    }

    public void addPayment(View v){
        Basket.getInstance().Address = getAddressName();
        Intent intent = new Intent(getApplicationContext(), Payment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        finish();
    }
}
