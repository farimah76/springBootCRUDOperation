package com.javacrud.model;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "Books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @NonNull
    private int bookId;
    @Column
    private String bookName;
    @Column
    private String author;
    @Column
    private int price;

}
