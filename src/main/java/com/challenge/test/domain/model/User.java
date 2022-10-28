package com.challenge.test.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @Column(name = "cpf_cnpj", unique = true)
    @NotNull
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @Column(unique = true, nullable = false)
    @NotNull
    private String phone;

    @NotNull
    private String password;

    @NotNull
    private String pin;

    private BigDecimal balance;

    private String ag;

    private String acc;

    @OneToMany(mappedBy = "userReceiverId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Extract> extractList;

}
