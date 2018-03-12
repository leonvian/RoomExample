package lvc.com.myroomapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import lvc.com.myroomapp.model.Driver;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface DriverDAO {

    @Query("SELECT * FROM Driver")
    List<Driver> getAllDrivers();

    @Query("SELECT id,name,age FROM Driver")
    List<Driver> getAllDriversNames();

    @Query("SELECT * FROM Driver WHERE name = :name")
    List<Driver> getDriversByName(String name);

    @Insert(onConflict = REPLACE)
    void insert(Driver driver);

    @Insert(onConflict = REPLACE)
    void insertAll(List<Driver> drivers);

    @Update
    void update(Driver driver);

    @Delete
    void delete(Driver driver);

    @Delete
    void delete(List<Driver> driver);

}
