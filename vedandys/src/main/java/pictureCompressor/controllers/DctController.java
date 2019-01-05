package pictureCompressor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pictureCompressor.controllers.messages.ParamsDctMessage;
import pictureCompressor.services.DctService;

import javax.validation.Valid;

@RequestMapping("/api/dct/")
@RestController
public class DctController {
    private DctService dctService;

    @Autowired
    public DctController(DctService dctService) {
        this.dctService = dctService;
    }

    @PostMapping("process")
    public Float[][] doDct(@Valid @RequestBody ParamsDctMessage paramsDctMessage) {
        dctService.setQuantizationFactor(paramsDctMessage.getQuantizationFactor());
        dctService.setOneMatrixSize(paramsDctMessage.getOneMatrixSize());
        dctService.setMatrix(paramsDctMessage.getImage());
        return dctService.doDct();
    }
}
