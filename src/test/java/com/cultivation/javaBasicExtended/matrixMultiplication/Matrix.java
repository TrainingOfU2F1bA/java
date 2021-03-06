package com.cultivation.javaBasicExtended.matrixMultiplication;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {return storage.length;}

    public int columns() {return storage[0].length;}

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        if (matrixArray==null) throw new IllegalArgumentException("Raw matrix is null");
        if (matrixArray.length==0) throw new IllegalArgumentException("Raw matrix contains 0 row");
        for (int[] ints : matrixArray) {
            if (ints == null) throw new IllegalArgumentException("Raw matrix contains null row");
            if (ints.length==0) {
                throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
            }
            if (matrixArray[0].length!=ints.length) throw new IllegalArgumentException("Raw matrix is not rectangle");
        }
            storage=matrixArray;
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left==null||right==null) throw new IllegalArgumentException("input matrix is null");
        if (left.columns()!=right.rows())  throw new IllegalArgumentException("should throws if matrix dimension not correct");
        Matrix result = new Matrix(new int[left.rows()][right.columns()]);
        for (int i = 0; i < left.rows(); i++) {
            int[] row = left.getRow(i);
            for (int k = 0; k < right.columns(); k++) {
                for (int index = 0; index < row.length; index++) {
                    result.storage[i][k] += row[index] * right.storage[index][k];
                }
            }
        }
        return result;
        // --end-->
    }

    // TODO: you can add some helper method if you like.
    // <--start

    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) { throw new IllegalArgumentException(); }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (this == obj) { return true; }
        if (Matrix.class != obj.getClass()) { return false; }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
            .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
