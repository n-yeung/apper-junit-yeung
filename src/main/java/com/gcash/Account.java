package com.gcash;

public class Account {
    private String id;
    private String name;
    private Double balance;
    // Constructor
    public Account(String id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for balance
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    // Getter for balance
    public Double getBalance() {
        return balance;
    }
}


/* sir orvyl's
package com.gcash;

public class Account {
    private String id;
    private String name;
    private Double balance;

    public Account(String id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
 */