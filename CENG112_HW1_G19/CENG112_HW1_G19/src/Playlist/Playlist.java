package Playlist;
//Serhat Eren TAS 280201020    Kerem Bugra KASAL 280201005 group 19
public class Playlist { // this class is to prepare playlist
	private int minimumDuration;
	private int[] suitability;
	private MyBag<Track> playlist;
	private Track[]array;
	
	Playlist(int min,int[]suitability){
		this.minimumDuration=min;
		this.suitability=suitability;
		playlist=new MyBag<Track>();
	}
	
	public void add(Track newer) {
		this.playlist.add(newer);
		array=playlist.toArray();
	}
	
	public float totalDuration() {
		float duration=0;
		for(int i=0;i<array.length;i++) {
			duration+=(array[i].track_duration);
		
		}
		return  ((float) Math.round(100*(duration/60)))/100; //(Math.round(100*(duration%60)))/100);	
	}	
	public void toArray() {
		array=playlist.toArray();
	}
	public int getMinimumDuration() {
		return this.minimumDuration;
	}
	public int numberOfTracks() {
		return array.length;
	}
	public int averagePopularity() {
		int total=0;
		for(int i=0;i<array.length;i++) {
			total+=array[i].track_popularity;
		}
		return total/array.length;
	}	
}
