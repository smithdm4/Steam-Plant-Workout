import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Clock implements Runnable {
	
	private SteamPlant steamPlant;
	
	public Clock(){
		steamPlant = SteamPlant.getSteamPlant();
	}
	
	@Override
	public void run(){
		
		while(true){
			
			try{
				TimeUnit.SECONDS.sleep(5); //Pause for 5 seconds
			} 
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			steamPlant.timeTick();
			steamPlant.changeCurrentPressure( (Math.random()*2.0)-1.0 );
			
		}
		
	}
	
}