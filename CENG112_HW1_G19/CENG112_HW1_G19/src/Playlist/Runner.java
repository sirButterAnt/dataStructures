package Playlist;
//Serhat Eren TAS 280201020    Kerem Bugra KASAL 280201005 group 19
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Runner { //our main class to run program
	public static void main(String []args){
		int chooseOfUser=6;
		while (chooseOfUser!=0) {
			Genre acoustic=new Genre(1);
			Genre instrumental=new Genre(2);
			Genre rock=new Genre(3);
			Genre rap=new Genre(4);
			Genre jazz=new Genre(5);
			Genre pop=new Genre(6);
			Scanner scanner=new Scanner(System.in);
			
			//reading file
			FileReader file = null;
			BufferedReader tracks=null;
			try {
				file  = new FileReader("src/tracks.txt");
				tracks =new BufferedReader(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("File is not found!");
			}
			
			System.out.println("--------------------------");
			System.out.println("Before playlist created : ");
			System.out.println("--------------------------\n");
			
			toGenre(acoustic,instrumental,rock,rap,jazz,pop,tracks);//creating genres (tracks mean tracks =new BufferedReader(file);)
			acoustic.leastToMostPopular();
			genrePrinter(acoustic);
			
			
			instrumental.leastToMostPopular();
			genrePrinter(instrumental);
			
			rock.leastToMostPopular();
			genrePrinter(rock);
		
			rap.leastToMostPopular();
			genrePrinter(rap);
			
			
			jazz.leastToMostPopular();
			genrePrinter(jazz);
			
			pop.leastToMostPopular();
			genrePrinter(pop);
			
			System.out.println("0(Quit)\n1(Sleeping)\n2(WorkOut)\n3(Dining)\n4(Meditation)\n5(RoadTrip)\n\nEnter a number to create a playlist : "); //user interface
	
			String playListName = null;
			Playlist playlist=null;
		
			chooseOfUser=scanner.nextInt();
			switch(chooseOfUser) { // according to user's decision playlist will be prepared due to wanted conditions like suitability and maximum time
				case 1://sleeping
					int[] SleepingSuitability={2,1,5};
					playlist=new Playlist(45,SleepingSuitability);
					SleepingSuitability=null;
					genreToPlaylist(playlist,instrumental,acoustic,jazz);
					playListName="Sleeping";
					break;
				case 2://workOut
					int[] workOutSuitability={4,3,6};
					playlist=new Playlist(60,workOutSuitability);
					workOutSuitability=null;
					genreToPlaylist(playlist,rap,rock,pop);
					playListName="WorkOut";
					break;
				case 3://dining
					int[] diningSuitability={5,1,2};
					playlist=new Playlist(90,diningSuitability);
					diningSuitability=null;
					genreToPlaylist(playlist,rap,rock,pop);
					playListName="Dining";
					break;
				case 4://Meditation
					int[] meditationSuitability={2,1,5};
					playlist=new Playlist(120,meditationSuitability);
					meditationSuitability=null;
					genreToPlaylist(playlist,rap,rock,pop);
					playListName="Meditation";
					break;
				case 5://RoadTrip
					int[] roadTripSuitability={3,6,1};
					playlist=new Playlist(180,roadTripSuitability);
					roadTripSuitability=null;
					genreToPlaylist(playlist,rap,rock,pop);
					playListName="RoadTrip";
					break;
			}
			
			
			
			
			
			System.out.println("--------------------------");
			System.out.println("After playlist created : ");
			System.out.println("--------------------------\n");
			
			genrePrinter(acoustic);    
			
			genrePrinter(instrumental);
			
			genrePrinter(rock);
		
			genrePrinter(rap);
			
			genrePrinter(jazz);
			
			genrePrinter(pop);
			
			playListPrinter(playListName, playlist); // wanted playlist will be printed with other information
			
			System.out.println("0(Quit)\n1(Sleeping)\n2(WorkOut)\n3(Dining)\n4(Meditation)\n5(RoadTrip)\n\nEnter a number to create a playlist : ");
			chooseOfUser=scanner.nextInt();
			try {
				tracks.close();
				scanner.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	
	
	//*************************functions****************************
	public static void genrePrinter(Genre genre)  {
		String variable=null;
		switch(genre.id){              //for each genre to find information about them from bags
			case 1:
				variable="Acoustic";
				break;
			case 2:
				variable="Instrumental";
				break;
			case 3:
				variable="Rock";
				break;
			case 4:
				variable="Rap";
				break;
			case 5:
				variable="Jazz";
				break;
			case 6:
				variable="Pop";
				break;
		}
		System.out.println("Number of "+variable+" tracks: "+genre.numberOfTracks());
		System.out.println("Total duration of "+ variable+" tracks: "+genre.totalDuration()+"min\n");
	}
	
	public static void playListPrinter(String playListName,Playlist playlist ) {     //after creating the playlist bag, we gather information about them and print the information out
		System.out.println("\n*************************************");
		System.out.println("Number of tracks : " +playlist.numberOfTracks());
		System.out.println("Total duration of "+playListName+" : "+playlist.totalDuration()+"min");
		System.out.println("Average popularity of playlist = "+playlist.averagePopularity());
		System.out.println("*************************************\n");
		
	}
	
	public static void genreToPlaylist(Playlist playlist,Genre genre1,Genre genre2,Genre genre3 ) {
		boolean maxMinError=false;
		while(!genre1.isEmpty()&&!maxMinError) {
			playlist.add(genre1.remove());
			if(playlist.getMinimumDuration()<playlist.totalDuration()) {
				maxMinError=true;
			}
		}
		while(!genre2.isEmpty()&&!maxMinError) {
			playlist.add(genre2.remove());
			if(playlist.getMinimumDuration()<playlist.totalDuration()) { 
				maxMinError=true;
			}
		}
		while(!genre3.isEmpty()&&!maxMinError) {
			playlist.add(genre3.remove());
			if(playlist.getMinimumDuration()<playlist.totalDuration()) {
				maxMinError=true;
			}
		}
	System.out.println(playlist.totalDuration());
	}
	
	public static int[] strToIntArray(String str){
		int[] intTrackProperties=new int[4];
		String[] strTrackProperties=str.split(",");
		for(int i=0;i<4;i++) {
			intTrackProperties[i]=Integer.parseInt(strTrackProperties[i]);
	    }
		return intTrackProperties;
	}
	
	public static void remove(int trackId,MyBag<Track> bag) {
		Track[] arrayOfBag=((MyBag<Track>) bag).toArray();
		for(int i=0;i<arrayOfBag.length;i++) {
			if (arrayOfBag[0].track_id==trackId) {
				bag.remove(arrayOfBag[0]);
			}
		}
	}
	
	public  static void toGenre(Genre acoustic,Genre instrumental,Genre rock ,Genre rap,Genre jazz,Genre pop,BufferedReader tracks) { 
		try {
			String linesOfFile=null;
			linesOfFile = tracks.readLine();
			int[]properties;
			while (linesOfFile!=null){
				properties=strToIntArray(linesOfFile);
				switch(properties[0]) {
				case 1:
					int a=properties[0];
					int b=properties[1];
					int c=properties[2];
					int d=properties[3];
					acoustic.add(new Track(a,b,c,d));
					break;
				case 2:
					int a2=properties[0];
					int b2=properties[1];
					int c2=properties[2];
					int d2=properties[3];
					instrumental.add(new Track(a2,b2,c2,d2));
					break;
				case 3:
					int a3=properties[0];
					int b3=properties[1];
					int c3=properties[2];
					int d3=properties[3];
					rock.add(new Track(a3,b3,c3,d3));
					break;	
				case 4:
					int a4=properties[0];
					int b4=properties[1];
					int c4=properties[2];
					int d4=properties[3];
					rap.add(new Track(a4,b4,c4,d4));
					break;
				case 5:
					int a5=properties[0];
					int b5=properties[1];
					int c5=properties[2];
					int d5=properties[3];
					jazz.add(new Track(a5,b5,c5,d5));
					break;
				case 6:
					int a6=properties[0];
					int b6=properties[1];
					int c6=properties[2];
					int d6=properties[3];
					pop.add(new Track(a6,b6,c6,d6));
					break;
				}
				linesOfFile = tracks.readLine();		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}