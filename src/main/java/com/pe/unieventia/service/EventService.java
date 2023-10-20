package com.pe.unieventia.service;

import com.pe.unieventia.mappers.EventMapper;
import com.pe.unieventia.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

}
