/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class BankCustomerDTO {

    private int id;
    private String fullName;
    private String accountNumber;
    private double balance;

    public BankCustomerDTO() {
    }

    public BankCustomerDTO(BankCustomer bankcus) {
        this.id = bankcus.getId();
        this.fullName = bankcus.getFirstName() + " " + bankcus.getLastName();
        this.accountNumber = bankcus.getAccountNumber();
        this.balance = bankcus.getBalance();
    }

    public static List<BankCustomerDTO> getDtos(List<BankCustomer> bcls) {
        List<BankCustomerDTO> bcdtoslis = new ArrayList();
        bcls.forEach(bc -> bcdtoslis.add(new BankCustomerDTO(bc)));
        return bcdtoslis;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BankCustomerDTO{fullName=").append(fullName);
        sb.append(", accountNumber=").append(accountNumber);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }

}
