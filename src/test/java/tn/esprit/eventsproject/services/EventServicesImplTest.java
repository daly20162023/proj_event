package tn.esprit.eventsproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;


import static org.mockito.Mockito.*;

class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @Test
    void addParticipant() {
        // Arrange
        Participant participant = new Participant();
        when(participantRepository.save(participant)).thenReturn(participant);

        // Act
        Participant result = eventServices.addParticipant(participant);

        // Assert
        verify(participantRepository, times(1)).save(participant);
        assert result.equals(participant);
    }
}