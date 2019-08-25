/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhumanov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Databaze {

    private ArrayList<Student> listOfStudents;
    private int lastId;
    private int countH;
    private int countT;
    private int countK;

    public Databaze() {
        listOfStudents = new ArrayList<>();
        countH = 0;
        countT = 0;
        countK = 0;
        lastId = 0;

    }

    public void addNewStudentT(String name, String surname, Date dateOfBirth) {
        countT++;
        listOfStudents.add(new StudentT(++lastId, name, surname, dateOfBirth));
    }

    public void addNewStudentH(String name, String surname, Date dateOfBirth) {
        countH++;
        listOfStudents.add(new StudentH(++lastId, name, surname, dateOfBirth));
    }

    public void addNewStudentK(String name, String surname, Date dateOfBirth) {
        countK++;
        listOfStudents.add(new StudentK(++lastId, name, surname, dateOfBirth));
    }

    public boolean addMark(int id, int mark) {
        boolean success = false;
        if (mark > 5 || mark < 1) {
        	return success;
        }
        for (int i = 0; i < listOfStudents.size(); i++) {
            if (listOfStudents.get(i).getId() == id) {
                listOfStudents.get(i).addNewMark(mark);
                success = true;
                break;
            }
        }
        return success;
    }

    public Student removeStudent(int id) {
        for (int i = 0; i < listOfStudents.size(); i++) {
            if (listOfStudents.get(i).getId() == id) {
                Student student = listOfStudents.get(i);
                if (student instanceof StudentT) {
                    countT--;
                }
                if (student instanceof StudentH) {
                    countH--;
                }
                if (student instanceof StudentK) {
                    countK--;
                }
                listOfStudents.remove(student);
                return student;
            }
        }
        return null;
    }

    public Student searchStudent(int id) {
        for (int i = 0; i < listOfStudents.size(); i++) {
            if (listOfStudents.get(i).getId() == id) {
                return listOfStudents.get(i);
            }
        }
        return null;
    }

    public ArrayList<Student> getSortedList() {
        ArrayList<Student> listH = new ArrayList<>();
        ArrayList<Student> listT = new ArrayList<>();
        ArrayList<Student> listK = new ArrayList<>();
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < listOfStudents.size(); i++) {
            if (listOfStudents.get(i) instanceof StudentT) {
                listT.add(listOfStudents.get(i));
            }
            if (listOfStudents.get(i) instanceof StudentH) {
                listH.add(listOfStudents.get(i));
            }
            if (listOfStudents.get(i) instanceof StudentK) {
                listK.add(listOfStudents.get(i));
            }
        }
        Collections.sort(listH);
        Collections.sort(listT);
        Collections.sort(listK);
        list.addAll(listT);
        list.addAll(listH);
        list.addAll(listK);
        return list;
    }

    public float getAVGTechHum() {
        float summ = 0;
        for (int i = 0; i < listOfStudents.size(); i++) {
            if ((listOfStudents.get(i) instanceof StudentT || listOfStudents.get(i) instanceof StudentH) && listOfStudents.get(i).getAVGMarks() != -1) {
                summ += listOfStudents.get(i).getAVGMarks();
            }
            
        }
        return summ / (countH + countT);
    }

    public int getCountH() {
        return countH;
    }

    public int getCountT() {
        return countT;
    }

    public int getCountK() {
        return countK;
    }

    public void saveDatabase() {
        try {
            FileWriter fw = new FileWriter("database.txt");
            fw.write(lastId + "\n");
            fw.write(countT + "\n");
            fw.write(countH + "\n");
            fw.write(countK + "\n");
            for (int i = 0; i < listOfStudents.size(); i++) {
                Student student = listOfStudents.get(i);
                String studentTmp = String.format("%d;%s;%s;%tF",
                        student.getId(),
                        student.getName(),
                        student.getSurname(),
                        student.getDateOfBirth());
                String strMarks = "";
                for (int j = 0; j < student.getMarks().size(); j++) {
                    strMarks += ";" + student.getMarks().get(j);
                }
                if (student instanceof StudentT) {
                    fw.write("T;" + studentTmp + strMarks + "\n");
                }
                if (student instanceof StudentH) {
                    fw.write("H;" + studentTmp + strMarks + "\n");
                }
                if (student instanceof StudentK) {
                    fw.write("K;" + studentTmp + strMarks + "\n");
                }
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("Chyba IO");
        }
    }

    public void readDatabase() {
        listOfStudents.clear();
        try {
            Scanner fr = new Scanner(new File("database.txt"));
            if (fr.hasNextLine()) {
                lastId = Integer.parseInt(fr.nextLine());
            }
            if (fr.hasNextLine()) {
                countT = Integer.parseInt(fr.nextLine());
            }
            if (fr.hasNextLine()) {
                countH = Integer.parseInt(fr.nextLine());
            }
            if (fr.hasNextLine()) {
                countK = Integer.parseInt(fr.nextLine());
            }
            while (fr.hasNextLine()) {
                Student student = null;
                String[] str = fr.nextLine().split(";");
                int id = Integer.parseInt(str[1]);
                String name = str[2];
                String surname = str[3];
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = df.parse(str[4]);
                switch (str[0]) {
                    case ("T"): {
                        student = new StudentT(id, name, surname, dateOfBirth);
                        break;
                    }
                    case ("H"): {
                        student = new StudentH(id, name, surname, dateOfBirth);
                        break;
                    }
                    case ("K"): {
                        student = new StudentK(id, name, surname, dateOfBirth);
                        break;
                    }
                }
                if (student != null) {
                    listOfStudents.add(student);
                }
                for (int i = 5; i < str.length; i++) {
                    student.addNewMark(Integer.parseInt(str[i]));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Databaze.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Databaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeStudentFromFile(int id) {
        try {
            ArrayList<String> listStr = new ArrayList<>();
            Scanner fr = new Scanner(new File("database.txt"));
            for (int i = 0; i < 4; i++) {
                fr.nextLine();
            }
            while (fr.hasNext()) {
                String buffer = fr.nextLine();
                String[] str = buffer.split(";");
                if (str[1].equalsIgnoreCase(String.valueOf(id))) {
                    continue;
                }
                listStr.add(buffer);
            }
            FileWriter fw = new FileWriter(new File("database.txt"));
            for (int i = 0; i < listStr.size(); i++) {
                fw.write(listStr.get(id) + "\n");
            }
            fw.flush();
            fw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Databaze.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Databaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student readStudentFromFile(String namePar, String surnamePar) {
        try {
            Scanner fr = new Scanner(new File("database.txt"));
            for (int i = 0; i < 4; i++) {
                fr.nextLine();
            }
            while (fr.hasNextLine()) {
                String[] str = fr.nextLine().split(";");
                if (str[2].equalsIgnoreCase(namePar) && str[3].equalsIgnoreCase(surnamePar)) {
                    Student student = null;
                    int id = Integer.parseInt(str[1]);
                    String name = str[2];
                    String surname = str[3];
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateOfBirth = df.parse(str[4]);
                    switch (str[0]) {
                        case ("T"): {
                            student = new StudentT(id, name, surname, dateOfBirth);
                            break;
                        }
                        case ("H"): {
                            student = new StudentH(id, name, surname, dateOfBirth);
                            break;
                        }
                        case ("K"): {
                            student = new StudentK(id, name, surname, dateOfBirth);
                            break;
                        }
                    }
                    for (int i = 0; i < listOfStudents.size(); i++) {
                        if (listOfStudents.get(i).getId() == student.getId()) {
                            student.setId(++lastId);
                        }
                    }
                    for (int i = 5; i < str.length; i++) {
						student.addNewMark(Integer.parseInt(str[i]));
					}
                    listOfStudents.add(student);
                    return student;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Databaze.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Databaze.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
