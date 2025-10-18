package com.example.webviewapp_bukov;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button backButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация кнопок и WebView
        webView = findViewById(R.id.webView);
        backButton = findViewById(R.id.backButton);
        exitButton = findViewById(R.id.exitButton);

        setupWebView();

        backButton.setOnClickListener(v -> goBackWebView());
        exitButton.setOnClickListener(v -> showExitDialog());
    }

    private void setupWebView() {
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.requestFocus();
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadUrl("https://cbr.ru");
    }

    private void goBackWebView() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            showExitDialog();
        }
    }

    private void showExitDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Подтверждение выхода");
        dialog.setMessage("Вы действительно хотите выйти из приложения?");
        dialog.setIcon(R.drawable.ic_launcher_foreground);
        dialog.setCancelable(false);

        dialog.setPositiveButton("Да", (dialogInterface, i) -> finish());
        dialog.setNegativeButton("Отмена", (dialogInterface, i) -> dialogInterface.dismiss());

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
