package Playlist;
//Serhat Eren TAS 280201020    Kerem Bugra KASAL 280201005 group 19
public class Track { // for tracks
	int genre_id;
	int track_id;
	int track_duration;
	int track_popularity;
	
	Track(int genre_id,int track_id,int track_duration,int track_popularity){
	this.genre_id=genre_id;
	this.track_id=track_id;
	this.track_duration=track_duration;
	this.track_popularity=track_popularity;
	}
	

}
