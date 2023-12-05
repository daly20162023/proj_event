package tn.esprit.eventsproject.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

import java.time.LocalDate;
import java.util.*;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

    @Test
    void addAffectEvenParticipant_WithId() {
        // Arrange
        Event event = new Event();
        int idParticipant = 1;
        Participant participant = new Participant();
        participant.setIdPart(idParticipant);
        when(participantRepository.findById(idParticipant)).thenReturn(Optional.of(participant));
        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Event result = eventServices.addAffectEvenParticipant(event, idParticipant);

        // Assert
        verify(participantRepository, times(1)).findById(idParticipant);
        verify(eventRepository, times(1)).save(event);
        assert result.equals(event);
    }

    @Test
    void addAffectEvenParticipant_WithoutId() {
        // Arrange
        Event event = new Event();
        Set<Participant> participants = new HashSet<>(Collections.singletonList(new Participant()));
        event.setParticipants(participants);
        when(eventRepository.save(event)).thenReturn(event);
        when(participantRepository.findById(anyInt())).thenReturn(Optional.of(new Participant()));

        // Act
        Event result = eventServices.addAffectEvenParticipant(event);

        // Assert
        verify(participantRepository, times(participants.size())).findById(anyInt());
        verify(eventRepository, times(1)).save(event);
        assert result.equals(event);
    }

    @Test
    void addAffectLog() {
        // Arrange
        Logistics logistics = new Logistics();
        String descriptionEvent = "Sample Event";
        Event event = new Event();
        when(eventRepository.findByDescription(descriptionEvent)).thenReturn(event);
        when(logisticsRepository.save(logistics)).thenReturn(logistics);

        // Act
        Logistics result = eventServices.addAffectLog(logistics, descriptionEvent);

        // Assert
        verify(eventRepository, times(1)).findByDescription(descriptionEvent);
        verify(eventRepository, times(1)).save(event);
        verify(logisticsRepository, times(1)).save(logistics);
        assert result.equals(logistics);
    }

    // Add similar tests for other methods




}

