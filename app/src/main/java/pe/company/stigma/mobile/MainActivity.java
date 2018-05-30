package pe.company.stigma.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private List<String> datos;
    private Button listar;
    private TextView user;
    ArrayList<String> lista = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listar = (Button)findViewById(R.id.listar);
        user = (TextView) findViewById(R.id.usuario);



        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarUsuario();
            }
        });
    }

    public void listarUsuario(){
        AsyncHttpClient client=  new AsyncHttpClient();
        String url = "http://172.17.1.13/mobile/";
         datos = new ArrayList<>();

        client.get(url, null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    pruebita(getJson(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public ArrayList<String> getJson(String response){
        try {
            JSONArray array = new JSONArray(response);
            String cadena;
            for (int i=0;i<array.length();i++){
                cadena = array.getJSONObject(i).getString("usuario");
                lista.add(cadena);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public  void pruebita(List<String> d){
        String user2 = d.get(4).toString();
        user.setText(user2);
    }
}
