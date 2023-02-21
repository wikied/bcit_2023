package com.springserver.api.controller;
import com.springserver.api.model.PrintingLabel;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.PrintingLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/printing-labels")
public class PrintingLabelController {

    private final PrintingLabelRepository printingLabelRepository;

    public PrintingLabelController(PrintingLabelRepository printingLabelRepository) {
        this.printingLabelRepository = printingLabelRepository;
    }

    @GetMapping
    public List<PrintingLabel> getAllPrintingLabels() {
        List<PrintingLabel> printingLabels = new ArrayList<>();
        printingLabelRepository.findAll().forEach(printingLabels::add);
        return printingLabels;
    }

    @GetMapping("/{id}")
    public PrintingLabel getPrintingLabelById(@PathVariable String id) {
        return printingLabelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PrintingLabel", "id", id));
    }

    @PostMapping
    public PrintingLabel createPrintingLabel(@RequestBody PrintingLabel printingLabel) {
        return printingLabelRepository.save(printingLabel);
    }

    @PutMapping("/{id}")
    public PrintingLabel updatePrintingLabel(@PathVariable String id, @RequestBody PrintingLabel printingLabelRequest) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrintingLabel(@PathVariable String id) {
        Optional<PrintingLabel> printingLabel = printingLabelRepository.findById(id);
        if (printingLabel.isEmpty()) {
            throw new ResourceNotFoundException("PrintingLabel", "id", id);
        }
        printingLabelRepository.delete(printingLabel.get());
        return ResponseEntity.ok().build();
    }
}