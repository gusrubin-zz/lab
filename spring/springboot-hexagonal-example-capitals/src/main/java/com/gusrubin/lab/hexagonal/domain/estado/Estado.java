package com.gusrubin.lab.hexagonal.domain.estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @author Gustavo Rubin
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    private Long id;
    private String nome;
    private String uf;
    private String capital;

}
