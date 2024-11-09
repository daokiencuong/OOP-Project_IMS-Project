package com.project.ims.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/*
CREATE TABLE Report (
    reportID INT PRIMARY KEY,
    reportName VARCHAR(100),
    reportDate DATE,
    fileReportPath VARCHAR(255)
);
 */

@Data
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportID;

    @Column(name = "reportName", nullable = false)
    private String reportName;

    @Column(name = "reportDate", nullable = false)
    private Date reportDate;

    @Column(name = "fileReportPath", nullable = false)
    private String fileReport;
}
