package br.edu.fateczl.atividade04_01;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private EditText etDay;
    private EditText etMonth;
    private EditText etYear;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etDay = findViewById(R.id.etDay);
        etMonth = findViewById(R.id.etMonth);
        etYear = findViewById(R.id.etYear);
        Button btnCalc;
        btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> calc());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void calc() {
        int day = Integer.parseInt(etDay.getText().toString());
        int month = Integer.parseInt(etMonth.getText().toString());
        int year = Integer.parseInt(etYear.getText().toString());
        LocalDate today = LocalDate.now();
        int day_today = today.getDayOfMonth();
        int month_today = today.getMonthValue();
        int year_today = today.getYear();
        int total_days = 0;
        for (int curYear = year; curYear < year_today; curYear++) {
            total_days += 365;
            boolean leapYear = isLeapYear(curYear);
            if (leapYear) total_days += 1;
        }

    }

    private boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || year % 400 == 0;
    }


}