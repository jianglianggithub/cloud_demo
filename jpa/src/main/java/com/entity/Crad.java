package com.entity;


import javax.persistence.*;

@Table(name = "t_card")
@Entity
public class Crad {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "crad_name")
    String cradName;


    @Column(name = "card_id")
    Long cardId;

    @OneToOne(mappedBy = "crad", fetch = FetchType.EAGER)
    User user;

    public Long getId() {
        return id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCradName() {
        return cradName;
    }

    public void setCradName(String cradName) {
        this.cradName = cradName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
