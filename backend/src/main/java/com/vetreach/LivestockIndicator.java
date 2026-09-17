package com.vetreach;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class LivestockIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String species;
    private double value;
    private String measureType;
    private String datasetScope;
    private String source;
    private int sourceYear;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getMeasureType() { return measureType; }
    public void setMeasureType(String measureType) { this.measureType = measureType; }

    public String getDatasetScope() { return datasetScope; }
    public void setDatasetScope(String datasetScope) { this.datasetScope = datasetScope; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public int getSourceYear() { return sourceYear; }
    public void setSourceYear(int sourceYear) { this.sourceYear = sourceYear; }

    public Municipality getMunicipality() { return municipality; }
    public void setMunicipality(Municipality municipality) { this.municipality = municipality; }
}
