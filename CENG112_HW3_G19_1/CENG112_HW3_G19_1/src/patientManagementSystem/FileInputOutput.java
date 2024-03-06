package patientManagementSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInputOutput {
	public static SortedLinkedList<Patient> read(String file) {
		//reading file
		//creating sorted list and filling it with patients
		SortedLinkedList<Patient> sortedListOfPatients = new SortedLinkedList<>(); //SortedList created here.
		try {
			BufferedReader inputFile = new BufferedReader( new FileReader(file));
			String  nextLine = inputFile.readLine();
			while (nextLine != null) {
				String[] arrayOfLine = nextLine.split(",");
				String name_surname = arrayOfLine[1];
				String age = arrayOfLine[2];
				String gender = arrayOfLine[3];
				String pregnancy = arrayOfLine[4];
				String disability = arrayOfLine[5];
				String time =arrayOfLine[6];
				String date = arrayOfLine[7];
				Patient newPatient = new Patient(name_surname, age, gender, pregnancy, disability, date, time);//Creating patients from the information given in the file.
				sortedListOfPatients.add(newPatient);
				nextLine = inputFile.readLine();
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sortedListOfPatients;
	}
}
