package com.example.bookStore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="MyBooks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MyBookList {
    @Id
    private Long id;
    private String name;
    private String author;
    private String price;
}
