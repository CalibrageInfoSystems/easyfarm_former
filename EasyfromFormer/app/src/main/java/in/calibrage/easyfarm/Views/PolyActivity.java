package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.GetGeoBoundaries;
import in.calibrage.easyfarm.model.LatLan;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PolyActivity extends  AppCompatActivity
        implements
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    String Farmer_code,code;
    private Subscription mSubscription;
    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);
   List<LatLng> coordinates = new ArrayList<LatLng>();
    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);
    String  latlan;
    double latitute,longtitude;
    List<LatLng> points= new ArrayList<LatLng>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        Intent i = getIntent();
        code = i.getStringExtra("code");
      //  Farmer_code = i.getStringExtra("Farmercode");
        Log.d("latlongs Code====", code + "");


        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



//
//




    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this tutorial, we add polylines and polygons to represent routes and areas on the map.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("latlongs Code====1", code + "");
        //String latlng = "[[13.041695199971244, 77.61311285197735], [13.042000923637021, 77.61313531547785], [13.041830750574812, 77.61335827410221], [13.041507062142946, 77.61269208043814]]";
        List<LatLng> coordinates = new ArrayList<>();
        List<LatLng> points = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(code);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray latLong = jsonArray.getJSONArray(i);
                Log.d("latLong", latLong + "");
                double lat = latLong.getDouble(0);
                double lon = latLong.getDouble(1);
                coordinates.add(new LatLng(lat, lon));
                points.add(new LatLng(lat,lon));
                Log.v("map","Map pints /n  lat :"+lat +"   , long :"+lon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       // Log.v("map","Map pints /n  lat181 :"+coordinates.get(0).latitude +"   , long2 :"+coordinates.get(0).longitude);
        if(points != null && points.size() > 0)
        {
            Polygon polygon = googleMap.addPolygon(new PolygonOptions()
                    .addAll(points)
                    .strokeColor(Color.BLUE)
                    .fillColor(Color.CYAN));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(points.get(0).latitude, points.get(0).longitude), 17));
            MarkerOptions marker = new MarkerOptions().position(new LatLng(points.get(0).latitude, points.get(0).longitude)).title("point");
            googleMap.addMarker(marker);
        }

//

//
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {


    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

        Toast.makeText(this, "Route type " + polyline.getTag().toString(),
                Toast.LENGTH_SHORT).show();

    }
}