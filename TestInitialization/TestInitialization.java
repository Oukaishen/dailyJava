package TestInitialization;

class father {
    public static int f = 0;
    public father(){
        System.out.print("Inside the father");
    }
}

class child extends father {
    public child (){
        System.out.print("Inside the child");
    }
}

public class TestInitialization {
    public static void main(String[] args){
        child.f++;
    }
}
