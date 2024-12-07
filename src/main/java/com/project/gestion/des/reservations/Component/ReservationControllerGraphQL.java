package com.project.gestion.des.reservations.Component;

import com.project.gestion.des.reservations.entities.Reservation;
import com.project.gestion.des.reservations.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ReservationControllerGraphQL {

    @Autowired
    private ReservationService reservationService;

    // Query pour consulter une réservation par son ID
    @QueryMapping
    public Reservation consulterReservation(@Argument Long reservationId) {
        return reservationService.consulterReservation(reservationId)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
    }

    // Mutation pour créer une réservation
    @MutationMapping
    public Reservation creerReservation(
            @Argument String nomClient,
            @Argument String prenomClient,
            @Argument String emailClient,
            @Argument String telephoneClient,
            @Argument String typeChambre,
            @Argument BigDecimal prixChambre,
            @Argument LocalDate dateDebut,
            @Argument LocalDate dateFin,
            @Argument String preferences) {
        return reservationService.creerReservation(
                nomClient, prenomClient, emailClient, telephoneClient,
                typeChambre, prixChambre, dateDebut, dateFin, preferences
        );
    }

    // Mutation pour modifier une réservation
    @MutationMapping
    public Reservation modifierReservation(
            @Argument Long reservationId,
            @Argument Long nouvelleReservationId) {
        return reservationService.modifierReservation(reservationId, nouvelleReservationId);
    }

    // Mutation pour supprimer une réservation
    @MutationMapping
    public boolean supprimerReservation(@Argument Long reservationId) {
        reservationService.supprimerReservation(reservationId);
        return true;
    }


}
