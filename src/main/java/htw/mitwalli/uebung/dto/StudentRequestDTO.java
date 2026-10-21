package htw.mitwalli.uebung.dto;

public record StudentRequestDTO(String firstname, String lastname, String password, String email, int currentSemester, Long roleId) {

}
