package us.dev.shipandcargo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging {
    private long size = 0;
    private long startRow = 0;
    private long endRow = 0;
    private Boolean hasMore;
    private long total = 0;
}
