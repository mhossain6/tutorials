package com.example.rest;

class MyInteger  {
    MyInteger value;

    public MyInteger(MyInteger value) {
        this.value = value;
    }

    public MyInteger getValue() {
        return value;
    }

    public void setValue(MyInteger value) {
        this.value = value;
    }

    @Override
    public MyInteger clone()  {
        if (this.value == null ){
            return new MyInteger(null);
        } else {
             return new MyInteger(value.clone());
        }
    }

    public void print() {
        System.out.print("1");
        if (value != null) {
            value.print();
        }
    }

}


public class IntegerUsingRecursion
{

   static void printMyInteger(MyInteger integer){
       if (null == integer) {
           System.out.println("0");
       } else {
           integer.print();
           System.out.print("\n");
       }
   }

    public static MyInteger add(MyInteger obj1, MyInteger obj2)  {
        if (obj1 == null ) {
            if (obj2 == null) return null;
            else return obj2.clone();
        }else {
            if (obj2 == null) return obj1.clone();
            else {
                MyInteger newObj = obj1.clone();
                MyInteger val = obj2.clone();
                MyInteger obj = newObj;
                while (obj.getValue() != null) {
                    obj = obj.getValue();
                }
                obj.setValue(val);
                return newObj;
            }
        }

    }

    public static void main(String[] args){

       // zero
        MyInteger integer1 = null;
        printMyInteger(integer1);

        // two
        MyInteger integer2 = new MyInteger(new MyInteger(null));
        printMyInteger(integer2);

        // two
        printMyInteger(add(integer1, integer2));

        // three
        MyInteger integer3 =  new MyInteger(new MyInteger(new MyInteger(null)));
        printMyInteger(  integer3);

        // five
        printMyInteger(add(integer2, integer3));
    }

}
