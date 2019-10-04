package mypack;

/**
 * Hello world!
 *
 */
public class PrintAscDescNumbers {
	 
	boolean desc;
	int count = 1;
	private int countDesc = 50;
 
	public void printAsc() {
		synchronized (this) {
			while (count <= 50) {
				while (!desc) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Asc Thread :" + count);
				count++;
				desc = false;
				notify();
			}
		}
	}
 
	public void printDesc() {
 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		synchronized (this) {
			while (countDesc >0) {
				while (desc) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Desc thread :" + countDesc);
				countDesc--;
				desc = true;
				notify();
 
			}
		}
	}
 
	public static void main(String[] args) {
 
		PrintAscDescNumbers oep = new PrintAscDescNumbers();
		oep.desc = true;
		Thread t1 = new Thread(new Runnable() {
 
			@Override
			public void run() {
				oep.printDesc();
 
			}
		});
		Thread t2 = new Thread(new Runnable() {
 
			@Override
			public void run() {
				oep.printAsc();
 
			}
		});
 
		t1.start();
		t2.start();
 
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 
	}
}