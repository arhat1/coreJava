package com.test.generic;
class PairAlg{
    public static boolean hasNulls(Pair<?> p){
        return p.getFirst() == null || p.getSecond() ==null;
    }
    public static <T>void swapHelper(Pair<T> p){
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }

    public static void swap(Pair<?> p){
        swapHelper(p);
    }
}

public class TestPair {
    public static void main(String[] args) {
        Pair<Employee> pe = new Pair<>(new Employee("张三", 3000.0), new Employee("李四", 4000.0));
        Pair<Manager>  pm = new Pair<>(new Manager("王麻子", 10000.0, "CEO"),new Manager("陈六", 8000.0, "CTO"));
        printBuddies(pe);
        printBuddies(pm);
        printBuddies2(pe);
        //printBuddies2(pm); //Pair<Manager> 与Pair<Employee>没有任何关系，pm不能作为Pair<Employee>的参数

        //Manager m;
        //m = new Employee("test", 20.0);   //这样不行
        Employee e;
        e = new Manager("test", 20.0, "CFO"); //这样可以,这样就可以理解通配符?extend和?super的关系了
        System.out.println(e);


    }
    public static void printBuddies(Pair<? extends Employee> p){  //通配符 + extend表示该参数只能读，调用get
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies");
    }

    public static void printBuddies2(Pair<Employee> p){  //通配符 + extend表示该参数只能读，调用get
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies");
    }

    public static void minmaxSalary(Manager[] ms, Pair<? super Manager> result){  //通配符 + super表示该参数可写,可调用set
        if (ms.length == 0) return;
        Manager min = ms[0];
        Manager max = ms[0];
        for (int i=0; i < ms.length; i++){
            if (min.getSalary() > ms[i].getSalary()) min = ms[i];
            if (max.getSalary() < ms[i].getSalary()) max = ms[i];
        }
        result.setFirst(min);
        result.setSecond(max);
        System.out.println(result.getFirst());
    }
    public static void maxminSalary(Manager[] ms, Pair<? super Manager> result){
        minmaxSalary(ms, result);
        PairAlg.swap(result);
    }
}
