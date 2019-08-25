/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhumanov;

import java.util.Date;


public class StudentT extends Student{
    
    public StudentT(int id, String name, String surname, Date dateOfBirth) {
        super(id, name, surname, dateOfBirth);
    }

    @Override
    public String addInfo() {
        int year = this.getDateOfBirth().getYear();
        if(year%4==0 && year%100 !=0 || year%400==0){
            return "Rok je prestupny";
        }else {
            return "Rok neni prestupny";
        }
    }
    
        @Override
    public String toString() {
        return "T: " + super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
