package laba.solvd.connection_pool;

public class ConnectionPool implements Runnable {
   boolean[] freeConnections = {true, true, true};

    public void startThreads() {
        Runnable runnable = new ConnectionPool();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < freeConnections.length; i++) {
            if (freeConnections[i]) {
                System.out.println("Starting Thread..." + Thread.currentThread().getName() + " works with connection # " + (i + 1));
                freeConnections[i] = false;
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                freeConnections[i] = true;
                System.out.println("Starting Thread..." + Thread.currentThread().getName() + " is stopped.");
                break;
            }
            if (i == 2) {
                i = -1;
            }
        }
    }
}
