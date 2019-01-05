package pictureCompressor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DctService implements IDctService {
    private MatrixUtilService matrixUtilService;
    private Integer quantizationFactor;
    private Integer oneMatrixSize;
    private Float[][] matrix;
    private Integer[][] quanMatrix;
    private Float[][] DCTMatrix;
    private Float[][] DCTTMatrix;
    private List<Float[][]> resultList;

    @Autowired
    public DctService(MatrixUtilService matrixUtilService) {
        this.matrixUtilService = matrixUtilService;
    }

    @Override
    public Float[][] doDct() {
        transform();
        return reTransform();
    }

    @Override
    public Float[][] transform() {
        List<Float[][]> matrixList = parseMatrix();
        resultList = new ArrayList<>();
        Float[][] curMatrix;
        prepare();

        for (Float[][] aMatrixList : matrixList) {
            curMatrix = aMatrixList;
            curMatrix = matrixUtilService.multiplication(DCTMatrix, curMatrix);
            curMatrix = matrixUtilService.multiplication(curMatrix, DCTTMatrix);
            curMatrix = matrixUtilService.divisionByElements(curMatrix, quanMatrix);
            resultList.add(round(curMatrix));
        }

        return toMatrix(resultList);
    }

    @Override
    public Float[][] reTransform() {
        List<Float[][]> transformMatrixList = new ArrayList<>();
        Float[][] curMatrix;

        for (Float[][] aResultList : resultList) {
            curMatrix = aResultList;
            curMatrix = matrixUtilService.multiplicationByElements(quanMatrix, curMatrix);
            curMatrix = matrixUtilService.multiplication(DCTTMatrix, curMatrix);
            curMatrix = matrixUtilService.multiplication(curMatrix, DCTMatrix);
            transformMatrixList.add(curMatrix);
        }
        return toMatrix(transformMatrixList);
    }

    private void prepare() {
        quanMatrix = getQuanMatrix();
        DCTMatrix = getDCT_matrix();
        DCTTMatrix = matrixUtilService.transpose(DCTMatrix);
    }

    private Integer[][] getQuanMatrix() {
        Integer[][] quanMatrix = new Integer[oneMatrixSize][oneMatrixSize];
        for (int i = 0; i < oneMatrixSize; i++)
            for (int j = 0; j < oneMatrixSize; j++)
                quanMatrix[i][j] = 1 + ((1 + i + j) * quantizationFactor);
        return quanMatrix;
    }

    private Float[][] round(Float[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = matrix[i][j] - (matrix[i][j] - (int) (float) matrix[i][j]);
            }
        }
        return matrix;
    }

    private List<Float[][]> parseMatrix() {
        List<Float[][]> matrixList = new ArrayList<>(matrix.length / oneMatrixSize);

        int col = 0, row = 0;
        for (int t = 0; t < matrix.length / oneMatrixSize; t++, col++) {
            for (int i = 0; i < matrix.length / oneMatrixSize; i++, row++) {
                Float[][] curMat = new Float[oneMatrixSize][oneMatrixSize];
                for (int j = 0; j < oneMatrixSize; j++) {
                    System.arraycopy(matrix[j + col * oneMatrixSize], row * oneMatrixSize, curMat[j], 0, oneMatrixSize);
                }
                matrixList.add(curMat);
            }
            row = 0;
        }
        return matrixList;
    }

    private Float[][] toMatrix(List<Float[][]> matrixList) {
        Float[][] result = new Float[matrix.length][matrix.length];

        int col = 0, row = 0;
        for (int t = 0; t < matrix.length / oneMatrixSize; t++, col++) {
            for (int i = 0; i < matrix.length / oneMatrixSize; i++, row++) {
                Float[][] curMatrix = matrixList.get(row + col * matrix.length / oneMatrixSize);
                for (int j = 0; j < oneMatrixSize; j++) {
                    System.arraycopy(curMatrix[j], 0, result[j + col * oneMatrixSize], row * oneMatrixSize, oneMatrixSize);
                }
            }
            row = 0;
        }
        return result;
    }

    private Float[][] getDCT_matrix() {
        Float[][] matrix = new Float[oneMatrixSize][oneMatrixSize];
        float size = (float) matrix.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) {
                    matrix[i][j] = (float) (1 / Math.sqrt(size));
                } else {
                    matrix[i][j] = (float) (Math.sqrt(2 / size) * Math.cos((2 * j + 1) * i * 3.14 / (2 * size)));
                }
            }
        }
        return matrix;
    }

    @Override
    public void setQuantizationFactor(Integer quantizationFactor) {
        this.quantizationFactor = quantizationFactor;
    }

    @Override
    public void setOneMatrixSize(Integer oneMatrixSize) {
        this.oneMatrixSize = oneMatrixSize;
    }

    @Override
    public void setMatrix(Float[][] matrix) {
        this.matrix = matrix;
    }
}
