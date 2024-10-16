import java.util.PriorityQueue;

public class PharmacyQueue {
    private PriorityQueue<Medication> queue;

    public PharmacyQueue() {
        queue = new PriorityQueue<>((m1, m2) -> Integer.compare(m2.getPriority(), m1.getPriority()));
    }

    public void addMedication(Medication medication) {
        queue.offer(medication);
    }

    public Medication getNextMedication() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
