package edu.njit.cs602.s2018.finalproject;

import java.net.Socket;

/**
 * Created by Prajwal Sankar
 */
public interface ServerStatus {

	//Connect to the server==> To use connect explicitly
	public static void connectToServer()
	{
		}

	//DisConnect from the server==> To use disconnect explicitly
	public static void DisconnectFromServer()
	{
		}
	//Check whether server is up/down and display the status
	public static void getServerStatus(String stat)
	{
	}

	//Validate the user if already present, make connection and try if new, then prompt
	public static void validateUser(String name)
	{
	}

	//If server is up, user is new, get id from user and connect to server
	public static void connServer(String id)
	{
	}

}
