package com.tvolkov.pbs.service;

import com.tvolkov.pbs.client.PrzybyszApiClient;
import com.tvolkov.pbs.configuration.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final Configuration configuration;
    private final PrzybyszApiClient przybyszApiClient;


}
