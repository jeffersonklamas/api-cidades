package com.github.jeffersonklamas.cidadesapi.countries.repository;

import com.github.jeffersonklamas.cidadesapi.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
