package com.ricohbaja.dev.sistemadeactivosfijos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    //String urlJSON = "http://10.84.141.94/AssetsTest/WebService1.asmx/GetAsset?id=";
    String urlJSON = "http://10.0.2.2/AssetsTest/WebService1.asmx/GetAsset?id=";
    String strUrlPicture = "http://10.84.141.94/F$/FIXEDASSETS/";
    //String[] listviewShortDescription = new String[18];
    String strPicture;

    String[] listviewShortDescription = new String[]{
            "SADASDAD", "MONITOR DE 24 P SAMSUNG", "SAMSUNG", "19S100N",
            "MXLDFDS34", "SISTEMAS", "FRANCISCO TOVAR", "-", "AS299",
            "MAY-09-2018", "A250", "AF", "MAQTOOLS",
            "200DLLS", "0", "-", "RICOH BAJA",
            "MAY-17-2018",
    };


    SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy");

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectResquest;

    Button btnSearch;
    EditText txtId;
    String stridAsset;
    int intValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.btnSearch);
        txtId = findViewById(R.id.txtId);

        requestQueue = Volley.newRequestQueue(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stridAsset = txtId.getText().toString();
                if(stridAsset.matches(""))
                {
                    Intent i = new Intent(MainActivity.this, ScanActivity.class);
                    startActivityForResult(i, 1);
                }
                else {

                    Intent myIntent;
                    myIntent = new Intent(getBaseContext(), AssetActivity.class);
                    myIntent.putExtra("listviewShortDescription", listviewShortDescription); //Optional parameters
                    myIntent.putExtra("strPicture", strPicture);
                    strPicture = "";

                    startActivity(myIntent);

                    //StartSearch(stridAsset);
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                txtId.setText("");
                stridAsset = data.getStringExtra("editTextId");
                txtId.setText(stridAsset);
                stridAsset = "";
            }
        }
    }

    private void StartSearch(String stridAsset) {
        String urlAssets = urlJSON + stridAsset;
        jsonObjectResquest = new JsonObjectRequest(Request.Method.GET, urlAssets, null, this, this);
        requestQueue.add(jsonObjectResquest);
        stridAsset = "";
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {



        Calendar calendar = Calendar.getInstance();
        String datereip;


        try {
            String visible = response.getString("visible");

            if(visible == "true"){

                if((response.getString("dateCreate") == "") || (  response.getString("dateCreate") == "null")){
                    datereip = response.getString("invoiceDate").replace("/Date(", "").replace(")/", "");
                    Long timeInMillis2 = Long.valueOf(datereip);
                    calendar.setTimeInMillis(timeInMillis2);
                }

                //strvalues[0] =  response.getString("IdAssets");
                listviewShortDescription[0] = ((response.getString("partNumber") == "") ||(  response.getString("partNumber") == "null")) ? "-" : response.getString("partNumber");
                listviewShortDescription[1] = ((response.getString("descrip") == "") ||(  response.getString("descrip") == "null")) ? "-" : response.getString("descrip");
                listviewShortDescription[2] = ((response.getString("brand") == "") ||(  response.getString("brand") == "null")) ? "-" : response.getString("brand");
                listviewShortDescription[3] = ((response.getString("model") == "") ||(  response.getString("model") == "null")) ? "-" : response.getString("model");
                listviewShortDescription[4] = ((response.getString("serial") == "") ||(  response.getString("serial") == "null")) ? "-" : response.getString("serial");
                listviewShortDescription[5] = ((response.getString("idLocationfk") == "") ||(  response.getString("idLocationfk") == "null")) ? "-" : response.getString("idLocationfk");
                listviewShortDescription[6] = ((response.getString("idResponsiblefk") == "") ||(  response.getString("idResponsiblefk") == "null")) ? "-" : response.getString("idResponsiblefk");
                listviewShortDescription[7] = ((response.getString("PO") == "") ||(  response.getString("PO") == "null")) ? "-" : response.getString("PO");
                listviewShortDescription[8] = ((response.getString("invoice") == "") ||(  response.getString("invoice") == "null")) ? "-" : response.getString("invoice");
                listviewShortDescription[9] = ((response.getString("invoiceDate") == "") ||(  response.getString("invoiceDate") == "null")) ? "-" : format.format(calendar.getTime());
                listviewShortDescription[10] = ((response.getString("pedimento") == "") ||(  response.getString("pedimento") == "null")) ? "-" : response.getString("pedimento");
                listviewShortDescription[11] = ((response.getString("codePedimento") == "") ||(  response.getString("codePedimento") == "null")) ? "-" : response.getString("codePedimento");
                listviewShortDescription[12] = ((response.getString("provider") == "") ||(  response.getString("provider") == "null")) ? "-" : response.getString("provider");
                listviewShortDescription[13] = ((response.getString("cost") == "0") ||(  response.getString("cost") == "null")) ? "-" : response.getString("cost");
                listviewShortDescription[14] = ((response.getString("costmx") == "0") ||(  response.getString("costmx") == "null")) ? "-" : response.getString("costmx");
                listviewShortDescription[15] = ((response.getString("notes") == "") ||(  response.getString("notes") == "null")) ? "-" : response.getString("notes");
                listviewShortDescription[16] = ((response.getString("owner") == "") ||(  response.getString("owner") == "null")) ? "-" : response.getString("owner");

                if((response.getString("dateCreate") == "") || (  response.getString("dateCreate") == "null")){
                    datereip = response.getString("dateCreate").replace("/Date(", "").replace(")/", "");
                    Long timeInMillis = Long.valueOf(datereip);
                    calendar.setTimeInMillis(timeInMillis);
                }

                listviewShortDescription[17] = ((response.getString("dateCreate") == "") || (  response.getString("dateCreate") == "null")) ? "-" : format.format(calendar.getTime());


                strPicture = strUrlPicture + response.getString("picture");

                Intent myIntent;
                myIntent = new Intent(getBaseContext(), AssetActivity.class);
                myIntent.putExtra("listviewShortDescription", listviewShortDescription); //Optional parameters
                myIntent.putExtra("strPicture", strPicture);
                strPicture = "";

                startActivity(myIntent);
            }
            else if(visible == "false"){
                Toast.makeText(this, "Este activo fijo ya no existe en la base de datos.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "El Activo que busca no existe.", Toast.LENGTH_LONG).show();
            }




        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: 2: " + e, Toast.LENGTH_LONG).show();
        }



    }
}
