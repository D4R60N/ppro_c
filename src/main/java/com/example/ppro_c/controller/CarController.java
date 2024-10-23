package com.example.ppro_c.controller;

import com.example.ppro_c.model.Car;
import com.example.ppro_c.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {
        //cars.add(new Car("abs", "sa", "red", 1.0f, 4));
        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/detail/{index}")
    public String detail(@PathVariable int index, Model model) {
        Car car = carService.getCarById(index);
        if (car != null) {
            model.addAttribute("car", car);
            return "car_detail";
        }
        return "redirect:/cars/";
    }
    @GetMapping("/delete/{index}")
    public String delete(@PathVariable int index) {
        carService.deleteCarById(index);
        return "redirect:/cars/";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "car_edit";
    }
    @GetMapping("/edit/{index}")
    public String edit(@PathVariable int index, Model model) {

        Car car = carService.getCarById(index);
        car.setId(index);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        return "car_edit";
    }
    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "car_edit";
        }
        carService.saveCar(car);
        return "redirect:/cars/";
    }
}
