package lvc.com.myroomapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;
import java.util.List;


@Entity
public class Driver {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int age;

    private Date hiredDay;

    @Embedded
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getHiredDay() {
        return hiredDay;
    }

    public void setHiredDay(Date hiredDay) {
        this.hiredDay = hiredDay;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", hiredDay=" + hiredDay +
                '}';
    }

    public static String toString(List<Driver> drivers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n \n");
        for (Driver driver : drivers) {
            stringBuilder.append(driver);
            stringBuilder.append("\n \n");
        }

        return stringBuilder.toString();
    }
}
