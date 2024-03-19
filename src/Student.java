import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Thread {
    // Name of the student, to identify it in the console
    private String name;

    private Semaphore sOfficeChair;
    // private Queue<Student> queue;
    private ArrayList<Student> queue;

    private Random random = new Random();

	public Student(String name, Semaphore sOfficeChair, ArrayList<Student> queue) {
		this.name = name;
		this.sOfficeChair = sOfficeChair;
		this.queue = queue;
	}

    public void run() {
        // System.out.println("- [" + name + "] Llegué a la oficina.");

        while (true) {
            try {
                System.out.println("- [" + name + "] Veré si el moni está disponible.");


                // If the queue is full, the student goes to the computer room to program.
                if (queue.size() == 4) {
                    System.out.println("- [" + name + "] No hay sillas en el pasillo, me iré a la sala de cómputo y volveré más tarde.");

                    // To simulate the time the student takes to program, we use a random number between 1 and 10 seconds.
                    int sleepTime = 1000 + random.nextInt(9001);
                    sleep(sleepTime);
                }
                
                else {
                    queue.add(this);

                    System.out.println("- [" + name + "] Esperaré mi turno en el pasillo.");

                    if (queue.get(0).equals(this)) {
                        System.out.println("- [" + name + "] ¡MONITOR! ¡Estoy en la fila! ¡Despierta!");
                    }

                    break;
                }

                // else {
                //     // If the queue is not full, the student enters the queue and waits for the monitor to attend him.
                //     queue.add(this);

                //     if (queue.get(0) == this && sOfficeChair.availablePermits() == 1) {
                        
                //         System.out.println("- [" + name + "] ¡MONITOR! ¡Estoy en la fila! ¡Despierta!");
                //         sOfficeChair.acquire();
                        
                //         queue.remove(this);
                //         sOfficeChair.release();
                //         System.out.println("- [" + name + "] ¡Terminé! Me voy a mi casita.");

                //         break;

                //     } else {
                //         System.out.println("- [" + name + "] El monitor está ocupado, esperaré en el pasillo.");
                        
                //         // int sleepTime = 1000 + random.nextInt(3001);
                //         // sleep(sleepTime);

                //         sleep(1000);

                //         queue.remove(this);
                //     }
                // }

            } catch (InterruptedException e) {
                System.out.println("Error en el estudiante: " + e.getMessage());
            }
        }
    }

    public String getStudentName() {
        return name;
    }

}
