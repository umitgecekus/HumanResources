package com.umit.service;

import com.umit.repository.BreakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreakService {

    private final BreakRepository breakRepository;

}
