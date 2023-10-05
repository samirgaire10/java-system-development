class Overload{
    static int  add(int a , int b)
    {
        return a + b ; 
    }
    
    static int  add(int a , int b , int c)
    {
        return a + b + c ; 
    }
    public static void main (String[] args)
    {
        System.out.println(add(10,20));
        System.out.println(add(10,20,30));
    }
}