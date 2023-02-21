package com.springserver.api.repository;

import com.springserver.api.model.PrintingLabel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrintingLabelRepository extends CrudRepository<PrintingLabel, String> {
}
