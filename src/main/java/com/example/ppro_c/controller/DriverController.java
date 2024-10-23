package com.example.ppro_c.controller;

import com.example.ppro_c.model.Driver;
import com.example.ppro_c.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String list(Model model) {
        //drivers.add(new Driver("abs", "sa", "red", 1.0f, 4));
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "driver_list";
    }

    @GetMapping("/detail/{index}")
    public String detail(@PathVariable int index, Model model) {
        Driver driver = driverService.getDriverById(index);
        if (driver != null) {
            model.addAttribute("driver", driver);
            return "driver_detail";
        }
        return "redirect:/drivers/";
    }
    @GetMapping("/delete/{index}")
    public String delete(@PathVariable int index) {
        driverService.deleteDriverById(index);
        return "redirect:/drivers/";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("edit", false);
        return "driver_edit";
    }
    @GetMapping("/edit/{index}")
    public String edit(@PathVariable int index, Model model) {

        Driver driver = driverService.getDriverById(index);
        driver.setId(index);
        model.addAttribute("driver", driver);
        model.addAttribute("edit", true);
        return "driver_edit";
    }
    @PostMapping("/save")
    public String save(@Valid Driver driver, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "driver_edit";
        }

        driverService.saveDriver(driver);
        return "redirect:/drivers/";
    }
}
