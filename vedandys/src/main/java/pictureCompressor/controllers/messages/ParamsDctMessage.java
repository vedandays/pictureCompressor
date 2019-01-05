package pictureCompressor.controllers.messages;

import javax.validation.constraints.NotNull;

public class ParamsDctMessage {
    @NotNull
    private Integer quantizationFactor;

    @NotNull
    private Integer oneMatrixSize;

    @NotNull
    private Float[][] image;

    public Float[][] getImage() {
        return image;
    }

    public void setImage(Float[][] image) {
        this.image = image;
    }

    public Integer getQuantizationFactor() {
        return quantizationFactor;
    }

    public void setQuantizationFactor(Integer quantizationFactor) {
        this.quantizationFactor = quantizationFactor;
    }

    public Integer getOneMatrixSize() {
        return oneMatrixSize;
    }

    public void setOneMatrixSize(Integer oneMatrixSize) {
        this.oneMatrixSize = oneMatrixSize;
    }
}
