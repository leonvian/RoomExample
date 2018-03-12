package lvc.com.myroomapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import lvc.com.myroomapp.model.Driver;

@Database(entities = {Driver.class}, version = 2)
@TypeConverters({RoomTypeConverters.class})
public abstract class DriverDatabase extends RoomDatabase {

    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Driver ADD COLUMN hiredDay INTEGER");
        }
    };

    private static DriverDatabase driverDatabase;

    public abstract DriverDAO createDriverDAO();

    public static DriverDatabase getInstance(Context context) {
        if(driverDatabase == null) {
            driverDatabase = Room.databaseBuilder(context.getApplicationContext(), DriverDatabase.class, "driver_database")
                    //.fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }

        return driverDatabase;
    }
}
