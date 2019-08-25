/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhumanov;

import java.time.DateTimeException;
import java.util.Date;


public class StudentH extends Student{
    
    public StudentH(int id, String name, String surname, Date dateOfBirth) {
        super(id, name, surname, dateOfBirth);
    }

    @Override
    public String addInfo() {
        int day = this.getDateOfBirth().getDate();
        int month = this.getDateOfBirth().getMonth();
        
        if (month == 2 && day >= 21 || month == 3 && day <= 19) {
            return "Beran";
        } else if (month == 3 && day >= 20 || month == 4 && day <= 20) {
            return "Byk";
        } else if (month == 4 && day >= 21 || month == 5 && day <= 21) {
            return "Blizenci";
        } else if (month == 5 && day >= 22 || month == 6 && day <= 22) {
            return "Rak";
        } else if (month == 6 && day >= 23 || month == 7 && day <= 22) {
            return "Lev";
        } else if (month == 7 && day >= 23 || month == 8 && day <= 22) {
            return "Panna";
        } else if (month == 8 && day >= 23 || month == 9 && day <= 22) {
            return "Vahy";
        } else if (month == 9 && day >= 23 || month == 10 && day <= 21) {
            return "Hadononos";
        } else if (month == 10 && day >= 22 || month == 11 && day <= 21) {
            return "Strelec";
        } else if (month == 11 && day >= 22 || month == 0 && day <= 19) {
            return "Kozoroh";
        } else
        if (month == 0 && day >= 20 || month == 1 && day <= 18) {
            return "Vodnar�";
        } else if (month == 1 && day >= 19 || month == 2 && day <= 20) {
            return "Ryby";
        } else  {
            return "Chyba daty narozeni";
        }
    }

    @Override
    public String toString() {
        return "H: " + super.toString();
    }

    
    
    
    
    
    
    
    
}
