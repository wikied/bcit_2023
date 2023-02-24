package com.springserver.api.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.springserver.api.controller.PrintingLabelController;
import com.springserver.api.model.Category;
import com.springserver.api.model.Garment;
import com.springserver.api.model.PrintingLabel;
import com.springserver.api.repository.PrintingLabelRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class PrintingLabelControllerTest {

    @Mock
    private PrintingLabelRepository printingLabelRepository;

    @InjectMocks
    private PrintingLabelController printingLabelController;


    @Test
    public void testGetAllPrintingLabels() {

        //Category Mock
//        Category category = new Category();
//        category.setCategoryName("Test");
//        category.setCreatedBy("Test");
//        category.setDescription("Test");
//        category.setUpdateTime(Instant.now());

        // Garment Mock
//        Garment garment = new Garment();
//        garment.setId("g1");
//        garment.setCategory(category);
//        garment.setDescription("A blue T-shirt");

        //--------------------------------------

        List<PrintingLabel> printingLabels = new ArrayList<>();
        PrintingLabel printingLabel1 = new PrintingLabel();
        PrintingLabel printingLabel2 = new PrintingLabel();
        printingLabels.add(printingLabel1);
        printingLabels.add(printingLabel2);
        Mockito.when(printingLabelRepository.findAll()).thenReturn(printingLabels);

        // Act
        List<PrintingLabel> result = printingLabelController.getAllPrintingLabels();

        // Assert
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(printingLabel1, result.get(0));
        Assert.assertEquals(printingLabel2, result.get(1));
    }

    @Test
    public void testGetPrintingLabelById() {
        // Arrange
        PrintingLabel printingLabel = new PrintingLabel();
        Mockito.when(printingLabelRepository.findById("1")).thenReturn(Optional.of(printingLabel));

        // Act
        PrintingLabel result = printingLabelController.getPrintingLabelById("1");

        // Assert
        Assert.assertEquals(printingLabel, result);
    }

    @Test
    public void testCreatePrintingLabel() {
        // Arrange
        PrintingLabel printingLabel = new PrintingLabel();
        Mockito.when(printingLabelRepository.save(printingLabel)).thenReturn(printingLabel);

        // Act
        PrintingLabel result = printingLabelController.createPrintingLabel(printingLabel);

        // Assert
        Assert.assertEquals(printingLabel, result);
    }

    @Test
    public void testUpdatePrintingLabel() {
        // Arrange
        PrintingLabel printingLabel = new PrintingLabel();
        PrintingLabel printingLabelRequest = new PrintingLabel();
        Optional<PrintingLabel> optionalPrintingLabel = Optional.of(printingLabel);
        Mockito.when(printingLabelRepository.findById("1")).thenReturn(optionalPrintingLabel);
        Mockito.when(printingLabelRepository.save(printingLabel)).thenReturn(printingLabel);

        // Act
        PrintingLabel result = printingLabelController.updatePrintingLabel("1", printingLabelRequest);

        // Assert
        Assert.assertEquals(printingLabelRequest.getPrintDate(), result.getPrintDate());
        Assert.assertEquals(printingLabelRequest.getAisleNumber(), result.getAisleNumber());
        Assert.assertEquals(printingLabelRequest.getItemBarcode(), result.getItemBarcode());
        Assert.assertEquals(printingLabelRequest.getGarment(), result.getGarment());
        Assert.assertNotNull(result.getUpdateTime());
        Assert.assertEquals("user", result.getUpdatedBy());
    }

    @Test
    public void testDeletePrintingLabel() {
        // Arrange
        PrintingLabel printingLabel = new PrintingLabel();
        Optional<PrintingLabel> optionalPrintingLabel = Optional.of(printingLabel);
        Mockito.when(printingLabelRepository.findById("1")).thenReturn(optionalPrintingLabel);

        // Act
        ResponseEntity<?> result = printingLabelController.deletePrintingLabel("1");

        // Assert
        Assert.assertEquals(ResponseEntity.ok().build(), result);
        Mockito.verify(printingLabelRepository).delete(printingLabel);
        Mockito.verifyNoMoreInteractions(printingLabelRepository);
    }
}