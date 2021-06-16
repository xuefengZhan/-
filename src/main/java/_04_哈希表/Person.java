package _04_哈希表;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age, float height, String name) {
        super();
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != getClass()) return false;

        Person person = (Person)obj;
        return person.age == age
                && person.height == height
                && person.name==null ? name==null : person.name.equals(name);
        // 传入name若为空，则当前对象name也必须为空才为 true
        // 传入name若不为空，则调用equals方法比较即可
    }
    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(age);// *31是因为JVM内部优化
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name!=null ? name.hashCode() : 0);
        return hashCode;
    }


    public static void main(String[] args) {
        Person p = new Person(18,175.6f,"Jerry");
        Person p1 = new Person(18,175.6f,"Jerry");

        System.out.println(p.hashCode());  //1211888640
        System.out.println(p1.hashCode()); //564160838
    }

}


