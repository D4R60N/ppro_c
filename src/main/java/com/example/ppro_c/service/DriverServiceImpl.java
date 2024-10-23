package com.example.ppro_c.service;

import com.example.ppro_c.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DriverServiceImpl implements DriverService {
    ArrayList<Driver> drivers = new ArrayList<Driver>();
    @Override
    public ArrayList<Driver> getAllDrivers() {
        return drivers;
    }

    @Override
    public Driver getDriverById(int id) {
        if (id >= drivers.size() || id < 0) {
            return null;
        }
        return drivers.get(id);
    }

    @Override
    public void deleteDriverById(int id) {
        if (id < drivers.size() && id >= 0) {
            Driver driver = drivers.remove(id);
        }

    }

    @Override
    public void saveDriver(Driver driver) {
        if (driver.getId() > -1) {
            drivers.remove(driver.getId());
        }
        drivers.add(driver);
    }
}
