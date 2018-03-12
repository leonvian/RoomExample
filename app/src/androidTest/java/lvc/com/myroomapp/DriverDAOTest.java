package lvc.com.myroomapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.CacheRequest;
import java.util.Date;
import java.util.List;

import lvc.com.myroomapp.database.DriverDAO;
import lvc.com.myroomapp.database.DriverDatabase;
import lvc.com.myroomapp.model.Address;
import lvc.com.myroomapp.model.Driver;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by leonardo2050 on 18/02/18.
 */

@RunWith(AndroidJUnit4.class)
public class DriverDAOTest {

    private DriverDAO driverDAO;
    private DriverDatabase driverDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getContext();
        driverDatabase = Room.inMemoryDatabaseBuilder(context, DriverDatabase.class).build();
        driverDAO = driverDatabase.createDriverDAO();
    }

    @After
    public void closeDb() {
      driverDatabase.close();
    }

    @Test
    public void shouldSaveAndGetDriver() {
        Driver driver = createDriver();
        driverDAO.insert(driver);
        List<Driver> drivers  = driverDAO.getAllDrivers();

        assertThat(drivers.size(), is(1));

        Driver loadedDriver = drivers.get(0);
        assertThat(loadedDriver.getName(), is("Antonio"));
        assertThat(loadedDriver.getAge(), is(45));
        assertThat(loadedDriver.getHiredDay().getTime(), is(fakeHiredDate.getTime()));

        assertThat(loadedDriver.getAddress().getCity(), is("Santa Luzia"));
        assertThat(loadedDriver.getAddress().getStreet(), is("Irmãos Kennedy"));
        assertThat(loadedDriver.getAddress().getNeighborhood(), is("Esplanada"));
        assertThat(loadedDriver.getAddress().getNumber(), is("124"));
        assertThat(loadedDriver.getAddress().getCountry(), is("Brasil"));
    }

    @Test
    public void shouldDeleteAllDrivers() {
        Driver driver = createDriver();
        driverDAO.insert(driver);
        List<Driver> drivers  = driverDAO.getAllDrivers();
        assertThat(drivers.size(), is(1));

        driverDAO.delete(drivers);

        assertThat(driverDAO.getAllDrivers().size(), is(0));
    }


    private static Driver createDriver() {
        Driver driver = new Driver();
        driver.setName("Antonio");
        driver.setAge(45);
        driver.setHiredDay(fakeHiredDate);

        Address address = new Address();
        address.setCity("Santa Luzia");
        address.setStreet("Irmãos Kennedy");
        address.setState("MG");
        address.setNeighborhood("Esplanada");
        address.setNumber("124");
        address.setCountry("Brasil");

        driver.setAddress(address);

        return driver;
    }


    private static Date fakeHiredDate = new Date();

}
