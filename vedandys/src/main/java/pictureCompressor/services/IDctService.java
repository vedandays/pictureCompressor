package pictureCompressor.services;

public interface IDctService {

    /**
     * Преобразование картинки
     *
     * @return результирующая матрица преобразованных байтов картинки
     */
    Float[][] doDct();

    /**
     * Прямое преобразование ДКП
     *
     * @return матрица преобразованных байтов картинки.
     */
    Float[][] transform();

    /**
     * Обратное преобразование ДКП
     *
     * @return матрица преобразованных байтов картинки.
     */
    Float[][] reTransform();

    /**
     * Задать фактор квантования
     *
     * @param quantizationFactor фактор квантования
     */
    void setQuantizationFactor(Integer quantizationFactor);

    /**
     * Задать размер матрицы
     *
     * @param oneMatrixSize размер матрицы
     */
    void setOneMatrixSize(Integer oneMatrixSize);

    /**
     * Задать матрицу
     *
     * @param matrix матрица байтов картинки
     */
    void setMatrix(Float[][] matrix);
}
