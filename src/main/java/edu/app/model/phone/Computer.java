package edu.app.model.phone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "computers")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Computer implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @NotEmpty(message = "Computer name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;
    @Min(value = 1, message = "Price should be greater than one.")
    @Max(value = 10000, message = "Price should be below than 10000")
    private double price;
    @NotEmpty(message = "Processor should not be empty")
    private String processor;
    @NotEmpty(message = "Img link should not be empty")
    private String img;
    @Enumerated(value = EnumType.STRING)
    private OperatingSystem os;
    @Temporal(TemporalType.DATE)
    private Date dateOfAdded;
    private double screenSize;
    private int randomAccessMemory;
    private int numberOfMainCameras;
    private int numberOfMatrixPoints;
    private int ssdCapacity;
    private int cpuClockSpeed;
    private String graphicsAccelerator;
    private String bodyMaterial;
    private String backCoverMaterial;
    private String bodyColor;
    private double length;
    private double width;
    private double thickness;
    private int weight;
    @Enumerated(value = EnumType.STRING)
    private ScreenTechnology screenTechnology;
    private int screenRefreshRate;
    private int accumulatorCapacity;
    private String accumulatorType;
    private String wifi;
    private String connectionConnector;

    private static final long serialVersionUID = 6295618226040646585L;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> images = new ArrayList<>();


    public Computer() {
        dateOfAdded = new Date();
    }

    public Computer(String name, double price, String processor, String img) {
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.img = img;
    }

    public Computer(long id, String name, double price, String processor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.processor = processor;
    }

    public Computer(String name, double price, String processor) {
        this.name = name;
        this.price = price;
        this.processor = processor;
    }


}