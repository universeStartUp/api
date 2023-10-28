package com.pe.unieventia.event.mapper;

import com.pe.unieventia.event.dto.DateRequestDTO;
import com.pe.unieventia.event.dto.DateResponseDTO;
import com.pe.unieventia.event.domain.entity.Date;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DateMapper {
    private final ModelMapper modelMapper;

    public DateMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public Date resourceToEntity(DateRequestDTO dateRequestDTO){
        return modelMapper.map(dateRequestDTO,Date.class);
    }

    public DateRequestDTO entityToResource (Date date){
        return modelMapper.map(date,DateRequestDTO.class);
    }

    public DateResponseDTO entityToResponseResource (Date date){
        return modelMapper.map(date,DateResponseDTO.class);
    }

    public List<DateResponseDTO> entityListToResponseResourceList (List<Date> dates){
        return dates
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public List<Date> resourceListToEntityList(List<DateRequestDTO> dateRequestDTOS){
        return dateRequestDTOS
                .stream()
                .map(this::resourceToEntity)
                .toList();

    }

    public List<DateRequestDTO> entityListToResourceList(List<Date> dates){
        return dates
                .stream()
                .map(this::entityToResource)
                .toList();
    }
}
