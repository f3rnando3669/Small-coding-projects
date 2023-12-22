public class StringFixedArrayList implements StringList
{
    String[] list;
    int length;
    
    public StringFixedArrayList(final int size) {
        this.length = 0;
        this.list = new String[size];
    }
    
    public void add(final String x) {
        if (this.length == this.list.length) {
            throw new IndexOutOfBoundsException("no more room.");
        }
        this.list[this.length] = x;
        ++this.length;
    }
    
    public boolean contains(final String x) {
        for (int i = 0; i < this.length; ++i) {
            if (x.equals(this.list[i])) {
                return true;
            }
        }
        return false;
    }
    
    public String get(final int i) {
        if (i < this.length) {
            return this.list[i];
        }
        throw new IndexOutOfBoundsException(new StringBuilder().append(i).toString());
    }
    
    public void remove(final String x) {
        for (int i = 0; i < this.length; ++i) {
            if (x.equals(this.list[i])) {
                for (int j = i; j < this.length - 1; ++j) {
                    this.list[j] = this.list[j + 1];
                }
                --this.length;
                return;
            }
        }
    }
    
    public void clear() {
        this.length = 0;
    }
    
    public int length() {
        return this.length;
    }
    
    public void sort() {
        for (int i = 0; i < this.length - 1; ++i) {
            for (int j = 0; j < this.length - (i + 1); ++j) {
                if (this.list[j].compareTo(this.list[j + 1]) > 0) {
                    final String temp = this.list[j];
                    this.list[j] = this.list[j + 1];
                    this.list[j + 1] = temp;
                }
            }
        }
    }
    
    public void mergeSort() {
        String[] data = new String[this.length];
        for (int i = 0; i < this.length; ++i) {
            data[i] = this.list[i];
        }
        data = this.mergeSort(data);
        for (int i = 0; i < this.length; ++i) {
            this.list[i] = data[i];
        }
    }
    
    private String[] mergeSort(final String[] arr) {
        final int n = arr.length;
        final int n2 = n / 2;
        final int n3 = n - n2;
        String[] ar1 = new String[n2];
        String[] ar2 = new String[n3];
        for (int i = 0; i < n2; ++i) {
            ar1[i] = arr[i];
        }
        for (int i = 0; i < n3; ++i) {
            ar2[i] = arr[i + n2];
        }
        ar1 = this.mergeSort(ar1);
        ar2 = this.mergeSort(ar2);
        int i2 = 0;
        int i3 = 0;
        for (int j = 0; j < n; ++j) {
            if (i3 < n3 && (i2 >= n2 || ar1[i2].compareTo(ar2[i3]) > 0)) {
                arr[j] = ar2[i3];
                ++i3;
            }
            else {
                arr[j] = ar1[i2];
                ++i2;
            }
        }
        return arr;
    }
}
