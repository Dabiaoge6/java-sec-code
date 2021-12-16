package org.vulhunter.common.insecuresocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class InsecureSocketCommon {

	public static Socket clientWithoutCert1() throws IOException {
		SocketFactory sf = SSLSocketFactory.getDefault();
		Socket s = sf.createSocket("localhost", 53241);
		return s;
	}

	public static Socket clientWithoutCert2() throws IOException {
		SocketFactory sf = SSLSocketFactory.getDefault();
		InetAddress clientAddress = InetAddress.getLocalHost();
		Socket s = sf.createSocket(clientAddress, 53241);
		return s;
	}

	public static Socket clientWithoutCert3() throws IOException {
		SocketFactory sf = SSLSocketFactory.getDefault();
		Socket s = sf.createSocket();
		s = new Socket("localhost", 53241);
		return s;
	}

	public static Socket clientWithoutCert4() throws IOException {
		SocketFactory sf = SSLSocketFactory.getDefault();
		InetAddress serverAddress = InetAddress.getByName("0.0.0.0");
		InetAddress clientAddress = InetAddress.getLocalHost();
		Socket s = sf.createSocket(serverAddress, 53241, clientAddress, 53241);
		return s;
	}

	public static Socket clientWithoutCert5() throws IOException {
		SocketFactory sf = SSLSocketFactory.getDefault();
		InetAddress clientAddress = InetAddress.getLocalHost();
		Socket s = sf.createSocket("0.0.0.0", 53241, clientAddress, 53241);
		return s;
	}

	public static Socket clientWithoutCert6() throws IOException {
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		Socket s1 = new Socket("localhost", 53241);
		InetAddress clientAddress = InetAddress.getLocalHost();
		Socket s = sf.createSocket(s1, "0.0.0.0", 53241, false);
		return s;
	}

}
