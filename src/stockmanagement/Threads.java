
package stockmanagement;
public class Threads extends Thread{
    private String st;
    @Override
    public void run() {
      //  super.run();
        System.out.print(st);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Threads() {
    }
     public Threads(String st) {
         this.st=st;         
    }
    public void runLoop(String st){
 
    }
    
}
