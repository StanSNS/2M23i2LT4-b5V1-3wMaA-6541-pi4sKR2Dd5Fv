package gasStation.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Table(name = "gas_stations")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GasStationEntity {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String brand;

    @Column
    private String street;

    @Column
    private String place;

    @Column
    private double lat;

    @Column
    private double lng;

    @Column
    private double dist;

    @Column
    private double diesel;

    @Column
    private double e5;

    @Column
    private double e10;

    @Column
    private boolean isOpen;

    @Column
    private String houseNumber;

    @Column
    private int postCode;

}
