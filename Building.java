import java.util.concurrent.TimeUnit;

public class Building implements Runnable {
	
	private SteamPlant steamPlant;
	private int size;
	private int thermostatSetting;
	private int outsideTemperature;

	public Building(int size, int thermostatSetting, int outsideTemperature){
		steamPlant = SteamPlant.getSteamPlant();
		this.size = size;
		this.thermostatSetting = thermostatSetting;
		this.outsideTemperature = outsideTemperature;
	}
	
	@Override
	public void run(){
		
		while(true){
			
			try{
				TimeUnit.SECONDS.sleep(3); //Pause for 3 seconds
			} 
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			int h = (thermostatSetting-outsideTemperature)*size/2500;
			if(h < 0){
				h = 0;
			}
			steamPlant.consumeHeat(h);
			
		}
		
	}
	
}