package br.edu.fateczl.atividade04_01;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    /*
     *@author:Gustavo Guimarães de Oliveira
     */
    private EditText etDay;
    private EditText etMonth;
    private EditText etYear;

    private TextView tvRes;

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
        tvRes = findViewById(R.id.tvRes);
        Button btnCalc;
        btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> calc());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void calc() {
        tvRes.setText("");
        LocalDate today = LocalDate.now();

        int day = Integer.parseInt(etDay.getText().toString());
        int month = Integer.parseInt(etMonth.getText().toString());
        int year = Integer.parseInt(etYear.getText().toString());

        int day_today = today.getDayOfMonth();
        int month_today = today.getMonthValue();
        int year_today = today.getYear();

        int birthday_formatted = Integer.parseInt(String.format("%04d%02d%02d", year, month, day));
        int today_formatted = Integer.parseInt(String.format("%04d%02d%02d", year_today, month_today, day_today));

        if (birthday_formatted >= today_formatted) {
            tvRes.setText("Deve ser uma data menor que a de hoje");
            return;
        }

        int days, months, years;
        if (day_today < day) {
            month_today -= 1;
            day_today += daysOfMonth(year, month);
        }
        days = day_today - day;
        if (month_today < month) {
            year_today -= 1;
            month_today += 12;
        }
        months = month_today - month;
        years = year_today - year;

        String res = String.format("%d anos\n%d meses\n%d dias", years, months, days);
        res = (years == 1) ? res.replace("anos", "ano"): res;
        res = (months == 1) ? res.replace("meses", "mês"): res;
        res = (days == 1) ? res.replace("dias", "dia"): res;
        tvRes.setText(res);
    }

    public int daysOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            return isLeapYear(year) ? 29: 28;
        } else {
            return 0;
        }
    }
    private boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }




}