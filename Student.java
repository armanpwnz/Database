/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhumanov;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Student implements Comparable<Student>{
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private ArrayList<Integer> marks;
    

    public Student(int id, String name, String surname, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        marks = new ArrayList<>();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public float getAVGMarks(){
        if (marks.size() == 0){
            return -1;
        }else {
            int sum=0;
            for (int i = 0; i < marks.size(); i++) {
                sum+=marks.get(i);                
            }
            return sum/marks.size();
        }
        
    }
    
    public String addInfo(){
        return null;
    }
    
    public void addNewMark(int mark){
        if (mark <= 5 && mark >= 1){
            marks.add(mark);
        }
    }
    
    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "ID:" + id + " " + name + " " + surname + " " + df.format(dateOfBirth) + " Prumer:" + getAVGMarks() + " " + addInfo();
    }

    @Override
    public int compareTo(Student o) {
         return this.surname.compareTo(o.surname);
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }
    
    
    
    
    
    
    
}
