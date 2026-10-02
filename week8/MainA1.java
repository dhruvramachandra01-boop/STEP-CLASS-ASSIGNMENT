
import java.util.*;

// Abstract Base Class for Wash Types (Allows adding new types like 'Delicate' easily)
abstract class WashType {

    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() {
        return name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getCharge() {
        return charge;
    }
}

class QuickWash extends WashType {

    public QuickWash() {
        super("Quick", 30, 20.00);
    }
}

class NormalWash extends WashType {

    public NormalWash() {
        super("Normal", 45, 30.00);
    }
}

class HeavyWash extends WashType {

    public HeavyWash() {
        super("Heavy", 60, 45.00);
    }
}

// Student Class
class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Washing Machine Class
class WashingMachine {

    private String machineId;
    private boolean isBusy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.isBusy = false;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return isBusy;
    }

    // Controlled state modification
    public boolean markBusy() {
        if (isBusy) {
            return false;
        }
        this.isBusy = true;
        return true;
    }

    public void markFree() {
        this.isBusy = false;
    }
}

// Wash Cycle Class
class WashCycle {

    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }
}

// Laundry Booking Service
class LaundryService {

    private Map<String, WashCycle> activeCycles = new HashMap<>();

    public void startWash(Student student, WashingMachine machine, WashType washType) {
        if (machine.isBusy()) {
            System.out.println("Machine " + machine.getMachineId() + " is currently busy.");
            return;
        }

        machine.markBusy();
        WashCycle cycle = new WashCycle(student, machine, washType);
        activeCycles.put(machine.getMachineId(), cycle);

        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.\n",
                washType.getName(), machine.getMachineId(), student.getName(),
                washType.getDurationMinutes(), washType.getCharge());
    }

    public void completeWash(WashingMachine machine) {
        if (!machine.isBusy() || !activeCycles.containsKey(machine.getMachineId())) {
            System.out.println("Machine " + machine.getMachineId() + " is not currently running a cycle.");
            return;
        }

        machine.markFree();
        activeCycles.remove(machine.getMachineId());
        System.out.println(machine.getMachineId() + " cycle completed. " + machine.getMachineId() + " is now free.");
    }
}

public class MainA1 {

    public static void main(String[] args) {
        LaundryService service = new LaundryService();

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        // Execution matching sample input
        service.startWash(asha, m1, new QuickWash());
        service.startWash(ravi, m1, new HeavyWash()); // Should fail as M1 is busy
        service.startWash(ravi, m2, new HeavyWash());
        service.completeWash(m1);
        service.startWash(neha, m1, new NormalWash());
    }
}
