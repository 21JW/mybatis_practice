package com.example.mybatis_practice_user.controller;

import com.example.mybatis_practice_user.Service.FlightService;
import com.example.mybatis_practice_user.model.dto.FlightDTO;
import com.example.mybatis_practice_user.model.dto.FlightSearchDTO;
import com.example.mybatis_practice_user.model.dto.IdListDTO;
import com.example.mybatis_practice_user.model.dto.UserDTO;
import com.example.mybatis_practice_user.model.entity.Flight;
import com.example.mybatis_practice_user.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private FlightService flightService;
    @Autowired
    public FlightController(FlightService flightService){
        this.flightService=flightService;
    }
    @PostMapping
    public ResponseEntity<?> Register(@RequestBody @Validated(FlightDTO.Insert.class) FlightDTO dto) {
        flightService.addFlight(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public Flight getFlightById(@PathVariable Integer id)
    {
        Flight flight=flightService.getFlightById(id);
        return flight;
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@RequestBody @Validated(FlightDTO.Update.class) FlightDTO dto, @PathVariable Integer id) {
        Flight flight = flightService.updateFlightById(dto, id);
        return flight;
    }

    @DeleteMapping("/{id}")
    public void deleteFlightById(@PathVariable Integer id)
    {
        flightService.deleteFlightById(id);
    }

    @GetMapping("/search")
    public List<Flight> searchFlight(@RequestBody FlightSearchDTO dto) {
        List<Flight> response=flightService.searchFlight(dto);
        return response;
    }

    @GetMapping("/searchByParams")
    public List<Flight> searchFlightByParams(@RequestBody FlightSearchDTO dto) {
        List<Flight> response=flightService.searchFlightByParams(dto);
        return response;
    }
}
