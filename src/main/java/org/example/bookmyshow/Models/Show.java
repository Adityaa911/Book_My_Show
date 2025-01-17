package org.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;
    private Date StartedAt;
    private Date EndedAt;
    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

}
