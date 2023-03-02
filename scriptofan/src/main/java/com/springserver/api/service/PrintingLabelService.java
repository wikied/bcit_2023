package com.springserver.api.service;

import com.springserver.api.model.PrintingLabel;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.PrintingLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrintingLabelService {

    private final PrintingLabelRepository printingLabelRepository;

    @Autowired
    public PrintingLabelService(PrintingLabelRepository printingLabelRepository) {
        this.printingLabelRepository = printingLabelRepository;
    }

    public List<PrintingLabel> getAllPrintingLabels() {
        List<PrintingLabel> printingLabels = new ArrayList<>();
        if (printingLabels == null || !printingLabels.iterator().hasNext()) {
            throw new ResourceNotFoundException("PrintingLabel", "printing labels not found", printingLabels);
        }
        printingLabelRepository.findAll().forEach(printingLabels::add);
        return printingLabels;
    }

    public PrintingLabel getPrintingLabelById(String id) {
        Optional<PrintingLabel> printingLabel = printingLabelRepository.findById(id);
        if (printingLabel.isEmpty()) {
            throw new ResourceNotFoundException("PrintingLabel", "id", id);
        }
        return printingLabelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PrintingLabel", "id", id));
    }

    public PrintingLabel createPrintingLabel(PrintingLabel printingLabel) {
        if (printingLabel == null) {
            throw new IllegalArgumentException("PrintingLabel cannot be null");
        }
        return printingLabelRepository.save(printingLabel);
    }

    public PrintingLabel updatePrintingLabel(String id, PrintingLabel printingLabelRequest) {
        Optional<PrintingLabel> printingLabel = printingLabelRepository.findById(id);
        if (printingLabel.isEmpty()) {
            throw new ResourceNotFoundException("PrintingLabel", "id", id);
        }
        PrintingLabel updatedPrintingLabel = printingLabel.get();
        updatedPrintingLabel.setPrintDate(printingLabelRequest.getPrintDate());
        updatedPrintingLabel.setAisleNumber(printingLabelRequest.getAisleNumber());
        updatedPrintingLabel.setItemBarcode(printingLabelRequest.getItemBarcode());
        updatedPrintingLabel.setGarment(printingLabelRequest.getGarment());
        updatedPrintingLabel.setUpdateTime(Instant.now());
        updatedPrintingLabel.setUpdatedBy("user"); // set the current user or use a different mechanism to determine who updated the entity
        return printingLabelRepository.save(updatedPrintingLabel);
    }

    public void deletePrintingLabel(String id) {
        Optional<PrintingLabel> printingLabel = printingLabelRepository.findById(id);
        if (printingLabel.isEmpty()) {
            throw new ResourceNotFoundException("PrintingLabel", "id", id);
        }
        printingLabelRepository.delete(printingLabel.get());
    }
}
