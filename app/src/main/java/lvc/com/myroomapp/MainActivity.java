package lvc.com.myroomapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lvc.com.myroomapp.database.DriverDAO;
import lvc.com.myroomapp.database.DriverDatabase;
import lvc.com.myroomapp.model.Address;
import lvc.com.myroomapp.model.Driver;

public class MainActivity extends AppCompatActivity {

    TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewData = findViewById(R.id.text_view_data);

        findViewById(R.id.button_list).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                new AsyncTask<Void,Void, List<Driver>>() {

                    @Override
                    protected List<Driver> doInBackground(Void... voids) {
                        DriverDAO driverDAO = DriverDatabase.getInstance(MainActivity.this).createDriverDAO();
                        return driverDAO.getAllDrivers();
                    }

                    @Override
                    protected void onPostExecute(List<Driver> drivers) {
                        super.onPostExecute(drivers);
                        textViewData.setText(" Size " + drivers.size() + " DATA = " + Driver.toString(drivers));
                    }

                }.execute();
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {


                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        DriverDAO driverDAO = DriverDatabase.getInstance(MainActivity.this).createDriverDAO();
                        List<Driver> drivers = driverDAO.getAllDrivers();
                        driverDAO.delete(drivers);
                        return null;
                    }

                }.execute();

            }
        });

        findViewById(R.id.button_insert).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        DriverDAO driverDAO = DriverDatabase.getInstance(MainActivity.this).createDriverDAO();

                        Driver driver = new Driver();
                        driver.setName("Antonio");
                        driver.setAge(45);
                        driver.setHiredDay(new Date());

                        Address address = new Address();
                        address.setCity("Santa Luzia");
                        address.setStreet("Irmãos Kennedy");
                        address.setState("MG");
                        address.setNeighborhood("Esplanada");
                        address.setNumber("124");
                        address.setCountry("Brasil");

                        driver.setAddress(address);

                        driverDAO.insert(driver);

                        return null;
                    }

                }.execute();

            }
        });

    }

}
