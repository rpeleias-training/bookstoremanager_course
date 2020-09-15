package com.rodrigopeleias.bookstoremanager.publishers.service;

import com.rodrigopeleias.bookstoremanager.publishers.builder.PublisherDTOBuilder;
import com.rodrigopeleias.bookstoremanager.publishers.dto.PublisherDTO;
import com.rodrigopeleias.bookstoremanager.publishers.entity.Publisher;
import com.rodrigopeleias.bookstoremanager.publishers.exception.PublisherAlreadyExistsException;
import com.rodrigopeleias.bookstoremanager.publishers.mapper.PublisherMapper;
import com.rodrigopeleias.bookstoremanager.publishers.repository.PublisherRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {

    private final PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private PublisherDTOBuilder publisherDTOBuilder;

    @BeforeEach
    void setUp() {
        publisherDTOBuilder = PublisherDTOBuilder.builder().build();
    }

    @Test
    void whenNewPublisherIsInformedThenItShouldBeCreated() {
        PublisherDTO expectedPublisherToCreateDTO = publisherDTOBuilder.buildPublisherDTO();
        Publisher expectedPublisherCreated = publisherMapper.toModel(expectedPublisherToCreateDTO);

        when(publisherRepository.findByNameOrCode(expectedPublisherToCreateDTO.getName(), expectedPublisherToCreateDTO.getCode()))
                .thenReturn(Optional.empty());
        when(publisherRepository.save(expectedPublisherCreated)).thenReturn(expectedPublisherCreated);

        PublisherDTO createdPublisherDTO = publisherService.create(expectedPublisherToCreateDTO);

        assertThat(createdPublisherDTO, is(equalTo(expectedPublisherToCreateDTO)));
    }

    @Test
    void whenExistingPublisherIsInformedThenAnExceptionShouldBeThrown() {
        PublisherDTO expectedPublisherToCreateDTO = publisherDTOBuilder.buildPublisherDTO();
        Publisher expectedPublisherDuplicated = publisherMapper.toModel(expectedPublisherToCreateDTO);

        when(publisherRepository.findByNameOrCode(expectedPublisherToCreateDTO.getName(), expectedPublisherToCreateDTO.getCode()))
                .thenReturn(Optional.of(expectedPublisherDuplicated));

        assertThrows(PublisherAlreadyExistsException.class, () -> publisherService.create(expectedPublisherToCreateDTO));
    }
}
