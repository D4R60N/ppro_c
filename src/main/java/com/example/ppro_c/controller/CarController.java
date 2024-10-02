package com.example.ppro_c.controller;

import com.example.ppro_c.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    List<Car> cars = new ArrayList<Car>();

    @GetMapping("/")
    public String list(Model model) {
        cars.add(new Car("abs", "sa", "red", 1.0f, 4));
        model.addAttribute("cars", cars);
        return "list";
    }

    @GetMapping("/detail/{index}")
    public String detail(@PathVariable int index, Model model) {
        if (index >= cars.size() || index < 0) {
            return "redirect:/";
        }
        Car car = cars.get(index);
        model.addAttribute("car", car);
        return "detail";
    }
}
