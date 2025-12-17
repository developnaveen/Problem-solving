public class exceptionhandling {
    public static void main(String[] args) {

        // ArithmeticException
        try {
            int n = 10;
            int m = 0;
            int c = n / m;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero: " + e.getMessage());
        }

        // NullPointerException
        String s = null;
        try {
            System.out.println(s.length());
        } catch (NullPointerException e) {
            System.out.println("No values: " + e);
        }

        // ArrayIndexOutOfBoundsException (index 3)
        int arr[] = {1, 2, 3};
        try {
            System.out.println(arr[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index issue: " + e);
        }

        // ArrayIndexOutOfBoundsException (negative index)
        try {
            System.out.println(arr[-3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Negative index issue: " + e);
        }

        // NegativeArraySizeException (correct example)
        try {
            int[] arr2 = new int[-3];
        } catch (NegativeArraySizeException e) {
            System.out.println("Cannot create array with negative size: " + e);
        }

        // StringIndexOutOfBoundsException
        String name = "govind";
        try {
            System.out.println(name.charAt(6));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid string index: " + e);
        }

        /* Summary Table (Perfect for Notes)
        Exception	Type	When It Occurs
        ArithmeticException	Runtime	Divide by zero
        NullPointerException	Runtime	Using null object
        ArrayIndexOutOfBoundsException	Runtime	Invalid array index
        StringIndexOutOfBoundsException	Runtime	Invalid string index
        ClassCastException	Runtime	Invalid object cast
        NumberFormatException	Runtime	Parsing invalid number
        IllegalArgumentException	Runtime	Invalid method argument
        IllegalStateException	Runtime	Method called in wrong state
        IOException	Checked	File/network operation failed
        FileNotFoundException	Checked	File missing
        SQLException	Checked	Database errors
        InterruptedException	Checked	Thread interruption
        ConcurrentModificationException	Runtime	Modify collection while iterating
        NoSuchElementException	Runtime	Accessing element that doesn't exist */

    }
}
