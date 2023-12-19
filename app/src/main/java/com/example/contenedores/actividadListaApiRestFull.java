package com.example.contenedores;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.contenedores.adaptadores.AdaptadorUsuario;
import com.example.contenedores.modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import WebServices.Asynchtask;
import WebServices.WebService;

public class actividadListaApiRestFull extends AppCompatActivity implements Asynchtask {

    ListView lstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_lista_api_rest_full);

        lstOpciones = (ListView) findViewById(R.id.lstListaUsuario);

        View header = getLayoutInflater().inflate(R.layout.ly_itemusuario, null);
        lstOpciones.addHeaderView(header);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://reqres.in/api/users",
                datos, actividadListaApiRestFull.this, actividadListaApiRestFull.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject JSONlista = new JSONObject(result);
        JSONArray JSONlistaUsuarios = JSONlista.getJSONArray("data");
        ArrayList<Usuario> lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);

        AdaptadorUsuario adaptadorUsuario = new AdaptadorUsuario(this, lstUsuarios);
        lstOpciones.setAdapter(adaptadorUsuario);
    }

}
