package lvc.com.myroomapp.model;

import android.arch.persistence.room.Entity;

/**
 * Created by leonardo2050 on 18/02/18.
 */

@Entity
public class Truck {

    private int id;
    private String model;
    private String plate;
    private int capacity;

}
