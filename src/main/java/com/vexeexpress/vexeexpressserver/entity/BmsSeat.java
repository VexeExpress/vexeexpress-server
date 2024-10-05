package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seats")
@Data
public class BmsSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer floor;
    private Integer row;
    private Integer seatColumn;
    private String name;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "seat_map_id", referencedColumnName = "id", nullable = false)
    private BmsSeatMap bmsSeatMap;


}
