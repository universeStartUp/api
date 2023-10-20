package com.pe.unieventia.service;

import com.pe.unieventia.dto.DateRequestDTO;
import com.pe.unieventia.dto.DateResponseDTO;
import com.pe.unieventia.entity.Date;
import com.pe.unieventia.expection.ResourceAlreadyExistsException;
import com.pe.unieventia.expection.ResourceNotFoundException;
import com.pe.unieventia.expection.ValidationException;
import com.pe.unieventia.mappers.DateMapper;
import com.pe.unieventia.repository.DateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DateService {
    private final DateRepository dateRepository;
    private final DateMapper dateMapper;

    public List<DateResponseDTO> getAllDates(){
        List<Date> dates = dateRepository.findAll();
        return dateMapper.entityListToResponseResourceList(dates);
    }

    public DateResponseDTO getDateById(Long dateID) {
        Date date = dateRepository.findById(dateID)
                .orElseThrow(() -> new ResourceNotFoundException("Date not found with id: " + dateID));
        return dateMapper.entityToResponseResource(date);

    }

    public DateResponseDTO getDateByRange(LocalDateTime beginDate, LocalDateTime endDate){
        Date date  = dateRepository.findByBeginDateAndEndDate(beginDate, endDate)
                .orElseThrow(()->new ResourceNotFoundException("Date not found with this range:" + beginDate.toString() + " - "+ endDate.toString() ));
        return dateMapper.entityToResponseResource(date);
    }

    public List<DateResponseDTO> getDateByBetweenRange(LocalDateTime beginDate, LocalDateTime endDate){

        boolean correctDateRange = beginDate.isAfter(endDate);
        if(correctDateRange){
            throw  new ValidationException("Range of dates are not correct");
        }
        List< Date> dates = dateRepository.findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(beginDate,endDate);
       if(dates.isEmpty()){
           throw new ResourceNotFoundException("Doesn't exists dates between range of dates");
       }
       return dateMapper.entityListToResponseResourceList(dates);
    }

    @Transactional
    public DateResponseDTO createDate(DateRequestDTO dateRequestDTO){
        if(dateRequestDTO.getBeginDate() == null || dateRequestDTO.getEndDate() == null){
            throw new ValidationException("date must have beginDate and endDate");
        }

        if(dateRepository.
                existsByBeginDateAndEndDate(
                        dateRequestDTO.getBeginDate(),
                        dateRequestDTO.getEndDate())){
            throw new ResourceAlreadyExistsException(
                    "Date with this dates exists"+
                            dateRequestDTO.
                                    getBeginDate().
                                    toString() + " - " + dateRequestDTO.getEndDate().toString());
        }

        Date date  = dateMapper.resourceToEntity(dateRequestDTO);
        date = dateRepository.save(date);
        return dateMapper.entityToResponseResource(date);
    }

    @Transactional
    public DateResponseDTO updateDate(Long dateId, DateRequestDTO dateResource) {
        Optional<Date> optionalDate = dateRepository.findById(dateId);

        if (optionalDate.isPresent()) {
            Date date = optionalDate.get();

            date.setBeginDate(dateResource.getBeginDate());
            date.setEndDate(dateResource.getBeginDate());

            date = dateRepository.save(date);
            return dateMapper.entityToResponseResource(date);
        } else {
            throw new ResourceNotFoundException("date not found with id: " + dateId);
        }
    }


    @Transactional
    public void deleteDate(Long dateId) {
        if (!dateRepository.existsById(dateId)) {
            throw new ResourceNotFoundException("Date not found with id: " + dateId);
        }

        dateRepository.deleteById(dateId);
    }

}
