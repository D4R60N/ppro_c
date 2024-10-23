package com.example.ppro_c.service;

import com.example.ppro_c.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    ArrayList<Car> cars = new ArrayList<Car>();
    @Override
    public ArrayList<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        if (id >= cars.size() || id < 0) {
            return null;
        }
        return cars.get(id);
    }

    @Override
    public void deleteCarById(int id) {
        if (id < cars.size() && id >= 0) {
            Car car = cars.remove(id);
        }

    }

    @Override
    public void saveCar(Car car) {
        if (car.getId() > -1) {
            cars.remove(car.getId());
        }
        cars.add(car);
    }
}
