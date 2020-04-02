package net.tfobz.synchranisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A serialized counter is a counter that persists. It means that the value
 * of the counter is saved to the disk
 * @author Michael Wild
 */
public class SerializedCounter
{
	public int getIncrementedValue() {
		int ret = loadCounter();
		ret++;
		saveCounter(ret);
		return ret;
	}

	public int getValue() {
		return loadCounter();
	}
	
	public void resetCounter() {
		saveCounter(0);
	}

	/**
	 * Saves the value of the counter in a file named by the classname and stored
	 * in the application directory
	 * @param value ob the counter to be stored
	 */
	private synchronized void saveCounter(int value) {
		String path = System.getProperty("user.dir") + "\\"
				+ getClass().getSimpleName() + ".obj";
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(value);
		} catch (IOException e) {
			System.out.println(e.getClass() + ": " + e.getMessage());
		} finally {
			try { out.close(); } catch (Exception e1) {	;	}
		}
	}

	/**
	 * Returns the actual value of the counter stored in a file in the application
	 * directory
	 * @return the value of the counter
	 */
	private synchronized int loadCounter() {
		int ret = -1;
		String path = System.getProperty("user.dir") + "\\"
			+ getClass().getSimpleName() + ".obj";
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(path));
			ret = (Integer)in.readObject();
		} catch (FileNotFoundException e) {
			// do nothing
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getClass() + ": " + e.getMessage());
		} finally {
			try { in.close(); } catch (Exception e1) {	;	}
		}
		return ret;
	}
}
