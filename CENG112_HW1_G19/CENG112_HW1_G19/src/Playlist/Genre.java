package Playlist;
//Serhat Eren TAS 280201020    Kerem Bugra KASAL 280201005 group 19
public class Genre { // this class is for track genres
	private MyBag<Track> tracks;
	private Track[] array;
	public int id;
	Genre(int number){
		this.tracks=new MyBag<>();
		array=tracks.toArray();
		id=number;
	}
	
	public void add(Track newer) {
		this.tracks.add(newer);
		array=tracks.toArray();
	}
	
	public void toArray() {
		array=tracks.toArray();
	}
	
	public Track remove() {
		Track lastItem=tracks.remove();
		array=tracks.toArray();
		return lastItem;
		
	}
	public boolean isEmpty() {
		return tracks.isEmpty();
	}
	
	public int numberOfTracks() {
		return array.length;
	}
	
	public float totalDuration() {
		float duration=0;
		for(int i=0;i<array.length;i++) {
			duration+=(array[i].track_duration);
		}
	return  ((float) Math.round(100*(duration/60)))/100; //(Math.round(100*(duration%60)))/100);
	}
	
	public void leastToMostPopular(){
		for(int i=0;i<array.length;i++) {
			for(int j=1;j<array.length;j++) {
				if (array[j-1].track_popularity>array[j].track_popularity) {
					Track morePopular=array[j-1];
					array[j-1]=array[j];
					array[j]=morePopular;
				}
			}
		}
		tracks.clear();
		for(int i=0; i<array.length; i++) {
			tracks.add(array[i]);
		}
	}
	
	public Track[] getArray() {			
		return array;
	}
} 
	
