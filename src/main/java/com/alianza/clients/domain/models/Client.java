package com.alianza.clients.domain.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sharedKey;
    private String name;
    private String email;
    private String phone;
    private String dateAdded;
    private String startDate;
    private String endDate;

    public Client(String sharedKey, String name, String email, String phone, String dateAdded, String startDate, String endDate) {
          this. sharedKey = sharedKey;
          this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateAdded = dateAdded;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}