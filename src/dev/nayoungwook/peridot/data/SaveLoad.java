package dev.nayoungwook.peridot.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad<T> {

	public SaveLoad() {

	}

	public void save(T ds, String name) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(name + ".dat")));

			oos.writeObject(ds);
		} catch (Exception e) {
			System.out.println("save exception!");
		}
	}

	@SuppressWarnings("unchecked")
	public T load(String name) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(name + ".dat")));

			return (T) ois.readObject();
		} catch (Exception e) {
			System.out.println("load exception!");
		}
		return null;
	}

}
