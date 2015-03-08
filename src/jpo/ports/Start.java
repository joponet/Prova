package jpo.ports;

import java.io.IOException;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDDeviceInfo;
import com.codeminders.hidapi.HIDManager;

public class Start {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		HIDManager manager;
		HIDDeviceInfo deviceInfo[] = null;
		HIDDevice device;
		
		ClassPathLibraryLoader.loadNativeHIDLibrary();

		try {
			manager = HIDManager.getInstance();
			deviceInfo = manager.listDevices();

//			for (int i=0 ; i<deviceInfo.length ; i++) {
//				System.out.println(deviceInfo[i].getProduct_string() + " -> " + deviceInfo[i].getPath());
//			}
			
//			System.out.println();
			for (int i=0 ; i<deviceInfo.length ; i++) {
				System.out.println(deviceInfo[i].getProduct_string());
				System.out.println(deviceInfo[i].getManufacturer_string());
				System.out.println(deviceInfo[i].getVendor_id());
				System.out.println(deviceInfo[i].getProduct_id());
				System.out.println(deviceInfo[i].getSerial_number());
				System.out.println(deviceInfo[i].getPath());
				System.out.println(deviceInfo[i].getUsage());
				System.out.println();
			}
						
			device = manager.openByPath(deviceInfo[0].getPath());

			while (true) {
				byte buf[] = new byte[8];
				int l = device.read(buf);
				System.out.print(l+":");
				for (int i=0 ; i<buf.length ; i++) {
					System.out.printf("%1$3.2h",buf[i]&0x000000ff);
				}
				System.out.println();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
