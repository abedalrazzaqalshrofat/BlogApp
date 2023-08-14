package com.newagetechsoft.BlogApp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage<T> {

    private List<T> contentResponse;
    private int pageSize;
    private int pageNumber;
    private long totalElement;
    private int totalPages;
    private boolean last;

}
