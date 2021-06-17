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

    @Override
    /**
     * 比较两个对象是否相等
     */
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
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name!=null ? name.hashCode() : 0);
        return hashCode;
    }

}

