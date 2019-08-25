/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhumanov;

import java.util.Date;


public class StudentK extends Student{
    
    public StudentK(int id, String name, String surname, Date dateOfBirth) {
        super(id, name, surname, dateOfBirth);
    }

    @Override
    public String addInfo() {
        int day = this.getDateOfBirth().getDate();
        int month = this.getDateOfBirth().getMonth();
        int year = this.getDateOfBirth().getYear();
        String s;
        if (month == 2 && day >= 21 || month == 3 && day <= 19) {
            s= "Beran";
        } else if (month == 3 && day >= 20 || month == 4 && day <= 20) {
            s= "Byk";
        } else if (month == 4 && day >= 21 || month == 5 && day <= 21) {
            s= "Blizenci";
        } else if (month == 5 && day >= 22 || month == 6 && day <= 22) {
            s= "Rak";
        } else if (month == 6 && day >= 23 || month == 7 && day <= 22) {
            s= "Lev";
        } else if (month == 7 && day >= 23 || month == 8 && day <= 22) {
            s= "Panna";
        } else if (month == 8 && day >= 23 || month == 9 && day <= 22) {
            s= "Vahy";
        } else if (month == 9 && day >= 23 || month == 10 && day <= 21) {
            s= "Hadonos";
        } else if (month == 10 && day >= 22 || month == 11 && day <= 21) {
            s= "Strelec";
        } else if (month == 11 && day >= 22 || month == 0 && day <= 19) {
            s= "Kozoroh";
        } else
        if (month == 0 && day >= 20 || month == 1 && day <= 18) {
            s= "Vodnar";
        } else if (month == 1 && day >= 19 || month == 2 && day <= 20) {
            s= "Ryby";
        } else  {
            s= "Chyba daty narozeni";
        }
        
        if(year%4==0 && year%100 !=0 || year%400==0){
            return s + ", Rok je prestupny";
        }else {
            return s + ", Rok neni prestupny";
        }
    }

    @Override
    public String toString() {
        return "K: " + super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
    
    
    
    
}
