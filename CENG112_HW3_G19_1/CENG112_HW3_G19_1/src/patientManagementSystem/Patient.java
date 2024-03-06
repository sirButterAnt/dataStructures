package patientManagementSystem;


import java.time.LocalDateTime;
import java.util.Arrays;


public class Patient implements Comparable<Patient>{
	private String name_surname, age, gender, pregnancy, disability, time, date;
	private LocalDateTime localDateTime; //(year, month, day, hour, minute)
	
	Patient(String name_surname,String age,String gender,String pregnancy,String disability,String time,String date) {
		this.name_surname = name_surname;
		this.age = age;
		this.gender = gender;
		this.pregnancy = pregnancy;
		this.disability = disability;
		this.time = time;
		this.date = date;
		strToLocalDateTime();
	}
	
	public int getPriority() {
		int intAge = Integer.parseInt(age);
		int prioritySum = 0;
		if (pregnancy.equals("preg")) {
			prioritySum += Application.pregnantPriority;
		}
		if (disability.equals("disabled")) {
			prioritySum += Application.disablePriority;
		}
		if (intAge >= 65 ) {
			prioritySum += Application.over65Priority;
		}
		if (intAge <= 18) {
			prioritySum += Application.under18Priority;
		}
		if (prioritySum == 0) {
			prioritySum += Application.regularPriority;
		}
		return prioritySum;
	}
	
	private void strToLocalDateTime() {
		String[] localTime = this.time.split(":"); // I didn't use LocalDate and LocalTime with considering the probability of we are not allowed to use
		String[] localDate = this.date.split("/");
		int hour = Integer.parseInt(localTime[0]);
		int min = Integer.parseInt(localTime[1]);
		int year = Integer.parseInt(localDate[2]);
		int month = Integer.parseInt(localDate[1]);
		int day = Integer.parseInt( localDate[0]);
		localDateTime = LocalDateTime.of(year, month, day, hour, min);	
	}
	
	public int compareTo(Patient other) {
		if (this.localDateTime.isAfter(other.localDateTime)) {
			return 1;	
		}else if (this.localDateTime.isBefore(other.localDateTime)){
			return -1;
		}else {
			return 0;
		}
	}
	
	public int compareToPriority(Patient other) {
		if (this.localDateTime.toLocalDate().equals(other.localDateTime.toLocalDate())) {
			if(this.getPriority()>other.getPriority()){
				return -1;
			}else if (this.getPriority()< other.getPriority()){
				return 1;
			}else {
				return 0;
			}
		}else if (this.localDateTime.isAfter(other.localDateTime)) {
			return 1;
		}else { //if (localDateTime.isBefore(other.localDateTime))
			return -1;
		}
	}
	
	//getter function
	public String getInfo(String info) {
		String returnedString = null;
		switch (info) {
		case "name_surname":
			returnedString = name_surname;
			break;
		case "age":
			returnedString = age;
			break;
		case "gender":
			returnedString = gender;
			break;
		case "pregnancy":
			returnedString = pregnancy;
			break;
		case "disability":
			returnedString = disability;
			break;
		case "time":
			returnedString = time;
			break;
		case "date":
			returnedString = date;
			break;
		}
		assert (returnedString != null);
		return returnedString;
	}
	
	public void printer() { 
		System.out.println("name_surname : " +name_surname+ "	|age : " +age+ "|	|gender : " +gender+ "|	|pregnancy : " +pregnancy+ "|	|disability : " +disability+
			            "|	|time : " +time+ "|	|date : " +date+"|  |priority : "+getPriority());
	}
}