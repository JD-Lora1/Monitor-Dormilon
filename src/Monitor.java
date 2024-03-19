import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {

    // Same attributes as in the Attention class
    // private Queue<Student> studentsQueue;
    private ArrayList<Student> studentsQueue;
    private Semaphore sOfficeChair;

    private boolean status = true; 

    private Random random = new Random();

    public Monitor(ArrayList<Student> studentsQueue, Semaphore sOfficeChair) {
        this.studentsQueue = studentsQueue;
        this.sOfficeChair = sOfficeChair;
    }

    public void run() {
        while (true) {
            try {
                // If the queue is empty, the monitor goes to sleep.
                if (studentsQueue.isEmpty()) {
                    System.out.println("- [MONITOR] No hay trabajo. A mimir.");
                    sOfficeChair.release();

                    sleep(3000);
                } else {
                    // If the queue is not empty, the monitor attends the first student in the queue.
                    System.out.println("- [MONITOR] ¡Estoy despierto! ¡Voy a atender a " + studentsQueue.get(0).getStudentName() + "!");
                    sOfficeChair.acquire();

                    // To simulate the time the monitor takes to attend the student, we use a random number between 1 and 10 seconds.
                    int sleepTime = 1000 + random.nextInt(4001);
                    sleep(sleepTime);

                    System.out.println("- [MONITOR] ¡Terminé de atender a " + studentsQueue.get(0).getStudentName() + "! ¡Siguiente!");

                    // The monitor removes the student from the queue and releases the semaphore to allow the next student to be attended.
                    studentsQueue.remove(0);
                    sOfficeChair.release();
                }

            } catch (Exception e) {
                System.out.println("Error en el monitor: " + e.getMessage());
            }
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public String getStatusStr() {
        return status ? "Dormido": "Despierto";
    }
}