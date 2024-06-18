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
import com.example.mankey.helpsuprimentos.model.Localizacao;
import com.example.mankey.helpsuprimentos.model.ServicoMedico;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CadastroServicoMedico extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private double currentLatitude = 0.0;
    private double currentLongitude = 0.0;
    private EditText usuarioUUID;
    private EditText tipo;
    private EditText edtLocalizacao;
    private Button btnSalvarServico;
    private DatabaseReference databaseReference; // Referência para o banco de dados
    private int servicoIndex; // Variável para armazenar o índice atual dos Pontos de Distribuição

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_medico_cadastro);

        usuarioUUID = findViewById(R.id.edtUsuarioUUID);
        edtLocalizacao = findViewById(R.id.edtLocalizacao);
        tipo = findViewById(R.id.edtTipoAtendimento);
        btnSalvarServico = findViewById(R.id.btnSalvarServico);

        databaseReference = FirebaseDatabase.getInstance().getReference("servico");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                servicoIndex = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });

        btnSalvarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarServico();
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Verifica permissões e solicita se necessário
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return; // Sai do método se as permissões não forem concedidas
        }

        // Solicita atualizações de localização
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, this);

        // Tenta obter a última localização conhecida
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownLocation != null) {
            currentLatitude = lastKnownLocation.getLatitude();
            currentLongitude = lastKnownLocation.getLongitude();
        }
    }

    private void salvarServico() {
        String usuario = usuarioUUID.getText().toString().trim();
        String tipoAtendimento = tipo.getText().toString().trim();
        String localizacao = edtLocalizacao.getText().toString().trim();
        double longitude = currentLongitude;
        double latitude = currentLatitude;

        if (!usuario.isEmpty()) {
            final int index = servicoIndex + 1;

            ServicoMedico servico = new ServicoMedico("ServicoAtendimento" + index, usuario, localizacao, tipoAtendimento, latitude, longitude);

            databaseReference.child("Servico" + index).setValue(servico);

            Toast.makeText(this, "Serviço Médico cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Exibe mensagem de erro
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarInformacoesGPS(View v) {
        // Verifica permissões e solicita se necessário
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return; // Sai do método se as permissões não forem concedidas
        }

        // Verifica se o GPS está habilitado
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Cria uma string com as coordenadas atuais
            String texto = "Latitude: " + currentLatitude + "\n" +
                    "Longitude: " + currentLongitude + "\n";
            // Mostra as coordenadas em um Toast
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

            // Chama o método para salvar a localização no Firebase
            salvarLocalizacaoNoFirebase(currentLatitude, currentLongitude);
        } else {
            // Mostra um Toast indicando que o GPS está desabilitado
            Toast.makeText(this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
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
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Localização salva com sucesso", Toast.LENGTH_SHORT).show()) // Mostra um Toast de sucesso
                    .addOnFailureListener(e -> Toast.makeText(this, "Erro ao salvar localização", Toast.LENGTH_SHORT).show()); // Mostra um Toast de erro
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
