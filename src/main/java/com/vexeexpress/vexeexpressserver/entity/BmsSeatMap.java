package com.vexeexpress.vexeexpressserver.entity;

import io.micrometer.common.KeyValues;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seat_maps")
@Data
public class BmsSeatMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "seat_map_name")
    private String seatMapName;
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "row")
    private Integer row;
    @Column(name = "seat_column")
    private Integer seatColumn;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

    @OneToMany(mappedBy = "bmsSeatMap", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BmsSeat> seats = new ArrayList<>();


}
