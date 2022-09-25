import java.util.ArrayList;

public class Matrix {
    int rows;
    int cols;
    double[][] data;


    Matrix(int rows,int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[this.rows][this.cols];
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] = 0;
            }
        }
    }

    public static Matrix fromArray(double[] arr) {
        var m = new Matrix(arr.length, 1);
        for (var i = 0; i < arr.length; i++) {
            m.data[i][0] = arr[i];            
        }
        return m;
    }

    public static Matrix subtract(Matrix a,Matrix b) {
        var result = new Matrix(a.rows, a.cols);
        for (var i = 0; i < result.rows; i++) {
            for (var j = 0; j < result.cols; j++) { 
                result.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return result;
    }

    public ArrayList<Double> toArray() {
        ArrayList<Double> arr = new ArrayList<Double>();
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                arr.add(this.data[i][j]);
            }
        }
        return arr;
    }

    public void randomize() {
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public void add(int n) {
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] += n;            
            }
        }
    }
    public void add(Matrix n){
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] += n.data[i][j];
            }
        }
    }

    public static Matrix transpose(Matrix matrix) {
        var result = new Matrix(matrix.cols, matrix.rows);
        for (var i = 0; i < matrix.rows; i++) {
            for (var j = 0; j < matrix.cols; j++) {
                result.data[j][i] = matrix.data[i][j];
            }
        }
        return result;
    }

     public static Matrix multiply(Matrix a,Matrix b) {
        // Matrix product
        if (a.cols != b.rows)
            return null;
        var result = new Matrix(a.rows, b.cols);
        for (var i = 0; i < result.rows; i++) {
            for (var j = 0; j < result.cols; j++) {
                double sum = 0;
                for (var k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public void multiply(Matrix n) {
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] *= n.data[i][j];
            }
        }
    }

    public void multiply(double n){
        // Scalar product
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] *= n;
            }
        }

    }

    public void map(String func) {
        // Apply a function to every element of matrix
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                var val = this.data[i][j];
                if(func.equals("sigmoid")){
                    this.data[i][j] = mapsigmoid(val);
                }
                else if(func.equals("dsigmoid")){
                    this.data[i][j] = mapdsigmoid(val);
                }
            }
        }
    }

    public static double mapsigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public static double mapdsigmoid(double y){
        return y * (1 - y);
    }
    
    public static Matrix map(Matrix matrix,String func) {
        var result = new Matrix(matrix.rows, matrix.cols);
        // Apply a function to every element of matrix
        for (var i = 0; i < matrix.rows; i++) {
            for (var j = 0; j < matrix.cols; j++) {
                double val = matrix.data[i][j];
                if(func.equals("sigmoid")){
                    result.data[i][j] = mapsigmoid(val);
                }else if(func.equals("dsigmoid")){
                    result.data[i][j] = mapdsigmoid(val);
                }
            }
        }
        return result;
    }
}