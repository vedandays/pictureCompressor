package pictureCompressor.services;

import org.springframework.stereotype.Service;

@Service
public class ImageUtilService {

    //Перемножение матриц
    public Float[][] multiplication(Float[][] a, Float[][] b) {
        Float[][] result = new Float[a.length][a.length];
        result = getZeroMatrix(result.length);

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b.length; j++)
                for (int k = 0; k < a.length; k++)
                    result[i][j] += a[i][k] * b[k][j];

        return result;
    }

    //Поэлементное умножение матриц
    public Float[][] multiplicationByElements(Integer[][] a, Float[][] b) {
        Float[][] result = new Float[a.length][a.length];
        result = getZeroMatrix(result.length);

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++) {
                result[i][j] = a[i][j] * b[i][j];
            }

        return result;
    }

    //Поэлементное деление матриц
    public Float[][] divisionByElements(Float[][] a, Integer[][] b) {
        Float[][] result = new Float[a.length][a.length];
        result = getZeroMatrix(result.length);

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++)
                result[i][j] = a[i][j] / b[i][j];

        return result;
    }

    //Округление матрицы
    private Integer[][] roundMatrix(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                matrix[i][j] = (int) matrix[i][j];
        return matrix;
    }

    //Транспонирование
    public Float[][] transpose(Float[][] matrix) {
        Float[][] result = new Float[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                result[i][j] = matrix[j][i];
        return result;
    }


    //Заполнение матрицы нулями
    private Float[][] getZeroMatrix(int size) {
        Float[][] result = new Float[size][size];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = 0f;
            }
        }
        return result;
    }
}
