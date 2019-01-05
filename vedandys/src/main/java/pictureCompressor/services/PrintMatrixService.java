package pictureCompressor.services;

import org.springframework.stereotype.Service;

@Service
public class PrintMatrixService {
    public void printMatrix(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
