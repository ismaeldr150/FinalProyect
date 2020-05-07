package com.illoismael.finalproyect.model;

public class Person implements IPerson{
    private int id;
    private String name;
    private int age;
    private String phone;
    private Videogame videogame;
    private Team team;

    public Person() {
    }

    public Person(int id, String name, int age, String phone, Videogame videogame, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.videogame = videogame;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", age=" + age + ", phone=" + phone + ", videogame=" + videogame + ", team=" + team + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Person other = (Person) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    
}
