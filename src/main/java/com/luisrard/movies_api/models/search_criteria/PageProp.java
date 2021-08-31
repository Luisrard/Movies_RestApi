package com.luisrard.movies_api.models.search_criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * Product to contains the info of the page to be showed as the size, the page, the sort direction and the sort by
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageProp {
    private int page = 0;
    private int size = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy;
}
