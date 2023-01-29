package com.tvolkov.pbs.service;

import com.tvolkov.pbs.client.PrzybyszApiClient;
import com.tvolkov.pbs.configuration.properties.PrzybyszProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final PrzybyszProperties przybyszProperties;
    private final PrzybyszApiClient przybyszApiClient;


}
