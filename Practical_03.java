import java.util.Scanner;

class Student {
    int[] marks = new int[5];
    int total = 0;
    float percentage = 0.0f;
    String result;
}


class TotalThread extends Thread {
    Student s;

    TotalThread(Student s) {
        this.s = s;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            s.total += s.marks[i];
        }
    }
}


class PercentageThread extends Thread {
    Student s;

    PercentageThread(Student s) {
        this.s = s;
    }

    public void run() {
        s.percentage = s.total / 5.0f;
    }
}


class ResultThread extends Thread {
    Student s;

    ResultThread(Student s) {
        this.s = s;
    }

    public void run() {
        boolean pass = true;
        for (int i = 0; i < 5; i++) {
            if (s.marks[i] < 30) {   // Fail if any subject < 30
                pass = false;
                break;
            }
        }
        s.result = (pass && s.percentage >= 30) ? "Pass" : "Fail";
    }
}

public class StudentResultProcessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student student = new Student();

   
        System.out.println("Enter marks for 5 subjects:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            student.marks[i] = sc.nextInt();
        }

      
        Thread t1 = new TotalThread(student);
        Thread t2 = new PercentageThread(student);
        Thread t3 = new ResultThread(student);

       
        try {
            t1.start();
            t1.join();

            t2.start();
            t2.join();

            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

  
        System.out.println("\nTotal Marks: " + student.total);
        System.out.println("Percentage: " + student.percentage + "%");
        System.out.println("Result: " + student.result);

        sc.close();
    }
}
