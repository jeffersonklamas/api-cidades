package com.github.jeffersonklamas.cidadesapi.countries;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
public class Country {

    @Id
    private Long id;

    @Column (name = "nome")
    private String name;

    @Column (name = "nome_pt")
    private String portuguesName;

    @Column (name = "sigla")
    private String code;

    private Integer bacen;

    //    Digitei Fn+Alt+Ind e criei Constructor sem nada
    public Country() {
    }

    //    Digitei Fn+Alt+Ind e criei todos os Getter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPortuguesName() {
        return portuguesName;
    }

    public String getCode() {
        return code;
    }

    public Integer getBancen() {
        return bacen;
    }
}
