package me.nicomunoz.kiroscraft.parkour.connection;

public interface AsyncQuery {
	
	public void executeAsync(boolean prepared, boolean select, QueryResult result);

}
