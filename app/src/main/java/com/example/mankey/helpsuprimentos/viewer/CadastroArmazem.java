package com.example.mankey.helpsuprimentos.viewer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mankey.helpsuprimentos.R;
import com.example.mankey.helpsuprimentos.model.Armazem;
import com.example.mankey.helpsuprimentos.model.Localizacao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CadastroArmazem extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private double currentLatitude = 0.0;
    private double currentLongitude = 0.0;
    private EditText edtNomeArmazem;
    private EditText edtLocalizacao;
    private EditText edtResponsavelUUID;
    private Button btnSalvarArmazem;
    private DatabaseReference databaseReference; // Referência para o banco de dados do Firebase
    private int armazemIndex; // Variável para armazenar o índice atual das categorias

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armazem_cadastro);

        edtNomeArmazem = findViewById(R.id.edtNomeArmazem);
        edtLocalizacao = findViewById(R.id.edtLocalizacao);
        edtResponsavelUUID = findViewById(R.id.edtResponsavelUUID);
        btnSalvarArmazem = findViewById(R.id.btnSalvarArmazem);
        databaseReference = FirebaseDatabase.getInstance().getReference("armazem");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Atualiza a variável roleIndex com o número de role existentes
                armazemIndex = (int) snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Tratar possíveis erros ao acessar o Firebase
            }
        });
        btnSalvarArmazem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarArmazem(); // Chama o método para salvar a categoria
            }
        });
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Verifica permissões e solicita se necessário
        if (ActivityCompat.checkSelfPermission(CadastroArmazem.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(CadastroArmazem.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CadastroArmazem.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return; // Sai do método se as permissões não forem concedidas
        }

        // Solicita atualizações de localização
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        // Tenta obter a última localização conhecida
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownLocation != null) {
            currentLatitude = lastKnownLocation.getLatitude();
            currentLongitude = lastKnownLocation.getLongitude();
        }
    }
    private void salvarArmazem(){
        // Obtém o nome da categoria do campo de entrada de texto
        String nomeArmazem = edtNomeArmazem.getText().toString().trim();
        String responsavelUUID = edtResponsavelUUID.getText().toString().trim();
        String localizacao = edtLocalizacao.getText().toString().trim();
        double longitute = currentLongitude;
        double lantitude = currentLatitude;

        // Verificar se o nome da categoria não está vazio
        if (!nomeArmazem.isEmpty()){
            // Incrementa o índice da categoria para criar uma nova categoria
            final int index = armazemIndex + 1;

            // Cria um objeto Categoria com o índice e nome fornecidos
            Armazem role = new Armazem("Armazem" + index, responsavelUUID, nomeArmazem, localizacao, lantitude, longitute);

            // Salva a categoria no Firebase usando o índice como chave
            databaseReference.child("Armazem" + index).setValue(role);

            // Exibe uma mensagem de sucesso
            Toast.makeText(this, "Armazem salvo!", Toast.LENGTH_SHORT).show();
            finish(); // Encerra a atividade
        } else {
            // Exibe uma mensagem de erro caso o nome da categoria esteja vazio
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarInformacoesGPS(View v) {
        // Verifica permissões e solicita se necessário
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CadastroArmazem.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(CadastroArmazem.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(CadastroArmazem.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return; // Sai do método se as permissões não forem concedidas
        }

        // Verifica se o GPS está habilitado
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Cria uma string com as coordenadas atuais
            String texto = "Latitude: " + currentLatitude + "\n" +
                    "Longitude: " + currentLongitude + "\n";
            // Mostra as coordenadas em um Toast
            Toast.makeText(CadastroArmazem.this, texto, Toast.LENGTH_LONG).show();

            // Chama o método para salvar a localização no Firebase
            salvarLocalizacaoNoFirebase(currentLatitude, currentLongitude);
        } else {
            // Mostra um Toast indicando que o GPS está desabilitado
            Toast.makeText(CadastroArmazem.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
        }

        // Chama o método para mostrar o Google Maps com as coordenadas atuais
        this.mostrarGoogleMaps(currentLatitude, currentLongitude);
    }

    // Método para exibir o Google Maps em um WebView
    public void mostrarGoogleMaps(double latitude, double longitude) {
        WebView wv = findViewById(R.id.webv);
        wv.getSettings().setJavaScriptEnabled(true); // Habilita o JavaScript no WebView
        wv.loadUrl("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude); // Carrega a URL do Google Maps com as coordenadas
    }

    // Método para salvar a localização no Firebase
    private void salvarLocalizacaoNoFirebase(double latitude, double longitude) {
        String id = databaseReference.push().getKey(); // Gera um ID único para a entrada no Firebase
        if (id != null) {
            Localizacao localizacao = new Localizacao(latitude, longitude); // Cria um objeto Localizacao com as coordenadas
            databaseReference.child(id).setValue(localizacao) // Salva a localização no Firebase
                    .addOnSuccessListener(aVoid -> Toast.makeText(CadastroArmazem.this, "Localização salva com sucesso", Toast.LENGTH_SHORT).show()) // Mostra um Toast de sucesso
                    .addOnFailureListener(e -> Toast.makeText(CadastroArmazem.this, "Erro ao salvar localização", Toast.LENGTH_SHORT).show()); // Mostra um Toast de erro
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        currentLatitude = location.getLatitude(); // Atualiza a latitude atual
        currentLongitude = location.getLongitude(); // Atualiza a longitude atual
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Método obsoleto, não é necessário implementar nada aqui
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        // Chamado quando um provedor de localização (como GPS) é habilitado
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        // Chamado quando um provedor de localização (como GPS) é desabilitado
    }
}
