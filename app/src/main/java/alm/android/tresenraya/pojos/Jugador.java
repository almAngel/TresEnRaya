package alm.android.tresenraya.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Jugador {

    @JsonProperty("id")
    public String id;
    @JsonProperty("nombre")
    public String email;
    @JsonProperty("activo")
    public Boolean activo;
    @JsonProperty("jugando")
    public Boolean jugando;
    @JsonProperty("ganadas")
    public Integer ganadas;
}
