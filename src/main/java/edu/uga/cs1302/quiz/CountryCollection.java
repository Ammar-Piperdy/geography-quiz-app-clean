package edu.uga.cs1302.quiz;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Collections;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

public class CountryCollection {
    private ArrayList<Country> countries;

    public CountryCollection() {
        countries = new ArrayList<>();
        loadCountriesFromCSV();
    }

    private void loadCountriesFromCSV() {
        try {
            InputStream in = getClass().getResourceAsStream("/country_continent.csv");
            InputStreamReader reader = new InputStreamReader(in);
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            
            for (CSVRecord record : parser) {
                String country = record.get(0).trim();
                String continent = record.get(1).trim();
                countries.add(new Country(country, continent));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Country getCountry(int index) {
        return countries.get(index);
    }

    public int size() {
        return countries.size();
    }

    public List<Country> getAllCountries() {
        return Collections.unmodifiableList(countries);
    }

    public List<Country> getRandomCountries(int count) {
        List<Country> copy = new ArrayList<>(countries);
        Collections.shuffle(copy);
        return copy.subList(0, count);
    }

    public List<String> getAllContinents() {
        return countries.stream()
            .map(Country::getContinent)
            .distinct()
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return countries.stream()
                .map(Country::toString)
                .collect(Collectors.joining("\n"));
    }
}
