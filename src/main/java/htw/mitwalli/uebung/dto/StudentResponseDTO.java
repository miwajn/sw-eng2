package htw.mitwalli.uebung.dto;

public record StudentResponseDTO(Long id, String firstname, String lastname, int currentSemester, String email, RoleResponseDTO role) {

}
