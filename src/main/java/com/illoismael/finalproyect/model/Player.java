package com.illoismael.finalproyect.model;

public class Player implements IPlayer{
    
    protected int id;
    protected String name;
    protected int age;
    protected int salary;
    
    //Constructor por defecto
    public Player() {
        this.id=-1;
        this.name="";
        this.age=-1;
        this.salary=-1;
    }

    //Constructor FULL
    public Player(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Player(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    

    @Override
    public String toString() {
        return "Player{" + "ido=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
