public class SteamPlant{
	
	private double MIN_PRESSURE = 0.5;
	private double MAX_PRESSURE = 10.0;
	private double INITIAL_PRESSURE = 7.0;
	private int INITIAL_HEAT_AVAILABLE = 2000;
	
	private static volatile SteamPlant instance;
	private double currentPressure;
	private int heatAvailable;
	
	private SteamPlant(){
		currentPressure = INITIAL_PRESSURE;
		heatAvailable = INITIAL_HEAT_AVAILABLE;
	}
	
	public static SteamPlant getSteamPlant(){
		
		//Uses double-checked locking to guarantee only one instantation
		if(instance == null){
			synchronized(SteamPlant.class){
				if(instance == null){
					instance = new SteamPlant();
				}
			}
		}
		
		return instance;
		
	}
	
	public synchronized int consumeHeat(int h){
		
		int consumedHeat;
		if(heatAvailable >= h){
			consumedHeat = h;
			heatAvailable -= h;
		}
		else{
			consumedHeat = heatAvailable; //Whatever heat remains will be consumed
			heatAvailable = 0;
		}
		
		return consumedHeat;
		
	}
	
	public void setCurrentPressure(double p){
		if(p > MAX_PRESSURE){
			currentPressure = MAX_PRESSURE;
		}
		else if(p < MIN_PRESSURE){
			currentPressure = MIN_PRESSURE;
		}
		else{
			currentPressure = p;
		}
	}
	
	public double changeCurrentPressure(double p){
		if(currentPressure+p > MAX_PRESSURE){
			currentPressure = MAX_PRESSURE;
		}
		else if(currentPressure+p < MIN_PRESSURE){ //In the case that p is negative
			currentPressure = MIN_PRESSURE;
		}
		else{
			currentPressure += p;
		}
		
		return currentPressure;
	}
	
	public synchronized void timeTick(){
		int increase = (int) (currentPressure-2)*400;
		if(increase < 0){
			increase = 0;
		}
		
		heatAvailable += increase;
	}
	
}