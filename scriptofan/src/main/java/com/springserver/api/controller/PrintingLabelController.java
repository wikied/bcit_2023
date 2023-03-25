package com.springserver.api.controller;
import com.springserver.api.model.PrintingLabel;
import com.springserver.api.repository.PrintingLabelRepository;
import com.springserver.api.service.PrintingLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/printing-labels")
public class PrintingLabelController {

    private final PrintingLabelRepository printingLabelRepository;

    @Autowired
    private final PrintingLabelService printingLabelService;

    public PrintingLabelController(PrintingLabelRepository printingLabelRepository, PrintingLabelService printingLabelService) {
        this.printingLabelRepository = printingLabelRepository;
        this.printingLabelService = printingLabelService;
    }

    @GetMapping
    public List<PrintingLabel> getAllPrintingLabels() {
        return printingLabelService.getAllPrintingLabels();
    }

    @GetMapping("/{id}")
    public PrintingLabel getPrintingLabelById(@PathVariable String id) {
        return printingLabelService.getPrintingLabelById(id);
    }

    @PostMapping
    public PrintingLabel createPrintingLabel(@RequestBody PrintingLabel printingLabel) {
        return printingLabelService.createPrintingLabel(printingLabel);
    }

    @PutMapping("/{id}")
    public PrintingLabel updatePrintingLabel(@PathVariable String id, @RequestBody PrintingLabel printingLabelRequest) {
        return printingLabelService.updatePrintingLabel(id, printingLabelRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePrintingLabel(@PathVariable String id) {

        printingLabelService.deletePrintingLabel(id);
    }
}