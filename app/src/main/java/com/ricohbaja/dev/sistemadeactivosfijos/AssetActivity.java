package com.ricohbaja.dev.sistemadeactivosfijos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssetActivity extends AppCompatActivity {

    ImageView imgParallax;
    String[] listviewShortDescription;
    String strPicture;

    int[] listviewImage = new int[]{
            R.drawable.partnumber, R.drawable.descrip, R.drawable.brand, R.drawable.model,
            R.drawable.serial, R.drawable.location, R.drawable.responsible, R.drawable.po,
            R.drawable.invoice, R.drawable.invoicedate, R.drawable.pedimento, R.drawable.codepedimento,
            R.drawable.supplier, R.drawable.costusa, R.drawable.costmx, R.drawable.notes,
            R.drawable.owner, R.drawable.datecreate,
    };

    String[] listviewTitle = new String[]{
            "Número de Parte", "Descripción", "Marca", "Modelo",
            "Serie", "Ubicación", "Responsable", "Orden de Compra",
            "Factura", "Fecha Factura", "Pedimento", "Clave Pedimento",
            "Proveedor", "Costo USA", "Costo MX", "Notas", "Propiedad de",
            "Fecha Creación",
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);

        listviewShortDescription = getIntent().getStringArrayExtra("listviewShortDescription");
        strPicture = getIntent().getStringExtra("picture");

        //Picasso.get().load(strPicture).into(imgParallax);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 18; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);
    }

}
