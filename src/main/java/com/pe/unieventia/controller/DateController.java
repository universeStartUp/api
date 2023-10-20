package com.pe.unieventia.controller;

import com.pe.unieventia.dto.DateRequestDTO;
import com.pe.unieventia.dto.DateResponseDTO;
import com.pe.unieventia.service.DateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/dates")
@RequiredArgsConstructor
public class DateController {
    private final DateService dateService;
    @GetMapping
    public  ResponseEntity<List<DateResponseDTO>> getAllDates(){
        List<DateResponseDTO> dateResponseDTOS = dateService.getAllDates();
        return new ResponseEntity<>(dateResponseDTOS,HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<DateResponseDTO> createDate(@Valid @RequestBody DateRequestDTO dateRequestDTO){
        DateResponseDTO dateResponseDTO = dateService.createDate(dateRequestDTO);
        return new ResponseEntity<>(dateResponseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/{dateId}")
    public ResponseEntity<DateResponseDTO> getDateId(@PathVariable Long dateId) {
        DateResponseDTO customerResponseResource = dateService.getDateById(dateId);
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @GetMapping("/date/date-range")
    public ResponseEntity<DateResponseDTO> getDateByBeginDateAndEndDate(@RequestParam LocalDateTime beginDate, @RequestParam LocalDateTime endDate) {
        DateResponseDTO customerResponseResource = dateService.getDateByRange(beginDate,endDate);
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<DateResponseDTO>> getAllDatesByBeginDateAndEndDate(@RequestParam LocalDateTime beginDate, @RequestParam LocalDateTime endDate) {
        List<DateResponseDTO> customerResponseResource = dateService.getDateByBetweenRange(beginDate,endDate);
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @PutMapping("/{dateId}")
    public ResponseEntity<DateResponseDTO> updateDate(
            @PathVariable Long dateId,
            @Valid @RequestBody DateRequestDTO dateRequestDTO) {
        DateResponseDTO dateResponseResource = dateService.updateDate(dateId, dateRequestDTO);
        return new ResponseEntity<>(dateResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{dateId}")
    public ResponseEntity<Void> deleteDate(@PathVariable Long dateId) {
        dateService.deleteDate(dateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
